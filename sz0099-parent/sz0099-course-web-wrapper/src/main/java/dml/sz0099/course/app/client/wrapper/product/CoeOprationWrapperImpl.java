package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.product.CoeOprationDelegate;
import dml.sz0099.course.app.persist.entity.product.CoeOpration;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOprationWrapperImpl,组件封装
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
public class CoeOprationWrapperImpl implements CoeOprationWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOprationWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeOprationDelegate coeOprationDelegate;
	
	/**
	 * 根据Id查询CoeOpration实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOpration findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeOpration coeOpration = coeOprationDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeOpration);
		return coeOpration;
	}
	
	@Override
	public CoeOpration findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeOpration coeOpration = coeOprationDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOpration);
		return coeOpration;
	}
	
	/**
	 * 根据IdList查询CoeOpration实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOpration> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeOpration> coeOprationList = coeOprationDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeOprationList);
		return coeOprationList;
	}
	
	@Override
	public CoeOpration persistEntity(CoeOpration coeOpration) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeOpration entity = coeOprationDelegate.persistEntity(coeOpration);
		Long id = coeOpration.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOpration mergeEntity(CoeOpration coeOpration) {
		Long id = coeOpration.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeOpration entity = coeOprationDelegate.mergeEntity(coeOpration);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOpration saveOrUpdate(CoeOpration coeOpration) {
		Long id = coeOpration.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOpration entity = coeOprationDelegate.saveOrUpdate(coeOpration);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOpration> findPage(CoeOpration coeOpration, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeOpration> page = coeOprationDelegate.findPage(coeOpration, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeOprationDelegate.existById(id);
	}
}
