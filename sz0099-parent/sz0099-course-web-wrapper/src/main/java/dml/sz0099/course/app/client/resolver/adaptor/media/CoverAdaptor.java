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
import dml.sz0099.course.app.client.wrapper.paragraph.PhotoCoverWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeProductWrapper;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.entity.paragraph.Photo;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoCover;
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

public class CoverAdaptor extends ImageDefaultAdaptor<CoeProduct>{

	@Autowired
	private CoeProductWrapper coeProductWrapper;
	
	@Autowired
	private PhotoCoverWrapper photoCoverWrapper;
	
	@Autowired
	private ImageProccessor imageProccessor;
	
	@Override
	public CoeProduct convert(ImageExtend imageExtend) {
		CoeProduct coeProduct = null;
		if(null != imageExtend) {
			coeProduct = new CoeProduct();
			
			List<Imagebase> images = imageExtend.getImages();
			
			List<PhotoCover> coverList = null;
			if(null != images && !images.isEmpty()) {
				coverList = new ArrayList<>(images.size());
				coeProduct.setCoverList(coverList);
				for(Imagebase image : images) {
					ImageRef ref = image.getImageRef();
					
					PhotoCover photoCover = new PhotoCover();
					coverList.add(photoCover);
					Photo photo = new Photo();
					photoCover.setPhoto(photo);
					
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
					
					photoCover.setFullurl(photo.getFullurl());
					photoCover.setExpectedUrl(ref.getExpectedUrl());//缩略图
					photoCover.setSubId(ref.getSubId());
					photoCover.setId(ref.getId());//指定id与ref.id相同
					photoCover.setPhotoId(photo.getId());//指定photoId
					photoCover.setOrderSeq(ref.getOrderSeq());//排序
					photoCover.setWidth(image.getWidth());
					photoCover.setName(photo.getName());//冗余
					photoCover.setTitle(photo.getTitle());//冗余
					photoCover.setCreatedBy(ref.getCreatedBy());
					photoCover.setLastModifiedBy(ref.getLastModifiedBy());
					
					photoCover.setSubId(ref.getSubId());
					photoCover.setMainId(ref.getMainId());
					
					if(coeProduct.getId()==null) {
						coeProduct.setId(ref.getMainId());
					}
					photoCover.setUserId(ref.getUserId());
					
				}
			}
		}
		return coeProduct;
	}

	@Override
	public boolean persist(CoeProduct t) {
		CoeProduct entity = coeProductWrapper.persistForCover(t);
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
		
		PhotoCover photoCover = new PhotoCover();
		photoCover.setPhoto(photo);
		
		photoCover.setId(ref.getId());
		photoCover.setPhotoId(baseId);
		photoCover.setSubId(ref.getSubId());
		photoCover.setMainId(ref.getMainId());
		
		photoCover.setOrderSeq(ref.getOrderSeq());
		photoCover.setName(ref.getName());
		photoCover.setTitle(ref.getTitle());
		photoCover.setLastModifiedBy(ref.getLastModifiedBy());
		PhotoCover entity= photoCoverWrapper.mergeForTitle(photoCover);
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
		
		PhotoCover photoCover = new PhotoCover();
		photoCover.setPhoto(photo);
		
		photoCover.setId(ref.getId());
		photoCover.setPhotoId(baseId);
		photoCover.setSubId(ref.getSubId());
		photoCover.setMainId(ref.getMainId());
		
		photoCover.setUserId(ref.getUserId());
		photoCover.setLastModifiedBy(ref.getLastModifiedBy());
		photoCoverWrapper.deleteById(photoCover);
		
		
		return true;
	}

	@Override
	public boolean deleteImageList(List<ImageRef> refList) {
		return false;
	}
	
	public boolean deleteFromRemote(PhotoCover photoCover) {
		
		Long subId = photoCover.getSubId();
		Long productId = photoCover.getMainId();
		ImageRequest imageRequest = new ImageRequest();
		imageRequest.setDevId("sz0099");
		imageRequest.setProject("ood");
		imageRequest.setModule("product");
		imageRequest.setVariety("product");
		imageRequest.setPosition("cover");
		imageRequest.setMainId(productId);
		imageRequest.setSubId(subId);
		imageRequest.setUserId(photoCover.getUserId());
		boolean result = imageProccessor.deleteByRequest(imageRequest);
		return result;
	}

}
