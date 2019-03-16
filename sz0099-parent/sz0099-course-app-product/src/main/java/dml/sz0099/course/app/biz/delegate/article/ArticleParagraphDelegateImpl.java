package dml.sz0099.course.app.biz.delegate.article;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.article.ParagraphService;
import dml.sz0099.course.app.persist.entity.article.Paragraph;

/**
 * paragraphServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ArticleParagraphDelegateImpl implements ArticleParagraphDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleParagraphDelegateImpl.class);
	
	@Autowired
	private ParagraphService paragraphService;

	/**
	 * 根据Id查询Paragraph实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Paragraph findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		Paragraph paragraph = paragraphService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, paragraph);
		return paragraph;
	}
	
	@Override
	public Paragraph findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		Paragraph paragraph = paragraphService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, paragraph);
		return paragraph;
	}
	
	/**
	 * 根据IdList查询Paragraph实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Paragraph> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<Paragraph> paragraphList = paragraphService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  paragraphList);
		return paragraphList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public Paragraph persistEntity(Paragraph paragraph) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		Paragraph entity = paragraphService.persistEntity(paragraph);
		Long id = paragraph.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Paragraph mergeEntity(Paragraph paragraph) {
		Long id = paragraph.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		Paragraph entity = paragraphService.mergeEntity(paragraph);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Paragraph saveOrUpdate(Paragraph paragraph) {
		Long id = paragraph.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		Paragraph entity = paragraphService.saveOrUpdate(paragraph);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Paragraph> findPage(Paragraph paragraph, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<Paragraph> page = paragraphService.findPage(paragraph, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return paragraphService.existById(id);
	}


	public Paragraph createParagraph(Paragraph paragraph ){		

		LOGGER.debug("-------delegate>>>ParagraphDelegateImpl.createParagraph----------begin---------");
		Paragraph entity = paragraphService.createParagraph( paragraph );
		return entity;
	}




	public void deleteByIdListAndUserId(List<Long> idList, Long userId, boolean cascade ){		

		LOGGER.debug("-------delegate>>>deleteByIdListAndUserId----------begin---------");
		paragraphService.deleteByIdListAndUserId(idList, userId, cascade);

	}
	
	public Paragraph persistForPhoto(Paragraph paragraph) {
		
		return paragraphService.persistForPhoto(paragraph);
	}


}
