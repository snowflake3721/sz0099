/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.media;

import java.util.List;

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

public interface ImageAdaptor<T> {
	
	ImageExtend config(ImageExtend extend);

	T convert(ImageExtend imageExtend);
	
	boolean persist(T t);
	
	boolean mergeImage(ImageRef ref);
	
	boolean deleteImage(ImageRef ref);
	
	boolean deleteImageList(List<ImageRef> refList);
}
