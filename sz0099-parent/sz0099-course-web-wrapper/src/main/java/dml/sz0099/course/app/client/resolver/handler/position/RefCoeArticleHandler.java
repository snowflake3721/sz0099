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
import dml.sz0099.course.app.client.wrapper.article.CoeArticlePositionWrapper;
import dml.sz0099.course.app.client.wrapper.article.CoeArticleWrapper;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionCover;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionImage;
import dml.sz0099.course.app.persist.entity.article.PhotoBanner;
import dml.sz0099.course.app.persist.entity.article.PhotoCover;
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

public class RefCoeArticleHandler implements RefHandler<CoeArticlePosition> {

	@Autowired
	protected CoeArticleWrapper coeArticleWrapper;
	
	@Autowired
	protected CoeArticlePositionWrapper coeArticlePositionWrapper;
	
	@Override
	public PageResult<CoeArticlePosition> queryPage(PositionRef position, Pageable pageable) {
		
		CoeArticlePosition coeArticlePostion = new CoeArticlePosition();
		coeArticlePostion.setBaseId(position.getBaseId());
		coeArticlePostion.setTitle(position.getTitle());
		coeArticlePostion.setMainNo(position.getMainNo());
		PageResult<CoeArticlePosition> page = coeArticlePositionWrapper.findPublishedForSelect(coeArticlePostion, pageable);
		return page;
	}
	
	@Override
	public CoeArticlePosition findByMainId(PositionRef positionRef) {
		Long mainId = positionRef.getMainId();
		return coeArticlePositionWrapper.findArticleByMainId(mainId);
	}
	
	@Override
	public PositionRef fillPositionRef(CoeArticlePosition t, PositionRef ref) {
		if(null != t && null != ref) {
			CoeArticle article = t.getArticle();
			Position position = ref.getPosition();
			if(null != article) {
				Long mainId = article.getId();
				List<PhotoCover> coverList = article.getCoverList();
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
				
				t.setMainNo(article.getArticleNo());
				
				//t.setAuthorId(article.getUserId());
				//t.setAuthorname(article.getPenname());
				
				t.setUserId(article.getUserId());
				t.setPenname(article.getPenname());
				t.setHeadImg(article.getHeadImg());
				t.setNickname(Base64Util.encode(article.getNickname()));
				
				t.setBaseId(ref.getBaseId());
				
				t.setMainId(mainId);
				t.setOriginalLink(article.getOriginalLink());
				t.setOrderSeq(article.getOrderSeq());
				t.setPreIntro(article.getPreIntro());
				t.setPreIntroType(article.getPreIntroType());
				
				t.setRefreshTime(refreshTime);
				t.setStatus(PositionRef.STATUS_1_OPEN.getValueInt());
				t.setOpenTime(refreshTime);
				t.setSubTitle(article.getSubTitle());
				t.setTitle(article.getTitle());
				
				t.setSaywordId(ref.getSaywordId());
				t.setSayword(ref.getSayword());
				
				//fill PositionRef
				if(null == ref.getId()) {
					ref.setId(refId);
				}
				ref.setMainNo(article.getArticleNo());
				
				//ref.setAuthorId(article.getUserId());
				//ref.setAuthorname(article.getPenname());
				ref.setUserId(article.getUserId());
				ref.setPenname(article.getPenname());
				ref.setHeadImg(article.getHeadImg());
				ref.setNickname(Base64Util.encode(article.getNickname()));
				
				ref.setName(position.getName());
				
				ref.setMainId(mainId);
				ref.setOriginalLink(article.getOriginalLink());
				ref.setOrderSeq(article.getOrderSeq());
				ref.setPreIntro(article.getPreIntro());
				ref.setPreIntroType(article.getPreIntroType());
				
				ref.setRefreshTime(refreshTime);
				ref.setStatus(PositionRef.STATUS_1_OPEN.getValueInt());
				ref.setOpenTime(refreshTime);
				ref.setSubTitle(article.getSubTitle());
				ref.setTitle(article.getTitle());
				ref.setUserId(article.getUserId());
				
				
				
				Long aid = article.getAid();
				if(null != aid) {
					ref.setTopLevel(PositionRef.TOP_LEVEL_1);
					t.setTopLevel(PositionRef.TOP_LEVEL_1);
				}
				
				List<PositionCover> imageCoverList = null;
				List<CoeArticlePositionCover> artCoverList = null;
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
						positionCover.setUserId(article.getUserId());
						positionCover.setOwnerId(createBy);//购买者用户
						//positionCover.setAuthorId(article.getUserId());
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
						CoeArticlePositionCover artPositionCover = new CoeArticlePositionCover();
						artCoverList.add(artPositionCover);
						//artPositionCover.setAuthorId(article.getUserId());
						positionCover.setUserId(article.getUserId());
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
				
				List<PhotoBanner> bannerList = article.getBannerList();
				List<PositionImage> imageList = null;
				List<CoeArticlePositionImage> artImageList = null;
				if(null != bannerList && !bannerList.isEmpty()) {
					imageList = new ArrayList<>(bannerList.size());
					artImageList = new ArrayList<>(bannerList.size());
					for(PhotoBanner photoBanner : bannerList) {
						PositionImage positionImage = new PositionImage();
						imageList.add(positionImage);
						positionImage.setUserId(article.getUserId());
						positionImage.setOwnerId(createBy);//购买者用户
						//positionImage.setAuthorId(article.getUserId());
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
						CoeArticlePositionImage artPositionImage = new CoeArticlePositionImage();
						artImageList.add(artPositionImage);
						artPositionImage.setUserId(article.getUserId());
						artPositionImage.setOwnerId(createBy);//购买者用户
						//artPositionImage.setAuthorId(article.getUserId());
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
