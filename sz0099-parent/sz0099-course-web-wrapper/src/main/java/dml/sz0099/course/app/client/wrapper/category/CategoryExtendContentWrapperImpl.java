package dml.sz0099.course.app.client.wrapper.category;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.category.CategoryExtendContentDelegate;
import dml.sz0099.course.app.persist.entity.category.CategoryExtendContent;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CategoryExtendContentWrapperImpl,组件封装
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
public class CategoryExtendContentWrapperImpl implements CategoryExtendContentWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendContentWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CategoryExtendContentDelegate categoryExtendContentDelegate;
	
	/**
	 * 根据Id查询CategoryExtendContent实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CategoryExtendContent findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CategoryExtendContent categoryExtendContent = categoryExtendContentDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, categoryExtendContent);
		return categoryExtendContent;
	}
	
	@Override
	public CategoryExtendContent findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CategoryExtendContent categoryExtendContent = categoryExtendContentDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryExtendContent);
		return categoryExtendContent;
	}
	
	/**
	 * 根据IdList查询CategoryExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CategoryExtendContent> categoryExtendContentList = categoryExtendContentDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  categoryExtendContentList);
		return categoryExtendContentList;
	}
	
	@Override
	public CategoryExtendContent persistEntity(CategoryExtendContent categoryExtendContent) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CategoryExtendContent entity = categoryExtendContentDelegate.persistEntity(categoryExtendContent);
		Long id = categoryExtendContent.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryExtendContent mergeEntity(CategoryExtendContent categoryExtendContent) {
		Long id = categoryExtendContent.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CategoryExtendContent entity = categoryExtendContentDelegate.mergeEntity(categoryExtendContent);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryExtendContent saveOrUpdate(CategoryExtendContent categoryExtendContent) {
		Long id = categoryExtendContent.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CategoryExtendContent entity = categoryExtendContentDelegate.saveOrUpdate(categoryExtendContent);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategoryExtendContent> findPage(CategoryExtendContent categoryExtendContent, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CategoryExtendContent> page = categoryExtendContentDelegate.findPage(categoryExtendContent, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return categoryExtendContentDelegate.existById(id);
	}
}
