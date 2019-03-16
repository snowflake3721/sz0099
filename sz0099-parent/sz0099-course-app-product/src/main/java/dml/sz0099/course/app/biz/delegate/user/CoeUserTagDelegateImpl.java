package dml.sz0099.course.app.biz.delegate.user;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.user.CoeUserTagService;
import dml.sz0099.course.app.persist.entity.user.CoeUserTag;

/**
 * coeUserTagServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeUserTagDelegateImpl implements CoeUserTagDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserTagDelegateImpl.class);
	
	@Autowired
	private CoeUserTagService coeUserTagService;

	/**
	 * 根据Id查询CoeUserTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserTag findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeUserTag coeUserTag = coeUserTagService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeUserTag);
		return coeUserTag;
	}
	
	@Override
	public CoeUserTag findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserTag coeUserTag = coeUserTagService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserTag);
		return coeUserTag;
	}
	
	/**
	 * 根据IdList查询CoeUserTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeUserTag> coeUserTagList = coeUserTagService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeUserTagList);
		return coeUserTagList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeUserTag persistEntity(CoeUserTag coeUserTag) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeUserTag entity = coeUserTagService.persistEntity(coeUserTag);
		Long id = coeUserTag.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserTag mergeEntity(CoeUserTag coeUserTag) {
		Long id = coeUserTag.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeUserTag entity = coeUserTagService.mergeEntity(coeUserTag);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserTag saveOrUpdate(CoeUserTag coeUserTag) {
		Long id = coeUserTag.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserTag entity = coeUserTagService.saveOrUpdate(coeUserTag);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserTag> findPage(CoeUserTag coeUserTag, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeUserTag> page = coeUserTagService.findPage(coeUserTag, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeUserTagService.existById(id);
	}
}
