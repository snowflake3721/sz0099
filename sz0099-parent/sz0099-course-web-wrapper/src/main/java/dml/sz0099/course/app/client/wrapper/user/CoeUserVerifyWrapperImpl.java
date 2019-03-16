package dml.sz0099.course.app.client.wrapper.user;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.jit4j.core.persist.page.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.user.CoeUserVerifyDelegate;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeUserVerifyWrapperImpl,组件封装
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
public class CoeUserVerifyWrapperImpl implements CoeUserVerifyWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserVerifyWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeUserVerifyDelegate coeUserVerifyDelegate;
	
	/**
	 * 根据Id查询CoeUserVerify实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserVerify findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeUserVerify coeUserVerify = coeUserVerifyDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeUserVerify);
		return coeUserVerify;
	}
	
	@Override
	public CoeUserVerify findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserVerify coeUserVerify = coeUserVerifyDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserVerify);
		return coeUserVerify;
	}
	
	/**
	 * 根据IdList查询CoeUserVerify实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserVerify> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeUserVerify> coeUserVerifyList = coeUserVerifyDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeUserVerifyList);
		return coeUserVerifyList;
	}
	
	@Override
	public CoeUserVerify persistEntity(CoeUserVerify coeUserVerify) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeUserVerify entity = coeUserVerifyDelegate.persistEntity(coeUserVerify);
		Long id = coeUserVerify.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserVerify mergeEntity(CoeUserVerify coeUserVerify) {
		Long id = coeUserVerify.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeUserVerify entity = coeUserVerifyDelegate.mergeEntity(coeUserVerify);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserVerify saveOrUpdate(CoeUserVerify coeUserVerify) {
		Long id = coeUserVerify.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserVerify entity = coeUserVerifyDelegate.saveOrUpdate(coeUserVerify);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserVerify> findPage(CoeUserVerify coeUserVerify, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeUserVerify> page = coeUserVerifyDelegate.findPage(coeUserVerify, pageable);
		return page;
	}
	
	
	@Override
	public boolean existById(Long id) {
		return coeUserVerifyDelegate.existById(id);
	}
	
	@Override
	public CoeUserVerify verifyIdentity(CoeUserVerify coeUser) {
		return coeUserVerifyDelegate.verifyIdentity(coeUser);
	}
	@Override
	public CoeUserVerify applyIdentity(CoeUserVerify coeUser)  {
		return coeUserVerifyDelegate.applyIdentity(coeUser);
	}
	
	public CoeUserVerify findByIdentity(CoeUserVerify coeUser) {
		return coeUserVerifyDelegate.findByIdentity(coeUser);
	}
	
	public CoeUserVerify findNotSelfByIdentity(CoeUserVerify coeUser) {
		return coeUserVerifyDelegate.findNotSelfByIdentity(coeUser);
	}
	
	public CoeUserVerify findByUserId(Long userId) {
		return coeUserVerifyDelegate.findByUserId(userId);
	}
	
	public CoeUserVerify mergeForRealname(CoeUserVerify coeUser) {
		return coeUserVerifyDelegate.mergeForRealname(coeUser);
	}
	
	public Page<CoeUserVerify> findPageForVerify(CoeUserVerify coeUserVerify, Pageable pageable){
		return coeUserVerifyDelegate.findPageForVerify(coeUserVerify, pageable);
	}
}
