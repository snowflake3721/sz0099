package dml.sz0099.course.app.biz.service.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.app.persist.entity.order.Order;
import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.service.tag.CoeTagService;
import dml.sz0099.course.app.persist.dao.activity.CoeActivityUserDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;
import dml.sz0099.course.app.persist.entity.activity.vo.CoeActivityUserVo;


/**
 * 
 * @formatter:off
 * description: CoeActivityUserServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeActivityUserServiceImpl extends GenericServiceImpl<CoeActivityUser, Long> implements CoeActivityUserService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityUserServiceImpl.class);

	@Autowired
	private CoeActivityUserDao coeActivityUserDao;
	
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
		this.genericDao = coeActivityUserDao;
	}

	/**
	 * 根据Id查询CoeActivityUser实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityUser findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeActivityUser coeActivityUser = coeActivityUserDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeActivityUser);
		return coeActivityUser;
	}
	
	@Override
	public CoeActivityUser findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityUser coeActivityUser = coeActivityUserDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityUser);
		return coeActivityUser;
	}

	/**
	 * 根据IdList查询CoeActivityUser实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityUser> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeActivityUser> coeActivityUserList = coeActivityUserDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeActivityUserList);
		return coeActivityUserList;
	}

	@Transactional
	@Override
	public CoeActivityUser persistEntity(CoeActivityUser coeActivityUser) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeActivityUser entity = save(coeActivityUser);
		Long id = coeActivityUser.getId();
		entity.setOrderSeq(entity.getAid());
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeActivityUser.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityUser mergeEntity(CoeActivityUser coeActivityUser) {
		Long id = coeActivityUser.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityUser entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeActivityUser.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeActivityUser.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityUser saveOrUpdate(CoeActivityUser coeActivityUser) {
		Long id = coeActivityUser.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityUser entity = null;
		if(null != id) {
			entity = mergeEntity(coeActivityUser);
		}else {
			entity = persistEntity(coeActivityUser);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityUser> findPage(CoeActivityUser coeActivityUser, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		// PAY_STATUS
		Integer payStatus = coeActivityUser.getPayStatus();
		List<Integer> statusList = new ArrayList<>();
		if(null == payStatus || CoeActivityUser.PAY_STATUS_ALL.getValueInt().equals(payStatus)) {
			statusList.add(Order.ORDER_STATUS_GENERATED.getValueInt());
			statusList.add(Order.ORDER_STATUS_PAY_BEGIN.getValueInt());
			statusList.add(Order.ORDER_STATUS_CHECKOUT.getValueInt());
			statusList.add(Order.ORDER_STATUS_PAY_SUCCESS.getValueInt());
			statusList.add(Order.ORDER_STATUS_WAIT_RUN.getValueInt());
			statusList.add(Order.ORDER_STATUS_SENT.getValueInt());
			statusList.add(Order.ORDER_STATUS_RECIEVED.getValueInt());
			statusList.add(Order.ORDER_STATUS_FINISHED.getValueInt());
		}else if(CoeActivityUser.PAY_STATUS_NO.getValueInt().equals(payStatus)) {
			statusList.add(Order.ORDER_STATUS_CANCEL.getValueInt());
			statusList.add(Order.ORDER_STATUS_GENERATED.getValueInt());
			statusList.add(Order.ORDER_STATUS_PAY_BEGIN.getValueInt());
			statusList.add(Order.ORDER_STATUS_CHECKOUT.getValueInt());
			statusList.add(Order.ORDER_STATUS_PAY_FAILURE.getValueInt());
		}else if(CoeActivityUser.PAY_STATUS_YES.getValueInt().equals(payStatus)) {
			statusList.add(Order.ORDER_STATUS_PAY_SUCCESS.getValueInt());
			statusList.add(Order.ORDER_STATUS_WAIT_RUN.getValueInt());
			statusList.add(Order.ORDER_STATUS_SENT.getValueInt());
			statusList.add(Order.ORDER_STATUS_RECIEVED.getValueInt());
			statusList.add(Order.ORDER_STATUS_FINISHED.getValueInt());
		}
		coeActivityUser.setStatusList(statusList);
		Page<CoeActivityUser> page = coeActivityUserDao.findPage(coeActivityUser, pageable);
		return page;
	}
	
	@Transactional
	public CoeActivityOrder cancelOrder(CoeActivityOrder order) {
		Long baseId = order.getId();
		List<CoeActivityUser> entityList = findByBaseId(baseId);
		if(null != entityList && !entityList.isEmpty()) {
			for(CoeActivityUser entity : entityList) {
				if(null != entity) {
					entity.setStatus(Order.ORDER_STATUS_CANCEL.getValueInt());
					entity.setLastModifiedBy(order.getLastModifiedBy());
					entity.setLastModifiedDate(order.getLastModifiedDate());
					entity.setSuccess(CoeActivityUser.SUCCESS_YES);
					save(entity);
				}
			}
			order.setUserList(entityList);
		}
		return order;
	}
	
	@Transactional
	public CoeActivityOrder confirmOrder(CoeActivityOrder order) {
		List<CoeActivityUser> userList = order.getUserList();
		Long baseId = order.getId();
		if(null != userList && !userList.isEmpty()) {
			List<CoeActivityUser> entityList = findByBaseId(baseId);
			if(null != entityList && !entityList.isEmpty()) {
				Map<Long,CoeActivityUser> map=new HashMap<>(entityList.size());
				for(CoeActivityUser entity : entityList) {
					Long id = entity.getId();
					map.put(id, entity);
				}
				
				for(CoeActivityUser user : userList) {
					Long id = user.getId();
					CoeActivityUser entity = map.get(id);
					if(null != entity) {
						entity.setIdentity(user.getIdentity());
						entity.setMobile(user.getMobile());
						entity.setRealname(user.getRealname());
						entity.setStatus(Order.ORDER_STATUS_GENERATED.getValueInt());
						entity.setLastModifiedBy(order.getLastModifiedBy());
						entity.setLastModifiedDate(order.getLastModifiedDate());
						entity.setSuccess(CoeActivityUser.SUCCESS_YES);
						save(entity);
					}
				}
			}
		}
		return order;
	}
	
	@Transactional
	public CoeActivityUser addUser(CoeActivityUser coeActivityUser) {
		coeActivityUser=persistEntity(coeActivityUser);
		return coeActivityUser;
	}
	
	@Transactional
	public CoeActivityUser addUser(CoeActivityOrder order) {
		CoeActivityUser actUser = new CoeActivityUser();
		Long userId = order.getUserId();
		actUser.setBaseId(order.getId());
		actUser.setUserId(userId);
		actUser.setCreatedBy(userId);
		actUser.setLastModifiedBy(userId);
		actUser.setMainId(order.getMainId());
		actUser.setStatus(Order.ORDER_STATUS_INIT.getValueInt());
		actUser.setInsuranceStatus(CoeActivityUser.INSURANCE_STATUS_NO.getValueInt());
		actUser=addUser(actUser);
		List<CoeActivityUser> userList = order.getUserList();
		if(null == userList) {
			userList = new ArrayList<>();
			order.setUserList(userList);
		}
		userList.add(actUser);
		return actUser;
	}
	
	@Transactional
	public CoeActivityUser deleteUser(CoeActivityUser coeActivityUser) {
		Long id = coeActivityUser.getId();
		Long baseId = coeActivityUser.getBaseId();
		CoeActivityUser entity = findById(id);
		if (null != entity) {
			Long entityBaseId = entity.getBaseId();
			if (entityBaseId.equals(baseId)) {
				delete(entity);
				coeActivityUser.setSuccess(CoeActivityUser.SUCCESS_YES);
			}
		}

		return coeActivityUser;
	}
	
	public List<CoeActivityUser> findByMainId(CoeActivityUser coeActivityUser) {
		Long activityId = coeActivityUser.getMainId();
		if (null != activityId) {
			List<CoeActivityUser> coeActivityUserList = coeActivityUserDao.findByMainId(activityId);
			return coeActivityUserList;
		}
		return null;
	}
	
	public Long countByMainId(Long activityId) {
		return coeActivityUserDao.countByMainId(activityId);
	}
	
	public List<CoeActivityUser> findByMainId(Long activityId) {
		return coeActivityUserDao.findByMainId(activityId);
	}
	public Page<CoeActivityUser> findByMainId(Long activityId, Pageable pageable){
		return coeActivityUserDao.findByMainId(activityId, pageable);
	}
	
	public Map<Long, List<CoeActivityUser>> findMapByMainIdList(List<Long> mainIdList) {
		List<CoeActivityUser> content = coeActivityUserDao.findByMainIdList(mainIdList);
		Map<Long, List<CoeActivityUser>> map = new HashMap<>();
		if(null != content && !content.isEmpty()) {
			for(CoeActivityUser c : content) {
				Long mainId = c.getMainId();
				List<CoeActivityUser> entityList = map.get(mainId);
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
	public List<CoeActivityUser> findByBaseId(Long baseId) {
		return coeActivityUserDao.findByBaseId(baseId);
	}
	
	public CoeActivityUser findByBaseIdAndIdentity(Long baseId, String identity) {
		return coeActivityUserDao.findByBaseIdAndIdentity(baseId,identity);
	}

	@Transactional
	@Override
	public CoeActivityUser mergeMobile(CoeActivityUser coeActivityUser) {
		Long id = coeActivityUser.getId();
		CoeActivityUser entity = findById(id);
		if(null != entity) {
			entity.setMobile(coeActivityUser.getMobile());
			entity.setLastModifiedBy(coeActivityUser.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
		}
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityUser mergeRealname(CoeActivityUser coeActivityUser) {
		Long id = coeActivityUser.getId();
		CoeActivityUser entity = findById(id);
		if(null != entity) {
			entity.setRealname(coeActivityUser.getRealname());
			entity.setLastModifiedBy(coeActivityUser.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
		}
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityUser mergeIdentity(CoeActivityUser coeActivityUser) {
		Long id = coeActivityUser.getId();
		CoeActivityUser entity = findById(id);
		if(null != entity) {
			entity.setIdentity(coeActivityUser.getIdentity());
			entity.setLastModifiedBy(coeActivityUser.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityOrder.SUCCESS_YES);
		}
		return entity;
	}
	
	public boolean existEffectiveUser(Long mainId, String identity, Long baseId) {
		return coeActivityUserDao.existEffectiveUser( mainId,  identity,  baseId);
	}
	
	public Long countPayVerifyUsers(Long mainId) {
		return coeActivityUserDao.countPayVerifyUsers(mainId);
	}
	
	public Long countByBaseId(Long baseId) {
		return coeActivityUserDao.countByBaseId(baseId);
	}

	@Override
	public CoeActivityUser mergeStatus(CoeActivityUser coeActivityUser) {
		Long id = coeActivityUser.getId();
		CoeActivityUser entity = findById(id);
		if(null != entity) {
			entity.setStatus(coeActivityUser.getStatus());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
		}
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityOrder mergeStatus(CoeActivityOrder order) {
		if(null != order) {
			Long baseId = order.getId();
			Integer orderStatus = order.getOrderStatus();
			List<CoeActivityUser>  userList = findByBaseId(baseId);
			if(null != userList && !userList.isEmpty()) {
				for(CoeActivityUser entity : userList) {
					if(null != entity) {
						entity.setStatus(orderStatus);
						DateTime lastModifiedDate = new DateTime();
						entity.setLastModifiedDate(lastModifiedDate);
						save(entity);
					}
				}
			}
		}
		return order;
	}
	
	public List<CoeActivityUserVo> countByMainIdList(List<Long> mainIdList) {
		return coeActivityUserDao.countByMainIdList(mainIdList);
	}

}
