package dml.sz0099.course.app.persist.dao.product;

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

import dml.sz0099.course.app.persist.entity.product.CoeOpration;
import dml.sz0099.course.app.persist.repository.product.CoeOprationRepository;
import dml.sz0099.course.app.persist.specification.product.CoeOprationSpecification;

/**
 * CoeOprationDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeOprationDaoImpl extends GenericDaoImpl<CoeOpration, Long> implements CoeOprationDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOprationDaoImpl.class);

	@Autowired
	private CoeOprationRepository coeOprationRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeOprationRepository;
	}

	/**
	 * 根据Id查询CoeOpration实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOpration findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeOpration coeOpration = coeOprationRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeOpration);
		return coeOpration;
	}

	@Override
	public CoeOpration findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeOpration coeOpration = coeOprationRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOpration);
		return coeOpration;
	}

	/**
	 * 根据IdList查询CoeOpration实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOpration> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeOpration> coeOprationList = coeOprationRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeOprationList);
		return coeOprationList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeOpration> findPage(CoeOpration coeOpration, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeOprationSpecification.getConditionWithQsl(coeOpration);
		Page<CoeOpration> page = coeOprationRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeOpration entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
