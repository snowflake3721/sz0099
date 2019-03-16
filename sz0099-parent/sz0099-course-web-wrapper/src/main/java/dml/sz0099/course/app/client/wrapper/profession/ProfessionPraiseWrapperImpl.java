package dml.sz0099.course.app.client.wrapper.profession;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import dml.sz0099.course.app.biz.delegate.profession.ProfessionPraiseDelegate;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.user.SaywordWrapper;
import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.data.show.blooming.config.MainCircleConfig;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPraise;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionPraiseWrapperImpl,组件封装
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
public class ProfessionPraiseWrapperImpl implements ProfessionPraiseWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPraiseWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ProfessionPraiseDelegate professionPraiseDelegate;
	
	@Autowired
	private ProfessionWrapper professionWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private SaywordWrapper saywordWrapper;
	
	@Resource(name="mainCircleConfigProfession")
	private MainCircleConfig mainCircleConfig;
	
	/**
	 * 根据Id查询ProfessionPraise实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionPraise findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ProfessionPraise professionPraise = professionPraiseDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, professionPraise);
		return professionPraise;
	}
	
	@Override
	public ProfessionPraise findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionPraise professionPraise = professionPraiseDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, professionPraise);
		return professionPraise;
	}
	
	/**
	 * 根据IdList查询ProfessionPraise实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionPraise> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ProfessionPraise> professionPraiseList = professionPraiseDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  professionPraiseList);
		return professionPraiseList;
	}
	
	@Override
	public ProfessionPraise persistEntity(ProfessionPraise professionPraise) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ProfessionPraise entity = professionPraiseDelegate.persistEntity(professionPraise);
		Long id = professionPraise.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionPraise mergeEntity(ProfessionPraise professionPraise) {
		Long id = professionPraise.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ProfessionPraise entity = professionPraiseDelegate.mergeEntity(professionPraise);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionPraise saveOrUpdate(ProfessionPraise professionPraise) {
		Long id = professionPraise.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionPraise entity = professionPraiseDelegate.saveOrUpdate(professionPraise);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionPraise> findPage(ProfessionPraise professionPraise, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ProfessionPraise> page = professionPraiseDelegate.findPage(professionPraise, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return professionPraiseDelegate.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return professionPraiseDelegate.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public ProfessionPraise findByMainIdAndUserId(Long mainId, Long userId) {
		ProfessionPraise entity = professionPraiseDelegate.findByMainIdAndUserId(mainId, userId);
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
	public Page<ProfessionPraise> findByMainId(Long mainId, Pageable pageable) {
		Page<ProfessionPraise> page = professionPraiseDelegate.findByMainId( mainId,  pageable);
		if(null != page) {
			List<ProfessionPraise> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				for(ProfessionPraise cp : content) {
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
	
	public Page<ProfessionPraise> findPageByMainIdWithMainType(Long mainId, Pageable pageable){
		Page<ProfessionPraise> entityPage = professionPraiseDelegate.findByMainId( mainId,  pageable);
		List<ProfessionPraise> praiseList = entityPage.getContent();
		Map<Long , ProfessionPraise> praiseMap = new HashMap<>();
		if(null != praiseList && !praiseList.isEmpty()) {
			List<Long> praiseUserIdList = new ArrayList<>(praiseList.size());
		}
		return null;
	}
	

	@Override
	public boolean hasPraisedByMainIdAndUserId(Long mainId, Long userId) {
		return professionPraiseDelegate.hasPraisedByMainIdAndUserId(mainId,userId);
	}

	@Override
	public ProfessionPraise praise(ProfessionPraise professionPraise) {
		professionPraise.setStatus(ProfessionPraise.STATUS_YES);
		Long mainId = professionPraise.getMainId();
		Long userId = professionPraise.getUserId();
		boolean hasPaised = hasPraisedByMainIdAndUserId(mainId, userId);
		String word = professionPraise.getWord();
		if(StringUtils.isNotBlank(word)) {
			professionPraise.setWord(Base64Util.encode(HtmlUtils.htmlEscape(word, "utf-8")));
		}
		if(hasPaised) {
			praiseAgain(professionPraise);
		}else {
			Profession profession = professionWrapper.findByIdOnly(mainId);
			if(null != profession) {
				professionPraise.setAuthorId(profession.getUserId());
				CoeUser user = coeUserWrapper.findByUserId(userId);
				professionPraise.setHeadImg(user.getHeadImg());
				professionPraise.setNickname(user.getNickname());
				//professionPraise.setSayword(user.getSayword());
				Sayword sayword = saywordWrapper.findByUserIdAndMainId(userId);
				professionPraise.setSaywordId(sayword.getId());
				
				Date refreshTime = new Date();
				professionPraise.setRefreshTime(refreshTime);
				return professionPraiseDelegate.mergeForPraise(professionPraise);
			}
		}
		return professionPraise;
	}
	
	public ProfessionPraise praiseAgain(ProfessionPraise professionPraise) {
		professionPraise=professionPraiseDelegate.praiseAgain(professionPraise);
		return professionPraise;
	}
	
	public ProfessionPraise mergeForRefreshTime(ProfessionPraise professionPraise) {
		return professionPraiseDelegate.mergeForRefreshTime(professionPraise);
	}
}
