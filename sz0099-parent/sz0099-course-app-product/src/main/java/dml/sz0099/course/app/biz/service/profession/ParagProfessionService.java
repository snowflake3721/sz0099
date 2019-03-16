package dml.sz0099.course.app.biz.service.profession;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ParagProfession;

/**
 * ParagProfessionService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ParagProfessionService extends GenericService<ParagProfession,Long>{


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
	 * @param paragProduct
	 * @return
	 */
	public ParagProfession persistEntity(ParagProfession paragProduct) ;
	
	
	public ParagProfession mergeEntity(ParagProfession paragProduct) ; 
	
	public ParagProfession saveOrUpdate(ParagProfession paragProduct) ;
	
	public Page<ParagProfession> findPage(ParagProfession paragProduct, Pageable pageable) ;
	


	public Page<ParagProfession> findByMainId(Long professionId, Pageable pageable );
	public Page<ParagProfession> findByMainIdAndUserId(Long professionId, Long userId, Pageable pageable );
	public Page<ParagProfession> resetOrderSeq(Long professionId, Long userId );
	
	
	public void deleteByProfessionIdAndUserId(Long professionId, Long userId );
	public ParagProfession createParagProfession(ParagProfession paragProduct);
	
	public Long countByMainId(Long professionId) ;
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade );


}
