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

import dml.sz0099.course.app.biz.delegate.activity.CoeActivityFeeDelegate;
import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityFeeWrapperImpl,组件封装
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
public class CoeActivityFeeWrapperImpl implements CoeActivityFeeWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityFeeWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeActivityFeeDelegate coeActivityFeeDelegate;
	
	/**
	 * 根据Id查询CoeActivityFee实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityFee findById(Long id) {
		LOGGER.debug("--- CoeActivityFeeWrapperImpl.findById begin --------- id is:{} ", id);
		CoeActivityFee coeActivityFee = coeActivityFeeDelegate.findById(id);
		LOGGER.debug("--- CoeActivityFeeWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeActivityFee);
		return coeActivityFee;
	}
	
	@Override
	public CoeActivityFee findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityFee coeActivityFee = coeActivityFeeDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityFee);
		return coeActivityFee;
	}
	
	/**
	 * 根据IdList查询CoeActivityFee实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityFee> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeActivityFeeWrapperImpl.findByIdList begin ---------  ");
		List<CoeActivityFee> coeActivityFeeList = coeActivityFeeDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeActivityFeeWrapperImpl.findByIdList end ---------  result is {} ",  coeActivityFeeList);
		return coeActivityFeeList;
	}
	
	@Override
	public CoeActivityFee persistEntity(CoeActivityFee coeActivityFee) {
		LOGGER.debug("--- CoeActivityFeeWrapperImpl.persistEntity begin ---------  ");
		CoeActivityFee entity = coeActivityFeeDelegate.persistEntity(coeActivityFee);
		Long id = coeActivityFee.getId();
		LOGGER.debug("--- CoeActivityFeeWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityFee mergeEntity(CoeActivityFee coeActivityFee) {
		Long id = coeActivityFee.getId();
		LOGGER.debug("--- CoeActivityFeeWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityFee entity = coeActivityFeeDelegate.mergeEntity(coeActivityFee);
		LOGGER.debug("--- CoeActivityFeeWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityFee saveOrUpdate(CoeActivityFee coeActivityFee) {
		Long id = coeActivityFee.getId();
		LOGGER.debug("--- CoeActivityFeeWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityFee entity = coeActivityFeeDelegate.saveOrUpdate(coeActivityFee);
		LOGGER.debug("--- CoeActivityFeeWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityFee> findPage(CoeActivityFee coeActivityFee, Pageable pageable) {
		LOGGER.debug("--- CoeActivityFeeWrapperImpl.findPage ---------  ");
		Page<CoeActivityFee> page = coeActivityFeeDelegate.findPage(coeActivityFee, pageable);
		return page;
	}

	@Override
	public CoeActivityFee findByMainId(CoeActivityFee coeActivityFee) {
		
		return coeActivityFeeDelegate.findByMainId(coeActivityFee);
	}
	
	public CoeActivityFee addFee(CoeActivityFee coeActivityFee) {
		return coeActivityFeeDelegate.addFee(coeActivityFee);
	}

	@Override
	public CoeActivityFee deleteFee(CoeActivityFee coeActivityFee) {
		return coeActivityFeeDelegate.deleteFee(coeActivityFee);
	}

	@Override
	public Long countByMainId(Long activityId) {
		return coeActivityFeeDelegate.countByMainId(activityId);
	}
	
	public CoeActivityFee findByMainId(Long activityId) {
		return coeActivityFeeDelegate.findByMainId(activityId);
	}
	
	@Override
	public Map<Long, CoeActivityFee> findMapByMainIdList(List<Long> activityIdList) {
		return coeActivityFeeDelegate.findMapByMainIdList(activityIdList);
	}
	
	public Page<CoeActivityFee> findPageWithNotself(CoeActivityFee coeActivityFee, Pageable pageable){
		return coeActivityFeeDelegate.findPageWithNotself(coeActivityFee, pageable);
	}
	
	public CoeActivityFee mergeDescription(CoeActivityFee coeActivityFee) {
		String description = coeActivityFee.getDescription();
		if(StringUtils.isNotBlank(description)) {
			coeActivityFee.setDescription(HtmlUtil.textarea2Escape(description));
		}
		return coeActivityFeeDelegate.mergeDescription(coeActivityFee); 
	}
	
	public CoeActivityFee mergeFee(CoeActivityFee coeActivityFee) {
		String description = coeActivityFee.getDescription();
		if(StringUtils.isNotBlank(description)) {
			coeActivityFee.setDescription(HtmlUtil.textarea2Escape(description));
		}
		return coeActivityFeeDelegate.mergeFee(coeActivityFee); 
	}
}
