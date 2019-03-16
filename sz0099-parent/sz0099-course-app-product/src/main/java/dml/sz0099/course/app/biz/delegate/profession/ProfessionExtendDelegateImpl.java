package dml.sz0099.course.app.biz.delegate.profession;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.profession.ProfessionExtendService;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;

/**
 * professionExtendServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ProfessionExtendDelegateImpl implements ProfessionExtendDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionExtendDelegateImpl.class);
	
	@Autowired
	private ProfessionExtendService professionExtendService;

	/**
	 * 根据Id查询ProfessionExtend实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionExtend findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ProfessionExtend professionExtend = professionExtendService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, professionExtend);
		return professionExtend;
	}
	
	@Override
	public ProfessionExtend findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionExtend professionExtend = professionExtendService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, professionExtend);
		return professionExtend;
	}
	
	/**
	 * 根据IdList查询ProfessionExtend实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ProfessionExtend> professionExtendList = professionExtendService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  professionExtendList);
		return professionExtendList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ProfessionExtend persistEntity(ProfessionExtend professionExtend) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ProfessionExtend entity = professionExtendService.persistEntity(professionExtend);
		Long id = professionExtend.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionExtend mergeEntity(ProfessionExtend professionExtend) {
		Long id = professionExtend.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ProfessionExtend entity = professionExtendService.mergeEntity(professionExtend);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionExtend saveOrUpdate(ProfessionExtend professionExtend) {
		Long id = professionExtend.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionExtend entity = professionExtendService.saveOrUpdate(professionExtend);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionExtend> findPage(ProfessionExtend professionExtend, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ProfessionExtend> page = professionExtendService.findPage(professionExtend, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionExtendService.existById(id);
	}

	@Override
	public List<ProfessionExtend> findAll() {
		return professionExtendService.findAll();
	}
	
	public ProfessionExtend create(ProfessionExtend professionExtend) {
		return professionExtendService.create(professionExtend);
	}
	
	
	@Override
	public ProfessionExtend findByPositionId(Long positionId) {
		return professionExtendService.findByPositionId(positionId);
	}

	@Override
	public ProfessionExtend findProfessionExtend(ProfessionExtend extend) {
		return professionExtendService.findProfessionExtend(extend);
	}
	
	public Long findPositionIdById(Long id) {
		return professionExtendService.findPositionIdById(id);
	}
	
	public Long countByUserId(Long userId) {
		return professionExtendService.countByUserId(userId);
	}
	
	public ProfessionExtend deleteEntity(ProfessionExtend extend) {
		
		return professionExtendService.deleteEntity(extend);
	}
}
