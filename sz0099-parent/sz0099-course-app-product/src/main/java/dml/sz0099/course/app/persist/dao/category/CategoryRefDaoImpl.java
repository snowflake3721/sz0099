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

import dml.sz0099.course.app.persist.entity.category.CategoryRef;
import dml.sz0099.course.app.persist.repository.category.CategoryRefRepository;
import dml.sz0099.course.app.persist.specification.category.CategoryRefSpecification;

/**
 * CategoryRefDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CategoryRefDaoImpl extends GenericDaoImpl<CategoryRef, Long> implements CategoryRefDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRefDaoImpl.class);

	@Autowired
	private CategoryRefRepository categoryRefRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = categoryRefRepository;
	}

	/**
	 * 根据Id查询CategoryRef实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CategoryRef findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CategoryRef categoryRef = categoryRefRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, categoryRef);
		return categoryRef;
	}

	@Override
	public CategoryRef findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CategoryRef categoryRef = categoryRefRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryRef);
		return categoryRef;
	}

	/**
	 * 根据IdList查询CategoryRef实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CategoryRef> categoryRefList = categoryRefRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", categoryRefList);
		return categoryRefList;
	}

	/**
	 * 条件查询
	 */
	public Page<CategoryRef> findPage(CategoryRef categoryRef, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CategoryRefSpecification.getConditionWithQsl(categoryRef);
		Page<CategoryRef> page = categoryRefRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CategoryRef entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public void deleteByBaseId(Long baseId) {
		categoryRefRepository.deleteByBaseId(baseId);
	}
	
	public Long countForBase(CategoryRef categoryRef) {
		Long baseId = categoryRef.getBaseId();
		return categoryRefRepository.countByBaseId(baseId);
	}

	@Override
	public List<CategoryRef> findByMainId(Long mainId) {
		return categoryRefRepository.findByMainId(mainId);
	}
	
	public void deleteByMainId(Long mainId) {
		categoryRefRepository.deleteByMainId(mainId);
	}

}
