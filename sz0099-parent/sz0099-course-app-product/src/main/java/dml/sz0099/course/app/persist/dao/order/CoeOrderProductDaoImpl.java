package dml.sz0099.course.app.persist.dao.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.jit4j.core.persist.page.Jit4jPageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.order.CoeOrderProduct;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderProductBo;
import dml.sz0099.course.app.persist.repository.order.CoeOrderProductRepository;
import dml.sz0099.course.app.persist.specification.order.CoeOrderProductSpecification;

/**
 * CoeOrderProductDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeOrderProductDaoImpl extends GenericDaoImpl<CoeOrderProduct, Long> implements CoeOrderProductDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderProductDaoImpl.class);

	@Autowired
	private CoeOrderProductRepository coeOrderProductRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeOrderProductRepository;
	}

	/**
	 * 根据Id查询CoeOrderProduct实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderProduct findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeOrderProduct coeOrderProduct = coeOrderProductRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeOrderProduct);
		return coeOrderProduct;
	}

	@Override
	public CoeOrderProduct findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderProduct coeOrderProduct = coeOrderProductRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderProduct);
		return coeOrderProduct;
	}

	/**
	 * 根据IdList查询CoeOrderProduct实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeOrderProduct> coeOrderProductList = coeOrderProductRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeOrderProductList);
		return coeOrderProductList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeOrderProduct> findPage(CoeOrderProduct coeOrderProduct, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeOrderProductSpecification.getConditionWithQsl(coeOrderProduct);
		Page<CoeOrderProduct> page = coeOrderProductRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeOrderProduct entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	/**
	 * 用户订单查询
	 */
	public Page<CoeOrderProduct> findPageForMyOrderList(CoeOrderProductBo orderProductBo, Pageable pageable) {
		LOGGER.debug("--- service.findPageForMyOrderList ---------  ");
		Long userId = orderProductBo.getUserId();
		BooleanExpression condition = CoeOrderProductSpecification.getConditionForMyOrder(orderProductBo);
		Page<CoeOrderProduct> page = null;
		if(null!=userId) {
			page = coeOrderProductRepository.findAll(condition, pageable);
		}else {
			page = new Jit4jPageResult<>(new ArrayList<>(0), pageable, 0l);
		}
		return page;
	}

	@Override
	public List<CoeOrderProduct> findByOrderIdList(List<Long> orderIdList) {
		return coeOrderProductRepository.findByOrderIdList(orderIdList);
	}
	
	public List<CoeOrderProduct> findByOrderId(Long orderId) {
		return coeOrderProductRepository.findByOrderId(orderId);
	}

}
