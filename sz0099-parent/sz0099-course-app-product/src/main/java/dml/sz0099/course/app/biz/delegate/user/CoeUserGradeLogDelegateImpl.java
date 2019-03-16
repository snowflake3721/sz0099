package dml.sz0099.course.app.biz.delegate.user;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.user.CoeUserGradeLogService;
import dml.sz0099.course.app.persist.entity.user.CoeUserGradeLog;

/**
 * coeUserGradeLogServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeUserGradeLogDelegateImpl implements CoeUserGradeLogDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserGradeLogDelegateImpl.class);
	
	@Autowired
	private CoeUserGradeLogService coeUserGradeLogService;

	/**
	 * 根据Id查询CoeUserGradeLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserGradeLog findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeUserGradeLog coeUserGradeLog = coeUserGradeLogService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeUserGradeLog);
		return coeUserGradeLog;
	}
	
	@Override
	public CoeUserGradeLog findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserGradeLog coeUserGradeLog = coeUserGradeLogService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserGradeLog);
		return coeUserGradeLog;
	}
	
	/**
	 * 根据IdList查询CoeUserGradeLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserGradeLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeUserGradeLog> coeUserGradeLogList = coeUserGradeLogService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeUserGradeLogList);
		return coeUserGradeLogList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeUserGradeLog persistEntity(CoeUserGradeLog coeUserGradeLog) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeUserGradeLog entity = coeUserGradeLogService.persistEntity(coeUserGradeLog);
		Long id = coeUserGradeLog.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserGradeLog mergeEntity(CoeUserGradeLog coeUserGradeLog) {
		Long id = coeUserGradeLog.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeUserGradeLog entity = coeUserGradeLogService.mergeEntity(coeUserGradeLog);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserGradeLog saveOrUpdate(CoeUserGradeLog coeUserGradeLog) {
		Long id = coeUserGradeLog.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserGradeLog entity = coeUserGradeLogService.saveOrUpdate(coeUserGradeLog);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserGradeLog> findPage(CoeUserGradeLog coeUserGradeLog, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeUserGradeLog> page = coeUserGradeLogService.findPage(coeUserGradeLog, pageable);
		return page;
	}
}
