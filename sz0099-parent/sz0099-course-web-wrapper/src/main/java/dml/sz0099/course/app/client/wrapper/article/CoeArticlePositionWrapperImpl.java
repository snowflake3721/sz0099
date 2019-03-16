package dml.sz0099.course.app.client.wrapper.article;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.dozer.DozerBeanMapper;
import org.jit4j.core.persist.page.PageResult;
import org.jit8j.core.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.article.CoeArticlePositionDelegate;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.data.handler.util.NicknameHandler;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionCover;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionImage;
import dml.sz0099.course.app.persist.entity.article.PhotoBanner;
import dml.sz0099.course.app.persist.entity.article.PhotoCover;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeArticlePositionWrapperImpl,组件封装
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
public class CoeArticlePositionWrapperImpl implements CoeArticlePositionWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePositionWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeArticlePositionDelegate coeArticlePositionDelegate;
	
	@Autowired
	private CoeArticleWrapper coeArticleWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private NicknameHandler nicknameHandler;
	
	/**
	 * 根据Id查询CoeArticlePosition实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticlePosition findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeArticlePosition coeArticlePosition = coeArticlePositionDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeArticlePosition);
		return coeArticlePosition;
	}
	
	@Override
	public CoeArticlePosition findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticlePosition coeArticlePosition = coeArticlePositionDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticlePosition);
		return coeArticlePosition;
	}
	
	/**
	 * 根据IdList查询CoeArticlePosition实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticlePosition> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeArticlePosition> coeArticlePositionList = coeArticlePositionDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeArticlePositionList);
		return coeArticlePositionList;
	}
	
	@Override
	public CoeArticlePosition persistEntity(CoeArticlePosition coeArticlePosition) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeArticlePosition entity = coeArticlePositionDelegate.persistEntity(coeArticlePosition);
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticlePosition mergeEntity(CoeArticlePosition coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeArticlePosition entity = coeArticlePositionDelegate.mergeEntity(coeArticlePosition);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticlePosition saveOrUpdate(CoeArticlePosition coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticlePosition entity = coeArticlePositionDelegate.saveOrUpdate(coeArticlePosition);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticlePosition> findPage(CoeArticlePosition coeArticlePosition, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeArticlePosition> page = coeArticlePositionDelegate.findPage(coeArticlePosition, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeArticlePositionDelegate.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionDelegate.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public List<CoeArticlePosition> findByMainIdAndUserId(Long mainId, Long userId) {
		List<CoeArticlePosition> entityList = coeArticlePositionDelegate.findByMainIdAndUserId(mainId, userId);
		if(null != entityList && !entityList.isEmpty()) {
			for(CoeArticlePosition entity : entityList) {
				entity.setNickname(Base64Util.decode(entity.getNickname()));
				entity.setPenname(Base64Util.decode(entity.getPenname()));
				//entity.setAuthorname(Base64Util.decode(entity.getAuthorname()));
			}
		}
		return entityList;
	}

	@Override
	public Page<CoeArticlePosition> findByMainId(Long mainId, Pageable pageable) {
		Page<CoeArticlePosition> page = coeArticlePositionDelegate.findByMainId( mainId,  pageable);
		if(null != page) {
			List<CoeArticlePosition> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				for(CoeArticlePosition cp : content) {
					cp.setNickname(Base64Util.decode(cp.getNickname()));
					cp.setPenname(Base64Util.decode(cp.getPenname()));
					//cp.setAuthorname(Base64Util.decode(cp.getAuthorname()));
				}
			}
		}
		return page;
	}

	@Override
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionDelegate.hasPositionByMainIdAndUserId(mainId,userId);
	}

	@Override
	public CoeArticlePosition bindPosition(CoeArticlePosition coeArticlePosition) {
		Long mainId = coeArticlePosition.getMainId();
		CoeArticle article = coeArticleWrapper.findByIdWithCoverAndBanner(mainId);
		convert2ArticlePostion(coeArticlePosition, article);
		return coeArticlePositionDelegate.mergeForPosition(coeArticlePosition);
	}

	/**
	 * @param coeArticlePosition
	 * @param article
	 */
	private void convert2ArticlePostion(CoeArticlePosition coeArticlePosition, CoeArticle article) {
		if(null != article) {
			Long ownerId = coeArticlePosition.getOwnerId();
			//coeArticlePosition.setAuthorId(ownerId);
			coeArticlePosition.setUserId(article.getUserId());
			CoeUser user = coeUserWrapper.findByUserId(ownerId);
			//coeArticlePosition.setAuthorname(Base64Util.encode(user.getNickname()));
			coeArticlePosition.setNickname(Base64Util.encode(user.getNickname()));
			coeArticlePosition.setHeadImg(user.getHeadImg());
			coeArticlePosition.setPenname(article.getPenname());
			coeArticlePosition.setDescription(article.getDescription());
			coeArticlePosition.setOriginalLink(article.getOriginalLink());
			coeArticlePosition.setMainId(article.getId());
			//coeArticlePosition.setName(CoeArticlePosition.POSITION.getLabel(coeArticlePosition.getPosition()));
			Date openTime = new Date();
			coeArticlePosition.setOpenTime(openTime);
			coeArticlePosition.setRefreshTime(openTime);
			coeArticlePosition.setPreIntro(article.getPreIntro());
			coeArticlePosition.setPreIntroType(article.getPreIntroType());
			//coeArticlePosition.setStatus(CoeArticlePosition.STATUS_1_OPEN.getValueInt());
			coeArticlePosition.setSubTitle(article.getSubTitle());
			coeArticlePosition.setTitle(article.getTitle());
			//coeArticlePosition.setTopLevel(topLevel);
			
			List<PhotoCover> coverList = article.getCoverList();
			String bannerImage=null;
			
			if(null != coverList && !coverList.isEmpty()) {
				List<CoeArticlePositionCover> bannerImageList = new ArrayList<>(coverList.size());
				int i=0;
				for(PhotoCover cover : coverList) {
					CoeArticlePositionCover image = new CoeArticlePositionCover();
					if(i==0) {
						bannerImage = cover.getFullurl();
						coeArticlePosition.setCoverImage(bannerImage);
					}
					i++;
					//image.setAuthorId(article.getUserId());
					image.setUserId(article.getUserId());
					image.setOwnerId(ownerId);
					
					image.setFullurl(cover.getFullurl());
					image.setExpectedUrl(cover.getExpectedUrl());
					image.setWidth(cover.getWidth());
					//image.setImageUrl(banner.getFullurl());
					image.setMainId(article.getId());
					image.setPhotoId(cover.getPhotoId());
				}
				
				coeArticlePosition.setCoverList(bannerImageList);
			}
			
			List<PhotoBanner> bannerList = article.getBannerList();
			
			if(null != bannerList && !bannerList.isEmpty()) {
				List<CoeArticlePositionImage> bannerImageList = new ArrayList<>(bannerList.size());
				for(PhotoBanner banner : bannerList) {
					CoeArticlePositionImage image = new CoeArticlePositionImage();
					//image.setAuthorId(article.getUserId());
					image.setUserId(article.getUserId());
					image.setOwnerId(ownerId);
					
					image.setFullurl(banner.getFullurl());
					image.setExpectedUrl(banner.getExpectedUrl());
					image.setWidth(banner.getWidth());
					//image.setImageUrl(banner.getFullurl());
					image.setLastModifiedBy(coeArticlePosition.getLastModifiedBy());
					image.setMainId(article.getId());
					image.setPhotoId(banner.getPhotoId());
					image.setUserId(coeArticlePosition.getLastModifiedBy());
					image.setCreatedBy(coeArticlePosition.getCreatedBy());
				}
				
				coeArticlePosition.setBannerList(bannerImageList);
			}
			
		}
	}
	
	public PageResult<CoeArticlePosition> findPublishedForSelect(CoeArticlePosition articlePosition, Pageable pageable) {
		CoeArticle coeArticle = new CoeArticle();
		coeArticle.setTitle(articlePosition.getTitle());
		coeArticle.setArticleNo(articlePosition.getMainNo());
		PageResult<CoeArticle> articlePage = coeArticleWrapper.findPublishedForSelect(coeArticle,pageable);
		PageResult<CoeArticlePosition> page = null;
		if(null != articlePage) {
			List<CoeArticle> content = articlePage.getContent();
			List<CoeArticlePosition> positionContent = new ArrayList<>();
			Map<Long, CoeArticlePosition> map = new HashMap<>();
			List<Long> mainIdList= new ArrayList<>();
			if(null != content) {
				for(CoeArticle c : content) {
					CoeArticlePosition postion = new CoeArticlePosition();
					//convert2ArticlePostionForSelect(postion,c);
					Long mainId = c.getId();
					postion.setArticle(c);
					positionContent.add(postion);
					map.put(mainId, postion);
					mainIdList.add(mainId);
				}
			}
			Long baseId = articlePosition.getBaseId();
			if(null != baseId && !mainIdList.isEmpty()) {
			List<CoeArticlePosition> refs = findByBaseIdAndMainIdList(baseId, mainIdList);
			if(null != refs && !refs.isEmpty()) {
				for(CoeArticlePosition ref : refs) {
					Long mainId = ref.getMainId();
					CoeArticlePosition p = map.get(mainId);
					if(null !=p) {
						p.setId(ref.getId());
						p.setAid(ref.getAid());
						//p.setAuthorname(ref.getAuthorname());
						//p.setArticle(article);
					}
				}
			}
			}
			
			page = new PageResult<>(positionContent,pageable,articlePage.getTotalElements()) ;
		}
		
		return page;
	}
	
	private void convert2ArticlePostionForSelect(CoeArticlePosition coeArticlePosition, CoeArticle article) {
		if(null != article) {
			Long ownerId = coeArticlePosition.getOwnerId();
			//coeArticlePosition.setAuthorId(userId);
			coeArticlePosition.setUserId(article.getUserId());
			CoeUser user = coeUserWrapper.findByUserId(ownerId);
//			coeArticlePosition.setAuthorname(user.getNickname());
			coeArticlePosition.setNickname(user.getNickname());
			coeArticlePosition.setHeadImg(user.getHeadImg());
			coeArticlePosition.setPenname(article.getPenname());
			
			coeArticlePosition.setDescription(article.getDescription());
			coeArticlePosition.setOriginalLink(article.getOriginalLink());
			coeArticlePosition.setMainId(article.getId());
			coeArticlePosition.setPreIntro(article.getPreIntro());
			coeArticlePosition.setPreIntroType(article.getPreIntroType());
			coeArticlePosition.setSubTitle(article.getSubTitle());
			coeArticlePosition.setTitle(article.getTitle());
			coeArticlePosition.setMainNo(article.getArticleNo());
			
			List<PhotoCover> coverList = article.getCoverList();
			String bannerImage=null;
			if(null != coverList && !coverList.isEmpty()) {
				List<CoeArticlePositionCover> bannerImageList = new ArrayList<>(coverList.size());
				int i=0;
				for(PhotoCover cover : coverList) {
					CoeArticlePositionCover image = new CoeArticlePositionCover();
					if(i==0) {
						bannerImage = cover.getFullurl();
						coeArticlePosition.setCoverImage(bannerImage);
					}
					i++;
					//image.setAuthorId(article.getUserId());
					image.setUserId(article.getUserId());
					image.setOwnerId(ownerId);
					
					image.setFullurl(cover.getFullurl());
					image.setExpectedUrl(cover.getExpectedUrl());
					image.setWidth(cover.getWidth());
					//image.setImageUrl(banner.getFullurl());
					image.setMainId(article.getId());
					image.setPhotoId(cover.getPhotoId());
				}
				
				coeArticlePosition.setCoverList(bannerImageList);
			}
			
			List<PhotoBanner> bannerList = article.getBannerList();
			
			if(null != bannerList && !bannerList.isEmpty()) {
				List<CoeArticlePositionImage> bannerImageList = new ArrayList<>(bannerList.size());
				for(PhotoBanner banner : bannerList) {
					CoeArticlePositionImage image = new CoeArticlePositionImage();
					//image.setAuthorId(article.getUserId());
					image.setUserId(article.getUserId());
					image.setOwnerId(ownerId);
					image.setFullurl(banner.getFullurl());
					image.setExpectedUrl(banner.getExpectedUrl());
					image.setWidth(banner.getWidth());
					//image.setImageUrl(banner.getFullurl());
					image.setMainId(article.getId());
					image.setPhotoId(banner.getPhotoId());
				}
				
				coeArticlePosition.setBannerList(bannerImageList);
			}
			
		}
	}
	
	public List<CoeArticlePosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList){
		return coeArticlePositionDelegate.findByBaseIdAndMainIdList(baseId,mainIdList);
	}

	@Override
	public CoeArticlePosition findArticleByMainId(Long mainId) {
		CoeArticlePosition coeArticlePosition = new CoeArticlePosition();
		CoeArticle article = coeArticleWrapper.findByIdWithCoverAndBanner(mainId);
		coeArticlePosition.setArticle(article);
		return coeArticlePosition;
	}
	
	public CoeArticlePosition addPositionRef(CoeArticlePosition coeArticlePosition) {
		return persistEntity(coeArticlePosition);
	}
	
	public void deleteById(CoeArticlePosition coeArticlePosition) {
		coeArticlePositionDelegate.deleteById(coeArticlePosition);
	}
	
	public void deletePositionRef(CoeArticlePosition coeArticlePosition) {
		deleteById(coeArticlePosition);
	}

	@Override
	public CoeArticlePosition mergePositionRef(CoeArticlePosition coeArticlePosition) {
		return coeArticlePositionDelegate.mergePositionRef(coeArticlePosition);
	}
	
	@Override
	public CoeArticlePosition openPositionRef(CoeArticlePosition positionRef) {
		Long id = positionRef.getId();
		if(null != id) {
			positionRef = coeArticlePositionDelegate.openPositionRef(positionRef);
		}
		return positionRef;
	}
	
	public CoeArticlePosition mergeSimpleSingle(CoeArticlePosition positionRef) {
		Long id = positionRef.getId();
		if(null != id) {
			positionRef = coeArticlePositionDelegate.mergeSimpleSingle(positionRef);
		}
		return positionRef;
	}
	
	public CoeArticlePosition deleteRefByBaseId(CoeArticlePosition positionRef) {
		positionRef = coeArticlePositionDelegate.deleteRefByBaseId(positionRef);
		return positionRef;
	}
	public List<CoeArticlePosition> findByBaseId(CoeArticlePosition positionRef) {
		return coeArticlePositionDelegate.findByBaseId(positionRef);
	}
	
	public Page<CoeArticlePosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable){
		return coeArticlePositionDelegate.findPageForPosition(ponMainId, ponSubId, positionId, ponPanel, pageable);
	}
	
	public Page<CoeArticlePosition> findByBaseId(Long baseId, Pageable pageable){
		Page<CoeArticlePosition> page = coeArticlePositionDelegate.findByBaseId( baseId,  pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeArticlePosition> content = page.getContent();
			handleNickname(content);
		}
		return page;
	}

	/**
	 * @param content
	 */
	private void handleNickname(List<CoeArticlePosition> content) {
			nicknameHandler.handleNickname(content);
	}
	public Page<CoeArticlePosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable) {
		Page<CoeArticlePosition> page = coeArticlePositionDelegate.findByBaseIdList(baseIdList,  pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeArticlePosition> content = page.getContent();
			handleNickname(content);
		}
		return page;
	}
	
}
