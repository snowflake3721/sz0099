package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.profession.ProfessionExtendLogDelegate;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendLog;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionExtendLogWrapperImpl,组件封装
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
public class ProfessionExtendLogWrapperImpl implements ProfessionExtendLogWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionExtendLogWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ProfessionExtendLogDelegate professionExtendLogDelegate;
	
	/**
	 * 根据Id查询ProfessionExtendLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionExtendLog findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ProfessionExtendLog professionExtendLog = professionExtendLogDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, professionExtendLog);
		return professionExtendLog;
	}
	
	@Override
	public ProfessionExtendLog findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionExtendLog professionExtendLog = professionExtendLogDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, professionExtendLog);
		return professionExtendLog;
	}
	
	/**
	 * 根据IdList查询ProfessionExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ProfessionExtendLog> professionExtendLogList = professionExtendLogDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  professionExtendLogList);
		return professionExtendLogList;
	}
	
	@Override
	public ProfessionExtendLog persistEntity(ProfessionExtendLog professionExtendLog) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ProfessionExtendLog entity = professionExtendLogDelegate.persistEntity(professionExtendLog);
		Long id = professionExtendLog.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionExtendLog mergeEntity(ProfessionExtendLog professionExtendLog) {
		Long id = professionExtendLog.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ProfessionExtendLog entity = professionExtendLogDelegate.mergeEntity(professionExtendLog);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionExtendLog saveOrUpdate(ProfessionExtendLog professionExtendLog) {
		Long id = professionExtendLog.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionExtendLog entity = professionExtendLogDelegate.saveOrUpdate(professionExtendLog);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionExtendLog> findPage(ProfessionExtendLog professionExtendLog, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ProfessionExtendLog> page = professionExtendLogDelegate.findPage(professionExtendLog, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return professionExtendLogDelegate.existById(id);
	}
	
	@Override
	public ProfessionExtendLog persistForFail(ProfessionExtend professionExtend) {
		return professionExtendLogDelegate.persistForFail(professionExtend);
	}
}
