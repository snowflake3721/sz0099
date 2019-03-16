/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.profession;

import java.util.List;
import java.util.Map;

import org.jit4j.core.persist.page.PageResult;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;

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

public interface ProfessionAdaptor<T> {
	
	ProfessionExtend config(ProfessionExtend extend);

	T convert(ProfessionExtend professionExtend);
	
	boolean persist(T t);
	
	
	
	
	
	
	boolean deleteProfessionList(List<ProfessionRef> refList);
	
	Profession findSingle(ProfessionRef ref);
	
	//增加关联相关
	T findByMainId(ProfessionRef professionRef);
	ProfessionRef fillProfessionRef(T t,ProfessionRef professionRef);
	T convert(ProfessionRef professionRef);
	boolean addProfessionRef(ProfessionRef ref, T t);
	
	/**
	 * 移除关联
	 * @param ref
	 * @return
	 */
	boolean deleteProfessionRef(ProfessionRef ref);
	/**
	 * 开闭关联
	 * @param ref
	 * @return
	 */
	boolean openProfessionRef(ProfessionRef ref);
	
	boolean mergeSimpleSingle(ProfessionRef ref);
	boolean deleteRefByBaseId(ProfessionRef ref);
	
	
	boolean mergeProfessionRef(ProfessionRef ref);
	
	
	Map<Long,Profession> findMap(List<Long> idList);
	
	PageResult<ProfessionRef> queryByViewType(ProfessionRef profession, Pageable pageable);
	
	
}
