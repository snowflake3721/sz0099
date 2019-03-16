package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ParagProfession;

/**
 * ParagProfessionDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ParagProfessionDao extends GenericDao<ParagProfession,Long>{

	/**
	 * 根据Id查询ParagProfession实体对象
	 * @param id
	 * @return
	 */
	ParagProfession findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ParagProfession findByAid(Long aid);
	
	/**
	 * 根据IdList查询ParagProfession实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ParagProfession> findByIdList(List<Long> idList);
	
	public Page<ParagProfession> findPage(ParagProfession paragProduct, Pageable pageable);
	public List<ParagProfession> findListByMainId(Long professionId);
	public List<ParagProfession> findListByMainIdAndUserId(Long professionId,Long userId);


	public Page<ParagProfession> findByMainId(Long professionId, Pageable pageable );
	public Page<ParagProfession> findByMainIdAndUserId(Long professionId, Long userId, Pageable pageable );



	public void deleteByProfessionIdAndUserId(Long professionId, Long userId );
	public Long countByMainId(Long professionId);
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade );

}
