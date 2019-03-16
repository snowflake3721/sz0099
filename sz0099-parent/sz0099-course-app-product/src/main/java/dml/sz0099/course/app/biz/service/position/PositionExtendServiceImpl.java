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

import dml.sz0099.course.app.persist.dao.position.PositionExtendDao;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;


/**
 * 
 * @formatter:off
 * description: PositionExtendServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class PositionExtendServiceImpl extends GenericServiceImpl<PositionExtend, Long> implements PositionExtendService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendServiceImpl.class);

	@Autowired
	private PositionExtendDao positionExtendDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = positionExtendDao;
	}

	/**
	 * 根据Id查询PositionExtend实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PositionExtend findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		PositionExtend positionExtend = positionExtendDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, positionExtend);
		return positionExtend;
	}
	
	@Override
	public PositionExtend findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		PositionExtend positionExtend = positionExtendDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, positionExtend);
		return positionExtend;
	}

	/**
	 * 根据IdList查询PositionExtend实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<PositionExtend> positionExtendList = positionExtendDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", positionExtendList);
		return positionExtendList;
	}

	@Transactional
	@Override
	public PositionExtend persistEntity(PositionExtend positionExtend) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		PositionExtend entity = save(positionExtend);
		Long id = positionExtend.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(PositionExtend.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public PositionExtend mergeEntity(PositionExtend positionExtend) {
		Long id = positionExtend.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		PositionExtend entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(positionExtend.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			
			entity.setDevId(positionExtend.getDevId());
			entity.setDomain(positionExtend.getDomain());
			entity.setMainMaxnum(positionExtend.getMainMaxnum());
			entity.setModule(positionExtend.getModule());
			entity.setPosition(positionExtend.getPosition());
			entity.setProject(positionExtend.getProject());
			entity.setRefMaxnum(positionExtend.getRefMaxnum());
			entity.setSubMaxnum(positionExtend.getSubMaxnum());
			entity.setUserId(positionExtend.getUserId());
			entity.setVariety(positionExtend.getVariety());
			
			Long positionId = entity.getPositionId();
			if(null == positionId) {
				entity.setPositionId(id);
			}
			
			save(entity);
			entity.setSuccess(PositionExtend.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public PositionExtend saveOrUpdate(PositionExtend positionExtend) {
		Long id = positionExtend.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		PositionExtend entity = null;
		if(null != id) {
			entity = mergeEntity(positionExtend);
		}else {
			entity = persistEntity(positionExtend);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionExtend> findPage(PositionExtend positionExtend, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<PositionExtend> page = positionExtendDao.findPage(positionExtend, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionExtendDao.existById(id);
	}
	
	public PositionExtend create(PositionExtend positionExtend) {
		return persistEntity(positionExtend);
	}
	
	@Override
	public PositionExtend findByPositionId(Long positionId) {
		return positionExtendDao.findByPositionId(positionId);
	}

	@Override
	public PositionExtend findPositionExtend(PositionExtend extend) {
		return positionExtendDao.findPositionExtend(extend);
	}
	
	@Override
	public Long findPositionIdById(Long id) {
		return positionExtendDao.findPositionIdById(id);
	}
	
	public Long countByUserId(Long userId) {
		return positionExtendDao.countByUserId(userId);
	}
	
	@Transactional
	public PositionExtend deleteEntity(PositionExtend extend) {
		Long id = extend.getId();
		PositionExtend entity = findById(id);
		if(null != entity) {
			Long userE = entity.getUserId();
			Long userU = extend.getUserId();
			if(userE.equals(userU)) {
				delete(entity);
				extend.setSuccess(PositionExtend.SUCCESS_YES);
			}
		}
		return extend;
	}

}
