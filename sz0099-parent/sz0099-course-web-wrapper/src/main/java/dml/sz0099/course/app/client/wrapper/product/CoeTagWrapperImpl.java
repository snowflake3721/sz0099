package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.tag.CoeTagDelegate;
import dml.sz0099.course.app.persist.entity.tag.CoeTag;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeTagWrapperImpl,组件封装
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
public class CoeTagWrapperImpl implements CoeTagWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeTagWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeTagDelegate coeTagDelegate;
	
	/**
	 * 根据Id查询CoeTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeTag findById(Long id) {
		LOGGER.debug("--- CoeTagWrapperImpl.findById begin --------- id is:{} ", id);
		CoeTag coeTag = coeTagDelegate.findById(id);
		LOGGER.debug("--- CoeTagWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeTag);
		return coeTag;
	}
	
	@Override
	public CoeTag findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeTag coeTag = coeTagDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeTag);
		return coeTag;
	}
	
	/**
	 * 根据IdList查询CoeTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeTagWrapperImpl.findByIdList begin ---------  ");
		List<CoeTag> coeTagList = coeTagDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeTagWrapperImpl.findByIdList end ---------  result is {} ",  coeTagList);
		return coeTagList;
	}
	
	@Override
	public CoeTag persistEntity(CoeTag coeTag) {
		LOGGER.debug("--- CoeTagWrapperImpl.persistEntity begin ---------  ");
		CoeTag entity = coeTagDelegate.persistEntity(coeTag);
		Long id = coeTag.getId();
		LOGGER.debug("--- CoeTagWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeTag mergeEntity(CoeTag coeTag) {
		Long id = coeTag.getId();
		LOGGER.debug("--- CoeTagWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		CoeTag entity = coeTagDelegate.mergeEntity(coeTag);
		LOGGER.debug("--- CoeTagWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeTag saveOrUpdate(CoeTag coeTag) {
		Long id = coeTag.getId();
		LOGGER.debug("--- CoeTagWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		CoeTag entity = coeTagDelegate.saveOrUpdate(coeTag);
		LOGGER.debug("--- CoeTagWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeTag> findPage(CoeTag coeTag, Pageable pageable) {
		LOGGER.debug("--- CoeTagWrapperImpl.findPage ---------  ");
		Page<CoeTag> page = coeTagDelegate.findPage(coeTag, pageable);
		return page;
	}
}
