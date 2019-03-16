package dml.sz0099.course.app.biz.service.user;

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

import dml.sz0099.course.app.persist.dao.user.CoeUserBindDao;
import dml.sz0099.course.app.persist.entity.user.CoeUserBind;


/**
 * 
 * @formatter:off
 * description: CoeUserBindServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeUserBindServiceImpl extends GenericServiceImpl<CoeUserBind, Long> implements CoeUserBindService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserBindServiceImpl.class);

	@Autowired
	private CoeUserBindDao coeUserBindDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeUserBindDao;
	}

	/**
	 * 根据Id查询CoeUserBind实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserBind findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeUserBind coeUserBind = coeUserBindDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeUserBind);
		return coeUserBind;
	}
	
	@Override
	public CoeUserBind findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserBind coeUserBind = coeUserBindDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserBind);
		return coeUserBind;
	}

	/**
	 * 根据IdList查询CoeUserBind实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserBind> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeUserBind> coeUserBindList = coeUserBindDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeUserBindList);
		return coeUserBindList;
	}

	@Transactional
	@Override
	public CoeUserBind persistEntity(CoeUserBind coeUserBind) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeUserBind entity = save(coeUserBind);
		Long id = coeUserBind.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeUserBind.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeUserBind mergeEntity(CoeUserBind coeUserBind) {
		Long id = coeUserBind.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeUserBind entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeUserBind.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeUserBind.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeUserBind saveOrUpdate(CoeUserBind coeUserBind) {
		Long id = coeUserBind.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserBind entity = null;
		if(null != id) {
			entity = mergeEntity(coeUserBind);
		}else {
			entity = persistEntity(coeUserBind);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserBind> findPage(CoeUserBind coeUserBind, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeUserBind> page = coeUserBindDao.findPage(coeUserBind, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeUserBindDao.existById(id);
	}

}
