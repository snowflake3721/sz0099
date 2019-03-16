package dml.sz0099.course.app.persist.dao.user;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.user.CoeUserBind;
import dml.sz0099.course.app.persist.repository.user.CoeUserBindRepository;
import dml.sz0099.course.app.persist.specification.user.CoeUserBindSpecification;

/**
 * CoeUserBindDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeUserBindDaoImpl extends GenericDaoImpl<CoeUserBind, Long> implements CoeUserBindDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserBindDaoImpl.class);

	@Autowired
	private CoeUserBindRepository coeUserBindRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeUserBindRepository;
	}

	/**
	 * 根据Id查询CoeUserBind实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserBind findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeUserBind coeUserBind = coeUserBindRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeUserBind);
		return coeUserBind;
	}

	@Override
	public CoeUserBind findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserBind coeUserBind = coeUserBindRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserBind);
		return coeUserBind;
	}

	/**
	 * 根据IdList查询CoeUserBind实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserBind> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeUserBind> coeUserBindList = coeUserBindRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeUserBindList);
		return coeUserBindList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeUserBind> findPage(CoeUserBind coeUserBind, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeUserBindSpecification.getConditionWithQsl(coeUserBind);
		Page<CoeUserBind> page = coeUserBindRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeUserBind entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
