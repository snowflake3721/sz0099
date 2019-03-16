package dml.sz0099.course.app.biz.delegate.profession;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.profession.ParagProfessionService;
import dml.sz0099.course.app.persist.entity.article.ParagArticle;
import dml.sz0099.course.app.persist.entity.profession.ParagProfession;

/**
 * paragProfessionServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ParagProfessionDelegateImpl implements ParagProfessionDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagProfessionDelegateImpl.class);
	
	@Autowired
	private ParagProfessionService paragProfessionService;

	/**
	 * 根据Id查询ParagProfession实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ParagProfession findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ParagProfession paragProfession = paragProfessionService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, paragProfession);
		return paragProfession;
	}
	
	@Override
	public ParagProfession findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ParagProfession paragProfession = paragProfessionService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, paragProfession);
		return paragProfession;
	}
	
	/**
	 * 根据IdList查询ParagProfession实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagProfession> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ParagProfession> paragProfessionList = paragProfessionService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  paragProfessionList);
		return paragProfessionList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ParagProfession persistEntity(ParagProfession paragProfession) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ParagProfession entity = paragProfessionService.persistEntity(paragProfession);
		Long id = paragProfession.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ParagProfession mergeEntity(ParagProfession paragProfession) {
		Long id = paragProfession.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ParagProfession entity = paragProfessionService.mergeEntity(paragProfession);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ParagProfession saveOrUpdate(ParagProfession paragProfession) {
		Long id = paragProfession.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ParagProfession entity = paragProfessionService.saveOrUpdate(paragProfession);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ParagProfession> findPage(ParagProfession paragProfession, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ParagProfession> page = paragProfessionService.findPage(paragProfession, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return paragProfessionService.existById(id);
	}


	public Page<ParagProfession> findByMainId(Long professionId, Pageable pageable ){		

		LOGGER.debug("-------delegate>>>ParagProfessionDelegateImpl.findByMainId----------begin---------");

		Page<ParagProfession> paragProfession = paragProfessionService.findByMainId( professionId,  pageable );

		LOGGER.info("-------delegate>>>ParagProfessionDelegateImpl.findByMainId----------end---------");

		return paragProfession;
	}
	
	public Page<ParagProfession> findByMainIdAndUserId(Long professionId, Long userId, Pageable pageable ){		

		LOGGER.debug("-------delegate>>>ParagProfessionDelegateImpl.findByMainIdAndUserId----------begin---------");
		Page<ParagProfession> paragProfession = paragProfessionService.findByMainIdAndUserId( professionId, userId, pageable );
		return paragProfession;
	}

	public Page<ParagProfession> resetOrderSeq(Long professionId, Long userId ){
		return paragProfessionService.resetOrderSeq(professionId, userId);
	}


	public void deleteByProfessionIdAndUserId(Long professionId, Long userId ){		

		LOGGER.debug("-------delegate>>>ParagProfessionDelegateImpl.deleteByProfessionIdAndUserId----------begin---------");
		paragProfessionService.deleteByProfessionIdAndUserId( professionId,  userId );
	}
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		paragProfessionService.deleteByParagIdAndUserId(paragId, userId, cascade);
	}
	
	public ParagProfession createParagProfession(ParagProfession paragProfession) {
		return paragProfessionService.createParagProfession(paragProfession);
	}
	
	public Long countByMainId(Long professionId) {
		return paragProfessionService.countByMainId(professionId);
	}


}
