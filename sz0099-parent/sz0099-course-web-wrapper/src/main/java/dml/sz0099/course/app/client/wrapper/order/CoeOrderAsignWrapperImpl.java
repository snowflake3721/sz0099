package dml.sz0099.course.app.client.wrapper.order;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.order.CoeOrderAsignDelegate;
import dml.sz0099.course.app.persist.entity.order.CoeOrderAsign;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOrderAsignWrapperImpl,组件封装
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
public class CoeOrderAsignWrapperImpl implements CoeOrderAsignWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderAsignWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeOrderAsignDelegate coeOrderAsignDelegate;
	
	/**
	 * 根据Id查询CoeOrderAsign实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderAsign findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeOrderAsign coeOrderAsign = coeOrderAsignDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeOrderAsign);
		return coeOrderAsign;
	}
	
	@Override
	public CoeOrderAsign findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderAsign coeOrderAsign = coeOrderAsignDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderAsign);
		return coeOrderAsign;
	}
	
	/**
	 * 根据IdList查询CoeOrderAsign实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderAsign> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeOrderAsign> coeOrderAsignList = coeOrderAsignDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeOrderAsignList);
		return coeOrderAsignList;
	}
	
	@Override
	public CoeOrderAsign persistEntity(CoeOrderAsign coeOrderAsign) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeOrderAsign entity = coeOrderAsignDelegate.persistEntity(coeOrderAsign);
		Long id = coeOrderAsign.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderAsign mergeEntity(CoeOrderAsign coeOrderAsign) {
		Long id = coeOrderAsign.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderAsign entity = coeOrderAsignDelegate.mergeEntity(coeOrderAsign);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderAsign saveOrUpdate(CoeOrderAsign coeOrderAsign) {
		Long id = coeOrderAsign.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderAsign entity = coeOrderAsignDelegate.saveOrUpdate(coeOrderAsign);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderAsign> findPage(CoeOrderAsign coeOrderAsign, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeOrderAsign> page = coeOrderAsignDelegate.findPage(coeOrderAsign, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeOrderAsignDelegate.existById(id);
	}
}
