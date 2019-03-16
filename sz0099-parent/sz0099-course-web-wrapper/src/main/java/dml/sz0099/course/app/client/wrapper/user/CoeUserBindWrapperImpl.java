package dml.sz0099.course.app.client.wrapper.user;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.jit4j.core.persist.page.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.user.CoeUserBindDelegate;
import dml.sz0099.course.app.persist.entity.user.CoeUserBind;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeUserBindWrapperImpl,组件封装
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
public class CoeUserBindWrapperImpl implements CoeUserBindWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserBindWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeUserBindDelegate coeUserBindDelegate;
	
	/**
	 * 根据Id查询CoeUserBind实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserBind findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeUserBind coeUserBind = coeUserBindDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeUserBind);
		return coeUserBind;
	}
	
	@Override
	public CoeUserBind findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserBind coeUserBind = coeUserBindDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserBind);
		return coeUserBind;
	}
	
	/**
	 * 根据IdList查询CoeUserBind实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserBind> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeUserBind> coeUserBindList = coeUserBindDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeUserBindList);
		return coeUserBindList;
	}
	
	@Override
	public CoeUserBind persistEntity(CoeUserBind coeUserBind) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeUserBind entity = coeUserBindDelegate.persistEntity(coeUserBind);
		Long id = coeUserBind.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserBind mergeEntity(CoeUserBind coeUserBind) {
		Long id = coeUserBind.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeUserBind entity = coeUserBindDelegate.mergeEntity(coeUserBind);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserBind saveOrUpdate(CoeUserBind coeUserBind) {
		Long id = coeUserBind.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserBind entity = coeUserBindDelegate.saveOrUpdate(coeUserBind);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserBind> findPage(CoeUserBind coeUserBind, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeUserBind> page = coeUserBindDelegate.findPage(coeUserBind, pageable);
		return page;
	}
	
	@Override
	public Page<CoeUserBind> findPageForHuan(CoeUserBind coeUserBind, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPageForHuan ---------  ");
		Long userId = coeUserBind.getUserId();
		if(null != userId) {
			coeUserBind.setStatus(CoeUserBind.STATUS_2_PASS.getValueInt());
			Page<CoeUserBind> page = coeUserBindDelegate.findPage(coeUserBind, pageable);
			return page;
		}
		return null;
	}
	
	public Page<CoeUserBind> findPageForHuanSingle(CoeUserBind coeUserBind){
		Pageable pageable = new PageRequest(0, 1,Direction.ASC, "topLevel");
		Page<CoeUserBind> page = findPageForHuan(coeUserBind, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeUserBindDelegate.existById(id);
	}
}
