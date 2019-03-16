package dml.sz0099.course.app.biz.service.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.app.biz.service.pipe.MsgProducerService;
import org.jit4j.app.module.define.Jit4jAppModule;
import org.jit4j.app.persist.entity.auth.User;
import org.jit4j.core.pipe.metaq.message.MessageBody;
import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.user.CoeUserDao;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;
import dml.sz0099.course.app.persist.entity.user.CoeUserImage;
import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;


/**
 * 
 * @formatter:off
 * description: CoeUserServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeUserServiceImpl extends GenericServiceImpl<CoeUser, Long> implements CoeUserService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserServiceImpl.class);

	@Autowired
	private CoeUserDao coeUserDao;
	
	@Autowired
	private CoeUserGradeService coeUserGradeService;
	
	@Autowired
	private CoeUserImageService coeUserImageService;
	
	@Autowired
	private CoeUserVerifyService coeUserVerifyService;

	@Autowired
	private SaywordService saywordService;
	
	@Autowired
	private MsgProducerService msgProducerService;
	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeUserDao;
	}

	/**
	 * 根据Id查询CoeUser实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeUser findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeUser coeUser = coeUserDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeUser);
		return coeUser;
	}
	
	@Override
	public CoeUser findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeUser coeUser = coeUserDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUser);
		return coeUser;
	}

	/**
	 * 根据IdList查询CoeUser实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUser> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeUser> coeUserList = coeUserDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeUserList);
		return coeUserList;
	}

	@Transactional
	@Override
	public CoeUser persistEntity(CoeUser coeUser) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		
		coeUser.setPayRecievable(CoeUser.PAYRECIEVABLE_0_NO.getValueInt());
		
		CoeUser entity = save(coeUser);
		
		Long id = coeUser.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeUser.SUCCESS_YES);
		//级联初始化认证信息
		CoeUserVerify verify = coeUser.getUserVerify();
		if(null == verify) {
			verify = new CoeUserVerify();
		}
		Integer idstatus = verify.getIdstatus();
		if(null == idstatus) {
			verify.setIdstatus(CoeUserVerify.IDSTATUS_0_NO.getValueInt());
		}
		verify.setId(entity.getId());
		verify.setUserId(coeUser.getUserId());
		verify.setCoeUserId(id);
		verify.setCreatedBy(coeUser.getCreatedBy());
		verify.setLastModifiedBy(coeUser.getLastModifiedBy());
		coeUserVerifyService.saveOrUpdate(verify);
		return entity;
	}
	
	public CoeUser  findByUserId(Long userId, boolean withVerify) {
		CoeUser entity = coeUserDao.findByUserId(userId);
		if(null != entity && withVerify) {
			CoeUserVerify verify = coeUserVerifyService.findByUserId(userId);
			entity.setUserVerify(verify);
		}
		LOGGER.debug("--- service.findByUserId , userId is:{} ---------", userId);
		return entity;
	}
	
	public CoeUser  findByUserId(Long userId) {
		CoeUser entity = coeUserDao.findByUserId(userId);
		LOGGER.debug("--- service.findByUserId , userId is:{} ---------", userId);
		return entity;
	}
	
	public List<CoeUser> findByUserIdList(List<Long> userIdList, boolean withImages) {
		return coeUserDao.findByUserIdList( userIdList);
	}
	
	public List<CoeUser> findByUserIdList(List<Long> userIdList) {
		return coeUserDao.findByUserIdList( userIdList);
	}
	
	public Map<Long, CoeUser> findMapByUserIdList(List<Long> userIdList, boolean withImages) {
		List<CoeUser> userList = findByUserIdList( userIdList,  withImages);
		if(null != userList && !userList.isEmpty()) {
			Map<Long, CoeUser> map = new HashMap<>();
			for(CoeUser user : userList) {
				Long userId = user.getUserId();
				//user.setNickname(Base64Util.decode(user.getNickname()));
				map.put(userId, user);
			}
			return map;
		}
		return null;
	}
	
	public CoeUser  findByUserId(Long userId, boolean withImages, boolean withVerify, boolean withSayword) {
		CoeUser entity = coeUserDao.findByUserId(userId);
		if(null != entity) {
			Long id = entity.getId();
			if(  withImages ) {
				//List<CoeUserImage>  images = coeUserImageService.findByCoeUserId(id);
				List<CoeUserImage>  images = coeUserImageService.findByCoeUserIdAndSubIdList(id, CoeUserImage.SUBIDLIST_USER);
				if(null != images && !images.isEmpty()) {
					for(CoeUserImage image: images) {
						Long subId = image.getSubId();
						/*if(CoeUserImage.SUBID_IDENTITY_FACE.equals(subId)) {
							entity.setIdImgFaceE(image);
						}else if(CoeUserImage.SUBID_IDENTITY_BACK.equals(subId)) {
							entity.setIdImgBackE(image);
						}else*/ if(CoeUserImage.SUBID_HEADIMAGE.equals(subId)) {
							entity.setHeadImgE(image);
						}else if(CoeUserImage.SUBID_PAY_RECIEVE.equals(subId)) {
							entity.setPayRecieveImgE(image);
						}
					}
				}
			}
			if(withSayword) {
				entity.setSayword(saywordService.findByUserIdAndMainId(userId));
			}
			
			if(withVerify) {
				entity.setUserVerify(coeUserVerifyService.findByCoeUserId(id, true));
			}
		}
		LOGGER.debug("--- service.findByUserId , userId is:{} ---------", userId);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeUser mergeEntity(CoeUser coeUser) {
		Long id = coeUser.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeUser entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeUser.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeUser saveOrUpdate(CoeUser coeUser) {
		Long id = coeUser.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUser entity = null;
		if(null != id) {
			entity = mergeEntity(coeUser);
		}else {
			entity = persistEntity(coeUser);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUser> findPage(CoeUser coeUser, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeUser> page = coeUserDao.findPage(coeUser, pageable);
		return page;
	}

	@Transactional
	@Override
	public CoeUser createCoeUser(CoeUser coeUser) {
		CoeUser entity = persistEntity(coeUser);
		CoeUserGrade userGrade = coeUserGradeService.createUserGrade(coeUser);
		entity.setUserGrade(userGrade);
		return entity;
	}
	
	@Override
	public CoeUser findByEmail(String email) {
		return coeUserDao.findByEmail(email);
	}
	
	@Override
	public CoeUser findByMobile(String mobile) {
		return coeUserDao.findByMobile(mobile);
	}
	
	@Transactional
	public CoeUser mergeForEmail(CoeUser coeUser) {
		Long userId = coeUser.getUserId();
		CoeUser entity = findByUserId(userId);
		if(null != entity) {
			DateTime lastModifiedDate = new DateTime();
			entity.setEmail(coeUser.getEmail());
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			save(entity);
			entity.setSuccess(CoeUser.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	public CoeUser mergeForMobile(CoeUser coeUser) {
		Long userId = coeUser.getUserId();
		CoeUser entity = findByUserId(userId);
		if(null != entity) {
			DateTime lastModifiedDate = new DateTime();
			String mobile=coeUser.getMobile();
			entity.setMobile(mobile);
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			save(entity);
			entity.setSuccess(CoeUser.SUCCESS_YES);
			if(StringUtils.isNotBlank(mobile)) {
				//只有非空时才执行同步
				MessageBody<User> messageBody = new MessageBody<>();
				//messageBody.setContent("=====test from ood prod====");
				messageBody.setUserId(userId);
				User user = new User();
				user.setId(userId);
				user.setMobile(mobile);
				user.setLastModifiedBy(entity.getLastModifiedBy());
				user.setLastModifiedDate(entity.getLastModifiedDate());
				messageBody.setContent(user);
				
				String bizkey = Jit4jAppModule.APP_AUTH_MSG_USER_MERGE_MOBILE.currentMsg();
				messageBody.setCode(bizkey);
				messageBody.setBizkey(bizkey);
				//messageBody.setMsgType(msgType);
				messageBody.setName(Jit4jAppModule.APP_AUTH_MSG_USER_MERGE_MOBILE.currentName());
				LOGGER.debug("====mergeForMobile begin--> send msg: {}, userId:{}, mobile:{}=== ", messageBody.getBizkey(),userId, mobile);
				msgProducerService.send(Jit4jAppModule.APP_AUTH, messageBody);
				LOGGER.debug("====mergeForMobile end--> send msg: {}, userId:{}, mobile:{}=== ", messageBody.getBizkey(),userId, mobile);
			}
		}
		return entity;
	}
	
	@Transactional
	public CoeUser deleteImage(CoeUser coeUser) {
	
		Long userId = coeUser.getUserId();
		Long subId = coeUser.getSubId(); 
		CoeUser entity = findByUserId(userId);
		if(entity.getId().equals(coeUser.getId())) {
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			if(CoeUserImage.SUBID_HEADIMAGE.equals(subId)) {
				entity.setHeadImg(null);
			}else if(CoeUserImage.SUBID_PAY_RECIEVE.equals(subId)) {
				entity.setPayRecieveImg(null);
			}
			save(entity);
			entity.setSuccess(CoeUser.SUCCESS_YES);
		}
		
		return entity;
	}
	
	@Transactional
	public CoeUser mereForImage(CoeUser coeUser) {
	
		Long userId = coeUser.getUserId();
		Long subId = coeUser.getSubId(); 
		
		if(CoeUserImage.SUBIDLIST_USER.contains(subId)) {
			CoeUser entity = findByUserId(userId);
			if(entity.getId().equals(coeUser.getId())) {
				entity.setLastModifiedBy(coeUser.getLastModifiedBy());
				entity.setLastModifiedDate(new DateTime());
				
				List<CoeUserImage> images = coeUser.getImages();
				if(null != images && !images.isEmpty()) {
					images = (List<CoeUserImage>)coeUserImageService.save(images);
					if(CoeUserImage.SUBID_HEADIMAGE.equals(subId)) {
						entity.setHeadImg(coeUser.getHeadImg());
					}else if(CoeUserImage.SUBID_PAY_RECIEVE.equals(subId)) {
						entity.setPayRecieveImg(coeUser.getPayRecieveImg());
					}
					save(entity);
					entity.setSuccess(CoeUser.SUCCESS_YES);
					entity.setImages(images);
					coeUser = entity;
				}
			}
		}else if(CoeUserImage.SUBIDLIST_VERIFY.contains(subId)) {
			CoeUserVerify entity = coeUserVerifyService.findByUserId(userId);
			if(null != entity && entity.getId().equals(coeUser.getId())) {
				entity.setLastModifiedBy(coeUser.getLastModifiedBy());
				entity.setLastModifiedDate(new DateTime());
				
				List<CoeUserImage> images = coeUser.getImages();
				if(null != images && !images.isEmpty()) {
					images = (List<CoeUserImage>)coeUserImageService.save(images);
					
					CoeUserVerify verify = coeUser.getUserVerify();
					if(null != verify) {
						if(CoeUserImage.SUBID_IDENTITY_FACE.equals(subId)) {
							entity.setIdentityFace(verify.getIdentityFace());
						}else if(CoeUserImage.SUBID_IDENTITY_BACK.equals(subId)) {
							entity.setIdentityBack(verify.getIdentityBack());
						}
						coeUserVerifyService.save(entity);
						coeUser.setUserVerify(entity);
						coeUser.setSuccess(CoeUser.SUCCESS_YES);
						coeUser.setImages(images);
					}
				}
			}else {
				List<CoeUserImage> images = coeUser.getImages();
				if(null != images && !images.isEmpty()) {
					images = (List<CoeUserImage>)coeUserImageService.save(images);
				}
				coeUserVerifyService.save(entity);
			}
		}
		return coeUser;
	}
	
	
	
	@Transactional
	public CoeUser mergeForNickname(CoeUser coeUser) {
		Long userId = coeUser.getUserId();
		CoeUser entity = findByUserId(userId);
		if(null != entity) {
			entity.setNickname(coeUser.getNickname());
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}
	
	@Transactional
	public CoeUser mergeForPostname(CoeUser coeUser){
		Long userId = coeUser.getUserId();
		CoeUser entity = findByUserId(userId);
		if(null != entity) {
			entity.setPostname(coeUser.getPostname());
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}
	@Transactional
	@Override
	public CoeUser mergeForPostnameShow(CoeUser coeUser) {
		Long userId = coeUser.getUserId();
		CoeUser entity = findByUserId(userId);
		if(null != entity) {
			entity.setPostnameShow(coeUser.getPostnameShow());
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}
	@Transactional
	@Override
	public CoeUser mergeForMobileShow(CoeUser coeUser) {
		Long userId = coeUser.getUserId();
		CoeUser entity = findByUserId(userId);
		if(null != entity) {
			entity.setMobileShow(coeUser.getMobileShow());
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}
	@Transactional
	@Override
	public CoeUser mergeForEmailShow(CoeUser coeUser) {
		Long userId = coeUser.getUserId();
		CoeUser entity = findByUserId(userId);
		if(null != entity) {
			entity.setEmailShow(coeUser.getEmailShow());
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}
	@Transactional
	@Override
	public CoeUser mergeForQqShow(CoeUser coeUser) {
		Long userId = coeUser.getUserId();
		CoeUser entity = findByUserId(userId);
		if(null != entity) {
			entity.setQqShow(coeUser.getQqShow());
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}
	/**
	 * 该方法暂时废弃，传说由专门的对象Sayword来存储
	 * 原sayword字段可更改成其他名称，merge时依然可用此方法
	 */
	@Transactional
	public CoeUser mergeForSayword(CoeUser coeUser) {
		Long userId = coeUser.getUserId();
		CoeUser entity = findByUserId(userId);
		if(null != entity) {
			entity.setSayword(coeUser.getSayword());
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}
	
	
	
	

}
