package dml.sz0099.course.app.biz.delegate.article;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.article.CoeArticlePositionService;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;

/**
 * coeArticlePositionServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeArticlePositionDelegateImpl implements CoeArticlePositionDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePositionDelegateImpl.class);
	
	@Autowired
	private CoeArticlePositionService coeArticlePositionService;

	/**
	 * 根据Id查询CoeArticlePosition实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticlePosition findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeArticlePosition coeArticlePosition = coeArticlePositionService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeArticlePosition);
		return coeArticlePosition;
	}
	
	@Override
	public CoeArticlePosition findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticlePosition coeArticlePosition = coeArticlePositionService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticlePosition);
		return coeArticlePosition;
	}
	
	/**
	 * 根据IdList查询CoeArticlePosition实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticlePosition> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeArticlePosition> coeArticlePositionList = coeArticlePositionService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeArticlePositionList);
		return coeArticlePositionList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeArticlePosition persistEntity(CoeArticlePosition coeArticlePosition) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeArticlePosition entity = coeArticlePositionService.persistEntity(coeArticlePosition);
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticlePosition mergeEntity(CoeArticlePosition coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeArticlePosition entity = coeArticlePositionService.mergeEntity(coeArticlePosition);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticlePosition saveOrUpdate(CoeArticlePosition coeArticlePosition) {
		Long id = coeArticlePosition.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticlePosition entity = coeArticlePositionService.saveOrUpdate(coeArticlePosition);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticlePosition> findPage(CoeArticlePosition coeArticlePosition, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeArticlePosition> page = coeArticlePositionService.findPage(coeArticlePosition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeArticlePositionService.existById(id);
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionService.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public List<CoeArticlePosition> findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionService.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticlePosition> findByMainId(Long mainId, Pageable pageable) {
		return coeArticlePositionService.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPositionByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePositionService.hasPositionByMainIdAndUserId(mainId,userId);
	}

	@Override
	public CoeArticlePosition mergeForPosition(CoeArticlePosition coeArticlePosition) {
		return coeArticlePositionService.mergeForPosition(coeArticlePosition);
	}
	
	public List<CoeArticlePosition> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList){
		return coeArticlePositionService.findByBaseIdAndMainIdList(baseId,mainIdList);
	}
	
	public void deleteById(CoeArticlePosition coeArticlePosition) {
		coeArticlePositionService.deleteById(coeArticlePosition);
	}
	
	public CoeArticlePosition mergePositionRef(CoeArticlePosition coeArticlePosition) {
		return coeArticlePositionService.mergePositionRef(coeArticlePosition);
	}
	
	public CoeArticlePosition openPositionRef(CoeArticlePosition positionRef) {
			positionRef = coeArticlePositionService.openPositionRef(positionRef);
		return positionRef;
	}
	
	public CoeArticlePosition mergeSimpleSingle(CoeArticlePosition positionRef) {
		positionRef = coeArticlePositionService.mergeSimpleSingle(positionRef);
		return positionRef;
	}
	
	public CoeArticlePosition deleteRefByBaseId(CoeArticlePosition positionRef) {
		positionRef = coeArticlePositionService.deleteRefByBaseId(positionRef);
		return positionRef;
	}
	public List<CoeArticlePosition> findByBaseId(CoeArticlePosition positionRef) {
		return coeArticlePositionService.findByBaseId(positionRef);
	}
	
	public Page<CoeArticlePosition> findPageForPosition(Long ponMainId,Long ponSubId, Long positionId, Integer ponPanel, Pageable pageable){
		return coeArticlePositionService.findPageForPosition(ponMainId, ponSubId, positionId, ponPanel, pageable);
	}
	
	public Page<CoeArticlePosition> findByBaseId(Long baseId, Pageable pageable){
		return coeArticlePositionService.findByBaseId( baseId,  pageable);
	}
	public Page<CoeArticlePosition> findByBaseIdList(List<Long> baseIdList, Pageable pageable) {
		return coeArticlePositionService.findByBaseIdList(baseIdList,  pageable);
	}
}
