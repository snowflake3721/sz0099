package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.user.CoeUserGradeLogDelegate;
import dml.sz0099.course.app.persist.entity.user.CoeUserGradeLog;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeUserGradeLogWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class CoeUserGradeLogWrapperImpl implements CoeUserGradeLogWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserGradeLogWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeUserGradeLogDelegate coeUserGradeLogDelegate;
	
	/**
	 * 根据Id查询CoeUserGradeLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserGradeLog findById(Long id) {
		LOGGER.debug("--- CoeUserGradeLogWrapperImpl.findById begin --------- id is:{} ", id);
		CoeUserGradeLog coeUserGradeLog = coeUserGradeLogDelegate.findById(id);
		LOGGER.debug("--- CoeUserGradeLogWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeUserGradeLog);
		return coeUserGradeLog;
	}
	
	@Override
	public CoeUserGradeLog findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserGradeLog coeUserGradeLog = coeUserGradeLogDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserGradeLog);
		return coeUserGradeLog;
	}
	
	/**
	 * 根据IdList查询CoeUserGradeLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserGradeLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeUserGradeLogWrapperImpl.findByIdList begin ---------  ");
		List<CoeUserGradeLog> coeUserGradeLogList = coeUserGradeLogDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeUserGradeLogWrapperImpl.findByIdList end ---------  result is {} ",  coeUserGradeLogList);
		return coeUserGradeLogList;
	}
	
	@Override
	public CoeUserGradeLog persistEntity(CoeUserGradeLog coeUserGradeLog) {
		LOGGER.debug("--- CoeUserGradeLogWrapperImpl.persistEntity begin ---------  ");
		CoeUserGradeLog entity = coeUserGradeLogDelegate.persistEntity(coeUserGradeLog);
		Long id = coeUserGradeLog.getId();
		LOGGER.debug("--- CoeUserGradeLogWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserGradeLog mergeEntity(CoeUserGradeLog coeUserGradeLog) {
		Long id = coeUserGradeLog.getId();
		LOGGER.debug("--- CoeUserGradeLogWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		CoeUserGradeLog entity = coeUserGradeLogDelegate.mergeEntity(coeUserGradeLog);
		LOGGER.debug("--- CoeUserGradeLogWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserGradeLog saveOrUpdate(CoeUserGradeLog coeUserGradeLog) {
		Long id = coeUserGradeLog.getId();
		LOGGER.debug("--- CoeUserGradeLogWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserGradeLog entity = coeUserGradeLogDelegate.saveOrUpdate(coeUserGradeLog);
		LOGGER.debug("--- CoeUserGradeLogWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserGradeLog> findPage(CoeUserGradeLog coeUserGradeLog, Pageable pageable) {
		LOGGER.debug("--- CoeUserGradeLogWrapperImpl.findPage ---------  ");
		Page<CoeUserGradeLog> page = coeUserGradeLogDelegate.findPage(coeUserGradeLog, pageable);
		return page;
	}
}
