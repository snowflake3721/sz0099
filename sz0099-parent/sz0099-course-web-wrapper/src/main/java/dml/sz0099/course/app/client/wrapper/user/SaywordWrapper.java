package dml.sz0099.course.app.client.wrapper.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.user.Sayword;


/**
 * SaywordWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface SaywordWrapper {

	/**
	 * 根据Id查询Sayword实体对象
	 * @param id
	 * @return
	 */
	public Sayword findById(Long id);
	
	public boolean existById(Long id);
	
	public Sayword findByAid(Long aid);
	
	/**
	 * 根据IdList查询Sayword实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Sayword> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param sayword
	 * @return
	 */
	public Sayword persistEntity(Sayword sayword) ;
	
	public Sayword mergeEntity(Sayword sayword) ; 
	
	public Sayword saveOrUpdate(Sayword sayword) ;
	
	public Page<Sayword> findPage(Sayword sayword, Pageable pageable) ; 
	public Page<Sayword> findPageForHuan(Sayword sayword, Pageable pageable);
	public Page<Sayword> findPageForHuanSingle(Sayword sayword);
	
	public Sayword findAndFixedSayword(Sayword sayword);
	
	public Sayword mergeSayword(Sayword sayword) ;
	
	public Sayword findByUserIdAndMainId(Long userId);
	
}
