package dml.sz0099.course.app.client.wrapper.position;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.position.PositionExtendContentDelegate;
import dml.sz0099.course.app.persist.entity.position.PositionExtendContent;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionExtendContentWrapperImpl,组件封装
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
public class PositionExtendContentWrapperImpl implements PositionExtendContentWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendContentWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private PositionExtendContentDelegate positionExtendContentDelegate;
	
	/**
	 * 根据Id查询PositionExtendContent实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PositionExtendContent findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		PositionExtendContent positionExtendContent = positionExtendContentDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, positionExtendContent);
		return positionExtendContent;
	}
	
	@Override
	public PositionExtendContent findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		PositionExtendContent positionExtendContent = positionExtendContentDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, positionExtendContent);
		return positionExtendContent;
	}
	
	/**
	 * 根据IdList查询PositionExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<PositionExtendContent> positionExtendContentList = positionExtendContentDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  positionExtendContentList);
		return positionExtendContentList;
	}
	
	@Override
	public PositionExtendContent persistEntity(PositionExtendContent positionExtendContent) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		PositionExtendContent entity = positionExtendContentDelegate.persistEntity(positionExtendContent);
		Long id = positionExtendContent.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionExtendContent mergeEntity(PositionExtendContent positionExtendContent) {
		Long id = positionExtendContent.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		PositionExtendContent entity = positionExtendContentDelegate.mergeEntity(positionExtendContent);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionExtendContent saveOrUpdate(PositionExtendContent positionExtendContent) {
		Long id = positionExtendContent.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		PositionExtendContent entity = positionExtendContentDelegate.saveOrUpdate(positionExtendContent);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionExtendContent> findPage(PositionExtendContent positionExtendContent, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<PositionExtendContent> page = positionExtendContentDelegate.findPage(positionExtendContent, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return positionExtendContentDelegate.existById(id);
	}
}
