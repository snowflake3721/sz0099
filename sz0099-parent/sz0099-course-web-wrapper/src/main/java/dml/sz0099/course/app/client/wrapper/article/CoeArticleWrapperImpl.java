package dml.sz0099.course.app.client.wrapper.article;

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

import dml.sz0099.course.app.biz.delegate.article.CoeArticleDelegate;
import dml.sz0099.course.app.client.resolver.adaptor.category.CategoryArticleAdaptor;
import dml.sz0099.course.app.client.resolver.adaptor.position.PositionArticleAdaptor;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.user.SaywordWrapper;
import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.data.handler.util.NicknameHandler;
import dml.sz0099.course.app.data.show.blooming.config.MainCircleConfig;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;
import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.article.PhotoBanner;
import dml.sz0099.course.app.persist.entity.article.PhotoCover;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeArticleWrapperImpl,组件封装
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
public class CoeArticleWrapperImpl implements CoeArticleWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeArticleDelegate coeArticleDelegate;
	
	@Autowired
	private CoeArticleTagWrapper coeArticleTagWrapper;
	
	
	@Autowired
	private PhotoCoverWrapper photoCoverWrapper;
	
	@Autowired
	private PhotoBannerWrapper photoBannerWrapper;
	
	@Autowired
	private CoeCategArticleWrapper coeCategArticleWrapper;
	
	@Autowired
	private CategoryArticleAdaptor categoryArticleAdaptor;
	
	@Autowired
	private CoeArticlePraiseWrapper coeArticlePraiseWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private SaywordWrapper saywordWrapper;
	
	@Resource(name="mainCircleConfigArticle")
	private MainCircleConfig mainCircleConfig;
	
	@Autowired
	private NicknameHandler nicknameHandler;
	
	private Long mainId = Robot.ROBOT_PLAT.getId();
	
	private Long subId = Robot.ROBOT_PLAT.getId();
	
	/**
	 * 根据Id查询CoeArticle实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticle findById(Long id) {
		CoeArticle coeArticle = findById(id,true, true, true, true, true, true,true,true);
		return coeArticle;
	}
	public CoeArticle findByIdOnly(Long id) {
		CoeArticle coeArticle = findById(id,false,false, false,false, false, false,false, false);
		return coeArticle;
	}
	
	public CoeArticle findByIdWithCoverAndBanner(Long id) {
		CoeArticle coeArticle = findById(id,false,true, true,false, false, false,false, false);
		return coeArticle;
	}
	
	public Category findCategoryTree(Long mainId, Long subId) {
		if(mainId==null) {
			mainId = this.mainId;
		}
		if(subId==null) {
			subId = this.subId;
		}
		Category categoryTree = categoryArticleAdaptor.queryTree(mainId, subId);
		return categoryTree;
	}
	
	public CoeArticle findById(Long id, boolean withTags , boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withCategoryTree, boolean withPraise,
			boolean withAuthorPayCode, boolean withSayword) {
		LOGGER.debug("--- CoeArticleWrapperImpl.findById begin --------- id is:{} , withTags: {} ", id, withTags);
		CoeArticle coeArticle = coeArticleDelegate.findById(id);
		if(null != coeArticle) {
			fillRefSingle(coeArticle, withTags, withCover, withBanner, withCategory, withCategoryTree, withPraise, withAuthorPayCode, withSayword);
		}
		LOGGER.debug("--- CoeArticleWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeArticle);
		return coeArticle;
	}
	
	public CoeArticle fillRefBaseSingle(CoeArticle coeArticle) {
		boolean withTags = true;
		boolean withCover = true;
		boolean withBanner = true;
		boolean withCategory = true;
		boolean withCategoryTree = true;
		boolean withPraise = true;
		boolean withAuthorPayCode = true;
		boolean withSayword = true;
		return fillRefSingle(coeArticle, withTags, withCover, withBanner, withCategory, withCategoryTree, withPraise, withAuthorPayCode, withSayword);
	}
	/**
	 * @param coeArticle
	 * @param withTags
	 * @param withCover
	 * @param withBanner
	 * @param withCategory
	 * @param withCategoryTree
	 * @param withPraise
	 * @param withAuthorPayCode
	 */
	public CoeArticle fillRefSingle(CoeArticle coeArticle, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory,
			boolean withCategoryTree, boolean withPraise, boolean withAuthorPayCode, boolean withSayword) {
		if(null != coeArticle) {
			Long id = coeArticle.getId();
			nicknameHandler.handleNickname(coeArticle);
			/*String nickname = coeArticle.getNickname();
			if(StringUtils.isNotBlank(nickname)) {
				coeArticle.setNickname(Base64Util.decode(nickname));
			}else {
				coeArticle.setNickname("卓无名");
			}*/
		
			if(withTags) {
				List<CoeArticleTag> articleTagList = coeArticleTagWrapper.findByMainId(id);
				coeArticle.setArticleTagList(articleTagList);
			}
			if(withCover) {
				List<Long> subIdList = new ArrayList<>(1);
				subIdList.add(PhotoCover.SUBID_COVER_HEAD);
				List<PhotoCover>  coverList = photoCoverWrapper.findBySubIdListAndMainId(subIdList, id);
				coeArticle.setCoverList(coverList);
			}
			if(withBanner) {
				List<Long> subIdList = new ArrayList<>(1);
				subIdList.add(PhotoBanner.SUBID_BANNER_HEAD);
				List<PhotoBanner>  bannerList = photoBannerWrapper.findBySubIdListAndMainId(subIdList, id);
				coeArticle.setBannerList(bannerList);
			}
			if(withCategory) {
				//获取分类树 TODO
				List<CoeCategArticle> categoryList = coeCategArticleWrapper.findByMainId(id);
				if(null == categoryList) {
					categoryList = new ArrayList<>(1);
				}
				if(categoryList.isEmpty()) {
					CoeCategArticle prod = new CoeCategArticle();
					prod.setMainId(id);
					categoryList.add(prod);
					
				}
				coeArticle.setCategoryList(categoryList);
				if(withCategoryTree) {
					Category categoryTree = categoryArticleAdaptor.queryTree(mainId, subId);
					coeArticle.setCategoryTree(categoryTree);
				}
			}
			if(withPraise) {
				//前30名点赞用户
				PageRequest pageable = new PageRequest(0, 30, Direction.ASC, "aid");
				Page<CoeArticlePraise> praisePage = coeArticlePraiseWrapper.findByMainId(id, pageable);
				coeArticle.setPraiseList(praisePage.getContent());
				coeArticle.setPraisePage(praisePage);
			}
			
			if(withAuthorPayCode) {
				Long userId = coeArticle.getUserId();
				CoeUser author = coeUserWrapper.findByUserId(userId,withAuthorPayCode,false, withSayword);
				nicknameHandler.handleNicknameForUser(author);
				coeArticle.setAuthor(author);
			}
			
			//处理传说
			Sayword sayword=coeArticle.getSayword();
			if(null != sayword) {
				String descr=sayword.getDescription();
				sayword.setDescription(HtmlUtil.textarea2UnEscapeForHtml(descr));
			}
		}
		return coeArticle;
	}
	
	public boolean existById(Long id) {
		LOGGER.debug("--- CoeArticleWrapperImpl.existById begin --------- id is:{} ", id);
		return coeArticleDelegate.existById(id);
	}
	
	/**
	 * 根据IdList查询CoeArticle实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticle> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeArticleWrapperImpl.findByIdList begin ---------  ");
		List<CoeArticle> coeArticleList = coeArticleDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeArticleWrapperImpl.findByIdList end ---------  result is {} ",  coeArticleList);
		return coeArticleList;
	}
	
	@Override
	public CoeArticle persistEntity(CoeArticle coeArticle) {
		LOGGER.debug("--- CoeArticleWrapperImpl.persistEntity begin ---------  ");
		CoeArticle entity = coeArticleDelegate.persistEntity(coeArticle);
		Long id = coeArticle.getId();
		LOGGER.debug("--- CoeArticleWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}
	
	public CoeArticle createDraft(CoeArticle coeArticle) {
		
		CoeArticle entity = coeArticleDelegate.createDraft(coeArticle);
		coeArticle.setArticleNo(coeArticle.getArticleNo());
		coeArticle.setOriginalLink(entity.getOriginalLink());
		coeArticle.setAid(entity.getAid());
		coeArticle.setId(entity.getId());
		
		return entity;
	}
	
	@Override
	public CoeArticle mergeArticle(CoeArticle coeArticle) {
		return coeArticleDelegate.mergeArticle(coeArticle);
	}
	
	@Override
	public CoeArticle mergeForBaseinfo(CoeArticle coeArticle) {
		//转义字符
		String title = coeArticle.getTitle();
		if(StringUtils.isNotBlank(title)) {
			title = HtmlUtils.htmlEscape(title,UrlUtil.CHARSET_UTF_8);
			coeArticle.setTitle(title);
			
		}
		String name = coeArticle.getName();
		if(StringUtils.isBlank(name)) {
			coeArticle.setName(title);
		}else {
			coeArticle.setName(HtmlUtils.htmlEscape(name,UrlUtil.CHARSET_UTF_8));
		}
		
		String description = coeArticle.getDescription();
		if(StringUtils.isNotBlank(description)) {
			coeArticle.setDescription(HtmlUtils.htmlEscape(description,UrlUtil.CHARSET_UTF_8));
		}
		
		String penname = coeArticle.getPenname();
		if(StringUtils.isNotBlank(penname)) {
			coeArticle.setPenname(HtmlUtils.htmlEscape(penname,UrlUtil.CHARSET_UTF_8));
		}
		
		String preIntro = coeArticle.getPreIntro();
		if(StringUtils.isNotBlank(preIntro)) {
			coeArticle.setPreIntro(HtmlUtils.htmlEscape(preIntro,UrlUtil.CHARSET_UTF_8));
		}
		
		return coeArticleDelegate.mergeForBaseinfo(coeArticle);
	}

	@Override
	public CoeArticle saveOrUpdate(CoeArticle coeArticle) {
		return coeArticleDelegate.saveOrUpdate(coeArticle);
	}

	@Override
	public CoeArticle mergeForUnPublished(CoeArticle coeArticle) {
		return coeArticleDelegate.mergeForUnPublished(coeArticle);
	}

	@Override
	public CoeArticle mergeArticleForLink(CoeArticle coeArticle) {
		return coeArticleDelegate.mergeArticleForLink(coeArticle);
	}

	@Override
	public CoeArticle mergeArticleForTitle(CoeArticle coeArticle) {
		String title = coeArticle.getTitle();
		if(StringUtils.isNotBlank(title)) {
			title = HtmlUtils.htmlEscape(title,UrlUtil.CHARSET_UTF_8);
			coeArticle.setTitle(title);
		}
		String name = coeArticle.getName();
		if(StringUtils.isBlank(name)) {
			coeArticle.setName(title);
		}else {
			coeArticle.setName(HtmlUtils.htmlEscape(name,UrlUtil.CHARSET_UTF_8));
		}
		
		String penname = coeArticle.getPenname();
		if(StringUtils.isNotBlank(penname)) {
			coeArticle.setPenname(HtmlUtils.htmlEscape(penname,UrlUtil.CHARSET_UTF_8));
		}
		
		String preIntro = coeArticle.getPreIntro();
		if(StringUtils.isNotBlank(preIntro)) {
			coeArticle.setPreIntro(HtmlUtils.htmlEscape(preIntro,UrlUtil.CHARSET_UTF_8));
		}
		return coeArticleDelegate.mergeArticleForTitle(coeArticle);
	}
	
	@Override
	public CoeArticle mergeArticleForTitleOnly(CoeArticle coeArticle) {
		//转义字符
		String title = coeArticle.getTitle();
		if(StringUtils.isNotBlank(title)) {
			title = HtmlUtils.htmlEscape(title,UrlUtil.CHARSET_UTF_8);
			coeArticle.setTitle(title);
		}
		String name = coeArticle.getName();
		if(StringUtils.isBlank(name)) {
			coeArticle.setName(title);
		}else {
			coeArticle.setName(HtmlUtils.htmlEscape(name,UrlUtil.CHARSET_UTF_8));
		}
		
		return coeArticleDelegate.mergeArticleForTitleOnly(coeArticle);
	}
	@Override
	public CoeArticle mergeArticleForDescriptionOnly(CoeArticle coeArticle) {
		String description = coeArticle.getDescription();
		if(StringUtils.isNotBlank(description)) {
			coeArticle.setDescription(HtmlUtils.htmlEscape(description,UrlUtil.CHARSET_UTF_8));
		}
		return coeArticleDelegate.mergeArticleForDescriptionOnly(coeArticle);
	}

	@Override
	public List<CoeArticle> findByPublished(CoeArticle coeArticle) {
		return coeArticleDelegate.findByPublished(coeArticle);
	}
	
	@Override
	public Page<CoeArticle> findByPublished(CoeArticle coeArticle, Pageable pageable, boolean withCategoryTree) {
		Page<CoeArticle> page = findByPublished(coeArticle,pageable,true,true, true, true,false,true);
		if(withCategoryTree) {
			Category categoryTree = findCategoryTree(this.mainId,this.subId);
			coeArticle.setCategoryTree(categoryTree);
		}
		return page;
	}
	
	@Override
	public Page<CoeArticle> findByPublished(CoeArticle coeArticle, Pageable pageable) {
		Page<CoeArticle> page = findByPublished(coeArticle,pageable,true);
		return page;
	}
	
	@Override
	public PageResult<CoeArticle> findPublishedForSelect(CoeArticle coeArticle, Pageable pageable) {
		PageResult<CoeArticle> page =(PageResult) findByPublished(coeArticle,pageable,true,true, true, true,false,false);
		return page;
	}
	
	public List<CoeArticle> findByIdList(List<Long> idList,boolean withTags, boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withPraise, boolean withAuthorPayCode){
		List<CoeArticle> content = findByIdList(idList);
		if(null != content && !content.isEmpty()) {
			fillRefBase( content, withTags, withCover, withBanner, withCategory, withPraise, withAuthorPayCode);
		}
		return content;
	}
	
	public Page<CoeArticle> findByPublished(CoeArticle coeArticle, Pageable pageable, 
			boolean withTags, boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withPraise, boolean withAuthorPayCode
			) {
		Page<CoeArticle> page = coeArticleDelegate.findByPublished(coeArticle, pageable);
		if (null != page && page.getTotalElements() > 0) {

			List<CoeArticle> content = page.getContent();
			fillRefBase(content, withTags, withCover, withBanner, withCategory, withPraise, withAuthorPayCode);
		}
		return page;
	}
	
	public List<CoeArticle> fillRefWithCoverAndBanner(List<CoeArticle> contentList) {
		 contentList = fillRefBase(contentList,false,true, true,false, false, false);
		return contentList;
	}
	
	public List<CoeArticle> fillRefWithCoverAndBannerAndAuthor(List<CoeArticle> contentList) {
		 contentList = fillRefBase(contentList,false,true, true,false, false, true);
		return contentList;
	}
	
	public List<CoeArticle> fillRefForList(List<CoeArticle> contentList) {
		 contentList = fillRefBase(contentList,true,true, true, true, true, true);
		return contentList;
	}
	
	public List<CoeArticle> fillRefBase(List<CoeArticle> content, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory,
			boolean withPraise, boolean withAuthorPayCode) {
		return fillRef( content,  withTags,  withCover,  withBanner,  withCategory,
				 withPraise,  withAuthorPayCode,false,null);
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
	public List<CoeArticle> fillRef(List<CoeArticle> content, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory,
			boolean withPraise, boolean withAuthorPayCode, boolean withRefPage, Pageable refPageable) {
		if (null != content && !content.isEmpty()) {
			List<Long> mainIdList = new ArrayList<>(content.size());
			List<Long> userIdList = new ArrayList<>(content.size());
			Map<Long, CoeArticle> cpMap = new HashMap<>(content.size());
			for (CoeArticle cp : content) {
				Long articleId = cp.getId();
				mainIdList.add(articleId);
				cpMap.put(articleId, cp);

				nicknameHandler.handleNickname(cp);
				
				Long userId = cp.getUserId();
				if (!userIdList.contains(userId)) {
					userIdList.add(userId);
				}
				
				//处理传说
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
							Long articleId = entry.getKey();
							CoeArticle cp = cpMap.get(articleId);
							cp.setCoverList(entry.getValue());
						}
					}
				}
				if (withBanner) {
					Map<Long, List<PhotoBanner>> bannerMap = photoBannerWrapper.findByMainIdListAndSubId(mainIdList, PhotoBanner.SUBID_BANNER_HEAD);
					if (null != bannerMap && !bannerMap.isEmpty()) {
						for (Map.Entry<Long, List<PhotoBanner>> entry : bannerMap.entrySet()) {
							Long articleId = entry.getKey();
							CoeArticle cp = cpMap.get(articleId);
							cp.setBannerList(entry.getValue());
						}
					}
				}
				if (withTags) {
					Map<Long, List<CoeArticleTag>> articleTagMap = coeArticleTagWrapper.findMapByMainIdList(mainIdList);
					if (null != articleTagMap && !articleTagMap.isEmpty()) {
						for (Map.Entry<Long, List<CoeArticleTag>> entry : articleTagMap.entrySet()) {
							Long articleId = entry.getKey();
							CoeArticle cp = cpMap.get(articleId);
							cp.setArticleTagList(entry.getValue());
						}
					}
				}
				if (withCategory) {
					Map<Long, List<CoeCategArticle>> categoryMap = coeCategArticleWrapper.findMapByMainIdList(mainIdList);
					if (null != categoryMap && !categoryMap.isEmpty()) {
						for (Map.Entry<Long, List<CoeCategArticle>> entry : categoryMap.entrySet()) {
							Long articleId = entry.getKey();
							CoeArticle cp = cpMap.get(articleId);
							cp.setCategoryList(entry.getValue());
						}
					}
				}

				if (withPraise) {
					// 最新4名点赞用户
					for (CoeArticle cp : content) {
						PageRequest pageable = new PageRequest(0, 4, Direction.DESC, "aid");
						Long id = cp.getId();
						Page<CoeArticlePraise> praisePage = coeArticlePraiseWrapper.findByMainId(id, pageable);
						cp.setPraiseList(praisePage.getContent());
						cp.setPraisePage(praisePage);
					}

				}

				if (withAuthorPayCode) {
					Map<Long, CoeUser> map = coeUserWrapper.findMapByUserIdList(userIdList, withAuthorPayCode);
					for (CoeArticle cp : content) {
						Long userId = cp.getUserId();
						CoeUser author = map.get(userId);
						nicknameHandler.handleNicknameForUser(author);
					}
				}
			}
		}
		return content;
	}
	
	@Override
	public List<CoeArticle> findPublishedByName(String name) {
		return coeArticleDelegate.findPublishedByName(name);
	}

	@Override
	public List<CoeArticle> findPublishedByTitle(String title) {
		return coeArticleDelegate.findPublishedByTitle(title);
	}
	
	public List<CoeArticle> findDraftList(CoeArticle coeArticle){
		return coeArticleDelegate.findDraftList(coeArticle);
	}
	
	public Long countDraftList(CoeArticle coeArticle) {
		return coeArticleDelegate.countDraftList(coeArticle);
	}

	//含tag信息
	@Override
	public CoeArticle findDetail(Long id) {
		return findById(id);
	}
	
	public Page<CoeArticle> findPublished(CoeArticle coeArticle, Pageable pageable) {
		String title = StringUtils.trim(coeArticle.getTitle());
		String name = StringUtils.trim(coeArticle.getName());
		if(StringUtils.isNotBlank(title)) {
			if(StringUtils.isBlank(name)) {
				coeArticle.setName(title);
			}
		}
		return coeArticleDelegate.findPublished(coeArticle,pageable);
	}

	public Page<CoeArticle> findPublishedWithCoverAndBanner(CoeArticle coeArticle, Pageable pageable) {
		String title = StringUtils.trim(coeArticle.getTitle());
		String name = StringUtils.trim(coeArticle.getName());
		if (StringUtils.isNotBlank(title)) {
			if (StringUtils.isBlank(name)) {
				coeArticle.setName(title);
			}
		}
		Page<CoeArticle> page = coeArticleDelegate.findPublished(coeArticle, pageable);
		List<CoeArticle> content = page.getContent();
		if(null != content && !content.isEmpty()) {
			fillRefWithCoverAndBanner(content);
		}
		return page;
	}

	@Override
	public CoeArticle mergeForRefresh(CoeArticle coeArticle) {
		return coeArticleDelegate.mergeForRefresh(coeArticle);
	}

	@Override
	public CoeArticle mergeForEditQickly(CoeArticle coeArticle) {
		return coeArticleDelegate.mergeForEditQickly(coeArticle);
	}

	@Override
	public CoeArticle mergeForPublish(CoeArticle coeArticle) {
		Long userId = coeArticle.getUserId();
		CoeUser coeUser = coeUserWrapper.findByUserId(userId);
		String headImg = coeUser.getHeadImg();
		//coeArticle.setSayword(coeUser.getSayword());
		Sayword sayword = saywordWrapper.findByUserIdAndMainId(userId);
		coeArticle.setSaywordId(sayword.getId());
		coeArticle.setHeadImg(headImg);
		coeArticle.setNickname(coeUser.getNickname());
		//coeArticle.getPenname();
		return coeArticleDelegate.mergeForPublish(coeArticle);
	}
	
	public CoeArticle mergeForClosed(CoeArticle coeArticle) {
		return coeArticleDelegate.mergeForClosed(coeArticle);
	}
	
	public CoeArticle mergeForDeleted(CoeArticle coeArticle) {
		coeArticle.setDeleted(true);
		return coeArticleDelegate.mergeForDeleted(coeArticle);
	}
	
	public CoeArticle mergeForPraise(CoeArticle coeArticle) {
		return coeArticleDelegate.mergeForPraise(coeArticle);
	}

	@Override
	public boolean publishedById(Long id) {
		boolean published = coeArticleDelegate.publishedById(id);
		return published;
	}

	@Override
	public CoeArticle persistForCover(CoeArticle coeArticle) {
		List<PhotoCover>  coverList = coeArticle.getCoverList();
		if(null != coverList && !coverList.isEmpty()) {
			coverList = photoCoverWrapper.persistForPhoto(coverList);
			coeArticle.setCoverList(coverList);
		}
		return coeArticle;
	}
	
	public CoeArticle persistForBanner(CoeArticle coeArticle) {
		List<PhotoBanner>  bannerList = coeArticle.getBannerList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = photoBannerWrapper.persistForPhoto(bannerList);
			coeArticle.setBannerList(bannerList);
		}
		return coeArticle;
	}
	
	@Override
	public CoeArticle findDetailLastRefreshByUserId(Long userId) {
		CoeArticle entity = findArticleLastRefreshByUserId(userId);
		if(null != entity) {
			fillRefBaseSingle(entity);
		}
		return entity;
	}
	
	public CoeArticle findArticleLastRefreshByUserId(Long userId) {
		Pageable pageable = new PageRequest(0,1,Direction.DESC,"refreshTime");
		Page<CoeArticle> page = findPageByUserId(userId, pageable);
		CoeArticle entity = null;
		if(null != page && page.getTotalElements()>0) {
			entity = page.getContent().get(0);
		}
		return entity;
	}
	
	public Page<CoeArticle> findPageByUserId(Long userId , Pageable pageable){
		CoeArticle coeArticle = new CoeArticle();
		coeArticle.setUserId(userId);
		return findPageByUserId( coeArticle ,  pageable);
	}
	
	public Page<CoeArticle> findPageByUserId(CoeArticle coeArticler , Pageable pageable){
		return coeArticleDelegate.findPageByUserId( coeArticler ,  pageable);
	}
	
	
	public Page<CoeArticle> findPageForInvitor(Long createdBy, Long userId){
		Page<CoeArticle> page = null;
		if(null != createdBy && userId != null) {
			if(userId.equals(createdBy)) {
				//没有邀请人
				//TODO 寻找位置绑定者，二期再做
			}
			Pageable pageable = new PageRequest(0,3,Direction.DESC, "refreshTime");
			CoeArticle coeArticle = new CoeArticle();
			coeArticle.setCreatedBy(createdBy);
			coeArticle.setUserId(createdBy);
			page = findPageForInvitor(coeArticle, pageable);
		}
		return page;
	}
	@Override
	public Page<CoeArticle> findPageForInvitor(CoeArticle coeArticle, Pageable pageable) {
		Page<CoeArticle> page = findPageByUserId(coeArticle,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<CoeArticle> content = page.getContent();
				fillRefBase(content, true, true, true, true, false, true);
		}
		return page;
	}
	
	public Page<CoeArticle> findPageForCurrentUser(Long userId){
		Page<CoeArticle> page = null;
		if( userId != null) {
			Pageable pageable = new PageRequest(0,3,Direction.DESC, "refreshTime");
			CoeArticle coeArticle = new CoeArticle();
			coeArticle.setUserId(userId);
			page = findPageForCurrentUser(coeArticle, pageable);
		}
		return page;
	}
	@Override
	public Page<CoeArticle> findPageForCurrentUser(CoeArticle coeArticle, Pageable pageable) {
		Page<CoeArticle> page = findPageByUserId(coeArticle,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<CoeArticle> content = page.getContent();
				fillRefBase(content, true, true, true, true, false, true);
		}
		return page;
	}
	
	public Page<CoeArticle> findPageTagForCurrentUser(Long userId){
		Page<CoeArticle> page = null;
		if( userId != null) {
			Pageable pageable = new PageRequest(0,2, Direction.DESC,"refreshTime");//最新的两篇文章
			CoeArticle coeArticle = new CoeArticle();
			coeArticle.setUserId(userId);
			page = findPageTagForCurrentUser(coeArticle, pageable);
		}
		return page;
	}
	public Page<CoeArticle> findPageTagForCurrentUser(CoeArticle coeArticle, Pageable pageable) {
		Page<CoeArticle> page = coeArticleDelegate.findPageByUserId(coeArticle,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<CoeArticle> content = page.getContent();
				fillRefBase(content, true, false, false, false, false, false);
		}
		return page;
	}
	
	@Autowired
	private PositionArticleAdaptor positionArticleAdaptor;
	//查询位置为技能推广里的技能信息
	public Page<CoeArticle> findPageForRecommend(Long userId, Pageable pageable){
		PositionExtend extend = positionArticleAdaptor.getConfigExtend();
		Long positionId = extend.getPositionId();
		Position.PANEL_PROFESSION_RECOMMEND.getValueInt();
		Position.SUBID_4_DATAREF.getValueInt();
		
		
		//positionCoeArticleAdaptor.findSingle(ref)
		
		return null;
	}
	
	public Page<CoeArticle> findPageForRandomUserId(Long userId){
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
			
			CoeArticle coeArticle = new CoeArticle();
			coeArticle.setUserId(userId);
			Page<CoeArticle> result = findPageForRandomUserId(coeArticle, pageable);
			return result;
		}
		return null;
	}
	
	
	public Page<CoeArticle> findByPublishedNotSelf(CoeArticle coeArticle, Pageable pageable){
		return coeArticleDelegate.findByPublishedNotSelf(coeArticle,pageable);
	}
	
	public Page<CoeArticle> findPageForRandomUserId(CoeArticle coeArticle, Pageable pageable){
		
		Page<CoeArticle> page = coeArticleDelegate.findByPublishedNotSelf(coeArticle,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<CoeArticle> content = page.getContent();
				fillRefBase(content, true, true, true, true, false, true);
		}
		return page;
	}
	
	public Long countForPublishedWithoutSelf(Long userId) {
		
		return coeArticleDelegate.countForPublishedWithoutSelf( userId);
	}

	@Override
	public Page<CoeArticle> findPageRefForUser(CoeArticle coeArticle, Pageable pageable) {
		Page<CoeArticle> page = coeArticleDelegate.findByPublished(coeArticle,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<CoeArticle> content = page.getContent();
				fillRefBase(content, true, true, true, false, false, true);
		}
		return page;
	}
	
	public Page<CoeArticle> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable){
		return coeArticleDelegate.findPageByMainTypeAndUserId(mainType, userIdList, publishStatus, pageable);
	}
	
	public Page<CoeArticle> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable){
		return coeArticleDelegate.findPageByMainTypeAndUserId( userIdList, pageable);
	}
	
	public List<CoeArticle> findListByMainTypeAndUserId(List<Long> userIdList){
		List<CoeArticle> content = coeArticleDelegate.findListByMainTypeAndUserId( userIdList);
		
		return content;
	}
	
	
	public CoeArticle findDetailFilledMainTypeList(Long id) {
		CoeArticle entity = findDetailWithMainTypeList(id);
		List<CoeArticle> mainTypeList = entity.getMainTypeList();
		//校验是否存在主技能，如不存在，则取最新5个刷新技能,后续改造成从该分类下获取刷新技能
		if(null == mainTypeList || mainTypeList.isEmpty()) {
			PageRequest refreshPageable = new PageRequest(0,5,Direction.DESC, "refreshTime");
			CoeArticle coeArticle = new CoeArticle();
			coeArticle.setId(id);
			coeArticle.setUserId(entity.getUserId());
			Page<CoeArticle> refreshPage = findByPublishedWithAuthorNotSelf(coeArticle, refreshPageable);
			if(null != refreshPage && refreshPage.getTotalElements()>0) {
				entity.setRefreshPage(refreshPage);
			}
		}
		return entity;
	}
	public CoeArticle findDetailFilledMainTypePage(Long id) {
		CoeArticle entity = findDetailWithMainTypeList(id);
		List<CoeArticle> mainTypeList = entity.getMainTypeList();
		//校验是否存在主技能，如不存在，则取最新5个刷新技能,后续改造成从该分类下获取刷新技能
		if(null == mainTypeList) {
			PageRequest refreshPageable = new PageRequest(0,5,Direction.DESC, "refreshTime");
			CoeArticle coeArticle = new CoeArticle();
			coeArticle.setId(id);
			coeArticle.setUserId(entity.getUserId());
			Page<CoeArticle> refreshPage = findByPublishedWithAuthorNotSelf(coeArticle, refreshPageable);
			if(null != refreshPage && refreshPage.getTotalElements()>0) {
				entity.setRefreshPage(refreshPage);
			}
		}
		return entity;
	}
	
	
	public CoeArticle findDetailWithMainTypeList(Long id) {
		CoeArticle entity = findDetail(id);
		entity = fillMainTypeList(entity);
		return entity;
	}
	
	/**
	 * TODO
	 * @param id
	 * @return
	 */
	public CoeArticle findWithPaisePage(Long id) {
		
		CoeArticle entity = findDetail(id);
		entity = fillMainTypeList(entity);
		return entity;
	}
	
	public CoeArticle fillMainTypeList(CoeArticle entity) {
		List<CoeArticlePraise> praiseList = entity.getPraiseList();
		Map<Long , CoeArticlePraise> praiseMap = new HashMap<>();
		if(null != praiseList && !praiseList.isEmpty()) {
			List<Long> praiseUserIdList = new ArrayList<>(praiseList.size());
			
			int j=0;
			int i=praiseList.size()-1;
			List<CoeArticle> mainTypeList =  null;
			for(;i>=0; i--) {
				CoeArticlePraise p = praiseList.get(i);
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
				for(CoeArticle mainType : mainTypeList) {
					/*Long userId = mainType.getUserId();
					CoeArticlePraise praise = praiseMap.get(userId);
					CoeUser author = new CoeUser();
					author.setUserId(praise.getUserId());
					author.setHeadImg(praise.getHeadImg());
					author.setNickname(praise.getNickname());
					author.setSayword(praise.getSayword());
					author.setRefreshTime(praise.getRefreshTime());
					mainType.setAuthor(author);*/
					
					nicknameHandler.handleNickname(mainType);
					/*String nickname = mainType.getNickname();
					if(StringUtils.isNotBlank(nickname)) {
						mainType.setNickname(Base64Util.decode(nickname));
					}else {
						mainType.setNickname("卓无名");
					}
					String headImg = mainType.getHeadImg();
					if(StringUtils.isBlank(headImg)) {
						mainType.setHeadImg(mainCircleConfig.getRandomHeadImageUrl());
					}*/
					
				}
				entity.setMainTypeList(mainTypeList);
			}
		}
		return entity;
	}
	
	public Page<CoeArticle> findByPublishedWithAuthor(CoeArticle coeArticle, Pageable pageable){
		Page<CoeArticle> page = findByPublished(coeArticle,pageable,false,false, false, false,false,true);
		return page;
	}
	public Page<CoeArticle> findByPublishedWithAuthorNotSelf(CoeArticle coeArticle, Pageable pageable){
		Page<CoeArticle> page = findByPublishedNotSelf(coeArticle,pageable);
		if (null != page && page.getTotalElements() > 0) {
			List<CoeArticle> content = page.getContent();
			fillRefBase(content, false, false, false, false, false, true);
		}
		return page;
	}
	
	@Override
	public List<CoeArticle> fillRef(List<CoeArticle> contentList, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory,
			boolean withPraise, boolean withAuthorPayCode) {
		contentList = fillRefBase(contentList, withTags, withCover, withBanner, withCategory, withPraise, withAuthorPayCode);
		return contentList;
	}
	
	
	@Override
	public CoeArticle mergeForMainType(CoeArticle coeArticle) {
		return coeArticleDelegate.mergeForMainType(coeArticle);
	}
	
	/**
	 * 查询技能点赞列表
	 * @param id
	 * @param pageable 点赞用户查询分页
	 * @return
	 */
	public CoeArticle findCoeArticleForPraisePage(Long id, Pageable pageable) {
		CoeArticle entity = findByIdOnly(id);
		if (null != entity) {
			Page<CoeArticlePraise> praisePage = coeArticlePraiseWrapper.findByMainId(id, pageable);
			entity.setPraisePage(praisePage);
			List<CoeArticlePraise> contentList = praisePage.getContent();
			if (null != contentList && !contentList.isEmpty()) {
				List<Long> praiseUserIdList = new ArrayList<>(contentList.size());
				Map<Long, CoeArticlePraise> map = new HashMap<>();
				for (CoeArticlePraise content : contentList) {
					Long userId = content.getUserId();
					praiseUserIdList.add(userId);
					map.put(userId, content);
				}
				List<CoeArticle> coeArticleList = findListByMainTypeAndUserId(praiseUserIdList);
				if (null != coeArticleList && !coeArticleList.isEmpty()) {
					//填充tag, cover, banner
					fillRefBase(coeArticleList,true,true,true,false,false,false);
					for (CoeArticle p : coeArticleList) {
						Long userId = p.getUserId();
						CoeArticlePraise praise = map.get(userId);
						praise.setArticle(p);
					}
				}
			}
		}
		return entity;
	}
	
	@Override
	public Page<CoeArticle> findPageForRecommend(CoeArticle coeArticle, Pageable pageable) {
		if(null == coeArticle) {
			coeArticle = new CoeArticle();
			coeArticle.setRecommend(CoeArticle.RECOMMEND_YES.getValueInt());
		}
		Page<CoeArticle> page = findByPublished(coeArticle, pageable);
		return page;
	}
	
	public CoeArticle findMainTypeByUserId(Long userId) {
		return coeArticleDelegate.findMainTypeByUserId(userId);
	}
	
	@Override
	public Long countPublishedByUserId(CoeArticle coeArticle) {
		if(null != coeArticle) {
			coeArticle.setPublishStatus(CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt());
			return coeArticleDelegate.countPublishedByUserId(coeArticle);
		}
		return 0l;
	}
	
	public Long countPublishedByUserId(Long userId) {
		CoeArticle coeArticle = new CoeArticle();
		coeArticle.setUserId(userId);
		return countPublishedByUserId(coeArticle);
	}
	
	
}
