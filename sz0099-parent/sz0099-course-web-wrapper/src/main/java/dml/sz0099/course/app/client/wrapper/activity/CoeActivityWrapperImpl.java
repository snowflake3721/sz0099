package dml.sz0099.course.app.client.wrapper.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;

import org.codehaus.plexus.util.StringUtils;
import org.dozer.DozerBeanMapper;
import org.jit4j.app.module.define.Robot;
import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.persist.page.PageResult;
import org.jit8j.core.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import dml.sz0099.course.app.biz.delegate.activity.CoeActivityDelegate;
import dml.sz0099.course.app.client.resolver.adaptor.category.CategoryActivityAdaptor;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.user.SaywordWrapper;
import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.data.handler.util.NicknameHandler;
import dml.sz0099.course.app.data.show.blooming.config.MainCircleConfig;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPraise;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
import dml.sz0099.course.app.persist.entity.activity.PhotoBanner;
import dml.sz0099.course.app.persist.entity.activity.PhotoCover;
import dml.sz0099.course.app.persist.entity.activity.vo.CoeActivityUserVo;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityWrapperImpl,组件封装
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
public class CoeActivityWrapperImpl implements CoeActivityWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeActivityDelegate coeActivityDelegate;
	
	@Autowired
	private CoeActivityTagWrapper coeActivityTagWrapper;
	
	
	@Autowired
	private PhotoCoverWrapper photoCoverWrapper;
	
	@Autowired
	private PhotoBannerWrapper photoBannerWrapper;
	
	@Autowired
	private CoeCategActivityWrapper coeCategActivityWrapper;
	
	@Autowired
	private CategoryActivityAdaptor categoryActivityAdaptor;
	
	@Autowired
	private CoeActivityPraiseWrapper coeActivityPraiseWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private SaywordWrapper saywordWrapper;
	
	@Autowired
	private JoinItemWrapper joinItemWrapper;
	
	@Autowired
	private CoeActivityTimeWrapper coeActivityTimeWrapper;
	
	@Autowired
	private CoeActivityFeeWrapper coeActivityFeeWrapper;
	
	@Autowired
	private CoeActivityUserWrapper coeActivityUserWrapper;
	
	@Resource(name="mainCircleConfigActivity")
	private MainCircleConfig mainCircleConfig;
	
	@Autowired
	private NicknameHandler nicknameHandler;
	
	private Long mainId = Robot.ROBOT_PLAT.getId();
	
	private Long subId = Robot.ROBOT_PLAT.getId();
	
	/**
	 * 根据Id查询CoeActivity实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivity findById(Long id) {
		CoeActivity coeActivity = findById(id,true, true, true, true, true, true,true,true, true,true);
		return coeActivity;
	}
	public CoeActivity findByIdOnly(Long id) {
		CoeActivity coeActivity = findById(id,false,false, false,false, false, false,false, false, false, false);
		return coeActivity;
	}
	
	public CoeActivity findByIdWithTimeAndFee(Long id) {
		CoeActivity coeActivity = findById(id,false,false, false,false, false, false,false, false, true, true);
		return coeActivity;
	}
	
	public CoeActivity findByIdWithCoverAndBanner(Long id) {
		CoeActivity coeActivity = findById(id,false,true, true,false, false, false,false, false, true,true);
		return coeActivity;
	}
	
	public Category findCategoryTree(Long mainId, Long subId) {
		if(mainId==null) {
			mainId = this.mainId;
		}
		if(subId==null) {
			subId = this.subId;
		}
		Category categoryTree = categoryActivityAdaptor.queryTree(mainId, subId);
		return categoryTree;
	}
	
	public CoeActivity findById(Long id, boolean withTags , boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withCategoryTree, boolean withPraise,
			boolean withAuthorPayCode, boolean withSayword, boolean withJoinItem , boolean withFee) {
		LOGGER.debug("--- CoeActivityWrapperImpl.findById begin --------- id is:{} , withTags: {} ", id, withTags);
		CoeActivity coeActivity = coeActivityDelegate.findById(id);
		if(null != coeActivity) {
			fillRefSingle(coeActivity, withTags, withCover, withBanner, withCategory, withCategoryTree, withPraise, withAuthorPayCode, withSayword, withJoinItem, withFee);
		}
		LOGGER.debug("--- CoeActivityWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeActivity);
		return coeActivity;
	}
	
	public CoeActivity fillRefBaseSingle(CoeActivity coeActivity) {
		boolean withTags = true;
		boolean withCover = true;
		boolean withBanner = true;
		boolean withCategory = true;
		boolean withCategoryTree = true;
		boolean withPraise = true;
		boolean withAuthorPayCode = true;
		boolean withSayword = true;
		boolean withJoinItem = false;
		boolean withFee = false;
		return fillRefSingle(coeActivity, withTags, withCover, withBanner, withCategory, withCategoryTree, withPraise, withAuthorPayCode, withSayword, withJoinItem, withFee);
	}
	/**
	 * @param coeActivity
	 * @param withTags
	 * @param withCover
	 * @param withBanner
	 * @param withCategory
	 * @param withCategoryTree
	 * @param withPraise
	 * @param withAuthorPayCode
	 */
	public CoeActivity fillRefSingle(CoeActivity coeActivity, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory,
			boolean withCategoryTree, boolean withPraise, boolean withAuthorPayCode,
			boolean withSayword, boolean withJoinItem, boolean withFee) {
		if(null != coeActivity) {
			Long id = coeActivity.getId();
			nicknameHandler.handleNickname(coeActivity);
			/*String nickname = coeActivity.getNickname();
			if(StringUtils.isNotBlank(nickname)) {
				coeActivity.setNickname(Base64Util.decode(nickname));
			}else {
				coeActivity.setNickname("卓无名");
			}*/
		
			if(withTags) {
				List<CoeActivityTag> activityTagList = coeActivityTagWrapper.findByMainId(id);
				coeActivity.setActivityTagList(activityTagList);
			}
			if(withCover) {
				List<Long> subIdList = new ArrayList<>(1);
				subIdList.add(PhotoCover.SUBID_COVER_HEAD);
				List<PhotoCover>  coverList = photoCoverWrapper.findBySubIdListAndMainId(subIdList, id);
				coeActivity.setCoverList(coverList);
			}
			if(withBanner) {
				List<Long> subIdList = new ArrayList<>(1);
				subIdList.add(PhotoBanner.SUBID_BANNER_HEAD);
				List<PhotoBanner>  bannerList = photoBannerWrapper.findBySubIdListAndMainId(subIdList, id);
				coeActivity.setBannerList(bannerList);
			}
			if(withCategory) {
				//获取分类树 TODO
				List<CoeCategActivity> categoryList = coeCategActivityWrapper.findByMainId(id);
				if(null == categoryList) {
					categoryList = new ArrayList<>(1);
				}
				if(categoryList.isEmpty()) {
					CoeCategActivity prod = new CoeCategActivity();
					prod.setMainId(id);
					categoryList.add(prod);
					
				}
				coeActivity.setCategoryList(categoryList);
				if(withCategoryTree) {
					Category categoryTree = categoryActivityAdaptor.queryTree(mainId, subId);
					coeActivity.setCategoryTree(categoryTree);
				}
			}
			if(withPraise) {
				//前30名点赞用户
				PageRequest pageable = new PageRequest(0, 30, Direction.ASC, "aid");
				Page<CoeActivityPraise> praisePage = coeActivityPraiseWrapper.findByMainId(id, pageable);
				coeActivity.setPraiseList(praisePage.getContent());
				coeActivity.setPraisePage(praisePage);
			}
			
			if(withAuthorPayCode) {
				Long userId = coeActivity.getUserId();
				CoeUser author = coeUserWrapper.findByUserId(userId,withAuthorPayCode,false, withSayword);
				nicknameHandler.handleNicknameForUser(author);
				coeActivity.setAuthor(author);
			}
			
			if(withJoinItem) {
				Long actTimeId=coeActivity.getActTimeId();
				CoeActivityTime actTimeEntity = coeActivityTimeWrapper.findById(actTimeId,true);
				/*List<JoinItem> joinItemList = actTimeEntity.getJoinItemList();
				if(null != joinItemList && !joinItemList.isEmpty()) {
					for(JoinItem ji : joinItemList) {
						String description = ji.getDescription();
						if(StringUtils.isNotBlank(description)) {
							ji.setDescription(HtmlUtil.textarea2UnEscape(description));
						}
						String place=ji.getPlace();
						if(StringUtils.isNotBlank(place)) {
							ji.setPlace(HtmlUtil.textarea2UnEscape(place));
						}
					}
				}*/
				coeActivity.setActTime(actTimeEntity);
			}
			if(withFee) {
				Long actFeeId=coeActivity.getFeeId();
				CoeActivityFee actFeeEntity = coeActivityFeeWrapper.findById(actFeeId);
				/*String description = actFeeEntity.getDescription();
				if(StringUtils.isNotBlank(description)) {
					actFeeEntity.setDescription(HtmlUtil.textarea2UnEscape(description));
				}*/
				coeActivity.setActFee(actFeeEntity);
			}
			//处理传说
			Sayword sayword=coeActivity.getSayword();
			if(null != sayword) {
				String descr=sayword.getDescription();
				sayword.setDescription(HtmlUtil.textarea2UnEscapeForHtml(descr));
			}
		}
		return coeActivity;
	}
	
	public boolean existById(Long id) {
		LOGGER.debug("--- CoeActivityWrapperImpl.existById begin --------- id is:{} ", id);
		return coeActivityDelegate.existById(id);
	}
	
	/**
	 * 根据IdList查询CoeActivity实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivity> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeActivityWrapperImpl.findByIdList begin ---------  ");
		List<CoeActivity> coeActivityList = coeActivityDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeActivityWrapperImpl.findByIdList end ---------  result is {} ",  coeActivityList);
		return coeActivityList;
	}
	
	@Override
	public CoeActivity persistEntity(CoeActivity coeActivity) {
		LOGGER.debug("--- CoeActivityWrapperImpl.persistEntity begin ---------  ");
		CoeActivity entity = coeActivityDelegate.persistEntity(coeActivity);
		Long id = coeActivity.getId();
		LOGGER.debug("--- CoeActivityWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}
	
	public CoeActivity createDraft(CoeActivity coeActivity) {
		
		CoeActivity entity = coeActivityDelegate.createDraft(coeActivity);
		coeActivity.setActivityNo(coeActivity.getActivityNo());
		coeActivity.setOriginalLink(entity.getOriginalLink());
		coeActivity.setAid(entity.getAid());
		coeActivity.setId(entity.getId());
		
		return entity;
	}
	
	@Override
	public CoeActivity mergeActivity(CoeActivity coeActivity) {
		return coeActivityDelegate.mergeActivity(coeActivity);
	}
	
	@Override
	public CoeActivity mergeForBaseinfo(CoeActivity coeActivity) {
		//转义字符
		String title = coeActivity.getTitle();
		if(StringUtils.isNotBlank(title)) {
			title = HtmlUtils.htmlEscape(title,UrlUtil.CHARSET_UTF_8);
			coeActivity.setTitle(title);
			
		}
		String name = coeActivity.getName();
		if(StringUtils.isBlank(name)) {
			coeActivity.setName(title);
		}else {
			coeActivity.setName(HtmlUtils.htmlEscape(name,UrlUtil.CHARSET_UTF_8));
		}
		
		String description = coeActivity.getDescription();
		if(StringUtils.isNotBlank(description)) {
			coeActivity.setDescription(HtmlUtils.htmlEscape(description,UrlUtil.CHARSET_UTF_8));
		}
		
		String penname = coeActivity.getPenname();
		if(StringUtils.isNotBlank(penname)) {
			coeActivity.setPenname(HtmlUtils.htmlEscape(penname,UrlUtil.CHARSET_UTF_8));
		}
		
		String preIntro = coeActivity.getPreIntro();
		if(StringUtils.isNotBlank(preIntro)) {
			coeActivity.setPreIntro(HtmlUtils.htmlEscape(preIntro,UrlUtil.CHARSET_UTF_8));
		}
		
		return coeActivityDelegate.mergeForBaseinfo(coeActivity);
	}

	@Override
	public CoeActivity saveOrUpdate(CoeActivity coeActivity) {
		return coeActivityDelegate.saveOrUpdate(coeActivity);
	}

	@Override
	public CoeActivity mergeForUnPublished(CoeActivity coeActivity) {
		return coeActivityDelegate.mergeForUnPublished(coeActivity);
	}

	@Override
	public CoeActivity mergeActivityForLink(CoeActivity coeActivity) {
		return coeActivityDelegate.mergeActivityForLink(coeActivity);
	}

	@Override
	public CoeActivity mergeActivityForTitle(CoeActivity coeActivity) {
		return coeActivityDelegate.mergeActivityForTitle(coeActivity);
	}

	@Override
	public List<CoeActivity> findByPublished(CoeActivity coeActivity) {
		return coeActivityDelegate.findByPublished(coeActivity);
	}
	
	@Override
	public Page<CoeActivity> findByPublished(CoeActivity coeActivity, Pageable pageable, boolean withCategoryTree) {
		Page<CoeActivity> page = findByPublished(coeActivity,pageable,true,true, true, true,false,true,true, true);
		if(withCategoryTree) {
			Category categoryTree = findCategoryTree(this.mainId,this.subId);
			coeActivity.setCategoryTree(categoryTree);
		}
		return page;
	}
	
	@Override
	public Page<CoeActivity> findByPublished(CoeActivity coeActivity, Pageable pageable) {
		Page<CoeActivity> page = findByPublished(coeActivity,pageable,true);
		return page;
	}
	
	@Override
	public PageResult<CoeActivity> findPublishedForSelect(CoeActivity coeActivity, Pageable pageable) {
		PageResult<CoeActivity> page =(PageResult) findByPublished(coeActivity,pageable,true,true, true, true,false,false, true,true);
		return page;
	}
	
	public List<CoeActivity> findByIdList(List<Long> idList,boolean withTags, boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withPraise, boolean withAuthorPayCode, boolean withJoinItem , boolean withFee){
		List<CoeActivity> content = findByIdList(idList);
		if(null != content && !content.isEmpty()) {
			fillRefBase( content, withTags, withCover, withBanner, withCategory, withPraise, withAuthorPayCode, withJoinItem, withFee);
		}
		return content;
	}
	
	public Page<CoeActivity> findByPublished(CoeActivity coeActivity, Pageable pageable, 
			boolean withTags, boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withPraise, boolean withAuthorPayCode,  boolean withJoinItem , boolean withFee
			) {
		Page<CoeActivity> page = coeActivityDelegate.findByPublished(coeActivity, pageable);
		if (null != page && page.getTotalElements() > 0) {

			List<CoeActivity> content = page.getContent();
			fillRefBase(content, withTags, withCover, withBanner, withCategory, withPraise, withAuthorPayCode,withJoinItem,withFee);
		}
		return page;
	}
	
	public List<CoeActivity> fillRefWithCoverAndBanner(List<CoeActivity> contentList) {
		 contentList = fillRefBase(contentList,false,true, true,false, false, false, true, true);
		return contentList;
	}
	
	public List<CoeActivity> fillRefWithCoverAndBannerAndAuthor(List<CoeActivity> contentList) {
		 contentList = fillRefBase(contentList,false,true, true,false, false, true, true, true);
		return contentList;
	}
	
	public List<CoeActivity> fillRefForList(List<CoeActivity> contentList) {
		 contentList = fillRefBase(contentList,true,true, true, true, true, true,true,true);
		return contentList;
	}
	
	public List<CoeActivity> fillRefBase(List<CoeActivity> content, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory,
			boolean withPraise, boolean withAuthorPayCode , boolean withJoinItem , boolean withFee) {
		return fillRef( content,  withTags,  withCover,  withBanner,  withCategory,
				 withPraise,  withAuthorPayCode, false, withJoinItem, withFee, null);
	}
	/**
	 * @param withTags
	 * @param withCover
	 * @param withBanner
	 * @param withCategory
	 * @param withPraise
	 * @param withAuthorPayCode
	 * @param content
	 */
	public List<CoeActivity> fillRef(List<CoeActivity> content, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory,
			boolean withPraise, boolean withAuthorPayCode, boolean withRefPage,  boolean withJoinItem , boolean withFee, 
			Pageable refPageable) {
		if (null != content && !content.isEmpty()) {
			List<Long> mainIdList = new ArrayList<>(content.size());
			List<Long> userIdList = new ArrayList<>(content.size());
			Map<Long, CoeActivity> cpMap = new HashMap<>(content.size());
			for (CoeActivity cp : content) {
				Long activityId = cp.getId();
				mainIdList.add(activityId);
				cpMap.put(activityId, cp);

				nicknameHandler.handleNickname(cp);
				
				Long userId = cp.getUserId();
				if (!userIdList.contains(userId)) {
					userIdList.add(userId);
				}
				
				Sayword sayword=cp.getSayword();
				if(null != sayword) {
					String descr=sayword.getDescription();
					sayword.setDescription(HtmlUtil.textarea2UnEscapeForHtml(descr));
				}
			}
			if (null != mainIdList && !mainIdList.isEmpty()) {
				if (withCover) {
					Map<Long, List<PhotoCover>> coverMap = photoCoverWrapper.findByMainIdListAndSubId(mainIdList, PhotoCover.SUBID_COVER_HEAD);
					if (null != coverMap && !coverMap.isEmpty()) {
						for (Map.Entry<Long, List<PhotoCover>> entry : coverMap.entrySet()) {
							Long activityId = entry.getKey();
							CoeActivity cp = cpMap.get(activityId);
							cp.setCoverList(entry.getValue());
						}
					}
				}
				if (withBanner) {
					Map<Long, List<PhotoBanner>> bannerMap = photoBannerWrapper.findByMainIdListAndSubId(mainIdList, PhotoBanner.SUBID_BANNER_HEAD);
					if (null != bannerMap && !bannerMap.isEmpty()) {
						for (Map.Entry<Long, List<PhotoBanner>> entry : bannerMap.entrySet()) {
							Long activityId = entry.getKey();
							CoeActivity cp = cpMap.get(activityId);
							cp.setBannerList(entry.getValue());
						}
					}
				}
				if (withTags) {
					Map<Long, List<CoeActivityTag>> activityTagMap = coeActivityTagWrapper.findMapByMainIdList(mainIdList);
					if (null != activityTagMap && !activityTagMap.isEmpty()) {
						for (Map.Entry<Long, List<CoeActivityTag>> entry : activityTagMap.entrySet()) {
							Long activityId = entry.getKey();
							CoeActivity cp = cpMap.get(activityId);
							cp.setActivityTagList(entry.getValue());
						}
					}
				}
				if (withCategory) {
					Map<Long, List<CoeCategActivity>> categoryMap = coeCategActivityWrapper.findMapByMainIdList(mainIdList);
					if (null != categoryMap && !categoryMap.isEmpty()) {
						for (Map.Entry<Long, List<CoeCategActivity>> entry : categoryMap.entrySet()) {
							Long activityId = entry.getKey();
							CoeActivity cp = cpMap.get(activityId);
							cp.setCategoryList(entry.getValue());
						}
					}
				}

				if (withPraise) {
					// 最新4名点赞用户
					for (CoeActivity cp : content) {
						PageRequest pageable = new PageRequest(0, 4, Direction.DESC, "aid");
						Long id = cp.getId();
						Page<CoeActivityPraise> praisePage = coeActivityPraiseWrapper.findByMainId(id, pageable);
						cp.setPraiseList(praisePage.getContent());
						cp.setPraisePage(praisePage);
					}

				}
				Map<Long, CoeUser> authorPayCodeMap = null; 
				if (withAuthorPayCode) {
					authorPayCodeMap = coeUserWrapper.findMapByUserIdList(userIdList, withAuthorPayCode);
				}
				
				Map<Long, CoeActivityTime> actTimeMap = null;
				if(withJoinItem) {
					actTimeMap = coeActivityTimeWrapper.findMapByMainIdList(mainIdList);
				}
				
				Map<Long, CoeActivityFee> actFeeMap = null;
				if(withFee) {
					actFeeMap = coeActivityFeeWrapper.findMapByMainIdList(mainIdList);
				}
				
				if(withAuthorPayCode || withJoinItem || withFee) {
					for (CoeActivity cp : content) {
						Long userId = cp.getUserId();
						Long id=cp.getId();
						if(null != authorPayCodeMap) {
							CoeUser author = authorPayCodeMap.get(userId);
							nicknameHandler.handleNicknameForUser(author);
						}
						if(null != actTimeMap) {
							CoeActivityTime actTime = actTimeMap.get(id);
							cp.setActTime(actTime);
						}
						
						if(null != actFeeMap) {
							CoeActivityFee actFee = actFeeMap.get(id);
							cp.setActFee(actFee);
						}
					}
				}
			}
		}
		return content;
	}
	
	@Override
	public List<CoeActivity> findPublishedByName(String name) {
		return coeActivityDelegate.findPublishedByName(name);
	}

	@Override
	public List<CoeActivity> findPublishedByTitle(String title) {
		return coeActivityDelegate.findPublishedByTitle(title);
	}
	
	public List<CoeActivity> findDraftList(CoeActivity coeActivity){
		return coeActivityDelegate.findDraftList(coeActivity);
	}
	
	public Long countDraftList(CoeActivity coeActivity) {
		return coeActivityDelegate.countDraftList(coeActivity);
	}
	
	public Long countTemplateForUser(Long userId) {
		return coeActivityDelegate.countTemplateForUser(userId);
	}

	//含tag信息
	@Override
	public CoeActivity findDetail(Long id) {
		return findById(id);
	}
	
	public Page<CoeActivity> findPublished(CoeActivity coeActivity, Pageable pageable) {
		String title = StringUtils.trim(coeActivity.getTitle());
		String name = StringUtils.trim(coeActivity.getName());
		if(StringUtils.isNotBlank(title)) {
			if(StringUtils.isBlank(name)) {
				coeActivity.setName(title);
			}
		}
		return coeActivityDelegate.findPublished(coeActivity,pageable);
	}

	public Page<CoeActivity> findPublishedWithCoverAndBanner(CoeActivity coeActivity, Pageable pageable) {
		String title = StringUtils.trim(coeActivity.getTitle());
		String name = StringUtils.trim(coeActivity.getName());
		if (StringUtils.isNotBlank(title)) {
			if (StringUtils.isBlank(name)) {
				coeActivity.setName(title);
			}
		}
		Page<CoeActivity> page = coeActivityDelegate.findPublished(coeActivity, pageable);
		List<CoeActivity> content = page.getContent();
		if(null != content && !content.isEmpty()) {
			fillRefWithCoverAndBanner(content);
			
			
			List<Long> idList = new ArrayList<>(content.size());
			Map<Long, CoeActivity> actMap = new HashMap<>(content.size());
			for(CoeActivity act : content) {
				Long id = act.getId();
				idList.add(id);
				actMap.put(id, act);
			}
			//查询已报名人数
			List<CoeActivityUserVo> actUserCountList = coeActivityUserWrapper.countByMainIdList(idList);
			if(null != actUserCountList && !actUserCountList.isEmpty()) {
				for(CoeActivityUserVo vo : actUserCountList) {
					Long mainId = vo.getMainId();
					CoeActivity act = actMap.get(mainId);
					if(null != act) {
						act.setParticipateNum(vo.getNum());
					}
				}
			}
		}
		return page;
	}

	@Override
	public CoeActivity mergeForRefresh(CoeActivity coeActivity) {
		return coeActivityDelegate.mergeForRefresh(coeActivity);
	}

	@Override
	public CoeActivity mergeForEditQickly(CoeActivity coeActivity) {
		return coeActivityDelegate.mergeForEditQickly(coeActivity);
	}

	@Override
	public CoeActivity mergeForPublish(CoeActivity coeActivity) {
		Long userId = coeActivity.getUserId();
		CoeUser coeUser = coeUserWrapper.findByUserId(userId);
		String headImg = coeUser.getHeadImg();
		//coeActivity.setSayword(coeUser.getSayword());
		Sayword sayword = saywordWrapper.findByUserIdAndMainId(userId);
		coeActivity.setSaywordId(sayword.getId());
		coeActivity.setHeadImg(headImg);
		coeActivity.setNickname(coeUser.getNickname());
		//coeActivity.getPenname();
		return coeActivityDelegate.mergeForPublish(coeActivity);
	}
	
	public CoeActivity mergeForClosed(CoeActivity coeActivity) {
		return coeActivityDelegate.mergeForClosed(coeActivity);
	}
	
	public CoeActivity mergeForTemplate(CoeActivity coeActivity) {
		return coeActivityDelegate.mergeForTemplate(coeActivity);
	}
	
	public CoeActivity mergeForDeleted(CoeActivity coeActivity) {
		coeActivity.setDeleted(true);
		return coeActivityDelegate.mergeForDeleted(coeActivity);
	}
	
	public CoeActivity mergeForPraise(CoeActivity coeActivity) {
		return coeActivityDelegate.mergeForPraise(coeActivity);
	}

	@Override
	public boolean publishedById(Long id) {
		boolean published = coeActivityDelegate.publishedById(id);
		return published;
	}

	@Override
	public CoeActivity persistForCover(CoeActivity coeActivity) {
		List<PhotoCover>  coverList = coeActivity.getCoverList();
		if(null != coverList && !coverList.isEmpty()) {
			coverList = photoCoverWrapper.persistForPhoto(coverList);
			coeActivity.setCoverList(coverList);
		}
		return coeActivity;
	}
	
	public CoeActivity persistForBanner(CoeActivity coeActivity) {
		List<PhotoBanner>  bannerList = coeActivity.getBannerList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = photoBannerWrapper.persistForPhoto(bannerList);
			coeActivity.setBannerList(bannerList);
		}
		return coeActivity;
	}
	
	@Override
	public CoeActivity findDetailLastRefreshByUserId(Long userId) {
		CoeActivity entity = findActivityLastRefreshByUserId(userId);
		if(null != entity) {
			fillRefBaseSingle(entity);
		}
		return entity;
	}
	
	public CoeActivity findActivityLastRefreshByUserId(Long userId) {
		Pageable pageable = new PageRequest(0,1,Direction.DESC,"refreshTime");
		Page<CoeActivity> page = findPageByUserId(userId, pageable);
		CoeActivity entity = null;
		if(null != page && page.getTotalElements()>0) {
			entity = page.getContent().get(0);
		}
		return entity;
	}
	
	public Page<CoeActivity> findPageByUserId(Long userId , Pageable pageable){
		CoeActivity coeActivity = new CoeActivity();
		coeActivity.setUserId(userId);
		return findPageByUserId( coeActivity ,  pageable);
	}
	
	public Page<CoeActivity> findPageByUserId(CoeActivity coeActivityr , Pageable pageable){
		return coeActivityDelegate.findPageByUserId( coeActivityr ,  pageable);
	}
	
	
	public Page<CoeActivity> findPageForInvitor(Long createdBy, Long userId){
		Page<CoeActivity> page = null;
		if(null != createdBy && userId != null) {
			if(userId.equals(createdBy)) {
				//没有邀请人
				//TODO 寻找位置绑定者，二期再做
			}
			Pageable pageable = new PageRequest(0,3,Direction.DESC, "refreshTime");
			CoeActivity coeActivity = new CoeActivity();
			coeActivity.setCreatedBy(createdBy);
			coeActivity.setUserId(createdBy);
			page = findPageForInvitor(coeActivity, pageable);
		}
		return page;
	}
	@Override
	public Page<CoeActivity> findPageForInvitor(CoeActivity coeActivity, Pageable pageable) {
		Page<CoeActivity> page = findPageByUserId(coeActivity,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<CoeActivity> content = page.getContent();
				fillRefBase(content, true, true, true, true, false, true, true,  true);
		}
		return page;
	}
	
	public Page<CoeActivity> findPageForCurrentUser(Long userId){
		Page<CoeActivity> page = null;
		if( userId != null) {
			Pageable pageable = new PageRequest(0,3,Direction.DESC, "refreshTime");
			CoeActivity coeActivity = new CoeActivity();
			coeActivity.setUserId(userId);
			page = findPageForCurrentUser(coeActivity, pageable);
		}
		return page;
	}
	@Override
	public Page<CoeActivity> findPageForCurrentUser(CoeActivity coeActivity, Pageable pageable) {
		Page<CoeActivity> page = findPageByUserId(coeActivity,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<CoeActivity> content = page.getContent();
				fillRefBase(content, true, true, true, true, false, true, true, true);
		}
		return page;
	}
	
	public Page<CoeActivity> findPageTagForCurrentUser(Long userId){
		Page<CoeActivity> page = null;
		if( userId != null) {
			Pageable pageable = new PageRequest(0,2, Direction.DESC,"refreshTime");//最新的两篇文章
			CoeActivity coeActivity = new CoeActivity();
			coeActivity.setUserId(userId);
			page = findPageTagForCurrentUser(coeActivity, pageable);
		}
		return page;
	}
	public Page<CoeActivity> findPageTagForCurrentUser(CoeActivity coeActivity, Pageable pageable) {
		Page<CoeActivity> page = coeActivityDelegate.findPageByUserId(coeActivity,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<CoeActivity> content = page.getContent();
				fillRefBase(content, true, false, false, false, false, false,false, false);
		}
		return page;
	}
	
	
	public Page<CoeActivity> findPageForRandomUserId(Long userId){
		int size=1;
		Long count = countForPublishedWithoutSelf( userId);
		if(count>0) {
			Integer countInt=count.intValue();
			Pageable pageable = null;
			if(count==1) {
				pageable = new PageRequest(0,1);
			}else {
				Integer offset = ThreadLocalRandom.current().nextInt(count.intValue());
				if(countInt-size < offset) {
					offset = countInt-size;
				}
				if(offset<0) {
					offset=0;
				}
				 pageable = new PageRequest(offset,size);
			}
			
			CoeActivity coeActivity = new CoeActivity();
			coeActivity.setUserId(userId);
			Page<CoeActivity> result = findPageForRandomUserId(coeActivity, pageable);
			return result;
		}
		return null;
	}
	
	
	public Page<CoeActivity> findByPublishedNotSelf(CoeActivity coeActivity, Pageable pageable){
		return coeActivityDelegate.findByPublishedNotSelf(coeActivity,pageable);
	}
	
	public Page<CoeActivity> findPageForRandomUserId(CoeActivity coeActivity, Pageable pageable){
		
		Page<CoeActivity> page = coeActivityDelegate.findByPublishedNotSelf(coeActivity,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<CoeActivity> content = page.getContent();
				fillRefBase(content, true, true, true, true, false, true, true, true);
		}
		return page;
	}
	
	public Long countForPublishedWithoutSelf(Long userId) {
		
		return coeActivityDelegate.countForPublishedWithoutSelf( userId);
	}

	@Override
	public Page<CoeActivity> findPageRefForUser(CoeActivity coeActivity, Pageable pageable) {
		Page<CoeActivity> page = coeActivityDelegate.findByPublished(coeActivity,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<CoeActivity> content = page.getContent();
				fillRefBase(content, true, true, true, false, false, true, true, true);
		}
		return page;
	}
	
	public Page<CoeActivity> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable){
		return coeActivityDelegate.findPageByMainTypeAndUserId(mainType, userIdList, publishStatus, pageable);
	}
	
	public Page<CoeActivity> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable){
		return coeActivityDelegate.findPageByMainTypeAndUserId( userIdList, pageable);
	}
	
	public List<CoeActivity> findListByMainTypeAndUserId(List<Long> userIdList){
		List<CoeActivity> content = coeActivityDelegate.findListByMainTypeAndUserId( userIdList);
		
		return content;
	}
	
	
	public CoeActivity findDetailFilledMainTypeList(Long id) {
		CoeActivity entity = findDetailWithMainTypeList(id);
		List<CoeActivity> mainTypeList = entity.getMainTypeList();
		//校验是否存在主技能，如不存在，则取最新5个刷新技能,后续改造成从该分类下获取刷新技能
		if(null == mainTypeList || mainTypeList.isEmpty()) {
			PageRequest refreshPageable = new PageRequest(0,5,Direction.DESC, "refreshTime");
			CoeActivity coeActivity = new CoeActivity();
			coeActivity.setId(id);
			coeActivity.setUserId(entity.getUserId());
			Page<CoeActivity> refreshPage = findByPublishedWithAuthorNotSelf(coeActivity, refreshPageable);
			if(null != refreshPage && refreshPage.getTotalElements()>0) {
				entity.setRefreshPage(refreshPage);
			}
		}
		return entity;
	}
	public CoeActivity findDetailFilledMainTypePage(Long id) {
		CoeActivity entity = findDetailWithMainTypeList(id);
		List<CoeActivity> mainTypeList = entity.getMainTypeList();
		//校验是否存在主技能，如不存在，则取最新5个刷新技能,后续改造成从该分类下获取刷新技能
		if(null == mainTypeList) {
			PageRequest refreshPageable = new PageRequest(0,5,Direction.DESC, "refreshTime");
			CoeActivity coeActivity = new CoeActivity();
			coeActivity.setId(id);
			coeActivity.setUserId(entity.getUserId());
			Page<CoeActivity> refreshPage = findByPublishedWithAuthorNotSelf(coeActivity, refreshPageable);
			if(null != refreshPage && refreshPage.getTotalElements()>0) {
				entity.setRefreshPage(refreshPage);
			}
		}
		return entity;
	}
	
	
	public CoeActivity findDetailWithMainTypeList(Long id) {
		CoeActivity entity = findDetail(id);
		entity = fillMainTypeList(entity);
		return entity;
	}
	
	/**
	 * TODO
	 * @param id
	 * @return
	 */
	public CoeActivity findWithPaisePage(Long id) {
		
		CoeActivity entity = findDetail(id);
		entity = fillMainTypeList(entity);
		return entity;
	}
	
	public CoeActivity fillMainTypeList(CoeActivity entity) {
		List<CoeActivityPraise> praiseList = entity.getPraiseList();
		Map<Long , CoeActivityPraise> praiseMap = new HashMap<>();
		if(null != praiseList && !praiseList.isEmpty()) {
			List<Long> praiseUserIdList = new ArrayList<>(praiseList.size());
			
			int j=0;
			int i=praiseList.size()-1;
			List<CoeActivity> mainTypeList =  null;
			for(;i>=0; i--) {
				CoeActivityPraise p = praiseList.get(i);
				Long userId = p.getUserId();
				praiseMap.put(userId, p);
				Long praiseUserId = p.getUserId();
				praiseUserIdList.add(praiseUserId);
				j++;
				if(j>4 && j%5==0) {
					mainTypeList = findListByMainTypeAndUserId(praiseUserIdList);
					if(null != mainTypeList && !mainTypeList.isEmpty()) {
						break;
					}
				}
			}
			if(null == mainTypeList) {
				mainTypeList = findListByMainTypeAndUserId(praiseUserIdList);
			}
			
			if(null != mainTypeList && !mainTypeList.isEmpty()) {
				for(CoeActivity mainType : mainTypeList) {
					nicknameHandler.handleNickname(mainType);
				}
				entity.setMainTypeList(mainTypeList);
			}
		}
		return entity;
	}
	
	public Page<CoeActivity> findByPublishedWithAuthor(CoeActivity coeActivity, Pageable pageable){
		Page<CoeActivity> page = findByPublished(coeActivity,pageable,false,false, false, false,false,true,false, false);
		return page;
	}
	public Page<CoeActivity> findByPublishedWithAuthorNotSelf(CoeActivity coeActivity, Pageable pageable){
		Page<CoeActivity> page = findByPublishedNotSelf(coeActivity,pageable);
		if (null != page && page.getTotalElements() > 0) {
			List<CoeActivity> content = page.getContent();
			fillRefBase(content, false, false, false, false, false, true, false, false);
		}
		return page;
	}
	
	@Override
	public List<CoeActivity> fillRef(List<CoeActivity> contentList, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory,
			boolean withPraise, boolean withAuthorPayCode, boolean withJoinItem , boolean withFee) {
		contentList = fillRefBase(contentList, withTags, withCover, withBanner, withCategory, withPraise, withAuthorPayCode, withJoinItem, withFee);
		return contentList;
	}
	
	
	@Override
	public CoeActivity mergeForMainType(CoeActivity coeActivity) {
		return coeActivityDelegate.mergeForMainType(coeActivity);
	}
	
	/**
	 * 查询活动点赞列表
	 * @param id
	 * @param pageable 点赞用户查询分页
	 * @return
	 */
	public CoeActivity findCoeActivityForPraisePage(Long id, Pageable pageable) {
		CoeActivity entity = findByIdOnly(id);
		if (null != entity) {
			Page<CoeActivityPraise> praisePage = coeActivityPraiseWrapper.findByMainId(id, pageable);
			entity.setPraisePage(praisePage);
			List<CoeActivityPraise> contentList = praisePage.getContent();
			if (null != contentList && !contentList.isEmpty()) {
				List<Long> praiseUserIdList = new ArrayList<>(contentList.size());
				Map<Long, CoeActivityPraise> map = new HashMap<>();
				for (CoeActivityPraise content : contentList) {
					Long userId = content.getUserId();
					praiseUserIdList.add(userId);
					map.put(userId, content);
				}
				List<CoeActivity> coeActivityList = findListByMainTypeAndUserId(praiseUserIdList);
				if (null != coeActivityList && !coeActivityList.isEmpty()) {
					//填充tag, cover, banner
					fillRefBase(coeActivityList,true,true,true,false,false,false,true, true);
					for (CoeActivity p : coeActivityList) {
						Long userId = p.getUserId();
						CoeActivityPraise praise = map.get(userId);
						praise.setActivity(p);
					}
				}
			}
		}
		return entity;
	}
	
	@Override
	public Page<CoeActivity> findPageForRecommend(CoeActivity coeActivity, Pageable pageable) {
		if(null == coeActivity) {
			coeActivity = new CoeActivity();
			coeActivity.setRecommend(CoeActivity.RECOMMEND_YES.getValueInt());
		}
		Page<CoeActivity> page = findByPublished(coeActivity, pageable);
		return page;
	}
	
	public CoeActivity findMainTypeByUserId(Long userId) {
		return coeActivityDelegate.findMainTypeByUserId(userId);
	}
	
	
	@Override
	public CoeActivity mergeActivityForTitleOnly(CoeActivity coeActivity) {
		//转义字符
		String title = coeActivity.getTitle();
		if(StringUtils.isNotBlank(title)) {
			title = HtmlUtils.htmlEscape(title,UrlUtil.CHARSET_UTF_8);
			coeActivity.setTitle(title);
		}
		String name = coeActivity.getName();
		if(StringUtils.isBlank(name)) {
			coeActivity.setName(title);
		}else {
			coeActivity.setName(HtmlUtils.htmlEscape(name,UrlUtil.CHARSET_UTF_8));
		}
		
		return coeActivityDelegate.mergeActivityForTitleOnly(coeActivity);
	}
	@Override
	public CoeActivity mergeActivityForDescriptionOnly(CoeActivity coeActivity) {
		String description = coeActivity.getDescription();
		if(StringUtils.isNotBlank(description)) {
			coeActivity.setDescription(HtmlUtils.htmlEscape(description,UrlUtil.CHARSET_UTF_8));
		}
		return coeActivityDelegate.mergeActivityForDescriptionOnly(coeActivity);
	}
	
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Pageable pageable){
		return coeActivityDelegate.findPageForTemplate( userId, template, pageable);
	}
	public Page<CoeActivity> findPageForTemplate(Long userId,Integer template, Long id,Pageable pageable){
		return coeActivityDelegate.findPageForTemplate( userId, template, id, pageable);
	}
	
	public CoeActivity loadTemplate(CoeActivity coeActivity) {
		return coeActivityDelegate.loadTemplate( coeActivity);
	}
	
}
