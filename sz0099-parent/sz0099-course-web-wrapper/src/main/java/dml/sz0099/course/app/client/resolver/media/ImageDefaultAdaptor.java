/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.media;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.wrapper.media.ImageExtendWrapper;
import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.ImageRef;

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

public class ImageDefaultAdaptor<T> implements ImageAdaptor<T>{
	
	@Autowired
	private ImageExtendWrapper imageExtendWrapper;

	protected ImageExtend configExtend;
	
	@Override
	public T convert(ImageExtend imageExtend) {
		return null;
	}

	@Override
	public boolean persist(T t) {
		return false;
	}

	@Override
	public boolean mergeImage(ImageRef ref) {
		return false;
	}

	@Override
	public boolean deleteImage(ImageRef ref) {
		return false;
	}

	@Override
	public boolean deleteImageList(List<ImageRef> refList) {
		return false;
	}
	
	public ImageExtend findExtend(ImageExtend extend) {
		ImageExtend entity  = imageExtendWrapper.findImageExtend(extend);
		if(null == entity) {
			imageExtendWrapper.persistEntity(extend);//不存在就创建
		}
		return entity;
	}
	
	public ImageExtend config() {
		
		//子类override
		return this.configExtend;
	}

	@Override
	public ImageExtend config(ImageExtend extend) {
		if(null != extend && getConfigExtend() == null) {
			this.configExtend = new ImageExtend();
			this.configExtend.setId(extend.getId());
			this.configExtend.setAid(extend.getAid());
			this.configExtend.setDevId(extend.getDevId());
			//this.configExtend.setDepthMaxnum(extend.getDepthMaxnum());
			this.configExtend.setDomain(extend.getDomain());
			this.configExtend.setMainMaxnum(extend.getMainMaxnum());
			this.configExtend.setModule(extend.getModule());
			this.configExtend.setOrderSeq(extend.getOrderSeq());
			this.configExtend.setPosition(extend.getPosition());
			this.configExtend.setPositionId(extend.getPositionId());
			this.configExtend.setProject(extend.getProject());
			//this.configExtend.setRefMaxnum(extend.getRefMaxnum());
			this.configExtend.setSubMaxnum(extend.getSubMaxnum());
			this.configExtend.setUserId(extend.getUserId());
			this.configExtend.setVariety(extend.getVariety());
			this.configExtend.setSizeMax(extend.getSizeMax());
		}
		return getConfigExtend();
	}
	
	public ImageExtend getConfigExtend() {
		return configExtend;
	}

	public void setConfigExtend(ImageExtend configExtend) {
		this.configExtend = configExtend;
	}

}
