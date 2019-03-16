package dml.sz0099.course.app.client.wrapper.activity;

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

import dml.sz0099.course.app.biz.delegate.activity.CoeActivityPraiseDelegate;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.user.SaywordWrapper;
import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.data.show.blooming.config.MainCircleConfig;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPraise;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityPraiseWrapperImpl,组件封装
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
public class CoeActivityPraiseWrapperImpl implements CoeActivityPraiseWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPraiseWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeActivityPraiseDelegate coeActivityPraiseDelegate;
	
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private SaywordWrapper saywordWrapper;
	
	@Resource(name="mainCircleConfigArticle")
	private MainCircleConfig mainCircleConfig;
	
	/**
	 * 根据Id查询CoeActivityPraise实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityPraise findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeActivityPraise coeActivityPraise = coeActivityPraiseDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeActivityPraise);
		return coeActivityPraise;
	}
	
	@Override
	public CoeActivityPraise findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityPraise coeActivityPraise = coeActivityPraiseDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityPraise);
		return coeActivityPraise;
	}
	
	/**
	 * 根据IdList查询CoeActivityPraise实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityPraise> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeActivityPraise> coeActivityPraiseList = coeActivityPraiseDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeActivityPraiseList);
		return coeActivityPraiseList;
	}
	
	@Override
	public CoeActivityPraise persistEntity(CoeActivityPraise coeActivityPraise) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeActivityPraise entity = coeActivityPraiseDelegate.persistEntity(coeActivityPraise);
		Long id = coeActivityPraise.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityPraise mergeEntity(CoeActivityPraise coeActivityPraise) {
		Long id = coeActivityPraise.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityPraise entity = coeActivityPraiseDelegate.mergeEntity(coeActivityPraise);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityPraise saveOrUpdate(CoeActivityPraise coeActivityPraise) {
		Long id = coeActivityPraise.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityPraise entity = coeActivityPraiseDelegate.saveOrUpdate(coeActivityPraise);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityPraise> findPage(CoeActivityPraise coeActivityPraise, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeActivityPraise> page = coeActivityPraiseDelegate.findPage(coeActivityPraise, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeActivityPraiseDelegate.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPraiseDelegate.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeActivityPraise findByMainIdAndUserId(Long mainId, Long userId) {
		CoeActivityPraise entity = coeActivityPraiseDelegate.findByMainIdAndUserId(mainId, userId);
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
	public Page<CoeActivityPraise> findByMainId(Long mainId, Pageable pageable) {
		Page<CoeActivityPraise> page = coeActivityPraiseDelegate.findByMainId( mainId,  pageable);
		if(null != page) {
			List<CoeActivityPraise> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				for(CoeActivityPraise cp : content) {
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
		return coeActivityPraiseDelegate.hasPraisedByMainIdAndUserId(mainId,userId);
	}

	@Override
	public CoeActivityPraise praise(CoeActivityPraise coeActivityPraise) {
		coeActivityPraise.setStatus(CoeActivityPraise.STATUS_YES);
		Long mainId = coeActivityPraise.getMainId();
		Long userId = coeActivityPraise.getUserId();
		
		boolean hasPaised = hasPraisedByMainIdAndUserId(mainId, userId);
		String word = coeActivityPraise.getWord();
		if(StringUtils.isNotBlank(word)) {
			coeActivityPraise.setWord(Base64Util.encode(HtmlUtils.htmlEscape(word, "utf-8")));
		}
		if(hasPaised) {
			praiseAgain(coeActivityPraise);
		}else {
			CoeActivity activity = coeActivityWrapper.findByIdOnly(mainId);
			if(null != activity) {
				coeActivityPraise.setAuthorId(activity.getUserId());
				
				CoeUser user = coeUserWrapper.findByUserId(userId);
				coeActivityPraise.setHeadImg(user.getHeadImg());
				coeActivityPraise.setNickname(user.getNickname());
				//coeActivityPraise.setSayword(user.getSayword());
				Sayword sayword = saywordWrapper.findByUserIdAndMainId(userId);
				coeActivityPraise.setSaywordId(sayword.getId());
				
				Date refreshTime = new Date();
				coeActivityPraise.setRefreshTime(refreshTime);
				return coeActivityPraiseDelegate.mergeForPraise(coeActivityPraise);
			}
		}
		return coeActivityPraise;
	}
	
	public CoeActivityPraise praiseAgain(CoeActivityPraise coeActivityPraise) {
		coeActivityPraise=coeActivityPraiseDelegate.praiseAgain(coeActivityPraise);
		return coeActivityPraise;
	}
	
	
	public CoeActivityPraise mergeForRefreshTime(CoeActivityPraise coeActivityPraise) {
		return coeActivityPraiseDelegate.mergeForRefreshTime(coeActivityPraise);
	}
}
