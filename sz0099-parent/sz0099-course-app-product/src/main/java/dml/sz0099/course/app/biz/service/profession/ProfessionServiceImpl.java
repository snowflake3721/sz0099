package dml.sz0099.course.app.biz.service.profession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.lang.time.DateUtils;
import org.codehaus.plexus.util.StringUtils;
import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.profession.ProfessionDao;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;
import dml.sz0099.course.app.persist.entity.profession.Profession;


/**
 * 
 * @formatter:off
 * description: ProfessionServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionServiceImpl extends GenericServiceImpl<Profession, Long> implements ProfessionService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionServiceImpl.class);

	@Autowired
	private ProfessionDao professionDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionDao;
	}

	/**
	 * 根据Id查询Profession实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Profession findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		Profession profession = professionDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, profession);
		return profession;
	}
	
	@Override
	public Profession findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		Profession profession = professionDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, profession);
		return profession;
	}

	/**
	 * 根据IdList查询Profession实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Profession> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<Profession> professionList = professionDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionList);
		return professionList;
	}

	@Transactional
	@Override
	public Profession persistEntity(Profession profession) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		String title = profession.getTitle();
		profession.setTitleLower(StringUtils.lowerCase(title));
		
		Long userId = profession.getCreatedBy();
		if(null != userId) {
			List<Profession> entityList = findByUserIdAndMainType(Profession.MAINTYPE_9_MAIN.getValueInt(),userId);
			if(entityList==null || entityList.isEmpty()) {
				profession.setMainType(Profession.MAINTYPE_9_MAIN.getValueInt());
			}else {
				profession.setMainType(Profession.MAINTYPE_0_NORMAL.getValueInt());
			}
		}
		Profession entity = save(profession);
		Long id = entity.getId();
		Long aid = entity.getAid();
		entity.setProfessionNo(String.valueOf(aid));
		entity.setOrderSeq(aid);
		String originalLink = profession.getOriginalLink();
		if(StringUtils.isNotBlank(originalLink)) {
			originalLink=originalLink.replace("{id}", String.valueOf(id));
			entity.setOriginalLink(originalLink);
		}
		entity.setSuccess(Profession.SUCCESS_YES);
		entity.setTopLevel(0);//初始化为0
		save(entity);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}
	
	
	@Transactional
	@Override
	public Profession mergeEntity(Profession profession) {
		Long id = profession.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		Profession entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(profession.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(Profession.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public Profession saveOrUpdate(Profession profession) {
		Long id = profession.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		Profession entity = null;
		if(null != id) {
			entity = mergeEntity(profession);
		}else {
			entity = persistEntity(profession);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Profession> findPage(Profession profession, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<Profession> page = professionDao.findPage(profession, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionDao.existById(id);
	}
	
	public Page<Profession> findPublished(Profession profession, Pageable pageable){
		
		Page<Profession> page = professionDao.findPublished(profession, pageable);
		
		return page;
	}
	
	@Transactional
	public Profession mergeForRefresh(Profession profession) {
		Long id = profession.getId();
		Profession entity = findById(id);
		if(null != entity) {
			DateTime lastModifiedDate = new DateTime();
			Date refreshTime = lastModifiedDate.toDate();
			entity.setRefreshTime(refreshTime);
			entity.setLastModifiedBy(profession.getLastModifiedBy());
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			profession.setRefreshTime(refreshTime);
			profession.setSuccess(Profession.SUCCESS_YES);
		}
		return profession;
	}
	
	
	@Transactional
	public Profession mergeForEditQickly(Profession profession) {
		Long id = profession.getId();
		Profession entity = findById(id);
		if(null != entity) {
			DateTime lastModifiedDate = new DateTime();
			String title = profession.getTitle();
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			
			Integer recommend = entity.getRecommend();
			if(null == recommend) {
				entity.setRecommend(recommend);
			}
			
			entity.setName(profession.getName());
			
			Integer preIntroType = profession.getPreIntroType();
			String preIntro = profession.getPreIntro();
			if(null== preIntroType || Profession.PREINTRO_TYPE_NO.getValueInt().equals(preIntroType)) {
				preIntro=null;
			}
			entity.setPreIntroType(preIntroType);
			entity.setPreIntro(preIntro);
			
			//entity.setPreIntro(profession.getPreIntro());
			//entity.setPreIntroType(profession.getPreIntroType());
			entity.setSubTitle(profession.getSubTitle());
			
			entity.setLastModifiedBy(profession.getLastModifiedBy());
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			profession.setSuccess(Profession.SUCCESS_YES);
		}
		return profession;
	}
	
	@Transactional
	public Profession mergeForPublish(Profession profession) {
		Long id = profession.getId();
		Profession entity = findById(id);
		if(null != entity) {
			entity=mergeForBaseinfo(entity, profession);
			//entity=mergeProductForPrice(entity, product);
			entity.setPublishStatus(Profession.PUBLISH_STATUS_PUBLISH.getValueInt());
			
			Date refreshTime = entity.getRefreshTime();
			Date publishTime = entity.getPublishTime();
			Date newTime = new Date();
			if(null == refreshTime) {
				//首次发布，初始化刷新时间。当撤回进行二次发布时，刷新时间不变
				entity.setRefreshTime(newTime);
			}
			if(null == publishTime) {
				//首次发布，初始化发布时间。当撤回进行二次发布时，发布时间不变
				entity.setPublishTime(newTime);
				entity.setSayword(profession.getSayword());//留下传说
				entity.setHeadImg(profession.getHeadImg());
				entity.setNickname(profession.getNickname());
			}
			Long saywordId = entity.getSaywordId();
			if(null == saywordId) {
				entity.setSaywordId(profession.getSaywordId());
			}
			save(entity);
		}
		return profession;
	}
	
	@Transactional
	@Override
	public Profession mergeForUnPublished(Profession profession) {
		Long id = profession.getId();
		Profession entity = findById(id);
		if(null != entity) {
			entity.setLastModifiedBy(profession.getLastModifiedBy());
			DateTime dateTime = new DateTime();
			entity.setLastModifiedDate(dateTime);
			entity.setPublishStatus(profession.getPublishStatus());
			save(entity);
			profession.setSuccess(Profession.SUCCESS_YES);
		}
		return profession;
	}
	
	@Transactional
	public Profession mergeForClosed(Profession product) {
		Long id = product.getId();
		Profession entity = findById(id);
		if(null != entity) {
			DateTime closedTime = new DateTime();
			entity.setClosedTime(closedTime.toDate());
			entity.setPublishStatus(Profession.PUBLISH_STATUS_CLOSED.getValueInt());
			entity.setLastModifiedBy(product.getLastModifiedBy());
			entity.setLastModifiedDate(closedTime);
			save(entity);
			product=entity;
			product.setSuccess(Profession.SUCCESS_YES);
		}
		return product;
	}
	@Transactional
	public Profession mergeForDeleted(Profession profession) {
		Long id = profession.getId();
		Profession entity = findById(id);
		if(null != entity) {
			DateTime deletedTime = new DateTime();
			entity.setDeleted(profession.getDeleted());
			entity.setLastModifiedBy(profession.getLastModifiedBy());
			entity.setLastModifiedDate(deletedTime);
			save(entity);
			profession=entity;
			profession.setSuccess(Profession.SUCCESS_YES);
		}
		return profession;
	}
	
	
	
	@Transactional
	@Override
	public Profession mergeForBaseinfo(Profession profession) {
		Profession entity = mergeForBaseinfo(null, profession);
		entity = mergeProfessionForTitle(null, profession);
		if(null != entity) {
			entity.setLastModifiedBy(profession.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
			entity.setSuccess(Profession.SUCCESS_YES);
		}
		return entity;
	}
	
	public Profession mergeForBaseinfo(Profession entity, Profession profession) {
		Long id = profession.getId();
		if(null == entity) {
			entity = findById(id);
		}
		if (null != entity) {
			entity.setName(profession.getName());
			String title = profession.getTitle();
			entity.setTitle(title);
			Integer recommend = profession.getRecommend();
			Integer recommendE = entity.getRecommend();
			if(recommend==null && recommendE==null) {
				entity.setRecommend(Profession.RECOMMEND_NO.getValueInt());//设置为非推荐
			}
			entity.setTitleLower(StringUtils.lowerCase(title));
			entity.setDescription(profession.getDescription());
			
		}
		return entity;
	}
	
	
	@Transactional
	@Override
	public Profession mergeProfessionForTitle(Profession profession) {
		Profession entity = mergeProfessionForTitle(null, profession);
		if(null != entity) {
			entity.setLastModifiedBy(profession.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
			entity.setSuccess(Profession.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public Profession mergeArticleForTitleOnly(Profession profession) {
		Long id = profession.getId();
		Profession	entity = profession;
		if(null != id) {
			entity = findById(id);
			String title = profession.getTitle();
			entity.setTitle(title);
			String titleLower = StringUtils.lowerCase(title);
			entity.setTitleLower(titleLower);
			String name = entity.getName();
			if(StringUtils.isBlank(name)) {
				entity.setName(title);
			}
			entity.setLastModifiedBy(profession.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
			entity.setSuccess(Profession.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public Profession mergeArticleForDescriptionOnly(Profession profession) {
		Long id = profession.getId();
		Profession	entity = profession;
		if(null != id) {
			entity = findById(id);
			String description = profession.getDescription();
			entity.setDescription(description);
			entity.setLastModifiedBy(profession.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
			entity.setSuccess(Profession.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	public Profession mergeProfessionForTitle(Profession entity , Profession profession) {
		Long id = profession.getId();
		if(null == entity) {
			entity = findById(id);
		}
		if(null != entity) {
			
			fillPreIntro(entity, profession);
			
			entity.setSubTitle(profession.getSubTitle());
			entity.setTitle(profession.getTitle());
			entity.setPenname(profession.getPenname());
			String titleLower = StringUtils.lowerCase(entity.getTitle());
			entity.setTitleLower(titleLower);
			
			String professionNo = entity.getProfessionNo();
			if(StringUtils.isBlank(professionNo)) {
				entity.setProfessionNo(String.valueOf(entity.getAid()));
			}
			
		}
		return entity;
	}
	
	private void fillPreIntro(Profession entity, Profession profession) {
		String preIntro = profession.getPreIntro();
		Integer preIntroType = profession.getPreIntroType();
		if(null == preIntroType || preIntroType.equals(Profession.PREINTRO_TYPE_NO.getValueInt())) {
			entity.setPreIntro("");
		}
		if(StringUtils.isNotBlank(preIntro)){
			entity.setPreIntro(preIntro);
		}
		entity.setPreIntroType(preIntroType);
	}
	
	
	
	@Transactional
	@Override
	public Profession createDraft(Profession profession) {
		//执行数据初始化
		profession.setPublishStatus(Profession.PUBLISH_STATUS_DRAFT.getValueInt());
		//profession.setShelved(Profession.SHELVED_NO.getValueInt());
		profession.setGrade(CoeGrade.GRADE_L0);
		
		Date expiredTime = DateUtils.addDays(new Date(), 365);
		profession.setExpiredTime(expiredTime);
		
		//CoeGrade grade = coeGradeService.findByGrade(CoeGrade.GRADE_L0);
		//profession.setRates(grade.getRates());
		//profession.setStrategy(Profession.STRATEGY_0_COMMON.getValueInt());
		
		Profession entity = persistEntity(profession);
		return entity;
	}
	
	@Override
	public List<Profession> findDraftList(Profession profession) {
		
		Long userId = profession.getUserId();
		
		if(null != userId) {
			return professionDao.findDraftList(profession);
		}
		
		return null;
	}
	
	public Long countDraftList(Profession profession) {
		return professionDao.countDraftList(profession);
	}
	
	public List<Profession> findByUserId(Long userId) {
		return professionDao.findByUserId( userId) ;
	}
	
	public Long countByUserId(Long userId) {
		return professionDao.countByUserId(userId);
	}
	
	public List<Profession> findByPublished(Profession profession){
		
		String title = profession.getTitle();
		if(StringUtils.isNotBlank(title)) {
			//findByTitle
			return findPublishedByTitle(title);
		}
		String name = profession.getName();
		if(StringUtils.isNotBlank(name)) {
			//findByName
			return findPublishedByName(name);
		}
		return null;
	}
	
	public Page<Profession> findByPublished(Profession profession, Pageable pageable){
		return professionDao.findByPublished( profession,  pageable);
	}
	
	public List<Profession> findPublishedByName(String name){
		return professionDao.findPublishedByName(name);
	}
	
	public List<Profession> findPublishedByTitle(String title){
		return professionDao.findPublishedByTitle(title);
	}
	
	public Page<Profession> findByPublishedNotSelf(Profession profession, Pageable pageable){
		return professionDao.findByPublishedNotSelf(profession,pageable);
	}
	
	public Long countForPublishedWithoutSelf(Long userId, Long positionId) {
		
		return professionDao.countForPublishedWithoutSelf(userId, positionId);
	}
	
	public Page<Profession> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable) {
		return professionDao.findPageByMainTypeAndUserId( mainType, userIdList, publishStatus, pageable);
	}
	public Page<Profession> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable) {
		Integer mainType = Profession.MAINTYPE_9_MAIN.getValueInt();
		Integer publishStatus=Profession.PUBLISH_STATUS_PUBLISH.getValueInt();
		return professionDao.findPageByMainTypeAndUserId( mainType, userIdList, publishStatus, pageable);
	}
	
	public List<Profession> findListByMainTypeAndUserId(List<Long> userIdList){
		Integer mainType = Profession.MAINTYPE_9_MAIN.getValueInt();
		Integer publishStatus=Profession.PUBLISH_STATUS_PUBLISH.getValueInt();
		return professionDao.findListByMainTypeAndUserId(mainType, userIdList, publishStatus);
	}
	
	@Transactional
	public Profession mergeForMainType(Profession profession) {
		Long userId = profession.getUserId();
		Long id = profession.getId();
		Integer mainType = profession.getMainType();
		DateTime dateTime = new DateTime();
		if(Profession.MAINTYPE_9_MAIN.getValueInt().equals(mainType)){
			//更新主技能
			List<Profession> entityList = findByUserId(userId);
			if(null != entityList && !entityList.isEmpty()) {
				List<Profession> needModifyList = new ArrayList<>();
				for(Profession entity : entityList) {
					Long eid = entity.getId();
					Integer eMainType = entity.getMainType();
					boolean isOwn = id.equals(eid);
					
					if(Profession.MAINTYPE_9_MAIN.getValueInt().equals(eMainType) && !isOwn) {
						entity.setMainType(Profession.MAINTYPE_0_NORMAL.getValueInt());
						entity.setLastModifiedBy(userId);
						entity.setLastModifiedDate(dateTime);
						needModifyList.add(entity);
						continue;
					}
					if(isOwn) {
						entity.setMainType(Profession.MAINTYPE_9_MAIN.getValueInt());
						entity.setLastModifiedBy(userId);
						entity.setLastModifiedDate(dateTime);
						needModifyList.add(entity);
						profession=entity;
					}
				}
				if(!needModifyList.isEmpty()) {
					save(needModifyList);
				}
			}
		}else {
			//非主技能，直接更新
			Profession entity = findById(id);
			if(!Profession.MAINTYPE_9_MAIN.getValueInt().equals(mainType)) {
				entity.setMainType(mainType);
				entity.setLastModifiedBy(userId);
				entity.setLastModifiedDate(dateTime);
			}
			save(entity);
			profession=entity;
		}
		
		
		
		return profession;
	}
	
	public List<Profession> findPublishByUserIdAndMainType( Integer mainType, Long userId) {
		//public List<Profession> findByUserIdAndMainType(Integer mainType,Long userId, Integer publishStatus)
		Integer publishStatus = Profession.PUBLISH_STATUS_PUBLISH.getValueInt();
		List<Profession> entityList = professionDao.findByUserIdAndMainType(mainType, userId, publishStatus);
		return entityList;
	}
	
	public List<Profession> findByUserIdAndMainType( Integer mainType, Long userId) {
		List<Profession> entityList = professionDao.findByUserIdAndMainType(mainType, userId);
		return entityList;
	}

}
