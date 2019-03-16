package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.CategProfession;


/**
 * CategProfessionWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CategProfessionWrapper {

	/**
	 * 根据Id查询CategProfession实体对象
	 * @param id
	 * @return
	 */
	public CategProfession findById(Long id);
	
	public boolean existById(Long id);
	
	public CategProfession findByAid(Long aid);
	
	/**
	 * 根据IdList查询CategProfession实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CategProfession> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param categProfession
	 * @return
	 */
	public CategProfession persistEntity(CategProfession categProfession) ;
	
	public CategProfession mergeEntity(CategProfession categProfession) ; 
	
	public CategProfession saveOrUpdate(CategProfession categProfession) ;
	
	public Page<CategProfession> findPage(CategProfession categProfession, Pageable pageable) ; 
	public Page<CategProfession> findPageForPublish(CategProfession categProfession, Pageable pageable) ; 
	public Page<CategProfession> findPageForPublishFromDetail(CategProfession categProfession, Pageable pageable);
	public Map<Long, List<CategProfession>> findMapByMainIdList(List<Long> professionIdList);
	
	public List<CategProfession> findByMainId(Long professionId);
	
	public CategProfession changeCategory(CategProfession categProfession);
	
	public Page<CategProfession> findPageForPublish(CategProfession categProfession, List<Long> excludeMainIdList, Pageable pageable, boolean cover, boolean banner, boolean author) ; 
	public Page<CategProfession> findPageForPublishWithChildren(CategProfession categProfession, List<Long> excludeMainIdList, Pageable pageable, boolean cover, boolean banner, boolean author);
	
}
