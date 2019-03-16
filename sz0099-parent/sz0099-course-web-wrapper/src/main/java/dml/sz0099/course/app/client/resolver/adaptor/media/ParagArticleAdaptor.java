/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.adaptor.media;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.core.pub.transfer.dto.ImageRequest;
import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.resolver.media.ImageDefaultAdaptor;
import dml.sz0099.course.app.client.resolver.media.ImageProccessor;
import dml.sz0099.course.app.client.resolver.media.PhotoUtil;
import dml.sz0099.course.app.client.wrapper.article.ParagraphWrapper;
import dml.sz0099.course.app.client.wrapper.article.PhotoParagWrapper;
import dml.sz0099.course.app.persist.entity.article.ParagArticle;
import dml.sz0099.course.app.persist.entity.article.Paragraph;
import dml.sz0099.course.app.persist.entity.article.Photo;
import dml.sz0099.course.app.persist.entity.article.PhotoParag;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;

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

public class ParagArticleAdaptor extends ImageDefaultAdaptor<Paragraph>{

	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	@Autowired
	private PhotoParagWrapper photoParagWrapper;
	
	@Autowired
	private ImageProccessor imageProccessor;
	
	private String basePath;

	private String basePathFolder;

	private String accessUrlBasePath;
	
	private String accessUrlMapping;
	
	public PhotoParag convertFromRef(ImageRef ref) {
		//ImageRef ref = image.getImageRef();
		Imagebase image = ref.getBase();
		PhotoParag photoParag = new PhotoParag();
		Photo photo = new Photo();
		photoParag.setPhoto(photo);
		
		photo.setAbsolute(image.getAbsolute());
		//photo.setDescription(ref.getDescription());//描述
		photo.setDomain(image.getDomain());
		photo.setFilename(image.getFilename());
		
		photo.setName(ref.getName());//名称
		photo.setOrderSeq(ref.getOrderSeq()); //排序
		photo.setRelative(image.getRelative());
		photo.setTitle(ref.getTitle());//标题
		photo.setUserId(image.getUserId());
		photo.setId(image.getId());//指定photo.id==Imagebase.id
		photo.setCreatedBy(image.getCreatedBy());
		photo.setLastModifiedBy(image.getLastModifiedBy());
		photo.setStrategy(image.getStrategy());
		photo.setOrderSeq(image.getOrderSeq());

		Integer type = ref.getType();
		if(ImageRef.TYPE_IMG.getValueInt().equals(type)) {
			photo.setAccessUrl(image.getViewUrl());
			String accessUrl = photo.getAccessUrl();
			if(StringUtils.isNotBlank(accessUrl)) {
				photo.setFullurl(PhotoUtil.getFullUrl(photo.getDomain(), photo.getAccessUrl()));
			}
			photo.setSize(image.getSize());
			photo.setWidth(image.getWidth());
			photo.setHeight(image.getHeight());
			photo.setViewUrl(image.getViewUrl());
			photo.setSuffix(image.getSuffix());
			photo.setOriginal(image.getOriginal());
			photo.setContentType(image.getContentType());
			
			photoParag.setFullurl(photo.getFullurl());
			photoParag.setExpectedUrl(ref.getExpectedUrl());//缩略图
			photoParag.setExpectedW(ref.getExpectedW());//期望宽度
			photoParag.setWidth(image.getWidth());
		}
		
		photoParag.setType(ref.getType());
		photoParag.setParagId(ref.getSubId());
		photoParag.setId(ref.getId());//指定id与ref.id相同
		photoParag.setPhotoId(photo.getId());//指定photoId
		photoParag.setOrderSeq(ref.getOrderSeq());//排序
		//photoParag.setName(photo.getName());//冗余
		photoParag.setTitle(photo.getTitle());//冗余
		photoParag.setDescription(ref.getDescription());
		photoParag.setCreatedBy(ref.getCreatedBy());
		photoParag.setLastModifiedBy(ref.getLastModifiedBy());
		photoParag.setMainId(ref.getMainId());
		photoParag.setUserId(ref.getUserId());
		photoParag.setOrderSeq(ref.getOrderSeq());
		return photoParag;
	}
	
	public ImageRef convertToRef(ImageRef ref, PhotoParag photoParag) {
		//ImageRef imageParag = photo.getImageRef();
		Photo photo = photoParag.getPhoto();
		if(null == ref) {
			ref = new ImageRef();
		}
		Imagebase image = ref.getBase();
		if(null == image) {
			image = new Imagebase();
			ref.setBase(image);
		}
		
		image.setAbsolute(photo.getAbsolute());
		image.setAccessUrl(photo.getViewUrl());
		//image.setDescription(imageParag.getDescription());//描述
		image.setDomain(photo.getDomain());
		image.setFilename(photo.getFilename());
		String accessUrl = image.getAccessUrl();
		if(StringUtils.isNotBlank(accessUrl)) {
			image.setAccessUrl(image.getAccessUrl());
		}
		//image.setName(photo.getName());//名称
		image.setOrderSeq(photoParag.getOrderSeq()); //排序
		image.setRelative(photo.getRelative());
		
		//image.setTitle(photo.getTitle());//标题
		image.setUserId(photo.getUserId());

		Long size=image.getSize();
		if(null == size) {
			size = photo.getSize();
		}
		image.setSize(size);
		
		Integer width = image.getWidth();
		if(null == width) {
			width = photo.getWidth();
		}
		image.setWidth(width);
		
		Integer height = image.getHeight();
		if(null == height) {
			height = photo.getHeight();
		}
		image.setHeight(height);
		
		String suffix = image.getSuffix();
		if(null == suffix) {
			suffix = photo.getSuffix();
		}
		image.setSuffix(suffix);
		
		String original = image.getOriginal();
		if(null == original) {
			original = photo.getOriginal();
		}
		image.setOriginal(original);
		String contentType = image.getContentType();
		if(null == contentType) {
			contentType = photo.getContentType();
		}
		image.setContentType(contentType);
		
		image.setId(photo.getId());//指定image.id==Imagebase.id
		image.setCreatedBy(photo.getCreatedBy());
		image.setLastModifiedBy(photo.getLastModifiedBy());
		image.setStrategy(photo.getStrategy());
		image.setViewUrl(photo.getViewUrl());
		
		image.setOrderSeq(photo.getOrderSeq());
		
		
		
		ref.setViewUrl(photo.getViewUrl());
		ref.setExpectedUrl(photoParag.getExpectedUrl());//缩略图
		ref.setExpectedW(photoParag.getExpectedW());//期望宽度
		ref.setType(photoParag.getType());
		ref.setSubId(photoParag.getParagId());
		ref.setId(photoParag.getId());//指定id与imageParag.id相同
		ref.setBaseId(photoParag.getId());//指定imageId
		ref.setOrderSeq(photoParag.getOrderSeq());//排序
		ref.setWidth(photo.getWidth());
		ref.setName(photo.getName());//冗余
		ref.setTitle(photo.getTitle());//冗余
		ref.setDescription(photoParag.getDescription());
		ref.setCreatedBy(photoParag.getCreatedBy());
		ref.setLastModifiedBy(photoParag.getLastModifiedBy());
		ref.setMainId(photoParag.getMainId());
		ref.setUserId(photoParag.getUserId());
		ref.setOrderSeq(photoParag.getOrderSeq());
		return ref;
	}
	
	
	
	
	
	@Override
	public Paragraph convert(ImageExtend imageExtend) {
		Paragraph paragraph = null;
		if(null != imageExtend) {
			paragraph = new Paragraph();
			
			ParagArticle article = new ParagArticle();
			paragraph.setArticle(article);
			
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
					//photo.setDescription(ref.getDescription());//描述
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
					//photoParag.setName(photo.getName());//冗余
					photoParag.setTitle(photo.getTitle());//冗余
					photoParag.setDescription(ref.getDescription());
					photoParag.setCreatedBy(ref.getCreatedBy());
					photoParag.setLastModifiedBy(ref.getLastModifiedBy());
					if(article.getParagId()==null) {
						article.setParagId(ref.getSubId());
						article.setMainId(ref.getMainId());
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
		//photo.setDescription(ref.getDescription());描述直接存到关联对象中，不往图片对象中存
		photo.setOrderSeq(ref.getOrderSeq());
		photo.setLastModifiedBy(ref.getLastModifiedBy());
		
		PhotoParag photoParag = new PhotoParag();
		photoParag.setPhoto(photo);
		
		photoParag.setId(ref.getId());
		photoParag.setPhotoId(baseId);
		photoParag.setParagId(ref.getSubId());
		photoParag.setOrderSeq(ref.getOrderSeq());
		//photoParag.setName(ref.getName());
		photoParag.setTitle(ref.getTitle());
		photoParag.setDescription(ref.getDescription());
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
	
	public boolean deleteFromRemote(ParagArticle paragArticle) {
		
		Long paragId = paragArticle.getParagId();
		Long productId = paragArticle.getMainId();
		ImageRequest imageRequest = new ImageRequest();
		imageRequest.setDevId("sz0099");
		imageRequest.setProject("ood");
		imageRequest.setModule("article");
		imageRequest.setVariety("article");
		imageRequest.setPosition("paragragh");
		imageRequest.setMainId(productId);
		imageRequest.setSubId(paragId);
		imageRequest.setUserId(paragArticle.getUserId());
		boolean result = imageProccessor.deleteByRequest(imageRequest);
		return result;
	}
	
	public ImageExtend config() {
		if(this.configExtend==null) {
			ImageExtend extend = new ImageExtend();
			extend.setPosition("paragragh");
			extend.setDevId("sz0099");
			extend.setDomain("http://m.dramala.com");
			extend.setModule("article");
			extend.setProject("ood");
			extend.setVariety("article");
			extend=super.findExtend(extend);
			super.config(extend);
		}
		return configExtend;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePathFolder() {
		return basePathFolder;
	}

	public void setBasePathFolder(String basePathFolder) {
		this.basePathFolder = basePathFolder;
	}

	public String getAccessUrlBasePath() {
		return accessUrlBasePath;
	}

	public void setAccessUrlBasePath(String accessUrlBasePath) {
		this.accessUrlBasePath = accessUrlBasePath;
	}

	public String getAccessUrlMapping() {
		return accessUrlMapping;
	}

	public void setAccessUrlMapping(String accessUrlMapping) {
		this.accessUrlMapping = accessUrlMapping;
	}



}
