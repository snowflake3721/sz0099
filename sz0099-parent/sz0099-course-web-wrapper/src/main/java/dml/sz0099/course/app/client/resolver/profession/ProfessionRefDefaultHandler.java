/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.profession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jit4j.core.persist.page.PageResult;
import org.jit8j.core.util.generator.DistributeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.client.wrapper.article.CoeArticleWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionRefWrapper;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.PhotoBanner;
import dml.sz0099.course.app.persist.entity.article.PhotoCover;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionCover;
import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;

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

public class ProfessionRefDefaultHandler implements ProfessionRefHandler<CoeArticle> {

	@Autowired
	protected CoeArticleWrapper coeArticleWrapper;
	
	@Autowired
	protected ProfessionRefWrapper positionRefWrapper;
	
	
	
	@Override
	public PageResult<CoeArticle> queryPage(ProfessionRef profession, Pageable pageable) {
		
		CoeArticle coeArticle = new CoeArticle();
		//coeArticle.setBaseId(position.getId());
		coeArticle.setTitle(profession.getTitle());
		coeArticle.setArticleNo(profession.getMainNo());
		PageResult<CoeArticle> page = coeArticleWrapper.findPublishedForSelect(coeArticle, pageable);
		
		return page;
	}



	@Override
	public CoeArticle findByMainId(ProfessionRef positionRef) {
		Long mainId = positionRef.getMainId();
		return null;//coeArticleWrapper.findArticleByMainId(mainId);
	}
	
	@Override
	public ProfessionRef fillProfessionRef(CoeArticle t, ProfessionRef ref) {
		if(null != t && null != ref) {
			CoeArticle article = t;
			Profession position = ref.getProfession();
			if(null != article) {
				Long mainId = article.getId();
				Date refreshTime = new Date();
				Long createBy = ref.getCreatedBy();
				Long id = DistributeIdGenerator.getFlowIdWorkerInstance().nextId();
				
				//fill ProfessionRef
				
				ref.setId(id);
				ref.setMainNo(article.getArticleNo());
				ref.setAuthorId(article.getUserId());
				ref.setAuthorname(article.getPenname());
				//ref.setBaseId(t.getBaseId());
				ref.setName(position.getName());
				
				ref.setMainId(mainId);
				ref.setOriginalLink(article.getOriginalLink());
				ref.setOrderSeq(article.getOrderSeq());
				ref.setPreIntro(article.getPreIntro());
				ref.setPreIntroType(article.getPreIntroType());
				
				ref.setRefreshTime(refreshTime);
				ref.setStatus(ProfessionRef.STATUS_1_OPEN.getValueInt());
				ref.setOpenTime(refreshTime);
				ref.setSubTitle(article.getSubTitle());
				ref.setTitle(article.getTitle());
				
				Long aid = article.getAid();
				if(null != aid) {
					ref.setTopLevel(aid.intValue());
				}
				
				List<PhotoCover> coverList = article.getCoverList();
				List<ProfessionCover> imageCoverList = null;
				if(null != coverList && !coverList.isEmpty()) {
					imageCoverList = new ArrayList<>(coverList.size());
					int i=0;
					for(PhotoCover photoCover : coverList) {
						if(i==0) {
							String coverImage = photoCover.getFullurl();
							ref.setCoverImage(coverImage);
						}
						i++;
						ProfessionCover professionCover = new ProfessionCover();
						imageCoverList.add(professionCover);
						professionCover.setAuthorId(article.getUserId());
						professionCover.setMainId(mainId);
						professionCover.setOrderSeq(photoCover.getOrderSeq());
						professionCover.setPhotoId(photoCover.getPhotoId());
						professionCover.setUserId(t.getUserId());
						
						professionCover.setFullurl(photoCover.getFullurl());
						professionCover.setWidth(photoCover.getWidth());
						professionCover.setExpectedUrl(photoCover.getExpectedUrl());
						//positionImage.setImageUrl(photoBanner.getFullurl());
						professionCover.setCreatedBy(createBy);
						professionCover.setLastModifiedBy(createBy);
						Long imgid = DistributeIdGenerator.getFlowIdWorkerInstance().nextId();
						professionCover.setId(imgid);
						professionCover.setRefId(id);
						
					}
					ref.setCoverList(imageCoverList);
				}
				
				List<PhotoBanner> bannerList = article.getBannerList();
				List<ProfessionImage> imageList = null;
				if(null != bannerList && !bannerList.isEmpty()) {
					imageList = new ArrayList<>(bannerList.size());
					for(PhotoBanner photoBanner : bannerList) {
						ProfessionImage professionImage = new ProfessionImage();
						imageList.add(professionImage);
						professionImage.setAuthorId(article.getUserId());
						professionImage.setMainId(mainId);
						professionImage.setOrderSeq(photoBanner.getOrderSeq());
						professionImage.setPhotoId(photoBanner.getPhotoId());
						professionImage.setUserId(t.getUserId());
						
						professionImage.setFullurl(photoBanner.getFullurl());
						professionImage.setWidth(photoBanner.getWidth());
						professionImage.setExpectedUrl(photoBanner.getExpectedUrl());
						//positionImage.setImageUrl(photoBanner.getFullurl());
						professionImage.setCreatedBy(createBy);
						professionImage.setLastModifiedBy(createBy);
						Long imgid = DistributeIdGenerator.getFlowIdWorkerInstance().nextId();
						professionImage.setId(imgid);
						professionImage.setRefId(id);
						
					}
					ref.setBannerList(imageList);
				}
			}
		}
		return ref;
	}


}
