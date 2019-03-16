package dml.sz0099.course.app.biz.service.paragraph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.paragraph.ParagProductDao;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.entity.paragraph.Paragraph;


/**
 * 
 * @formatter:off
 * description: ParagProductServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ParagProductServiceImpl extends GenericServiceImpl<ParagProduct, Long> implements ParagProductService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagProductServiceImpl.class);

	@Autowired
	private ParagProductDao paragProductDao;
	
	@Autowired
	private ParagraphService paragraphService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = paragProductDao;
	}

	/**
	 * 根据Id查询ParagProduct实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ParagProduct findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ParagProduct paragProduct = paragProductDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, paragProduct);
		return paragProduct;
	}
	
	@Override
	public ParagProduct findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ParagProduct paragProduct = paragProductDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, paragProduct);
		return paragProduct;
	}

	/**
	 * 根据IdList查询ParagProduct实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ParagProduct> paragProductList = paragProductDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", paragProductList);
		return paragProductList;
	}

	@Transactional
	@Override
	public ParagProduct persistEntity(ParagProduct paragProduct) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ParagProduct entity = save(paragProduct);
		Long id = paragProduct.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ParagProduct.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ParagProduct mergeEntity(ParagProduct paragProduct) {
		Long id = paragProduct.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ParagProduct entity = findById(id);
		if(entity != null) {
			entity.setOrderSeq(paragProduct.getOrderSeq());
			entity.setLastModifiedBy(paragProduct.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ParagProduct.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ParagProduct saveOrUpdate(ParagProduct paragProduct) {
		Long id = paragProduct.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ParagProduct entity = null;
		if(null != id) {
			entity = mergeEntity(paragProduct);
		}else {
			entity = persistEntity(paragProduct);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ParagProduct> findPage(ParagProduct paragProduct, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ParagProduct> page = paragProductDao.findPage(paragProduct, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return paragProductDao.existById(id);
	}



	public Page<ParagProduct> findByMainId(Long productId, Pageable pageable ){		

		LOGGER.debug("-------service>>>findByMainId----------begin---------");
		Page<ParagProduct> page = paragProductDao.findByMainId( productId,  pageable );
		fillParagraphForContent(page);
		return page;
	}
	
	public Page<ParagProduct> findByMainIdAndUserId(Long productId, Long userId, Pageable pageable ){		

		LOGGER.debug("-------service>>>findByMainIdAndUserId----------begin---------");
		Page<ParagProduct> paragProduct = findByMainIdAndUserId(productId, userId, pageable, true);
		
		return paragProduct;
	}
	
	public Page<ParagProduct> findByMainIdAndUserId(Long productId, Long userId, Pageable pageable, boolean withParagraph){
		LOGGER.debug("-------service>>>findByMainIdAndUserId----------begin,withParagraph: {} ---------", withParagraph);
		Page<ParagProduct> page = paragProductDao.findByMainIdAndUserId( productId, userId, pageable );
		if(withParagraph) {
			fillParagraphForContent(page);
		}
		return page;
	}

	/**
	 * @param withParagraph
	 * @param page
	 */
	private void fillParagraphForContent( Page<ParagProduct> page) {
		if( null != page) {
			List<ParagProduct> content = page.getContent();
			fillParagraphForContent(content);
		}
	}

	/**
	 * @param content
	 */
	private void fillParagraphForContent(List<ParagProduct> content) {
		if(null != content && !content.isEmpty()) {
			List<Long> paragIdList = new ArrayList<>();
			Map<Long , ParagProduct> map = new HashMap<>(paragIdList.size());
			for(ParagProduct p : content) {
				Long paragId = p.getParagId();
				paragIdList.add(paragId);
				map.put(paragId, p);
			}
			List<Paragraph> paragList = paragraphService.findByIdList(paragIdList);
			for(Paragraph p : paragList) {
				Long paragId = p.getId();
				ParagProduct product = map.get(paragId);
				product.setParagraph(p);
			}
			
		}
	}
	
	
	public List<ParagProduct> findListByMainId(Long productId){
		List<ParagProduct> content = paragProductDao.findListByMainId(productId);
		fillParagraphForContent(content);
		return content;
	}
	
	public List<ParagProduct> findListByMainIdAndUserId(Long productId,Long userId){
		List<ParagProduct> content = paragProductDao.findListByMainIdAndUserId(productId, userId);
		fillParagraphForContent(content);
		return content;
	}


	/**
	 * 添加段落
	 * @param paragProduct
	 * @return
	 */
	@Transactional
	public ParagProduct createParagProduct(ParagProduct paragProduct) {
		Long productId = paragProduct.getMainId();
		Long createdBy = paragProduct.getCreatedBy();
		Long userId = paragProduct.getUserId();

		if (null != productId && null != userId) {
			
			//findByp
			// 1.创建段落
			Paragraph paragraph = new Paragraph();
			paragraph.setCreatedBy(createdBy);
			paragraph.setUserId(userId);
			Paragraph entityParag = paragraphService.createParagraph(paragraph);

			// 2.关联产品与段落
			Long paragId = entityParag.getId();
			Long aid = entityParag.getAid();
			paragProduct.setOrderSeq(aid);
			paragProduct.setParagId(paragId);
			paragProduct = persistEntity(paragProduct);
			
			paragProduct.setSuccess(ParagProduct.SUCCESS_YES);
		}

		return paragProduct;
	}
	

	/**
	 * 删除产品与段落关联（仅删除关联，不清除段落）
	 */
	@Transactional
	public void deleteByMainIdAndUserId(Long productId, Long userId ){		
		LOGGER.debug("-------service>>>ParagProductServiceImpl.deleteByMainIdAndUserId----------begin---------");
		deleteByMainIdAndUserId( productId,  userId, true);
	}
	
	/**
	 * 删除某用户下的某个产品的全部段落
	 * @param productId
	 * @param userId
	 * @param cascade
	 */
	@Transactional
	public void deleteByMainIdAndUserId(Long productId, Long userId, boolean cascade ) {
		
		if(cascade) {
			List<ParagProduct>  content = findListByMainIdAndUserId(productId,userId);
			if(null != content && !content.isEmpty()) {
				
				List<Long> paragIdList = new ArrayList<>(content.size());
				for(ParagProduct c : content) {
					paragIdList.add(c.getParagId());
				}
				
				//1.删除段落(同时级联删除段落中的图片)
				paragraphService.deleteByIdListAndUserId(paragIdList, userId, cascade);
				
			}
		}
		//2.解除段落与产品关联
		paragProductDao.deleteByMainIdAndUserId(productId, userId);
	}
	
	@Transactional
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		
		if(cascade) {
			List<Long> paragIdList = new ArrayList<>(1);
			paragIdList.add(paragId);
			paragraphService.deleteByIdListAndUserId(paragIdList, userId, cascade);
		}
		paragProductDao.deleteByParagIdAndUserId(paragId, userId,cascade);
	}
	
	public Long countByMainId(Long productId) {
		return paragProductDao.countByMainId(productId);
	}

	


}
