package dml.sz0099.course.app.biz.service.profession;

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

import dml.sz0099.course.app.persist.dao.profession.ProfessionExtendContentDao;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendContent;


/**
 * 
 * @formatter:off
 * description: ProfessionExtendContentServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionExtendContentServiceImpl extends GenericServiceImpl<ProfessionExtendContent, Long> implements ProfessionExtendContentService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionExtendContentServiceImpl.class);

	@Autowired
	private ProfessionExtendContentDao professionExtendContentDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionExtendContentDao;
	}

	/**
	 * 根据Id查询ProfessionExtendContent实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionExtendContent findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ProfessionExtendContent professionExtendContent = professionExtendContentDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, professionExtendContent);
		return professionExtendContent;
	}
	
	@Override
	public ProfessionExtendContent findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionExtendContent professionExtendContent = professionExtendContentDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, professionExtendContent);
		return professionExtendContent;
	}

	/**
	 * 根据IdList查询ProfessionExtendContent实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ProfessionExtendContent> professionExtendContentList = professionExtendContentDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionExtendContentList);
		return professionExtendContentList;
	}

	@Transactional
	@Override
	public ProfessionExtendContent persistEntity(ProfessionExtendContent professionExtendContent) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ProfessionExtendContent entity = save(professionExtendContent);
		Long id = professionExtendContent.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ProfessionExtendContent.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionExtendContent mergeEntity(ProfessionExtendContent professionExtendContent) {
		Long id = professionExtendContent.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ProfessionExtendContent entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(professionExtendContent.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ProfessionExtendContent.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ProfessionExtendContent saveOrUpdate(ProfessionExtendContent professionExtendContent) {
		Long id = professionExtendContent.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionExtendContent entity = null;
		if(null != id) {
			entity = mergeEntity(professionExtendContent);
		}else {
			entity = persistEntity(professionExtendContent);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionExtendContent> findPage(ProfessionExtendContent professionExtendContent, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ProfessionExtendContent> page = professionExtendContentDao.findPage(professionExtendContent, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionExtendContentDao.existById(id);
	}

}
