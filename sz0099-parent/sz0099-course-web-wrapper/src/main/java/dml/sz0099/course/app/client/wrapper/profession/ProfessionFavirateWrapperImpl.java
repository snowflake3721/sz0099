package dml.sz0099.course.app.client.wrapper.profession;

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

import dml.sz0099.course.app.biz.delegate.profession.ProfessionFavirateDelegate;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.user.SaywordWrapper;
import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.data.show.blooming.config.MainCircleConfig;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionFavirate;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionFavirateWrapperImpl,组件封装
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
public class ProfessionFavirateWrapperImpl implements ProfessionFavirateWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionFavirateWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ProfessionFavirateDelegate professionFavirateDelegate;
	
	@Autowired
	private ProfessionWrapper professionWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private SaywordWrapper saywordWrapper;
	
	@Autowired
	private MainCircleConfig mainCircleConfig;
	
	/**
	 * 根据Id查询ProfessionFavirate实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionFavirate findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ProfessionFavirate professionFavirate = professionFavirateDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, professionFavirate);
		return professionFavirate;
	}
	
	@Override
	public ProfessionFavirate findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionFavirate professionFavirate = professionFavirateDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, professionFavirate);
		return professionFavirate;
	}
	
	/**
	 * 根据IdList查询ProfessionFavirate实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionFavirate> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ProfessionFavirate> professionFavirateList = professionFavirateDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  professionFavirateList);
		return professionFavirateList;
	}
	
	@Override
	public ProfessionFavirate persistEntity(ProfessionFavirate professionFavirate) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ProfessionFavirate entity = professionFavirateDelegate.persistEntity(professionFavirate);
		Long id = professionFavirate.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionFavirate mergeEntity(ProfessionFavirate professionFavirate) {
		Long id = professionFavirate.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ProfessionFavirate entity = professionFavirateDelegate.mergeEntity(professionFavirate);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionFavirate saveOrUpdate(ProfessionFavirate professionFavirate) {
		Long id = professionFavirate.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionFavirate entity = professionFavirateDelegate.saveOrUpdate(professionFavirate);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionFavirate> findPage(ProfessionFavirate professionFavirate, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ProfessionFavirate> page = professionFavirateDelegate.findPage(professionFavirate, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return professionFavirateDelegate.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return professionFavirateDelegate.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public ProfessionFavirate findByMainIdAndUserId(Long mainId, Long userId) {
		ProfessionFavirate entity = professionFavirateDelegate.findByMainIdAndUserId(mainId, userId);
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
	public Page<ProfessionFavirate> findByMainId(Long mainId, Pageable pageable) {
		Page<ProfessionFavirate> page = professionFavirateDelegate.findByMainId( mainId,  pageable);
		if(null != page) {
			List<ProfessionFavirate> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				for(ProfessionFavirate cp : content) {
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
		return professionFavirateDelegate.hasFaviratedByMainIdAndUserId(mainId,userId);
	}

	@Override
	public ProfessionFavirate favirate(ProfessionFavirate professionFavirate) {
		professionFavirate.setStatus(ProfessionFavirate.STATUS_YES);
		Long mainId = professionFavirate.getMainId();
		Profession profession = professionWrapper.findByIdOnly(mainId);
		if(null != profession) {
			professionFavirate.setAuthorId(profession.getUserId());
			Long userId = professionFavirate.getUserId();
			CoeUser user = coeUserWrapper.findByUserId(userId);
			professionFavirate.setHeadImg(user.getHeadImg());
			professionFavirate.setNickname(user.getNickname());
			Sayword sayword = saywordWrapper.findByUserIdAndMainId(userId);
			professionFavirate.setSaywordId(sayword.getId());
			//professionFavirate.setSayword(user.getSayword());
			return professionFavirateDelegate.mergeForFavirate(professionFavirate);
		}
		return professionFavirate;
	}

}
