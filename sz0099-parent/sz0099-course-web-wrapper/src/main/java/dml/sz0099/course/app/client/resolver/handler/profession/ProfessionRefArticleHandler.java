/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.handler.profession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jit4j.core.persist.page.PageResult;
import org.jit8j.core.util.generator.DistributeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.client.resolver.profession.ProfessionRefHandler;
import dml.sz0099.course.app.client.wrapper.article.CoeArticleWrapper;
import dml.sz0099.course.app.client.wrapper.article.CoeCategArticleWrapper;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.article.PhotoBanner;
import dml.sz0099.course.app.persist.entity.article.PhotoCover;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionCover;
import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-10-23 10:29:36
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-10-23	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class ProfessionRefArticleHandler implements ProfessionRefHandler<CoeCategArticle> {

	@Autowired
	protected CoeCategArticleWrapper coeCategArticleWrapper;
	
	@Autowired
	protected CoeArticleWrapper coeArticleWrapper;

	@Override
	public PageResult<CoeCategArticle> queryPage(ProfessionRef profession, Pageable pageable) {

		CoeCategArticle coeCategArticle = new CoeCategArticle();
		CoeArticle coeArticle = new CoeArticle();
		coeCategArticle.setArticle(coeArticle);

		// coeArticle.setBaseId(position.getBaseId());
		coeArticle.setTitle(profession.getTitle());
		coeArticle.setArticleNo(profession.getMainNo());
		coeArticle.setUserId(profession.getUserId());
		Category category = new Category();
		coeCategArticle.setCategory(category);
		category.setCode(Category.CODE_ARTICLE_PROFESSION);
		
		PageResult<CoeCategArticle> page = (PageResult<CoeCategArticle>) coeCategArticleWrapper.findPageForPublish(coeCategArticle, pageable);
		return page;
	}

	@Override
	public CoeCategArticle findByMainId(ProfessionRef positionRef) {
		Long mainId = positionRef.getMainId();
		CoeArticle mainEntity = coeArticleWrapper.findById(mainId);
		positionRef.setMainEntity(mainEntity);
		CoeCategArticle coeCategArticle = new CoeCategArticle();
		coeCategArticle.setArticle(mainEntity);
		return coeCategArticle;
	}

	@Override
	public ProfessionRef fillProfessionRef(CoeCategArticle articleCateg, ProfessionRef ref) {
		if (null != articleCateg && null != ref) {
			Profession profession = ref.getProfession();
			CoeArticle article = articleCateg.getArticle();
			if (null != article) {
				Long mainId = article.getId();
				Date refreshTime = new Date();
				Long createBy = ref.getCreatedBy();
				Long id = DistributeIdGenerator.getFlowIdWorkerInstance().nextId();
				// fill ProfessionRef

				ref.setId(id);
				ref.setMainNo(article.getArticleNo());
				ref.setAuthorId(article.getUserId());
				ref.setAuthorname(article.getPenname());
				ref.setName(profession.getName());

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
				ref.setDescription(article.getDescription());
				
				//ref.setMainEntity(article);

				Long aid = article.getAid();
				if (null != aid) {
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
						professionCover.setUserId(ref.getUserId());
						
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
				if (null != bannerList && !bannerList.isEmpty()) {
					imageList = new ArrayList<>(bannerList.size());
					for (PhotoBanner photoBanner : bannerList) {
						ProfessionImage professionImage = new ProfessionImage();
						imageList.add(professionImage);
						professionImage.setAuthorId(article.getUserId());
						professionImage.setMainId(mainId);
						professionImage.setOrderSeq(photoBanner.getOrderSeq());
						professionImage.setPhotoId(photoBanner.getPhotoId());
						professionImage.setUserId(createBy);// 购买者用户
						professionImage.setFullurl(photoBanner.getFullurl());
						professionImage.setWidth(photoBanner.getWidth());
						professionImage.setExpectedUrl(photoBanner.getExpectedUrl());
						//professionImage.setImageUrl(photoBanner.getFullurl());
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
