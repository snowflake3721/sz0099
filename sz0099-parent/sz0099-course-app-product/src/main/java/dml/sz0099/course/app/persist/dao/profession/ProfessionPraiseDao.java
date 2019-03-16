package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPraise;

/**
 * ProfessionPraiseDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionPraiseDao extends GenericDao<ProfessionPraise,Long>{

	/**
	 * 根据Id查询ProfessionPraise实体对象
	 * @param id
	 * @return
	 */
	ProfessionPraise findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ProfessionPraise findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionPraise实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionPraise> findByIdList(List<Long> idList);
	
	public Page<ProfessionPraise> findPage(ProfessionPraise professionPraise, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionPraise findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionPraise> findByMainId(Long mainId, Pageable pageable) ; 
}
