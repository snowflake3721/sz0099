package dml.sz0099.course.app.biz.service.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import dml.sz0099.course.app.biz.service.tag.CoeTagService;
import dml.sz0099.course.app.persist.dao.activity.JoinItemDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.activity.JoinItem;


/**
 * 
 * @formatter:off
 * description: JoinItemServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class JoinItemServiceImpl extends GenericServiceImpl<JoinItem, Long> implements JoinItemService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(JoinItemServiceImpl.class);

	@Autowired
	private JoinItemDao joinItemDao;
	
	@Autowired
	private CoeTagService coeTagService;
	
	@Autowired
	private CoeActivityService coeActivityService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = joinItemDao;
	}

	/**
	 * 根据Id查询JoinItem实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public JoinItem findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		JoinItem joinItem = joinItemDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, joinItem);
		return joinItem;
	}
	
	@Override
	public JoinItem findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		JoinItem joinItem = joinItemDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, joinItem);
		return joinItem;
	}

	/**
	 * 根据IdList查询JoinItem实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<JoinItem> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<JoinItem> joinItemList = joinItemDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", joinItemList);
		return joinItemList;
	}

	@Transactional
	@Override
	public JoinItem persistEntity(JoinItem joinItem) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		JoinItem entity = save(joinItem);
		Long id = joinItem.getId();
		entity.setOrderSeq(entity.getAid());
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(JoinItem.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public JoinItem mergeEntity(JoinItem joinItem) {
		Long id = joinItem.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		JoinItem entity = findById(id);
		if(entity != null) {
			entity.setBaseId(joinItem.getBaseId());
			entity.setDescription(joinItem.getDescription());
			entity.setPlace(joinItem.getPlace());
			entity.setJoinTime(joinItem.getJoinTime());
			//entity.setMainId(mainId);
			entity.setLastModifiedBy(joinItem.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(JoinItem.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public JoinItem saveOrUpdate(JoinItem joinItem) {
		Long id = joinItem.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		JoinItem entity = null;
		if(null != id) {
			entity = mergeEntity(joinItem);
		}else {
			entity = persistEntity(joinItem);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<JoinItem> findPage(JoinItem joinItem, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<JoinItem> page = joinItemDao.findPage(joinItem, pageable);
		return page;
	}
	
	@Transactional
	public JoinItem addJoinItem(JoinItem joinItem) {
		joinItem=persistEntity(joinItem);
		return joinItem;
	}
	
	@Transactional
	public JoinItem addJoinItem(CoeActivityTime actTime) {
		JoinItem joinItem = new JoinItem();
		Long userId = actTime.getUserId();
		joinItem.setBaseId(actTime.getId());
		joinItem.setUserId(userId);
		joinItem.setCreatedBy(userId);
		joinItem.setLastModifiedBy(userId);
		joinItem.setMainId(actTime.getMainId());
		//joinItem.setJoinTime(actTime.getBeginTime());
		joinItem=addJoinItem(joinItem);
		List<JoinItem> joinItemList = actTime.getJoinItemList();
		if(null == joinItemList) {
			joinItemList = new ArrayList<>();
			actTime.setJoinItemList(joinItemList);
		}
		joinItemList.add(joinItem);
		return joinItem;
	}
	
	@Transactional
	public JoinItem deleteJoinItem(JoinItem joinItem) {
		Long pTagId = joinItem.getId();
		Long baseId = joinItem.getBaseId();
		JoinItem entity = findById(pTagId);
		if (null != entity) {
			Long entityBaseId = entity.getBaseId();
			if (entityBaseId.equals(baseId)) {
				delete(entity);
				joinItem.setSuccess(JoinItem.SUCCESS_YES);
			}
		}

		return joinItem;
	}
	
	public List<JoinItem> findByMainId(JoinItem joinItem) {
		Long activityId = joinItem.getMainId();
		if (null != activityId) {
			List<JoinItem> joinItemList = joinItemDao.findByMainId(activityId);
			return joinItemList;
		}
		return null;
	}
	
	public Long countByMainId(Long activityId) {
		return joinItemDao.countByMainId(activityId);
	}
	
	public List<JoinItem> findByMainId(Long activityId) {
		return joinItemDao.findByMainId(activityId);
	}
	
	public Map<Long, List<JoinItem>> findMapByMainIdList(List<Long> mainIdList) {
		List<JoinItem> content = joinItemDao.findByMainIdList(mainIdList);
		Map<Long, List<JoinItem>> map = new HashMap<>();
		if(null != content && !content.isEmpty()) {
			for(JoinItem c : content) {
				Long mainId = c.getMainId();
				List<JoinItem> entityList = map.get(mainId);
				if(null == entityList) {
					entityList = new ArrayList<>();
					map.put(mainId, entityList);
				}
				entityList.add(c);
			}
		}
		return map;
	}
	
	@Override
	public List<JoinItem> findByBaseId(Long baseId) {
		return joinItemDao.findByBaseId(baseId);
	}

	@Transactional
	@Override
	public JoinItem mergeJoinTime(JoinItem joinItem) {
		Long id = joinItem.getId();
		JoinItem entity = findById(id);
		if(null != entity) {
			entity.setJoinTime(joinItem.getJoinTime());
			entity.setLastModifiedBy(joinItem.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityTime.SUCCESS_YES);
			save(entity);
		}
		return entity;
	}

	@Transactional
	@Override
	public JoinItem mergePlace(JoinItem joinItem) {
		Long id = joinItem.getId();
		JoinItem entity = findById(id);
		if(null != entity) {
			entity.setPlace(joinItem.getPlace());
			entity.setLastModifiedBy(joinItem.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityTime.SUCCESS_YES);
			save(entity);
		}
		return entity;
	}

	@Transactional
	@Override
	public JoinItem mergeDescription(JoinItem joinItem) {
		Long id = joinItem.getId();
		JoinItem entity = findById(id);
		if(null != entity) {
			entity.setDescription(joinItem.getDescription());
			entity.setLastModifiedBy(joinItem.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityTime.SUCCESS_YES);
			save(entity);
		}
		return entity;
	}

}
