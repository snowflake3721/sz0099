package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.user.CoeUserGradeDelegate;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeUserGradeWrapperImpl,组件封装
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
public class CoeUserGradeWrapperImpl implements CoeUserGradeWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserGradeWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeUserGradeDelegate coeUserGradeDelegate;
	
	/**
	 * 根据Id查询CoeUserGrade实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserGrade findById(Long id) {
		LOGGER.debug("--- CoeUserGradeWrapperImpl.findById begin --------- id is:{} ", id);
		CoeUserGrade coeUserGrade = coeUserGradeDelegate.findById(id);
		LOGGER.debug("--- CoeUserGradeWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeUserGrade);
		return coeUserGrade;
	}
	
	@Override
	public CoeUserGrade findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserGrade coeUserGrade = coeUserGradeDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserGrade);
		return coeUserGrade;
	}
	
	/**
	 * 根据IdList查询CoeUserGrade实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserGrade> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeUserGradeWrapperImpl.findByIdList begin ---------  ");
		List<CoeUserGrade> coeUserGradeList = coeUserGradeDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeUserGradeWrapperImpl.findByIdList end ---------  result is {} ",  coeUserGradeList);
		return coeUserGradeList;
	}
	
	@Override
	public CoeUserGrade persistEntity(CoeUserGrade coeUserGrade) {
		LOGGER.debug("--- CoeUserGradeWrapperImpl.persistEntity begin ---------  ");
		CoeUserGrade entity = coeUserGradeDelegate.persistEntity(coeUserGrade);
		Long id = coeUserGrade.getId();
		LOGGER.debug("--- CoeUserGradeWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserGrade mergeEntity(CoeUserGrade coeUserGrade) {
		Long id = coeUserGrade.getId();
		LOGGER.debug("--- CoeUserGradeWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		CoeUserGrade entity = coeUserGradeDelegate.mergeEntity(coeUserGrade);
		LOGGER.debug("--- CoeUserGradeWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserGrade saveOrUpdate(CoeUserGrade coeUserGrade) {
		Long id = coeUserGrade.getId();
		LOGGER.debug("--- CoeUserGradeWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserGrade entity = coeUserGradeDelegate.saveOrUpdate(coeUserGrade);
		LOGGER.debug("--- CoeUserGradeWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserGrade> findPage(CoeUserGrade coeUserGrade, Pageable pageable) {
		LOGGER.debug("--- CoeUserGradeWrapperImpl.findPage ---------  ");
		Page<CoeUserGrade> page = coeUserGradeDelegate.findPage(coeUserGrade, pageable);
		return page;
	}
	
	public CoeUserGrade findByUserId(Long userId) {
		return coeUserGradeDelegate.findByUserId(userId);
	}
}
