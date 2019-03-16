package dml.sz0099.course.app.client.wrapper.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.activity.CoeCategActivityDelegate;
import dml.sz0099.course.app.client.wrapper.category.CategoryWrapper;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
import dml.sz0099.course.app.persist.entity.category.Category;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeCategActivityWrapperImpl,组件封装
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
public class CoeCategActivityWrapperImpl implements CoeCategActivityWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategActivityWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeCategActivityDelegate coeCategActivityDelegate;
	
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	@Autowired
	private CategoryWrapper categoryWrapper;
	
	
	/**
	 * 根据Id查询CoeCategActivity实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeCategActivity findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeCategActivity coeCategActivity = coeCategActivityDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeCategActivity);
		return coeCategActivity;
	}
	
	@Override
	public CoeCategActivity findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeCategActivity coeCategActivity = coeCategActivityDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategActivity);
		return coeCategActivity;
	}
	
	/**
	 * 根据IdList查询CoeCategActivity实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeCategActivity> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeCategActivity> coeCategActivityList = coeCategActivityDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeCategActivityList);
		return coeCategActivityList;
	}
	
	@Override
	public CoeCategActivity persistEntity(CoeCategActivity coeCategActivity) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeCategActivity entity = coeCategActivityDelegate.persistEntity(coeCategActivity);
		Long id = coeCategActivity.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeCategActivity mergeEntity(CoeCategActivity coeCategActivity) {
		Long id = coeCategActivity.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeCategActivity entity = coeCategActivityDelegate.mergeEntity(coeCategActivity);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeCategActivity saveOrUpdate(CoeCategActivity coeCategActivity) {
		Long id = coeCategActivity.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeCategActivity entity = coeCategActivityDelegate.saveOrUpdate(coeCategActivity);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeCategActivity> findPage(CoeCategActivity coeCategActivity, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeCategActivity> page = coeCategActivityDelegate.findPage(coeCategActivity, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeCategActivityDelegate.existById(id);
	}
	
	public Map<Long, List<CoeCategActivity>> findMapByMainIdList(List<Long> activityIdList) {
		return coeCategActivityDelegate.findMapByMainIdList(activityIdList);
	}
	
	public List<CoeCategActivity> findByMainId(Long activityId){
		return coeCategActivityDelegate.findByMainId(activityId);
	}

	@Override
	public CoeCategActivity changeCategory(CoeCategActivity coeCategActivity) {
		return coeCategActivityDelegate.changeCategory(coeCategActivity);
	}
	
	public Page<CoeCategActivity> findPageForPublish(CoeCategActivity coeCategActivity, Pageable pageable) {
		Page<CoeCategActivity> page = coeCategActivityDelegate.findPageForPublish(coeCategActivity, pageable);
		List<CoeCategActivity> contentList = page.getContent();
		if (null != contentList && !contentList.isEmpty()) {
			List<CoeActivity> activityList = new ArrayList<>(contentList.size());
			for (CoeCategActivity cca : contentList) {
				if (null != cca) {
					CoeActivity activity = cca.getActivity();
					activityList.add(activity);
				}
			}
			coeActivityWrapper.fillRefForList(activityList);
		}
		return page;
	}
	
	public Page<CoeCategActivity> findPageForPublishFromDetail(CoeCategActivity coeCategActivity, Pageable pageable){
		Page<CoeCategActivity> page = coeCategActivityDelegate.findPageForPublishFromDetail(coeCategActivity, pageable);
		List<CoeCategActivity> contentList = page.getContent();
		if (null != contentList && !contentList.isEmpty()) {
			List<CoeActivity> coeActivityList = new ArrayList<>(contentList.size());
			for (CoeCategActivity cca : contentList) {
				if (null != cca) {
					CoeActivity coeActivity = cca.getActivity();
					coeActivityList.add(coeActivity);
				}
			}
			coeActivityWrapper.fillRefForList(coeActivityList);
		}
		return page;
	}
	
	
	public Page<CoeCategActivity> findPageForPublish(CoeCategActivity coeCategActivity, List<Long> excludeMainIdList,  Pageable pageable, boolean cover, boolean banner, boolean author) {
		Page<CoeCategActivity> page = coeCategActivityDelegate.findPageForPublish(coeCategActivity, excludeMainIdList, pageable);
		List<CoeCategActivity> contentList = page.getContent();
		if (null != contentList && !contentList.isEmpty()) {
			List<CoeActivity> activityList = new ArrayList<>(contentList.size());
			for (CoeCategActivity cca : contentList) {
				if (null != cca) {
					CoeActivity activity = cca.getActivity();
					activityList.add(activity);
				}
			}
			if(cover && banner && author ) {
				coeActivityWrapper.fillRefWithCoverAndBannerAndAuthor(activityList);
			}else {
					coeActivityWrapper.fillRefWithCoverAndBanner(activityList);
			}
		}
		return page;
	}
	
	public Page<CoeCategActivity> findPageForPublishWithChildren(CoeCategActivity coeCategActivity, List<Long> excludeMainIdList,  Pageable pageable, boolean cover, boolean banner, boolean author){
		CoeActivity coeActivity = coeCategActivity.getActivity();
		if(null == coeActivity) {
			coeActivity = new CoeActivity();
			coeCategActivity.setActivity(coeActivity);
		}
		
		Category category = coeCategActivity.getCategory();
		Long baseId = coeCategActivity.getBaseId();
		
		List<Long> baseIdList = null;
		if(null != baseId) {
			baseIdList = categoryWrapper.findListByBaseIdWithChilren(baseId);
		}else {
			if(null != category) {
				Long id = category.getId();
				if(null != id) {
					baseIdList = categoryWrapper.findListByBaseIdWithChilren(id);
				}else {
					String code = category.getCode();
					baseIdList = categoryWrapper.findListByCodeWithChilren(code);
				}
			}
		}
		
		coeActivity.setPublishStatus(CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		if(null != baseIdList || baseId!= null) {
			if(null == baseId && !baseIdList.isEmpty()) {
				coeCategActivity.setBaseId(baseIdList.get(0));
			}
			LOGGER.debug("-----2-findPageForPublishWithChildren.baseId:{} -------",baseId);
			Page<CoeCategActivity>  categoryPage = coeCategActivityDelegate.findPageForPublishWithChildren(coeCategActivity, baseIdList, excludeMainIdList, pageable);
			if(null != categoryPage && categoryPage.getTotalElements()>0) {
				LOGGER.debug("-----2-findPageForPublishWithChildren, baseId:{},  totalElements : {} -------",baseId, categoryPage.getTotalElements());
				List<CoeCategActivity> contentList = categoryPage.getContent();
				if (null != contentList && !contentList.isEmpty()) {
					List<CoeActivity> coeActivityList = new ArrayList<>(contentList.size());
					for (CoeCategActivity cca : contentList) {
						if (null != cca) {
							CoeActivity artEntity = cca.getActivity();
							coeActivityList.add(artEntity);
						}
					}
					if(cover && banner && author ) {
							coeActivityWrapper.fillRefWithCoverAndBannerAndAuthor(coeActivityList);
					}else {
							coeActivityWrapper.fillRefWithCoverAndBanner(coeActivityList);
					}
				}
			}
			return categoryPage;
		}
		return null;
	}
}
