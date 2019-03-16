package dml.sz0099.course.app.persist.dao.category;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.repository.category.CategoryRepository;
import dml.sz0099.course.app.persist.specification.category.CategorySpecification;

/**
 * CoeCategoryDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDaoImpl.class);
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = categoryRepository;
	}

	/**
	 * 根据Id查询CoeCategory实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Category findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		Category category = categoryRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, category);
		return category;
	}
	
	@Override
	public Category findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		Category category = categoryRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, category);
		return category;
	}
	
	/**
	 * 根据IdList查询CoeCategory实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Category> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<Category> categoryList = categoryRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  categoryList);
		return categoryList;
	}

	/**
	 * 条件查询
	 */
	public Page<Category> findPage(Category category, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CategorySpecification.getConditionWithQsl(category);
		Page<Category> page = categoryRepository.findAll(condition, pageable);
		return page;
	}
	
	public List<Category> findByParentId(Long parentId){
		List<Category> content = categoryRepository.findByParentId(parentId);
		return content;
	}
	
	public List<Category> findByParentIdList(List<Long> parentIdList){
		List<Category> content = categoryRepository.findByParentIdList(parentIdList);
		return content;
	}
	
	public Category findByCode(String code){
		Category entity = categoryRepository.findByCode(code);
		return entity;
	}
	
	@Override
	public boolean existById(Long id) {
		Category entity = findById(id);
		return entity != null;
	}
	
	public List<Category> findMainAndSub(Category category) {
		Long extendId = category.getExtendId();
		Long mainId = category.getMainId();
		Long subId = category.getSubId();
		Long userId = category.getUserId();
		return categoryRepository.findMainAndSub(extendId,mainId,subId,userId);
	}
	
	/**
	 * 查询子类数目
	 */
	public Long countByParentId(Category category) {
		Long id = category.getId();
		return categoryRepository.countByParentId(id);
	}
	
	public Long countByExtendId(Long extendId) {
		return categoryRepository.countByExtendId(extendId);
	}
	
	public Category deleteAllByExtend(Category category) {
		Long extendId = category.getExtendId();
		Long userId = category.getUserId();
		Long mainId = category.getMainId();
		Long subId = category.getSubId();
		categoryRepository.deleteAllByExtend( extendId,userId,mainId,subId);
		category.setSuccess(Category.SUCCESS_YES);
		return category;
	}
	
	public List<Category> findForMain(Long extendId, Long userId, Long mainId, Long subId){
		List<Category> entityList = categoryRepository.findForMain(extendId,  userId,  mainId,  subId);
		return entityList;
	}

}
