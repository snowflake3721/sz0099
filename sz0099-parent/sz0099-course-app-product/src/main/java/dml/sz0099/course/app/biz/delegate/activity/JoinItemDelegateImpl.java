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

import dml.sz0099.course.app.biz.service.activity.JoinItemService;
import dml.sz0099.course.app.persist.entity.activity.JoinItem;

/**
 * joinItemServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class JoinItemDelegateImpl implements JoinItemDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(JoinItemDelegateImpl.class);
	
	@Autowired
	private JoinItemService joinItemService;

	/**
	 * 根据Id查询JoinItem实体对象
	 * @param id
	 * @return
	 */
	@Override
	public JoinItem findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		JoinItem joinItem = joinItemService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, joinItem);
		return joinItem;
	}
	
	@Override
	public JoinItem findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		JoinItem joinItem = joinItemService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, joinItem);
		return joinItem;
	}
	
	/**
	 * 根据IdList查询JoinItem实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<JoinItem> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<JoinItem> joinItemList = joinItemService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  joinItemList);
		return joinItemList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public JoinItem persistEntity(JoinItem joinItem) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		JoinItem entity = joinItemService.persistEntity(joinItem);
		Long id = joinItem.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public JoinItem mergeEntity(JoinItem joinItem) {
		Long id = joinItem.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		JoinItem entity = joinItemService.mergeEntity(joinItem);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public JoinItem saveOrUpdate(JoinItem joinItem) {
		Long id = joinItem.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		JoinItem entity = joinItemService.saveOrUpdate(joinItem);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<JoinItem> findPage(JoinItem joinItem, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<JoinItem> page = joinItemService.findPage(joinItem, pageable);
		return page;
	}

	@Override
	public List<JoinItem> findByMainId(JoinItem joinItem) {
		return joinItemService.findByMainId(joinItem);
	}
	
	public JoinItem addJoinItem(JoinItem joinItem) {
		return joinItemService.addJoinItem(joinItem);
	}
	
	public JoinItem deleteJoinItem(JoinItem joinItem) {
		return joinItemService.deleteJoinItem(joinItem);
	}
	
	public Long countByMainId(Long activityId) {
		return joinItemService.countByMainId(activityId);
	}

	@Override
	public List<JoinItem> findByMainId(Long activityId) {
		return joinItemService.findByMainId(activityId);
	}
	
	public List<JoinItem> findByBaseId(Long activityId) {
		return joinItemService.findByBaseId(activityId);
	}
	
	public Map<Long, List<JoinItem>> findMapByMainIdList(List<Long> mainIdList) {
		return joinItemService.findMapByMainIdList(mainIdList);
	}

	@Override
	public JoinItem mergeJoinTime(JoinItem joinItem) {
		return joinItemService.mergeJoinTime(joinItem);
	}

	@Override
	public JoinItem mergePlace(JoinItem joinItem) {
		return joinItemService.mergePlace(joinItem);
	}

	@Override
	public JoinItem mergeDescription(JoinItem joinItem) {
		return joinItemService.mergeDescription(joinItem);
	}
	
}
