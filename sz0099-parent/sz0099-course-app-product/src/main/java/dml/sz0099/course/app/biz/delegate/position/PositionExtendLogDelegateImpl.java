package dml.sz0099.course.app.biz.delegate.position;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.position.PositionExtendLogService;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionExtendLog;
import dml.sz0099.course.app.persist.entity.position.PositionExtendLog;

/**
 * positionExtendLogServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class PositionExtendLogDelegateImpl implements PositionExtendLogDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendLogDelegateImpl.class);
	
	@Autowired
	private PositionExtendLogService positionExtendLogService;

	/**
	 * 根据Id查询PositionExtendLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PositionExtendLog findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		PositionExtendLog positionExtendLog = positionExtendLogService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, positionExtendLog);
		return positionExtendLog;
	}
	
	@Override
	public PositionExtendLog findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		PositionExtendLog positionExtendLog = positionExtendLogService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, positionExtendLog);
		return positionExtendLog;
	}
	
	/**
	 * 根据IdList查询PositionExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<PositionExtendLog> positionExtendLogList = positionExtendLogService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  positionExtendLogList);
		return positionExtendLogList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public PositionExtendLog persistEntity(PositionExtendLog positionExtendLog) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		PositionExtendLog entity = positionExtendLogService.persistEntity(positionExtendLog);
		Long id = positionExtendLog.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionExtendLog mergeEntity(PositionExtendLog positionExtendLog) {
		Long id = positionExtendLog.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		PositionExtendLog entity = positionExtendLogService.mergeEntity(positionExtendLog);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionExtendLog saveOrUpdate(PositionExtendLog positionExtendLog) {
		Long id = positionExtendLog.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		PositionExtendLog entity = positionExtendLogService.saveOrUpdate(positionExtendLog);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionExtendLog> findPage(PositionExtendLog positionExtendLog, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<PositionExtendLog> page = positionExtendLogService.findPage(positionExtendLog, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionExtendLogService.existById(id);
	}
	
	@Override
	public PositionExtendLog persistForFail(PositionExtend positionExtend) {
		return positionExtendLogService.persistForFail(positionExtend);
	}
}
