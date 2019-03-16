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

import dml.sz0099.course.app.persist.entity.category.CategoryExtendLog;
import dml.sz0099.course.app.persist.repository.category.CategoryExtendLogRepository;
import dml.sz0099.course.app.persist.specification.category.CategoryExtendLogSpecification;

/**
 * CategoryExtendLogDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CategoryExtendLogDaoImpl extends GenericDaoImpl<CategoryExtendLog, Long> implements CategoryExtendLogDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendLogDaoImpl.class);

	@Autowired
	private CategoryExtendLogRepository categoryExtendLogRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = categoryExtendLogRepository;
	}

	/**
	 * 根据Id查询CategoryExtendLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CategoryExtendLog findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CategoryExtendLog categoryExtendLog = categoryExtendLogRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, categoryExtendLog);
		return categoryExtendLog;
	}

	@Override
	public CategoryExtendLog findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CategoryExtendLog categoryExtendLog = categoryExtendLogRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryExtendLog);
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
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CategoryExtendLog> categoryExtendLogList = categoryExtendLogRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", categoryExtendLogList);
		return categoryExtendLogList;
	}

	/**
	 * 条件查询
	 */
	public Page<CategoryExtendLog> findPage(CategoryExtendLog categoryExtendLog, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CategoryExtendLogSpecification.getConditionWithQsl(categoryExtendLog);
		Page<CategoryExtendLog> page = categoryExtendLogRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CategoryExtendLog entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
