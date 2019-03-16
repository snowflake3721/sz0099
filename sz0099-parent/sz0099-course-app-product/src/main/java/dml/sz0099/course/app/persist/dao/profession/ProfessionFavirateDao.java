package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionFavirate;

/**
 * ProfessionFavirateDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionFavirateDao extends GenericDao<ProfessionFavirate,Long>{

	/**
	 * 根据Id查询ProfessionFavirate实体对象
	 * @param id
	 * @return
	 */
	ProfessionFavirate findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ProfessionFavirate findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionFavirate实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionFavirate> findByIdList(List<Long> idList);
	
	public Page<ProfessionFavirate> findPage(ProfessionFavirate professionFavirate, Pageable pageable);
	
	public boolean existByMainIdAndUserId(Long mainId, Long userId);
	public ProfessionFavirate findByMainIdAndUserId(Long mainId, Long userId);
	public Page<ProfessionFavirate> findByMainId(Long mainId, Pageable pageable) ; 
	
}
