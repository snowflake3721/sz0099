package dml.sz0099.course.app.client.wrapper.profession;

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
import org.jit8j.core.util.Base64Util;
import org.jit8j.core.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import dml.sz0099.course.app.biz.delegate.profession.ProfessionDelegate;
import dml.sz0099.course.app.client.resolver.adaptor.category.CategoryProfessionAdaptor;
import dml.sz0099.course.app.client.resolver.adaptor.position.PositionProfessionAdaptor;
import dml.sz0099.course.app.client.resolver.adaptor.profession.ProfessionArticleAdaptor;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.client.wrapper.user.SaywordWrapper;
import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.data.handler.util.NicknameHandler;
import dml.sz0099.course.app.data.show.blooming.config.MainCircleConfig;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.entity.profession.PhotoBanner;
import dml.sz0099.course.app.persist.entity.profession.PhotoCover;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPraise;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;
import dml.sz0099.course.app.persist.entity.profession.ProfessionTag;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionWrapperImpl,组件封装
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
public class ProfessionWrapperImpl implements ProfessionWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ProfessionDelegate professionDelegate;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private ProfessionTagWrapper professionTagWrapper;
	
	@Autowired
	private PhotoCoverWrapper photoCoverWrapper;
	
	@Autowired
	private PhotoBannerWrapper photoBannerWrapper;
	
	@Autowired
	private CategProfessionWrapper categProfessionWrapper;
	
	@Autowired
	private CategoryProfessionAdaptor categoryProfessionAdaptor;
	
	@Autowired
	private ProfessionArticleAdaptor professionArticleAdaptor;
	
	@Resource(name="mainCircleConfigProfession")
	private MainCircleConfig mainCircleConfig;
	
	@Autowired
	private ProfessionRefWrapper professionRefWrapper;
	
	@Autowired
	private ProfessionPraiseWrapper professionPraiseWrapper;
	
	@Autowired
	private NicknameHandler	nicknameHandler;
	
	@Autowired
	private SaywordWrapper saywordWrapper;
	
	private Long mainId = Robot.ROBOT_PLAT.getId();
	
	private Long subId = Robot.ROBOT_PLAT.getId();
	
	/**
	 * 根据Id查询Profession实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Profession findById(Long id) {
		Profession profession = findById(id,true,true,true, true, true, true,true, true);
		return profession;
	}
	
	public Profession findByIdOnly(Long id) {
		Profession profession = findById(id,false,false, false,false, false, false,false,false);
		return profession;
	}
	
	public Profession findByIdWithCoverAndBanner(Long id) {
		Profession profession = findById(id,false,true, true,false, false, false,false,false);
		return profession;
	}
	
	public Profession findById(Long id, boolean withTags, boolean withCover, boolean withBanner,
			boolean withCategory, boolean withCategoryTree, boolean withPraise,
			boolean withAuthorPayCode, boolean withSayword) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} , withTags: {} ,withCover:{} , withBanner:{}", id, withTags,withCover,withBanner);
		Profession profession = professionDelegate.findById(id);
		
		if(null != profession) {
			String nickname = profession.getNickname();
			if(StringUtils.isNotBlank(nickname)) {
				profession.setNickname(Base64Util.decode(nickname));
			}else {
				profession.setNickname("玛无名");
			}
			
			if(withTags) {
			List<ProfessionTag> productTagList = professionTagWrapper.findByMainId(id);
			profession.setProTagList(productTagList);
			}
			if(withCover) {
				List<Long> subIdList = new ArrayList<>(1);
				subIdList.add(PhotoCover.SUBID_COVER_HEAD);
				List<PhotoCover>  coverList = photoCoverWrapper.findBySubIdListAndMainId(subIdList, id);
				profession.setCoverList(coverList);
			}
			
			if(withBanner) {
				List<Long> subIdList = new ArrayList<>(1);
				subIdList.add(PhotoBanner.SUBID_BANNER_HEAD);
				List<PhotoBanner>  bannerList = photoBannerWrapper.findBySubIdListAndMainId(subIdList, id);
				profession.setBannerList(bannerList);
			}
			
			if(withCategory) {
				//获取分类树 TODO
				List<CategProfession> categoryList = categProfessionWrapper.findByMainId(id);
				if(null == categoryList) {
					categoryList = new ArrayList<>(1);
				}
				if(categoryList.isEmpty()) {
					CategProfession prod = new CategProfession();
					prod.setMainId(id);
					categoryList.add(prod);
					
				}
				profession.setCategoryList(categoryList);
				if(withCategoryTree) {
					Category categoryTree = categoryProfessionAdaptor.queryTree(mainId, subId);
					profession.setCategoryTree(categoryTree);
				}
			}
			if(withPraise) {
				//最新刷新点赞的 30名点赞用户
				PageRequest pageable = new PageRequest(0, 30, Direction.DESC, "refreshTime");
				Page<ProfessionPraise> praisePage = professionPraiseWrapper.findByMainId(id, pageable);
				profession.setPraiseList(praisePage.getContent());
				profession.setPraisePage(praisePage);
			}
			
			if(withAuthorPayCode) {
				Long userId = profession.getUserId();
				CoeUser author = coeUserWrapper.findByUserId(userId,withAuthorPayCode,false,withSayword);
				profession.setAuthor(author);
				nicknameHandler.handleNicknameForUser(author);
				/*author.setNickname(Base64Util.decode(author.getNickname()));
				String headImg = author.getHeadImg();
				if(StringUtils.isBlank(headImg)) {
					author.setHeadImg(mainCircleConfig.getRandomHeadImageUrl());
				}*/
			}
			
			Sayword sayword=profession.getSayword();
			if(null != sayword) {
				String descr=sayword.getDescription();
				sayword.setDescription(HtmlUtil.textarea2UnEscapeForHtml(descr));
			}
		}
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, profession);
		return profession;
	}
	
	@Override
	public Profession findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		Profession profession = professionDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, profession);
		return profession;
	}
	
	/**
	 * 根据IdList查询Profession实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Profession> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<Profession> professionList = professionDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  professionList);
		return professionList;
	}
	
	@Override
	public Profession persistEntity(Profession profession) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		Profession entity = professionDelegate.persistEntity(profession);
		Long id = profession.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Profession mergeEntity(Profession profession) {
		Long id = profession.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		Profession entity = professionDelegate.mergeEntity(profession);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Profession saveOrUpdate(Profession profession) {
		Long id = profession.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		Profession entity = professionDelegate.saveOrUpdate(profession);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Profession> findPage(Profession profession, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<Profession> page = professionDelegate.findPage(profession, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return professionDelegate.existById(id);
	}
	
	public List<Profession> findByIdList(List<Long> idList,boolean withTags, boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withPraise, boolean withAuthorPayCode){
		List<Profession> content = findByIdList(idList);
		if(null != content && !content.isEmpty()) {
			fillRefBase( content, withTags, withCover, withBanner, withCategory, withPraise, withAuthorPayCode);
		}
		return content;
	}
	
	public Page<Profession> findPublished(Profession profession, Pageable pageable) {
		String title = StringUtils.trim(profession.getTitle());
		String name = StringUtils.trim(profession.getName());
		if(StringUtils.isNotBlank(title)) {
			if(StringUtils.isBlank(name)) {
				profession.setName(title);
			}
		}
		return professionDelegate.findPublished(profession,pageable);
	}
	
	public Page<Profession> findByPublishedWithAuthor(Profession profession, Pageable pageable){
		Page<Profession> page = findByPublished(profession,pageable,false,false, false, false,false,true);
		return page;
	}
	public Page<Profession> findByPublishedWithAuthorNotSelf(Profession profession, Pageable pageable){
		Page<Profession> page = findByPublishedNotSelf(profession,pageable);
		if (null != page && page.getTotalElements() > 0) {
			List<Profession> content = page.getContent();
			fillRefBase(content, false, false, false, false, false, true);
		}
		return page;
	}
	@Override
	public Page<Profession> findByPublished(Profession profession, Pageable pageable) {
		Page<Profession> page = findByPublished(profession,pageable,true,true, false, true,false,true);
		return page;
	}
	
	@Override
	public PageResult<Profession> findPublishedForSelect(Profession profession, Pageable pageable) {
		PageResult<Profession> page =(PageResult) findByPublished(profession,pageable,true,true, true, true,false,false);
		return page;
	}
	
	public Page<Profession> findByPublished(Profession profession, Pageable pageable, 
			boolean withTags, boolean withCover, boolean withBanner, 
			boolean withCategory, boolean withPraise, boolean withAuthorPayCode
			) {
		Page<Profession> page = professionDelegate.findByPublished(profession,pageable);
		if(null != page && page.getTotalElements()>0) {
			
				List<Profession> content = page.getContent();
				fillRefBase(content, withTags, withCover, withBanner, withCategory, withPraise, withAuthorPayCode);
		}
		return page;
	}
	
	public List<Profession> fillRefWithCoverAndBannerAndAuthor(List<Profession> contentList){
		return fillRefBase(contentList,false,true, true, false, false, true);
	}
	public List<Profession> fillRefWithCoverAndBanner(List<Profession> contentList){
		return fillRefBase(contentList,false,true, true, false, false, false);
	}
	
	public List<Profession> fillRefWithCoverAndBanner(List<Profession> contentList, boolean withCover, boolean withBanner){
		return fillRefBase(contentList,false,withCover, withBanner, false, false, false);
	}
	
	public List<Profession> fillRefForList(List<Profession> contentList) {
		 contentList = fillRefBase(contentList,true,true, true, true, true, true);
		return contentList;
	}

	public List<Profession> fillRefBase(
			List<Profession> content, boolean withTags, boolean withCover, boolean withBanner, 
			boolean withCategory,
			boolean withPraise, boolean withAuthorPayCode) {
		return fillRef(content,  withTags,  withCover,  withBanner,  withCategory,
				 withPraise,  withAuthorPayCode, false, null);
	}
	/**
	 * @param content
	 * @param withTags
	 * @param withCover
	 * @param withBanner
	 * @param withCategory
	 * @param withPraise
	 * @param withAuthorPayCode
	 */
	public List<Profession> fillRef(List<Profession> content, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory,
			boolean withPraise, boolean withAuthorPayCode, boolean withRefPage, Pageable refPageable) {
		List<Long> mainIdList = new ArrayList<>(content.size());
		List<Long> userIdList = new ArrayList<>(content.size());
		Map<Long, Profession> cpMap = new HashMap<>(content.size());
		for (Profession cp : content) {
			Long professionId = cp.getId();
			mainIdList.add(professionId);
			cpMap.put(professionId, cp);
			
			nicknameHandler.handleNickname(cp);
			/*String nickname = cp.getNickname();
			if(StringUtils.isNotBlank(nickname)) {
				cp.setNickname(Base64Util.decode(nickname));
			}else {
				cp.setNickname("无名大侠");
			}
			
			String headImg = cp.getHeadImg();
			if(StringUtils.isBlank(headImg)) {
				cp.setHeadImg(mainCircleConfig.getRandomHeadImageUrl());
			}*/
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
						Long professionId = entry.getKey();
						Profession cp = cpMap.get(professionId);
						cp.setCoverList(entry.getValue());
					}
				}
			}
			if (withBanner) {
				Map<Long, List<PhotoBanner>> bannerMap = photoBannerWrapper.findByMainIdListAndSubId(mainIdList, PhotoBanner.SUBID_BANNER_HEAD);
				if (null != bannerMap && !bannerMap.isEmpty()) {
					for (Map.Entry<Long, List<PhotoBanner>> entry : bannerMap.entrySet()) {
						Long professionId = entry.getKey();
						Profession cp = cpMap.get(professionId);
						cp.setBannerList(entry.getValue());
					}
				}
			}
			if (withTags) {
				Map<Long, List<ProfessionTag>> professionTagMap = professionTagWrapper.findMapByMainIdList(mainIdList);
				if (null != professionTagMap && !professionTagMap.isEmpty()) {
					for (Map.Entry<Long, List<ProfessionTag>> entry : professionTagMap.entrySet()) {
						Long professionId = entry.getKey();
						Profession cp = cpMap.get(professionId);
						cp.setProTagList(entry.getValue());
					}
				}
			}
			if (withCategory) {
				Map<Long, List<CategProfession>> categoryMap = categProfessionWrapper.findMapByMainIdList(mainIdList);
				if (null != categoryMap && !categoryMap.isEmpty()) {
					for (Map.Entry<Long, List<CategProfession>> entry : categoryMap.entrySet()) {
						Long professionId = entry.getKey();
						Profession cp = cpMap.get(professionId);
						cp.setCategoryList(entry.getValue());
					}
				}
			}

			if (withPraise) {
				// 最新4名点赞用户
				for (Profession cp : content) {
					PageRequest pageable = new PageRequest(0, 4, Direction.DESC, "aid");
					Long id = cp.getId();
					Page<ProfessionPraise> praisePage = professionPraiseWrapper.findByMainId(id, pageable);
					cp.setPraiseList(praisePage.getContent());
					cp.setPraisePage(praisePage);
				}
				
			}

			if (withAuthorPayCode) {
				Map<Long, CoeUser> map = coeUserWrapper.findMapByUserIdList(userIdList, withAuthorPayCode);
				for (Profession cp : content) {
					Long userId = cp.getUserId();
					CoeUser author = map.get(userId);
					cp.setAuthor(author);
					nicknameHandler.handleNicknameForUser(author);
				}
			}
			
			if(withRefPage && null != refPageable) {
				for (Profession cp : content) {
					if(cp != null) {
						Long baseId = cp.getId();
						Pageable pageable=new PageRequest(refPageable.getPageNumber(), refPageable.getPageSize());
						Page<ProfessionRef> refPage = professionRefWrapper.findPageByBaseId(baseId, pageable, true, false);
						cp.setRefPage(refPage);
					}
				}
			}
		}
		return content;
	}
	
	

	@Override
	public Profession mergeForRefresh(Profession profession) {
		return professionDelegate.mergeForRefresh(profession);
	}

	@Override
	public Profession mergeForPublish(Profession profession) {
		Long userId = profession.getUserId();
		CoeUser coeUser = coeUserWrapper.findByUserId(userId);
		String headImg = coeUser.getHeadImg();
		//profession.setSayword(coeUser.getSayword());
		
		Sayword sayword = saywordWrapper.findByUserIdAndMainId(userId);
		profession.setSaywordId(sayword.getId());
		profession.setHeadImg(headImg);
		profession.setNickname(coeUser.getNickname());
		return professionDelegate.mergeForPublish(profession);
	}
	
	public Profession mergeForClosed(Profession profession) {
		return professionDelegate.mergeForClosed(profession);
	}
	
	public Profession mergeForDeleted(Profession profession) {
		profession.setDeleted(true);
		return professionDelegate.mergeForDeleted(profession);
	}
	
	public Profession createDraft(Profession profession) {

		Long userId = profession.getUserId();

		CoeUser user = coeUserWrapper.findByUserId(userId);
		if (null != user) {
			profession.setCoeUserId(user.getId());
		}

		ProfessionExtend extend = professionArticleAdaptor.getConfigDefault(userId);
		if (null != extend) {
			profession.setExtendId(extend.getId());
			profession.setPositionId(extend.getPositionId());
			//profession.setMainId(this.mainId);
			//profession.setSubId(this.subId);
			
			/*profession.setVerifyType(Profession.VERIFY_TYPE_NO.getValueInt());
			profession.setVerifyStatus(Profession.VERIFY_STATUS_NO.getValueInt());
			profession.setVerifyPre(Profession.VERIFY_TYPE_NO.getLabel());
			profession.setVerifyLevel(0);//认证级别，1-5星
*/
			Profession entity = professionDelegate.createDraft(profession);
			profession.setProfessionNo(entity.getProfessionNo());
			profession.setOriginalLink(entity.getOriginalLink());
			profession.setAid(entity.getAid());
			profession.setId(entity.getId());

			return entity;
		}
		return profession;
	}
	
	public List<Profession> findDraftList(Profession profession){
		return professionDelegate.findDraftList(profession);
	}
	
	public Long countDraftList(Profession profession) {
		return professionDelegate.countDraftList(profession);
	}
	
	public List<Profession> findByUserId(Long userId) {
		return professionDelegate.findByUserId( userId) ;
	}
	
	public Long countByUserId(Long userId) {
		return professionDelegate.countByUserId(userId);
	}
	
	
	@Override
	public Profession mergeForBaseinfo(Profession profession) {
		//转义字符
		String title = profession.getTitle();
		if(StringUtils.isNotBlank(title)) {
			title = HtmlUtils.htmlEscape(title,UrlUtil.CHARSET_UTF_8);
			profession.setTitle(title);
			
		}
		String name = profession.getName();
		if(StringUtils.isBlank(name)) {
			profession.setName(title);
		}else {
			profession.setName(HtmlUtils.htmlEscape(name,UrlUtil.CHARSET_UTF_8));
		}
		
		String description = profession.getDescription();
		if(StringUtils.isNotBlank(description)) {
			profession.setDescription(HtmlUtils.htmlEscape(description,UrlUtil.CHARSET_UTF_8));
		}
		
		return professionDelegate.mergeForBaseinfo(profession);
	}
	
	@Override
	public Profession mergeProfessionForTitle(Profession profession) {
		return professionDelegate.mergeProfessionForTitle(profession);
	}
	
	@Override
	public Profession mergeArticleForTitleOnly(Profession profession) {
		//转义字符
		String title = profession.getTitle();
		if(StringUtils.isNotBlank(title)) {
			title = HtmlUtils.htmlEscape(title,UrlUtil.CHARSET_UTF_8);
			profession.setTitle(title);
		}
		String name = profession.getName();
		if(StringUtils.isBlank(name)) {
			profession.setName(title);
		}else {
			profession.setName(HtmlUtils.htmlEscape(name,UrlUtil.CHARSET_UTF_8));
		}
		
		return professionDelegate.mergeArticleForTitleOnly(profession);
	}
	@Override
	public Profession mergeArticleForDescriptionOnly(Profession profession) {
		String description = profession.getDescription();
		if(StringUtils.isNotBlank(description)) {
			profession.setDescription(HtmlUtils.htmlEscape(description,UrlUtil.CHARSET_UTF_8));
		}
		return professionDelegate.mergeArticleForDescriptionOnly(profession);
	}
	
	@Override
	public Profession persistForCover(Profession profession) {
		List<PhotoCover>  coverList = profession.getCoverList();
		if(null != coverList && !coverList.isEmpty()) {
			coverList = photoCoverWrapper.persistForPhoto(coverList);
			profession.setCoverList(coverList);
		}
		return profession;
	}
	
	public Profession persistForBanner(Profession profession) {
		List<PhotoBanner>  bannerList = profession.getBannerList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = photoBannerWrapper.persistForPhoto(bannerList);
			profession.setBannerList(bannerList);
		}
		return profession;
	}
	
	@Override
	public Profession mergeForEditQickly(Profession profession) {
		return professionDelegate.mergeForEditQickly(profession);
	}
	
	@Override
	public Profession mergeForUnPublished(Profession profession) {
		return professionDelegate.mergeForUnPublished(profession);
	}
	
	@Override
	public Profession mergeForMainType(Profession profession) {
		return professionDelegate.mergeForMainType(profession);
	}

	@Override
	public Profession findDetail(Long id) {
		return findById(id);
	}
	
	public Profession findDetailFilledMainTypeList(Long id) {
		Profession entity = findDetailWithMainTypeList(id);
		List<Profession> mainTypeList = entity.getMainTypeList();
		//校验是否存在主技能，如不存在，则取最新5个刷新技能,后续改造成从该分类下获取刷新技能
		if(null == mainTypeList || mainTypeList.isEmpty()) {
			PageRequest refreshPageable = new PageRequest(0,5,Direction.DESC, "refreshTime");
			Profession profession = new Profession();
			profession.setId(id);
			profession.setUserId(entity.getUserId());
			Page<Profession> refreshPage = findByPublishedWithAuthorNotSelf(profession, refreshPageable);
			if(null != refreshPage && refreshPage.getTotalElements()>0) {
				entity.setRefreshPage(refreshPage);
			}
		}
		return entity;
	}
	public Profession findDetailFilledMainTypePage(Long id) {
		Profession entity = findDetailWithMainTypeList(id);
		List<Profession> mainTypeList = entity.getMainTypeList();
		//校验是否存在主技能，如不存在，则取最新5个刷新技能,后续改造成从该分类下获取刷新技能
		if(null == mainTypeList || mainTypeList.isEmpty()) {
			PageRequest refreshPageable = new PageRequest(0,5,Direction.DESC, "refreshTime");
			Profession profession = new Profession();
			profession.setId(id);
			profession.setUserId(entity.getUserId());
			Page<Profession> refreshPage = findByPublishedWithAuthorNotSelf(profession, refreshPageable);
			if(null != refreshPage && refreshPage.getTotalElements()>0) {
				entity.setRefreshPage(refreshPage);
			}
		}
		return entity;
	}
	
	
	public Profession findDetailWithMainTypeList(Long id) {
		Profession entity = findDetail(id);
		entity = fillMainTypeList(entity);
		return entity;
	}
	
	/**
	 * TODO
	 * @param id
	 * @return
	 */
	public Profession findWithPaisePage(Long id) {
		
		Profession entity = findDetail(id);
		entity = fillMainTypeList(entity);
		return entity;
	}
	
	public Profession fillMainTypeList(Profession entity) {
		List<ProfessionPraise> praiseList = entity.getPraiseList();
		Map<Long , ProfessionPraise> praiseMap = new HashMap<>();
		if(null != praiseList && !praiseList.isEmpty()) {
			List<Long> praiseUserIdList = new ArrayList<>(praiseList.size());
			
			int j=0;
			int i=praiseList.size()-1;
			List<Profession> mainTypeList =  null;
			for(;i>=0; i--) {
				ProfessionPraise p = praiseList.get(i);
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
				for(Profession mainType : mainTypeList) {
					/*Long userId = mainType.getUserId();
					ProfessionPraise praise = praiseMap.get(userId);
					CoeUser author = new CoeUser();
					author.setUserId(praise.getUserId());
					author.setHeadImg(praise.getHeadImg());
					author.setNickname(praise.getNickname());
					author.setSayword(praise.getSayword());
					author.setRefreshTime(praise.getRefreshTime());
					mainType.setAuthor(author);*/
					nicknameHandler.handleNickname(mainType);
					/*String nickname=mainType.getNickname();
					if(StringUtils.isNotBlank(nickname)) {
						mainType.setNickname(Base64Util.decode(nickname));
					}else {
						mainType.setNickname("玛无名");
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
	
	/**
	 * 查询技能点赞列表
	 * @param id
	 * @param pageable 点赞用户查询分页
	 * @return
	 */
	public Profession findProfessionForPraisePage(Long id, Pageable pageable) {
		Profession entity = findByIdOnly(id);
		if (null != entity) {
			Page<ProfessionPraise> praisePage = professionPraiseWrapper.findByMainId(id, pageable);
			entity.setPraisePage(praisePage);
			List<ProfessionPraise> contentList = praisePage.getContent();
			if (null != contentList && !contentList.isEmpty()) {
				List<Long> praiseUserIdList = new ArrayList<>(contentList.size());
				Map<Long, ProfessionPraise> map = new HashMap<>();
				for (ProfessionPraise content : contentList) {
					Long userId = content.getUserId();
					praiseUserIdList.add(userId);
					map.put(userId, content);
				}
				List<Profession> professionList = findListByMainTypeAndUserId(praiseUserIdList);
				if (null != professionList && !professionList.isEmpty()) {
					//填充tag, cover, banner
					fillRefBase(professionList,true,true,true,false,false,false);
					for (Profession p : professionList) {
						Long userId = p.getUserId();
						ProfessionPraise praise = map.get(userId);
						praise.setProfession(p);
					}
				}
			}
		}
		return entity;
	}
	
	public Category findCategoryTree(Long mainId, Long subId) {
		if(mainId==null) {
			mainId = this.mainId;
		}
		if(subId==null) {
			subId = this.subId;
		}
		Category categoryTree = categoryProfessionAdaptor.queryTree(mainId, subId);
		return categoryTree;
	}

	public Page<Profession> findPageForInvitor(Long createdBy, Long userId){
		Page<Profession> page = null;
		if(null != createdBy && userId != null) {
			if(userId.equals(createdBy)) {
				//没有邀请人
				//TODO 寻找位置绑定者，二期再做
			}
			Pageable pageable = new PageRequest(0,3);
			Profession profession = new Profession();
			profession.setCreatedBy(createdBy);
			profession.setUserId(createdBy);
			page = findPageForInvitor(profession, pageable);
		}
		return page;
	}
	@Override
	public Page<Profession> findPageForInvitor(Profession profession, Pageable pageable) {
		Page<Profession> page = professionDelegate.findByPublished(profession,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<Profession> content = page.getContent();
				fillRefBase(content, true, true, true, true, false, true);
		}
		return page;
	}
	
	public Page<Profession> findPageForCurrentUser(Long userId){
		Page<Profession> page = null;
		if( userId != null) {
			Pageable pageable = new PageRequest(0,3);
			Profession profession = new Profession();
			profession.setUserId(userId);
			page = findPageForCurrentUser(profession, pageable);
		}
		return page;
	}
	@Override
	public Page<Profession> findPageForCurrentUser(Profession profession, Pageable pageable) {
		Page<Profession> page = professionDelegate.findByPublished(profession,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<Profession> content = page.getContent();
				fillRefBase(content, true, true, true, true, false, true);
		}
		return page;
	}
	
	public Page<Profession> findPageTagForCurrentUser(Long userId){
		Page<Profession> page = null;
		if( userId != null) {
			Pageable pageable = new PageRequest(0,3);
			Profession profession = new Profession();
			profession.setUserId(userId);
			page = findPageTagForCurrentUser(profession, pageable);
		}
		return page;
	}
	public Page<Profession> findPageTagForCurrentUser(Profession profession, Pageable pageable) {
		Page<Profession> page = professionDelegate.findByPublished(profession,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<Profession> content = page.getContent();
				fillRefBase(content, true, false, false, false, false, false);
		}
		return page;
	}
	
	@Autowired
	private PositionProfessionAdaptor positionProfessionAdaptor;
	//查询位置为技能推广里的技能信息
	public Page<Profession> findPageForRecommend(Long userId, Pageable pageable){
		PositionExtend extend = positionProfessionAdaptor.getConfigExtend();
		Long positionId = extend.getPositionId();
		Position.PANEL_PROFESSION_RECOMMEND.getValueInt();
		Position.SUBID_4_DATAREF.getValueInt();
		
		
		//positionProfessionAdaptor.findSingle(ref)
		
		return null;
	}
	
	public Page<Profession> findPageForRandomUserId(Long userId){
		int size=1;
		ProfessionExtend extend = professionArticleAdaptor.getConfigDefault(userId);
		Long positionId = extend.getPositionId();
		Long count = countForPublishedWithoutSelf( userId, positionId);
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
			
			Profession profession = new Profession();
			profession.setUserId(userId);
			Page<Profession> result = findPageForRandomUserId(profession, pageable);
			return result;
		}
		return null;
	}
	
	
	public Page<Profession> findByPublishedNotSelf(Profession profession, Pageable pageable){
		return professionDelegate.findByPublishedNotSelf(profession,pageable);
	}
	
	public Page<Profession> findPageForRandomUserId(Profession profession, Pageable pageable){
		
		Page<Profession> page = professionDelegate.findByPublishedNotSelf(profession,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<Profession> content = page.getContent();
				fillRefBase(content, true, true, true, true, false, true);
		}
		return page;
	}
	
	public Long countForPublishedWithoutSelf(Long userId, Long positionId) {
		
		return professionDelegate.countForPublishedWithoutSelf( userId, positionId);
	}

	@Override
	public Page<Profession> findPageRefForUser(Profession profession, Pageable pageable) {
		Page<Profession> page = professionDelegate.findByPublished(profession,pageable);
		if(null != page && page.getTotalElements()>0) {
				List<Profession> content = page.getContent();
				fillRef(content, true, true, true, false, false, true, true, pageable);
		}
		return page;
	}
	
	public Page<Profession> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable){
		return professionDelegate.findPageByMainTypeAndUserId(mainType, userIdList, publishStatus, pageable);
	}
	
	public Page<Profession> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable){
		return professionDelegate.findPageByMainTypeAndUserId( userIdList, pageable);
	}
	
	public List<Profession> findListByMainTypeAndUserId(List<Long> userIdList){
		List<Profession> content = professionDelegate.findListByMainTypeAndUserId( userIdList);
		
		return content;
	}
	
	@Override
	public Page<Profession> findPageForRecommend(Profession profession, Pageable pageable) {
		if(null == profession) {
			profession = new Profession();
			profession.setRecommend(Profession.RECOMMEND_YES.getValueInt());
		}
		Page<Profession> page = findByPublished(profession, pageable);
		return page;
	}
	
	public Page<Profession> findPageByUserId(Long userId, Pageable pageable) {
		Profession profession = new Profession();
		profession.setUserId(userId);
		Page<Profession> page =  findPage( profession, pageable) ;
		return page;
	}
	public Page<Profession> findPageByUserId(Long userId, Pageable pageable, boolean withCover,boolean withBanner) {
		Page<Profession> page =  findPageByUserId( userId, pageable) ;
		if(null != page && page.getTotalElements()>0) {
			List<Profession> content = page.getContent();
			fillRefWithCoverAndBanner(content,withCover,withBanner);
		}
		return page;
	}
	
	public Page<Profession> findPublishedWithCoverAndBanner(Profession profession, Pageable pageable) {
		String title = StringUtils.trim(profession.getTitle());
		String name = StringUtils.trim(profession.getName());
		if (StringUtils.isNotBlank(title)) {
			if (StringUtils.isBlank(name)) {
				profession.setName(title);
			}
		}
		Page<Profession> page = professionDelegate.findPublished(profession, pageable);
		List<Profession> content = page.getContent();
		if(null != content && !content.isEmpty()) {
			fillRefWithCoverAndBanner(content);
		}
		return page;
	}
	
}
