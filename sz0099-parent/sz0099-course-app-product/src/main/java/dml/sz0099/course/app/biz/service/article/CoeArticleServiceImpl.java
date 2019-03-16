package dml.sz0099.course.app.biz.service.article;

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
import dml.sz0099.course.app.persist.dao.article.CoeArticleDao;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.profession.Profession;


/**
 * 
 * @formatter:off
 * description: CoeArticleServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeArticleServiceImpl extends GenericServiceImpl<CoeArticle, Long> implements CoeArticleService, Serializable {

	private static final long serialVersionUID = 2544053574902113969L;

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleServiceImpl.class);

	@Autowired
	private CoeArticleDao coeArticleDao;
	
	@Autowired
	private CoeGradeService coeGradeService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeArticleDao;
	}

	/**
	 * 根据Id查询CoeArticle实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticle findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeArticle coeArticle = coeArticleDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeArticle);
		return coeArticle;
	}
	
	public boolean existById(Long id) {
		CoeArticle coeArticle = coeArticleDao.findById(id);
		return coeArticle!=null;
	}

	/**
	 * 根据IdList查询CoeArticle实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticle> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeArticle> coeArticleList = coeArticleDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeArticleList);
		return coeArticleList;
	}
	
	public List<CoeArticle> findPublishByIdList(List<Long> idList){
		Integer publishStatus=CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt();
		List<CoeArticle> coeArticleList = coeArticleDao.findPublishByIdList(idList,publishStatus);
		LOGGER.debug("--- dao>>>findPublishByIdList end ---------  result is {} ", coeArticleList);
		return coeArticleList;
	}
	
	/**
	 * 新建草稿，将会产生产品originalLink,articleNo
	 * @param coeArticle
	 * @return
	 */
	@Transactional
	@Override
	public CoeArticle createDraft(CoeArticle coeArticle) {
		//执行数据初始化
		coeArticle.setPublishStatus(CoeArticle.PUBLISH_STATUS_DRAFT.getValueInt());
		//coeArticle.setGrade(CoeGrade.GRADE_L0);
		
		//CoeGrade grade = coeGradeService.findByGrade(CoeGrade.GRADE_L0);
		
		CoeArticle entity = persistEntity(coeArticle);
		return entity;
	}

	@Transactional
	@Override
	public CoeArticle persistEntity(CoeArticle coeArticle) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		String title = coeArticle.getTitle();
		coeArticle.setTitleLower(StringUtils.lowerCase(title));
		CoeArticle entity = save(coeArticle);
		Long id = entity.getId();
		Long aid = entity.getAid();
		entity.setArticleNo(String.valueOf(aid));
		String originalLink = coeArticle.getOriginalLink();
		if(StringUtils.isNotBlank(originalLink)) {
			originalLink=originalLink.replace("{id}", String.valueOf(id));
			entity.setOriginalLink(originalLink);
		}
		
		Long userId = coeArticle.getCreatedBy();
		if(null != userId) {
			List<CoeArticle> entityList = findByUserIdAndMainType(CoeArticle.MAINTYPE_9_MAIN.getValueInt(),userId);
			if(entityList==null || entityList.isEmpty()) {
				entity.setMainType(Profession.MAINTYPE_9_MAIN.getValueInt());
			}else {
				entity.setMainType(Profession.MAINTYPE_0_NORMAL.getValueInt());
			}
		}
		entity.setTopLevel(0);//初始化为0
		entity.setSuccess(CoeArticle.SUCCESS_YES);
		
		save(entity);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}
	
	public static void main(String[] args) {
		CoeArticle coeArticle = new CoeArticle();
		Long id=1234400L;
		String originalLink = "/article/detail/{id}";
		if(StringUtils.isNotBlank(originalLink)) {
			originalLink=originalLink.replace("{id}", String.valueOf(id));
		}
		System.out.println(originalLink);
	}
	
	@Transactional
	@Override
	public CoeArticle saveOrUpdate(CoeArticle coeArticle) {
		Long id = coeArticle.getId();
		if(null != id) {
			return mergeArticle(coeArticle);
		}else {
			return persistEntity(coeArticle);
		}
	}
	
	@Transactional
	@Override
	public CoeArticle mergeArticle(CoeArticle coeArticle) {
		Long id = coeArticle.getId();
		CoeArticle entity = findById(id);
		if(null != entity) {
			entity.setDescription(coeArticle.getDescription());
			entity.setLastModifiedBy(coeArticle.getLastModifiedBy());
			entity.setName(coeArticle.getName());
			String title = coeArticle.getTitle();
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			entity.setLastModifiedBy(coeArticle.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
			coeArticle.setSuccess(CoeArticle.SUCCESS_YES);
		}
		return coeArticle;
	}
	
	@Transactional
	@Override
	public CoeArticle mergeForBaseinfo(CoeArticle coeArticle) {
		CoeArticle entity = mergeForBaseinfo(null, coeArticle);
		if(null != entity) {
			save(entity);
			entity.setSuccess(CoeArticle.SUCCESS_YES);
		}
		return entity;
	}
	
	public CoeArticle mergeForBaseinfo(CoeArticle entity, CoeArticle coeArticle) {
		Long id = coeArticle.getId();
		if(null == entity) {
			entity = findById(id);
		}
		if (null != entity) {
			
			fillPreIntro(entity, coeArticle);
			
			String title = coeArticle.getTitle();
			String name = coeArticle.getName();
			if(StringUtils.isBlank(name)) {
				entity.setName(title);
			}else {
				entity.setName(name);
			}
			entity.setRecommend(CoeArticle.RECOMMEND_NO.getValueInt());//设置为非推荐
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			entity.setPenname(coeArticle.getPenname());
			entity.setDescription(coeArticle.getDescription());
			entity.setLastModifiedBy(coeArticle.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}

	/**
	 * @param entity
	 * @param coeArticle
	 */
	private void fillPreIntro(CoeArticle entity, CoeArticle coeArticle) {
		String preIntro = coeArticle.getPreIntro();
		Integer preIntroType = coeArticle.getPreIntroType();
		if(null == preIntroType || preIntroType.equals(CoeArticle.PREINTRO_TYPE_NO.getValueInt())) {
			entity.setPreIntro("");
		}
		if(StringUtils.isNotBlank(preIntro)){
			entity.setPreIntro(preIntro);
		}
		entity.setPreIntroType(preIntroType);
	}
	
	@Transactional
	@Override
	public CoeArticle mergeForUnPublished(CoeArticle coeArticle) {
		Long id = coeArticle.getId();
		CoeArticle entity = findById(id);
		if(null != entity) {
			entity.setLastModifiedBy(coeArticle.getLastModifiedBy());
			DateTime dateTime = new DateTime();
			entity.setLastModifiedDate(dateTime);
			entity.setPublishStatus(coeArticle.getPublishStatus());
			save(entity);
			coeArticle.setSuccess(CoeArticle.SUCCESS_YES);
		}
		return coeArticle;
	}
	
	@Transactional
	@Override
	public CoeArticle mergeArticleForLink(CoeArticle coeArticle) {
		Long id = coeArticle.getId();
		CoeArticle entity = findById(id);
		if(null != entity) {
			String title = coeArticle.getTitle();
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			save(entity);
			coeArticle.setSuccess(CoeArticle.SUCCESS_YES);
		}
		return coeArticle;
	}
	
	@Transactional
	@Override
	public CoeArticle mergeArticleForTitle(CoeArticle coeArticle) {
		CoeArticle entity = mergeArticleForTitle(null, coeArticle);
		if(null != entity) {
			save(entity);
			entity.setSuccess(CoeArticle.SUCCESS_YES);
		}
		return entity;
	}
	@Transactional
	@Override
	public CoeArticle mergeArticleForTitleOnly(CoeArticle coeArticle) {
		Long id = coeArticle.getId();
		CoeArticle	entity = coeArticle;
		if(null != id) {
			entity = findById(id);
			String title = coeArticle.getTitle();
			entity.setTitle(title);
			String titleLower = StringUtils.lowerCase(title);
			entity.setTitleLower(titleLower);
			String name = entity.getName();
			if(StringUtils.isBlank(name)) {
				entity.setName(title);
			}
			entity.setLastModifiedBy(coeArticle.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
			entity.setSuccess(CoeArticle.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeArticle mergeArticleForDescriptionOnly(CoeArticle coeArticle) {
		Long id = coeArticle.getId();
		CoeArticle	entity = coeArticle;
		if(null != id) {
			entity = findById(id);
			String description = coeArticle.getDescription();
			entity.setDescription(description);
			entity.setLastModifiedBy(coeArticle.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
			entity.setSuccess(CoeArticle.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	public CoeArticle mergeArticleForTitle(CoeArticle entity , CoeArticle coeArticle) {
		Long id = coeArticle.getId();
		if(null == entity) {
			entity = findById(id);
		}
		if(null != entity) {
			Integer preIntroType = coeArticle.getPreIntroType();
			if(null == preIntroType || preIntroType.equals(CoeArticle.PREINTRO_TYPE_NO.getValueInt())) {
				entity.setPreIntro("");
			}else {
				entity.setPreIntro(coeArticle.getPreIntro());
			}
			entity.setPreIntroType(preIntroType);
			entity.setSubTitle(coeArticle.getSubTitle());
			entity.setTitle(coeArticle.getTitle());
			entity.setPenname(coeArticle.getPenname());
			String titleLower = StringUtils.lowerCase(entity.getTitle());
			entity.setTitleLower(titleLower);
			entity.setLastModifiedBy(coeArticle.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}
	
	@Override
	public List<CoeArticle> findDraftList(CoeArticle coeArticle) {
		
		Long userId = coeArticle.getUserId();
		
		if(null != userId) {
			return coeArticleDao.findDraftList(coeArticle);
		}
		
		return null;
	}
	
	public Long countDraftList(CoeArticle coeArticle) {
		return coeArticleDao.countDraftList(coeArticle);
	}
	
	public CoeArticle findDetail(Long id) {
		return findById(id);
	}
	
	public Page<CoeArticle> findPublished(CoeArticle coeArticle, Pageable pageable){
		
		Page<CoeArticle> page = coeArticleDao.findPublished(coeArticle, pageable);
		
		return page;
	}
	
	@Transactional
	public CoeArticle mergeForRefresh(CoeArticle coeArticle) {
		Long id = coeArticle.getId();
		CoeArticle entity = findById(id);
		if(null != entity) {
			DateTime lastModifiedDate = new DateTime();
			Date refreshTime = lastModifiedDate.toDate();
			entity.setRefreshTime(refreshTime);
			entity.setLastModifiedBy(coeArticle.getLastModifiedBy());
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			coeArticle.setRefreshTime(refreshTime);
			coeArticle.setSuccess(CoeArticle.SUCCESS_YES);
		}
		return coeArticle;
	}
	
	@Transactional
	public CoeArticle mergeForEditQickly(CoeArticle article) {
		Long id = article.getId();
		CoeArticle entity = findById(id);
		if(null != entity) {
			DateTime lastModifiedDate = new DateTime();
			String title = article.getTitle();
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			
			entity.setName(article.getName());
			
			fillPreIntro(entity, article);
			/*Integer preIntroType = article.getPreIntroType();
			if(null == preIntroType || preIntroType.equals(CoeArticle.PREINTRO_TYPE_NO.getValueInt())) {
				entity.setPreIntro("");
			}else {
				entity.setPreIntro(article.getPreIntro());
			}
			entity.setPreIntroType(preIntroType);*/
			
			entity.setSubTitle(article.getSubTitle());
			
			entity.setLastModifiedBy(article.getLastModifiedBy());
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			article.setSuccess(CoeArticle.SUCCESS_YES);
		}
		return article;
	}
	
	@Transactional
	public CoeArticle mergeForPublish(CoeArticle article) {
		Long id = article.getId();
		CoeArticle entity = findById(id);
		if(null != entity) {
			entity=mergeForBaseinfo(entity, article);
			entity=mergeArticleForTitle(entity, article);
			
			entity.setPublishStatus(CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt());
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
				entity.setSayword(article.getSayword());//留下传说
				entity.setHeadImg(article.getHeadImg());
				entity.setNickname(article.getNickname());
			}
			
			Long saywordId = entity.getSaywordId();
			if(null == saywordId) {
				entity.setSaywordId(article.getSaywordId());
			}
			
			save(entity);
		}
		return article;
	}
	
	@Transactional
	public CoeArticle mergeForClosed(CoeArticle article) {
		Long id = article.getId();
		CoeArticle entity = findById(id);
		if(null != entity) {
			DateTime closedTime = new DateTime();
			entity.setClosedTime(closedTime.toDate());
			entity.setPublishStatus(CoeArticle.PUBLISH_STATUS_CLOSED.getValueInt());
			entity.setLastModifiedBy(article.getLastModifiedBy());
			entity.setLastModifiedDate(closedTime);
			save(entity);
			article=entity;
			article.setSuccess(CoeArticle.SUCCESS_YES);
		}
		return article;
	}
	@Transactional
	public CoeArticle mergeForDeleted(CoeArticle article) {
		Long id = article.getId();
		CoeArticle entity = findById(id);
		if(null != entity) {
			DateTime deletedTime = new DateTime();
			entity.setDeleted(article.getDeleted());
			entity.setLastModifiedBy(article.getLastModifiedBy());
			entity.setLastModifiedDate(deletedTime);
			save(entity);
			article=entity;
			article.setSuccess(CoeArticle.SUCCESS_YES);
		}
		return article;
	}
	
	public boolean publishedById(Long id) {
		 CoeArticle entity = findById(id);
		 Integer publishStatus = entity.getPublishStatus();
		 boolean published = CoeArticle.PUBLISH_STATUS_PUBLISH.equals(publishStatus);
		return published;
	}
	
	public List<CoeArticle> findByPublished(CoeArticle coeArticle){
		
		String title = coeArticle.getTitle();
		if(StringUtils.isNotBlank(title)) {
			//findByTitle
			return findPublishedByTitle(title);
		}
		String name = coeArticle.getName();
		if(StringUtils.isNotBlank(name)) {
			//findByName
			return findPublishedByName(name);
		}
		return null;
	}
	
	public Page<CoeArticle> findByPublished(CoeArticle coeArticle, Pageable pageable){
		return coeArticleDao.findByPublished( coeArticle,  pageable);
	}
	
	public List<CoeArticle> findPublishedByName(String name){
		return coeArticleDao.findPublishedByName(name);
	}
	
	public List<CoeArticle> findPublishedByTitle(String title){
		return coeArticleDao.findPublishedByTitle(title);
	}
	
	public Page<CoeArticle> findPageByUserId(Long userId , Pageable pageable){
		CoeArticle coeArticle = new CoeArticle();
		
		coeArticle.setUserId(userId);
		coeArticle.setPublishStatus(CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt());
		return coeArticleDao.findPageByUserId( coeArticle ,  pageable);
	}
	
	public Page<CoeArticle> findPageByUserId(CoeArticle coeArticle, Pageable pageable) {
		if (coeArticle == null) {
			coeArticle = new CoeArticle();
		}
		coeArticle.setPublishStatus(CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt());
		return coeArticleDao.findPageByUserId(coeArticle, pageable);
	}
	
	
	public Page<CoeArticle> findByPublishedNotSelf(CoeArticle coeArticle, Pageable pageable){
		return coeArticleDao.findByPublishedNotSelf(coeArticle,pageable);
	}
	
	public Long countForPublishedWithoutSelf(Long userId) {
		return coeArticleDao.countForPublishedWithoutSelf(userId);
	}
	
	
	public Page<CoeArticle> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable) {
		return coeArticleDao.findPageByMainTypeAndUserId( mainType, userIdList, publishStatus, pageable);
	}
	public Page<CoeArticle> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable) {
		Integer mainType = CoeArticle.MAINTYPE_9_MAIN.getValueInt();
		Integer publishStatus=CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt();
		return coeArticleDao.findPageByMainTypeAndUserId( mainType, userIdList, publishStatus, pageable);
	}
	
	public List<CoeArticle> findListByMainTypeAndUserId(List<Long> userIdList){
		Integer mainType = CoeArticle.MAINTYPE_9_MAIN.getValueInt();
		Integer publishStatus=CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt();
		return coeArticleDao.findListByMainTypeAndUserId(mainType, userIdList, publishStatus);
	}
	
	@Transactional
	public CoeArticle mergeForMainType(CoeArticle coeArticle) {
		Long userId = coeArticle.getUserId();
		Long id = coeArticle.getId();
		Integer mainType = coeArticle.getMainType();
		DateTime dateTime = new DateTime();
		if(CoeArticle.MAINTYPE_9_MAIN.getValueInt().equals(mainType)){
			//更新主技能
			List<CoeArticle> entityList = findByUserIdAndMainType(mainType, userId);
			if(null != entityList && !entityList.isEmpty()) {
				List<CoeArticle> needModifyList = new ArrayList<>();
				for(CoeArticle entity : entityList) {
					Long eid = entity.getId();
					Integer eMainType = entity.getMainType();
					boolean isOwn = id.equals(eid);
					
					if(CoeArticle.MAINTYPE_9_MAIN.getValueInt().equals(eMainType) && !isOwn) {
						entity.setMainType(CoeArticle.MAINTYPE_0_NORMAL.getValueInt());
						entity.setLastModifiedBy(userId);
						entity.setLastModifiedDate(dateTime);
						needModifyList.add(entity);
						continue;
					}
					if(isOwn) {
						entity.setMainType(CoeArticle.MAINTYPE_9_MAIN.getValueInt());
						entity.setLastModifiedBy(userId);
						entity.setLastModifiedDate(dateTime);
						needModifyList.add(entity);
						coeArticle=entity;
					}
				}
				
				CoeArticle entity = findById(id);
				entity.setMainType(CoeArticle.MAINTYPE_9_MAIN.getValueInt());
				entity.setLastModifiedBy(userId);
				entity.setLastModifiedDate(dateTime);
				needModifyList.add(entity);
				if(!needModifyList.isEmpty()) {
					save(needModifyList);
				}
			}
		}else {
			//非主技能，直接更新
			CoeArticle entity = findById(id);
			if(!CoeArticle.MAINTYPE_9_MAIN.getValueInt().equals(mainType)) {
				entity.setMainType(mainType);
				entity.setLastModifiedBy(userId);
				entity.setLastModifiedDate(dateTime);
			}
			save(entity);
			coeArticle=entity;
		}
		
		
		
		return coeArticle;
	}
	
	public CoeArticle findMainTypeByUserId(Long userId) {
		Integer mainType = CoeArticle.MAINTYPE_9_MAIN.getValueInt();
		List<CoeArticle> entityList = findPublishByUserIdAndMainType(mainType, userId);
		CoeArticle entity=null;
		if(null != entityList && !entityList.isEmpty()) {
			entity = entityList.get(0);
		}
		return entity;
	}
	
	public List<CoeArticle> findPublishByUserIdAndMainType( Integer mainType, Long userId) {
		//public List<CoeArticle> findByUserIdAndMainType(Integer mainType,Long userId, Integer publishStatus)
		Integer publishStatus = CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt();
		List<CoeArticle> entityList = coeArticleDao.findByUserIdAndMainType(mainType, userId, publishStatus);
		return entityList;
	}
	
	public List<CoeArticle> findByUserIdAndMainType( Integer mainType, Long userId) {
		List<CoeArticle> entityList = coeArticleDao.findByUserIdAndMainType(mainType, userId);
		return entityList;
	}
	
	public List<CoeArticle> findByUserId(Long userId) {
		return coeArticleDao.findByUserId( userId) ;
	}
	
	public Long countByUserId(Long userId) {
		return coeArticleDao.countByUserId(userId);
	}
	
	public Long countPublishedByUserId(CoeArticle coeArticle) {
		return coeArticleDao.countPublishedByUserId(coeArticle);
	}

}
