/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.adaptor.media;

import java.util.ArrayList;
import java.util.List;

import org.jit4j.core.pub.transfer.dto.ImageRequest;
import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.resolver.media.ImageDefaultAdaptor;
import dml.sz0099.course.app.client.resolver.media.ImageProccessor;
import dml.sz0099.course.app.client.resolver.media.PhotoUtil;
import dml.sz0099.course.app.client.wrapper.paragraph.PhotoBannerWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeProductWrapper;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.entity.paragraph.Photo;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoBanner;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-23 12:04:45
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-23	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class BannerAdaptor extends ImageDefaultAdaptor<CoeProduct>{

	@Autowired
	private CoeProductWrapper coeProductWrapper;
	
	@Autowired
	private PhotoBannerWrapper photoBannerWrapper;
	
	@Autowired
	private ImageProccessor imageProccessor;
	
	@Override
	public CoeProduct convert(ImageExtend imageExtend) {
		CoeProduct coeProduct = null;
		if(null != imageExtend) {
			coeProduct = new CoeProduct();
			
			List<Imagebase> images = imageExtend.getImages();
			
			List<PhotoBanner> bannerList = null;
			if(null != images && !images.isEmpty()) {
				bannerList = new ArrayList<>(images.size());
				coeProduct.setBannerList(bannerList);
				for(Imagebase image : images) {
					ImageRef ref = image.getImageRef();
					
					PhotoBanner photoBanner = new PhotoBanner();
					bannerList.add(photoBanner);
					Photo photo = new Photo();
					photoBanner.setPhoto(photo);
					
					photo.setAbsolute(image.getAbsolute());
					photo.setAccessUrl(image.getViewUrl());
					photo.setDescription(ref.getDescription());//描述
					photo.setDomain(imageExtend.getDomain());
					photo.setFilename(image.getFilename());
					photo.setFullurl(PhotoUtil.getFullUrl(photo.getDomain(), photo.getAccessUrl()));
					photo.setName(ref.getName());//名称
					photo.setOrderSeq(ref.getOrderSeq()); //排序
					photo.setRelative(image.getRelative());
					photo.setSize(image.getSize());
					photo.setTitle(ref.getTitle());//标题
					photo.setUserId(image.getUserId());
					photo.setWidth(image.getWidth());
					photo.setHeight(image.getHeight());
					photo.setId(image.getId());//指定photo.id==Imagebase.id
					photo.setCreatedBy(image.getCreatedBy());
					photo.setLastModifiedBy(image.getLastModifiedBy());
					
					photoBanner.setFullurl(photo.getFullurl());
					photoBanner.setExpectedUrl(ref.getExpectedUrl());//缩略图
					photoBanner.setSubId(ref.getSubId());
					photoBanner.setId(ref.getId());//指定id与ref.id相同
					photoBanner.setPhotoId(photo.getId());//指定photoId
					photoBanner.setOrderSeq(ref.getOrderSeq());//排序
					photoBanner.setWidth(image.getWidth());
					photoBanner.setName(photo.getName());//冗余
					photoBanner.setTitle(photo.getTitle());//冗余
					photoBanner.setCreatedBy(ref.getCreatedBy());
					photoBanner.setLastModifiedBy(ref.getLastModifiedBy());
					
					photoBanner.setSubId(ref.getSubId());
					photoBanner.setMainId(ref.getMainId());
					
					if(coeProduct.getId()==null) {
						coeProduct.setId(ref.getMainId());
					}
					photoBanner.setUserId(ref.getUserId());
					
				}
			}
		}
		return coeProduct;
	}

	@Override
	public boolean persist(CoeProduct t) {
		CoeProduct entity = coeProductWrapper.persistForBanner(t);
		if(null != entity && CoeProduct.SUCCESS_YES==entity.getSuccess()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean mergeImage(ImageRef ref) {
		Long baseId = ref.getBaseId();
		Photo photo = new Photo();
		photo.setId(baseId);
		photo.setName(ref.getName());
		photo.setTitle(ref.getTitle());
		photo.setDescription(ref.getDescription());
		photo.setOrderSeq(ref.getOrderSeq());
		photo.setLastModifiedBy(ref.getLastModifiedBy());
		
		PhotoBanner photoBanner = new PhotoBanner();
		photoBanner.setPhoto(photo);
		
		photoBanner.setId(ref.getId());
		photoBanner.setPhotoId(baseId);
		photoBanner.setSubId(ref.getSubId());
		photoBanner.setMainId(ref.getMainId());
		
		photoBanner.setOrderSeq(ref.getOrderSeq());
		photoBanner.setName(ref.getName());
		photoBanner.setTitle(ref.getTitle());
		photoBanner.setLastModifiedBy(ref.getLastModifiedBy());
		PhotoBanner entity= photoBannerWrapper.mergeForTitle(photoBanner);
		if(null != entity && CoeProduct.SUCCESS_YES==entity.getSuccess()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteImage(ImageRef ref) {
		Long baseId = ref.getBaseId();
		Photo photo = new Photo();
		photo.setId(baseId);
		photo.setLastModifiedBy(ref.getLastModifiedBy());
		
		PhotoBanner photoBanner = new PhotoBanner();
		photoBanner.setPhoto(photo);
		
		photoBanner.setId(ref.getId());
		photoBanner.setPhotoId(baseId);
		photoBanner.setSubId(ref.getSubId());
		photoBanner.setMainId(ref.getMainId());
		
		photoBanner.setUserId(ref.getUserId());
		photoBanner.setLastModifiedBy(ref.getLastModifiedBy());
		photoBannerWrapper.deleteById(photoBanner);
		
		
		return true;
	}

	@Override
	public boolean deleteImageList(List<ImageRef> refList) {
		return false;
	}
	
	public boolean deleteFromRemote(PhotoBanner photoBanner) {
		
		Long subId = photoBanner.getSubId();
		Long productId = photoBanner.getMainId();
		ImageRequest imageRequest = new ImageRequest();
		imageRequest.setDevId("sz0099");
		imageRequest.setProject("ood");
		imageRequest.setModule("product");
		imageRequest.setVariety("product");
		imageRequest.setPosition("banner");
		imageRequest.setMainId(productId);
		imageRequest.setSubId(subId);
		imageRequest.setUserId(photoBanner.getUserId());
		boolean result = imageProccessor.deleteByRequest(imageRequest);
		return result;
	}

}
