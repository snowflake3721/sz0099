package dml.sz0099.course.app.client.wrapper.article;

import java.util.List;

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

import dml.sz0099.course.app.biz.delegate.article.CoeArticleFavirateDelegate;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.user.SaywordWrapper;
import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.data.show.blooming.config.MainCircleConfig;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticleFavirate;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeArticleFavirateWrapperImpl,组件封装
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
public class CoeArticleFavirateWrapperImpl implements CoeArticleFavirateWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleFavirateWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeArticleFavirateDelegate coeArticleFavirateDelegate;
	
	@Autowired
	private CoeArticleWrapper coeArticleWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private SaywordWrapper saywordWrapper;
	
	@Autowired
	private MainCircleConfig mainCircleConfig;
	
	/**
	 * 根据Id查询CoeArticleFavirate实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticleFavirate findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeArticleFavirate coeArticleFavirate = coeArticleFavirateDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeArticleFavirate);
		return coeArticleFavirate;
	}
	
	@Override
	public CoeArticleFavirate findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticleFavirate coeArticleFavirate = coeArticleFavirateDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticleFavirate);
		return coeArticleFavirate;
	}
	
	/**
	 * 根据IdList查询CoeArticleFavirate实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticleFavirate> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeArticleFavirate> coeArticleFavirateList = coeArticleFavirateDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeArticleFavirateList);
		return coeArticleFavirateList;
	}
	
	@Override
	public CoeArticleFavirate persistEntity(CoeArticleFavirate coeArticleFavirate) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeArticleFavirate entity = coeArticleFavirateDelegate.persistEntity(coeArticleFavirate);
		Long id = coeArticleFavirate.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticleFavirate mergeEntity(CoeArticleFavirate coeArticleFavirate) {
		Long id = coeArticleFavirate.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeArticleFavirate entity = coeArticleFavirateDelegate.mergeEntity(coeArticleFavirate);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticleFavirate saveOrUpdate(CoeArticleFavirate coeArticleFavirate) {
		Long id = coeArticleFavirate.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticleFavirate entity = coeArticleFavirateDelegate.saveOrUpdate(coeArticleFavirate);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticleFavirate> findPage(CoeArticleFavirate coeArticleFavirate, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeArticleFavirate> page = coeArticleFavirateDelegate.findPage(coeArticleFavirate, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeArticleFavirateDelegate.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticleFavirateDelegate.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeArticleFavirate findByMainIdAndUserId(Long mainId, Long userId) {
		CoeArticleFavirate entity = coeArticleFavirateDelegate.findByMainIdAndUserId(mainId, userId);
		if(null != entity) {
			entity.setNickname(Base64Util.decode(entity.getNickname()));
			String headImg = entity.getHeadImg();
			if(StringUtils.isBlank(headImg)) {
				entity.setHeadImg(mainCircleConfig.getRandomHeadImageUrl());
			}
		}
		return entity;
	}

	@Override
	public Page<CoeArticleFavirate> findByMainId(Long mainId, Pageable pageable) {
		Page<CoeArticleFavirate> page = coeArticleFavirateDelegate.findByMainId( mainId,  pageable);
		if(null != page) {
			List<CoeArticleFavirate> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				for(CoeArticleFavirate cp : content) {
					cp.setNickname(Base64Util.decode(cp.getNickname()));
					String headImg = cp.getHeadImg();
					if(StringUtils.isBlank(headImg)) {
						cp.setHeadImg(mainCircleConfig.getRandomHeadImageUrl());
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
	public boolean hasFaviratedByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticleFavirateDelegate.hasFaviratedByMainIdAndUserId(mainId,userId);
	}

	@Override
	public CoeArticleFavirate favirate(CoeArticleFavirate coeArticleFavirate) {
		coeArticleFavirate.setStatus(CoeArticleFavirate.STATUS_YES);
		Long mainId = coeArticleFavirate.getMainId();
		CoeArticle article = coeArticleWrapper.findByIdOnly(mainId);
		if(null != article) {
			coeArticleFavirate.setAuthorId(article.getUserId());
			Long userId = coeArticleFavirate.getUserId();
			CoeUser user = coeUserWrapper.findByUserId(userId);
			coeArticleFavirate.setHeadImg(user.getHeadImg());
			coeArticleFavirate.setNickname(user.getNickname());
			Sayword sayword = saywordWrapper.findByUserIdAndMainId(userId);
			coeArticleFavirate.setSaywordId(sayword.getId());
			return coeArticleFavirateDelegate.mergeForFavirate(coeArticleFavirate);
		}
		return coeArticleFavirate;
	}
}
