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

import dml.sz0099.course.app.biz.service.activity.CoeActivityOrderLogService;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrderLog;

/**
 * coeActivityOrderServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeActivityOrderLogDelegateImpl implements CoeActivityOrderLogDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityOrderLogDelegateImpl.class);
	
	@Autowired
	private CoeActivityOrderLogService coeActivityOrderLogService;

	@Override
	public CoeActivityOrderLog findById(Long id) {
		return coeActivityOrderLogService.findById(id);
	}

	@Override
	public CoeActivityOrderLog findByAid(Long aid) {
		return coeActivityOrderLogService.findByAid(aid);
	}

	@Override
	public List<CoeActivityOrderLog> findByIdList(List<Long> idList) {
		return coeActivityOrderLogService.findByIdList(idList);
	}

	@Override
	public CoeActivityOrderLog persistEntity(CoeActivityOrderLog orderLog) {
		return coeActivityOrderLogService.persistEntity(orderLog);
	}

	@Override
	public CoeActivityOrderLog mergeEntity(CoeActivityOrderLog orderLog) {
		return coeActivityOrderLogService.mergeEntity(orderLog);
	}

	@Override
	public CoeActivityOrderLog saveOrUpdate(CoeActivityOrderLog orderLog) {
		return coeActivityOrderLogService.saveOrUpdate(orderLog);
	}

	@Override
	public Page<CoeActivityOrderLog> findPage(CoeActivityOrderLog orderLog, Pageable pageable) {
		return coeActivityOrderLogService.findPage(orderLog, pageable);
	}

	@Override
	public CoeActivityOrderLog findByMainId(CoeActivityOrderLog orderLog) {
		return coeActivityOrderLogService.findByMainId(orderLog);
	}

	@Override
	public Long countByMainId(Long activityId) {
		return coeActivityOrderLogService.countByMainId(activityId);
	}

	@Override
	public CoeActivityOrderLog findByMainId(Long activityId) {
		return coeActivityOrderLogService.findByMainId(activityId);
	}

	@Override
	public Map<Long, CoeActivityOrderLog> findMapByMainIdList(List<Long> mainIdList) {
		return coeActivityOrderLogService.findMapByMainIdList(mainIdList);
	}

	@Override
	public Map<Long, CoeActivityOrderLog> findMapByOrderIdList(List<Long> orderIdList) {
		return coeActivityOrderLogService.findMapByOrderIdList(orderIdList);
	}

	@Override
	public CoeActivityOrderLog create(CoeActivityOrder order) {
		return coeActivityOrderLogService.create(order);
	}

}
