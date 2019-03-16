package dml.sz0099.course.app.client.wrapper.activity;

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

import dml.sz0099.course.app.biz.delegate.activity.CoeActivityFavirateDelegate;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.user.SaywordWrapper;
import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.data.show.blooming.config.MainCircleConfig;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFavirate;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityFavirateWrapperImpl,组件封装
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
public class CoeActivityFavirateWrapperImpl implements CoeActivityFavirateWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityFavirateWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeActivityFavirateDelegate coeActivityFavirateDelegate;
	
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private SaywordWrapper saywordWrapper;
	
	@Autowired
	private MainCircleConfig mainCircleConfig;
	
	/**
	 * 根据Id查询CoeActivityFavirate实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityFavirate findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeActivityFavirate coeActivityFavirate = coeActivityFavirateDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeActivityFavirate);
		return coeActivityFavirate;
	}
	
	@Override
	public CoeActivityFavirate findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityFavirate coeActivityFavirate = coeActivityFavirateDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityFavirate);
		return coeActivityFavirate;
	}
	
	/**
	 * 根据IdList查询CoeActivityFavirate实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityFavirate> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeActivityFavirate> coeActivityFavirateList = coeActivityFavirateDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeActivityFavirateList);
		return coeActivityFavirateList;
	}
	
	@Override
	public CoeActivityFavirate persistEntity(CoeActivityFavirate coeActivityFavirate) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeActivityFavirate entity = coeActivityFavirateDelegate.persistEntity(coeActivityFavirate);
		Long id = coeActivityFavirate.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityFavirate mergeEntity(CoeActivityFavirate coeActivityFavirate) {
		Long id = coeActivityFavirate.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityFavirate entity = coeActivityFavirateDelegate.mergeEntity(coeActivityFavirate);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityFavirate saveOrUpdate(CoeActivityFavirate coeActivityFavirate) {
		Long id = coeActivityFavirate.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityFavirate entity = coeActivityFavirateDelegate.saveOrUpdate(coeActivityFavirate);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityFavirate> findPage(CoeActivityFavirate coeActivityFavirate, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeActivityFavirate> page = coeActivityFavirateDelegate.findPage(coeActivityFavirate, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeActivityFavirateDelegate.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityFavirateDelegate.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeActivityFavirate findByMainIdAndUserId(Long mainId, Long userId) {
		CoeActivityFavirate entity = coeActivityFavirateDelegate.findByMainIdAndUserId(mainId, userId);
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
	public Page<CoeActivityFavirate> findByMainId(Long mainId, Pageable pageable) {
		Page<CoeActivityFavirate> page = coeActivityFavirateDelegate.findByMainId( mainId,  pageable);
		if(null != page) {
			List<CoeActivityFavirate> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				for(CoeActivityFavirate cp : content) {
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
		return coeActivityFavirateDelegate.hasFaviratedByMainIdAndUserId(mainId,userId);
	}

	@Override
	public CoeActivityFavirate favirate(CoeActivityFavirate coeActivityFavirate) {
		coeActivityFavirate.setStatus(CoeActivityFavirate.STATUS_YES);
		Long mainId = coeActivityFavirate.getMainId();
		CoeActivity activity = coeActivityWrapper.findByIdOnly(mainId);
		if(null != activity) {
			coeActivityFavirate.setAuthorId(activity.getUserId());
			Long userId = coeActivityFavirate.getUserId();
			CoeUser user = coeUserWrapper.findByUserId(userId);
			coeActivityFavirate.setHeadImg(user.getHeadImg());
			coeActivityFavirate.setNickname(user.getNickname());
			Sayword sayword = saywordWrapper.findByUserIdAndMainId(userId);
			coeActivityFavirate.setSaywordId(sayword.getId());
			return coeActivityFavirateDelegate.mergeForFavirate(coeActivityFavirate);
		}
		return coeActivityFavirate;
	}
}
