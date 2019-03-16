/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.position;

import java.util.List;
import java.util.Map;

import org.jit4j.core.persist.page.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionRef;

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

public interface PositionAdaptor<T> {
	
	PositionExtend config(PositionExtend extend);

	T convert(PositionExtend positionExtend);
	
	boolean persist(T t);
	
	List<Position> findPosition(Long ponMainId, Long ponSubId, Long positionId, Integer ponPanel );
	
	
	
	
	
	
	boolean deletePositionList(List<PositionRef> refList);
	
	Position findSingle(PositionRef ref);
	
	//增加关联相关
	T findByMainId(PositionRef positionRef);
	PositionRef fillPositionRef(T t,PositionRef positionRef);
	T convert(PositionRef positionRef);
	boolean addPositionRef(PositionRef ref, T t);
	
	/**
	 * 移除关联
	 * @param ref
	 * @return
	 */
	boolean deletePositionRef(PositionRef ref);
	/**
	 * 开闭关联
	 * @param ref
	 * @return
	 */
	boolean openPositionRef(PositionRef ref);
	
	boolean mergeSimpleSingle(PositionRef ref);
	boolean deleteRefByBaseId(PositionRef ref);
	
	
	boolean mergePositionRef(PositionRef ref);
	
	
	Map<Long,Position> findMap(List<Long> idList);
	
	PageResult<T> queryByViewType(PositionRef position, Pageable pageable);
	
	
	Page<T> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable);
	
}
