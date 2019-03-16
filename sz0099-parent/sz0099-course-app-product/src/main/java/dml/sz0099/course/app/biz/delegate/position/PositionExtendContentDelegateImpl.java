package dml.sz0099.course.app.biz.delegate.position;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.position.PositionExtendContentService;
import dml.sz0099.course.app.persist.entity.position.PositionExtendContent;

/**
 * positionExtendContentServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class PositionExtendContentDelegateImpl implements PositionExtendContentDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendContentDelegateImpl.class);
	
	@Autowired
	private PositionExtendContentService positionExtendContentService;

	/**
	 * 根据Id查询PositionExtendContent实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PositionExtendContent findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		PositionExtendContent positionExtendContent = positionExtendContentService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, positionExtendContent);
		return positionExtendContent;
	}
	
	@Override
	public PositionExtendContent findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		PositionExtendContent positionExtendContent = positionExtendContentService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, positionExtendContent);
		return positionExtendContent;
	}
	
	/**
	 * 根据IdList查询PositionExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<PositionExtendContent> positionExtendContentList = positionExtendContentService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  positionExtendContentList);
		return positionExtendContentList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public PositionExtendContent persistEntity(PositionExtendContent positionExtendContent) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		PositionExtendContent entity = positionExtendContentService.persistEntity(positionExtendContent);
		Long id = positionExtendContent.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionExtendContent mergeEntity(PositionExtendContent positionExtendContent) {
		Long id = positionExtendContent.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		PositionExtendContent entity = positionExtendContentService.mergeEntity(positionExtendContent);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionExtendContent saveOrUpdate(PositionExtendContent positionExtendContent) {
		Long id = positionExtendContent.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		PositionExtendContent entity = positionExtendContentService.saveOrUpdate(positionExtendContent);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionExtendContent> findPage(PositionExtendContent positionExtendContent, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<PositionExtendContent> page = positionExtendContentService.findPage(positionExtendContent, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionExtendContentService.existById(id);
	}
}
