package dml.sz0099.course.app.biz.delegate.user;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.delegate.user.CoeUserBindDelegate;
import dml.sz0099.course.app.biz.service.user.CoeUserBindService;
import dml.sz0099.course.app.persist.entity.user.CoeUserBind;

/**
 * coeUserBindServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeUserBindDelegateImpl implements CoeUserBindDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserBindDelegateImpl.class);
	
	@Autowired
	private CoeUserBindService coeUserBindService;

	/**
	 * 根据Id查询CoeUserBind实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserBind findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeUserBind coeUserBind = coeUserBindService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeUserBind);
		return coeUserBind;
	}
	
	@Override
	public CoeUserBind findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserBind coeUserBind = coeUserBindService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserBind);
		return coeUserBind;
	}
	
	/**
	 * 根据IdList查询CoeUserBind实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserBind> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeUserBind> coeUserBindList = coeUserBindService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeUserBindList);
		return coeUserBindList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeUserBind persistEntity(CoeUserBind coeUserBind) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeUserBind entity = coeUserBindService.persistEntity(coeUserBind);
		Long id = coeUserBind.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserBind mergeEntity(CoeUserBind coeUserBind) {
		Long id = coeUserBind.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeUserBind entity = coeUserBindService.mergeEntity(coeUserBind);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserBind saveOrUpdate(CoeUserBind coeUserBind) {
		Long id = coeUserBind.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserBind entity = coeUserBindService.saveOrUpdate(coeUserBind);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserBind> findPage(CoeUserBind coeUserBind, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeUserBind> page = coeUserBindService.findPage(coeUserBind, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeUserBindService.existById(id);
	}
}
