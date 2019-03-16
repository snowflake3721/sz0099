package dml.sz0099.course.app.biz.service.position;

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

import dml.sz0099.course.app.persist.dao.position.PositionExtendContentDao;
import dml.sz0099.course.app.persist.entity.position.PositionExtendContent;


/**
 * 
 * @formatter:off
 * description: PositionExtendContentServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class PositionExtendContentServiceImpl extends GenericServiceImpl<PositionExtendContent, Long> implements PositionExtendContentService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendContentServiceImpl.class);

	@Autowired
	private PositionExtendContentDao positionExtendContentDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = positionExtendContentDao;
	}

	/**
	 * 根据Id查询PositionExtendContent实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PositionExtendContent findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		PositionExtendContent positionExtendContent = positionExtendContentDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, positionExtendContent);
		return positionExtendContent;
	}
	
	@Override
	public PositionExtendContent findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		PositionExtendContent positionExtendContent = positionExtendContentDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, positionExtendContent);
		return positionExtendContent;
	}

	/**
	 * 根据IdList查询PositionExtendContent实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<PositionExtendContent> positionExtendContentList = positionExtendContentDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", positionExtendContentList);
		return positionExtendContentList;
	}

	@Transactional
	@Override
	public PositionExtendContent persistEntity(PositionExtendContent positionExtendContent) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		PositionExtendContent entity = save(positionExtendContent);
		Long id = positionExtendContent.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(PositionExtendContent.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public PositionExtendContent mergeEntity(PositionExtendContent positionExtendContent) {
		Long id = positionExtendContent.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		PositionExtendContent entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(positionExtendContent.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(PositionExtendContent.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public PositionExtendContent saveOrUpdate(PositionExtendContent positionExtendContent) {
		Long id = positionExtendContent.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		PositionExtendContent entity = null;
		if(null != id) {
			entity = mergeEntity(positionExtendContent);
		}else {
			entity = persistEntity(positionExtendContent);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionExtendContent> findPage(PositionExtendContent positionExtendContent, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<PositionExtendContent> page = positionExtendContentDao.findPage(positionExtendContent, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionExtendContentDao.existById(id);
	}

}
