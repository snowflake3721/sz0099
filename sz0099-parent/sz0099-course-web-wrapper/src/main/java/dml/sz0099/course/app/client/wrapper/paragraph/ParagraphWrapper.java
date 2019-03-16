package dml.sz0099.course.app.client.wrapper.paragraph;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.paragraph.Paragraph;


/**
 * ParagraphWrapper
 * 服务组件
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ParagraphWrapper {

	/**
	 * 根据Id查询Paragraph实体对象
	 * @param id
	 * @return
	 */
	public Paragraph findById(Long id);
	
	public boolean existById(Long id);
	
	public Paragraph findByAid(Long aid);
	
	/**
	 * 根据IdList查询Paragraph实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Paragraph> findByIdList(List<Long> idList);
	
	/**
	 * 持久化实体
	 * @param paragraph
	 * @return
	 */
	public Paragraph persistEntity(Paragraph paragraph) ;
	
	public Paragraph mergeEntity(Paragraph paragraph) ; 
	
	public Paragraph persistForPhoto(Paragraph paragraph) ;
	
	public Paragraph saveOrUpdate(Paragraph paragraph) ;
	
	public Page<Paragraph> findPage(Paragraph paragraph, Pageable pageable) ; 
	


	public Paragraph createParagraph(Paragraph paragraph );


	public void deleteByIdListAndUserId(List<Long> idList, Long userId, boolean cascade );


}
