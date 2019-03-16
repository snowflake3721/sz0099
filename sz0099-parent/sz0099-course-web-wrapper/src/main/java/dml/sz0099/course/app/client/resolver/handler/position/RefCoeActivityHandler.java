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
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityPositionWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionCover;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionImage;
import dml.sz0099.course.app.persist.entity.activity.PhotoBanner;
import dml.sz0099.course.app.persist.entity.activity.PhotoCover;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionCover;
import dml.sz0099.course.app.persist.entity.position.PositionImage;
import dml.sz0099.course.app.persist.entity.position.PositionRef;

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

public class RefCoeActivityHandler implements RefHandler<CoeActivityPosition> {

	@Autowired
	protected CoeActivityWrapper coeActivityWrapper;
	
	@Autowired
	protected CoeActivityPositionWrapper coeActivityPositionWrapper;
	
	@Override
	public PageResult<CoeActivityPosition> queryPage(PositionRef position, Pageable pageable) {
		
		CoeActivityPosition coeActivityPostion = new CoeActivityPosition();
		coeActivityPostion.setBaseId(position.getBaseId());
		coeActivityPostion.setTitle(position.getTitle());
		coeActivityPostion.setMainNo(position.getMainNo());
		PageResult<CoeActivityPosition> page = coeActivityPositionWrapper.findPublishedForSelect(coeActivityPostion, pageable);
		return page;
	}
	
	@Override
	public CoeActivityPosition findByMainId(PositionRef positionRef) {
		Long mainId = positionRef.getMainId();
		return coeActivityPositionWrapper.findActivityByMainId(mainId);
	}
	
	@Override
	public PositionRef fillPositionRef(CoeActivityPosition t, PositionRef ref) {
		if(null != t && null != ref) {
			CoeActivity activity = t.getActivity();
			Position position = ref.getPosition();
			if(null != activity) {
				Long mainId = activity.getId();
				List<PhotoCover> coverList = activity.getCoverList();
				Date refreshTime = new Date();
				Long createBy = ref.getCreatedBy();
				//Long id = DistributeIdGenerator.getFlowIdWorkerInstance().nextId();
				Long refId = ref.getId();
				if(null == refId) {
					refId = DistributeIdGenerator.getFlowIdWorkerInstance().nextId();
				}
				t.setId(refId);
				t.setOwnerId(ref.getOwnerId());
				//ref.setOwnerId(ref.getOwnerId());
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
				
				t.setMainNo(activity.getActivityNo());
				
				//t.setAuthorId(activity.getUserId());
				//t.setAuthorname(activity.getPenname());
				
				t.setUserId(activity.getUserId());
				t.setPenname(activity.getPenname());
				t.setHeadImg(activity.getHeadImg());
				t.setNickname(Base64Util.encode(activity.getNickname()));
				
				t.setBaseId(ref.getBaseId());
				
				t.setMainId(mainId);
				t.setOriginalLink(activity.getOriginalLink());
				t.setOrderSeq(activity.getOrderSeq());
				t.setPreIntro(activity.getPreIntro());
				t.setPreIntroType(activity.getPreIntroType());
				
				t.setRefreshTime(refreshTime);
				t.setStatus(PositionRef.STATUS_1_OPEN.getValueInt());
				t.setOpenTime(refreshTime);
				t.setSubTitle(activity.getSubTitle());
				t.setTitle(activity.getTitle());
				
				t.setSaywordId(ref.getSaywordId());
				t.setSayword(ref.getSayword());
				
				//fill PositionRef
				if(null == ref.getId()) {
					ref.setId(refId);
				}
				ref.setMainNo(activity.getActivityNo());
				
				//ref.setAuthorId(activity.getUserId());
				//ref.setAuthorname(activity.getPenname());
				ref.setUserId(activity.getUserId());
				ref.setPenname(activity.getPenname());
				ref.setHeadImg(activity.getHeadImg());
				ref.setNickname(Base64Util.encode(activity.getNickname()));
				
				ref.setName(position.getName());
				
				ref.setMainId(mainId);
				ref.setOriginalLink(activity.getOriginalLink());
				ref.setOrderSeq(activity.getOrderSeq());
				ref.setPreIntro(activity.getPreIntro());
				ref.setPreIntroType(activity.getPreIntroType());
				
				ref.setRefreshTime(refreshTime);
				ref.setStatus(PositionRef.STATUS_1_OPEN.getValueInt());
				ref.setOpenTime(refreshTime);
				ref.setSubTitle(activity.getSubTitle());
				ref.setTitle(activity.getTitle());
				ref.setUserId(activity.getUserId());
				
				
				
				Long aid = activity.getAid();
				if(null != aid) {
					ref.setTopLevel(PositionRef.TOP_LEVEL_1);
					t.setTopLevel(PositionRef.TOP_LEVEL_1);
				}
				
				List<PositionCover> imageCoverList = null;
				List<CoeActivityPositionCover> artCoverList = null;
				if(null != coverList && !coverList.isEmpty()) {
					int i=0;
					imageCoverList = new ArrayList<>(coverList.size());
					artCoverList = new ArrayList<>(coverList.size());
					for(PhotoCover cover : coverList) {
						if(i==0) {
							String coverImage = cover.getFullurl();
							ref.setCoverImage(coverImage);
							t.setCoverImage(coverImage);
						}
						PositionCover positionCover = new PositionCover();
						imageCoverList.add(positionCover);
						positionCover.setUserId(activity.getUserId());
						positionCover.setOwnerId(createBy);//购买者用户
						//positionCover.setAuthorId(activity.getUserId());
						positionCover.setMainId(mainId);
						positionCover.setOrderSeq(cover.getOrderSeq());
						positionCover.setPhotoId(cover.getPhotoId());
						positionCover.setFullurl(cover.getFullurl());
						positionCover.setWidth(cover.getWidth());
						positionCover.setExpectedUrl(cover.getExpectedUrl());
						//positionImage.setImageUrl(photoBanner.getFullurl());
						positionCover.setCreatedBy(createBy);
						positionCover.setLastModifiedBy(createBy);
						Long imgid = DistributeIdGenerator.getFlowIdWorkerInstance().nextId();
						positionCover.setId(imgid);
						positionCover.setRefId(refId);
						
						//
						CoeActivityPositionCover artPositionCover = new CoeActivityPositionCover();
						artCoverList.add(artPositionCover);
						//artPositionCover.setAuthorId(activity.getUserId());
						positionCover.setUserId(activity.getUserId());
						positionCover.setOwnerId(createBy);//购买者用户
						artPositionCover.setMainId(mainId);
						artPositionCover.setOrderSeq(cover.getOrderSeq());
						artPositionCover.setPhotoId(cover.getPhotoId());
						//artPositionCover.setUserId(createBy);//购买者用户
						artPositionCover.setFullurl(cover.getFullurl());
						artPositionCover.setWidth(cover.getWidth());
						artPositionCover.setExpectedUrl(cover.getExpectedUrl());
						//artPositionImage.setImageUrl(photoBanner.getFullurl());
						artPositionCover.setId(imgid);
						artPositionCover.setRefId(refId);
						artPositionCover.setCreatedBy(createBy);
						artPositionCover.setLastModifiedBy(createBy);
					}
					ref.setCoverList(imageCoverList);
					t.setCoverList(artCoverList);
				}
				
				List<PhotoBanner> bannerList = activity.getBannerList();
				List<PositionImage> imageList = null;
				List<CoeActivityPositionImage> artImageList = null;
				if(null != bannerList && !bannerList.isEmpty()) {
					imageList = new ArrayList<>(bannerList.size());
					artImageList = new ArrayList<>(bannerList.size());
					for(PhotoBanner photoBanner : bannerList) {
						PositionImage positionImage = new PositionImage();
						imageList.add(positionImage);
						positionImage.setUserId(activity.getUserId());
						positionImage.setOwnerId(createBy);//购买者用户
						//positionImage.setAuthorId(activity.getUserId());
						positionImage.setMainId(mainId);
						positionImage.setOrderSeq(photoBanner.getOrderSeq());
						positionImage.setPhotoId(photoBanner.getPhotoId());
						//positionImage.setUserId(createBy);//购买者用户
						positionImage.setFullurl(photoBanner.getFullurl());
						positionImage.setWidth(photoBanner.getWidth());
						positionImage.setExpectedUrl(photoBanner.getExpectedUrl());
						//positionImage.setImageUrl(photoBanner.getFullurl());
						positionImage.setCreatedBy(createBy);
						positionImage.setLastModifiedBy(createBy);
						Long imgid = DistributeIdGenerator.getFlowIdWorkerInstance().nextId();
						positionImage.setId(imgid);
						positionImage.setRefId(refId);
						
						//
						CoeActivityPositionImage artPositionImage = new CoeActivityPositionImage();
						artImageList.add(artPositionImage);
						artPositionImage.setUserId(activity.getUserId());
						artPositionImage.setOwnerId(createBy);//购买者用户
						//artPositionImage.setAuthorId(activity.getUserId());
						artPositionImage.setMainId(mainId);
						artPositionImage.setOrderSeq(photoBanner.getOrderSeq());
						artPositionImage.setPhotoId(photoBanner.getPhotoId());
						//artPositionImage.setUserId(createBy);//购买者用户
						artPositionImage.setFullurl(photoBanner.getFullurl());
						artPositionImage.setWidth(photoBanner.getWidth());
						artPositionImage.setExpectedUrl(photoBanner.getExpectedUrl());
						//artPositionImage.setImageUrl(photoBanner.getFullurl());
						artPositionImage.setId(imgid);
						artPositionImage.setRefId(refId);
						artPositionImage.setCreatedBy(createBy);
						artPositionImage.setLastModifiedBy(createBy);
					}
					ref.setBannerList(imageList);
					t.setBannerList(artImageList);
				}
			}
		}
		return ref;
	}


}
