package dml.sz0099.course.app.persist.dao.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jit4j.app.persist.entity.order.Order;
import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;
import dml.sz0099.course.app.persist.entity.activity.vo.CoeActivityUserVo;
import dml.sz0099.course.app.persist.repository.activity.CoeActivityUserRepository;
import dml.sz0099.course.app.persist.specification.activity.CoeActivityUserSpecification;

/**
 * CoeActivityUserDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeActivityUserDaoImpl extends GenericDaoImpl<CoeActivityUser, Long> implements CoeActivityUserDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityUserDaoImpl.class);
	
	@Autowired
	private CoeActivityUserRepository coeActivityUserRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeActivityUserRepository;
	}

	/**
	 * 根据Id查询CoeActivityUser实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityUser findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeActivityUser coeActivityUser = coeActivityUserRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeActivityUser);
		return coeActivityUser;
	}
	
	@Override
	public CoeActivityUser findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityUser coeActivityUser = coeActivityUserRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityUser);
		return coeActivityUser;
	}
	
	/**
	 * 根据IdList查询CoeActivityUser实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityUser> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeActivityUser> coeActivityUserList = coeActivityUserRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeActivityUserList);
		return coeActivityUserList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeActivityUser> findPage(CoeActivityUser coeActivityUser, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeActivityUserSpecification.getConditionWithQsl(coeActivityUser);
		Page<CoeActivityUser> page = coeActivityUserRepository.findAll(condition, pageable);
		return page;
	}
	
	public Page<CoeActivityUser> findPageWithNotself(CoeActivityUser coeActivityUser, Pageable pageable) {
		LOGGER.debug("--- dao.findPageWithNotself ---------  ");
		BooleanExpression condition = CoeActivityUserSpecification.getConditionWithNotself(coeActivityUser);
		Page<CoeActivityUser> page = coeActivityUserRepository.findAll(condition, pageable);
		return page;
	}
	
	public Long countByMainId(Long mainId) {
		return coeActivityUserRepository.countByMainId(mainId);
	}
	
	public List<CoeActivityUser> findByMainId(Long mainId){
		List<Integer> statusList = new ArrayList<>();
		statusList.add(Order.ORDER_STATUS_GENERATED.getValueInt());
		statusList.add(Order.ORDER_STATUS_PAY_BEGIN.getValueInt());
		statusList.add(Order.ORDER_STATUS_CHECKOUT.getValueInt());
		statusList.add(Order.ORDER_STATUS_PAY_SUCCESS.getValueInt());
		statusList.add(Order.ORDER_STATUS_WAIT_RUN.getValueInt());
		statusList.add(Order.ORDER_STATUS_SENT.getValueInt());
		statusList.add(Order.ORDER_STATUS_RECIEVED.getValueInt());
		statusList.add(Order.ORDER_STATUS_FINISHED.getValueInt());
		return coeActivityUserRepository.findByMainId(mainId, statusList);
	}
	
	public Page<CoeActivityUser> findByMainId(Long mainId, Pageable pageable){
		List<Integer> statusList = new ArrayList<>();
		statusList.add(Order.ORDER_STATUS_GENERATED.getValueInt());
		statusList.add(Order.ORDER_STATUS_PAY_BEGIN.getValueInt());
		statusList.add(Order.ORDER_STATUS_CHECKOUT.getValueInt());
		statusList.add(Order.ORDER_STATUS_PAY_SUCCESS.getValueInt());
		statusList.add(Order.ORDER_STATUS_WAIT_RUN.getValueInt());
		statusList.add(Order.ORDER_STATUS_SENT.getValueInt());
		statusList.add(Order.ORDER_STATUS_RECIEVED.getValueInt());
		statusList.add(Order.ORDER_STATUS_FINISHED.getValueInt());
		return coeActivityUserRepository.findByMainId(mainId, statusList, pageable);
	}
	
	public List<CoeActivityUser> findByBaseId(Long baseId){
		return coeActivityUserRepository.findByBaseId(baseId);
	}
	
	public CoeActivityUser findByBaseIdAndIdentity(Long baseId, String identity) {
		return coeActivityUserRepository.findByBaseIdAndIdentity(baseId,identity);
	}
	
	@Override
	public List<CoeActivityUser> findByMainIdList(List<Long> activityIdList) {
		return coeActivityUserRepository.findByMainIdList(activityIdList);
	}
	
	public boolean existEffectiveUser(Long mainId, String identity, Long baseId) {
		List<CoeActivityUser> userList = coeActivityUserRepository.findEffectiveUser( mainId,  identity, baseId);
		if(null != userList && !userList.isEmpty()) {
			for(CoeActivityUser user : userList) {
				Long eBaseId = user.getBaseId();
				if(eBaseId.equals(baseId)) {
					continue;
				}
				Integer status = user.getStatus();
				if (!status.equals(Order.ORDER_STATUS_INIT.getValueInt()) 
						&& !status.equals(Order.ORDER_STATUS_CLOSED.getValueInt())
						&& !status.equals(Order.ORDER_STATUS_CANCEL.getValueInt())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Long countPayVerifyUsers(Long mainId) {
		List<Integer> statusList = new ArrayList<>();
		statusList.add(Order.ORDER_STATUS_GENERATED.getValueInt());
		statusList.add(Order.ORDER_STATUS_PAY_BEGIN.getValueInt());
		statusList.add(Order.ORDER_STATUS_CHECKOUT.getValueInt());
		statusList.add(Order.ORDER_STATUS_PAY_SUCCESS.getValueInt());
		statusList.add(Order.ORDER_STATUS_WAIT_RUN.getValueInt());
		statusList.add(Order.ORDER_STATUS_SENT.getValueInt());
		statusList.add(Order.ORDER_STATUS_RECIEVED.getValueInt());
		statusList.add(Order.ORDER_STATUS_FINISHED.getValueInt());
		return coeActivityUserRepository.countPayVerifyUsers(mainId, statusList);
	}
	
	public Long countByBaseId(Long baseId) {
		return coeActivityUserRepository.countByBaseId(baseId);
	}
	
	public List<CoeActivityUserVo> countByMainIdList(List<Long> mainIdList) {
		List<Integer> statusList = new ArrayList<>();
		statusList.add(Order.ORDER_STATUS_GENERATED.getValueInt());
		statusList.add(Order.ORDER_STATUS_PAY_BEGIN.getValueInt());
		statusList.add(Order.ORDER_STATUS_CHECKOUT.getValueInt());
		statusList.add(Order.ORDER_STATUS_PAY_SUCCESS.getValueInt());
		statusList.add(Order.ORDER_STATUS_WAIT_RUN.getValueInt());
		statusList.add(Order.ORDER_STATUS_SENT.getValueInt());
		statusList.add(Order.ORDER_STATUS_RECIEVED.getValueInt());
		statusList.add(Order.ORDER_STATUS_FINISHED.getValueInt());
		return coeActivityUserRepository.countByMainIdList(mainIdList, statusList);
	}

}
