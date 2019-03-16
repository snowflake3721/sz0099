package dml.sz0099.course.app.client.wrapper.article;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.jit4j.core.persist.page.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.article.ParagArticleDelegate;
import dml.sz0099.course.app.persist.entity.article.ParagArticle;
import dml.sz0099.course.app.persist.entity.article.Paragraph;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagArticleWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service("articleParagArticleWrapperImpl")
public class ParagArticleWrapperImpl implements ParagArticleWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParagArticleWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ParagArticleDelegate paragArticleDelegate;
	
	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	/**
	 * 根据Id查询ParagArticle实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ParagArticle findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ParagArticle paragArticle = paragArticleDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, paragArticle);
		return paragArticle;
	}
	
	@Override
	public ParagArticle findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ParagArticle paragArticle = paragArticleDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, paragArticle);
		return paragArticle;
	}
	
	/**
	 * 根据IdList查询ParagArticle实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagArticle> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ParagArticle> paragArticleList = paragArticleDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  paragArticleList);
		return paragArticleList;
	}
	
	@Override
	public ParagArticle persistEntity(ParagArticle paragArticle) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ParagArticle entity = paragArticleDelegate.persistEntity(paragArticle);
		Long id = paragArticle.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ParagArticle mergeEntity(ParagArticle paragArticle) {
		Long id = paragArticle.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ParagArticle entity = paragArticleDelegate.mergeEntity(paragArticle);
		Paragraph paragraph = paragArticle.getParagraph();
		paragraph.setLastModifiedBy(paragArticle.getLastModifiedBy());
		paragraph.setUserId(paragArticle.getUserId());
		paragraph.setOrderSeq(paragArticle.getOrderSeq());
		paragraph=paragraphWrapper.mergeEntity(paragraph);
		paragArticle.setParagraph(paragraph);
		return entity;
	}

	@Override
	public ParagArticle saveOrUpdate(ParagArticle paragArticle) {
		Long id = paragArticle.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ParagArticle entity = paragArticleDelegate.saveOrUpdate(paragArticle);
		return entity;
	}

	@Override
	public Page<ParagArticle> findPage(ParagArticle paragArticle, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ParagArticle> page = paragArticleDelegate.findPage(paragArticle, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return paragArticleDelegate.existById(id);
	}


	public Page<ParagArticle> findByMainId(Long articleId, Pageable pageable ){		

		LOGGER.debug("-------wrapper>>>ParagArticleWrapperImpl.findByMainId----------begin---------");

		Page<ParagArticle> page = paragArticleDelegate.findByMainId( articleId,  pageable );


		return page;
	}
	
	public Page<ParagArticle> findByMainIdAndUserId(Long articleId, Long userId, Pageable pageable ){
		return paragArticleDelegate.findByMainIdAndUserId(articleId, userId, pageable);
	}

	public Page<ParagArticle> resetOrderSeq(Long articleId, Long userId ){
		return paragArticleDelegate.resetOrderSeq(articleId, userId);
	}


	public void deleteByArticleIdAndUserId(Long articleId, Long userId ){		

		LOGGER.debug("-------wrapper>>>ParagArticleWrapperImpl.deleteByArticleIdAndUserId----------begin---------");

		paragArticleDelegate.deleteByArticleIdAndUserId( articleId,  userId );

	}
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		paragArticleDelegate.deleteByParagIdAndUserId(paragId, userId, cascade);
	}
	
	public ParagArticle createParagArticle(ParagArticle paragArticle) {
		return paragArticleDelegate.createParagArticle(paragArticle);
	}

	@Override
	public Long countByMainId(Long articleId) {
		return paragArticleDelegate.countByMainId(articleId);
	}


}
