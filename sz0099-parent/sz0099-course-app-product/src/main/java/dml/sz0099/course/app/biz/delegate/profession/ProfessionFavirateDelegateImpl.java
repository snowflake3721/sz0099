package dml.sz0099.course.app.biz.delegate.profession;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.profession.ProfessionFavirateService;
import dml.sz0099.course.app.persist.entity.profession.ProfessionFavirate;

/**
 * professionFavirateServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ProfessionFavirateDelegateImpl implements ProfessionFavirateDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionFavirateDelegateImpl.class);
	
	@Autowired
	private ProfessionFavirateService professionFavirateService;

	/**
	 * 根据Id查询ProfessionFavirate实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionFavirate findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ProfessionFavirate professionFavirate = professionFavirateService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, professionFavirate);
		return professionFavirate;
	}
	
	@Override
	public ProfessionFavirate findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionFavirate professionFavirate = professionFavirateService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, professionFavirate);
		return professionFavirate;
	}
	
	/**
	 * 根据IdList查询ProfessionFavirate实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionFavirate> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ProfessionFavirate> professionFavirateList = professionFavirateService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  professionFavirateList);
		return professionFavirateList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ProfessionFavirate persistEntity(ProfessionFavirate professionFavirate) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ProfessionFavirate entity = professionFavirateService.persistEntity(professionFavirate);
		Long id = professionFavirate.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionFavirate mergeEntity(ProfessionFavirate professionFavirate) {
		Long id = professionFavirate.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ProfessionFavirate entity = professionFavirateService.mergeEntity(professionFavirate);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionFavirate saveOrUpdate(ProfessionFavirate professionFavirate) {
		Long id = professionFavirate.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionFavirate entity = professionFavirateService.saveOrUpdate(professionFavirate);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionFavirate> findPage(ProfessionFavirate professionFavirate, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ProfessionFavirate> page = professionFavirateService.findPage(professionFavirate, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionFavirateService.existById(id);
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return professionFavirateService.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public ProfessionFavirate findByMainIdAndUserId(Long mainId, Long userId) {
		return professionFavirateService.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionFavirate> findByMainId(Long mainId, Pageable pageable) {
		return professionFavirateService.findByMainId( mainId,  pageable);
	}
	
	public boolean hasFaviratedByMainIdAndUserId(Long mainId, Long userId) {
		return professionFavirateService.hasFaviratedByMainIdAndUserId(mainId,userId);
	}

	@Override
	public ProfessionFavirate mergeForFavirate(ProfessionFavirate professionFavirate) {
		return professionFavirateService.mergeForFavirate(professionFavirate);
	}
	
}
