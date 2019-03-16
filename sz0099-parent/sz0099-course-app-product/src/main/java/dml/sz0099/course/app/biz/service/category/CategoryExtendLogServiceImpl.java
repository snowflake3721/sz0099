package dml.sz0099.course.app.biz.service.category;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.jit8j.core.util.GsonBuilderUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.category.CategoryExtendLogDao;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.CategoryExtendContent;
import dml.sz0099.course.app.persist.entity.category.CategoryExtendLog;


/**
 * 
 * @formatter:off
 * description: CategoryExtendLogServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CategoryExtendLogServiceImpl extends GenericServiceImpl<CategoryExtendLog, Long> implements CategoryExtendLogService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendLogServiceImpl.class);

	@Autowired
	private CategoryExtendLogDao categoryExtendLogDao;
	
	@Autowired
	private CategoryExtendContentService categoryExtendContentService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = categoryExtendLogDao;
	}

	/**
	 * 根据Id查询CategoryExtendLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CategoryExtendLog findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CategoryExtendLog categoryExtendLog = categoryExtendLogDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, categoryExtendLog);
		return categoryExtendLog;
	}
	
	@Override
	public CategoryExtendLog findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CategoryExtendLog categoryExtendLog = categoryExtendLogDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryExtendLog);
		return categoryExtendLog;
	}

	/**
	 * 根据IdList查询CategoryExtendLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CategoryExtendLog> categoryExtendLogList = categoryExtendLogDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", categoryExtendLogList);
		return categoryExtendLogList;
	}

	@Transactional
	@Override
	public CategoryExtendLog persistEntity(CategoryExtendLog categoryExtendLog) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CategoryExtendLog entity = save(categoryExtendLog);
		Long id = categoryExtendLog.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CategoryExtendLog.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CategoryExtendLog mergeEntity(CategoryExtendLog categoryExtendLog) {
		Long id = categoryExtendLog.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CategoryExtendLog entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(categoryExtendLog.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CategoryExtendLog.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CategoryExtendLog saveOrUpdate(CategoryExtendLog categoryExtendLog) {
		Long id = categoryExtendLog.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CategoryExtendLog entity = null;
		if(null != id) {
			entity = mergeEntity(categoryExtendLog);
		}else {
			entity = persistEntity(categoryExtendLog);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategoryExtendLog> findPage(CategoryExtendLog categoryExtendLog, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CategoryExtendLog> page = categoryExtendLogDao.findPage(categoryExtendLog, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return categoryExtendLogDao.existById(id);
	}

	@Transactional
	@Override
	public CategoryExtendLog persistForFail(CategoryExtend categoryExtend) {
		CategoryExtendLog categoryExtendLog = new CategoryExtendLog();
		
		categoryExtendLog.setExtendId(categoryExtend.getId());
		categoryExtendLog.setDevId(categoryExtend.getDevId());
		categoryExtendLog.setDomain(categoryExtend.getDomain());
		categoryExtendLog.setModule(categoryExtend.getModule());
		categoryExtendLog.setPosition(categoryExtend.getPosition());
		categoryExtendLog.setPositionId(categoryExtend.getPositionId());
		categoryExtendLog.setProject(categoryExtend.getProject());
		categoryExtendLog.setUserId(categoryExtend.getUserId());
		categoryExtendLog.setVariety(categoryExtend.getVariety());
		categoryExtendLog.setCreatedBy(categoryExtend.getCreatedBy());
		categoryExtendLog.setLastModifiedBy(categoryExtend.getLastModifiedBy());
		categoryExtendLog = persistEntity(categoryExtendLog);
		CategoryExtendContent content = new CategoryExtendContent();
		content.setExtendLogId(categoryExtendLog.getId());
		content.setContent(GsonBuilderUtils.toJsonOmitnull(categoryExtend));
		content = categoryExtendContentService.persistEntity(content);
		categoryExtendLog.setContent(content);
		
		return categoryExtendLog;
	}

}
