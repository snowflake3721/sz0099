package dml.sz0099.course.app.client.wrapper.profession;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.jit4j.core.persist.page.PageResult;
import org.jit8j.core.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.profession.ProfessionPositionDelegate;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.persist.entity.profession.PhotoBanner;
import dml.sz0099.course.app.persist.entity.profession.PhotoCover;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionCover;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionImage;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionPositionWrapperImpl,组件封装
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
public class ProfessionPositionWrapperImpl implements ProfessionPositionWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPositionWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ProfessionPositionDelegate professionPositionDelegate;
	
	@Autowired
	private ProfessionWrapper professionWrapper;
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	/**
	 * 根据Id查询ProfessionPosition实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionPosition findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ProfessionPosition professionPosition = professionPositionDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, professionPosition);
		return professionPosition;
	}
	
	@Override
	public ProfessionPosition findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionPosition professionPosition = professionPositionDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, professionPosition);
		return professionPosition;
	}
	
	/**
	 * 根据IdList查询ProfessionPosition实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionPosition> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ProfessionPosition> professionPositionList = professionPositionDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  professionPositionList);
		return professionPositionList;
	}
	
	@Override
	public ProfessionPosition persistEntity(ProfessionPosition professionPosition) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ProfessionPosition entity = professionPositionDelegate.persistEntity(professionPosition);
		Long id = professionPosition.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionPosition mergeEntity(ProfessionPosition professionPosition) {
		Long id = professionPosition.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ProfessionPosition entity = professionPositionDelegate.mergeEntity(professionPosition);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionPosition saveOrUpdate(ProfessionPosition professionPosition) {
		Long id = professionPosition.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionPosition entity = professionPositionDelegate.saveOrUpdate(professionPosition);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionPosition> findPage(ProfessionPosition professionPosition, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ProfessionPosition> page = professionPositionDelegate.findPage(professionPosition, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return professionPositionDelegate.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return professionPositionDelegate.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public List<ProfessionPosition> findByMainIdAndUserId(Long mainId, Long userId) {
		List<ProfessionPosition> entityList = professionPositionDelegate.findByMainIdAndUserId(mainId, userId);
		if(null != entityList && !entityList.isEmpty()) {
			for(ProfessionPosition entity : entityList) {
				//entity.setAuthorname(Base64Util.decode(entity.getAuthorname()));
				entity.setNickname(Base64Util.decode(entity.getNickname()));
				entity.setPenname(Base64Util.decode(entity.getPenname()));
			}
		}
		return entityList;
	}

	@Override
	public Page<ProfessionPosition> findByMainId(Long mainId, Pageable pageable) {
		Page<ProfessionPosition> page = professionPositionDelegate.findByMainId( mainId,  pageable);
		if(null != page) {
			List<ProfessionPosition> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				for(ProfessionPosition cp : content) {
					//cp.setAuthorname(Base64Util.decode(cp.getAuthorname()));
					cp.setNickname(Base64Util.decode(cp.getNickname()));
					cp.setPenname(Base64Util.decode(cp.getPenname()));
				}
			}
		}
		return page;
	}

	@Override
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		return professionPositionDelegate.hasPositionByMainIdAndUserId(mainId,userId);
	}

	@Override
	public ProfessionPosition bindPosition(ProfessionPosition professionPosition) {
		Long mainId = professionPosition.getMainId();
		Profession profession = professionWrapper.findByIdWithCoverAndBanner(mainId);
		convert2ProfessionPostion(professionPosition, profession);
		return professionPositionDelegate.mergeForPosition(professionPosition);
	}

	/**
	 * @param professionPosition
	 * @param profession
	 */
	private void convert2ProfessionPostion(ProfessionPosition professionPosition, Profession profession) {
		if(null != profession) {
			//Long userId = professionPosition.getUserId();
			//professionPosition.setAuthorId(userId);
			//CoeUser user = coeUserWrapper.findByUserId(userId);
			//professionPosition.setAuthorname(Base64Util.encode(user.getNickname()));
			
			Long ownerId = professionPosition.getOwnerId();
			//coeArticlePosition.setAuthorId(ownerId);
			professionPosition.setUserId(profession.getUserId());
			CoeUser user = coeUserWrapper.findByUserId(ownerId);
			//coeArticlePosition.setAuthorname(Base64Util.encode(user.getNickname()));
			professionPosition.setNickname(Base64Util.encode(user.getNickname()));
			professionPosition.setHeadImg(user.getHeadImg());
			professionPosition.setPenname(profession.getPenname());
			
			professionPosition.setDescription(profession.getDescription());
			professionPosition.setOriginalLink(profession.getOriginalLink());
			professionPosition.setMainId(profession.getId());
			//professionPosition.setName(ProfessionPosition.POSITION.getLabel(professionPosition.getPosition()));
			Date openTime = new Date();
			professionPosition.setOpenTime(openTime);
			professionPosition.setRefreshTime(openTime);
			professionPosition.setPreIntro(profession.getPreIntro());
			professionPosition.setPreIntroType(profession.getPreIntroType());
			//professionPosition.setStatus(ProfessionPosition.STATUS_1_OPEN.getValueInt());
			professionPosition.setSubTitle(profession.getSubTitle());
			professionPosition.setTitle(profession.getTitle());
			//professionPosition.setTopLevel(topLevel);
			
			List<PhotoCover> coverList = profession.getCoverList();
			String coverImage=null;
			
			if(null != coverList && !coverList.isEmpty()) {
				List<ProfessionPositionCover> coverImageList = new ArrayList<>(coverList.size());
				int i=0;
				for(PhotoCover cover : coverList) {
					ProfessionPositionCover image = new ProfessionPositionCover();
					if(i==0) {
						coverImage = cover.getFullurl();
						professionPosition.setCoverImage(coverImage);
					}
					i++;
					//image.setAuthorId(profession.getUserId());
					
					image.setUserId(profession.getUserId());
					image.setOwnerId(ownerId);
					
					image.setFullurl(cover.getFullurl());
					image.setExpectedUrl(cover.getExpectedUrl());
					image.setWidth(cover.getWidth());
					//image.setImageUrl(banner.getFullurl());
					image.setMainId(profession.getId());
					image.setPhotoId(cover.getPhotoId());
				}
				
				professionPosition.setCoverList(coverImageList);
			}
			
			List<PhotoBanner> bannerList = profession.getBannerList();
			
			if(null != bannerList && !bannerList.isEmpty()) {
				List<ProfessionPositionImage> bannerImageList = new ArrayList<>(bannerList.size());
				for(PhotoBanner banner : bannerList) {
					ProfessionPositionImage image = new ProfessionPositionImage();
					//image.setAuthorId(profession.getUserId());
					
					image.setUserId(profession.getUserId());
					image.setOwnerId(ownerId);
					
					image.setFullurl(banner.getFullurl());
					image.setExpectedUrl(banner.getExpectedUrl());
					image.setWidth(banner.getWidth());
					//image.setImageUrl(banner.getFullurl());
					image.setLastModifiedBy(professionPosition.getLastModifiedBy());
					image.setMainId(profession.getId());
					image.setPhotoId(banner.getPhotoId());
					image.setUserId(professionPosition.getLastModifiedBy());
					image.setCreatedBy(professionPosition.getCreatedBy());
				}
				
				professionPosition.setBannerList(bannerImageList);
			}
			
		}
	}
	

	
	public PageResult<ProfessionPosition> findPublishedForSelect(ProfessionPosition professionPosition, Pageable pageable) {
		Profession profession = new Profession();
		profession.setTitle(professionPosition.getTitle());
		profession.setProfessionNo(professionPosition.getMainNo());
		PageResult<Profession> professionPage = professionWrapper.findPublishedForSelect(profession,pageable);
		PageResult<ProfessionPosition> page = null;
		if(null != professionPage) {
			List<Profession> content = professionPage.getContent();
			List<ProfessionPosition> positionContent = new ArrayList<>();
			Map<Long, ProfessionPosition> map = new HashMap<>();
			List<Long> mainIdList= new ArrayList<>();
			if(null != content) {
				for(Profession c : content) {
					ProfessionPosition postion = new ProfessionPosition();
					//convert2ProfessionPostionForSelect(postion,c);
					Long mainId = c.getId();
					postion.setProfession(c);
					positionContent.add(postion);
					map.put(mainId, postion);
					mainIdList.add(mainId);
				}
			}
			Long baseId = professionPosition.getBaseId();
			if(null != baseId && !mainIdList.isEmpty()) {
			List<ProfessionPosition> refs = findByBaseIdAndMainIdList(baseId, mainIdList);
			if(null != refs && !refs.isEmpty()) {
				for(ProfessionPosition ref : refs) {
					Long mainId = ref.getMainId();
					ProfessionPosition p = map.get(mainId);
					if(null !=p) {
						p.setId(ref.getId());
						p.setAid(ref.getAid());
						//p.setAuthorname(ref.getAuthorname());
						//p.setProfession(profession);
					}
				}
			}
			}
			
			page = new PageResult<>(positionContent,pageable,professionPage.getTotalElements()) ;
		}
		
		return page;
	}
	
	private void convert2ProfessionPostionForSelect(ProfessionPosition professionPosition, Profession profession) {
		if(null != profession) {
			//Long userId = professionPosition.getUserId();
			//professionPosition.setAuthorId(userId);
			//CoeUser user = coeUserWrapper.findByUserId(userId);
			//professionPosition.setAuthorname(user.getNickname());
			
			Long ownerId = professionPosition.getOwnerId();
			//coeArticlePosition.setAuthorId(userId);
			professionPosition.setUserId(profession.getUserId());
			CoeUser user = coeUserWrapper.findByUserId(ownerId);
//			coeArticlePosition.setAuthorname(user.getNickname());
			professionPosition.setNickname(user.getNickname());
			professionPosition.setHeadImg(user.getHeadImg());
			professionPosition.setPenname(profession.getPenname());
			
			professionPosition.setDescription(profession.getDescription());
			professionPosition.setOriginalLink(profession.getOriginalLink());
			professionPosition.setMainId(profession.getId());
			professionPosition.setPreIntro(profession.getPreIntro());
			professionPosition.setPreIntroType(profession.getPreIntroType());
			professionPosition.setSubTitle(profession.getSubTitle());
			professionPosition.setTitle(profession.getTitle());
			professionPosition.setMainNo(profession.getProfessionNo());
			
			List<PhotoCover> coverList = profession.getCoverList();
			
			String coverImage=null;
			
			if(null != coverList && !coverList.isEmpty()) {
				List<ProfessionPositionCover> coverImageList = new ArrayList<>(coverList.size());
				int i=0;
				for(PhotoCover cover : coverList) {
					ProfessionPositionCover image = new ProfessionPositionCover();
					if(i==0) {
						coverImage = cover.getFullurl();
						professionPosition.setCoverImage(coverImage);
					}
					i++;
					//image.setAuthorId(profession.getUserId());
					image.setUserId(profession.getUserId());
					image.setOwnerId(ownerId);
					
					image.setFullurl(cover.getFullurl());
					image.setExpectedUrl(cover.getExpectedUrl());
					image.setWidth(cover.getWidth());
					//image.setImageUrl(banner.getFullurl());
					image.setMainId(profession.getId());
					image.setPhotoId(cover.getPhotoId());
				}
				
				professionPosition.setCoverList(coverImageList);
			}
			
			List<PhotoBanner> bannerList = profession.getBannerList();
			
			if(null != bannerList && !bannerList.isEmpty()) {
				List<ProfessionPositionImage> bannerImageList = new ArrayList<>(bannerList.size());
				for(PhotoBanner banner : bannerList) {
					ProfessionPositionImage image = new ProfessionPositionImage();
					//image.setAuthorId(profession.getUserId());
					image.setUserId(profession.getUserId());
					image.setOwnerId(ownerId);
					
					image.setFullurl(banner.getFullurl());
					image.setExpectedUrl(banner.getExpectedUrl());
					image.setWidth(banner.getWidth());
					//image.setImageUrl(banner.getFullurl());
					image.setMainId(profession.getId());
					image.setPhotoId(banner.getPhotoId());
				}
				
				professionPosition.setBannerList(bannerImageList);
			}
			
		}
	}
	
	public List<ProfessionPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList){
		return professionPositionDelegate.findByBaseIdAndMainIdList(baseId,mainIdList);
	}

	@Override
	public ProfessionPosition findProfessionByMainId(Long mainId) {
		ProfessionPosition professionPosition = new ProfessionPosition();
		Profession profession = professionWrapper.findByIdWithCoverAndBanner(mainId);
		professionPosition.setProfession(profession);
		return professionPosition;
	}
	
	public ProfessionPosition addPositionRef(ProfessionPosition professionPosition) {
		return persistEntity(professionPosition);
	}
	
	public void deleteById(ProfessionPosition professionPosition) {
		professionPositionDelegate.deleteById(professionPosition);
	}
	
	public void deletePositionRef(ProfessionPosition professionPosition) {
		deleteById(professionPosition);
	}

	@Override
	public ProfessionPosition mergePositionRef(ProfessionPosition professionPosition) {
		return professionPositionDelegate.mergePositionRef(professionPosition);
	}
	
	@Override
	public ProfessionPosition openPositionRef(ProfessionPosition positionRef) {
		Long id = positionRef.getId();
		if(null != id) {
			positionRef = professionPositionDelegate.openPositionRef(positionRef);
		}
		return positionRef;
	}
	
	public ProfessionPosition mergeSimpleSingle(ProfessionPosition positionRef) {
		Long id = positionRef.getId();
		if(null != id) {
			positionRef = professionPositionDelegate.mergeSimpleSingle(positionRef);
		}
		return positionRef;
	}
	
	public ProfessionPosition deleteRefByBaseId(ProfessionPosition positionRef) {
		positionRef = professionPositionDelegate.deleteRefByBaseId(positionRef);
		return positionRef;
	}
	public List<ProfessionPosition> findByBaseId(ProfessionPosition positionRef) {
		return professionPositionDelegate.findByBaseId(positionRef);
	}
	
	public Page<ProfessionPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable){
		return professionPositionDelegate.findPageForPosition(ponMainId, ponSubId, positionId, ponPanel, pageable);
	}
	
	public Page<ProfessionPosition> findByBaseId(Long baseId, Pageable pageable){
		return professionPositionDelegate.findByBaseId(baseId, pageable);
	}
	public Page<ProfessionPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable){
		return professionPositionDelegate.findByBaseIdList(baseIdList, pageable);
	}
}
