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
import dml.sz0099.course.app.client.wrapper.profession.PhotoCoverWrapper;
import dml.sz0099.course.app.client.wrapper.profession.ProfessionWrapper;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.entity.profession.Photo;
import dml.sz0099.course.app.persist.entity.profession.PhotoCover;
import dml.sz0099.course.app.persist.entity.profession.Profession;

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

public class CoverProfessionAdaptor extends ImageDefaultAdaptor<Profession>{

	@Autowired
	private ProfessionWrapper professionWrapper;
	
	@Autowired
	private PhotoCoverWrapper photoCoverWrapper;
	
	@Autowired
	private ImageProccessor imageProccessor;
	
	private String basePath;

	private String basePathFolder;

	private String accessUrlBasePath;
	
	private String accessUrlMapping;
	
	@Override
	public Profession convert(ImageExtend imageExtend) {
		Profession profession = null;
		if(null != imageExtend) {
			profession = new Profession();
			
			List<Imagebase> images = imageExtend.getImages();
			
			List<PhotoCover> coverList = null;
			if(null != images && !images.isEmpty()) {
				coverList = new ArrayList<>(images.size());
				profession.setCoverList(coverList);
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
					//photoCover.setName(photo.getName());//冗余
					photoCover.setTitle(photo.getTitle());//冗余
					photoCover.setCreatedBy(ref.getCreatedBy());
					photoCover.setLastModifiedBy(ref.getLastModifiedBy());
					
					photoCover.setSubId(ref.getSubId());
					photoCover.setMainId(ref.getMainId());
					
					if(profession.getId()==null) {
						profession.setId(ref.getMainId());
					}
					photoCover.setUserId(ref.getUserId());
					
				}
			}
		}
		return profession;
	}

	@Override
	public boolean persist(Profession t) {
		Profession entity = professionWrapper.persistForCover(t);
		if(null != entity && Profession.SUCCESS_YES==entity.getSuccess()) {
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
		//photoCover.setName(ref.getName());
		photoCover.setTitle(ref.getTitle());
		photoCover.setLastModifiedBy(ref.getLastModifiedBy());
		PhotoCover entity= photoCoverWrapper.mergeForTitle(photoCover);
		if(null != entity && Profession.SUCCESS_YES==entity.getSuccess()) {
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
		imageRequest.setModule("personal");
		imageRequest.setVariety("profession");
		imageRequest.setPosition("cover");
		imageRequest.setMainId(productId);
		imageRequest.setSubId(subId);
		imageRequest.setUserId(photoCover.getUserId());
		boolean result = imageProccessor.deleteByRequest(imageRequest);
		return result;
	}
	
	public ImageExtend config() {
		if(this.configExtend==null) {
			config(true);
		}
		return configExtend;
	}
	public ImageExtend config(boolean refresh) {
		if(this.configExtend==null || refresh) {
			ImageExtend extend = new ImageExtend();
			extend.setPosition("cover");
			extend.setDevId("sz0099");
			extend.setDomain("http://m.dramala.com");
			extend.setModule("personal");
			extend.setProject("ood");
			extend.setVariety("profession");
			extend=super.findExtend(extend);
			super.config(extend);
		}
		return configExtend;
	}
	
	public PhotoCover convertFromRef(ImageRef ref) {
		//ImageRef ref = image.getImageRef();
		Imagebase image = ref.getBase();
		PhotoCover photoCover = new PhotoCover();
		Photo photo = new Photo();
		photoCover.setPhoto(photo);
		
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
		
		
		photoCover.setFullurl(photo.getFullurl());
		photoCover.setExpectedUrl(ref.getExpectedUrl());//缩略图
		photoCover.setExpectedW(ref.getExpectedW());//期望宽度
		photoCover.setType(ref.getType());
		photoCover.setSubId(ref.getSubId());
		photoCover.setId(ref.getId());//指定id与ref.id相同
		photoCover.setPhotoId(photo.getId());//指定photoId
		photoCover.setOrderSeq(ref.getOrderSeq());//排序
		photoCover.setWidth(image.getWidth());
		//photoCover.setName(photo.getName());//冗余
		photoCover.setTitle(photo.getTitle());//冗余
		photoCover.setDescription(ref.getDescription());
		photoCover.setCreatedBy(ref.getCreatedBy());
		photoCover.setLastModifiedBy(ref.getLastModifiedBy());
		photoCover.setMainId(ref.getMainId());
		photoCover.setUserId(ref.getUserId());
		photoCover.setOrderSeq(ref.getOrderSeq());
		return photoCover;
	}

	public ProfessionWrapper getProfessionWrapper() {
		return professionWrapper;
	}

	public void setProfessionWrapper(ProfessionWrapper professionWrapper) {
		this.professionWrapper = professionWrapper;
	}

	public PhotoCoverWrapper getPhotoCoverWrapper() {
		return photoCoverWrapper;
	}

	public void setPhotoCoverWrapper(PhotoCoverWrapper photoCoverWrapper) {
		this.photoCoverWrapper = photoCoverWrapper;
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
