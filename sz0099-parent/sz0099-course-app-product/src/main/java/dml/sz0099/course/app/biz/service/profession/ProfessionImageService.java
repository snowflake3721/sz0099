package dml.sz0099.course.app.biz.service.profession;

import java.util.List;

import org.jit4j.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;

/**
 * ProfessionImageService
 * 业务服务层
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ProfessionImageService extends GenericService<ProfessionImage,Long>{


	/**
	 * 根据Id查询ProfessionImage实体对象
	 * @param id
	 * @return
	 */
	public ProfessionImage findById(Long id);
	
	public boolean existById(Long id);
	
	public ProfessionImage findByAid(Long aid);
	
	/**
	 * 根据IdList查询ProfessionImage实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ProfessionImage> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param professionImage
	 * @return
	 */
	public ProfessionImage persistEntity(ProfessionImage professionImage) ;
	
	
	public ProfessionImage mergeEntity(ProfessionImage professionImage) ; 
	
	public ProfessionImage saveOrUpdate(ProfessionImage professionImage) ;
	
	public Page<ProfessionImage> findPage(ProfessionImage professionImage, Pageable pageable) ;
	
	public List<ProfessionImage> findByRefIdList(List<Long> refIdList);
	public List<ProfessionImage> findByRefId(Long refId);
	
	public void deleteByRefIdList(List<Long> refIdList);
	public void deleteByRefId(Long refId);
	public ProfessionRef addProfessionImage(ProfessionRef professionRef);
	public ProfessionRef mergeForProfession(ProfessionRef professionRef);
	
}
