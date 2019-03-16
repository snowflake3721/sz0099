package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;

/**
 * ProfessionExtendDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionExtendDao extends GenericDao<ProfessionExtend,Long>{

	/**
	 * 根据Id查询ProfessionExtend实体对象
	 * @param id
	 * @return
	 */
	ProfessionExtend findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ProfessionExtend findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionExtend实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionExtend> findByIdList(List<Long> idList);
	
	public Page<ProfessionExtend> findPage(ProfessionExtend professionExtend, Pageable pageable);
	
	public ProfessionExtend findByPositionId(Long positionId) ;
	
	public ProfessionExtend findProfessionExtend(ProfessionExtend extend);

	public Long findPositionIdById(Long id);
	
	public Long countByUserId(Long userId) ;
	
}
