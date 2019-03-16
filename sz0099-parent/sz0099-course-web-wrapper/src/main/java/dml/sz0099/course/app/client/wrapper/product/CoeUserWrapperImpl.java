package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.dozer.DozerBeanMapper;
import org.jit4j.app.client.wrapper.auth.UserRoleWrapper;
import org.jit4j.app.persist.entity.auth.Role;
import org.jit4j.app.persist.entity.auth.User;
import org.jit4j.app.persist.entity.auth.UserRole;
import org.jit4j.app.persist.entity.auth.UserWechat;
import org.jit8j.core.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.user.CoeUserDelegate;
import dml.sz0099.course.app.client.wrapper.user.SaywordWrapper;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeUserWrapperImpl,组件封装
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
public class CoeUserWrapperImpl implements CoeUserWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeUserDelegate coeUserDelegate;
	
	@Autowired
	private SaywordWrapper saywordWrapper;
	
	@Autowired
	private UserRoleWrapper userRoleWrapper;
	
	/**
	 * 根据Id查询CoeUser实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUser findById(Long id) {
		LOGGER.debug("--- CoeUserWrapperImpl.findById begin --------- id is:{} ", id);
		CoeUser coeUser = coeUserDelegate.findById(id);
		LOGGER.debug("--- CoeUserWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeUser);
		return coeUser;
	}
	
	@Override
	public CoeUser findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeUser coeUser = coeUserDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUser);
		return coeUser;
	}
	
	/**
	 * 根据IdList查询CoeUser实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUser> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeUserWrapperImpl.findByIdList begin ---------  ");
		List<CoeUser> coeUserList = coeUserDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeUserWrapperImpl.findByIdList end ---------  result is {} ",  coeUserList);
		return coeUserList;
	}
	
	@Override
	public CoeUser persistEntity(CoeUser coeUser) {
		LOGGER.debug("--- CoeUserWrapperImpl.persistEntity begin ---------  ");
		CoeUser entity = coeUserDelegate.persistEntity(coeUser);
		Long id = coeUser.getId();
		LOGGER.debug("--- CoeUserWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUser mergeEntity(CoeUser coeUser) {
		Long id = coeUser.getId();
		LOGGER.debug("--- CoeUserWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		CoeUser entity = coeUserDelegate.mergeEntity(coeUser);
		LOGGER.debug("--- CoeUserWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUser saveOrUpdate(CoeUser coeUser) {
		Long id = coeUser.getId();
		LOGGER.debug("--- CoeUserWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUser entity = coeUserDelegate.saveOrUpdate(coeUser);
		LOGGER.debug("--- CoeUserWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUser> findPage(CoeUser coeUser, Pageable pageable) {
		LOGGER.debug("--- CoeUserWrapperImpl.findPage ---------  ");
		Page<CoeUser> page = coeUserDelegate.findPage(coeUser, pageable);
		return page;
	}
	
	public CoeUser  findByUserId(Long userId) {
		return coeUserDelegate.findByUserId(userId);
	}
	
	public CoeUser  findByUserId(Long userId, boolean withVerify) {
		return coeUserDelegate.findByUserId(userId, withVerify);
	}
	public CoeUser  findAvailableByUserId(Long userId) {
		CoeUser entity = findByUserId(userId);
		if(null != entity) {
			String nickname = entity.getNickname();
			if(StringUtils.isNotBlank(nickname)) {
				entity.setNickname(Base64Util.decode(nickname));
			}
			entity.setSayword(saywordWrapper.findByUserIdAndMainId(userId));
		}
		return entity;
	}

	
	@Override
	public CoeUser create(User user) {
		CoeUser coeUser = null;
		if(null != user) {
			coeUser = new CoeUser();
			Long userId = user.getId();
			coeUser.setId(userId);
			coeUser.setCreatedBy(user.getCreatedBy());
			coeUser.setUserId(userId);
			coeUser.setLastModifiedBy(user.getLastModifiedBy());
			coeUser.setEmail(user.getEmail());
			coeUser.setQq(user.getQq());
			coeUser.setMobile(user.getMobile());
			//coeUser.setIdstatus(CoeUser.IDSTATUS_0_NO.getValueInt());
			coeUser.setPayRecievable(CoeUser.PAYRECIEVABLE_0_NO.getValueInt());
			UserWechat wechat = user.getWechat();
			if(null != wechat) {
			String nickname = wechat.getNickname();
				coeUser.setNickname(Base64Util.encode(nickname));
				coeUser.setOpenIdwc(wechat.getOpenIdwc());
				coeUser.setHeadImg(wechat.getHeadImg());
				coeUser.setSex(wechat.getSex());
			}
			coeUser = coeUserDelegate.createCoeUser(coeUser);
			
			//URL url = new URL("");
			//FileUtils.toFile(url);
		}
		
		return coeUser;
	}
	
	public CoeUser findThenCreate(User user) {
		Long userId=user.getId();
		CoeUser entity = findByUserId(userId, true,true, true);
		//若用户信息不存在，执行创建
		//User user = UserUtils.getUser();
		if(null == entity) {
			//执行创建
			entity = create(user);
		}
		String nickname = entity.getNickname();
		if(StringUtils.isNotBlank(nickname)) {
			entity.setNickname(Base64Util.decode(nickname));
		}
		return entity;
	}

	@Override
	public CoeUser findByEmail(String email) {
		return coeUserDelegate.findByEmail(email);
	}
	
	@Override
	public CoeUser findByMobile(String mobile) {
		return coeUserDelegate.findByMobile(mobile);
	}
	
	
	
	public CoeUser mergeForEmail(CoeUser coeUser) {
		return coeUserDelegate.mergeForEmail(coeUser);
	}
	
	public CoeUser mergeForMobile(CoeUser coeUser) {
		return coeUserDelegate.mergeForMobile(coeUser);
	}
	
	public CoeUser mergeForNickname(CoeUser coeUser) {
		String nickname = coeUser.getNickname();
		String encode = Base64Util.encode(nickname);
		coeUser.setNickname(encode);
		CoeUser entity = coeUserDelegate.mergeForNickname(coeUser);
		entity.setNickname(Base64Util.decode(entity.getNickname()));
		return entity;
	}
	
	public CoeUser mergeForPostname(CoeUser coeUser) {
		String nickname = coeUser.getPostname();
		//String encode = Base64Util.encode(nickname);
		//coeUser.setNickname(encode);
		CoeUser entity = coeUserDelegate.mergeForPostname(coeUser);
		//entity.setNickname(Base64Util.decode(entity.getNickname()));
		return entity;
	}
	
	public CoeUser mergeForSayword(CoeUser coeUser) {
		CoeUser entity = coeUserDelegate.mergeForSayword(coeUser);
		return entity;
	}
	
	

	@Override
	public CoeUser deleteImage(CoeUser coeUser) {
		
		return coeUserDelegate.deleteImage(coeUser);
	}
	
	public CoeUser mereForImage(CoeUser coeUser) {
			
			return coeUserDelegate.mereForImage(coeUser);
	}
	
	public CoeUser  findByUserId(Long userId, boolean withImages, boolean withVerify,boolean withSayword) {
		return coeUserDelegate.findByUserId( userId,  withImages, withVerify, withSayword) ;
	}

	

	@Override
	public List<CoeUser> findByUserIdList(List<Long> userIdList, boolean withImages) {
		return coeUserDelegate.findByUserIdList( userIdList,  withImages);
	}
	
	public Map<Long, CoeUser> findMapByUserIdList(List<Long> userIdList, boolean withImages) {
		return coeUserDelegate.findMapByUserIdList( userIdList,  withImages);
	}

	@Override
	public CoeUser mergeForPostnameShow(CoeUser coeUser) {
		return coeUserDelegate.mergeForPostnameShow(coeUser);
	}

	@Override
	public CoeUser mergeForMobileShow(CoeUser coeUser) {
		return coeUserDelegate.mergeForMobileShow(coeUser);
	}

	@Override
	public CoeUser mergeForEmailShow(CoeUser coeUser) {
		return coeUserDelegate.mergeForEmailShow(coeUser);
	}

	@Override
	public CoeUser mergeForQqShow(CoeUser coeUser) {
		return coeUserDelegate.mergeForQqShow(coeUser);
	}
	
	@Override
	public UserRole findClubLeader(CoeUser coeUser) {
		Long userId = coeUser.getUserId();
		String roleCode = Role.CODE_CLUB_LEADER.getValueStr();
		UserRole userRole = userRoleWrapper.findByUserIdAndRoleCode(userId, roleCode);
		return userRole;
	}
	
}
