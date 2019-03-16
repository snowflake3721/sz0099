package dml.sz0099.course.app.biz.delegate.position;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.position.PositionRefService;
import dml.sz0099.course.app.persist.entity.position.PositionRef;

/**
 * positionRefServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class PositionRefDelegateImpl implements PositionRefDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionRefDelegateImpl.class);
	
	@Autowired
	private PositionRefService positionRefService;

	/**
	 * 根据Id查询PositionRef实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PositionRef findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		PositionRef positionRef = positionRefService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, positionRef);
		return positionRef;
	}
	
	@Override
	public PositionRef findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		PositionRef positionRef = positionRefService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, positionRef);
		return positionRef;
	}
	
	/**
	 * 根据IdList查询PositionRef实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<PositionRef> positionRefList = positionRefService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  positionRefList);
		return positionRefList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public PositionRef persistEntity(PositionRef positionRef) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		PositionRef entity = positionRefService.persistEntity(positionRef);
		Long id = positionRef.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionRef mergeEntity(PositionRef positionRef) {
		Long id = positionRef.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		PositionRef entity = positionRefService.mergeEntity(positionRef);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionRef saveOrUpdate(PositionRef positionRef) {
		Long id = positionRef.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		PositionRef entity = positionRefService.saveOrUpdate(positionRef);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionRef> findPage(PositionRef positionRef, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<PositionRef> page = positionRefService.findPage(positionRef, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionRefService.existById(id);
	}
	
	public Long countForBase(PositionRef positionRef) {
		Long baseId = positionRef.getBaseId();
		return positionRefService.countForBase(positionRef);
	}
	
	public PositionRef changeSingle(PositionRef positionRef) {
		positionRef=positionRefService.changeSingle(positionRef);
		return positionRef;
	}

	@Override
	public List<PositionRef> findByBaseId(Long baseId) {
		return positionRefService.findByBaseId(baseId);
	}

	@Override
	public Page<PositionRef> findPageByBaseId(Long baseId, Pageable pageable, boolean withImages) {
		return positionRefService.findPageByBaseId(baseId, pageable, withImages);
	}
	
	
	
	public List<PositionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList) {
		return positionRefService.findByBaseIdAndMainIdList( baseId, mainIdList) ;
	}
	
	public PositionRef findMainIdAndBaseId(PositionRef positionRef) {
		return positionRefService.findMainIdAndBaseId(positionRef);
	}
	
	public void deleteById(PositionRef positionRef) {
		positionRefService.deleteById(positionRef);
	}
	
	public PositionRef mergePositionRef(PositionRef positionRef) {
		return positionRefService.mergePositionRef(positionRef);
	}
	
	public PositionRef openPositionRef(PositionRef positionRef) {
		positionRef = positionRefService.openPositionRef(positionRef);
		return positionRef;
	}
	
	public PositionRef mergeSimpleSingle(PositionRef positionRef) {
			positionRef = positionRefService.mergeSimpleSingle(positionRef);
		return positionRef;
	}
	
	public PositionRef deleteRefByBaseId(PositionRef positionRef) {
		Long baseId = positionRef.getBaseId();
		if(null != baseId) {
			positionRef = positionRefService.deleteRefByBaseId(positionRef);
		}
		return positionRef;
	}
}
