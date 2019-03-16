package dml.sz0099.course.app.client.wrapper.activity;

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

import dml.sz0099.course.app.biz.delegate.activity.CoeActivityPositionDelegate;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.data.handler.util.NicknameHandler;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionCover;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionImage;
import dml.sz0099.course.app.persist.entity.activity.PhotoBanner;
import dml.sz0099.course.app.persist.entity.activity.PhotoCover;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityPositionWrapperImpl,组件封装
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
public class CoeActivityPositionWrapperImpl implements CoeActivityPositionWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPositionWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeActivityPositionDelegate coeActivityPositionDelegate;
	
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	@Autowired
	private NicknameHandler nicknameHandler;
	
	/**
	 * 根据Id查询CoeActivityPosition实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityPosition findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeActivityPosition coeActivityPosition = coeActivityPositionDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeActivityPosition);
		return coeActivityPosition;
	}
	
	@Override
	public CoeActivityPosition findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityPosition coeActivityPosition = coeActivityPositionDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityPosition);
		return coeActivityPosition;
	}
	
	/**
	 * 根据IdList查询CoeActivityPosition实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityPosition> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeActivityPosition> coeActivityPositionList = coeActivityPositionDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeActivityPositionList);
		return coeActivityPositionList;
	}
	
	@Override
	public CoeActivityPosition persistEntity(CoeActivityPosition coeActivityPosition) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeActivityPosition entity = coeActivityPositionDelegate.persistEntity(coeActivityPosition);
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityPosition mergeEntity(CoeActivityPosition coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityPosition entity = coeActivityPositionDelegate.mergeEntity(coeActivityPosition);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityPosition saveOrUpdate(CoeActivityPosition coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityPosition entity = coeActivityPositionDelegate.saveOrUpdate(coeActivityPosition);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityPosition> findPage(CoeActivityPosition coeActivityPosition, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeActivityPosition> page = coeActivityPositionDelegate.findPage(coeActivityPosition, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeActivityPositionDelegate.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPositionDelegate.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public List<CoeActivityPosition> findByMainIdAndUserId(Long mainId, Long userId) {
		List<CoeActivityPosition> entityList = coeActivityPositionDelegate.findByMainIdAndUserId(mainId, userId);
		if(null != entityList && !entityList.isEmpty()) {
			for(CoeActivityPosition entity : entityList) {
				entity.setNickname(Base64Util.decode(entity.getNickname()));
				entity.setPenname(Base64Util.decode(entity.getPenname()));
				//entity.setAuthorname(Base64Util.decode(entity.getAuthorname()));
			}
		}
		return entityList;
	}

	@Override
	public Page<CoeActivityPosition> findByMainId(Long mainId, Pageable pageable) {
		Page<CoeActivityPosition> page = coeActivityPositionDelegate.findByMainId( mainId,  pageable);
		if(null != page) {
			List<CoeActivityPosition> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				for(CoeActivityPosition cp : content) {
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
		return coeActivityPositionDelegate.hasPositionByMainIdAndUserId(mainId,userId);
	}

	@Override
	public CoeActivityPosition bindPosition(CoeActivityPosition coeActivityPosition) {
		Long mainId = coeActivityPosition.getMainId();
		CoeActivity activity = coeActivityWrapper.findByIdWithCoverAndBanner(mainId);
		convert2ActivityPostion(coeActivityPosition, activity);
		return coeActivityPositionDelegate.mergeForPosition(coeActivityPosition);
	}

	/**
	 * @param coeActivityPosition
	 * @param activity
	 */
	private void convert2ActivityPostion(CoeActivityPosition coeActivityPosition, CoeActivity activity) {
		if(null != activity) {
			Long ownerId = coeActivityPosition.getOwnerId();
			//coeActivityPosition.setAuthorId(ownerId);
			coeActivityPosition.setUserId(activity.getUserId());
			CoeUser user = coeUserWrapper.findByUserId(ownerId);
			//coeActivityPosition.setAuthorname(Base64Util.encode(user.getNickname()));
			coeActivityPosition.setNickname(Base64Util.encode(user.getNickname()));
			coeActivityPosition.setHeadImg(user.getHeadImg());
			coeActivityPosition.setPenname(activity.getPenname());
			coeActivityPosition.setDescription(activity.getDescription());
			coeActivityPosition.setOriginalLink(activity.getOriginalLink());
			coeActivityPosition.setMainId(activity.getId());
			//coeActivityPosition.setName(CoeActivityPosition.POSITION.getLabel(coeActivityPosition.getPosition()));
			Date openTime = new Date();
			coeActivityPosition.setOpenTime(openTime);
			coeActivityPosition.setRefreshTime(openTime);
			coeActivityPosition.setPreIntro(activity.getPreIntro());
			coeActivityPosition.setPreIntroType(activity.getPreIntroType());
			//coeActivityPosition.setStatus(CoeActivityPosition.STATUS_1_OPEN.getValueInt());
			coeActivityPosition.setSubTitle(activity.getSubTitle());
			coeActivityPosition.setTitle(activity.getTitle());
			//coeActivityPosition.setTopLevel(topLevel);
			
			List<PhotoCover> coverList = activity.getCoverList();
			String bannerImage=null;
			
			if(null != coverList && !coverList.isEmpty()) {
				List<CoeActivityPositionCover> bannerImageList = new ArrayList<>(coverList.size());
				int i=0;
				for(PhotoCover cover : coverList) {
					CoeActivityPositionCover image = new CoeActivityPositionCover();
					if(i==0) {
						bannerImage = cover.getFullurl();
						coeActivityPosition.setCoverImage(bannerImage);
					}
					i++;
					//image.setAuthorId(activity.getUserId());
					image.setUserId(activity.getUserId());
					image.setOwnerId(ownerId);
					
					image.setFullurl(cover.getFullurl());
					image.setExpectedUrl(cover.getExpectedUrl());
					image.setWidth(cover.getWidth());
					//image.setImageUrl(banner.getFullurl());
					image.setMainId(activity.getId());
					image.setPhotoId(cover.getPhotoId());
				}
				
				coeActivityPosition.setCoverList(bannerImageList);
			}
			
			List<PhotoBanner> bannerList = activity.getBannerList();
			
			if(null != bannerList && !bannerList.isEmpty()) {
				List<CoeActivityPositionImage> bannerImageList = new ArrayList<>(bannerList.size());
				for(PhotoBanner banner : bannerList) {
					CoeActivityPositionImage image = new CoeActivityPositionImage();
					//image.setAuthorId(activity.getUserId());
					image.setUserId(activity.getUserId());
					image.setOwnerId(ownerId);
					
					image.setFullurl(banner.getFullurl());
					image.setExpectedUrl(banner.getExpectedUrl());
					image.setWidth(banner.getWidth());
					//image.setImageUrl(banner.getFullurl());
					image.setLastModifiedBy(coeActivityPosition.getLastModifiedBy());
					image.setMainId(activity.getId());
					image.setPhotoId(banner.getPhotoId());
					image.setUserId(coeActivityPosition.getLastModifiedBy());
					image.setCreatedBy(coeActivityPosition.getCreatedBy());
				}
				
				coeActivityPosition.setBannerList(bannerImageList);
			}
			
		}
	}
	
	public PageResult<CoeActivityPosition> findPublishedForSelect(CoeActivityPosition activityPosition, Pageable pageable) {
		CoeActivity coeActivity = new CoeActivity();
		coeActivity.setTitle(activityPosition.getTitle());
		coeActivity.setActivityNo(activityPosition.getMainNo());
		PageResult<CoeActivity> activityPage = coeActivityWrapper.findPublishedForSelect(coeActivity,pageable);
		PageResult<CoeActivityPosition> page = null;
		if(null != activityPage) {
			List<CoeActivity> content = activityPage.getContent();
			List<CoeActivityPosition> positionContent = new ArrayList<>();
			Map<Long, CoeActivityPosition> map = new HashMap<>();
			List<Long> mainIdList= new ArrayList<>();
			if(null != content) {
				for(CoeActivity c : content) {
					CoeActivityPosition postion = new CoeActivityPosition();
					//convert2ActivityPostionForSelect(postion,c);
					Long mainId = c.getId();
					postion.setActivity(c);
					positionContent.add(postion);
					map.put(mainId, postion);
					mainIdList.add(mainId);
				}
			}
			Long baseId = activityPosition.getBaseId();
			if(null != baseId && !mainIdList.isEmpty()) {
			List<CoeActivityPosition> refs = findByBaseIdAndMainIdList(baseId, mainIdList);
			if(null != refs && !refs.isEmpty()) {
				for(CoeActivityPosition ref : refs) {
					Long mainId = ref.getMainId();
					CoeActivityPosition p = map.get(mainId);
					if(null !=p) {
						p.setId(ref.getId());
						p.setAid(ref.getAid());
						//p.setAuthorname(ref.getAuthorname());
						//p.setActivity(activity);
					}
				}
			}
			}
			
			page = new PageResult<>(positionContent,pageable,activityPage.getTotalElements()) ;
		}
		
		return page;
	}
	
	private void convert2ActivityPostionForSelect(CoeActivityPosition coeActivityPosition, CoeActivity activity) {
		if(null != activity) {
			Long ownerId = coeActivityPosition.getOwnerId();
			//coeActivityPosition.setAuthorId(userId);
			coeActivityPosition.setUserId(activity.getUserId());
			CoeUser user = coeUserWrapper.findByUserId(ownerId);
//			coeActivityPosition.setAuthorname(user.getNickname());
			coeActivityPosition.setNickname(user.getNickname());
			coeActivityPosition.setHeadImg(user.getHeadImg());
			coeActivityPosition.setPenname(activity.getPenname());
			
			coeActivityPosition.setDescription(activity.getDescription());
			coeActivityPosition.setOriginalLink(activity.getOriginalLink());
			coeActivityPosition.setMainId(activity.getId());
			coeActivityPosition.setPreIntro(activity.getPreIntro());
			coeActivityPosition.setPreIntroType(activity.getPreIntroType());
			coeActivityPosition.setSubTitle(activity.getSubTitle());
			coeActivityPosition.setTitle(activity.getTitle());
			coeActivityPosition.setMainNo(activity.getActivityNo());
			
			List<PhotoCover> coverList = activity.getCoverList();
			String bannerImage=null;
			if(null != coverList && !coverList.isEmpty()) {
				List<CoeActivityPositionCover> bannerImageList = new ArrayList<>(coverList.size());
				int i=0;
				for(PhotoCover cover : coverList) {
					CoeActivityPositionCover image = new CoeActivityPositionCover();
					if(i==0) {
						bannerImage = cover.getFullurl();
						coeActivityPosition.setCoverImage(bannerImage);
					}
					i++;
					//image.setAuthorId(activity.getUserId());
					image.setUserId(activity.getUserId());
					image.setOwnerId(ownerId);
					
					image.setFullurl(cover.getFullurl());
					image.setExpectedUrl(cover.getExpectedUrl());
					image.setWidth(cover.getWidth());
					//image.setImageUrl(banner.getFullurl());
					image.setMainId(activity.getId());
					image.setPhotoId(cover.getPhotoId());
				}
				
				coeActivityPosition.setCoverList(bannerImageList);
			}
			
			List<PhotoBanner> bannerList = activity.getBannerList();
			
			if(null != bannerList && !bannerList.isEmpty()) {
				List<CoeActivityPositionImage> bannerImageList = new ArrayList<>(bannerList.size());
				for(PhotoBanner banner : bannerList) {
					CoeActivityPositionImage image = new CoeActivityPositionImage();
					//image.setAuthorId(activity.getUserId());
					image.setUserId(activity.getUserId());
					image.setOwnerId(ownerId);
					image.setFullurl(banner.getFullurl());
					image.setExpectedUrl(banner.getExpectedUrl());
					image.setWidth(banner.getWidth());
					//image.setImageUrl(banner.getFullurl());
					image.setMainId(activity.getId());
					image.setPhotoId(banner.getPhotoId());
				}
				
				coeActivityPosition.setBannerList(bannerImageList);
			}
			
		}
	}
	
	public List<CoeActivityPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList){
		return coeActivityPositionDelegate.findByBaseIdAndMainIdList(baseId,mainIdList);
	}

	@Override
	public CoeActivityPosition findActivityByMainId(Long mainId) {
		CoeActivityPosition coeActivityPosition = new CoeActivityPosition();
		CoeActivity activity = coeActivityWrapper.findByIdWithCoverAndBanner(mainId);
		coeActivityPosition.setActivity(activity);
		return coeActivityPosition;
	}
	
	public CoeActivityPosition addPositionRef(CoeActivityPosition coeActivityPosition) {
		return persistEntity(coeActivityPosition);
	}
	
	public void deleteById(CoeActivityPosition coeActivityPosition) {
		coeActivityPositionDelegate.deleteById(coeActivityPosition);
	}
	
	public void deletePositionRef(CoeActivityPosition coeActivityPosition) {
		deleteById(coeActivityPosition);
	}

	@Override
	public CoeActivityPosition mergePositionRef(CoeActivityPosition coeActivityPosition) {
		return coeActivityPositionDelegate.mergePositionRef(coeActivityPosition);
	}
	
	@Override
	public CoeActivityPosition openPositionRef(CoeActivityPosition positionRef) {
		Long id = positionRef.getId();
		if(null != id) {
			positionRef = coeActivityPositionDelegate.openPositionRef(positionRef);
		}
		return positionRef;
	}
	
	public CoeActivityPosition mergeSimpleSingle(CoeActivityPosition positionRef) {
		Long id = positionRef.getId();
		if(null != id) {
			positionRef = coeActivityPositionDelegate.mergeSimpleSingle(positionRef);
		}
		return positionRef;
	}
	
	public CoeActivityPosition deleteRefByBaseId(CoeActivityPosition positionRef) {
		positionRef = coeActivityPositionDelegate.deleteRefByBaseId(positionRef);
		return positionRef;
	}
	public List<CoeActivityPosition> findByBaseId(CoeActivityPosition positionRef) {
		return coeActivityPositionDelegate.findByBaseId(positionRef);
	}
	
	public Page<CoeActivityPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable){
		return coeActivityPositionDelegate.findPageForPosition(ponMainId, ponSubId, positionId, ponPanel, pageable);
	}
	
	public Page<CoeActivityPosition> findByBaseId(Long baseId, Pageable pageable){
		Page<CoeActivityPosition> page = coeActivityPositionDelegate.findByBaseId( baseId,  pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeActivityPosition> content = page.getContent();
			handleNickname(content);
		}
		return page;
	}

	/**
	 * @param content
	 */
	private void handleNickname(List<CoeActivityPosition> content) {
			nicknameHandler.handleNickname(content);
	}
	public Page<CoeActivityPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable) {
		Page<CoeActivityPosition> page = coeActivityPositionDelegate.findByBaseIdList(baseIdList,  pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeActivityPosition> content = page.getContent();
			handleNickname(content);
		}
		return page;
	}
	
}
