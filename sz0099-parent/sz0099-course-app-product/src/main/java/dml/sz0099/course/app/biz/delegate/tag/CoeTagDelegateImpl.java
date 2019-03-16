package dml.sz0099.course.app.biz.delegate.tag;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.tag.CoeTagService;
import dml.sz0099.course.app.persist.entity.tag.CoeTag;

/**
 * coeTagServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeTagDelegateImpl implements CoeTagDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeTagDelegateImpl.class);
	
	@Autowired
	private CoeTagService coeTagService;

	/**
	 * 根据Id查询CoeTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeTag findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeTag coeTag = coeTagService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeTag);
		return coeTag;
	}
	
	@Override
	public CoeTag findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeTag coeTag = coeTagService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeTag);
		return coeTag;
	}
	
	/**
	 * 根据IdList查询CoeTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeTag> coeTagList = coeTagService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeTagList);
		return coeTagList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeTag persistEntity(CoeTag coeTag) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeTag entity = coeTagService.persistEntity(coeTag);
		Long id = coeTag.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeTag mergeEntity(CoeTag coeTag) {
		Long id = coeTag.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeTag entity = coeTagService.mergeEntity(coeTag);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeTag saveOrUpdate(CoeTag coeTag) {
		Long id = coeTag.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeTag entity = coeTagService.saveOrUpdate(coeTag);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeTag> findPage(CoeTag coeTag, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeTag> page = coeTagService.findPage(coeTag, pageable);
		return page;
	}
}
