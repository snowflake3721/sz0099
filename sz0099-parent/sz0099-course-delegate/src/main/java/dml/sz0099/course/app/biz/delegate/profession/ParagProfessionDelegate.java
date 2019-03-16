package dml.sz0099.course.app.biz.delegate.profession;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ParagProfession;

/**
 * ParagProfessionDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ParagProfessionDelegate {

	/**
	 * 根据Id查询ParagProfession实体对象
	 * @param id
	 * @return
	 */
	public ParagProfession findById(Long id);
	
	public boolean existById(Long id);
	
	public ParagProfession findByAid(Long aid);

	/**
	 * 根据IdList查询ParagProfession实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ParagProfession> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param paragProfession
	 * @return
	 */
	public ParagProfession persistEntity(ParagProfession paragProfession) ;
	
	public ParagProfession mergeEntity(ParagProfession paragProfession) ; 
	
	public ParagProfession saveOrUpdate(ParagProfession paragProfession) ;
	
	public Page<ParagProfession> findPage(ParagProfession paragProfession, Pageable pageable) ;
	

	public Page<ParagProfession> findByMainId(Long professionId, Pageable pageable );
	public Page<ParagProfession> findByMainIdAndUserId(Long professionId, Long userId, Pageable pageable );
	public Page<ParagProfession> resetOrderSeq(Long professionId, Long userId );
	
	public void deleteByProfessionIdAndUserId(Long professionId, Long userId );

	public ParagProfession createParagProfession(ParagProfession paragProfession);
	
	public Long countByMainId(Long professionId) ;
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade );

}
