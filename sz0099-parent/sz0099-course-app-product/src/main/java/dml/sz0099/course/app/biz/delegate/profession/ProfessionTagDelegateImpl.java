package dml.sz0099.course.app.biz.delegate.profession;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.profession.ProfessionTagService;
import dml.sz0099.course.app.persist.entity.profession.ProfessionTag;

/**
 * professionTagServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ProfessionTagDelegateImpl implements ProfessionTagDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionTagDelegateImpl.class);
	
	@Autowired
	private ProfessionTagService professionTagService;

	/**
	 * 根据Id查询ProfessionTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionTag findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ProfessionTag professionTag = professionTagService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, professionTag);
		return professionTag;
	}
	
	@Override
	public ProfessionTag findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionTag professionTag = professionTagService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, professionTag);
		return professionTag;
	}
	
	/**
	 * 根据IdList查询ProfessionTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ProfessionTag> professionTagList = professionTagService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  professionTagList);
		return professionTagList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ProfessionTag persistEntity(ProfessionTag professionTag) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ProfessionTag entity = professionTagService.persistEntity(professionTag);
		Long id = professionTag.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionTag mergeEntity(ProfessionTag professionTag) {
		Long id = professionTag.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ProfessionTag entity = professionTagService.mergeEntity(professionTag);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionTag saveOrUpdate(ProfessionTag professionTag) {
		Long id = professionTag.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionTag entity = professionTagService.saveOrUpdate(professionTag);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionTag> findPage(ProfessionTag professionTag, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ProfessionTag> page = professionTagService.findPage(professionTag, pageable);
		return page;
	}

	@Override
	public ProfessionTag findByMainIdAndName(ProfessionTag professionTag) {
		return professionTagService.findByMainIdAndName(professionTag);
	}
	
	public ProfessionTag addTag(ProfessionTag professionTag) {
		return professionTagService.addTag(professionTag);
	}
	
	public ProfessionTag deleteTag(ProfessionTag professionTag) {
		return professionTagService.deleteTag(professionTag);
	}
	
	public Long countByMainId(Long professionId) {
		return professionTagService.countByMainId(professionId);
	}

	@Override
	public List<ProfessionTag> findByMainId(Long professionId) {
		return professionTagService.findByMainId(professionId);
	}
	
	public Map<Long, List<ProfessionTag>> findMapByMainIdList(List<Long> mainIdList) {
		return professionTagService.findMapByMainIdList(mainIdList);
	}
}
