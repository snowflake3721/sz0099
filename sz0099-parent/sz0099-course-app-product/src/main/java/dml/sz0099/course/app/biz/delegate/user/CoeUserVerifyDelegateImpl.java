package dml.sz0099.course.app.biz.delegate.user;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.user.CoeUserVerifyService;
import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;

/**
 * coeUserVerifyServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeUserVerifyDelegateImpl implements CoeUserVerifyDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserVerifyDelegateImpl.class);
	
	@Autowired
	private CoeUserVerifyService coeUserVerifyService;

	/**
	 * 根据Id查询CoeUserVerify实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserVerify findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeUserVerify coeUserVerify = coeUserVerifyService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeUserVerify);
		return coeUserVerify;
	}
	
	@Override
	public CoeUserVerify findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserVerify coeUserVerify = coeUserVerifyService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserVerify);
		return coeUserVerify;
	}
	
	/**
	 * 根据IdList查询CoeUserVerify实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserVerify> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeUserVerify> coeUserVerifyList = coeUserVerifyService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeUserVerifyList);
		return coeUserVerifyList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeUserVerify persistEntity(CoeUserVerify coeUserVerify) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeUserVerify entity = coeUserVerifyService.persistEntity(coeUserVerify);
		Long id = coeUserVerify.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserVerify mergeEntity(CoeUserVerify coeUserVerify) {
		Long id = coeUserVerify.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeUserVerify entity = coeUserVerifyService.mergeEntity(coeUserVerify);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserVerify saveOrUpdate(CoeUserVerify coeUserVerify) {
		Long id = coeUserVerify.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserVerify entity = coeUserVerifyService.saveOrUpdate(coeUserVerify);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserVerify> findPage(CoeUserVerify coeUserVerify, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeUserVerify> page = coeUserVerifyService.findPage(coeUserVerify, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeUserVerifyService.existById(id);
	}
	
	public CoeUserVerify verifyIdentity(CoeUserVerify coeUser) {
		return coeUserVerifyService.verifyIdentity(coeUser);
	}
	public CoeUserVerify applyIdentity(CoeUserVerify coeUser)  {
		return coeUserVerifyService.applyIdentity(coeUser);
	}
	
	public CoeUserVerify findByIdentity(CoeUserVerify coeUser) {
		return coeUserVerifyService.findByIdentity(coeUser);
	}
	
	public CoeUserVerify findNotSelfByIdentity(CoeUserVerify coeUser) {
		return coeUserVerifyService.findNotSelfByIdentity(coeUser);
	}
	
	public CoeUserVerify findByUserId(Long userId) {
		return coeUserVerifyService.findByUserId(userId);
	}
	
	public CoeUserVerify mergeForRealname(CoeUserVerify coeUser) {
		return coeUserVerifyService.mergeForRealname(coeUser);
	}
	
	public Page<CoeUserVerify> findPageForVerify(CoeUserVerify coeUserVerify, Pageable pageable){
		return coeUserVerifyService.findPageForVerify(coeUserVerify, pageable);
	}
}
