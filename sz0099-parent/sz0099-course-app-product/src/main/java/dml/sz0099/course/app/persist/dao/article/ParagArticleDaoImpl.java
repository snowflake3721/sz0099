package dml.sz0099.course.app.persist.dao.article;

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

import dml.sz0099.course.app.persist.entity.article.ParagArticle;
import dml.sz0099.course.app.persist.repository.article.ParagArticleRepository;
import dml.sz0099.course.app.persist.specification.article.ParagArticleSpecification;

/**
 * ParagArticleDaoImpl 数据访问封装
 *  ----------------------------------------------------------------------------------------
 * Requirement 		Author 		Date 		Function 
 * init 			bruceyang 	2017-08-16 	basic init
 * 
 * 
 */
@Repository("articleParagArticleDaoImpl")
public class ParagArticleDaoImpl extends GenericDaoImpl<ParagArticle, Long> implements ParagArticleDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagArticleDaoImpl.class);

	@Autowired
	private ParagArticleRepository paragArticleRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = paragArticleRepository;
	}

	/**
	 * 根据Id查询ParagArticle实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ParagArticle findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ParagArticle paragArticle = paragArticleRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, paragArticle);
		return paragArticle;
	}

	@Override
	public ParagArticle findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ParagArticle paragArticle = paragArticleRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, paragArticle);
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
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ParagArticle> paragArticleList = paragArticleRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", paragArticleList);
		return paragArticleList;
	}

	/**
	 * 条件查询
	 */
	public Page<ParagArticle> findPage(ParagArticle paragArticle, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ParagArticleSpecification.getConditionWithQsl(paragArticle);
		Page<ParagArticle> page = paragArticleRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ParagArticle entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}



	public Page<ParagArticle> findByMainId(Long articleId, Pageable pageable ){		

		LOGGER.debug("-------dao>>>ParagArticleDaoImpl.findByMainId----------begin---------");
		Page<ParagArticle> page = paragArticleRepository.findByMainId( articleId,  pageable );
		return page;
	}
	
	public Page<ParagArticle> findByMainIdAndUserId(Long articleId, Long userId, Pageable pageable ){
		return paragArticleRepository.findByMainIdAndUserId(articleId, userId, pageable);
	}




	public void deleteByArticleIdAndUserId(Long articleId, Long userId ){		

		LOGGER.debug("-------dao>>>ParagArticleDaoImpl.deleteByArticleIdAndUserId----------begin---------");
		paragArticleRepository.deleteByArticleIdAndUserId( articleId,  userId );
	}
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		paragArticleRepository.deleteByParagIdAndUserId(paragId,userId);
	}

	@Override
	public List<ParagArticle> findListByMainId(Long articleId) {
		List<ParagArticle> content = paragArticleRepository.findListByMainId(articleId);
		return content;
	}
	
	public List<ParagArticle> findListByMainIdAndUserId(Long articleId,Long userId){
		List<ParagArticle> content = paragArticleRepository.findListByMainIdAndUserId(articleId, userId);
		return content;
	}
	
	public Long countByMainId(Long articleId) {
		return paragArticleRepository.countByMainId(articleId);
	}


}
