package dml.sz0099.course.app.biz.delegate.article;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.article.CoeArticleFavirateService;
import dml.sz0099.course.app.persist.entity.article.CoeArticleFavirate;

/**
 * coeArticleFavirateServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeArticleFavirateDelegateImpl implements CoeArticleFavirateDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleFavirateDelegateImpl.class);
	
	@Autowired
	private CoeArticleFavirateService coeArticleFavirateService;

	/**
	 * 根据Id查询CoeArticleFavirate实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticleFavirate findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeArticleFavirate coeArticleFavirate = coeArticleFavirateService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeArticleFavirate);
		return coeArticleFavirate;
	}
	
	@Override
	public CoeArticleFavirate findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticleFavirate coeArticleFavirate = coeArticleFavirateService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticleFavirate);
		return coeArticleFavirate;
	}
	
	/**
	 * 根据IdList查询CoeArticleFavirate实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticleFavirate> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeArticleFavirate> coeArticleFavirateList = coeArticleFavirateService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeArticleFavirateList);
		return coeArticleFavirateList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeArticleFavirate persistEntity(CoeArticleFavirate coeArticleFavirate) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeArticleFavirate entity = coeArticleFavirateService.persistEntity(coeArticleFavirate);
		Long id = coeArticleFavirate.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticleFavirate mergeEntity(CoeArticleFavirate coeArticleFavirate) {
		Long id = coeArticleFavirate.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeArticleFavirate entity = coeArticleFavirateService.mergeEntity(coeArticleFavirate);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeArticleFavirate saveOrUpdate(CoeArticleFavirate coeArticleFavirate) {
		Long id = coeArticleFavirate.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticleFavirate entity = coeArticleFavirateService.saveOrUpdate(coeArticleFavirate);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticleFavirate> findPage(CoeArticleFavirate coeArticleFavirate, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeArticleFavirate> page = coeArticleFavirateService.findPage(coeArticleFavirate, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeArticleFavirateService.existById(id);
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticleFavirateService.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeArticleFavirate findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticleFavirateService.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticleFavirate> findByMainId(Long mainId, Pageable pageable) {
		return coeArticleFavirateService.findByMainId( mainId,  pageable);
	}
	
	public boolean hasFaviratedByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticleFavirateService.hasFaviratedByMainIdAndUserId(mainId,userId);
	}

	@Override
	public CoeArticleFavirate mergeForFavirate(CoeArticleFavirate coeArticleFavirate) {
		return coeArticleFavirateService.mergeForFavirate(coeArticleFavirate);
	}
}
