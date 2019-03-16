package dml.sz0099.course.app.biz.delegate.activity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.activity.CoeActivityTagService;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;

/**
 * coeActivityTagServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeActivityTagDelegateImpl implements CoeActivityTagDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityTagDelegateImpl.class);
	
	@Autowired
	private CoeActivityTagService coeActivityTagService;

	/**
	 * 根据Id查询CoeActivityTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityTag findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeActivityTag coeActivityTag = coeActivityTagService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeActivityTag);
		return coeActivityTag;
	}
	
	@Override
	public CoeActivityTag findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityTag coeActivityTag = coeActivityTagService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityTag);
		return coeActivityTag;
	}
	
	/**
	 * 根据IdList查询CoeActivityTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeActivityTag> coeActivityTagList = coeActivityTagService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeActivityTagList);
		return coeActivityTagList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeActivityTag persistEntity(CoeActivityTag coeActivityTag) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeActivityTag entity = coeActivityTagService.persistEntity(coeActivityTag);
		Long id = coeActivityTag.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityTag mergeEntity(CoeActivityTag coeActivityTag) {
		Long id = coeActivityTag.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityTag entity = coeActivityTagService.mergeEntity(coeActivityTag);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityTag saveOrUpdate(CoeActivityTag coeActivityTag) {
		Long id = coeActivityTag.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityTag entity = coeActivityTagService.saveOrUpdate(coeActivityTag);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityTag> findPage(CoeActivityTag coeActivityTag, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeActivityTag> page = coeActivityTagService.findPage(coeActivityTag, pageable);
		return page;
	}

	@Override
	public CoeActivityTag findByMainIdAndName(CoeActivityTag coeActivityTag) {
		return coeActivityTagService.findByMainIdAndName(coeActivityTag);
	}
	
	public CoeActivityTag addTag(CoeActivityTag coeActivityTag) {
		return coeActivityTagService.addTag(coeActivityTag);
	}
	
	public CoeActivityTag deleteTag(CoeActivityTag coeActivityTag) {
		return coeActivityTagService.deleteTag(coeActivityTag);
	}
	
	public Long countByMainId(Long activityId) {
		return coeActivityTagService.countByMainId(activityId);
	}

	@Override
	public List<CoeActivityTag> findByMainId(Long activityId) {
		return coeActivityTagService.findByMainId(activityId);
	}
	
	public Map<Long, List<CoeActivityTag>> findMapByMainIdList(List<Long> mainIdList) {
		return coeActivityTagService.findMapByMainIdList(mainIdList);
	}
	
	public Page<CoeActivityTag> findPageWithNotself(CoeActivityTag coeActivityTag, Pageable pageable){
		return coeActivityTagService.findPageWithNotself(coeActivityTag, pageable);
	}
}
