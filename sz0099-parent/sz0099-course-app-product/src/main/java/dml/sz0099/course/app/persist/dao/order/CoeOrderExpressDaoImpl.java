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

import dml.sz0099.course.app.persist.entity.order.CoeOrderExpress;
import dml.sz0099.course.app.persist.repository.order.CoeOrderExpressRepository;
import dml.sz0099.course.app.persist.specification.order.CoeOrderExpressSpecification;

/**
 * CoeOrderExpressDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeOrderExpressDaoImpl extends GenericDaoImpl<CoeOrderExpress, Long> implements CoeOrderExpressDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderExpressDaoImpl.class);

	@Autowired
	private CoeOrderExpressRepository coeOrderExpressRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeOrderExpressRepository;
	}

	/**
	 * 根据Id查询CoeOrderExpress实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderExpress findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeOrderExpress coeOrderExpress = coeOrderExpressRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeOrderExpress);
		return coeOrderExpress;
	}

	@Override
	public CoeOrderExpress findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderExpress coeOrderExpress = coeOrderExpressRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderExpress);
		return coeOrderExpress;
	}

	/**
	 * 根据IdList查询CoeOrderExpress实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderExpress> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeOrderExpress> coeOrderExpressList = coeOrderExpressRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeOrderExpressList);
		return coeOrderExpressList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeOrderExpress> findPage(CoeOrderExpress coeOrderExpress, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeOrderExpressSpecification.getConditionWithQsl(coeOrderExpress);
		Page<CoeOrderExpress> page = coeOrderExpressRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeOrderExpress entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

	@Override
	public CoeOrderExpress findByOrderId(Long orderId) {
		return coeOrderExpressRepository.findByOrderId(orderId);
	}

}
