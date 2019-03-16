package dml.sz0099.course.app.biz.service.paragraph;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.paragraph.ParagraphDao;
import dml.sz0099.course.app.persist.entity.paragraph.Paragraph;


/**
 * 
 * @formatter:off
 * description: ParagraphServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ParagraphServiceImpl extends GenericServiceImpl<Paragraph, Long> implements ParagraphService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphServiceImpl.class);

	@Autowired
	private ParagraphDao paragraphDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = paragraphDao;
	}

	/**
	 * 根据Id查询Paragraph实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Paragraph findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		Paragraph paragraph = paragraphDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, paragraph);
		return paragraph;
	}
	
	@Override
	public Paragraph findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		Paragraph paragraph = paragraphDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, paragraph);
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
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<Paragraph> paragraphList = paragraphDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", paragraphList);
		return paragraphList;
	}

	@Transactional
	@Override
	public Paragraph persistEntity(Paragraph paragraph) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		Paragraph entity = save(paragraph);
		Long id = paragraph.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(Paragraph.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public Paragraph mergeEntity(Paragraph paragraph) {
		Long id = paragraph.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		Paragraph entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(paragraph.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(Paragraph.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public Paragraph saveOrUpdate(Paragraph paragraph) {
		Long id = paragraph.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		Paragraph entity = null;
		if(null != id) {
			entity = mergeEntity(paragraph);
		}else {
			entity = persistEntity(paragraph);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Paragraph> findPage(Paragraph paragraph, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<Paragraph> page = paragraphDao.findPage(paragraph, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return paragraphDao.existById(id);
	}



	public Paragraph createParagraph(Paragraph paragraph ){		

		LOGGER.debug("-------service>>>ParagraphServiceImpl.createParagraph----------begin---------");

		Paragraph paragraph = paragraphDao.createParagraph( paragraph );

		LOGGER.info("-------service>>>ParagraphServiceImpl.createParagraph----------end---------");

		return paragraph;
	}


}
