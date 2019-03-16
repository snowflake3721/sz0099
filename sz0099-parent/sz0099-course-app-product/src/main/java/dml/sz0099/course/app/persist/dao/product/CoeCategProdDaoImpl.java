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

import dml.sz0099.course.app.persist.entity.product.CoeCategProd;
import dml.sz0099.course.app.persist.repository.product.CoeCategProdRepository;
import dml.sz0099.course.app.persist.specification.product.CoeCategProdSpecification;

/**
 * CoeCategProdDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeCategProdDaoImpl extends GenericDaoImpl<CoeCategProd, Long> implements CoeCategProdDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategProdDaoImpl.class);

	@Autowired
	private CoeCategProdRepository coeCategProdRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeCategProdRepository;
	}

	/**
	 * 根据Id查询CoeCategProd实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeCategProd findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeCategProd coeCategProd = coeCategProdRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeCategProd);
		return coeCategProd;
	}

	@Override
	public CoeCategProd findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeCategProd coeCategProd = coeCategProdRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategProd);
		return coeCategProd;
	}

	/**
	 * 根据IdList查询CoeCategProd实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeCategProd> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeCategProd> coeCategProdList = coeCategProdRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeCategProdList);
		return coeCategProdList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeCategProd> findPage(CoeCategProd coeCategProd, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeCategProdSpecification.getConditionWithQsl(coeCategProd);
		Page<CoeCategProd> page = coeCategProdRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeCategProd entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

	@Override
	public List<CoeCategProd> findByMainIdList(List<Long> productIdList) {
		return coeCategProdRepository.findByMainIdList(productIdList);
	}
	
	public List<CoeCategProd> findByMainId(Long productId){
		return coeCategProdRepository.findByMainId(productId);
	}

}
