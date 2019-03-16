package dml.sz0099.course.app.biz.service.product;

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

import dml.sz0099.course.app.persist.dao.product.CoeOprationDao;
import dml.sz0099.course.app.persist.entity.product.CoeOpration;


/**
 * 
 * @formatter:off
 * description: CoeOprationServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeOprationServiceImpl extends GenericServiceImpl<CoeOpration, Long> implements CoeOprationService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOprationServiceImpl.class);

	@Autowired
	private CoeOprationDao coeOprationDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeOprationDao;
	}

	/**
	 * 根据Id查询CoeOpration实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOpration findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeOpration coeOpration = coeOprationDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeOpration);
		return coeOpration;
	}
	
	@Override
	public CoeOpration findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeOpration coeOpration = coeOprationDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOpration);
		return coeOpration;
	}

	/**
	 * 根据IdList查询CoeOpration实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOpration> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeOpration> coeOprationList = coeOprationDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeOprationList);
		return coeOprationList;
	}

	@Transactional
	@Override
	public CoeOpration persistEntity(CoeOpration coeOpration) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeOpration entity = save(coeOpration);
		Long id = coeOpration.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeOpration.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeOpration mergeEntity(CoeOpration coeOpration) {
		Long id = coeOpration.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeOpration entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeOpration.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeOpration.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeOpration saveOrUpdate(CoeOpration coeOpration) {
		Long id = coeOpration.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOpration entity = null;
		if(null != id) {
			entity = mergeEntity(coeOpration);
		}else {
			entity = persistEntity(coeOpration);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOpration> findPage(CoeOpration coeOpration, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeOpration> page = coeOprationDao.findPage(coeOpration, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeOprationDao.existById(id);
	}

}
