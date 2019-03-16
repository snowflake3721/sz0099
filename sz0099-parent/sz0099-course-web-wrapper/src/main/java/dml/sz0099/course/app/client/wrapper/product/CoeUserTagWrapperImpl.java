package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.user.CoeUserTagDelegate;
import dml.sz0099.course.app.persist.entity.user.CoeUserTag;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeUserTagWrapperImpl,组件封装
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
public class CoeUserTagWrapperImpl implements CoeUserTagWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserTagWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeUserTagDelegate coeUserTagDelegate;
	
	/**
	 * 根据Id查询CoeUserTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserTag findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeUserTag coeUserTag = coeUserTagDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeUserTag);
		return coeUserTag;
	}
	
	@Override
	public CoeUserTag findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserTag coeUserTag = coeUserTagDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserTag);
		return coeUserTag;
	}
	
	/**
	 * 根据IdList查询CoeUserTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeUserTag> coeUserTagList = coeUserTagDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeUserTagList);
		return coeUserTagList;
	}
	
	@Override
	public CoeUserTag persistEntity(CoeUserTag coeUserTag) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeUserTag entity = coeUserTagDelegate.persistEntity(coeUserTag);
		Long id = coeUserTag.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserTag mergeEntity(CoeUserTag coeUserTag) {
		Long id = coeUserTag.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeUserTag entity = coeUserTagDelegate.mergeEntity(coeUserTag);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserTag saveOrUpdate(CoeUserTag coeUserTag) {
		Long id = coeUserTag.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserTag entity = coeUserTagDelegate.saveOrUpdate(coeUserTag);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserTag> findPage(CoeUserTag coeUserTag, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeUserTag> page = coeUserTagDelegate.findPage(coeUserTag, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeUserTagDelegate.existById(id);
	}
}
