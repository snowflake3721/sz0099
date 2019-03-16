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
import dml.sz0099.course.app.client.wrapper.activity.PhotoBannerWrapper;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.entity.activity.Photo;
import dml.sz0099.course.app.persist.entity.activity.PhotoBanner;
import dml.sz0099.course.app.persist.entity.activity.PhotoBanner;
import dml.sz0099.course.app.persist.entity.activity.PhotoBanner;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;

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

public class BannerActivityAdaptor extends ImageDefaultAdaptor<CoeActivity>{

	@Autowired
	private CoeActivityWrapper activityWrapper;
	
	@Autowired
	private PhotoBannerWrapper photoBannerWrapper;
	
	@Autowired
	private ImageProccessor imageProccessor;
	
	
	private String basePath;

	private String basePathFolder;

	private String accessUrlBasePath;
	
	private String accessUrlMapping;
	
	@Override
	public CoeActivity convert(ImageExtend imageExtend) {
		CoeActivity activity = null;
		if(null != imageExtend) {
			activity = new CoeActivity();
			
			List<Imagebase> images = imageExtend.getImages();
			
			List<PhotoBanner> bannerList = null;
			if(null != images && !images.isEmpty()) {
				bannerList = new ArrayList<>(images.size());
				activity.setBannerList(bannerList);
				for(Imagebase image : images) {
					ImageRef ref = image.getImageRef();
					
					PhotoBanner photoBanner = new PhotoBanner();
					bannerList.add(photoBanner);
					Photo photo = new Photo();
					photoBanner.setPhoto(photo);
					
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
					
					photoBanner.setFullurl(photo.getFullurl());
					photoBanner.setExpectedUrl(ref.getExpectedUrl());//缩略图
					photoBanner.setSubId(ref.getSubId());
					photoBanner.setId(ref.getId());//指定id与ref.id相同
					photoBanner.setPhotoId(photo.getId());//指定photoId
					photoBanner.setOrderSeq(ref.getOrderSeq());//排序
					photoBanner.setWidth(image.getWidth());
					//photoBanner.setName(photo.getName());//冗余
					photoBanner.setTitle(photo.getTitle());//冗余
					photoBanner.setDescription(ref.getDescription());
					photoBanner.setCreatedBy(ref.getCreatedBy());
					photoBanner.setLastModifiedBy(ref.getLastModifiedBy());
					
					photoBanner.setSubId(ref.getSubId());
					photoBanner.setMainId(ref.getMainId());
					
					if(activity.getId()==null) {
						activity.setId(ref.getMainId());
					}
					photoBanner.setUserId(ref.getUserId());
					
				}
			}
		}
		return activity;
	}

	@Override
	public boolean persist(CoeActivity t) {
		CoeActivity entity = activityWrapper.persistForBanner(t);
		if(null != entity && CoeActivity.SUCCESS_YES==entity.getSuccess()) {
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
		//photo.setDescription(ref.getDescription());
		photo.setOrderSeq(ref.getOrderSeq());
		photo.setLastModifiedBy(ref.getLastModifiedBy());
		
		PhotoBanner photoBanner = new PhotoBanner();
		photoBanner.setPhoto(photo);
		
		photoBanner.setId(ref.getId());
		photoBanner.setPhotoId(baseId);
		photoBanner.setSubId(ref.getSubId());
		photoBanner.setMainId(ref.getMainId());
		
		photoBanner.setOrderSeq(ref.getOrderSeq());
		//photoBanner.setName(ref.getName());
		photoBanner.setDescription(ref.getDescription());
		photoBanner.setTitle(ref.getTitle());
		photoBanner.setLastModifiedBy(ref.getLastModifiedBy());
		PhotoBanner entity= photoBannerWrapper.mergeForTitle(photoBanner);
		if(null != entity && CoeActivity.SUCCESS_YES==entity.getSuccess()) {
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
		Long activityId = photoBanner.getMainId();
		ImageRequest imageRequest = new ImageRequest();
		imageRequest.setDevId("sz0099");
		imageRequest.setProject("ood");
		imageRequest.setModule("activity");
		imageRequest.setVariety("activity");
		imageRequest.setPosition("banner");
		imageRequest.setMainId(activityId);
		imageRequest.setSubId(subId);
		imageRequest.setUserId(photoBanner.getUserId());
		boolean result = imageProccessor.deleteByRequest(imageRequest);
		return result;
	}
	
	public ImageExtend config(Long userId) {
		if(this.configExtend==null) {
			config(true,userId);
		}
		return configExtend;
	}
	
	public ImageExtend config() {
		if(this.configExtend==null) {
			config(true,1l);
		}
		return configExtend;
	}
	public ImageExtend config(boolean refresh,Long userId) {
		if(this.configExtend==null || refresh) {
			ImageExtend extend = new ImageExtend();
			extend.setUserId(userId);
			extend.setCreatedBy(userId);
			extend.setLastModifiedBy(userId);
			extend.setPosition("banner");
			extend.setDevId("sz0099");
			extend.setDomain("http://m.dramala.com");
			extend.setModule("activity");
			extend.setProject("ood");
			extend.setVariety("activity");
			extend=super.findExtend(extend);
			super.config(extend);
		}
		return configExtend;
	}
	
	public PhotoBanner convertFromRef(ImageRef ref) {
		//ImageRef ref = image.getImageRef();
		Imagebase image = ref.getBase();
		PhotoBanner photoBanner = new PhotoBanner();
		Photo photo = new Photo();
		photoBanner.setPhoto(photo);
		
		photo.setAbsolute(image.getAbsolute());
		photo.setAccessUrl(image.getViewUrl());
		//photo.setDescription(ref.getDescription());//描述
		photo.setDomain(image.getDomain());
		photo.setFilename(image.getFilename());
		String accessUrl = photo.getAccessUrl();
		if(StringUtils.isNotBlank(accessUrl)) {
			photo.setFullurl(PhotoUtil.getFullUrl(photo.getDomain(), photo.getAccessUrl()));
		}
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
		photo.setStrategy(image.getStrategy());
		photo.setViewUrl(image.getViewUrl());
		photo.setSuffix(image.getSuffix());
		photo.setOriginal(image.getOriginal());
		photo.setOrderSeq(image.getOrderSeq());
		photo.setContentType(image.getContentType());
		
		
		photoBanner.setFullurl(photo.getFullurl());
		photoBanner.setExpectedUrl(ref.getExpectedUrl());//缩略图
		photoBanner.setExpectedW(ref.getExpectedW());//期望宽度
		photoBanner.setType(ref.getType());
		photoBanner.setSubId(ref.getSubId());
		photoBanner.setId(ref.getId());//指定id与ref.id相同
		photoBanner.setPhotoId(photo.getId());//指定photoId
		photoBanner.setOrderSeq(ref.getOrderSeq());//排序
		photoBanner.setWidth(image.getWidth());
		//photoBanner.setName(photo.getName());//冗余
		photoBanner.setTitle(photo.getTitle());//冗余
		photoBanner.setDescription(ref.getDescription());
		photoBanner.setCreatedBy(ref.getCreatedBy());
		photoBanner.setLastModifiedBy(ref.getLastModifiedBy());
		photoBanner.setMainId(ref.getMainId());
		photoBanner.setUserId(ref.getUserId());
		photoBanner.setOrderSeq(ref.getOrderSeq());
		return photoBanner;
	}

	public CoeActivityWrapper getActivityWrapper() {
		return activityWrapper;
	}

	public void setActivityWrapper(CoeActivityWrapper activityWrapper) {
		this.activityWrapper = activityWrapper;
	}

	public PhotoBannerWrapper getPhotoBannerWrapper() {
		return photoBannerWrapper;
	}

	public void setPhotoBannerWrapper(PhotoBannerWrapper photoBannerWrapper) {
		this.photoBannerWrapper = photoBannerWrapper;
	}

	public ImageProccessor getImageProccessor() {
		return imageProccessor;
	}

	public void setImageProccessor(ImageProccessor imageProccessor) {
		this.imageProccessor = imageProccessor;
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
