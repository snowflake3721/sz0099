package dml.sz0099.course.app.biz.delegate.profession;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.Profession;

/**
 * ProfessionDelegate
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionDelegate {

	/**
	 * 根据Id查询Profession实体对象
	 * @param id
	 * @return
	 */
	public Profession findById(Long id);
	
	public boolean existById(Long id);
	
	public Profession findByAid(Long aid);

	/**
	 * 根据IdList查询Profession实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Profession> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param profession
	 * @return
	 */
	public Profession persistEntity(Profession profession) ;
	
	public Profession mergeEntity(Profession profession) ; 
	
	public Profession saveOrUpdate(Profession profession) ;
	
	public Page<Profession> findPage(Profession profession, Pageable pageable) ;
	
	public Profession mergeForPublish(Profession profession);
	public Profession mergeForClosed(Profession profession);
	public Profession mergeForDeleted(Profession profession);
	public Profession mergeForRefresh(Profession profession);
	public Page<Profession> findPublished(Profession profession, Pageable pageable);
	
	public Profession createDraft(Profession profession);
	
	public List<Profession> findDraftList(Profession profession);
	
	public Long countDraftList(Profession profession) ;
	
	public Profession mergeForBaseinfo(Profession profession);
	public Profession mergeProfessionForTitle(Profession profession);
	public Profession mergeArticleForTitleOnly(Profession profession);
	public Profession mergeArticleForDescriptionOnly(Profession profession);
	
	public List<Profession> findByPublished(Profession profession);
	public Page<Profession> findByPublished(Profession profession, Pageable pageable);
	
	public Profession mergeForEditQickly(Profession profession);
	public Profession mergeForUnPublished(Profession profession);
	
	public Long countForPublishedWithoutSelf(Long userId, Long positionId);
	public Page<Profession> findByPublishedNotSelf(Profession profession, Pageable pageable);
	
	public List<Profession> findByUserId(Long userId);
	
	public Long countByUserId(Long userId) ;
	
	public Page<Profession> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable);
	public Page<Profession> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable);
	public List<Profession> findListByMainTypeAndUserId(List<Long> userIdList);
	public Profession mergeForMainType(Profession profession);
	
}
