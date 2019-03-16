package dml.sz0099.course.app.biz.delegate.user;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.user.CoeUserGradeService;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;

/**
 * coeUserGradeServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeUserGradeDelegateImpl implements CoeUserGradeDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserGradeDelegateImpl.class);
	
	@Autowired
	private CoeUserGradeService coeUserGradeService;

	/**
	 * 根据Id查询CoeUserGrade实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserGrade findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeUserGrade coeUserGrade = coeUserGradeService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeUserGrade);
		return coeUserGrade;
	}
	
	@Override
	public CoeUserGrade findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserGrade coeUserGrade = coeUserGradeService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserGrade);
		return coeUserGrade;
	}
	
	/**
	 * 根据IdList查询CoeUserGrade实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserGrade> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeUserGrade> coeUserGradeList = coeUserGradeService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeUserGradeList);
		return coeUserGradeList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeUserGrade persistEntity(CoeUserGrade coeUserGrade) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeUserGrade entity = coeUserGradeService.persistEntity(coeUserGrade);
		Long id = coeUserGrade.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserGrade mergeEntity(CoeUserGrade coeUserGrade) {
		Long id = coeUserGrade.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeUserGrade entity = coeUserGradeService.mergeEntity(coeUserGrade);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserGrade saveOrUpdate(CoeUserGrade coeUserGrade) {
		Long id = coeUserGrade.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserGrade entity = coeUserGradeService.saveOrUpdate(coeUserGrade);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserGrade> findPage(CoeUserGrade coeUserGrade, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeUserGrade> page = coeUserGradeService.findPage(coeUserGrade, pageable);
		return page;
	}
	@Override
	public CoeUserGrade findByUserId(Long userId) {
		return coeUserGradeService.findByUserId(userId);
	}
}
