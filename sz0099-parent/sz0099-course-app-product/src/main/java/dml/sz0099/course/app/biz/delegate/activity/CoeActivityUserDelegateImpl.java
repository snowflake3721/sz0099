/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.biz.delegate.activity;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.activity.CoeActivityUserService;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;
import dml.sz0099.course.app.persist.entity.activity.vo.CoeActivityUserVo;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2019-01-05 01:14:18
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2019-01-05	basic init
 * 
 * @formatter:on
 * </pre>
 */

@Service
public class CoeActivityUserDelegateImpl implements CoeActivityUserDelegate {

	@Autowired
	private CoeActivityUserService coeActivityUserService;
	
	@Override
	public CoeActivityUser findById(Long id) {
		return coeActivityUserService.findById(id);
	}

	@Override
	public CoeActivityUser findByAid(Long aid) {
		return coeActivityUserService.findByAid(aid);
	}

	@Override
	public List<CoeActivityUser> findByIdList(List<Long> idList) {
		return coeActivityUserService.findByIdList(idList);
	}

	@Override
	public CoeActivityUser persistEntity(CoeActivityUser coeActivityUser) {
		return coeActivityUserService.persistEntity(coeActivityUser);
	}

	@Override
	public CoeActivityUser mergeEntity(CoeActivityUser coeActivityUser) {
		return coeActivityUserService.mergeEntity(coeActivityUser);
	}

	@Override
	public CoeActivityUser saveOrUpdate(CoeActivityUser coeActivityUser) {
		return coeActivityUserService.saveOrUpdate(coeActivityUser);
	}

	@Override
	public Page<CoeActivityUser> findPage(CoeActivityUser coeActivityUser, Pageable pageable) {
		return coeActivityUserService.findPage(coeActivityUser, pageable);
	}

	@Override
	public List<CoeActivityUser> findByMainId(CoeActivityUser coeActivityUser) {
		return coeActivityUserService.findByMainId(coeActivityUser);
	}

	@Override
	public CoeActivityUser addUser(CoeActivityUser coeActivityUser) {
		return coeActivityUserService.addUser(coeActivityUser);
	}

	@Override
	public CoeActivityUser addUser(CoeActivityOrder order) {
		return coeActivityUserService.addUser(order);
	}

	@Override
	public CoeActivityUser deleteUser(CoeActivityUser coeActivityUser) {
		return coeActivityUserService.deleteUser(coeActivityUser);
	}

	@Override
	public Long countByMainId(Long activityId) {
		return coeActivityUserService.countByMainId(activityId);
	}

	@Override
	public List<CoeActivityUser> findByMainId(Long activityId) {
		return coeActivityUserService.findByMainId(activityId);
	}
	
	public Page<CoeActivityUser> findByMainId(Long activityId, Pageable pageable) {
		return coeActivityUserService.findByMainId(activityId, pageable);
	}

	@Override
	public List<CoeActivityUser> findByBaseId(Long baseId) {
		return coeActivityUserService.findByBaseId(baseId);
	}
	
	public CoeActivityUser findByBaseIdAndIdentity(Long baseId, String identity) {
		return coeActivityUserService.findByBaseIdAndIdentity(baseId,identity);
	}

	@Override
	public Map<Long, List<CoeActivityUser>> findMapByMainIdList(List<Long> mainIdList) {
		return coeActivityUserService.findMapByMainIdList(mainIdList);
	}

	@Override
	public CoeActivityUser mergeMobile(CoeActivityUser coeActivityUser) {
		return coeActivityUserService.mergeMobile(coeActivityUser);
	}

	@Override
	public CoeActivityUser mergeRealname(CoeActivityUser coeActivityUser) {
		return coeActivityUserService.mergeRealname(coeActivityUser);
	}

	@Override
	public CoeActivityUser mergeIdentity(CoeActivityUser coeActivityUser) {
		return coeActivityUserService.mergeIdentity(coeActivityUser);
	}
	
	public boolean existEffectiveUser(Long mainId, String identity, Long baseId) {
		return coeActivityUserService.existEffectiveUser( mainId,  identity,  baseId);
	}
	
	public Long countPayVerifyUsers(Long mainId) {
		return coeActivityUserService.countPayVerifyUsers(mainId);
	}
	
	public Long countByBaseId(Long baseId) {
		return coeActivityUserService.countByBaseId(baseId);
	}
	
	public List<CoeActivityUserVo> countByMainIdList(List<Long> mainIdList) {
		return coeActivityUserService.countByMainIdList(mainIdList);
	}

}
