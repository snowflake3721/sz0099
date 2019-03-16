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

import dml.sz0099.course.app.persist.entity.product.CoeProduct;
import dml.sz0099.course.app.persist.repository.product.CoeProductRepository;
import dml.sz0099.course.app.persist.specification.product.CoeProductSpecification;

/**
 * CoeProductDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeProductDaoImpl extends GenericDaoImpl<CoeProduct, Long> implements CoeProductDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductDaoImpl.class);
	
	@Autowired
	private CoeProductRepository coeProductRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeProductRepository;
	}

	/**
	 * 根据Id查询CoeProduct实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeProduct findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeProduct coeProduct = coeProductRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeProduct);
		return coeProduct;
	}
	
	/**
	 * 根据IdList查询CoeProduct实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeProduct> coeProductList = coeProductRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeProductList);
		return coeProductList;
	}
	
	public List<CoeProduct> findShelvedByName(String name){
		return coeProductRepository.findShelvedByName(name);
	}
	
	public List<CoeProduct> findShelvedByTitle(String title){
		return coeProductRepository.findShelvedByTitle(title);
	}
	
	public Page<CoeProduct> findByShelved(CoeProduct coeProduct, Pageable pageable){
		BooleanExpression condition = CoeProductSpecification.getConditionByCodeWithQslExpression(coeProduct);
		Page<CoeProduct> page = coeProductRepository.findAll( condition,  pageable);
		return page;
	}

	@Override
	public List<CoeProduct> findDraftList(CoeProduct coeProduct) {
		Long userId = coeProduct.getUserId();
		Integer publishStatus = CoeProduct.PUBLISH_STATUS_DRAFT.getValueInt();
		List<CoeProduct> content = coeProductRepository.findByUserIdAndPublishStatus(userId, publishStatus);
		return content;
	}
	
	public Long countDraftList(CoeProduct coeProduct) {
		Long userId = coeProduct.getUserId();
		Integer publishStatus = CoeProduct.PUBLISH_STATUS_DRAFT.getValueInt();
		return coeProductRepository.countByUserIdAndPublishStatus(userId, publishStatus);
	}
	
	public Page<CoeProduct> findPublished(CoeProduct coeProduct, Pageable pageable){
		
		Integer publishStatus = CoeProduct.PUBLISH_STATUS_PUBLISH.getValueInt();
		coeProduct.setPublishStatus(publishStatus);
		BooleanExpression condition = CoeProductSpecification.getConditionForPublish(coeProduct);
		Page<CoeProduct> page = coeProductRepository.findAll(condition, pageable);
		return page;
	}

}
