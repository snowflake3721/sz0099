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
import dml.sz0099.course.app.client.wrapper.product.CoeUserImageWrapper;
import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.entity.paragraph.Paragraph;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserImage;
import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;

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

public class PersonalSingleAdaptor extends ImageDefaultAdaptor<CoeUser>{

	@Autowired
	private CoeUserWrapper userWrapper;
	
	@Autowired
	private CoeUserImageWrapper userImageWrapper;
	
	@Autowired
	private ImageProccessor imageProccessor;
	
	@Override
	public CoeUser convert(ImageExtend imageExtend) {
		CoeUser user = null;
		if(null != imageExtend) {
			user = new CoeUser();
			List<Imagebase> images = imageExtend.getImages();
			if(null != images && !images.isEmpty()) {
				
				List<CoeUserImage> userImages = new ArrayList<>(images.size());
				user.setImages(userImages);
				for(Imagebase image : images) {
					ImageRef ref = image.getImageRef();
					
					CoeUserImage coeUserImage = new CoeUserImage();
					userImages.add(coeUserImage);
					
					coeUserImage.setId(ref.getId());
					coeUserImage.setUserId(ref.getUserId());
					
					String fullurl = PhotoUtil.getFullUrl(imageExtend.getDomain(), ref.getViewUrl());
					Long subId = ref.getSubId();
					coeUserImage.setSubId(subId);
					coeUserImage.setFullurl(fullurl);
					coeUserImage.setCoeUserId(ref.getMainId());//mainId
					
					user.setSubId(subId);
					if(CoeUserImage.SUBIDLIST_USER.contains(subId)) {
						if(CoeUserImage.SUBID_HEADIMAGE.equals(subId)) {
							user.setHeadImg(fullurl);
						}else if(CoeUserImage.SUBID_PAY_RECIEVE.equals(subId)) {
							user.setPayRecieveImg(fullurl);
						}
					}else if(CoeUserImage.SUBIDLIST_VERIFY.contains(subId)) {
						CoeUserVerify verify = new CoeUserVerify();
						user.setUserVerify(verify);
						verify.setUserId(ref.getMainId());
						verify.setLastModifiedBy(ref.getLastModifiedBy());
						if(CoeUserImage.SUBID_IDENTITY_FACE.equals(subId)) {
							verify.setIdentityFace(fullurl);
						}else if(CoeUserImage.SUBID_IDENTITY_BACK.equals(subId)) {
							verify.setIdentityBack(fullurl);
						} 
					}
					user.setLastModifiedBy(ref.getLastModifiedBy());
					user.setId(ref.getMainId());
					user.setUserId(ref.getUserId());
				}
			}
		}
		return user;
	}

	@Override
	public boolean persist(CoeUser t) {
		CoeUser entity = userWrapper.mereForImage(t);
		if(null != entity && Paragraph.SUCCESS_YES==entity.getSuccess()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean mergeImage(ImageRef ref) {
		
		return true;
	}

	@Override
	public boolean deleteImage(ImageRef ref) {
		
		Long userId = ref.getUserId();
		Long mainId = ref.getMainId();
		Long subId = ref.getSubId();
		Long refId = ref.getId();
		
		CoeUserImage userImage = new CoeUserImage();
		userImage.setId(refId);
		userImage.setSubId(subId);
		userImage.setUserId(userId);
		userImage.setCoeUserId(mainId);
		userImage.setLastModifiedBy(userId);
		userImageWrapper.deleteImage(userImage);
		
		
		
		return true;
	}

	@Override
	public boolean deleteImageList(List<ImageRef> refList) {
		return false;
	}
	
	public boolean deleteFromRemote(CoeUser user) {
		
		Long subId = user.getSubId();
		Long mainId = user.getUserId();
		ImageRequest imageRequest = new ImageRequest();
		imageRequest.setDevId("sz0099");
		imageRequest.setProject("ood");
		imageRequest.setModule("product");
		imageRequest.setVariety("personal");
		imageRequest.setPosition("identity");
		imageRequest.setMainId(mainId);
		imageRequest.setSubId(subId);
		imageRequest.setUserId(user.getUserId());
		boolean result = imageProccessor.deleteByRequest(imageRequest);
		return result;
	}

}
