package dml.sz0099.course.app.biz.service.profession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.profession.ParagProfessionDao;
import dml.sz0099.course.app.persist.entity.article.ParagArticle;
import dml.sz0099.course.app.persist.entity.profession.ParagProfession;
import dml.sz0099.course.app.persist.entity.profession.Paragraph;


/**
 * 
 * @formatter:off
 * description: ParagProfessionServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service("profParagProfessionServiceImpl")
public class ParagProfessionServiceImpl extends GenericServiceImpl<ParagProfession, Long> implements ParagProfessionService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagProfessionServiceImpl.class);

	@Autowired
	private ParagProfessionDao paragProfessionDao;
	
	@Autowired
	private ParagraphService paragraphService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = paragProfessionDao;
	}

	/**
	 * 根据Id查询ParagProfession实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ParagProfession findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ParagProfession paragProduct = paragProfessionDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, paragProduct);
		return paragProduct;
	}
	
	@Override
	public ParagProfession findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ParagProfession paragProduct = paragProfessionDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, paragProduct);
		return paragProduct;
	}

	/**
	 * 根据IdList查询ParagProfession实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagProfession> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ParagProfession> paragProductList = paragProfessionDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", paragProductList);
		return paragProductList;
	}

	@Transactional
	@Override
	public ParagProfession persistEntity(ParagProfession paragProduct) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ParagProfession entity = save(paragProduct);
		Long id = paragProduct.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ParagProfession.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ParagProfession mergeEntity(ParagProfession paragProduct) {
		Long id = paragProduct.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ParagProfession entity = findById(id);
		if(entity != null) {
			entity.setOrderSeq(paragProduct.getOrderSeq());
			entity.setLastModifiedBy(paragProduct.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ParagProfession.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ParagProfession saveOrUpdate(ParagProfession paragProduct) {
		Long id = paragProduct.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ParagProfession entity = null;
		if(null != id) {
			entity = mergeEntity(paragProduct);
		}else {
			entity = persistEntity(paragProduct);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ParagProfession> findPage(ParagProfession paragProduct, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ParagProfession> page = paragProfessionDao.findPage(paragProduct, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return paragProfessionDao.existById(id);
	}



	public Page<ParagProfession> findByMainId(Long professionId, Pageable pageable ){		

		LOGGER.debug("-------service>>>findByMainId----------begin---------");
		Page<ParagProfession> page = paragProfessionDao.findByMainId( professionId,  pageable );
		fillParagraphForContent(page);
		return page;
	}
	
	public Page<ParagProfession> findByMainIdAndUserId(Long professionId, Long userId, Pageable pageable ){		

		LOGGER.debug("-------service>>>findByMainIdAndUserId----------begin---------");
		Page<ParagProfession> paragProduct = findByMainIdAndUserId(professionId, userId, pageable, true);
		
		return paragProduct;
	}
	
	public Page<ParagProfession> findByMainIdAndUserId(Long professionId, Long userId, Pageable pageable, boolean withParagraph){
		LOGGER.debug("-------service>>>findByMainIdAndUserId----------begin,withParagraph: {} ---------", withParagraph);
		Page<ParagProfession> page = paragProfessionDao.findByMainIdAndUserId( professionId, userId, pageable );
		if(withParagraph) {
			fillParagraphForContent(page);
		}
		return page;
	}
	
	

	/**
	 * @param withParagraph
	 * @param page
	 */
	private void fillParagraphForContent( Page<ParagProfession> page) {
		if( null != page) {
			List<ParagProfession> content = page.getContent();
			fillParagraphForContent(content);
		}
	}

	/**
	 * @param content
	 */
	private void fillParagraphForContent(List<ParagProfession> content) {
		if(null != content && !content.isEmpty()) {
			List<Long> paragIdList = new ArrayList<>();
			Map<Long , ParagProfession> map = new HashMap<>(paragIdList.size());
			for(ParagProfession p : content) {
				Long paragId = p.getParagId();
				paragIdList.add(paragId);
				map.put(paragId, p);
			}
			List<Paragraph> paragList = paragraphService.findByIdList(paragIdList);
			for(Paragraph p : paragList) {
				Long paragId = p.getId();
				ParagProfession product = map.get(paragId);
				product.setParagraph(p);
			}
			
		}
	}
	
	
	public List<ParagProfession> findListByMainId(Long professionId){
		List<ParagProfession> content = paragProfessionDao.findListByMainId(professionId);
		fillParagraphForContent(content);
		return content;
	}
	
	public List<ParagProfession> findListByMainIdAndUserId(Long professionId,Long userId){
		List<ParagProfession> content = paragProfessionDao.findListByMainIdAndUserId(professionId, userId);
		fillParagraphForContent(content);
		return content;
	}
	
	
	public Page<ParagProfession> resetOrderSeq(Long articleId, Long userId) {
		PageRequest pageable = new PageRequest(0, 100, Direction.ASC, "orderSeq");
		Page<ParagProfession> page = paragProfessionDao.findByMainIdAndUserId(articleId, userId, pageable);
		List<ParagProfession> content = page.getContent();
		if (null != content && !content.isEmpty()) {
			List<Long> paragIdList = new ArrayList<>();
			Map<Long, ParagProfession> map = new HashMap<>(paragIdList.size());
			for (ParagProfession p : content) {
				Long paragId = p.getParagId();
				paragIdList.add(paragId);
				map.put(paragId, p);
			}
			List<Paragraph> paragList = paragraphService.resetOrderSeqByIdList(paragIdList, true, userId);
			for (Paragraph p : paragList) {
				Long paragId = p.getId();
				ParagProfession product = map.get(paragId);
				product.setParagraph(p);
			}
		}
		return page;
	}


	/**
	 * 添加段落
	 * @param paragProduct
	 * @return
	 */
	@Transactional
	public ParagProfession createParagProfession(ParagProfession paragProduct) {
		Long professionId = paragProduct.getMainId();
		Long createdBy = paragProduct.getCreatedBy();
		Long userId = paragProduct.getUserId();

		if (null != professionId && null != userId) {
			
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
			
			paragProduct.setSuccess(ParagProfession.SUCCESS_YES);
		}

		return paragProduct;
	}
	

	/**
	 * 删除产品与段落关联（仅删除关联，不清除段落）
	 */
	@Transactional
	public void deleteByProfessionIdAndUserId(Long professionId, Long userId ){		
		LOGGER.debug("-------service>>>ParagProfessionServiceImpl.deleteByProfessionIdAndUserId----------begin---------");
		deleteByProfessionIdAndUserId( professionId,  userId, true);
	}
	
	/**
	 * 删除某用户下的某个产品的全部段落
	 * @param professionId
	 * @param userId
	 * @param cascade
	 */
	@Transactional
	public void deleteByProfessionIdAndUserId(Long professionId, Long userId, boolean cascade ) {
		
		if(cascade) {
			List<ParagProfession>  content = findListByMainIdAndUserId(professionId,userId);
			if(null != content && !content.isEmpty()) {
				
				List<Long> paragIdList = new ArrayList<>(content.size());
				for(ParagProfession c : content) {
					paragIdList.add(c.getParagId());
				}
				
				//1.删除段落(同时级联删除段落中的图片)
				paragraphService.deleteByIdListAndUserId(paragIdList, userId, cascade);
				
			}
		}
		//2.解除段落与产品关联
		paragProfessionDao.deleteByProfessionIdAndUserId(professionId, userId);
	}
	
	@Transactional
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		
		if(cascade) {
			List<Long> paragIdList = new ArrayList<>(1);
			paragIdList.add(paragId);
			paragraphService.deleteByIdListAndUserId(paragIdList, userId, cascade);
		}
		paragProfessionDao.deleteByParagIdAndUserId(paragId, userId,cascade);
	}
	
	public Long countByMainId(Long professionId) {
		return paragProfessionDao.countByMainId(professionId);
	}

	


}
