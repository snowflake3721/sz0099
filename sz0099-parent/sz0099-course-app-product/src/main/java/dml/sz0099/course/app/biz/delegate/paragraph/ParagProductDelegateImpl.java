package dml.sz0099.course.app.biz.delegate.paragraph;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.paragraph.ParagProductService;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;

/**
 * paragProductServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ParagProductDelegateImpl implements ParagProductDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagProductDelegateImpl.class);
	
	@Autowired
	private ParagProductService paragProductService;

	/**
	 * 根据Id查询ParagProduct实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ParagProduct findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ParagProduct paragProduct = paragProductService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, paragProduct);
		return paragProduct;
	}
	
	@Override
	public ParagProduct findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ParagProduct paragProduct = paragProductService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, paragProduct);
		return paragProduct;
	}
	
	/**
	 * 根据IdList查询ParagProduct实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ParagProduct> paragProductList = paragProductService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  paragProductList);
		return paragProductList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ParagProduct persistEntity(ParagProduct paragProduct) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ParagProduct entity = paragProductService.persistEntity(paragProduct);
		Long id = paragProduct.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ParagProduct mergeEntity(ParagProduct paragProduct) {
		Long id = paragProduct.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ParagProduct entity = paragProductService.mergeEntity(paragProduct);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ParagProduct saveOrUpdate(ParagProduct paragProduct) {
		Long id = paragProduct.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ParagProduct entity = paragProductService.saveOrUpdate(paragProduct);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ParagProduct> findPage(ParagProduct paragProduct, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ParagProduct> page = paragProductService.findPage(paragProduct, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return paragProductService.existById(id);
	}


	public Page<ParagProduct> findByMainId(Long productId, Pageable pageable ){		

		LOGGER.debug("-------delegate>>>ParagProductDelegateImpl.findByMainId----------begin---------");

		Page<ParagProduct> paragProduct = paragProductService.findByMainId( productId,  pageable );

		LOGGER.info("-------delegate>>>ParagProductDelegateImpl.findByMainId----------end---------");

		return paragProduct;
	}
	
	public Page<ParagProduct> findByMainIdAndUserId(Long productId, Long userId, Pageable pageable ){		

		LOGGER.debug("-------delegate>>>ParagProductDelegateImpl.findByMainIdAndUserId----------begin---------");
		Page<ParagProduct> paragProduct = paragProductService.findByMainIdAndUserId( productId, userId, pageable );
		return paragProduct;
	}




	public void deleteByMainIdAndUserId(Long productId, Long userId ){		

		LOGGER.debug("-------delegate>>>ParagProductDelegateImpl.deleteByMainIdAndUserId----------begin---------");
		paragProductService.deleteByMainIdAndUserId( productId,  userId );
	}
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		paragProductService.deleteByParagIdAndUserId(paragId, userId, cascade);
	}
	
	public ParagProduct createParagProduct(ParagProduct paragProduct) {
		return paragProductService.createParagProduct(paragProduct);
	}
	
	public Long countByMainId(Long productId) {
		return paragProductService.countByMainId(productId);
	}


}
