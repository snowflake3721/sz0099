package dml.sz0099.course.app.biz.delegate.activity;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.activity.CoeActivityPositionService;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;

/**
 * coeActivityPositionServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeActivityPositionDelegateImpl implements CoeActivityPositionDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPositionDelegateImpl.class);
	
	@Autowired
	private CoeActivityPositionService coeActivityPositionService;

	/**
	 * 根据Id查询CoeActivityPosition实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityPosition findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeActivityPosition coeActivityPosition = coeActivityPositionService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeActivityPosition);
		return coeActivityPosition;
	}
	
	@Override
	public CoeActivityPosition findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityPosition coeActivityPosition = coeActivityPositionService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityPosition);
		return coeActivityPosition;
	}
	
	/**
	 * 根据IdList查询CoeActivityPosition实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityPosition> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeActivityPosition> coeActivityPositionList = coeActivityPositionService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeActivityPositionList);
		return coeActivityPositionList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeActivityPosition persistEntity(CoeActivityPosition coeActivityPosition) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeActivityPosition entity = coeActivityPositionService.persistEntity(coeActivityPosition);
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityPosition mergeEntity(CoeActivityPosition coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityPosition entity = coeActivityPositionService.mergeEntity(coeActivityPosition);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityPosition saveOrUpdate(CoeActivityPosition coeActivityPosition) {
		Long id = coeActivityPosition.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityPosition entity = coeActivityPositionService.saveOrUpdate(coeActivityPosition);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityPosition> findPage(CoeActivityPosition coeActivityPosition, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeActivityPosition> page = coeActivityPositionService.findPage(coeActivityPosition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeActivityPositionService.existById(id);
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPositionService.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public List<CoeActivityPosition> findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPositionService.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityPosition> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityPositionService.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPositionService.hasPositionByMainIdAndUserId(mainId,userId);
	}

	@Override
	public CoeActivityPosition mergeForPosition(CoeActivityPosition coeActivityPosition) {
		return coeActivityPositionService.mergeForPosition(coeActivityPosition);
	}
	
	public List<CoeActivityPosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList){
		return coeActivityPositionService.findByBaseIdAndMainIdList(baseId,mainIdList);
	}
	
	public void deleteById(CoeActivityPosition coeActivityPosition) {
		coeActivityPositionService.deleteById(coeActivityPosition);
	}
	
	public CoeActivityPosition mergePositionRef(CoeActivityPosition coeActivityPosition) {
		return coeActivityPositionService.mergePositionRef(coeActivityPosition);
	}
	
	public CoeActivityPosition openPositionRef(CoeActivityPosition positionRef) {
			positionRef = coeActivityPositionService.openPositionRef(positionRef);
		return positionRef;
	}
	
	public CoeActivityPosition mergeSimpleSingle(CoeActivityPosition positionRef) {
		positionRef = coeActivityPositionService.mergeSimpleSingle(positionRef);
		return positionRef;
	}
	
	public CoeActivityPosition deleteRefByBaseId(CoeActivityPosition positionRef) {
		positionRef = coeActivityPositionService.deleteRefByBaseId(positionRef);
		return positionRef;
	}
	public List<CoeActivityPosition> findByBaseId(CoeActivityPosition positionRef) {
		return coeActivityPositionService.findByBaseId(positionRef);
	}
	
	public Page<CoeActivityPosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable){
		return coeActivityPositionService.findPageForPosition(ponMainId, ponSubId, positionId, ponPanel, pageable);
	}
	
	public Page<CoeActivityPosition> findByBaseId(Long baseId, Pageable pageable){
		return coeActivityPositionService.findByBaseId( baseId,  pageable);
	}
	public Page<CoeActivityPosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable) {
		return coeActivityPositionService.findByBaseIdList(baseIdList,  pageable);
	}
}
