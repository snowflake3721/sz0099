package dml.sz0099.course.app.biz.service.activity;

import java.io.Serializable;
import java.util.Date;
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

import dml.sz0099.course.app.persist.dao.activity.CoeActivityPraiseDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPraise;


/**
 * 
 * @formatter:off
 * description: CoeActivityPraiseServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeActivityPraiseServiceImpl extends GenericServiceImpl<CoeActivityPraise, Long> implements CoeActivityPraiseService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPraiseServiceImpl.class);

	@Autowired
	private CoeActivityPraiseDao coeActivityPraiseDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeActivityPraiseDao;
	}

	/**
	 * 根据Id查询CoeActivityPraise实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityPraise findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeActivityPraise coeActivityPraise = coeActivityPraiseDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeActivityPraise);
		return coeActivityPraise;
	}
	
	@Override
	public CoeActivityPraise findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityPraise coeActivityPraise = coeActivityPraiseDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityPraise);
		return coeActivityPraise;
	}

	/**
	 * 根据IdList查询CoeActivityPraise实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityPraise> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeActivityPraise> coeActivityPraiseList = coeActivityPraiseDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeActivityPraiseList);
		return coeActivityPraiseList;
	}

	@Transactional
	@Override
	public CoeActivityPraise persistEntity(CoeActivityPraise coeActivityPraise) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeActivityPraise entity = save(coeActivityPraise);
		Long id = coeActivityPraise.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeActivityPraise.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityPraise mergeEntity(CoeActivityPraise coeActivityPraise) {
		Long id = coeActivityPraise.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityPraise entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeActivityPraise.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeActivityPraise.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityPraise saveOrUpdate(CoeActivityPraise coeActivityPraise) {
		Long id = coeActivityPraise.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityPraise entity = null;
		if(null != id) {
			entity = mergeEntity(coeActivityPraise);
		}else {
			entity = persistEntity(coeActivityPraise);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityPraise> findPage(CoeActivityPraise coeActivityPraise, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeActivityPraise> page = coeActivityPraiseDao.findPage(coeActivityPraise, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeActivityPraiseDao.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPraiseDao.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeActivityPraise findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPraiseDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityPraise> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityPraiseDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPraisedByMainIdAndUserId(Long mainId, Long userId) {
		CoeActivityPraise entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			if(CoeActivityPraise.STATUS_YES==entity.getStatus()) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public CoeActivityPraise mergeForPraise(CoeActivityPraise coeActivityPraise) {
		Long mainId = coeActivityPraise.getMainId();
		Long userId = coeActivityPraise.getUserId();
		CoeActivityPraise entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			entity.setNickname(coeActivityPraise.getNickname());
			entity.setHeadImg(coeActivityPraise.getHeadImg());
			entity.setSayword(coeActivityPraise.getSayword());
			entity.setSaywordId(coeActivityPraise.getSaywordId());
			entity.setStatus(coeActivityPraise.getStatus());
			entity.setLastModifiedBy(coeActivityPraise.getLastModifiedBy());
			entity.setWord(coeActivityPraise.getWord());
			DateTime lastModifiedDate = new DateTime();
			entity.setRefreshTime(lastModifiedDate.toDate());
			entity.setLastModifiedDate(lastModifiedDate);
			entity = save(entity);
		}else {
			entity = save(coeActivityPraise);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityPraise praiseAgain(CoeActivityPraise coeActivityPraise) {
		Long mainId = coeActivityPraise.getMainId();
		Long userId = coeActivityPraise.getUserId();
		CoeActivityPraise entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			entity.setLastModifiedBy(coeActivityPraise.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setWord(coeActivityPraise.getWord());
		}
		return entity;
	}
	
	@Transactional
	public CoeActivityPraise mergeForRefreshTime(CoeActivityPraise coeActivityPraise) {
		Long id = coeActivityPraise.getId();
		if(null != id) {
			CoeActivityPraise entity = findById(id);
			if(null != entity) {
				Date refreshTime = new Date();
				entity.setRefreshTime(refreshTime);
				entity.setLastModifiedBy(coeActivityPraise.getLastModifiedBy());
				entity.setLastModifiedDate(new DateTime(refreshTime));
				coeActivityPraise=entity;
				coeActivityPraise.setSuccess(CoeActivityPraise.SUCCESS_YES);
			}
		}
		return coeActivityPraise;
	}

}
