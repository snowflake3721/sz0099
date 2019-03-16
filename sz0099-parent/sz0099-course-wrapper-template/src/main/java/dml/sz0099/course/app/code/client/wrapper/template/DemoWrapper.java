package dml.sz0099.course.app.code.client.wrapper.template;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.code.persist.entity.template.Demo;


/**
 * DemoWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface DemoWrapper {

	/**
	 * 根据Id查询Demo实体对象
	 * @param id
	 * @return
	 */
	public Demo findById(Long id);
	
	public boolean existById(Long id);
	
	public Demo findByAid(Long aid);
	
	/**
	 * 根据IdList查询Demo实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Demo> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param demo
	 * @return
	 */
	public Demo persistEntity(Demo demo) ;
	
	public Demo mergeEntity(Demo demo) ; 
	
	public Demo saveOrUpdate(Demo demo) ;
	
	public Page<Demo> findPage(Demo demo, Pageable pageable) ; 
	
}