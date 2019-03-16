/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.handler.position;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jit4j.core.persist.page.PageResult;
import org.jit8j.core.util.Base64Util;
import org.jit8j.core.util.generator.DistributeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.client.resolver.position.RefHandler;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionPositionWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionWrapper;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionCover;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionCover;
import dml.sz0099.course.app.persist.entity.position.PositionImage;
import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.profession.PhotoBanner;
import dml.sz0099.course.app.persist.entity.profession.PhotoCover;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionCover;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionImage;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-10-23 11:18:20
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-10-23	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class RefProfessionHandler implements RefHandler<ProfessionPosition> {

	@Autowired
	protected ProfessionWrapper professionWrapper;
	
	@Autowired
	protected ProfessionPositionWrapper professionPositionWrapper;
	
	@Override
	public PageResult<ProfessionPosition> queryPage(PositionRef position, Pageable pageable) {
		
		ProfessionPosition professionPostion = new ProfessionPosition();
		professionPostion.setBaseId(position.getBaseId());
		professionPostion.setTitle(position.getTitle());
		professionPostion.setMainNo(position.getMainNo());
		PageResult<ProfessionPosition> page = professionPositionWrapper.findPublishedForSelect(professionPostion, pageable);
		return page;
	}
	
	@Override
	public ProfessionPosition findByMainId(PositionRef positionRef) {
		Long mainId = positionRef.getMainId();
		return professionPositionWrapper.findProfessionByMainId(mainId);
	}
	
	@Override
	public PositionRef fillPositionRef(ProfessionPosition t, PositionRef ref) {
		if(null != t && null != ref) {
			Profession profession = t.getProfession();
			Position position = ref.getPosition();
			if(null != profession) {
				Long mainId = profession.getId();
				List<PhotoCover> coverList = profession.getCoverList();
				Date refreshTime = new Date();
				Long createBy = ref.getCreatedBy();
				Long id = DistributeIdGenerator.getFlowIdWorkerInstance().nextId();
				t.setId(id);
				//t.setUserId(ref.getUserId());
				t.setOwnerId(ref.getUserId());
				ref.setOwnerId(ref.getUserId());
				
				t.setViewType(ref.getViewType());
				t.setCreatedBy(createBy);
				t.setLastModifiedBy(createBy);
				
				t.setPonLayout(position.getLayout());//布局
				t.setPonMainId(position.getMainId());
				t.setPonPanel(position.getPanel());
				t.setPonSubId(position.getSubId());
				t.setExtendId(position.getExtendId());
				t.setName(position.getName());
				t.setPositionId(ref.getPositionId());
				
				t.setMainNo(profession.getProfessionNo());
				
				//t.setAuthorId(profession.getUserId());
				//t.setAuthorname(profession.getPenname());
				t.setUserId(profession.getUserId());
				t.setPenname(profession.getPenname());
				t.setHeadImg(profession.getHeadImg());
				t.setNickname(Base64Util.encode(profession.getNickname()));
				
				t.setBaseId(ref.getBaseId());
				
				t.setMainId(mainId);
				t.setOriginalLink(profession.getOriginalLink());
				t.setOrderSeq(profession.getOrderSeq());
				t.setPreIntro(profession.getPreIntro());
				t.setPreIntroType(profession.getPreIntroType());
				
				t.setRefreshTime(refreshTime);
				t.setStatus(PositionRef.STATUS_1_OPEN.getValueInt());
				t.setOpenTime(refreshTime);
				t.setSubTitle(profession.getSubTitle());
				t.setTitle(profession.getTitle());
				t.setSaywordId(profession.getSaywordId());
				t.setSayword(profession.getSayword());
				
				//fill PositionRef
				
				ref.setId(id);
				ref.setMainNo(profession.getProfessionNo());
				//ref.setAuthorId(profession.getUserId());
				//ref.setAuthorname(profession.getPenname());
				ref.setUserId(profession.getUserId());
				ref.setPenname(profession.getPenname());
				ref.setHeadImg(profession.getHeadImg());
				ref.setNickname(Base64Util.encode(profession.getNickname()));
				
				ref.setName(position.getName());
				
				ref.setMainId(mainId);
				ref.setOriginalLink(profession.getOriginalLink());
				ref.setOrderSeq(profession.getOrderSeq());
				ref.setPreIntro(profession.getPreIntro());
				ref.setPreIntroType(profession.getPreIntroType());
				
				ref.setRefreshTime(refreshTime);
				ref.setStatus(PositionRef.STATUS_1_OPEN.getValueInt());
				ref.setOpenTime(refreshTime);
				ref.setSubTitle(profession.getSubTitle());
				ref.setTitle(profession.getTitle());
				ref.setUserId(profession.getUserId());
				
				Long aid = profession.getAid();
				if(null != aid) {
					ref.setTopLevel(PositionRef.TOP_LEVEL_1);
					t.setTopLevel(PositionRef.TOP_LEVEL_1);
				}
				
				
				List<PositionCover> imageCoverList = null;
				List<ProfessionPositionCover> professionCoverList = null;
				if(null != coverList && !coverList.isEmpty()) {
					int i=0;
					imageCoverList = new ArrayList<>(coverList.size());
					professionCoverList = new ArrayList<>(coverList.size());
					for(PhotoCover cover : coverList) {
						if(i==0) {
							String coverImage = cover.getFullurl();
							ref.setCoverImage(coverImage);
							t.setCoverImage(coverImage);
						}
						i++;
						PositionCover positionCover = new PositionCover();
						imageCoverList.add(positionCover);
						//positionCover.setAuthorId(profession.getUserId());
						positionCover.setMainId(mainId);
						positionCover.setOrderSeq(cover.getOrderSeq());
						positionCover.setPhotoId(cover.getPhotoId());
						positionCover.setUserId(profession.getUserId());
						positionCover.setOwnerId(createBy);//购买者用户
						positionCover.setFullurl(cover.getFullurl());
						positionCover.setWidth(cover.getWidth());
						positionCover.setExpectedUrl(cover.getExpectedUrl());
						//positionImage.setImageUrl(photoBanner.getFullurl());
						positionCover.setCreatedBy(createBy);
						positionCover.setLastModifiedBy(createBy);
						Long imgid = DistributeIdGenerator.getFlowIdWorkerInstance().nextId();
						positionCover.setId(imgid);
						positionCover.setRefId(id);
						
						//
						ProfessionPositionCover professionPositionCover = new ProfessionPositionCover();
						professionCoverList.add(professionPositionCover);
						//professionPositionCover.setAuthorId(profession.getUserId());
						professionPositionCover.setMainId(mainId);
						professionPositionCover.setOrderSeq(cover.getOrderSeq());
						professionPositionCover.setPhotoId(cover.getPhotoId());
						positionCover.setUserId(profession.getUserId());
						positionCover.setOwnerId(createBy);//购买者用户
						professionPositionCover.setFullurl(cover.getFullurl());
						professionPositionCover.setWidth(cover.getWidth());
						professionPositionCover.setExpectedUrl(cover.getExpectedUrl());
						//artPositionImage.setImageUrl(photoBanner.getFullurl());
						professionPositionCover.setId(imgid);
						professionPositionCover.setRefId(id);
						professionPositionCover.setCreatedBy(createBy);
						professionPositionCover.setLastModifiedBy(createBy);
					}
					ref.setCoverList(imageCoverList);
					t.setCoverList(professionCoverList);
				}
				
				
				List<PhotoBanner> bannerList = profession.getBannerList();
				List<PositionImage> imageList = null;
				List<ProfessionPositionImage> professionImageList = null;
				if(null != bannerList && !bannerList.isEmpty()) {
					imageList = new ArrayList<>(bannerList.size());
					professionImageList = new ArrayList<>(bannerList.size());
					for(PhotoBanner photoBanner : bannerList) {
						PositionImage positionImage = new PositionImage();
						imageList.add(positionImage);
						//positionImage.setAuthorId(profession.getUserId());
						positionImage.setMainId(mainId);
						positionImage.setOrderSeq(photoBanner.getOrderSeq());
						positionImage.setPhotoId(photoBanner.getPhotoId());
						positionImage.setUserId(profession.getUserId());
						positionImage.setOwnerId(createBy);//购买者用户
						positionImage.setFullurl(photoBanner.getFullurl());
						positionImage.setWidth(photoBanner.getWidth());
						positionImage.setExpectedUrl(photoBanner.getExpectedUrl());
						//positionImage.setImageUrl(photoBanner.getFullurl());
						positionImage.setCreatedBy(createBy);
						positionImage.setLastModifiedBy(createBy);
						Long imgid = DistributeIdGenerator.getFlowIdWorkerInstance().nextId();
						positionImage.setId(imgid);
						positionImage.setRefId(id);
						
						//
						ProfessionPositionImage professionPositionImage = new ProfessionPositionImage();
						professionImageList.add(professionPositionImage);
						//professionPositionImage.setAuthorId(profession.getUserId());
						professionPositionImage.setMainId(mainId);
						professionPositionImage.setOrderSeq(photoBanner.getOrderSeq());
						professionPositionImage.setPhotoId(photoBanner.getPhotoId());
						//professionPositionImage.setUserId(createBy);//购买者用户
						professionPositionImage.setUserId(profession.getUserId());
						professionPositionImage.setOwnerId(createBy);//购买者用户
						professionPositionImage.setFullurl(photoBanner.getFullurl());
						professionPositionImage.setWidth(photoBanner.getWidth());
						professionPositionImage.setExpectedUrl(photoBanner.getExpectedUrl());
						//artPositionImage.setImageUrl(photoBanner.getFullurl());
						professionPositionImage.setId(imgid);
						professionPositionImage.setRefId(id);
						professionPositionImage.setCreatedBy(createBy);
						professionPositionImage.setLastModifiedBy(createBy);
					}
					ref.setBannerList(imageList);
					t.setBannerList(professionImageList);
				}
			}
		}
		return ref;
	}


}
