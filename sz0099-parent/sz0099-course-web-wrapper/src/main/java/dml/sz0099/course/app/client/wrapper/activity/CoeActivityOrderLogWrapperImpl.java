package dml.sz0099.course.app.client.wrapper.activity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.activity.CoeActivityOrderLogDelegate;
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
public class CoeActivityOrderLogWrapperImpl implements CoeActivityOrderLogWrapper, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityOrderLogWrapperImpl.class);
	
	@Autowired
	private CoeActivityOrderLogDelegate coeActivityOrderLogDelegate;

	@Override
	public CoeActivityOrderLog findById(Long id) {
		return coeActivityOrderLogDelegate.findById(id);
	}

	@Override
	public CoeActivityOrderLog findByAid(Long aid) {
		return coeActivityOrderLogDelegate.findByAid(aid);
	}

	@Override
	public List<CoeActivityOrderLog> findByIdList(List<Long> idList) {
		return coeActivityOrderLogDelegate.findByIdList(idList);
	}

	@Override
	public CoeActivityOrderLog persistEntity(CoeActivityOrderLog orderLog) {
		return coeActivityOrderLogDelegate.persistEntity(orderLog);
	}

	@Override
	public CoeActivityOrderLog mergeEntity(CoeActivityOrderLog orderLog) {
		return coeActivityOrderLogDelegate.mergeEntity(orderLog);
	}

	@Override
	public CoeActivityOrderLog saveOrUpdate(CoeActivityOrderLog orderLog) {
		return coeActivityOrderLogDelegate.saveOrUpdate(orderLog);
	}

	@Override
	public Page<CoeActivityOrderLog> findPage(CoeActivityOrderLog orderLog, Pageable pageable) {
		return coeActivityOrderLogDelegate.findPage(orderLog, pageable);
	}

	@Override
	public CoeActivityOrderLog findByMainId(CoeActivityOrderLog orderLog) {
		return coeActivityOrderLogDelegate.findByMainId(orderLog);
	}

	@Override
	public Long countByMainId(Long activityId) {
		return coeActivityOrderLogDelegate.countByMainId(activityId);
	}

	@Override
	public CoeActivityOrderLog findByMainId(Long activityId) {
		return coeActivityOrderLogDelegate.findByMainId(activityId);
	}

	@Override
	public Map<Long, CoeActivityOrderLog> findMapByMainIdList(List<Long> mainIdList) {
		return coeActivityOrderLogDelegate.findMapByMainIdList(mainIdList);
	}

	@Override
	public Map<Long, CoeActivityOrderLog> findMapByOrderIdList(List<Long> orderIdList) {
		return coeActivityOrderLogDelegate.findMapByOrderIdList(orderIdList);
	}

	@Override
	public CoeActivityOrderLog create(CoeActivityOrder order) {
		return coeActivityOrderLogDelegate.create(order);
	}

}
