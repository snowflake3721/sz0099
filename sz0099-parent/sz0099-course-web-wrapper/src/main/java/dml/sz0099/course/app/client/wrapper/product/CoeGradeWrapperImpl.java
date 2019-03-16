package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.product.CoeGradeDelegate;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeGradeWrapperImpl,组件封装
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
public class CoeGradeWrapperImpl implements CoeGradeWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeGradeWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeGradeDelegate coeGradeDelegate;
	
	/**
	 * 根据Id查询CoeGrade实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeGrade findById(Long id) {
		LOGGER.debug("--- CoeGradeWrapperImpl.findById begin --------- id is:{} ", id);
		CoeGrade coeGrade = coeGradeDelegate.findById(id);
		LOGGER.debug("--- CoeGradeWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeGrade);
		return coeGrade;
	}
	
	@Override
	public CoeGrade findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeGrade coeGrade = coeGradeDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeGrade);
		return coeGrade;
	}
	
	/**
	 * 根据IdList查询CoeGrade实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeGrade> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeGradeWrapperImpl.findByIdList begin ---------  ");
		List<CoeGrade> coeGradeList = coeGradeDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeGradeWrapperImpl.findByIdList end ---------  result is {} ",  coeGradeList);
		return coeGradeList;
	}
	
	@Override
	public CoeGrade persistEntity(CoeGrade coeGrade) {
		LOGGER.debug("--- CoeGradeWrapperImpl.persistEntity begin ---------  ");
		CoeGrade entity = coeGradeDelegate.persistEntity(coeGrade);
		Long id = coeGrade.getId();
		LOGGER.debug("--- CoeGradeWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeGrade mergeEntity(CoeGrade coeGrade) {
		Long id = coeGrade.getId();
		LOGGER.debug("--- CoeGradeWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		CoeGrade entity = coeGradeDelegate.mergeEntity(coeGrade);
		LOGGER.debug("--- CoeGradeWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeGrade saveOrUpdate(CoeGrade coeGrade) {
		Long id = coeGrade.getId();
		LOGGER.debug("--- CoeGradeWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		CoeGrade entity = coeGradeDelegate.saveOrUpdate(coeGrade);
		LOGGER.debug("--- CoeGradeWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeGrade> findPage(CoeGrade coeGrade, Pageable pageable) {
		LOGGER.debug("--- CoeGradeWrapperImpl.findPage ---------  ");
		Page<CoeGrade> page = coeGradeDelegate.findPage(coeGrade, pageable);
		return page;
	}

	@Override
	public List<CoeGrade> findAll() {
		return coeGradeDelegate.findAll();
	}
}
