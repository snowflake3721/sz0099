package dml.sz0099.course.app.biz.service.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.user.CoeUserVerifyDao;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserImage;
import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;


/**
 * 
 * @formatter:off
 * description: CoeUserVerifyServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeUserVerifyServiceImpl extends GenericServiceImpl<CoeUserVerify, Long> implements CoeUserVerifyService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserVerifyServiceImpl.class);

	@Autowired
	private CoeUserVerifyDao coeUserVerifyDao;
	
	@Autowired
	private CoeUserService coeUserService;
	
	
	@Autowired
	private CoeUserImageService coeUserImageService;
	
	
	

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeUserVerifyDao;
	}

	/**
	 * 根据Id查询CoeUserVerify实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserVerify findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeUserVerify coeUserVerify = coeUserVerifyDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeUserVerify);
		return coeUserVerify;
	}
	
	@Override
	public CoeUserVerify findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserVerify coeUserVerify = coeUserVerifyDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserVerify);
		return coeUserVerify;
	}

	/**
	 * 根据IdList查询CoeUserVerify实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserVerify> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeUserVerify> coeUserVerifyList = coeUserVerifyDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeUserVerifyList);
		return coeUserVerifyList;
	}

	@Transactional
	@Override
	public CoeUserVerify persistEntity(CoeUserVerify coeUserVerify) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeUserVerify	entity = save(coeUserVerify);
		Long id = coeUserVerify.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeUserVerify.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeUserVerify mergeEntity(CoeUserVerify coeUserVerify) {
		Long id = coeUserVerify.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeUserVerify entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeUserVerify.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeUserVerify.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeUserVerify saveOrUpdate(CoeUserVerify coeUserVerify) {
		Long id = coeUserVerify.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserVerify entity = null;
		entity = findById(id);
		if(null != id && null != entity) {
			entity = mergeEntity(coeUserVerify);
		}else {
			entity = persistEntity(coeUserVerify);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserVerify> findPage(CoeUserVerify coeUserVerify, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeUserVerify> page = coeUserVerifyDao.findPage(coeUserVerify, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeUserVerifyDao.existById(id);
	}
	
	public CoeUserVerify findByIdentity(CoeUserVerify coeUser) {
		String identity = coeUser.getIdentity();
		return coeUserVerifyDao.findByIdentity(identity);
	}
	
	public CoeUserVerify findNotSelfByIdentity(CoeUserVerify coeUser) {
		return coeUserVerifyDao.findNotSelfByIdentity(coeUser);
	}
	
	public CoeUserVerify findByUserId(Long userId) {
		CoeUserVerify verify = coeUserVerifyDao.findByUserId(userId);
		if(null != verify) {
			CoeUser coeUser = coeUserService.findByUserId(userId);
			verify.setCoeUser(coeUser);
		}
		return verify;
	}
	
	public CoeUserVerify findByCoeUserId(Long coeUserId) {
		return coeUserVerifyDao.findByCoeUserId(coeUserId);
	}
	
	
	public CoeUserVerify findByCoeUserId(Long coeUserId, boolean withImages) {
		CoeUserVerify coeUserVerify = findByCoeUserId(coeUserId);
		if(withImages && null != coeUserVerify) {
			List<CoeUserImage>  images = coeUserImageService.findByCoeUserIdAndSubIdList(coeUserId, CoeUserImage.SUBIDLIST_VERIFY);
			if(null != images && !images.isEmpty()) {
				for(CoeUserImage image: images) {
					Long subId = image.getSubId();
					if(CoeUserImage.SUBID_IDENTITY_FACE.equals(subId)) {
						coeUserVerify.setIdImgFaceE(image);
					}else if(CoeUserImage.SUBID_IDENTITY_BACK.equals(subId)) {
						coeUserVerify.setIdImgBackE(image);
					}
				}
			}
		}
		return coeUserVerify;
	}
	
	/**
	 * 提交认证申请
	 */
	@Transactional
	public CoeUserVerify applyIdentity(CoeUserVerify coeUser) {
		Long userId = coeUser.getUserId();
		CoeUserVerify entity = findByUserId(userId);
		
		if(null != entity ) {
			Integer vefiryStatus = entity.getIdstatus();
			if(null==vefiryStatus || CoeUserVerify.IDSTATUS_0_NO.getValueInt().equals(vefiryStatus)
					|| CoeUserVerify.IDSTATUS_3_REJECT.getValueInt().equals(vefiryStatus)
			) {
				entity.setIdentity(coeUser.getIdentity());
				entity.setRealname(coeUser.getRealname());
				DateTime dataTime = new DateTime();
				entity.setIdstatus(CoeUserVerify.IDSTATUS_1_PROCESS.getValueInt());
				entity.setApplyTime(dataTime.toDate());
				entity.setLastModifiedBy(coeUser.getLastModifiedBy());
				entity.setLastModifiedDate(dataTime);
				save(entity);
				entity.setSuccess(CoeUser.SUCCESS_YES);
			}
		}
		return entity;
	}
	
	
	/**
	 * 审核认证
	 */
	@Transactional
	public CoeUserVerify verifyIdentity(CoeUserVerify coeUser) {
		Long userId = coeUser.getUserId();
		CoeUserVerify entity = findByUserId(userId);
		if(null != entity ) {
			Integer vefiryStatus = entity.getIdstatus();
			if(!CoeUserVerify.IDSTATUS_0_NO.getValueInt().equals(vefiryStatus)
					&& null !=vefiryStatus
					) {
				DateTime dataTime = new DateTime();
				entity.setIdstatus(coeUser.getIdstatus());
				entity.setVerifyTime(dataTime.toDate());
				entity.setRemark(coeUser.getRemark());
				entity.setLastModifiedBy(coeUser.getLastModifiedBy());
				entity.setLastModifiedDate(dataTime);
				save(entity);
				entity.setSuccess(CoeUser.SUCCESS_YES);
			}
		}
		return entity;
	}
	
	@Transactional
	public CoeUserVerify deleteImage(CoeUserVerify coeUser) {
	
		Long userId = coeUser.getUserId();
		Long subId = coeUser.getSubId(); 
		CoeUserVerify entity = findByUserId(userId);
		if(entity.getId().equals(coeUser.getId())) {
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			if(CoeUserImage.SUBID_IDENTITY_FACE.equals(subId)) {
				entity.setIdentityFace(null);
			}else if(CoeUserImage.SUBID_IDENTITY_BACK.equals(subId)) {
				entity.setIdentityBack(null);
			}
			save(entity);
			entity.setSuccess(CoeUserVerify.SUCCESS_YES);
		}
		
		return entity;
	}
	
	@Transactional
	public CoeUserVerify mergeForRealname(CoeUserVerify coeUser){
		Long userId = coeUser.getUserId();
		CoeUserVerify entity = findByUserId(userId);
		if(null != entity) {
			entity.setRealname(coeUser.getRealname());
			entity.setLastModifiedBy(coeUser.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}
	
	
	public List<CoeUserVerify> findByUserIdList(List<Long> userIdList) {
		LOGGER.debug("--- service>>>findByUserIdList  ---------  ");
		List<CoeUserVerify> coeUserVerifyList = coeUserVerifyDao.findByUserIdList(userIdList);
		return coeUserVerifyList;
	}
	
	public Page<CoeUserVerify> findPageForVerify(CoeUserVerify coeUserVerify, Pageable pageable){
		Page<CoeUserVerify> page = coeUserVerifyDao.findPageForVerify(coeUserVerify, pageable);
		if(null != page && page.getTotalElements() > 0) {
			List<CoeUserVerify> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				Map<Long, CoeUserVerify> userMap = new HashMap<>(content.size());
				List<Long> userIdList = new ArrayList<>();
				for(CoeUserVerify verify : content) {
					Long userId = verify.getUserId();
					userMap.put(userId, verify);
					userIdList.add(userId);
				}
				List<CoeUser> userList = coeUserService.findByUserIdList(userIdList);
				if(null != userList && !userList.isEmpty()) {
					for(CoeUser user : userList) {
						Long userId = user.getUserId();
						CoeUserVerify cu = userMap.get(userId);
						if(null != cu) {
							cu.setCoeUser(user);
						}
					}
				}
			}
		}
		return page;
	}

}
