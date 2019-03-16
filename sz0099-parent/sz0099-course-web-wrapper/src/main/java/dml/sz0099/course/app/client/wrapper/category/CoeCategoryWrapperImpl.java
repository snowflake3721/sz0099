package dml.sz0099.course.app.client.wrapper.category;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.category.CategoryDelegate;
import dml.sz0099.course.app.persist.entity.category.Category;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeCategoryWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class CoeCategoryWrapperImpl implements CoeCategoryWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategoryWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CategoryDelegate coeCategoryDelegate;
	
	/**
	 * 根据Id查询CoeCategory实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Category findById(Long id) {
		LOGGER.debug("--- CoeCategoryWrapperImpl.findById begin --------- id is:{} ", id);
		Category coeCategory = coeCategoryDelegate.findById(id);
		LOGGER.debug("--- CoeCategoryWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeCategory);
		return coeCategory;
	}
	
	@Override
	public Category findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		Category coeCategory = coeCategoryDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategory);
		return coeCategory;
	}
	
	/**
	 * 根据IdList查询CoeCategory实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Category> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeCategoryWrapperImpl.findByIdList begin ---------  ");
		List<Category> coeCategoryList = coeCategoryDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeCategoryWrapperImpl.findByIdList end ---------  result is {} ",  coeCategoryList);
		return coeCategoryList;
	}
	
	@Override
	public Category persistEntity(Category coeCategory) {
		LOGGER.debug("--- CoeCategoryWrapperImpl.persistEntity begin ---------  ");
		Category entity = coeCategoryDelegate.persistEntity(coeCategory);
		Long id = coeCategory.getId();
		LOGGER.debug("--- CoeCategoryWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Category mergeEntity(Category coeCategory) {
		Long id = coeCategory.getId();
		LOGGER.debug("--- CoeCategoryWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		Category entity = coeCategoryDelegate.mergeEntity(coeCategory);
		LOGGER.debug("--- CoeCategoryWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Category saveOrUpdate(Category coeCategory) {
		Long id = coeCategory.getId();
		LOGGER.debug("--- CoeCategoryWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		Category entity = coeCategoryDelegate.saveOrUpdate(coeCategory);
		LOGGER.debug("--- CoeCategoryWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Category> findPage(Category coeCategory, Pageable pageable) {
		LOGGER.debug("--- CoeCategoryWrapperImpl.findPage ---------  ");
		Page<Category> page = coeCategoryDelegate.findPage(coeCategory, pageable);
		return page;
	}
}
