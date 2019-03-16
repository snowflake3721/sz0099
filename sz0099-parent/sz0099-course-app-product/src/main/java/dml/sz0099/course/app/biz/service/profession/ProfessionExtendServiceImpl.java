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

import dml.sz0099.course.app.persist.dao.profession.ProfessionExtendDao;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;


/**
 * 
 * @formatter:off
 * description: ProfessionExtendServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionExtendServiceImpl extends GenericServiceImpl<ProfessionExtend, Long> implements ProfessionExtendService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionExtendServiceImpl.class);

	@Autowired
	private ProfessionExtendDao professionExtendDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionExtendDao;
	}

	/**
	 * 根据Id查询ProfessionExtend实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionExtend findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ProfessionExtend professionExtend = professionExtendDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, professionExtend);
		return professionExtend;
	}
	
	@Override
	public ProfessionExtend findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionExtend professionExtend = professionExtendDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, professionExtend);
		return professionExtend;
	}

	/**
	 * 根据IdList查询ProfessionExtend实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ProfessionExtend> professionExtendList = professionExtendDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionExtendList);
		return professionExtendList;
	}

	@Transactional
	@Override
	public ProfessionExtend persistEntity(ProfessionExtend professionExtend) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ProfessionExtend entity = save(professionExtend);
		Long id = professionExtend.getId();
		entity.setPositionId(id);
		save(entity);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ProfessionExtend.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionExtend mergeEntity(ProfessionExtend professionExtend) {
		Long id = professionExtend.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ProfessionExtend entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(professionExtend.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			
			entity.setDevId(professionExtend.getDevId());
			entity.setDomain(professionExtend.getDomain());
			entity.setMainMaxnum(professionExtend.getMainMaxnum());
			entity.setModule(professionExtend.getModule());
			entity.setPosition(professionExtend.getPosition());
			entity.setProject(professionExtend.getProject());
			entity.setRefMaxnum(professionExtend.getRefMaxnum());
			entity.setSubMaxnum(professionExtend.getSubMaxnum());
			entity.setUserId(professionExtend.getUserId());
			entity.setVariety(professionExtend.getVariety());
			
			Long positionId = entity.getPositionId();
			if(null == positionId) {
				entity.setPositionId(id);
			}
			
			save(entity);
			entity.setSuccess(ProfessionExtend.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ProfessionExtend saveOrUpdate(ProfessionExtend professionExtend) {
		Long id = professionExtend.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionExtend entity = null;
		if(null != id) {
			entity = mergeEntity(professionExtend);
		}else {
			entity = persistEntity(professionExtend);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionExtend> findPage(ProfessionExtend professionExtend, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ProfessionExtend> page = professionExtendDao.findPage(professionExtend, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionExtendDao.existById(id);
	}
	
	public ProfessionExtend create(ProfessionExtend professionExtend) {
		return persistEntity(professionExtend);
	}
	
	@Override
	public ProfessionExtend findByPositionId(Long positionId) {
		return professionExtendDao.findByPositionId(positionId);
	}

	@Override
	public ProfessionExtend findProfessionExtend(ProfessionExtend extend) {
		return professionExtendDao.findProfessionExtend(extend);
	}
	
	@Override
	public Long findPositionIdById(Long id) {
		return professionExtendDao.findPositionIdById(id);
	}
	
	public Long countByUserId(Long userId) {
		return professionExtendDao.countByUserId(userId);
	}
	
	@Transactional
	public ProfessionExtend deleteEntity(ProfessionExtend extend) {
		Long id = extend.getId();
		ProfessionExtend entity = findById(id);
		if(null != entity) {
			Long userE = entity.getUserId();
			Long userU = extend.getUserId();
			if(userE.equals(userU)) {
				delete(entity);
				extend.setSuccess(ProfessionExtend.SUCCESS_YES);
			}
		}
		return extend;
	}

}
