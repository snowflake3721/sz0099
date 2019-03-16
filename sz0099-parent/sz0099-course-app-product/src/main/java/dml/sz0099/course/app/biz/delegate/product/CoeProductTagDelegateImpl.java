package dml.sz0099.course.app.biz.delegate.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.product.CoeProductTagService;
import dml.sz0099.course.app.persist.entity.product.CoeProductTag;

/**
 * coeProductTagServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeProductTagDelegateImpl implements CoeProductTagDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductTagDelegateImpl.class);
	
	@Autowired
	private CoeProductTagService coeProductTagService;

	/**
	 * 根据Id查询CoeProductTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeProductTag findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeProductTag coeProductTag = coeProductTagService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeProductTag);
		return coeProductTag;
	}
	
	@Override
	public CoeProductTag findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeProductTag coeProductTag = coeProductTagService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeProductTag);
		return coeProductTag;
	}
	
	/**
	 * 根据IdList查询CoeProductTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProductTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeProductTag> coeProductTagList = coeProductTagService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeProductTagList);
		return coeProductTagList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeProductTag persistEntity(CoeProductTag coeProductTag) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeProductTag entity = coeProductTagService.persistEntity(coeProductTag);
		Long id = coeProductTag.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeProductTag mergeEntity(CoeProductTag coeProductTag) {
		Long id = coeProductTag.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeProductTag entity = coeProductTagService.mergeEntity(coeProductTag);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeProductTag saveOrUpdate(CoeProductTag coeProductTag) {
		Long id = coeProductTag.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeProductTag entity = coeProductTagService.saveOrUpdate(coeProductTag);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeProductTag> findPage(CoeProductTag coeProductTag, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeProductTag> page = coeProductTagService.findPage(coeProductTag, pageable);
		return page;
	}

	@Override
	public CoeProductTag findByMainIdAndName(CoeProductTag coeProductTag) {
		return coeProductTagService.findByMainIdAndName(coeProductTag);
	}
	
	public CoeProductTag addTag(CoeProductTag coeProductTag) {
		return coeProductTagService.addTag(coeProductTag);
	}
	
	public CoeProductTag deleteTag(CoeProductTag coeProductTag) {
		return coeProductTagService.deleteTag(coeProductTag);
	}
	
	public Long countByMainId(Long productId) {
		return coeProductTagService.countByMainId(productId);
	}

	@Override
	public List<CoeProductTag> findByMainId(Long productId) {
		return coeProductTagService.findByMainId(productId);
	}
	
	public Map<Long, List<CoeProductTag>> findMapByMainIdList(List<Long> productIdList) {
		return coeProductTagService.findMapByMainIdList(productIdList);
	}
}
