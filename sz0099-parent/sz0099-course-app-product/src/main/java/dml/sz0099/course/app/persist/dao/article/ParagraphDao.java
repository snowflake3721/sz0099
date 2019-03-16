package dml.sz0099.course.app.persist.dao.article;

import java.util.List;

import org.jit4j.core.persist.dao.GenericDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dml.sz0099.course.app.persist.entity.article.Paragraph;

/**
 * ParagraphDao
 * 数据访问接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
public interface ParagraphDao extends GenericDao<Paragraph,Long>{

	/**
	 * 根据Id查询Paragraph实体对象
	 * @param id
	 * @return
	 */
	Paragraph findById(Long id);
	
	boolean existById(Long id) ;
	/**
	 * 自增长id查询实体对象
	 * @param aid
	 * @return
	 */
	Paragraph findByAid(Long aid);
	
	/**
	 * 根据IdList查询Paragraph实体对象列表
	 * @param idList
	 * @return
	 */
	public List<Paragraph> findByIdList(List<Long> idList);
	
	public Page<Paragraph> findPage(Paragraph paragraph, Pageable pageable);
	


	public Paragraph createParagraph(Paragraph paragraph );




	public void deleteByIdListAndUserId(List<Long> idList, Long userId );


}
