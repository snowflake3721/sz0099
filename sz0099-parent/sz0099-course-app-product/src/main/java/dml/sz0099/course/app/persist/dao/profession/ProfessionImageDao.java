package dml.sz0099.course.app.persist.dao.profession;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;

/**
 * ProfessionImageDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionImageDao extends GenericDao<ProfessionImage,Long>{

	/**
	 * 根据Id查询ProfessionImage实体对象
	 * @param id
	 * @return
	 */
	ProfessionImage findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	ProfessionImage findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionImage实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionImage> findByIdList(List<Long> idList);
	
	public Page<ProfessionImage> findPage(ProfessionImage professionImage, Pageable pageable);
	
	public List<ProfessionImage> findByRefIdList(List<Long> refIdList);
	
	public void deleteByRefIdList(List<Long> refIdList) ;
	public void deleteByRefId(Long refId);
	public List<ProfessionImage> findByRefId(Long refId);
}
