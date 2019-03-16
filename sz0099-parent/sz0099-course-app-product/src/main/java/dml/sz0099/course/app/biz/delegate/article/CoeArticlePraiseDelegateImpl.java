package dml.sz0099.course.app.biz.delegate.article;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.article.CoeArticlePraiseService;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;

/**
 * coeArticlePraiseServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeArticlePraiseDelegateImpl implements CoeArticlePraiseDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePraiseDelegateImpl.class);
	
	@Autowired
	private CoeArticlePraiseService coeArticlePraiseService;

	/**
	 * 根据Id查询CoeArticlePraise实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticlePraise findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeArticlePraise coeArticlePraise = coeArticlePraiseService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeArticlePraise);
		return coeArticlePraise;
	}
	
	@Override
	public CoeArticlePraise findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticlePraise coeArticlePraise = coeArticlePraiseService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticlePraise);
		return coeArticlePraise;
	}
	
	/**
	 * 根据IdList查询CoeArticlePraise实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticlePraise> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeArticlePraise> coeArticlePraiseList = coeArticlePraiseService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeArticlePraiseList);
		return coeArticlePraiseList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeArticlePraise persistEntity(CoeArticlePraise coeArticlePraise) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeArticlePraise entity = coeArticlePraiseService.persistEntity(coeArticlePraise);
		Long id = coeArticlePraise.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticlePraise mergeEntity(CoeArticlePraise coeArticlePraise) {
		Long id = coeArticlePraise.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeArticlePraise entity = coeArticlePraiseService.mergeEntity(coeArticlePraise);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticlePraise saveOrUpdate(CoeArticlePraise coeArticlePraise) {
		Long id = coeArticlePraise.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticlePraise entity = coeArticlePraiseService.saveOrUpdate(coeArticlePraise);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticlePraise> findPage(CoeArticlePraise coeArticlePraise, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeArticlePraise> page = coeArticlePraiseService.findPage(coeArticlePraise, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeArticlePraiseService.existById(id);
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePraiseService.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeArticlePraise findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePraiseService.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticlePraise> findByMainId(Long mainId, Pageable pageable) {
		return coeArticlePraiseService.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPraisedByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePraiseService.hasPraisedByMainIdAndUserId(mainId,userId);
	}

	@Override
	public CoeArticlePraise mergeForPraise(CoeArticlePraise coeArticlePraise) {
		return coeArticlePraiseService.mergeForPraise(coeArticlePraise);
	}
	
	public CoeArticlePraise mergeForRefreshTime(CoeArticlePraise coeArticlePraise) {
		return coeArticlePraiseService.mergeForRefreshTime(coeArticlePraise);
	}
	
	public CoeArticlePraise praiseAgain(CoeArticlePraise coeArticlePraise) {
		return coeArticlePraiseService.praiseAgain(coeArticlePraise);
	}
}
