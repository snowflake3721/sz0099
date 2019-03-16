package dml.sz0099.course.app.persist.dao.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.jit4j.core.persist.page.PageResultBaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;
import dml.sz0099.course.app.persist.repository.user.CoeUserVerifyRepository;
import dml.sz0099.course.app.persist.specification.user.CoeUserVerifySpecification;

/**
 * CoeUserVerifyDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeUserVerifyDaoImpl extends GenericDaoImpl<CoeUserVerify, Long> implements CoeUserVerifyDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserVerifyDaoImpl.class);

	@Autowired
	private CoeUserVerifyRepository coeUserVerifyRepository;
	
	@Autowired
	private CoeUserDao coeUserDao;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeUserVerifyRepository;
	}

	/**
	 * 根据Id查询CoeUserVerify实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserVerify findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeUserVerify coeUserVerify = coeUserVerifyRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeUserVerify);
		return coeUserVerify;
	}

	@Override
	public CoeUserVerify findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserVerify coeUserVerify = coeUserVerifyRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserVerify);
		return coeUserVerify;
	}

	/**
	 * 根据IdList查询CoeUserVerify实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserVerify> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeUserVerify> coeUserVerifyList = coeUserVerifyRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeUserVerifyList);
		return coeUserVerifyList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeUserVerify> findPage(CoeUserVerify coeUserVerify, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeUserVerifySpecification.getConditionWithQsl(coeUserVerify);
		Page<CoeUserVerify> page = coeUserVerifyRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeUserVerify entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public CoeUserVerify findNotSelfByIdentity(CoeUserVerify coeUser) {
		String identity = coeUser.getIdentity();
		Long userId = coeUser.getUserId();
		CoeUserVerify entity = coeUserVerifyRepository.findNotSelfbyIdentity(identity, userId);
		
		return entity;
	}
	
	public CoeUserVerify findByIdentity(String identity) {
		
		CoeUserVerify entity = coeUserVerifyRepository.findByIdentity(identity);
		
		return entity;
	}
	
	public CoeUserVerify findByUserId(Long userId) {
		return coeUserVerifyRepository.findByUserId(userId);
	}
	
	public CoeUserVerify findByCoeUserId(Long coeUserId) {
		return coeUserVerifyRepository.findByCoeUserId(coeUserId);
	}
	
	public List<CoeUserVerify> findByUserIdList(List<Long> userIdList){
		return coeUserVerifyRepository.findByUserIdList(userIdList);
	}
	
	/**
	 * 查询认证状态用户
	 */
	public Page<CoeUserVerify> findPageForVerify(CoeUserVerify coeUserVerify, Pageable pageable) {
		LOGGER.debug("--- dao.findPageForVerify ---------  ");
		CoeUser user = coeUserVerify.getCoeUser();
		if(null !=user) {
			String mobile = user.getMobile();
			if(StringUtils.isNotBlank(mobile)) {
				CoeUser userE = coeUserDao.findByMobile(mobile);
				if(null != userE) {
					Long userId = userE.getUserId();
					CoeUserVerify verify = findByUserId(userId);
					if(null != verify) {
						verify.setCoeUser(userE);
						List<CoeUserVerify> content = new ArrayList<>(1);
						content.add(verify);
						Page<CoeUserVerify> page = new PageResultBaseEntity<>(content, pageable, 1);
						return page;
					}
				}else {
					List<CoeUserVerify> content = new ArrayList<>(0);
					Page<CoeUserVerify> page = new PageResultBaseEntity<>(content, pageable, 0);
					return page;
				}
			}
		}
		BooleanExpression condition = CoeUserVerifySpecification.getConditionForVerify(coeUserVerify);
		Page<CoeUserVerify> page = coeUserVerifyRepository.findAll(condition, pageable);
		return page;
	}

}
