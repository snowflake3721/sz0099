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

import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;
import dml.sz0099.course.app.persist.repository.user.CoeUserRepository;
import dml.sz0099.course.app.persist.specification.user.CoeUserSpecification;

/**
 * CoeUserDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CoeUserDaoImpl extends GenericDaoImpl<CoeUser, Long> implements CoeUserDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserDaoImpl.class);
	
	@Autowired
	private CoeUserRepository coeUserRepository;
	
	@Autowired
	private CoeUserGradeDao coeUserGradeDao;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeUserRepository;
	}

	/**
	 * 根据Id查询CoeUser实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUser findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeUser coeUser = coeUserRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeUser);
		return coeUser;
	}
	
	@Override
	public CoeUser findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeUser coeUser = coeUserRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUser);
		return coeUser;
	}
	
	/**
	 * 根据IdList查询CoeUser实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUser> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeUser> coeUserList = coeUserRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  coeUserList);
		return coeUserList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeUser> findPage(CoeUser coeUser, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeUserSpecification.getConditionWithQsl(coeUser);
		Page<CoeUser> page = coeUserRepository.findAll(condition, pageable);
		return page;
	}
	
	public CoeUser  findByUserId(Long userId) {
		CoeUser entity = coeUserRepository.findByUserId(userId);
		if(null != entity) {
			CoeUserGrade coeUserGrade  = coeUserGradeDao.findByUserId(userId);
			entity.setUserGrade(coeUserGrade);
		}
		LOGGER.debug("--- dao.findByUserId , userId is:{} ---------", userId);
		return entity;
	}
	
	public List<CoeUser> findByUserIdList(List<Long> userIdList) {
		return coeUserRepository.findByUserIdList( userIdList);
	}
	
	@Override
	public CoeUser findByEmail(String email) {
		return coeUserRepository.findByEmail(email);
	}
	
	@Override
	public CoeUser findByMobile(String mobile) {
		return coeUserRepository.findByMobile(mobile);
	}
	

}
