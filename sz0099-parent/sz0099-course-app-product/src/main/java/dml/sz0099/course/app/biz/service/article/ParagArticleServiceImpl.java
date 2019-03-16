package dml.sz0099.course.app.biz.service.article;

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

import dml.sz0099.course.app.persist.dao.article.ParagArticleDao;
import dml.sz0099.course.app.persist.entity.article.ParagArticle;
import dml.sz0099.course.app.persist.entity.article.Paragraph;


/**
 * 
 * @formatter:off
 * description: ParagArticleServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service("articleParagArticleServiceImpl")
public class ParagArticleServiceImpl extends GenericServiceImpl<ParagArticle, Long> implements ParagArticleService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagArticleServiceImpl.class);

	@Autowired
	private ParagArticleDao paragArticleDao;
	
	@Autowired
	private ParagraphService paragraphService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = paragArticleDao;
	}

	/**
	 * 根据Id查询ParagArticle实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ParagArticle findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ParagArticle paragArticle = paragArticleDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, paragArticle);
		return paragArticle;
	}
	
	@Override
	public ParagArticle findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ParagArticle paragArticle = paragArticleDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, paragArticle);
		return paragArticle;
	}

	/**
	 * 根据IdList查询ParagArticle实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagArticle> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ParagArticle> paragArticleList = paragArticleDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", paragArticleList);
		return paragArticleList;
	}

	@Transactional
	@Override
	public ParagArticle persistEntity(ParagArticle paragArticle) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ParagArticle entity = save(paragArticle);
		Long id = paragArticle.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ParagArticle.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ParagArticle mergeEntity(ParagArticle paragArticle) {
		Long id = paragArticle.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ParagArticle entity = findById(id);
		if(entity != null) {
			entity.setOrderSeq(paragArticle.getOrderSeq());
			entity.setLastModifiedBy(paragArticle.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ParagArticle.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ParagArticle saveOrUpdate(ParagArticle paragArticle) {
		Long id = paragArticle.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ParagArticle entity = null;
		if(null != id) {
			entity = mergeEntity(paragArticle);
		}else {
			entity = persistEntity(paragArticle);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ParagArticle> findPage(ParagArticle paragArticle, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ParagArticle> page = paragArticleDao.findPage(paragArticle, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return paragArticleDao.existById(id);
	}



	public Page<ParagArticle> findByMainId(Long articleId, Pageable pageable ){		

		LOGGER.debug("-------service>>>findByMainId----------begin---------");
		Page<ParagArticle> page = paragArticleDao.findByMainId( articleId,  pageable );
		fillParagraphForContent(page);
		return page;
	}
	
	public Page<ParagArticle> findByMainIdAndUserId(Long articleId, Long userId, Pageable pageable ){		

		LOGGER.debug("-------service>>>findByMainIdAndUserId----------begin---------");
		Page<ParagArticle> paragArticle = findByMainIdAndUserId(articleId, userId, pageable, true);
		
		return paragArticle;
	}
	
	public Page<ParagArticle> findByMainIdAndUserId(Long articleId, Long userId, Pageable pageable, boolean withParagraph){
		LOGGER.debug("-------service>>>findByMainIdAndUserId----------begin,withParagraph: {} ---------", withParagraph);
		Page<ParagArticle> page = paragArticleDao.findByMainIdAndUserId( articleId, userId, pageable );
		if(withParagraph) {
			fillParagraphForContent(page);
		}
		return page;
	}

	/**
	 * @param withParagraph
	 * @param page
	 */
	private void fillParagraphForContent( Page<ParagArticle> page) {
		if( null != page) {
			List<ParagArticle> content = page.getContent();
			fillParagraphForContent(content);
		}
	}

	/**
	 * @param content
	 */
	private void fillParagraphForContent(List<ParagArticle> content) {
		if(null != content && !content.isEmpty()) {
			List<Long> paragIdList = new ArrayList<>();
			Map<Long , ParagArticle> map = new HashMap<>(paragIdList.size());
			for(ParagArticle p : content) {
				Long paragId = p.getParagId();
				paragIdList.add(paragId);
				map.put(paragId, p);
			}
			List<Paragraph> paragList = paragraphService.findByIdList(paragIdList);
			for(Paragraph p : paragList) {
				Long paragId = p.getId();
				ParagArticle product = map.get(paragId);
				product.setParagraph(p);
			}
			
		}
	}
	
	
	public List<ParagArticle> findListByMainId(Long articleId){
		List<ParagArticle> content = paragArticleDao.findListByMainId(articleId);
		fillParagraphForContent(content);
		return content;
	}
	
	public List<ParagArticle> findListByMainIdAndUserId(Long articleId,Long userId){
		List<ParagArticle> content = paragArticleDao.findListByMainIdAndUserId(articleId, userId);
		fillParagraphForContent(content);
		return content;
	}
	
	public Page<ParagArticle> resetOrderSeq(Long articleId, Long userId) {
		PageRequest pageable = new PageRequest(0, 100, Direction.ASC, "orderSeq");
		Page<ParagArticle> page = paragArticleDao.findByMainIdAndUserId(articleId, userId, pageable);
		List<ParagArticle> content = page.getContent();
		if (null != content && !content.isEmpty()) {
			List<Long> paragIdList = new ArrayList<>();
			Map<Long, ParagArticle> map = new HashMap<>(paragIdList.size());
			for (ParagArticle p : content) {
				Long paragId = p.getParagId();
				paragIdList.add(paragId);
				map.put(paragId, p);
			}
			List<Paragraph> paragList = paragraphService.resetOrderSeqByIdList(paragIdList, true, userId);
			for (Paragraph p : paragList) {
				Long paragId = p.getId();
				ParagArticle product = map.get(paragId);
				product.setParagraph(p);
			}
		}
		return page;
	}


	/**
	 * 添加段落
	 * @param paragArticle
	 * @return
	 */
	@Transactional
	public ParagArticle createParagArticle(ParagArticle paragArticle) {
		Long articleId = paragArticle.getMainId();
		Long createdBy = paragArticle.getCreatedBy();
		Long userId = paragArticle.getUserId();

		if (null != articleId && null != userId) {
			
			//findByp
			// 1.创建段落
			Paragraph paragraph = new Paragraph();
			paragraph.setCreatedBy(createdBy);
			paragraph.setUserId(userId);
			Paragraph entityParag = paragraphService.createParagraph(paragraph);

			// 2.关联产品与段落
			Long paragId = entityParag.getId();
			Long aid = entityParag.getAid();
			paragArticle.setOrderSeq(aid);
			paragArticle.setParagId(paragId);
			paragArticle = persistEntity(paragArticle);
			
			paragArticle.setSuccess(ParagArticle.SUCCESS_YES);
		}

		return paragArticle;
	}
	

	/**
	 * 删除产品与段落关联（仅删除关联，不清除段落）
	 */
	@Transactional
	public void deleteByArticleIdAndUserId(Long articleId, Long userId ){		
		LOGGER.debug("-------service>>>ParagArticleServiceImpl.deleteByArticleIdAndUserId----------begin---------");
		deleteByArticleIdAndUserId( articleId,  userId, true);
	}
	
	/**
	 * 删除某用户下的某个产品的全部段落
	 * @param articleId
	 * @param userId
	 * @param cascade
	 */
	@Transactional
	public void deleteByArticleIdAndUserId(Long articleId, Long userId, boolean cascade ) {
		
		if(cascade) {
			List<ParagArticle>  content = findListByMainIdAndUserId(articleId,userId);
			if(null != content && !content.isEmpty()) {
				
				List<Long> paragIdList = new ArrayList<>(content.size());
				for(ParagArticle c : content) {
					paragIdList.add(c.getParagId());
				}
				
				//1.删除段落(同时级联删除段落中的图片)
				paragraphService.deleteByIdListAndUserId(paragIdList, userId, cascade);
				
			}
		}
		//2.解除段落与产品关联
		paragArticleDao.deleteByArticleIdAndUserId(articleId, userId);
	}
	
	@Transactional
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		
		if(cascade) {
			List<Long> paragIdList = new ArrayList<>(1);
			paragIdList.add(paragId);
			paragraphService.deleteByIdListAndUserId(paragIdList, userId, cascade);
		}
		paragArticleDao.deleteByParagIdAndUserId(paragId, userId,cascade);
	}
	
	public Long countByMainId(Long articleId) {
		return paragArticleDao.countByMainId(articleId);
	}

	


}
