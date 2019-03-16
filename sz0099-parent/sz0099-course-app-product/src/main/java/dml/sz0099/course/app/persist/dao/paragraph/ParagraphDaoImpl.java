package dml.sz0099.course.app.persist.dao.paragraph;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.paragraph.Paragraph;
import dml.sz0099.course.app.persist.repository.paragraph.ParagraphRepository;
import dml.sz0099.course.app.persist.specification.paragraph.ParagraphSpecification;

/**
 * ParagraphDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ParagraphDaoImpl extends GenericDaoImpl<Paragraph, Long> implements ParagraphDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphDaoImpl.class);

	@Autowired
	private ParagraphRepository paragraphRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = paragraphRepository;
	}

	/**
	 * 根据Id查询Paragraph实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Paragraph findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		Paragraph paragraph = paragraphRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, paragraph);
		return paragraph;
	}

	@Override
	public Paragraph findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		Paragraph paragraph = paragraphRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, paragraph);
		return paragraph;
	}

	/**
	 * 根据IdList查询Paragraph实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Paragraph> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<Paragraph> paragraphList = paragraphRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", paragraphList);
		return paragraphList;
	}

	/**
	 * 条件查询
	 */
	public Page<Paragraph> findPage(Paragraph paragraph, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ParagraphSpecification.getConditionWithQsl(paragraph);
		Page<Paragraph> page = paragraphRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		Paragraph entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}



	public Paragraph createParagraph(Paragraph paragraph ){		

		LOGGER.debug("-------dao>>>ParagraphDaoImpl.createParagraph----------begin---------");

		//Paragraph entity = paragraphRepository.createParagraph( paragraph );

		//return entity;
		return null;
	}




	public void deleteByIdListAndUserId(List<Long> idList, Long userId ){		

		LOGGER.debug("-------dao>>>deleteByIdListAndUserId----------begin---------");

		paragraphRepository.deleteByIdListAndUserId( idList,  userId );


	}


}
