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

import dml.sz0099.course.app.persist.entity.category.CategoryExtendContent;
import dml.sz0099.course.app.persist.repository.category.CategoryExtendContentRepository;
import dml.sz0099.course.app.persist.specification.category.CategoryExtendContentSpecification;

/**
 * CategoryExtendContentDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CategoryExtendContentDaoImpl extends GenericDaoImpl<CategoryExtendContent, Long> implements CategoryExtendContentDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendContentDaoImpl.class);

	@Autowired
	private CategoryExtendContentRepository categoryExtendContentRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = categoryExtendContentRepository;
	}

	/**
	 * 根据Id查询CategoryExtendContent实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CategoryExtendContent findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CategoryExtendContent categoryExtendContent = categoryExtendContentRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, categoryExtendContent);
		return categoryExtendContent;
	}

	@Override
	public CategoryExtendContent findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CategoryExtendContent categoryExtendContent = categoryExtendContentRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryExtendContent);
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
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CategoryExtendContent> categoryExtendContentList = categoryExtendContentRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", categoryExtendContentList);
		return categoryExtendContentList;
	}

	/**
	 * 条件查询
	 */
	public Page<CategoryExtendContent> findPage(CategoryExtendContent categoryExtendContent, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CategoryExtendContentSpecification.getConditionWithQsl(categoryExtendContent);
		Page<CategoryExtendContent> page = categoryExtendContentRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CategoryExtendContent entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
