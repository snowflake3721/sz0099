package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.activity.JoinItemDelegate;
import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.persist.entity.activity.JoinItem;
import dml.sz0099.course.app.persist.entity.activity.JoinItem;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * JoinItemWrapperImpl,组件封装
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
public class JoinItemWrapperImpl implements JoinItemWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JoinItemWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private JoinItemDelegate joinItemDelegate;
	
	/**
	 * 根据Id查询JoinItem实体对象
	 * @param id
	 * @return
	 */
	@Override
	public JoinItem findById(Long id) {
		LOGGER.debug("--- JoinItemWrapperImpl.findById begin --------- id is:{} ", id);
		JoinItem joinItem = joinItemDelegate.findById(id);
		LOGGER.debug("--- JoinItemWrapperImpl.findById end --------- id is:{} , result is {} ", id, joinItem);
		return joinItem;
	}
	
	@Override
	public JoinItem findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		JoinItem joinItem = joinItemDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, joinItem);
		return joinItem;
	}
	
	/**
	 * 根据IdList查询JoinItem实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<JoinItem> findByIdList(List<Long> idList) {
		LOGGER.debug("--- JoinItemWrapperImpl.findByIdList begin ---------  ");
		List<JoinItem> joinItemList = joinItemDelegate.findByIdList(idList);
		LOGGER.debug("--- JoinItemWrapperImpl.findByIdList end ---------  result is {} ",  joinItemList);
		return joinItemList;
	}
	
	@Override
	public JoinItem persistEntity(JoinItem joinItem) {
		LOGGER.debug("--- JoinItemWrapperImpl.persistEntity begin ---------  ");
		JoinItem entity = joinItemDelegate.persistEntity(joinItem);
		Long id = joinItem.getId();
		LOGGER.debug("--- JoinItemWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public JoinItem mergeEntity(JoinItem joinItem) {
		Long id = joinItem.getId();
		LOGGER.debug("--- JoinItemWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		JoinItem entity = joinItemDelegate.mergeEntity(joinItem);
		LOGGER.debug("--- JoinItemWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public JoinItem saveOrUpdate(JoinItem joinItem) {
		Long id = joinItem.getId();
		LOGGER.debug("--- JoinItemWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		JoinItem entity = joinItemDelegate.saveOrUpdate(joinItem);
		LOGGER.debug("--- JoinItemWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<JoinItem> findPage(JoinItem joinItem, Pageable pageable) {
		LOGGER.debug("--- JoinItemWrapperImpl.findPage ---------  ");
		Page<JoinItem> page = joinItemDelegate.findPage(joinItem, pageable);
		return page;
	}

	@Override
	public List<JoinItem> findByMainId(JoinItem joinItem) {
		
		return joinItemDelegate.findByMainId(joinItem);
	}
	
	public JoinItem addJoinItem(JoinItem joinItem) {
		return joinItemDelegate.addJoinItem(joinItem);
	}

	@Override
	public JoinItem deleteJoinItem(JoinItem joinItem) {
		return joinItemDelegate.deleteJoinItem(joinItem);
	}

	@Override
	public Long countByMainId(Long activityId) {
		return joinItemDelegate.countByMainId(activityId);
	}
	
	public List<JoinItem> findByMainId(Long activityId) {
		return joinItemDelegate.findByMainId(activityId);
	}
	
	public List<JoinItem> findByBaseId(Long baseId) {
		return joinItemDelegate.findByBaseId(baseId);
	}
	
	@Override
	public Map<Long, List<JoinItem>> findMapByMainIdList(List<Long> activityIdList) {
		return joinItemDelegate.findMapByMainIdList(activityIdList);
	}

	@Override
	public JoinItem mergeJoinTime(JoinItem joinItem) {
		return joinItemDelegate.mergeJoinTime(joinItem);
	}

	@Override
	public JoinItem mergePlace(JoinItem joinItem) {
		String place = joinItem.getPlace();
		if(StringUtils.isNotBlank(place)) {
			joinItem.setPlace(HtmlUtil.textarea2Escape(place));
		}
		return joinItemDelegate.mergePlace(joinItem);
	}

	@Override
	public JoinItem mergeDescription(JoinItem joinItem) {
		String description = joinItem.getDescription();
		if(StringUtils.isNotBlank(description)) {
			joinItem.setDescription(HtmlUtil.textarea2Escape(description));
		}
		return joinItemDelegate.mergeDescription(joinItem);
	}
	
}
