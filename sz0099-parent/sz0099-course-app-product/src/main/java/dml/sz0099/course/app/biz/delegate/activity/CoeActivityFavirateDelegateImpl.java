package dml.sz0099.course.app.biz.delegate.activity;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.activity.CoeActivityFavirateService;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFavirate;

/**
 * coeActivityFavirateServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeActivityFavirateDelegateImpl implements CoeActivityFavirateDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityFavirateDelegateImpl.class);
	
	@Autowired
	private CoeActivityFavirateService coeActivityFavirateService;

	/**
	 * 根据Id查询CoeActivityFavirate实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityFavirate findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeActivityFavirate coeActivityFavirate = coeActivityFavirateService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeActivityFavirate);
		return coeActivityFavirate;
	}
	
	@Override
	public CoeActivityFavirate findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityFavirate coeActivityFavirate = coeActivityFavirateService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityFavirate);
		return coeActivityFavirate;
	}
	
	/**
	 * 根据IdList查询CoeActivityFavirate实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityFavirate> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeActivityFavirate> coeActivityFavirateList = coeActivityFavirateService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeActivityFavirateList);
		return coeActivityFavirateList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeActivityFavirate persistEntity(CoeActivityFavirate coeActivityFavirate) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeActivityFavirate entity = coeActivityFavirateService.persistEntity(coeActivityFavirate);
		Long id = coeActivityFavirate.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityFavirate mergeEntity(CoeActivityFavirate coeActivityFavirate) {
		Long id = coeActivityFavirate.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityFavirate entity = coeActivityFavirateService.mergeEntity(coeActivityFavirate);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityFavirate saveOrUpdate(CoeActivityFavirate coeActivityFavirate) {
		Long id = coeActivityFavirate.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityFavirate entity = coeActivityFavirateService.saveOrUpdate(coeActivityFavirate);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityFavirate> findPage(CoeActivityFavirate coeActivityFavirate, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeActivityFavirate> page = coeActivityFavirateService.findPage(coeActivityFavirate, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeActivityFavirateService.existById(id);
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityFavirateService.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeActivityFavirate findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityFavirateService.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityFavirate> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityFavirateService.findByMainId( mainId,  pageable);
	}
	
	public boolean hasFaviratedByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityFavirateService.hasFaviratedByMainIdAndUserId(mainId,userId);
	}

	@Override
	public CoeActivityFavirate mergeForFavirate(CoeActivityFavirate coeActivityFavirate) {
		return coeActivityFavirateService.mergeForFavirate(coeActivityFavirate);
	}
}
