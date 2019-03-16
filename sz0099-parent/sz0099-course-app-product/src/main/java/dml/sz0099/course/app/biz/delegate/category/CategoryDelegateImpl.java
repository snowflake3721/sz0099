package dml.sz0099.course.app.biz.delegate.category;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.category.CategoryService;
import dml.sz0099.course.app.persist.entity.category.Category;

/**
 * categoryServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CategoryDelegateImpl implements CategoryDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDelegateImpl.class);
	
	@Autowired
	private CategoryService categoryService;

	/**
	 * 根据Id查询CoeCategory实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Category findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		Category category = categoryService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, category);
		return category;
	}
	
	@Override
	public Category findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		Category category = categoryService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, category);
		return category;
	}
	
	/**
	 * 根据IdList查询CoeCategory实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Category> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<Category> categoryList = categoryService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  categoryList);
		return categoryList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public Category persistEntity(Category category) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		Category entity = categoryService.persistEntity(category);
		Long id = category.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Category mergeEntity(Category category) {
		Long id = category.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		Category entity = categoryService.mergeEntity(category);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Category saveOrUpdate(Category category) {
		Long id = category.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		Category entity = categoryService.saveOrUpdate(category);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Category> findPage(Category category, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<Category> page = categoryService.findPage(category, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return categoryService.existById(id);
	}
	
	public List<Category> findMainAndSub(Category category) {
		return categoryService.findMainAndSub(category);
	}
	
	public Category findByCode(String code) {
		return categoryService.findByCode(code);
	}
	
	public Long countByParentId(Category category) {
		return categoryService.countByParentId(category);
	}
	
	public Category deleteCategory(Category category) {
		return categoryService.deleteCategory(category);
	}
	
	public Long countByExtendId(Long extendId) {
		return categoryService.countByExtendId(extendId);
	}
	
	public Category deleteAllByExtend(Category category) {
		return categoryService.deleteAllByExtend(category);
	}
	
	public Category findById(Long id, boolean cascade) {
		return categoryService.findById(id,cascade);
	}

	@Override
	public Category findTop(Long parentId, boolean cascade) {
		return categoryService.findTop(parentId, cascade);
	}
	
	public Map<Long,Category> findMapByIdList(List<Long> idList) {
		Map<Long,Category> map = categoryService.findMapByIdList(idList);
		return map;
	}
	
	public List<Long> findListByCodeWithChilren(String code){
		return categoryService.findListByCodeWithChilren(code);
	}
	
	public List<Long> findListByBaseIdWithChilren(Long baseId){
		return categoryService.findListByBaseIdWithChilren(baseId);
	}
}
