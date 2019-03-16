package dml.sz0099.course.app.client.wrapper.position;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.position.PositionExtendLogDelegate;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionExtendLog;
import dml.sz0099.course.app.persist.entity.position.PositionExtendLog;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionExtendLogWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class PositionExtendLogWrapperImpl implements PositionExtendLogWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendLogWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private PositionExtendLogDelegate positionExtendLogDelegate;
	
	/**
	 * 根据Id查询PositionExtendLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PositionExtendLog findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		PositionExtendLog positionExtendLog = positionExtendLogDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, positionExtendLog);
		return positionExtendLog;
	}
	
	@Override
	public PositionExtendLog findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		PositionExtendLog positionExtendLog = positionExtendLogDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, positionExtendLog);
		return positionExtendLog;
	}
	
	/**
	 * 根据IdList查询PositionExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<PositionExtendLog> positionExtendLogList = positionExtendLogDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  positionExtendLogList);
		return positionExtendLogList;
	}
	
	@Override
	public PositionExtendLog persistEntity(PositionExtendLog positionExtendLog) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		PositionExtendLog entity = positionExtendLogDelegate.persistEntity(positionExtendLog);
		Long id = positionExtendLog.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionExtendLog mergeEntity(PositionExtendLog positionExtendLog) {
		Long id = positionExtendLog.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		PositionExtendLog entity = positionExtendLogDelegate.mergeEntity(positionExtendLog);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionExtendLog saveOrUpdate(PositionExtendLog positionExtendLog) {
		Long id = positionExtendLog.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		PositionExtendLog entity = positionExtendLogDelegate.saveOrUpdate(positionExtendLog);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionExtendLog> findPage(PositionExtendLog positionExtendLog, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<PositionExtendLog> page = positionExtendLogDelegate.findPage(positionExtendLog, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return positionExtendLogDelegate.existById(id);
	}
	
	@Override
	public PositionExtendLog persistForFail(PositionExtend positionExtend) {
		return positionExtendLogDelegate.persistForFail(positionExtend);
	}
}
