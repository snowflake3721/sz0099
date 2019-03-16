package dml.sz0099.course.app.biz.service.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.codehaus.plexus.util.StringUtils;
import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.service.product.CoeGradeService;
import dml.sz0099.course.app.persist.dao.activity.CoeActivityDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
import dml.sz0099.course.app.persist.entity.profession.Profession;


/**
 * 
 * @formatter:off
 * description: CoeActivityServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeActivityServiceImpl extends GenericServiceImpl<CoeActivity, Long> implements CoeActivityService, Serializable {

	private static final long serialVersionUID = 2544053574902113969L;

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityServiceImpl.class);

	@Autowired
	private CoeActivityDao coeActivityDao;
	
	@Autowired
	private CoeGradeService coeGradeService;
	
	@Autowired
	private CoeActivityFeeService coeActivityFeeService;
	
	@Autowired
	private CoeActivityTimeService coeActivityTimeService;
	
	@Autowired
	private CoeCategActivityService coeCategActivityService;
	
	@Autowired
	private PhotoParagService photoParagService;

	@Autowired
	private CoeActivityTagService coeActivityTagService;
	
	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeActivityDao;
	}

	/**
	 * 根据Id查询CoeActivity实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivity findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeActivity coeActivity = coeActivityDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeActivity);
		return coeActivity;
	}
	
	public boolean existById(Long id) {
		CoeActivity coeActivity = coeActivityDao.findById(id);
		return coeActivity!=null;
	}

	/**
	 * 根据IdList查询CoeActivity实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivity> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeActivity> coeActivityList = coeActivityDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeActivityList);
		return coeActivityList;
	}
	
	public List<CoeActivity> findPublishByIdList(List<Long> idList){
		Integer publishStatus=CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt();
		List<CoeActivity> coeActivityList = coeActivityDao.findPublishByIdList(idList,publishStatus);
		LOGGER.debug("--- dao>>>findPublishByIdList end ---------  result is {} ", coeActivityList);
		return coeActivityList;
	}
	
	/**
	 * 新建草稿，将会产生产品originalLink,activityNo
	 * @param coeActivity
	 * @return
	 */
	@Transactional
	@Override
	public CoeActivity createDraft(CoeActivity coeActivity) {
		//执行数据初始化
		coeActivity.setPublishStatus(CoeActivity.PUBLISH_STATUS_DRAFT.getValueInt());
		coeActivity.setActStatus(CoeActivity.ACT_STATUS_INIT.getValueInt());
		coeActivity.setActOrganize(CoeActivity.ACT_ORGANIZE_CALLING.getValueInt());//默认为集体活动
		coeActivity.setRecommend(CoeActivity.RECOMMEND_NO.getValueInt());//设置为非推荐
		coeActivity.setPreIntroType(CoeActivity.PREINTRO_TYPE_NO.getValueInt());//设置为无
		coeActivity.setPreIntro(CoeActivity.PREINTRO_TYPE_NO.getLabel());
		coeActivity.setTemplate(CoeActivity.TEMPLATE_NO.getValueInt());
		//coeActivity.setGrade(CoeGrade.GRADE_L0);
		
		//CoeGrade grade = coeGradeService.findByGrade(CoeGrade.GRADE_L0);
		
		CoeActivity entity = persistEntity(coeActivity);
		return entity;
	}

	@Transactional
	@Override
	public CoeActivity persistEntity(CoeActivity coeActivity) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		String title = coeActivity.getTitle();
		coeActivity.setTitleLower(StringUtils.lowerCase(title));
		CoeActivity entity = save(coeActivity);
		Long id = entity.getId();
		Long aid = entity.getAid();
		entity.setActivityNo(String.valueOf(aid));
		String originalLink = coeActivity.getOriginalLink();
		if(StringUtils.isNotBlank(originalLink)) {
			originalLink=originalLink.replace("{id}", String.valueOf(id));
			entity.setOriginalLink(originalLink);
		}
		
		Long userId = coeActivity.getCreatedBy();
		if(null != userId) {
			List<CoeActivity> entityList = findByUserIdAndMainType(CoeActivity.MAINTYPE_9_MAIN.getValueInt(),userId);
			if(entityList==null || entityList.isEmpty()) {
				entity.setMainType(Profession.MAINTYPE_9_MAIN.getValueInt());
			}else {
				entity.setMainType(Profession.MAINTYPE_0_NORMAL.getValueInt());
			}
		}
		entity.setTopLevel(0);//初始化为0
		
		//创建时间
		CoeActivityTime actTime = coeActivityTimeService.addTime(entity);
		entity.setActTimeId(actTime.getId());
		entity.setActTime(actTime);
		
		//创建费用
		CoeActivityFee actFee=coeActivityFeeService.addFee(entity);
		entity.setActFee(actFee);
		entity.setFeeId(actFee.getId());
		
		entity.setSuccess(CoeActivity.SUCCESS_YES);
		
		save(entity);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}
	
	public static void main(String[] args) {
		CoeActivity coeActivity = new CoeActivity();
		Long id=1234400L;
		String originalLink = "/activity/detail/{id}";
		if(StringUtils.isNotBlank(originalLink)) {
			originalLink=originalLink.replace("{id}", String.valueOf(id));
		}
		System.out.println(originalLink);
	}
	
	@Transactional
	@Override
	public CoeActivity saveOrUpdate(CoeActivity coeActivity) {
		Long id = coeActivity.getId();
		if(null != id) {
			return mergeActivity(coeActivity);
		}else {
			return persistEntity(coeActivity);
		}
	}
	
	@Transactional
	@Override
	public CoeActivity mergeActivity(CoeActivity coeActivity) {
		Long id = coeActivity.getId();
		CoeActivity entity = findById(id);
		if(null != entity) {
			entity.setDescription(coeActivity.getDescription());
			entity.setLastModifiedBy(coeActivity.getLastModifiedBy());
			entity.setName(coeActivity.getName());
			String title = coeActivity.getTitle();
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			entity.setLastModifiedBy(coeActivity.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
			coeActivity.setSuccess(CoeActivity.SUCCESS_YES);
		}
		return coeActivity;
	}
	
	@Transactional
	@Override
	public CoeActivity mergeForBaseinfo(CoeActivity coeActivity) {
		CoeActivity entity = mergeForBaseinfo(null, coeActivity);
		if(null != entity) {
			save(entity);
			entity.setSuccess(CoeActivity.SUCCESS_YES);
		}
		return entity;
	}
	
	public CoeActivity mergeForBaseinfo(CoeActivity entity, CoeActivity coeActivity) {
		Long id = coeActivity.getId();
		if(null == entity) {
			entity = findById(id);
		}
		if (null != entity) {
			
			fillPreIntro(entity, coeActivity);
			
			String title = coeActivity.getTitle();
			String name = coeActivity.getName();
			if(StringUtils.isBlank(name)) {
				entity.setName(title);
			}else {
				entity.setName(name);
			}
			
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			entity.setPenname(coeActivity.getPenname());
			entity.setDescription(coeActivity.getDescription());
			entity.setKilometer(coeActivity.getKilometer());
			entity.setDifficulty(coeActivity.getDifficulty());
			entity.setMinNum(coeActivity.getMinNum());
			entity.setMaxNum(coeActivity.getMaxNum());
			entity.setActOrganize(coeActivity.getActOrganize());
			entity.setLastModifiedBy(coeActivity.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}

	/**
	 * @param entity
	 * @param coeActivity
	 */
	private void fillPreIntro(CoeActivity entity, CoeActivity coeActivity) {
		String preIntro = coeActivity.getPreIntro();
		Integer preIntroType = coeActivity.getPreIntroType();
		if(null == preIntroType || preIntroType.equals(CoeActivity.PREINTRO_TYPE_NO.getValueInt())) {
			entity.setPreIntro("");
		}
		if(StringUtils.isNotBlank(preIntro)){
			entity.setPreIntro(preIntro);
		}
		entity.setPreIntroType(preIntroType);
	}
	
	@Transactional
	@Override
	public CoeActivity mergeForUnPublished(CoeActivity coeActivity) {
		Long id = coeActivity.getId();
		CoeActivity entity = findById(id);
		if(null != entity) {
			entity.setLastModifiedBy(coeActivity.getLastModifiedBy());
			DateTime dateTime = new DateTime();
			entity.setLastModifiedDate(dateTime);
			entity.setPublishStatus(coeActivity.getPublishStatus());
			entity.setActStatus(CoeActivity.ACT_STATUS_INIT.getValueInt());
			save(entity);
			coeActivity.setSuccess(CoeActivity.SUCCESS_YES);
		}
		return coeActivity;
	}
	
	@Transactional
	@Override
	public CoeActivity mergeActivityForLink(CoeActivity coeActivity) {
		Long id = coeActivity.getId();
		CoeActivity entity = findById(id);
		if(null != entity) {
			String title = coeActivity.getTitle();
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			save(entity);
			coeActivity.setSuccess(CoeActivity.SUCCESS_YES);
		}
		return coeActivity;
	}
	
	@Transactional
	@Override
	public CoeActivity mergeActivityForTitle(CoeActivity coeActivity) {
		CoeActivity entity = mergeActivityForTitle(null, coeActivity);
		if(null != entity) {
			save(entity);
			entity.setSuccess(CoeActivity.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivity mergeActivityForTitleOnly(CoeActivity coeActivity) {
		Long id = coeActivity.getId();
		CoeActivity	entity = coeActivity;
		if(null != id) {
			entity = findById(id);
			String title = coeActivity.getTitle();
			entity.setTitle(title);
			String titleLower = StringUtils.lowerCase(title);
			entity.setTitleLower(titleLower);
			String name = entity.getName();
			if(StringUtils.isBlank(name)) {
				entity.setName(title);
			}
			entity.setLastModifiedBy(coeActivity.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
			entity.setSuccess(CoeActivity.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivity mergeActivityForDescriptionOnly(CoeActivity coeActivity) {
		Long id = coeActivity.getId();
		CoeActivity	entity = coeActivity;
		if(null != id) {
			entity = findById(id);
			String description = coeActivity.getDescription();
			entity.setDescription(description);
			entity.setLastModifiedBy(coeActivity.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
			entity.setSuccess(CoeActivity.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	public CoeActivity mergeActivityForTitle(CoeActivity entity , CoeActivity coeActivity) {
		Long id = coeActivity.getId();
		if(null == entity) {
			entity = findById(id);
		}
		if(null != entity) {
			Integer preIntroType = coeActivity.getPreIntroType();
			if(null == preIntroType || preIntroType.equals(CoeActivity.PREINTRO_TYPE_NO.getValueInt())) {
				entity.setPreIntro("");
			}else {
				entity.setPreIntro(coeActivity.getPreIntro());
			}
			entity.setPreIntroType(preIntroType);
			entity.setSubTitle(coeActivity.getSubTitle());
			entity.setTitle(coeActivity.getTitle());
			entity.setPenname(coeActivity.getPenname());
			String titleLower = StringUtils.lowerCase(entity.getTitle());
			entity.setTitleLower(titleLower);
			entity.setLastModifiedBy(coeActivity.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}
	
	@Override
	public List<CoeActivity> findDraftList(CoeActivity coeActivity) {
		
		Long userId = coeActivity.getUserId();
		
		if(null != userId) {
			return coeActivityDao.findDraftList(coeActivity);
		}
		
		return null;
	}
	
	public Long countDraftList(CoeActivity coeActivity) {
		return coeActivityDao.countDraftList(coeActivity);
	}
	
	public Long countTemplateForUser(Long userId) {
		Integer template = CoeActivity.TEMPLATE_USER.getValueInt();
		return coeActivityDao.countTemplate(userId,  template);
	}
	
	public CoeActivity findDetail(Long id) {
		return findById(id);
	}
	
	public Page<CoeActivity> findPublished(CoeActivity coeActivity, Pageable pageable){
		
		Page<CoeActivity> page = coeActivityDao.findPublished(coeActivity, pageable);
		
		return page;
	}
	
	@Transactional
	public CoeActivity mergeForRefresh(CoeActivity coeActivity) {
		Long id = coeActivity.getId();
		CoeActivity entity = findById(id);
		if(null != entity) {
			DateTime lastModifiedDate = new DateTime();
			Date refreshTime = lastModifiedDate.toDate();
			entity.setRefreshTime(refreshTime);
			entity.setLastModifiedBy(coeActivity.getLastModifiedBy());
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			coeActivity.setRefreshTime(refreshTime);
			coeActivity.setSuccess(CoeActivity.SUCCESS_YES);
		}
		return coeActivity;
	}
	
	@Transactional
	public CoeActivity mergeForEditQickly(CoeActivity activity) {
		Long id = activity.getId();
		CoeActivity entity = findById(id);
		if(null != entity) {
			DateTime lastModifiedDate = new DateTime();
			String title = activity.getTitle();
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			
			entity.setName(activity.getName());
			
			entity.setPenname(activity.getPenname());
			entity.setKilometer(activity.getKilometer());
			entity.setDifficulty(activity.getDifficulty());
			
			entity.setMinNum(activity.getMinNum());
			entity.setMaxNum(activity.getMaxNum());
			
			fillPreIntro(entity, activity);
			
			entity.setSubTitle(activity.getSubTitle());
			
			entity.setLastModifiedBy(activity.getLastModifiedBy());
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			activity.setSuccess(CoeActivity.SUCCESS_YES);
		}
		return activity;
	}
	
	@Transactional
	public CoeActivity mergeForPublish(CoeActivity activity) {
		Long id = activity.getId();
		CoeActivity entity = findById(id);
		if(null != entity) {
			entity=mergeForBaseinfo(entity, activity);
			entity=mergeActivityForTitle(entity, activity);
			
			entity.setPublishStatus(CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt());
			entity.setActStatus(CoeActivity.ACT_STATUS_CALLING.getValueInt());
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
				entity.setSayword(activity.getSayword());//留下传说
				entity.setHeadImg(activity.getHeadImg());
				entity.setNickname(activity.getNickname());
			}
			
			Long saywordId = entity.getSaywordId();
			if(null == saywordId) {
				entity.setSaywordId(activity.getSaywordId());
			}
			
			save(entity);
		}
		return activity;
	}
	
	@Transactional
	public CoeActivity mergeForClosed(CoeActivity activity) {
		Long id = activity.getId();
		CoeActivity entity = findById(id);
		if(null != entity) {
			DateTime closedTime = new DateTime();
			entity.setClosedTime(closedTime.toDate());
			entity.setPublishStatus(CoeActivity.PUBLISH_STATUS_CLOSED.getValueInt());
			entity.setActStatus(CoeActivity.ACT_STATUS_CLOSED.getValueInt());
			entity.setLastModifiedBy(activity.getLastModifiedBy());
			entity.setLastModifiedDate(closedTime);
			save(entity);
			activity=entity;
			activity.setSuccess(CoeActivity.SUCCESS_YES);
		}
		return activity;
	}
	
	@Transactional
	public CoeActivity mergeForTemplate(CoeActivity activity) {
		Long id = activity.getId();
		CoeActivity entity = findById(id);
		if(null != entity) {
			Integer template = activity.getTemplate();
			if(null == template) {
				template=CoeActivity.TEMPLATE_NO.getValueInt();
			}
			
			entity.setTemplate(template);
			DateTime lastModifiedDate=new DateTime();
			entity.setLastModifiedBy(activity.getLastModifiedBy());
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			
			Integer cascadeParag = activity.getCascadeParag();
			if(null == cascadeParag) {
				cascadeParag=CoeActivity.CASCADEPARAG_YES.getValueInt();
			}
			//级联更新模板状态
			photoParagService.mergeForTemplate(activity);
			
			activity=entity;
			activity.setSuccess(CoeActivity.SUCCESS_YES);
		}
		return activity;
	}
	
	
	
	@Transactional
	public CoeActivity mergeForDeleted(CoeActivity activity) {
		Long id = activity.getId();
		CoeActivity entity = findById(id);
		if(null != entity) {
			DateTime deletedTime = new DateTime();
			entity.setDeleted(activity.getDeleted());
			entity.setLastModifiedBy(activity.getLastModifiedBy());
			entity.setLastModifiedDate(deletedTime);
			save(entity);
			activity=entity;
			activity.setSuccess(CoeActivity.SUCCESS_YES);
		}
		return activity;
	}
	
	public boolean publishedById(Long id) {
		 CoeActivity entity = findById(id);
		 Integer publishStatus = entity.getPublishStatus();
		 boolean published = CoeActivity.PUBLISH_STATUS_PUBLISH.equals(publishStatus);
		return published;
	}
	
	public List<CoeActivity> findByPublished(CoeActivity coeActivity){
		
		String title = coeActivity.getTitle();
		if(StringUtils.isNotBlank(title)) {
			//findByTitle
			return findPublishedByTitle(title);
		}
		String name = coeActivity.getName();
		if(StringUtils.isNotBlank(name)) {
			//findByName
			return findPublishedByName(name);
		}
		return null;
	}
	
	public Page<CoeActivity> findByPublished(CoeActivity coeActivity, Pageable pageable){
		return coeActivityDao.findByPublished( coeActivity,  pageable);
	}
	
	public List<CoeActivity> findPublishedByName(String name){
		return coeActivityDao.findPublishedByName(name);
	}
	
	public List<CoeActivity> findPublishedByTitle(String title){
		return coeActivityDao.findPublishedByTitle(title);
	}
	
	public Page<CoeActivity> findPageByUserId(Long userId , Pageable pageable){
		CoeActivity coeActivity = new CoeActivity();
		
		coeActivity.setUserId(userId);
		coeActivity.setPublishStatus(CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt());
		return coeActivityDao.findPageByUserId( coeActivity ,  pageable);
	}
	
	public Page<CoeActivity> findPageByUserId(CoeActivity coeActivity, Pageable pageable) {
		if (coeActivity == null) {
			coeActivity = new CoeActivity();
		}
		coeActivity.setPublishStatus(CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt());
		return coeActivityDao.findPageByUserId(coeActivity, pageable);
	}
	
	
	public Page<CoeActivity> findByPublishedNotSelf(CoeActivity coeActivity, Pageable pageable){
		return coeActivityDao.findByPublishedNotSelf(coeActivity,pageable);
	}
	
	public Long countForPublishedWithoutSelf(Long userId) {
		return coeActivityDao.countForPublishedWithoutSelf(userId);
	}
	
	
	public Page<CoeActivity> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable) {
		return coeActivityDao.findPageByMainTypeAndUserId( mainType, userIdList, publishStatus, pageable);
	}
	public Page<CoeActivity> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable) {
		Integer mainType = CoeActivity.MAINTYPE_9_MAIN.getValueInt();
		Integer publishStatus=CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt();
		return coeActivityDao.findPageByMainTypeAndUserId( mainType, userIdList, publishStatus, pageable);
	}
	
	public List<CoeActivity> findListByMainTypeAndUserId(List<Long> userIdList){
		Integer mainType = CoeActivity.MAINTYPE_9_MAIN.getValueInt();
		Integer publishStatus=CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt();
		return coeActivityDao.findListByMainTypeAndUserId(mainType, userIdList, publishStatus);
	}
	
	@Transactional
	public CoeActivity mergeForMainType(CoeActivity coeActivity) {
		Long userId = coeActivity.getUserId();
		Long id = coeActivity.getId();
		Integer mainType = coeActivity.getMainType();
		DateTime dateTime = new DateTime();
		if(CoeActivity.MAINTYPE_9_MAIN.getValueInt().equals(mainType)){
			//更新主技能
			List<CoeActivity> entityList = findByUserIdAndMainType(mainType, userId);
			if(null != entityList && !entityList.isEmpty()) {
				List<CoeActivity> needModifyList = new ArrayList<>();
				for(CoeActivity entity : entityList) {
					Long eid = entity.getId();
					Integer eMainType = entity.getMainType();
					boolean isOwn = id.equals(eid);
					
					if(CoeActivity.MAINTYPE_9_MAIN.getValueInt().equals(eMainType) && !isOwn) {
						entity.setMainType(CoeActivity.MAINTYPE_0_NORMAL.getValueInt());
						entity.setLastModifiedBy(userId);
						entity.setLastModifiedDate(dateTime);
						needModifyList.add(entity);
						continue;
					}
					if(isOwn) {
						entity.setMainType(CoeActivity.MAINTYPE_9_MAIN.getValueInt());
						entity.setLastModifiedBy(userId);
						entity.setLastModifiedDate(dateTime);
						needModifyList.add(entity);
						coeActivity=entity;
					}
				}
				
				CoeActivity entity = findById(id);
				entity.setMainType(CoeActivity.MAINTYPE_9_MAIN.getValueInt());
				entity.setLastModifiedBy(userId);
				entity.setLastModifiedDate(dateTime);
				needModifyList.add(entity);
				if(!needModifyList.isEmpty()) {
					save(needModifyList);
				}
			}
		}else {
			//非主技能，直接更新
			CoeActivity entity = findById(id);
			if(!CoeActivity.MAINTYPE_9_MAIN.getValueInt().equals(mainType)) {
				entity.setMainType(mainType);
				entity.setLastModifiedBy(userId);
				entity.setLastModifiedDate(dateTime);
			}
			save(entity);
			coeActivity=entity;
		}
		
		
		
		return coeActivity;
	}
	
	public CoeActivity findMainTypeByUserId(Long userId) {
		Integer mainType = CoeActivity.MAINTYPE_9_MAIN.getValueInt();
		List<CoeActivity> entityList = findPublishByUserIdAndMainType(mainType, userId);
		CoeActivity entity=null;
		if(null != entityList && !entityList.isEmpty()) {
			entity = entityList.get(0);
		}
		return entity;
	}
	
	public List<CoeActivity> findPublishByUserIdAndMainType( Integer mainType, Long userId) {
		//public List<CoeActivity> findByUserIdAndMainType(Integer mainType,Long userId, Integer publishStatus)
		Integer publishStatus = CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt();
		List<CoeActivity> entityList = coeActivityDao.findByUserIdAndMainType(mainType, userId, publishStatus);
		return entityList;
	}
	
	public List<CoeActivity> findByUserIdAndMainType( Integer mainType, Long userId) {
		List<CoeActivity> entityList = coeActivityDao.findByUserIdAndMainType(mainType, userId);
		return entityList;
	}
	
	public List<CoeActivity> findByUserId(Long userId) {
		return coeActivityDao.findByUserId( userId) ;
	}
	
	public Long countByUserId(Long userId) {
		return coeActivityDao.countByUserId(userId);
	}
	
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Pageable pageable){
		Page<CoeActivity> pageReault = null;
		if(CoeActivity.TEMPLATE_COMMON.getValueInt().equals(template)) {
			pageReault = coeActivityDao.findPageForTemplate( template, pageable);
		}else {
			pageReault = coeActivityDao.findPageForTemplate( userId, template, pageable);
		}
		return pageReault;
	}
	
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Long id,Pageable pageable){
		Page<CoeActivity> pageReault = null;
		if(CoeActivity.TEMPLATE_COMMON.getValueInt().equals(template)) {
			pageReault = coeActivityDao.findPageForTemplate( template, pageable);
		}else {
			pageReault = coeActivityDao.findPageForTemplate( userId, template, id, pageable);
		}
		return pageReault;
	}
	
	@Transactional
	public CoeActivity loadTemplate(CoeActivity coeActivity) {
		
		Long id = coeActivity.getId();
		Long templateId = coeActivity.getTemplateId();
		if(null != id && null != templateId) {
			CoeActivity entity=findById(id);
			CoeActivity template=findById(templateId);
			entity=syncFromTemplate( entity ,  template);
			return entity;
		}
		return coeActivity;
	}
	
	@Transactional
	public CoeActivity syncFromTemplate(CoeActivity entity , CoeActivity template) {
		
		if(null == entity || null==template) {
			return null;
		}
		
		entity.setActOrganize(template.getActOrganize());
		entity.setCascadeParag(template.getCascadeParag());
		String description = entity.getDescription();
		if(StringUtils.isBlank(description)) {
			entity.setDescription(template.getDescription());
		}
		Integer kilometer = entity.getKilometer();
		if(null==kilometer || kilometer==0) {
			entity.setKilometer(template.getKilometer());
		}
		entity.setDifficulty(template.getDifficulty());
		Integer maxNum = entity.getMaxNum();
		if(null == maxNum || maxNum==0) {
			entity.setMaxNum(template.getMaxNum());
		}
		
		Integer minNum = entity.getMinNum();
		if(null == minNum || minNum==0) {
			entity.setMinNum(template.getMinNum());
		}
		
		String name = entity.getName();
		if(StringUtils.isBlank(name)) {
			entity.setName(template.getName());
		}
		String penname = entity.getPenname();
		if(StringUtils.isBlank(penname)) {
			entity.setPenname(template.getPenname());
		}
		
		Integer preIntroType = entity.getPreIntroType();
		if(null == preIntroType || CoeActivity.PREINTRO_TYPE_NO.getValueInt().equals(preIntroType)) {
			entity.setPreIntroType(template.getPreIntroType());
			entity.setPreIntro(template.getPreIntro());
		}
		
		String subTitle = entity.getSubTitle();
		if(StringUtils.isBlank(subTitle)) {
			entity.setSubTitle(template.getSubTitle());
			entity.setTitleLower(template.getTitleLower());
		}
		save(entity);
		//同步分类数据
		entity=coeCategActivityService.syncFromTemplate(entity, template);
		
		//同步时间地点数据
		entity=coeActivityTimeService.syncFromTemplate(entity, template);
		
		//同步标签数据
		entity=coeActivityTagService.syncFromTemplate(entity, template);
		
		//同步费用数据
		entity=coeActivityFeeService.syncFromTemplate(entity, template);
		
		//同步段落数据
		entity=photoParagService.syncFromTemplate(entity, template);
		
		//同步封面图、头部图数据(此数据不同步)
		
		return entity;
	}

}
