package dml.sz0099.course.app.biz.delegate.profession;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.profession.ProfessionPraiseService;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPraise;

/**
 * professionPraiseServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ProfessionPraiseDelegateImpl implements ProfessionPraiseDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionPraiseDelegateImpl.class);
	
	@Autowired
	private ProfessionPraiseService professionPraiseService;

	/**
	 * 根据Id查询ProfessionPraise实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionPraise findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ProfessionPraise professionPraise = professionPraiseService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, professionPraise);
		return professionPraise;
	}
	
	@Override
	public ProfessionPraise findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionPraise professionPraise = professionPraiseService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, professionPraise);
		return professionPraise;
	}
	
	/**
	 * 根据IdList查询ProfessionPraise实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionPraise> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ProfessionPraise> professionPraiseList = professionPraiseService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  professionPraiseList);
		return professionPraiseList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ProfessionPraise persistEntity(ProfessionPraise professionPraise) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ProfessionPraise entity = professionPraiseService.persistEntity(professionPraise);
		Long id = professionPraise.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionPraise mergeEntity(ProfessionPraise professionPraise) {
		Long id = professionPraise.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ProfessionPraise entity = professionPraiseService.mergeEntity(professionPraise);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionPraise saveOrUpdate(ProfessionPraise professionPraise) {
		Long id = professionPraise.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionPraise entity = professionPraiseService.saveOrUpdate(professionPraise);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionPraise> findPage(ProfessionPraise professionPraise, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ProfessionPraise> page = professionPraiseService.findPage(professionPraise, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionPraiseService.existById(id);
	}
	
	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return professionPraiseService.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public ProfessionPraise findByMainIdAndUserId(Long mainId, Long userId) {
		return professionPraiseService.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<ProfessionPraise> findByMainId(Long mainId, Pageable pageable) {
		return professionPraiseService.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPraisedByMainIdAndUserId(Long mainId, Long userId) {
		return professionPraiseService.hasPraisedByMainIdAndUserId(mainId,userId);
	}

	@Override
	public ProfessionPraise mergeForPraise(ProfessionPraise professionPraise) {
		return professionPraiseService.mergeForPraise(professionPraise);
	}
	
	public ProfessionPraise mergeForRefreshTime(ProfessionPraise professionPraise) {
		return professionPraiseService.mergeForRefreshTime(professionPraise);
	}
	
	public ProfessionPraise praiseAgain(ProfessionPraise professionPraise) {
		professionPraise=professionPraiseService.praiseAgain(professionPraise);
		return professionPraise;
	}
}
