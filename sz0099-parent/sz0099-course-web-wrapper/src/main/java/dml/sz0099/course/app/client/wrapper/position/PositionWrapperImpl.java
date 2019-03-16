package dml.sz0099.course.app.client.wrapper.position;

import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.position.PositionDelegate;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.Position;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionWrapperImpl,组件封装
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
public class PositionWrapperImpl implements PositionWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private PositionDelegate positionDelegate;
	
	
	@Autowired
	private PositionExtendWrapper positionExtendWrapper;
	
	/**
	 * 根据Id查询Position实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Position findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		Position position = positionDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, position);
		return position;
	}
	
	@Override
	public Position findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		Position position = positionDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, position);
		return position;
	}
	
	/**
	 * 根据IdList查询Position实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Position> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<Position> positionList = positionDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  positionList);
		return positionList;
	}
	
	@Override
	public Position persistEntity(Position position) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		Position entity = positionDelegate.persistEntity(position);
		Long id = position.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Position mergeEntity(Position position) {
		Long id = position.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		Position entity = positionDelegate.mergeEntity(position);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Position saveOrUpdate(Position position) {
		Long id = position.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		Position entity = positionDelegate.saveOrUpdate(position);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Position> findPage(Position position, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<Position> page = positionDelegate.findPage(position, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return positionDelegate.existById(id);
	}
	
	
	@Override
	public List<Position> findMainIdAndExtendId(Position position) {
		return positionDelegate.findMainIdAndExtendId(position);
	}
	
	@Override
	public List<Position> findBindList(Position position, Pageable pageable) {
		return positionDelegate.findBindList(position,pageable);
	}

	@Override
	public Position createPosition(Position position) {
		
		Long positionId = position.getPositionId();
		if(null == positionId) {
			Long extendId = position.getExtendId();
			PositionExtend extend = positionExtendWrapper.findById(extendId);
			if(null != extend) {
				position.setPositionId(extend.getPositionId());
			}
		}
		
		Position entity = persistEntity(position);
		return entity;
	}


	
	public Long countByExtendId(Long extendId) {
		return positionDelegate.countByExtendId(extendId);
	}

	@Override
	public Position deletePosition(Position position) {
		return positionDelegate.deletePosition(position);
	}

	@Override
	public Position deleteAllByExtend(Position position) {
		Long positionId = position.getPositionId();
		if(null == positionId) {
			Long extendId = position.getExtendId();
			PositionExtend extend = positionExtendWrapper.findById(extendId);
			if(null != extend) {
				position.setPositionId(extend.getPositionId());
			}
		}
		return positionDelegate.deleteAllByExtend(position);
	}

	@Override
	public Map<Long, Position> findMapByIdList(List<Long> idList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Position findById(Long id, boolean cascade, boolean withImages, Pageable pageable) {
		return positionDelegate.findById(id, cascade, withImages, pageable);
	}
	
	public List<Position> findPosition(Long mainId, Long subId, Long positionId, Integer panel){
		return positionDelegate.findPosition(mainId, subId, positionId, panel);
	}
	
}
