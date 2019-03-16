package dml.sz0099.course.app.biz.delegate.position;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.position.PositionExtendService;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;

/**
 * positionExtendServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class PositionExtendDelegateImpl implements PositionExtendDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendDelegateImpl.class);
	
	@Autowired
	private PositionExtendService positionExtendService;

	/**
	 * 根据Id查询PositionExtend实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PositionExtend findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		PositionExtend positionExtend = positionExtendService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, positionExtend);
		return positionExtend;
	}
	
	@Override
	public PositionExtend findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		PositionExtend positionExtend = positionExtendService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, positionExtend);
		return positionExtend;
	}
	
	/**
	 * 根据IdList查询PositionExtend实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<PositionExtend> positionExtendList = positionExtendService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  positionExtendList);
		return positionExtendList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public PositionExtend persistEntity(PositionExtend positionExtend) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		PositionExtend entity = positionExtendService.persistEntity(positionExtend);
		Long id = positionExtend.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionExtend mergeEntity(PositionExtend positionExtend) {
		Long id = positionExtend.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		PositionExtend entity = positionExtendService.mergeEntity(positionExtend);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionExtend saveOrUpdate(PositionExtend positionExtend) {
		Long id = positionExtend.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		PositionExtend entity = positionExtendService.saveOrUpdate(positionExtend);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionExtend> findPage(PositionExtend positionExtend, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<PositionExtend> page = positionExtendService.findPage(positionExtend, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionExtendService.existById(id);
	}

	@Override
	public List<PositionExtend> findAll() {
		return positionExtendService.findAll();
	}
	
	public PositionExtend create(PositionExtend positionExtend) {
		return positionExtendService.create(positionExtend);
	}
	
	
	@Override
	public PositionExtend findByPositionId(Long positionId) {
		return positionExtendService.findByPositionId(positionId);
	}

	@Override
	public PositionExtend findPositionExtend(PositionExtend extend) {
		return positionExtendService.findPositionExtend(extend);
	}
	
	public Long findPositionIdById(Long id) {
		return positionExtendService.findPositionIdById(id);
	}
	
	public Long countByUserId(Long userId) {
		return positionExtendService.countByUserId(userId);
	}
	
	public PositionExtend deleteEntity(PositionExtend extend) {
		
		return positionExtendService.deleteEntity(extend);
	}
}
