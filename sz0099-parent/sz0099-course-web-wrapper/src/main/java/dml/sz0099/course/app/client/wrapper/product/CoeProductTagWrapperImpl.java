package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.product.CoeProductTagDelegate;
import dml.sz0099.course.app.persist.entity.product.CoeProductTag;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeProductTagWrapperImpl,组件封装
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
public class CoeProductTagWrapperImpl implements CoeProductTagWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductTagWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeProductTagDelegate coeProductTagDelegate;
	
	/**
	 * 根据Id查询CoeProductTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeProductTag findById(Long id) {
		LOGGER.debug("--- CoeProductTagWrapperImpl.findById begin --------- id is:{} ", id);
		CoeProductTag coeProductTag = coeProductTagDelegate.findById(id);
		LOGGER.debug("--- CoeProductTagWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeProductTag);
		return coeProductTag;
	}
	
	@Override
	public CoeProductTag findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeProductTag coeProductTag = coeProductTagDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeProductTag);
		return coeProductTag;
	}
	
	/**
	 * 根据IdList查询CoeProductTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProductTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeProductTagWrapperImpl.findByIdList begin ---------  ");
		List<CoeProductTag> coeProductTagList = coeProductTagDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeProductTagWrapperImpl.findByIdList end ---------  result is {} ",  coeProductTagList);
		return coeProductTagList;
	}
	
	@Override
	public CoeProductTag persistEntity(CoeProductTag coeProductTag) {
		LOGGER.debug("--- CoeProductTagWrapperImpl.persistEntity begin ---------  ");
		CoeProductTag entity = coeProductTagDelegate.persistEntity(coeProductTag);
		Long id = coeProductTag.getId();
		LOGGER.debug("--- CoeProductTagWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeProductTag mergeEntity(CoeProductTag coeProductTag) {
		Long id = coeProductTag.getId();
		LOGGER.debug("--- CoeProductTagWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		CoeProductTag entity = coeProductTagDelegate.mergeEntity(coeProductTag);
		LOGGER.debug("--- CoeProductTagWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeProductTag saveOrUpdate(CoeProductTag coeProductTag) {
		Long id = coeProductTag.getId();
		LOGGER.debug("--- CoeProductTagWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		CoeProductTag entity = coeProductTagDelegate.saveOrUpdate(coeProductTag);
		LOGGER.debug("--- CoeProductTagWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeProductTag> findPage(CoeProductTag coeProductTag, Pageable pageable) {
		LOGGER.debug("--- CoeProductTagWrapperImpl.findPage ---------  ");
		Page<CoeProductTag> page = coeProductTagDelegate.findPage(coeProductTag, pageable);
		return page;
	}

	@Override
	public CoeProductTag findByMainIdAndName(CoeProductTag coeProductTag) {
		
		return coeProductTagDelegate.findByMainIdAndName(coeProductTag);
	}
	
	public CoeProductTag addTag(CoeProductTag coeProductTag) {
		return coeProductTagDelegate.addTag(coeProductTag);
	}

	@Override
	public CoeProductTag deleteTag(CoeProductTag coeProductTag) {
		return coeProductTagDelegate.deleteTag(coeProductTag);
	}

	@Override
	public Long countByMainId(Long productId) {
		return coeProductTagDelegate.countByMainId(productId);
	}
	
	public List<CoeProductTag> findByMainId(Long productId) {
		return coeProductTagDelegate.findByMainId(productId);
	}

	@Override
	public Map<Long, List<CoeProductTag>> findMapByMainIdList(List<Long> productIdList) {
		return coeProductTagDelegate.findMapByMainIdList(productIdList);
	}
}
