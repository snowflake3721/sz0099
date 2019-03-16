package dml.sz0099.course.app.biz.delegate.product;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.product.CoeOprationService;
import dml.sz0099.course.app.persist.entity.product.CoeOpration;

/**
 * coeOprationServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeOprationDelegateImpl implements CoeOprationDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOprationDelegateImpl.class);
	
	@Autowired
	private CoeOprationService coeOprationService;

	/**
	 * 根据Id查询CoeOpration实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOpration findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeOpration coeOpration = coeOprationService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeOpration);
		return coeOpration;
	}
	
	@Override
	public CoeOpration findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeOpration coeOpration = coeOprationService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOpration);
		return coeOpration;
	}
	
	/**
	 * 根据IdList查询CoeOpration实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOpration> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeOpration> coeOprationList = coeOprationService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeOprationList);
		return coeOprationList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeOpration persistEntity(CoeOpration coeOpration) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeOpration entity = coeOprationService.persistEntity(coeOpration);
		Long id = coeOpration.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOpration mergeEntity(CoeOpration coeOpration) {
		Long id = coeOpration.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeOpration entity = coeOprationService.mergeEntity(coeOpration);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOpration saveOrUpdate(CoeOpration coeOpration) {
		Long id = coeOpration.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOpration entity = coeOprationService.saveOrUpdate(coeOpration);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOpration> findPage(CoeOpration coeOpration, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeOpration> page = coeOprationService.findPage(coeOpration, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeOprationService.existById(id);
	}
}
