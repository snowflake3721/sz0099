package dml.sz0099.course.app.biz.delegate.profession;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.profession.ProfessionPositionService;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;

/**
 * professionPositionServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ProfessionPositionDelegateImpl implements ProfessionPositionDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPositionDelegateImpl.class);
	
	@Autowired
	private ProfessionPositionService professionPositionService;

	/**
	 * 根据Id查询ProfessionPosition实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionPosition findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ProfessionPosition professionPosition = professionPositionService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, professionPosition);
		return professionPosition;
	}
	
	@Override
	public ProfessionPosition findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionPosition professionPosition = professionPositionService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, professionPosition);
		return professionPosition;
	}
	
	/**
	 * 根据IdList查询ProfessionPosition实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionPosition> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ProfessionPosition> professionPositionList = professionPositionService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  professionPositionList);
		return professionPositionList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ProfessionPosition persistEntity(ProfessionPosition professionPosition) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ProfessionPosition entity = professionPositionService.persistEntity(professionPosition);
		Long id = professionPosition.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionPosition mergeEntity(ProfessionPosition professionPosition) {
		Long id = professionPosition.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ProfessionPosition entity = professionPositionService.mergeEntity(professionPosition);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionPosition saveOrUpdate(ProfessionPosition professionPosition) {
		Long id = professionPosition.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionPosition entity = professionPositionService.saveOrUpdate(professionPosition);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionPosition> findPage(ProfessionPosition professionPosition, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ProfessionPosition> page = professionPositionService.findPage(professionPosition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionPositionService.existById(id);
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return professionPositionService.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public List<ProfessionPosition> findByMainIdAndUserId(Long mainId, Long userId) {
		return professionPositionService.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionPosition> findByMainId(Long mainId, Pageable pageable) {
		return professionPositionService.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		return professionPositionService.hasPositionByMainIdAndUserId(mainId,userId);
	}

	@Override
	public ProfessionPosition mergeForPosition(ProfessionPosition professionPosition) {
		return professionPositionService.mergeForPosition(professionPosition);
	}
	
	public List<ProfessionPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList){
		return professionPositionService.findByBaseIdAndMainIdList(baseId,mainIdList);
	}
	
	public void deleteById(ProfessionPosition professionPosition) {
		professionPositionService.deleteById(professionPosition);
	}
	
	public ProfessionPosition mergePositionRef(ProfessionPosition professionPosition) {
		return professionPositionService.mergePositionRef(professionPosition);
	}
	
	public ProfessionPosition openPositionRef(ProfessionPosition positionRef) {
			positionRef = professionPositionService.openPositionRef(positionRef);
		return positionRef;
	}
	
	public ProfessionPosition mergeSimpleSingle(ProfessionPosition positionRef) {
		positionRef = professionPositionService.mergeSimpleSingle(positionRef);
		return positionRef;
	}
	
	public ProfessionPosition deleteRefByBaseId(ProfessionPosition positionRef) {
		positionRef = professionPositionService.deleteRefByBaseId(positionRef);
		return positionRef;
	}
	public List<ProfessionPosition> findByBaseId(ProfessionPosition positionRef) {
		return professionPositionService.findByBaseId(positionRef);
	}
	
	public Page<ProfessionPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable){
		return professionPositionService.findPageForPosition(ponMainId, ponSubId, positionId, ponPanel, pageable);
	}
	
	public Page<ProfessionPosition> findByBaseId(Long baseId, Pageable pageable){
		return professionPositionService.findByBaseId(baseId, pageable);
	}
	public Page<ProfessionPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable){
		return professionPositionService.findByBaseIdList(baseIdList, pageable);
	}
}
