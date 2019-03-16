package dml.sz0099.course.app.client.wrapper.paragraph;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.paragraph.ParagraphDelegate;
import dml.sz0099.course.app.persist.entity.paragraph.Paragraph;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagraphWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class ParagraphWrapperImpl implements ParagraphWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ParagraphDelegate paragraphDelegate;
	
	/**
	 * 根据Id查询Paragraph实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Paragraph findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		Paragraph paragraph = paragraphDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, paragraph);
		return paragraph;
	}
	
	@Override
	public Paragraph findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		Paragraph paragraph = paragraphDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, paragraph);
		return paragraph;
	}
	
	/**
	 * 根据IdList查询Paragraph实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Paragraph> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<Paragraph> paragraphList = paragraphDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  paragraphList);
		return paragraphList;
	}
	
	@Override
	public Paragraph persistEntity(Paragraph paragraph) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		Paragraph entity = paragraphDelegate.persistEntity(paragraph);
		Long id = paragraph.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Paragraph mergeEntity(Paragraph paragraph) {
		Long id = paragraph.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		Paragraph entity = paragraphDelegate.mergeEntity(paragraph);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Paragraph saveOrUpdate(Paragraph paragraph) {
		Long id = paragraph.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		Paragraph entity = paragraphDelegate.saveOrUpdate(paragraph);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Paragraph> findPage(Paragraph paragraph, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<Paragraph> page = paragraphDelegate.findPage(paragraph, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return paragraphDelegate.existById(id);
	}


	public Paragraph createParagraph(Paragraph paragraph ){		

		LOGGER.debug("-------wrapper>>>createParagraph----------begin---------");

		Paragraph entity = paragraphDelegate.createParagraph( paragraph );

		return entity;
	}



	
	public void deleteByIdListAndUserId(List<Long> idList, Long userId, boolean cascade ){		

		LOGGER.debug("-------wrapper>>>deleteByIdListAndUserId----------begin---------");
		paragraphDelegate.deleteByIdListAndUserId(idList, userId, cascade);

	}

	@Override
	public Paragraph persistForPhoto(Paragraph paragraph) {
		return paragraphDelegate.persistForPhoto(paragraph);
	}


}
