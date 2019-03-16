package dml.sz0099.course.app.biz.delegate.activity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.activity.CoeActivityFeeService;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;

/**
 * coeActivityFeeServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeActivityFeeDelegateImpl implements CoeActivityFeeDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityFeeDelegateImpl.class);
	
	@Autowired
	private CoeActivityFeeService coeActivityFeeService;

	/**
	 * 根据Id查询CoeActivityFee实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityFee findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeActivityFee coeActivityFee = coeActivityFeeService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeActivityFee);
		return coeActivityFee;
	}
	
	@Override
	public CoeActivityFee findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityFee coeActivityFee = coeActivityFeeService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityFee);
		return coeActivityFee;
	}
	
	/**
	 * 根据IdList查询CoeActivityFee实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityFee> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeActivityFee> coeActivityFeeList = coeActivityFeeService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeActivityFeeList);
		return coeActivityFeeList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeActivityFee persistEntity(CoeActivityFee coeActivityFee) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeActivityFee entity = coeActivityFeeService.persistEntity(coeActivityFee);
		Long id = coeActivityFee.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityFee mergeEntity(CoeActivityFee coeActivityFee) {
		Long id = coeActivityFee.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityFee entity = coeActivityFeeService.mergeEntity(coeActivityFee);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityFee saveOrUpdate(CoeActivityFee coeActivityFee) {
		Long id = coeActivityFee.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityFee entity = coeActivityFeeService.saveOrUpdate(coeActivityFee);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityFee> findPage(CoeActivityFee coeActivityFee, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeActivityFee> page = coeActivityFeeService.findPage(coeActivityFee, pageable);
		return page;
	}

	@Override
	public CoeActivityFee findByMainId(CoeActivityFee coeActivityFee) {
		return coeActivityFeeService.findByMainId(coeActivityFee);
	}
	
	public CoeActivityFee addFee(CoeActivityFee coeActivityFee) {
		return coeActivityFeeService.addFee(coeActivityFee);
	}
	
	public CoeActivityFee deleteFee(CoeActivityFee coeActivityFee) {
		return coeActivityFeeService.deleteFee(coeActivityFee);
	}
	
	public Long countByMainId(Long activityId) {
		return coeActivityFeeService.countByMainId(activityId);
	}

	@Override
	public CoeActivityFee findByMainId(Long activityId) {
		return coeActivityFeeService.findByMainId(activityId);
	}
	
	public Map<Long, CoeActivityFee> findMapByMainIdList(List<Long> mainIdList) {
		return coeActivityFeeService.findMapByMainIdList(mainIdList);
	}
	
	public Page<CoeActivityFee> findPageWithNotself(CoeActivityFee coeActivityFee, Pageable pageable){
		return coeActivityFeeService.findPageWithNotself(coeActivityFee, pageable);
	}
	
	public CoeActivityFee mergeDescription(CoeActivityFee coeActivityFee) {
		return coeActivityFeeService.mergeDescription(coeActivityFee); 
	}
	
	public CoeActivityFee mergeFee(CoeActivityFee coeActivityFee) {
		return coeActivityFeeService.mergeFee(coeActivityFee); 
	}
}
