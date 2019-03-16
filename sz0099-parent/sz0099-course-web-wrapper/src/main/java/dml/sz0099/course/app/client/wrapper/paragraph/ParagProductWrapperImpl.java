package dml.sz0099.course.app.client.wrapper.paragraph;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.paragraph.ParagProductDelegate;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.entity.paragraph.Paragraph;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagProductWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class ParagProductWrapperImpl implements ParagProductWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParagProductWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ParagProductDelegate paragProductDelegate;
	
	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	/**
	 * 根据Id查询ParagProduct实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ParagProduct findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ParagProduct paragProduct = paragProductDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, paragProduct);
		return paragProduct;
	}
	
	@Override
	public ParagProduct findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ParagProduct paragProduct = paragProductDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, paragProduct);
		return paragProduct;
	}
	
	/**
	 * 根据IdList查询ParagProduct实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ParagProduct> paragProductList = paragProductDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  paragProductList);
		return paragProductList;
	}
	
	@Override
	public ParagProduct persistEntity(ParagProduct paragProduct) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ParagProduct entity = paragProductDelegate.persistEntity(paragProduct);
		Long id = paragProduct.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ParagProduct mergeEntity(ParagProduct paragProduct) {
		Long id = paragProduct.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ParagProduct entity = paragProductDelegate.mergeEntity(paragProduct);
		Paragraph paragraph = paragProduct.getParagraph();
		paragraph.setLastModifiedBy(paragProduct.getLastModifiedBy());
		paragraph.setUserId(paragProduct.getUserId());
		paragraph.setOrderSeq(paragProduct.getOrderSeq());
		paragraph=paragraphWrapper.mergeEntity(paragraph);
		paragProduct.setParagraph(paragraph);
		return entity;
	}

	@Override
	public ParagProduct saveOrUpdate(ParagProduct paragProduct) {
		Long id = paragProduct.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ParagProduct entity = paragProductDelegate.saveOrUpdate(paragProduct);
		return entity;
	}

	@Override
	public Page<ParagProduct> findPage(ParagProduct paragProduct, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ParagProduct> page = paragProductDelegate.findPage(paragProduct, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return paragProductDelegate.existById(id);
	}


	public Page<ParagProduct> findByMainId(Long productId, Pageable pageable ){		

		LOGGER.debug("-------wrapper>>>ParagProductWrapperImpl.findByMainId----------begin---------");

		Page<ParagProduct> page = paragProductDelegate.findByMainId( productId,  pageable );


		return page;
	}
	
	public Page<ParagProduct> findByMainIdAndUserId(Long productId, Long userId, Pageable pageable ){
		return paragProductDelegate.findByMainIdAndUserId(productId, userId, pageable);
	}




	public void deleteByMainIdAndUserId(Long productId, Long userId ){		

		LOGGER.debug("-------wrapper>>>ParagProductWrapperImpl.deleteByMainIdAndUserId----------begin---------");

		paragProductDelegate.deleteByMainIdAndUserId( productId,  userId );

	}
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		paragProductDelegate.deleteByParagIdAndUserId(paragId, userId, cascade);
	}
	
	public ParagProduct createParagProduct(ParagProduct paragProduct) {
		return paragProductDelegate.createParagProduct(paragProduct);
	}

	@Override
	public Long countByMainId(Long productId) {
		return paragProductDelegate.countByMainId(productId);
	}


}
