package dml.sz0099.course.app.biz.delegate.profession;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.profession.ProfessionRefService;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;

/**
 * professionRefServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ProfessionRefDelegateImpl implements ProfessionRefDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionRefDelegateImpl.class);
	
	@Autowired
	private ProfessionRefService professionRefService;

	/**
	 * 根据Id查询ProfessionRef实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionRef findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ProfessionRef professionRef = professionRefService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, professionRef);
		return professionRef;
	}
	
	@Override
	public ProfessionRef findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionRef professionRef = professionRefService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, professionRef);
		return professionRef;
	}
	
	/**
	 * 根据IdList查询ProfessionRef实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ProfessionRef> professionRefList = professionRefService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  professionRefList);
		return professionRefList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ProfessionRef persistEntity(ProfessionRef professionRef) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ProfessionRef entity = professionRefService.persistEntity(professionRef);
		Long id = professionRef.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionRef mergeEntity(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ProfessionRef entity = professionRefService.mergeEntity(professionRef);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionRef saveOrUpdate(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionRef entity = professionRefService.saveOrUpdate(professionRef);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionRef> findPage(ProfessionRef professionRef, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ProfessionRef> page = professionRefService.findPage(professionRef, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionRefService.existById(id);
	}
	
	public Long countForBase(ProfessionRef professionRef) {
		Long baseId = professionRef.getBaseId();
		return professionRefService.countForBase(professionRef);
	}
	
	public ProfessionRef changeSingle(ProfessionRef professionRef) {
		professionRef=professionRefService.changeSingle(professionRef);
		return professionRef;
	}

	@Override
	public List<ProfessionRef> findByBaseId(Long baseId) {
		return professionRefService.findByBaseId(baseId);
	}

	@Override
	public Page<ProfessionRef> findPageByBaseId(Long baseId, Pageable pageable, boolean withCover, boolean withBanner) {
		return professionRefService.findPageByBaseId(baseId, pageable, withCover,withBanner);
	}
	
	
	
	public List<ProfessionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList) {
		return professionRefService.findByBaseIdAndMainIdList( baseId, mainIdList) ;
	}
	
	public ProfessionRef findMainIdAndBaseId(ProfessionRef professionRef) {
		return professionRefService.findMainIdAndBaseId(professionRef);
	}
	
	public void deleteById(ProfessionRef professionRef) {
		professionRefService.deleteById(professionRef);
	}
	
	public ProfessionRef mergeProfessionRef(ProfessionRef professionRef) {
		return professionRefService.mergeProfessionRef(professionRef);
	}
	
	public ProfessionRef openProfessionRef(ProfessionRef professionRef) {
		professionRef = professionRefService.openProfessionRef(professionRef);
		return professionRef;
	}
	
	public ProfessionRef mergeSimpleSingle(ProfessionRef professionRef) {
			professionRef = professionRefService.mergeSimpleSingle(professionRef);
		return professionRef;
	}
	
	public ProfessionRef deleteRefByBaseId(ProfessionRef professionRef) {
		Long baseId = professionRef.getBaseId();
		if(null != baseId) {
			professionRef = professionRefService.deleteRefByBaseId(professionRef);
		}
		return professionRef;
	}
}
