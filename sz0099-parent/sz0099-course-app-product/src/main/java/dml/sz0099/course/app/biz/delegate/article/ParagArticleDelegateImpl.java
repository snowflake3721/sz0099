package dml.sz0099.course.app.biz.delegate.article;

import java.io.Serializable;
import java.util.List;

import org.jit4j.core.persist.page.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.article.ParagArticleService;
import dml.sz0099.course.app.persist.entity.article.ParagArticle;

/**
 * paragArticleServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ParagArticleDelegateImpl implements ParagArticleDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagArticleDelegateImpl.class);
	
	@Autowired
	private ParagArticleService paragArticleService;

	/**
	 * 根据Id查询ParagArticle实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ParagArticle findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ParagArticle paragArticle = paragArticleService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, paragArticle);
		return paragArticle;
	}
	
	@Override
	public ParagArticle findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ParagArticle paragArticle = paragArticleService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, paragArticle);
		return paragArticle;
	}
	
	/**
	 * 根据IdList查询ParagArticle实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagArticle> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ParagArticle> paragArticleList = paragArticleService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  paragArticleList);
		return paragArticleList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ParagArticle persistEntity(ParagArticle paragArticle) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ParagArticle entity = paragArticleService.persistEntity(paragArticle);
		Long id = paragArticle.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ParagArticle mergeEntity(ParagArticle paragArticle) {
		Long id = paragArticle.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ParagArticle entity = paragArticleService.mergeEntity(paragArticle);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ParagArticle saveOrUpdate(ParagArticle paragArticle) {
		Long id = paragArticle.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ParagArticle entity = paragArticleService.saveOrUpdate(paragArticle);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ParagArticle> findPage(ParagArticle paragArticle, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ParagArticle> page = paragArticleService.findPage(paragArticle, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return paragArticleService.existById(id);
	}


	public Page<ParagArticle> findByMainId(Long articleId, Pageable pageable ){		

		LOGGER.debug("-------delegate>>>ParagArticleDelegateImpl.findByMainId----------begin---------");

		Page<ParagArticle> paragArticle = paragArticleService.findByMainId( articleId,  pageable );

		LOGGER.info("-------delegate>>>ParagArticleDelegateImpl.findByMainId----------end---------");

		return paragArticle;
	}
	
	public Page<ParagArticle> findByMainIdAndUserId(Long articleId, Long userId, Pageable pageable ){		

		LOGGER.debug("-------delegate>>>ParagArticleDelegateImpl.findByMainIdAndUserId----------begin---------");
		Page<ParagArticle> paragArticle = paragArticleService.findByMainIdAndUserId( articleId, userId, pageable );
		return paragArticle;
	}

	public Page<ParagArticle> resetOrderSeq(Long articleId, Long userId ){
		return paragArticleService.resetOrderSeq(articleId, userId);
	}


	public void deleteByArticleIdAndUserId(Long articleId, Long userId ){		

		LOGGER.debug("-------delegate>>>ParagArticleDelegateImpl.deleteByArticleIdAndUserId----------begin---------");
		paragArticleService.deleteByArticleIdAndUserId( articleId,  userId );
	}
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		paragArticleService.deleteByParagIdAndUserId(paragId, userId, cascade);
	}
	
	public ParagArticle createParagArticle(ParagArticle paragArticle) {
		return paragArticleService.createParagArticle(paragArticle);
	}
	
	public Long countByMainId(Long articleId) {
		return paragArticleService.countByMainId(articleId);
	}


}
