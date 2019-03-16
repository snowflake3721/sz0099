/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;
import java.util.Map;

import org.jit4j.app.persist.entity.auth.UserRole;
import org.jit4j.app.persist.entity.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.activity.CoeActivityUserDelegate;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;
import dml.sz0099.course.app.persist.entity.activity.vo.CoeActivityUserVo;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2019-01-05 10:28:06
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2019-01-05	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class CoeActivityUserWrapperImpl implements CoeActivityUserWrapper {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityUserWrapperImpl.class);

	@Autowired
	private CoeActivityUserDelegate coeActivityUserDelegate;
	
	@Override
	public CoeActivityUser findById(Long id) {
		return coeActivityUserDelegate.findById(id);
	}

	@Override
	public CoeActivityUser findByAid(Long aid) {
		return coeActivityUserDelegate.findByAid(aid);
	}

	@Override
	public List<CoeActivityUser> findByIdList(List<Long> idList) {
		return coeActivityUserDelegate.findByIdList(idList);
	}

	@Override
	public CoeActivityUser persistEntity(CoeActivityUser coeActivityUser) {
		return coeActivityUserDelegate.persistEntity(coeActivityUser);
	}

	@Override
	public CoeActivityUser mergeEntity(CoeActivityUser coeActivityUser) {
		return coeActivityUserDelegate.mergeEntity(coeActivityUser);
	}

	@Override
	public CoeActivityUser saveOrUpdate(CoeActivityUser coeActivityUser) {
		return coeActivityUserDelegate.saveOrUpdate(coeActivityUser);
	}

	@Override
	public Page<CoeActivityUser> findPage(CoeActivityUser coeActivityUser, Pageable pageable) {
		
		return coeActivityUserDelegate.findPage(coeActivityUser, pageable);
	}
	
	@Override
	public Page<CoeActivityUser> findPageByMainId(CoeActivityUser coeActivityUser, Pageable pageable) {
		
		return findPage(coeActivityUser, pageable);
	}

	@Override
	public List<CoeActivityUser> findByMainId(CoeActivityUser coeActivityUser) {
		return coeActivityUserDelegate.findByMainId(coeActivityUser);
	}

	@Override
	public CoeActivityUser addUser(CoeActivityUser coeActivityUser) {
		coeActivityUser.setStatus(Order.ORDER_STATUS_INIT.getValueInt());
		return coeActivityUserDelegate.addUser(coeActivityUser);
	}

	@Override
	public CoeActivityUser addUser(CoeActivityOrder order) {
		return coeActivityUserDelegate.addUser(order);
	}

	@Override
	public CoeActivityUser deleteUser(CoeActivityUser coeActivityUser) {
		return coeActivityUserDelegate.deleteUser(coeActivityUser);
	}

	@Override
	public Long countByMainId(Long activityId) {
		return coeActivityUserDelegate.countByMainId(activityId);
	}

	@Override
	public List<CoeActivityUser> findByMainId(Long activityId) {
		return coeActivityUserDelegate.findByMainId(activityId);
	}
	
	@Override
	public Page<CoeActivityUser> findByMainId(Long activityId, Pageable pageable) {
		return coeActivityUserDelegate.findByMainId(activityId, pageable);
	}

	@Override
	public List<CoeActivityUser> findByBaseId(Long baseId) {
		return coeActivityUserDelegate.findByBaseId(baseId);
	}
	
	@Override
	public CoeActivityUser findByBaseIdAndIdentity(Long baseId, String identity){
		return coeActivityUserDelegate.findByBaseIdAndIdentity(baseId, identity);
	}

	@Override
	public Map<Long, List<CoeActivityUser>> findMapByMainIdList(List<Long> mainIdList) {
		return coeActivityUserDelegate.findMapByMainIdList(mainIdList);
	}

	@Override
	public CoeActivityUser mergeMobile(CoeActivityUser coeActivityUser) {
		return coeActivityUserDelegate.mergeMobile(coeActivityUser);
	}

	@Override
	public CoeActivityUser mergeRealname(CoeActivityUser coeActivityUser) {
		return coeActivityUserDelegate.mergeRealname(coeActivityUser);
	}

	@Override
	public CoeActivityUser mergeIdentity(CoeActivityUser coeActivityUser) {
		return coeActivityUserDelegate.mergeIdentity(coeActivityUser);
	}

	@Override
	public boolean existEffectiveUser(Long mainId, String identity, Long baseId) {
		return coeActivityUserDelegate.existEffectiveUser( mainId,  identity,  baseId);
	}

	@Override
	public Long countPayVerifyUsers(Long mainId) {
		return coeActivityUserDelegate.countPayVerifyUsers(mainId);
	}
	
	public Long countByBaseId(Long baseId) {
		return coeActivityUserDelegate.countByBaseId(baseId);
	}

	@Override
	public List<CoeActivityUserVo> countByMainIdList(List<Long> mainIdList) {
		return coeActivityUserDelegate.countByMainIdList(mainIdList);
	}
	
	public UserRole applyClubLeader(UserRole userRole) {
		
		return null;
	}

}
