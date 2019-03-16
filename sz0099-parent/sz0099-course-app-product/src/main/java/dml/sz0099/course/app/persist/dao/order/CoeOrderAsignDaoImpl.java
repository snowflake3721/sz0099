package dml.sz0099.course.app.persist.dao.order;

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

import dml.sz0099.course.app.persist.entity.order.CoeOrderAsign;
import dml.sz0099.course.app.persist.repository.order.CoeOrderAsignRepository;
import dml.sz0099.course.app.persist.specification.order.CoeOrderAsignSpecification;

/**
 * CoeOrderAsignDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeOrderAsignDaoImpl extends GenericDaoImpl<CoeOrderAsign, Long> implements CoeOrderAsignDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderAsignDaoImpl.class);

	@Autowired
	private CoeOrderAsignRepository coeOrderAsignRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeOrderAsignRepository;
	}

	/**
	 * 根据Id查询CoeOrderAsign实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderAsign findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeOrderAsign coeOrderAsign = coeOrderAsignRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeOrderAsign);
		return coeOrderAsign;
	}

	@Override
	public CoeOrderAsign findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderAsign coeOrderAsign = coeOrderAsignRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderAsign);
		return coeOrderAsign;
	}

	/**
	 * 根据IdList查询CoeOrderAsign实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderAsign> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeOrderAsign> coeOrderAsignList = coeOrderAsignRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeOrderAsignList);
		return coeOrderAsignList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeOrderAsign> findPage(CoeOrderAsign coeOrderAsign, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeOrderAsignSpecification.getConditionWithQsl(coeOrderAsign);
		Page<CoeOrderAsign> page = coeOrderAsignRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeOrderAsign entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public CoeOrderAsign findByOrderId(Long orderId) {
		CoeOrderAsign entity = coeOrderAsignRepository.findByOrderId(orderId);
		return entity;
	}

}
