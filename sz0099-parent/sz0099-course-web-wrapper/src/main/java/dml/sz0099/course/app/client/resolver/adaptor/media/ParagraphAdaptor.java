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
import dml.sz0099.course.app.client.wrapper.paragraph.ParagraphWrapper;
import dml.sz0099.course.app.client.wrapper.paragraph.PhotoParagWrapper;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.entity.paragraph.Paragraph;
import dml.sz0099.course.app.persist.entity.paragraph.Photo;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoParag;

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

public class ParagraphAdaptor extends ImageDefaultAdaptor<Paragraph>{

	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	@Autowired
	private PhotoParagWrapper photoParagWrapper;
	
	@Autowired
	private ImageProccessor imageProccessor;
	
	@Override
	public Paragraph convert(ImageExtend imageExtend) {
		Paragraph paragraph = null;
		if(null != imageExtend) {
			paragraph = new Paragraph();
			
			ParagProduct product = new ParagProduct();
			paragraph.setProduct(product);
			
			List<Imagebase> images = imageExtend.getImages();
			
			List<PhotoParag> photoList = null;
			if(null != images && !images.isEmpty()) {
				photoList = new ArrayList<>(images.size());
				paragraph.setPhotoList(photoList);
				for(Imagebase image : images) {
					ImageRef ref = image.getImageRef();
					
					PhotoParag photoParag = new PhotoParag();
					photoList.add(photoParag);
					Photo photo = new Photo();
					photoParag.setPhoto(photo);
					
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
					
					photoParag.setFullurl(photo.getFullurl());
					photoParag.setExpectedUrl(ref.getExpectedUrl());//缩略图
					photoParag.setParagId(ref.getSubId());
					photoParag.setId(ref.getId());//指定id与ref.id相同
					photoParag.setPhotoId(photo.getId());//指定photoId
					photoParag.setOrderSeq(ref.getOrderSeq());//排序
					photoParag.setWidth(image.getWidth());
					photoParag.setName(photo.getName());//冗余
					photoParag.setTitle(photo.getTitle());//冗余
					photoParag.setCreatedBy(ref.getCreatedBy());
					photoParag.setLastModifiedBy(ref.getLastModifiedBy());
					if(product.getParagId()==null) {
						product.setParagId(ref.getSubId());
						product.setMainId(ref.getMainId());
					}
					photoParag.setUserId(ref.getUserId());
					
				}
			}
		}
		return paragraph;
	}

	@Override
	public boolean persist(Paragraph t) {
		Paragraph entity = paragraphWrapper.persistForPhoto(t);
		if(null != entity && Paragraph.SUCCESS_YES==entity.getSuccess()) {
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
		
		PhotoParag photoParag = new PhotoParag();
		photoParag.setPhoto(photo);
		
		photoParag.setId(ref.getId());
		photoParag.setPhotoId(baseId);
		photoParag.setParagId(ref.getSubId());
		photoParag.setOrderSeq(ref.getOrderSeq());
		photoParag.setName(ref.getName());
		photoParag.setTitle(ref.getTitle());
		photoParag.setLastModifiedBy(ref.getLastModifiedBy());
		PhotoParag entity= photoParagWrapper.mergeForTitle(photoParag);
		if(null != entity && Paragraph.SUCCESS_YES==entity.getSuccess()) {
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
		
		PhotoParag photoParag = new PhotoParag();
		photoParag.setPhoto(photo);
		
		photoParag.setId(ref.getId());
		photoParag.setPhotoId(baseId);
		photoParag.setParagId(ref.getSubId());
		photoParag.setUserId(ref.getUserId());
		photoParag.setLastModifiedBy(ref.getLastModifiedBy());
		photoParagWrapper.deleteById(photoParag);
		
		
		return true;
	}

	@Override
	public boolean deleteImageList(List<ImageRef> refList) {
		return false;
	}
	
	public boolean deleteFromRemote(ParagProduct paragProduct) {
		
		Long paragId = paragProduct.getParagId();
		Long productId = paragProduct.getMainId();
		ImageRequest imageRequest = new ImageRequest();
		imageRequest.setDevId("sz0099");
		imageRequest.setProject("ood");
		imageRequest.setModule("product");
		imageRequest.setVariety("product");
		imageRequest.setPosition("paragragh");
		imageRequest.setMainId(productId);
		imageRequest.setSubId(paragId);
		imageRequest.setUserId(paragProduct.getUserId());
		boolean result = imageProccessor.deleteByRequest(imageRequest);
		return result;
	}

}
