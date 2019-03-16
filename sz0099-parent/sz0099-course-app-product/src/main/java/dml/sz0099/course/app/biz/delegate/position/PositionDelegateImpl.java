package dml.sz0099.course.app.biz.delegate.position;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.position.PositionService;
import dml.sz0099.course.app.persist.entity.position.Position;

/**
 * positionServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class PositionDelegateImpl implements PositionDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionDelegateImpl.class);
	
	@Autowired
	private PositionService positionService;

	/**
	 * 根据Id查询Position实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Position findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		Position position = positionService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, position);
		return position;
	}
	
	@Override
	public Position findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		Position position = positionService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, position);
		return position;
	}
	
	/**
	 * 根据IdList查询Position实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Position> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<Position> positionList = positionService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  positionList);
		return positionList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public Position persistEntity(Position position) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		Position entity = positionService.persistEntity(position);
		Long id = position.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Position mergeEntity(Position position) {
		Long id = position.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		Position entity = positionService.mergeEntity(position);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Position saveOrUpdate(Position position) {
		Long id = position.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		Position entity = positionService.saveOrUpdate(position);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Position> findPage(Position position, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<Position> page = positionService.findPage(position, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionService.existById(id);
	}
	
	public List<Position> findMainIdAndExtendId(Position position) {
		return positionService.findMainIdAndExtendId(position);
	}
	
	
	public Position deletePosition(Position position) {
		return positionService.deletePosition(position);
	}
	
	public Long countByExtendId(Long extendId) {
		return positionService.countByExtendId(extendId);
	}
	
	public Position deleteAllByExtend(Position position) {
		return positionService.deleteAllByExtend(position);
	}
	
	public Position findById(Long id, boolean cascade, boolean withImages, Pageable pageable) {
		return positionService.findById(id,cascade, withImages,pageable);
	}

	
	public Map<Long,Position> findMapByIdList(List<Long> idList) {
		Map<Long,Position> map = positionService.findMapByIdList(idList);
		return map;
	}
	
	public List<Position> findBindList(Position position, Pageable pageable) {
		return positionService.findBindList(position, pageable);
	}
	
	public List<Position> findPosition(Long mainId, Long subId, Long positionId, Integer panel){
		return positionService.findPosition(mainId, subId, positionId, panel);
	}
}
