package dml.sz0099.course.app.biz.service.order;

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

import dml.sz0099.course.app.persist.dao.order.CoeOrderTransferDao;
import dml.sz0099.course.app.persist.entity.order.CoeOrderTransfer;


/**
 * 
 * @formatter:off
 * description: CoeOrderTransferServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeOrderTransferServiceImpl extends GenericServiceImpl<CoeOrderTransfer, Long> implements CoeOrderTransferService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderTransferServiceImpl.class);

	@Autowired
	private CoeOrderTransferDao coeOrderTransferDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeOrderTransferDao;
	}

	/**
	 * 根据Id查询CoeOrderTransfer实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderTransfer findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeOrderTransfer coeOrderTransfer = coeOrderTransferDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeOrderTransfer);
		return coeOrderTransfer;
	}
	
	@Override
	public CoeOrderTransfer findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderTransfer coeOrderTransfer = coeOrderTransferDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderTransfer);
		return coeOrderTransfer;
	}

	/**
	 * 根据IdList查询CoeOrderTransfer实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderTransfer> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeOrderTransfer> coeOrderTransferList = coeOrderTransferDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeOrderTransferList);
		return coeOrderTransferList;
	}

	@Transactional
	@Override
	public CoeOrderTransfer persistEntity(CoeOrderTransfer coeOrderTransfer) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeOrderTransfer entity = save(coeOrderTransfer);
		Long id = coeOrderTransfer.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeOrderTransfer.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeOrderTransfer mergeEntity(CoeOrderTransfer coeOrderTransfer) {
		Long id = coeOrderTransfer.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderTransfer entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeOrderTransfer.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeOrderTransfer.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeOrderTransfer saveOrUpdate(CoeOrderTransfer coeOrderTransfer) {
		Long id = coeOrderTransfer.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderTransfer entity = null;
		if(null != id) {
			entity = mergeEntity(coeOrderTransfer);
		}else {
			entity = persistEntity(coeOrderTransfer);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderTransfer> findPage(CoeOrderTransfer coeOrderTransfer, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeOrderTransfer> page = coeOrderTransferDao.findPage(coeOrderTransfer, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeOrderTransferDao.existById(id);
	}

}
