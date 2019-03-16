package dml.sz0099.course.app.biz.service.user;

import java.io.Serializable;
import java.util.List;

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

import dml.sz0099.course.app.persist.dao.user.CoeUserImageDao;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserImage;
import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;


/**
 * 
 * @formatter:off
 * description: CoeUserImageServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeUserImageServiceImpl extends GenericServiceImpl<CoeUserImage, Long> implements CoeUserImageService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserImageServiceImpl.class);

	@Autowired
	private CoeUserImageDao coeUserImageDao;
	
	@Autowired
	private CoeUserService coeUserService;
	
	@Autowired
	private CoeUserVerifyService coeUserVerifyService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeUserImageDao;
	}

	/**
	 * 根据Id查询CoeUserImage实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserImage findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeUserImage coeUserImage = coeUserImageDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeUserImage);
		return coeUserImage;
	}
	
	@Override
	public CoeUserImage findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserImage coeUserImage = coeUserImageDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserImage);
		return coeUserImage;
	}

	/**
	 * 根据IdList查询CoeUserImage实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeUserImage> coeUserImageList = coeUserImageDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeUserImageList);
		return coeUserImageList;
	}

	@Transactional
	@Override
	public CoeUserImage persistEntity(CoeUserImage coeUserImage) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeUserImage entity = save(coeUserImage);
		Long id = coeUserImage.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeUserImage.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeUserImage mergeEntity(CoeUserImage coeUserImage) {
		Long id = coeUserImage.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeUserImage entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeUserImage.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeUserImage.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeUserImage saveOrUpdate(CoeUserImage coeUserImage) {
		Long id = coeUserImage.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserImage entity = null;
		if(null != id) {
			entity = mergeEntity(coeUserImage);
		}else {
			entity = persistEntity(coeUserImage);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserImage> findPage(CoeUserImage coeUserImage, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeUserImage> page = coeUserImageDao.findPage(coeUserImage, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeUserImageDao.existById(id);
	}

	@Override
	public List<CoeUserImage> findByCoeUserId(Long coeUserId) {
		List<CoeUserImage> content = coeUserImageDao.findByCoeUserId(coeUserId);
		return content;
	}
	
	public List<CoeUserImage> findByCoeUserIdAndSubIdList(Long coeUserId, List<Long> subIdList) {
		List<CoeUserImage> content = coeUserImageDao.findByCoeUserIdAndSubIdList(coeUserId, subIdList);
		return content;
	}
	
	public List<CoeUserImage> findByCoeUserId(Long coeUserId, Long subId) {
		List<CoeUserImage> content = coeUserImageDao.findByCoeUserId(coeUserId, subId);
		return content;
	}
	
	@Transactional
	public void deleteImage(CoeUserImage userImage) {
		
		Long id = userImage.getId();
		
		CoeUserImage entity = findById(id);
		
		if(entity.getCoeUserId().equals(userImage.getCoeUserId()) 
				&& entity.getSubId().equals(userImage.getSubId())) {
			Long userId = entity.getUserId();
			CoeUser userE = coeUserService.findByUserId(userId);
			CoeUserVerify verifyE = coeUserVerifyService.findByUserId(userId);
			Integer idstatus = verifyE.getIdstatus();
			if(idstatus== null || CoeUserVerify.IDSTATUS_0_NO.getValueInt().equals(idstatus) || CoeUserVerify.IDSTATUS_3_REJECT.getValueInt().equals(idstatus)) {
				CoeUser coeUser = new CoeUser();
				coeUser.setUserId(userImage.getUserId());
				coeUser.setId(entity.getCoeUserId());
				coeUser.setSubId(entity.getSubId());
				coeUser.setLastModifiedBy(userImage.getLastModifiedBy());
				
				Long subId = entity.getSubId();
				if(CoeUserImage.SUBIDLIST_USER.contains(subId)) {
					coeUserService.deleteImage(coeUser);
				}else if(CoeUserImage.SUBIDLIST_VERIFY.contains(subId)) {
					CoeUserVerify verify = new CoeUserVerify();
					verify.setUserId(userImage.getUserId());
					verify.setCoeUserId(userImage.getUserId());
					coeUserVerifyService.deleteImage(verify);
				}
				
				delete(entity);
			}
		}
	}

}
