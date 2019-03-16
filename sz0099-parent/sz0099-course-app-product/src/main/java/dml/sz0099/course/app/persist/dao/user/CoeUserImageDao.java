package dml.sz0099.course.app.persist.dao.user;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.CoeUserImage;

/**
 * CoeUserImageDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface CoeUserImageDao extends GenericDao<CoeUserImage,Long>{

	/**
	 * 根据Id查询CoeUserImage实体对象
	 * @param id
	 * @return
	 */
	CoeUserImage findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	CoeUserImage findByAid(Long aid);
	
	/**
	 * 根据IdList查询CoeUserImage实体对象列表
	 * @param idList
	 * @return
	 */
	public List<CoeUserImage> findByIdList(List<Long> idList);
	
	public Page<CoeUserImage> findPage(CoeUserImage coeUserImage, Pageable pageable);
	
	public List<CoeUserImage> findByCoeUserId(Long coeUserId) ;
	
	public List<CoeUserImage> findByCoeUserId(Long coeUserId, Long subId) ;
	
	public List<CoeUserImage> findByCoeUserIdAndSubIdList(Long coeUserId, List<Long> subIdList);
	
}
