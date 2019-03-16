package dml.sz0099.course.app.client.wrapper.activity;

import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.activity.CoeActivityTagDelegate;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeActivityTagWrapperImpl,组件封装
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
public class CoeActivityTagWrapperImpl implements CoeActivityTagWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityTagWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeActivityTagDelegate coeActivityTagDelegate;
	
	/**
	 * 根据Id查询CoeActivityTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityTag findById(Long id) {
		LOGGER.debug("--- CoeActivityTagWrapperImpl.findById begin --------- id is:{} ", id);
		CoeActivityTag coeActivityTag = coeActivityTagDelegate.findById(id);
		LOGGER.debug("--- CoeActivityTagWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeActivityTag);
		return coeActivityTag;
	}
	
	@Override
	public CoeActivityTag findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityTag coeActivityTag = coeActivityTagDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityTag);
		return coeActivityTag;
	}
	
	/**
	 * 根据IdList查询CoeActivityTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeActivityTagWrapperImpl.findByIdList begin ---------  ");
		List<CoeActivityTag> coeActivityTagList = coeActivityTagDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeActivityTagWrapperImpl.findByIdList end ---------  result is {} ",  coeActivityTagList);
		return coeActivityTagList;
	}
	
	@Override
	public CoeActivityTag persistEntity(CoeActivityTag coeActivityTag) {
		LOGGER.debug("--- CoeActivityTagWrapperImpl.persistEntity begin ---------  ");
		CoeActivityTag entity = coeActivityTagDelegate.persistEntity(coeActivityTag);
		Long id = coeActivityTag.getId();
		LOGGER.debug("--- CoeActivityTagWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityTag mergeEntity(CoeActivityTag coeActivityTag) {
		Long id = coeActivityTag.getId();
		LOGGER.debug("--- CoeActivityTagWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityTag entity = coeActivityTagDelegate.mergeEntity(coeActivityTag);
		LOGGER.debug("--- CoeActivityTagWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityTag saveOrUpdate(CoeActivityTag coeActivityTag) {
		Long id = coeActivityTag.getId();
		LOGGER.debug("--- CoeActivityTagWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityTag entity = coeActivityTagDelegate.saveOrUpdate(coeActivityTag);
		LOGGER.debug("--- CoeActivityTagWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityTag> findPage(CoeActivityTag coeActivityTag, Pageable pageable) {
		LOGGER.debug("--- CoeActivityTagWrapperImpl.findPage ---------  ");
		Page<CoeActivityTag> page = coeActivityTagDelegate.findPage(coeActivityTag, pageable);
		return page;
	}

	@Override
	public CoeActivityTag findByMainIdAndName(CoeActivityTag coeActivityTag) {
		
		return coeActivityTagDelegate.findByMainIdAndName(coeActivityTag);
	}
	
	public CoeActivityTag addTag(CoeActivityTag coeActivityTag) {
		return coeActivityTagDelegate.addTag(coeActivityTag);
	}

	@Override
	public CoeActivityTag deleteTag(CoeActivityTag coeActivityTag) {
		return coeActivityTagDelegate.deleteTag(coeActivityTag);
	}

	@Override
	public Long countByMainId(Long activityId) {
		return coeActivityTagDelegate.countByMainId(activityId);
	}
	
	public List<CoeActivityTag> findByMainId(Long activityId) {
		return coeActivityTagDelegate.findByMainId(activityId);
	}
	
	@Override
	public Map<Long, List<CoeActivityTag>> findMapByMainIdList(List<Long> activityIdList) {
		return coeActivityTagDelegate.findMapByMainIdList(activityIdList);
	}
	
	public Page<CoeActivityTag> findPageWithNotself(CoeActivityTag coeActivityTag, Pageable pageable){
		return coeActivityTagDelegate.findPageWithNotself(coeActivityTag, pageable);
	}
}
