package dml.sz0099.course.app.biz.delegate.profession;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.profession.ProfessionExtendLogService;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendLog;

/**
 * professionExtendLogServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ProfessionExtendLogDelegateImpl implements ProfessionExtendLogDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionExtendLogDelegateImpl.class);
	
	@Autowired
	private ProfessionExtendLogService professionExtendLogService;

	/**
	 * 根据Id查询ProfessionExtendLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionExtendLog findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ProfessionExtendLog professionExtendLog = professionExtendLogService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, professionExtendLog);
		return professionExtendLog;
	}
	
	@Override
	public ProfessionExtendLog findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionExtendLog professionExtendLog = professionExtendLogService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, professionExtendLog);
		return professionExtendLog;
	}
	
	/**
	 * 根据IdList查询ProfessionExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ProfessionExtendLog> professionExtendLogList = professionExtendLogService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  professionExtendLogList);
		return professionExtendLogList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ProfessionExtendLog persistEntity(ProfessionExtendLog professionExtendLog) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ProfessionExtendLog entity = professionExtendLogService.persistEntity(professionExtendLog);
		Long id = professionExtendLog.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionExtendLog mergeEntity(ProfessionExtendLog professionExtendLog) {
		Long id = professionExtendLog.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ProfessionExtendLog entity = professionExtendLogService.mergeEntity(professionExtendLog);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionExtendLog saveOrUpdate(ProfessionExtendLog professionExtendLog) {
		Long id = professionExtendLog.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionExtendLog entity = professionExtendLogService.saveOrUpdate(professionExtendLog);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionExtendLog> findPage(ProfessionExtendLog professionExtendLog, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ProfessionExtendLog> page = professionExtendLogService.findPage(professionExtendLog, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionExtendLogService.existById(id);
	}
	
	@Override
	public ProfessionExtendLog persistForFail(ProfessionExtend professionExtend) {
		return professionExtendLogService.persistForFail(professionExtend);
	}
}
