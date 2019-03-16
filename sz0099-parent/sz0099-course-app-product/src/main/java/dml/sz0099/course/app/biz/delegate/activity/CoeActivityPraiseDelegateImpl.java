package dml.sz0099.course.app.biz.delegate.activity;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.activity.CoeActivityPraiseService;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPraise;

/**
 * coeActivityPraiseServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeActivityPraiseDelegateImpl implements CoeActivityPraiseDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityPraiseDelegateImpl.class);
	
	@Autowired
	private CoeActivityPraiseService coeActivityPraiseService;

	/**
	 * 根据Id查询CoeActivityPraise实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityPraise findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeActivityPraise coeActivityPraise = coeActivityPraiseService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeActivityPraise);
		return coeActivityPraise;
	}
	
	@Override
	public CoeActivityPraise findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityPraise coeActivityPraise = coeActivityPraiseService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityPraise);
		return coeActivityPraise;
	}
	
	/**
	 * 根据IdList查询CoeActivityPraise实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityPraise> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeActivityPraise> coeActivityPraiseList = coeActivityPraiseService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeActivityPraiseList);
		return coeActivityPraiseList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeActivityPraise persistEntity(CoeActivityPraise coeActivityPraise) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeActivityPraise entity = coeActivityPraiseService.persistEntity(coeActivityPraise);
		Long id = coeActivityPraise.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityPraise mergeEntity(CoeActivityPraise coeActivityPraise) {
		Long id = coeActivityPraise.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityPraise entity = coeActivityPraiseService.mergeEntity(coeActivityPraise);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeActivityPraise saveOrUpdate(CoeActivityPraise coeActivityPraise) {
		Long id = coeActivityPraise.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityPraise entity = coeActivityPraiseService.saveOrUpdate(coeActivityPraise);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityPraise> findPage(CoeActivityPraise coeActivityPraise, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeActivityPraise> page = coeActivityPraiseService.findPage(coeActivityPraise, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeActivityPraiseService.existById(id);
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPraiseService.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeActivityPraise findByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPraiseService.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeActivityPraise> findByMainId(Long mainId, Pageable pageable) {
		return coeActivityPraiseService.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPraisedByMainIdAndUserId(Long mainId, Long userId) {
		return coeActivityPraiseService.hasPraisedByMainIdAndUserId(mainId,userId);
	}

	@Override
	public CoeActivityPraise mergeForPraise(CoeActivityPraise coeActivityPraise) {
		return coeActivityPraiseService.mergeForPraise(coeActivityPraise);
	}
	
	public CoeActivityPraise mergeForRefreshTime(CoeActivityPraise coeActivityPraise) {
		return coeActivityPraiseService.mergeForRefreshTime(coeActivityPraise);
	}
	
	public CoeActivityPraise praiseAgain(CoeActivityPraise coeActivityPraise) {
		coeActivityPraise=coeActivityPraiseService.praiseAgain(coeActivityPraise);
		return coeActivityPraise;
	}
}
