/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.profession;

import org.jit4j.core.persist.page.PageResult;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;

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

public interface ProfessionRefHandler<T> {
	
	PageResult<T> queryPage(ProfessionRef profession, Pageable pageable);
	
	public T findByMainId(ProfessionRef professionRef);
	
	public ProfessionRef fillProfessionRef(T t, ProfessionRef professionRef) ;
	

}
