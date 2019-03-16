package dml.sz0099.course.app.client.wrapper.article;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.plexus.util.StringUtils;
import org.dozer.DozerBeanMapper;
import org.jit8j.core.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import dml.sz0099.course.app.biz.delegate.article.CoeArticlePraiseDelegate;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.user.SaywordWrapper;
import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.data.show.blooming.config.MainCircleConfig;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPraise;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeArticlePraiseWrapperImpl,组件封装
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
public class CoeArticlePraiseWrapperImpl implements CoeArticlePraiseWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePraiseWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeArticlePraiseDelegate coeArticlePraiseDelegate;
	
	@Autowired
	private CoeArticleWrapper coeArticleWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private SaywordWrapper saywordWrapper;
	
	@Resource(name="mainCircleConfigArticle")
	private MainCircleConfig mainCircleConfig;
	
	/**
	 * 根据Id查询CoeArticlePraise实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticlePraise findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeArticlePraise coeArticlePraise = coeArticlePraiseDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeArticlePraise);
		return coeArticlePraise;
	}
	
	@Override
	public CoeArticlePraise findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticlePraise coeArticlePraise = coeArticlePraiseDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticlePraise);
		return coeArticlePraise;
	}
	
	/**
	 * 根据IdList查询CoeArticlePraise实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticlePraise> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeArticlePraise> coeArticlePraiseList = coeArticlePraiseDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeArticlePraiseList);
		return coeArticlePraiseList;
	}
	
	@Override
	public CoeArticlePraise persistEntity(CoeArticlePraise coeArticlePraise) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeArticlePraise entity = coeArticlePraiseDelegate.persistEntity(coeArticlePraise);
		Long id = coeArticlePraise.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticlePraise mergeEntity(CoeArticlePraise coeArticlePraise) {
		Long id = coeArticlePraise.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeArticlePraise entity = coeArticlePraiseDelegate.mergeEntity(coeArticlePraise);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticlePraise saveOrUpdate(CoeArticlePraise coeArticlePraise) {
		Long id = coeArticlePraise.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticlePraise entity = coeArticlePraiseDelegate.saveOrUpdate(coeArticlePraise);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticlePraise> findPage(CoeArticlePraise coeArticlePraise, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeArticlePraise> page = coeArticlePraiseDelegate.findPage(coeArticlePraise, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeArticlePraiseDelegate.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePraiseDelegate.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeArticlePraise findByMainIdAndUserId(Long mainId, Long userId) {
		CoeArticlePraise entity = coeArticlePraiseDelegate.findByMainIdAndUserId(mainId, userId);
		if(null != entity) {
			entity.setNickname(Base64Util.decode(entity.getNickname()));
			String headImg = entity.getHeadImg();
			if(StringUtils.isBlank(headImg)) {
				entity.setHeadImg(mainCircleConfig.getRandomHeadImageUrl());
			}
			String word=entity.getWord();
			if(StringUtils.isNotBlank(word)) {
				entity.setWord(Base64Util.decode(word));
			}
		}
		return entity;
	}

	@Override
	public Page<CoeArticlePraise> findByMainId(Long mainId, Pageable pageable) {
		Page<CoeArticlePraise> page = coeArticlePraiseDelegate.findByMainId( mainId,  pageable);
		if(null != page) {
			List<CoeArticlePraise> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				for(CoeArticlePraise cp : content) {
					cp.setNickname(Base64Util.decode(cp.getNickname()));
					String headImg = cp.getHeadImg();
					if(StringUtils.isBlank(headImg)) {
						cp.setHeadImg(mainCircleConfig.getRandomHeadImageUrl());
					}
					
					String word=cp.getWord();
					if(StringUtils.isNotBlank(word)) {
						cp.setWord(Base64Util.decode(word));
					}
					
					Sayword sayword=cp.getSayword();
					if(null != sayword) {
						String descr=sayword.getDescription();
						sayword.setDescription(HtmlUtil.textarea2UnEscapeForHtml(descr));
					}
				}
			}
		}
		return page;
	}

	@Override
	public boolean hasPraisedByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePraiseDelegate.hasPraisedByMainIdAndUserId(mainId,userId);
	}

	@Override
	public CoeArticlePraise praise(CoeArticlePraise coeArticlePraise) {
		coeArticlePraise.setStatus(CoeArticlePraise.STATUS_YES);
		Long mainId = coeArticlePraise.getMainId();
		Long userId = coeArticlePraise.getUserId();
		boolean hasPaised = hasPraisedByMainIdAndUserId(mainId, userId);
		String word = coeArticlePraise.getWord();
		if(StringUtils.isNotBlank(word)) {
			coeArticlePraise.setWord(Base64Util.encode(HtmlUtils.htmlEscape(word, "utf-8")));
		}
		if(hasPaised) {
			praiseAgain(coeArticlePraise);
		}else {
			CoeArticle article = coeArticleWrapper.findByIdOnly(mainId);
			if(null != article) {
				coeArticlePraise.setAuthorId(article.getUserId());
				CoeUser user = coeUserWrapper.findByUserId(userId);
				coeArticlePraise.setHeadImg(user.getHeadImg());
				coeArticlePraise.setNickname(user.getNickname());
				//coeArticlePraise.setSayword(user.getSayword());
				Sayword sayword = saywordWrapper.findByUserIdAndMainId(userId);
				coeArticlePraise.setSaywordId(sayword.getId());
				
				Date refreshTime = new Date();
				coeArticlePraise.setRefreshTime(refreshTime);
				return coeArticlePraiseDelegate.mergeForPraise(coeArticlePraise);
			}
		}
		return coeArticlePraise;
	}
	
	public CoeArticlePraise praiseAgain(CoeArticlePraise coeArticlePraise) {
		coeArticlePraise=coeArticlePraiseDelegate.praiseAgain(coeArticlePraise);
		return coeArticlePraise;
	}
	
	public CoeArticlePraise mergeForRefreshTime(CoeArticlePraise coeArticlePraise) {
		return coeArticlePraiseDelegate.mergeForRefreshTime(coeArticlePraise);
	}
}
