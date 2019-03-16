package dml.sz0099.course.app.biz.service.user;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.service.product.CoeGradeService;
import dml.sz0099.course.app.persist.dao.user.CoeUserGradeDao;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;


/**
 * 
 * @formatter:off
 * description: CoeUserGradeServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeUserGradeServiceImpl extends GenericServiceImpl<CoeUserGrade, Long> implements CoeUserGradeService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserGradeServiceImpl.class);

	@Autowired
	private CoeUserGradeDao coeUserGradeDao;
	
	@Autowired
	private CoeGradeService coeGradeService;
	
	@Autowired
	private CoeUserService coeUserService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeUserGradeDao;
	}

	/**
	 * 根据Id查询CoeUserGrade实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserGrade findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeUserGrade coeUserGrade = coeUserGradeDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeUserGrade);
		return coeUserGrade;
	}
	
	@Override
	public CoeUserGrade findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserGrade coeUserGrade = coeUserGradeDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserGrade);
		return coeUserGrade;
	}

	/**
	 * 根据IdList查询CoeUserGrade实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserGrade> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeUserGrade> coeUserGradeList = coeUserGradeDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeUserGradeList);
		return coeUserGradeList;
	}

	@Transactional
	@Override
	public CoeUserGrade persistEntity(CoeUserGrade coeUserGrade) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeUserGrade entity = save(coeUserGrade);
		Long id = coeUserGrade.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeUserGrade.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeUserGrade mergeEntity(CoeUserGrade coeUserGrade) {
		Long id = coeUserGrade.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeUserGrade entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeUserGrade.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeUserGrade.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeUserGrade saveOrUpdate(CoeUserGrade coeUserGrade) {
		Long id = coeUserGrade.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserGrade entity = null;
		if(null != id) {
			entity = mergeEntity(coeUserGrade);
		}else {
			entity = persistEntity(coeUserGrade);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserGrade> findPage(CoeUserGrade coeUserGrade, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeUserGrade> page = coeUserGradeDao.findPage(coeUserGrade, pageable);
		return page;
	}

	@Override
	public CoeUserGrade findByUserId(Long userId) {
		CoeUserGrade coeUserGrade = coeUserGradeDao.findByUserId(userId);
		return coeUserGrade;
	}
	
	public CoeUserGrade findByUserId(Long userId, boolean withUser) {
		CoeUserGrade coeUserGrade = coeUserGradeDao.findByUserId(userId);
		/*if(withUser) {
			CoeUser user = coeUserService.findByUserId(userId);
			coeUserGrade.setu
		}*/
		return coeUserGrade;
	}

	@Transactional
	@Override
	public CoeUserGrade createUserGrade(CoeUser user) {
		Long userId = user.getUserId();
		CoeUserGrade grade = findByUserId( userId);
		if(null == grade) {
			CoeUserGrade userGrade = new CoeUserGrade();
			CoeGrade coeGrade = coeGradeService.findByGrade(CoeGrade.GRADE_L0);
			userGrade.setBaseRadix(coeGrade.getBaseRadix());
			userGrade.setCreatedBy(user.getCreatedBy());
			userGrade.setLastModifiedBy(user.getLastModifiedBy());
			userGrade.setDescription(coeGrade.getDescription());
			userGrade.setFee(coeGrade.getFee());
			userGrade.setGrade(coeGrade.getGrade());
			userGrade.setTagNum(coeGrade.getTagNum());
			userGrade.setProfessionTagNum(coeGrade.getProfessionTagNum());
			userGrade.setName(coeGrade.getName());
			userGrade.setRates(coeGrade.getRates());
			userGrade.setUserId(userId);
			grade= persistEntity(userGrade);
		}
		return grade;
	}

}
