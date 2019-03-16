package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;

import org.jit4j.core.persist.page.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.position.PositionRef;


/**
 * ProfessionPositionWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionPositionWrapper {

	/**
	 * 根据Id查询ProfessionPosition实体对象
	 * @param id
	 * @return
	 */
	public ProfessionPosition findById(Long id);
	
	public boolean existById(Long id);
	
	public ProfessionPosition findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionPosition实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionPosition> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionPosition
	 * @return
	 */
	public ProfessionPosition persistEntity(ProfessionPosition professionPosition) ;
	
	public ProfessionPosition mergeEntity(ProfessionPosition professionPosition) ; 
	
	public ProfessionPosition saveOrUpdate(ProfessionPosition professionPosition) ;
	
	public Page<ProfessionPosition> findPage(ProfessionPosition professionPosition, Pageable pageable) ; 
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId);
	public List<ProfessionPosition> findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionPosition> findByMainId(Long mainId, Pageable pageable) ; 
	public ProfessionPosition bindPosition(ProfessionPosition professionPosition);
	public ProfessionPosition addPositionRef(ProfessionPosition professionPosition);
	
	public ProfessionPosition findProfessionByMainId(Long mainId);
	
	public List<ProfessionPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList);
	public PageResult<ProfessionPosition> findPublishedForSelect(ProfessionPosition professionPosition, Pageable pageable);
	
	public void deleteById(ProfessionPosition professionPosition);
	public void deletePositionRef(ProfessionPosition professionPosition);
	public ProfessionPosition mergePositionRef(ProfessionPosition professionPosition) ;
	public ProfessionPosition openPositionRef(ProfessionPosition professionPosition);
	public ProfessionPosition mergeSimpleSingle(ProfessionPosition positionRef);
	public ProfessionPosition deleteRefByBaseId(ProfessionPosition positionRef);
	public List<ProfessionPosition> findByBaseId(ProfessionPosition positionRef);
	
	//public Page<ProfessionPosition> findPage(ProfessionPosition professionPosition, Pageable pageable) ;
	
	public Page<ProfessionPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable);
	public Page<ProfessionPosition> findByBaseId(Long baseId, Pageable pageable) ;
	public Page<ProfessionPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable) ;
	
}
