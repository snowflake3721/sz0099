package dml.sz0099.course.app.biz.delegate.product;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.product.CoeGradeService;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;

/**
 * coeGradeServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeGradeDelegateImpl implements CoeGradeDelegate, Serializable {


	private static final long serialVersionUID = 3838271541323987652L;

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeGradeDelegateImpl.class);
	
	@Autowired
	private CoeGradeService coeGradeService;

	/**
	 * 根据Id查询CoeGrade实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeGrade findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeGrade coeGrade = coeGradeService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeGrade);
		return coeGrade;
	}
	
	@Override
	public CoeGrade findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeGrade coeGrade = coeGradeService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeGrade);
		return coeGrade;
	}
	
	/**
	 * 根据IdList查询CoeGrade实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeGrade> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeGrade> coeGradeList = coeGradeService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeGradeList);
		return coeGradeList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeGrade persistEntity(CoeGrade coeGrade) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeGrade entity = coeGradeService.persistEntity(coeGrade);
		Long id = coeGrade.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeGrade mergeEntity(CoeGrade coeGrade) {
		Long id = coeGrade.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeGrade entity = coeGradeService.mergeEntity(coeGrade);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeGrade saveOrUpdate(CoeGrade coeGrade) {
		Long id = coeGrade.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeGrade entity = coeGradeService.saveOrUpdate(coeGrade);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeGrade> findPage(CoeGrade coeGrade, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeGrade> page = coeGradeService.findPage(coeGrade, pageable);
		return page;
	}
	
	public List<CoeGrade> findAll() {
		return coeGradeService.findAll();
	}

	@Override
	public CoeGrade findByGrade(Integer grade) {
		return coeGradeService.findByGrade(grade);
	}
}
