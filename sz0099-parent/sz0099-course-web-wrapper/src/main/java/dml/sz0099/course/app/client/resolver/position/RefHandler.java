/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.position;

import org.jit4j.core.persist.page.PageResult;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.position.PositionRef;

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

public interface RefHandler<T> {
	
	PageResult<T> queryPage(PositionRef position, Pageable pageable);
	
	public T findByMainId(PositionRef positionRef);
	
	public PositionRef fillPositionRef(T t, PositionRef positionRef) ;
	

}
