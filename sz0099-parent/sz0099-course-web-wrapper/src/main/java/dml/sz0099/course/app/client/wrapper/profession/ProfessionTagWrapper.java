package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionTag;
import dml.sz0099.course.app.persist.entity.profession.ProfessionTag;


/**
 * ProfessionTagWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionTagWrapper {

	/**
	 * 根据Id查询ProfessionTag实体对象
	 * @param id
	 * @return
	 */
	public ProfessionTag findById(Long id);
	
	public ProfessionTag findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionTag实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionTag> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionTag
	 * @return
	 */
	public ProfessionTag persistEntity(ProfessionTag professionTag) ;
	
	public ProfessionTag mergeEntity(ProfessionTag professionTag) ; 
	
	public ProfessionTag saveOrUpdate(ProfessionTag professionTag) ;
	
	public Page<ProfessionTag> findPage(ProfessionTag professionTag, Pageable pageable) ; 
	
	public ProfessionTag findByMainIdAndName(ProfessionTag professionTag);
	public Long countByMainId(Long professionId);
	public List<ProfessionTag> findByMainId(Long professionId);
	
	public ProfessionTag addTag(ProfessionTag professionTag);
	public ProfessionTag deleteTag(ProfessionTag professionTag);
	public Map<Long,List<ProfessionTag>> findMapByMainIdList(List<Long> professionIdList);
}
