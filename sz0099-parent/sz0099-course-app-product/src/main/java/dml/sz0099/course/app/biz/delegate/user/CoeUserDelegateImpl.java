package dml.sz0099.course.app.biz.delegate.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.user.CoeUserService;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * coeUserServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeUserDelegateImpl implements CoeUserDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserDelegateImpl.class);
	
	@Autowired
	private CoeUserService coeUserService;

	/**
	 * 根据Id查询CoeUser实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUser findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeUser coeUser = coeUserService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeUser);
		return coeUser;
	}
	
	@Override
	public CoeUser findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeUser coeUser = coeUserService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUser);
		return coeUser;
	}
	
	/**
	 * 根据IdList查询CoeUser实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUser> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeUser> coeUserList = coeUserService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeUserList);
		return coeUserList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeUser persistEntity(CoeUser coeUser) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeUser entity = coeUserService.persistEntity(coeUser);
		Long id = coeUser.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUser mergeEntity(CoeUser coeUser) {
		Long id = coeUser.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeUser entity = coeUserService.mergeEntity(coeUser);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUser saveOrUpdate(CoeUser coeUser) {
		Long id = coeUser.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUser entity = coeUserService.saveOrUpdate(coeUser);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUser> findPage(CoeUser coeUser, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeUser> page = coeUserService.findPage(coeUser, pageable);
		return page;
	}
	
	@Override
	public CoeUser  findByUserId(Long userId) {
		return coeUserService.findByUserId(userId);
	}
	
	
	public CoeUser createCoeUser(CoeUser coeUser) {
		return coeUserService.createCoeUser(coeUser);
	}
	
	@Override
	public CoeUser findByEmail(String email) {
		return coeUserService.findByEmail(email);
	}
	
	@Override
	public CoeUser findByMobile(String mobile) {
		return coeUserService.findByMobile(mobile);
	}
	
	public CoeUser mergeForEmail(CoeUser coeUser) {
		return coeUserService.mergeForEmail(coeUser);
	}
	
	public CoeUser mergeForMobile(CoeUser coeUser) {
		return coeUserService.mergeForMobile(coeUser);
	}
	
	public CoeUser deleteImage(CoeUser coeUser) {
		
		return coeUserService.deleteImage(coeUser);
	}
	
	public CoeUser mereForImage(CoeUser coeUser) {
		return coeUserService.mereForImage(coeUser);
	}
	
	public CoeUser  findByUserId(Long userId, boolean withImages, boolean withVerify, boolean withSayword) {
		return coeUserService.findByUserId( userId,  withImages, withVerify, withSayword) ;
	}
	
	public CoeUser mergeForNickname(CoeUser coeUser) {
		return coeUserService.mergeForNickname(coeUser);
	}
	public CoeUser mergeForPostname(CoeUser coeUser)  {
		return coeUserService.mergeForPostname(coeUser);
	}
	
	@Override
	public CoeUser mergeForPostnameShow(CoeUser coeUser) {
		return coeUserService.mergeForPostnameShow(coeUser);
	}

	@Override
	public CoeUser mergeForMobileShow(CoeUser coeUser) {
		return coeUserService.mergeForMobileShow(coeUser);
	}

	@Override
	public CoeUser mergeForEmailShow(CoeUser coeUser) {
		return coeUserService.mergeForEmailShow(coeUser);
	}

	@Override
	public CoeUser mergeForQqShow(CoeUser coeUser) {
		return coeUserService.mergeForQqShow(coeUser);
	}
	
	public CoeUser mergeForSayword(CoeUser coeUser) {
		CoeUser entity = coeUserService.mergeForSayword(coeUser);
		return entity;
	}
	
	
	public List<CoeUser> findByUserIdList(List<Long> userIdList, boolean withImages) {
		return coeUserService.findByUserIdList( userIdList,  withImages);
	}
	
	public Map<Long, CoeUser> findMapByUserIdList(List<Long> userIdList, boolean withImages) {
		return coeUserService.findMapByUserIdList( userIdList,  withImages);
	}

	public CoeUser  findByUserId(Long userId, boolean withVerify) {
		return coeUserService.findByUserId(userId, withVerify);
	}
	
}
