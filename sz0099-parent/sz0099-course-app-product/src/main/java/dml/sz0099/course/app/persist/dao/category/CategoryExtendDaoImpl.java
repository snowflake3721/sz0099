package dml.sz0099.course.app.persist.dao.category;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.repository.category.CategoryExtendRepository;
import dml.sz0099.course.app.persist.specification.category.CategoryExtendSpecification;

/**
 * CategoryExtendDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CategoryExtendDaoImpl extends GenericDaoImpl<CategoryExtend, Long> implements CategoryExtendDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendDaoImpl.class);

	@Autowired
	private CategoryExtendRepository categoryExtendRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = categoryExtendRepository;
	}

	/**
	 * 根据Id查询CategoryExtend实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CategoryExtend findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CategoryExtend categoryExtend = categoryExtendRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, categoryExtend);
		return categoryExtend;
	}

	@Override
	public CategoryExtend findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CategoryExtend categoryExtend = categoryExtendRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryExtend);
		return categoryExtend;
	}

	/**
	 * 根据IdList查询CategoryExtend实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CategoryExtend> categoryExtendList = categoryExtendRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", categoryExtendList);
		return categoryExtendList;
	}

	/**
	 * 条件查询
	 */
	public Page<CategoryExtend> findPage(CategoryExtend categoryExtend, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CategoryExtendSpecification.getConditionWithQsl(categoryExtend);
		Page<CategoryExtend> page = categoryExtendRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CategoryExtend entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	
	public CategoryExtend findByPositionId(Long positionId) {
		return categoryExtendRepository.findByPositionId(positionId);
	}
	
	public CategoryExtend findCategoryExtend(CategoryExtend extend) {
		String devId = extend.getDevId();
		String project = extend.getProject();
		String module = extend.getModule();
		String variety = extend.getVariety();
		String position = extend.getPosition();
		if(StringUtils.isNotBlank(devId)
				&& StringUtils.isNotBlank(project)
				&& StringUtils.isNotBlank(module)
				&& StringUtils.isNotBlank(variety)
				&& StringUtils.isNotBlank(position)
				) {
			return categoryExtendRepository.findCategoryExtend(devId,project,module,variety,position);
		}
		return null;
	}

	@Override
	public Long findPositionIdById(Long id) {
		CategoryExtend categoryExtend = findById(id);
		Long positionId = null;
		if(null != categoryExtend) {
			positionId = categoryExtend.getPositionId();
		}
		return positionId;
	}
	
	public Long countByUserId(Long userId) {
		return categoryExtendRepository.countByUserId(userId);
	}

}
