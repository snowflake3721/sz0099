package dml.sz0099.course.app.biz.service.product;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.dao.product.CoeGradeDao;
import dml.sz0099.course.app.persist.data.CoeGradeData;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;


/**
 * 
 * @formatter:off
 * description: CoeGradeServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeGradeServiceImpl extends GenericServiceImpl<CoeGrade, Long> implements CoeGradeService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeGradeServiceImpl.class);

	@Autowired
	private CoeGradeDao coeGradeDao;
	

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeGradeDao;
	}

	/**
	 * 根据Id查询CoeGrade实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeGrade findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeGrade coeGrade = coeGradeDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeGrade);
		return coeGrade;
	}
	
	@Override
	public CoeGrade findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeGrade coeGrade = coeGradeDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeGrade);
		return coeGrade;
	}

	/**
	 * 根据IdList查询CoeGrade实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeGrade> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeGrade> coeGradeList = coeGradeDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeGradeList);
		return coeGradeList;
	}

	@Transactional
	@Override
	public CoeGrade persistEntity(CoeGrade coeGrade) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeGrade entity = save(coeGrade);
		Long id = coeGrade.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeGrade.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeGrade mergeEntity(CoeGrade coeGrade) {
		Long id = coeGrade.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeGrade entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeGrade.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeGrade.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeGrade saveOrUpdate(CoeGrade coeGrade) {
		Long id = coeGrade.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeGrade entity = null;
		if(null != id) {
			entity = mergeEntity(coeGrade);
		}else {
			entity = persistEntity(coeGrade);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeGrade> findPage(CoeGrade coeGrade, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeGrade> page = coeGradeDao.findPage(coeGrade, pageable);
		return page;
	}

	@Override
	public List<CoeGrade> initData() {
		List<CoeGrade> dataList = CoeGradeData.generateCoeGradeList();
		save(dataList);
		return dataList;
	}

	/*
	 * 根据会员等级查询折扣系数
	 */
	@Override
	public CoeGrade findByGrade(Integer grade) {
		CoeGrade entity = coeGradeDao.findByGrade( grade);
		return entity;
	}

}
