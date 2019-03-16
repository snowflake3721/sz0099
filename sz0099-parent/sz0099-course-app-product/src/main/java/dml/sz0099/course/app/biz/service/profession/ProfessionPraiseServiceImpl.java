package dml.sz0099.course.app.biz.service.profession;

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

import dml.sz0099.course.app.persist.dao.profession.ProfessionPraiseDao;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPraise;


/**
 * 
 * @formatter:off
 * description: ProfessionPraiseServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ProfessionPraiseServiceImpl extends GenericServiceImpl<ProfessionPraise, Long> implements ProfessionPraiseService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPraiseServiceImpl.class);

	@Autowired
	private ProfessionPraiseDao professionPraiseDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = professionPraiseDao;
	}

	/**
	 * 根据Id查询ProfessionPraise实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionPraise findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ProfessionPraise professionPraise = professionPraiseDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, professionPraise);
		return professionPraise;
	}
	
	@Override
	public ProfessionPraise findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionPraise professionPraise = professionPraiseDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, professionPraise);
		return professionPraise;
	}

	/**
	 * 根据IdList查询ProfessionPraise实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionPraise> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ProfessionPraise> professionPraiseList = professionPraiseDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", professionPraiseList);
		return professionPraiseList;
	}

	@Transactional
	@Override
	public ProfessionPraise persistEntity(ProfessionPraise professionPraise) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ProfessionPraise entity = save(professionPraise);
		Long id = professionPraise.getId();
		Date refreshTime = entity.getRefreshTime();
		if(null == refreshTime) {
			entity.setRefreshTime(entity.getCreatedDate().toDate());
			save(entity);
		}
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ProfessionPraise.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionPraise mergeEntity(ProfessionPraise professionPraise) {
		Long id = professionPraise.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ProfessionPraise entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(professionPraise.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ProfessionPraise.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ProfessionPraise saveOrUpdate(ProfessionPraise professionPraise) {
		Long id = professionPraise.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionPraise entity = null;
		if(null != id) {
			entity = mergeEntity(professionPraise);
		}else {
			entity = persistEntity(professionPraise);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionPraise> findPage(ProfessionPraise professionPraise, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ProfessionPraise> page = professionPraiseDao.findPage(professionPraise, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionPraiseDao.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return professionPraiseDao.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public ProfessionPraise findByMainIdAndUserId(Long mainId, Long userId) {
		return professionPraiseDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionPraise> findByMainId(Long mainId, Pageable pageable) {
		return professionPraiseDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPraisedByMainIdAndUserId(Long mainId, Long userId) {
		ProfessionPraise entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			if(ProfessionPraise.STATUS_YES==entity.getStatus()) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public ProfessionPraise mergeForPraise(ProfessionPraise professionPraise) {
		Long mainId = professionPraise.getMainId();
		Long userId = professionPraise.getUserId();
		ProfessionPraise entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			entity.setNickname(professionPraise.getNickname());
			entity.setHeadImg(professionPraise.getHeadImg());
			entity.setSayword(professionPraise.getSayword());
			entity.setStatus(professionPraise.getStatus());
			entity.setSaywordId(professionPraise.getSaywordId());
			entity.setWord(professionPraise.getWord());
			entity.setLastModifiedBy(professionPraise.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setRefreshTime(lastModifiedDate.toDate());
			entity.setLastModifiedDate(lastModifiedDate);
			entity = save(entity);
		}else {
			entity = save(professionPraise);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public ProfessionPraise praiseAgain(ProfessionPraise professionPraise) {
		Long mainId = professionPraise.getMainId();
		Long userId = professionPraise.getUserId();
		ProfessionPraise entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			entity.setLastModifiedBy(professionPraise.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setWord(professionPraise.getWord());
		}
		return entity;
	}
	
	@Transactional
	public ProfessionPraise mergeForRefreshTime(ProfessionPraise professionPraise) {
		Long id = professionPraise.getId();
		if(null != id) {
			ProfessionPraise entity = findById(id);
			if(null != entity) {
				Date refreshTime = new Date();
				entity.setRefreshTime(refreshTime);
				entity.setLastModifiedBy(professionPraise.getLastModifiedBy());
				entity.setLastModifiedDate(new DateTime(refreshTime));
				professionPraise=entity;
				professionPraise.setSuccess(ProfessionPraise.SUCCESS_YES);
			}
		}
		return professionPraise;
	}

}
