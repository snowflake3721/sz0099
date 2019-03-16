package dml.sz0099.course.app.client.wrapper.paragraph;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;


/**
 * ParagProductWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ParagProductWrapper {

	/**
	 * 根据Id查询ParagProduct实体对象
	 * @param id
	 * @return
	 */
	public ParagProduct findById(Long id);
	
	public boolean existById(Long id);
	
	public ParagProduct findByAid(Long aid);
	
	/**
	 * 根据IdList查询ParagProduct实体对象列表
	 * @param idList
	 * @return
	 */
	public List<ParagProduct> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param paragProduct
	 * @return
	 */
	public ParagProduct persistEntity(ParagProduct paragProduct) ;
	
	public ParagProduct mergeEntity(ParagProduct paragProduct) ; 
	
	public ParagProduct saveOrUpdate(ParagProduct paragProduct) ;
	
	public Page<ParagProduct> findPage(ParagProduct paragProduct, Pageable pageable) ; 
	


	public ParagProduct> findByMainId(Long long, Pageable pageable );


}
