package dml.sz0099.course.app.client.wrapper.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.category.CategoryDelegate;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CategoryWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service("categoryWrapperImpl")
public class CategoryWrapperImpl implements CategoryWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CategoryDelegate categoryDelegate;
	
	@Autowired
	private CategoryExtendWrapper categoryExtendWapper;
	
	/**
	 * 根据Id查询Category实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Category findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		Category category = categoryDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, category);
		return category;
	}
	
	@Override
	public Category findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		Category category = categoryDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, category);
		return category;
	}
	
	/**
	 * 根据IdList查询Category实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Category> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<Category> categoryList = categoryDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  categoryList);
		return categoryList;
	}
	
	public Map<Long,Category> findMapByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findMapByIdList begin ---------  ");
		Map<Long,Category> map = categoryDelegate.findMapByIdList(idList);
		return map;
	}
	
	@Override
	public Category persistEntity(Category category) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		Category entity = categoryDelegate.persistEntity(category);
		Long id = category.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Category mergeEntity(Category category) {
		Long id = category.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		Category entity = categoryDelegate.mergeEntity(category);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Category saveOrUpdate(Category category) {
		Long id = category.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		Category entity = categoryDelegate.saveOrUpdate(category);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Category> findPage(Category category, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<Category> page = categoryDelegate.findPage(category, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return categoryDelegate.existById(id);
	}

	@Override
	public List<Category> findMainAndSub(Category category) {
		return categoryDelegate.findMainAndSub(category);
	}

	@Override
	public Category createCategory(Category category) {
		Long pid = category.getParentId();
		
		if(null == pid) {
			category.setParentId(CategoryExtend.TOP_PARENTID);
		}
		
		Long positionId = category.getPositionId();
		if(null == positionId) {
			Long extendId = category.getExtendId();
			CategoryExtend extend = categoryExtendWapper.findById(extendId);
			if(null != extend) {
				category.setPositionId(extend.getPositionId());
			}
		}
		
		Category entity = persistEntity(category);
		return entity;
	}

	@Override
	public Category findByCode(String code) {
		return categoryDelegate.findByCode(code);
	}

	@Override
	public Long countByParentId(Category category) {
		return categoryDelegate.countByParentId(category);
	}
	
	public Long countByExtendId(Long extendId) {
		return categoryDelegate.countByExtendId(extendId);
	}

	@Override
	public Category deleteCategory(Category category) {
		return categoryDelegate.deleteCategory(category);
	}

	@Override
	public Category deleteAllByExtend(Category category) {
		return categoryDelegate.deleteAllByExtend(category);
	}
	
	public Category findById(Long id, boolean cascade) {
		return categoryDelegate.findById(id,cascade);
	}

	@Override
	public Category findTop(Long parentId, boolean cascade) {
		return categoryDelegate.findTop(parentId,cascade);
	}
	
	public List<Long> findListByCodeWithChilren(String code){
		return categoryDelegate.findListByCodeWithChilren(code);
	}
	
	public List<Long> findListByBaseIdWithChilren(Long baseId){
		return categoryDelegate.findListByBaseIdWithChilren(baseId);
	}
}
