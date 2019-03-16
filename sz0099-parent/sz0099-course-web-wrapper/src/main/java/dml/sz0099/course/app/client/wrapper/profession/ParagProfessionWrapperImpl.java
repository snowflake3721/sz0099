package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.profession.ParagProfessionDelegate;
import dml.sz0099.course.app.persist.entity.article.ParagArticle;
import dml.sz0099.course.app.persist.entity.profession.ParagProfession;
import dml.sz0099.course.app.persist.entity.profession.Paragraph;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ParagProfessionWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service("profParagProfessionWrapperImpl")
public class ParagProfessionWrapperImpl implements ParagProfessionWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParagProfessionWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ParagProfessionDelegate paragProfessionDelegate;
	
	@Autowired
	private ParagraphWrapper paragraphWrapper;
	
	/**
	 * 根据Id查询ParagProfession实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ParagProfession findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ParagProfession paragProfession = paragProfessionDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, paragProfession);
		return paragProfession;
	}
	
	@Override
	public ParagProfession findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ParagProfession paragProfession = paragProfessionDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, paragProfession);
		return paragProfession;
	}
	
	/**
	 * 根据IdList查询ParagProfession实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagProfession> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ParagProfession> paragProfessionList = paragProfessionDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  paragProfessionList);
		return paragProfessionList;
	}
	
	@Override
	public ParagProfession persistEntity(ParagProfession paragProfession) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ParagProfession entity = paragProfessionDelegate.persistEntity(paragProfession);
		Long id = paragProfession.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ParagProfession mergeEntity(ParagProfession paragProfession) {
		Long id = paragProfession.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ParagProfession entity = paragProfessionDelegate.mergeEntity(paragProfession);
		Paragraph paragraph = paragProfession.getParagraph();
		paragraph.setLastModifiedBy(paragProfession.getLastModifiedBy());
		paragraph.setUserId(paragProfession.getUserId());
		paragraph.setOrderSeq(paragProfession.getOrderSeq());
		paragraph=paragraphWrapper.mergeEntity(paragraph);
		paragProfession.setParagraph(paragraph);
		return entity;
	}

	@Override
	public ParagProfession saveOrUpdate(ParagProfession paragProfession) {
		Long id = paragProfession.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ParagProfession entity = paragProfessionDelegate.saveOrUpdate(paragProfession);
		return entity;
	}

	@Override
	public Page<ParagProfession> findPage(ParagProfession paragProfession, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ParagProfession> page = paragProfessionDelegate.findPage(paragProfession, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return paragProfessionDelegate.existById(id);
	}


	public Page<ParagProfession> findByMainId(Long professionId, Pageable pageable ){		

		LOGGER.debug("-------wrapper>>>ParagProfessionWrapperImpl.findByMainId----------begin---------");

		Page<ParagProfession> page = paragProfessionDelegate.findByMainId( professionId,  pageable );


		return page;
	}
	
	public Page<ParagProfession> findByMainIdAndUserId(Long professionId, Long userId, Pageable pageable ){
		return paragProfessionDelegate.findByMainIdAndUserId(professionId, userId, pageable);
	}




	public void deleteByProfessionIdAndUserId(Long professionId, Long userId ){		

		LOGGER.debug("-------wrapper>>>ParagProfessionWrapperImpl.deleteByProfessionIdAndUserId----------begin---------");

		paragProfessionDelegate.deleteByProfessionIdAndUserId( professionId,  userId );

	}
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		paragProfessionDelegate.deleteByParagIdAndUserId(paragId, userId, cascade);
	}
	
	public ParagProfession createParagProfession(ParagProfession paragProfession) {
		return paragProfessionDelegate.createParagProfession(paragProfession);
	}

	@Override
	public Long countByMainId(Long professionId) {
		return paragProfessionDelegate.countByMainId(professionId);
	}

	public Page<ParagProfession> resetOrderSeq(Long professionId, Long userId ){
		return paragProfessionDelegate.resetOrderSeq(professionId, userId);
	}

}
