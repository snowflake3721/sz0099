package dml.sz0099.course.app.biz.service.category;

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

import dml.sz0099.course.app.persist.dao.category.CategoryExtendContentDao;
import dml.sz0099.course.app.persist.entity.category.CategoryExtendContent;


/**
 * 
 * @formatter:off
 * description: CategoryExtendContentServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CategoryExtendContentServiceImpl extends GenericServiceImpl<CategoryExtendContent, Long> implements CategoryExtendContentService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendContentServiceImpl.class);

	@Autowired
	private CategoryExtendContentDao categoryExtendContentDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = categoryExtendContentDao;
	}

	/**
	 * 根据Id查询CategoryExtendContent实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CategoryExtendContent findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CategoryExtendContent categoryExtendContent = categoryExtendContentDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, categoryExtendContent);
		return categoryExtendContent;
	}
	
	@Override
	public CategoryExtendContent findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CategoryExtendContent categoryExtendContent = categoryExtendContentDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryExtendContent);
		return categoryExtendContent;
	}

	/**
	 * 根据IdList查询CategoryExtendContent实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CategoryExtendContent> categoryExtendContentList = categoryExtendContentDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", categoryExtendContentList);
		return categoryExtendContentList;
	}

	@Transactional
	@Override
	public CategoryExtendContent persistEntity(CategoryExtendContent categoryExtendContent) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CategoryExtendContent entity = save(categoryExtendContent);
		Long id = categoryExtendContent.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CategoryExtendContent.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CategoryExtendContent mergeEntity(CategoryExtendContent categoryExtendContent) {
		Long id = categoryExtendContent.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CategoryExtendContent entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(categoryExtendContent.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CategoryExtendContent.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CategoryExtendContent saveOrUpdate(CategoryExtendContent categoryExtendContent) {
		Long id = categoryExtendContent.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CategoryExtendContent entity = null;
		if(null != id) {
			entity = mergeEntity(categoryExtendContent);
		}else {
			entity = persistEntity(categoryExtendContent);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategoryExtendContent> findPage(CategoryExtendContent categoryExtendContent, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CategoryExtendContent> page = categoryExtendContentDao.findPage(categoryExtendContent, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return categoryExtendContentDao.existById(id);
	}

}
