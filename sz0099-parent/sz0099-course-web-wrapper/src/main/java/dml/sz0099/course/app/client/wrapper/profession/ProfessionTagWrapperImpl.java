package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.profession.ProfessionTagDelegate;
import dml.sz0099.course.app.persist.entity.profession.ProfessionTag;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionTagWrapperImpl,组件封装
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
public class ProfessionTagWrapperImpl implements ProfessionTagWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionTagWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ProfessionTagDelegate professionTagDelegate;
	
	/**
	 * 根据Id查询ProfessionTag实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionTag findById(Long id) {
		LOGGER.debug("--- ProfessionTagWrapperImpl.findById begin --------- id is:{} ", id);
		ProfessionTag professionTag = professionTagDelegate.findById(id);
		LOGGER.debug("--- ProfessionTagWrapperImpl.findById end --------- id is:{} , result is {} ", id, professionTag);
		return professionTag;
	}
	
	@Override
	public ProfessionTag findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionTag professionTag = professionTagDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, professionTag);
		return professionTag;
	}
	
	/**
	 * 根据IdList查询ProfessionTag实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionTag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- ProfessionTagWrapperImpl.findByIdList begin ---------  ");
		List<ProfessionTag> professionTagList = professionTagDelegate.findByIdList(idList);
		LOGGER.debug("--- ProfessionTagWrapperImpl.findByIdList end ---------  result is {} ",  professionTagList);
		return professionTagList;
	}
	
	@Override
	public ProfessionTag persistEntity(ProfessionTag professionTag) {
		LOGGER.debug("--- ProfessionTagWrapperImpl.persistEntity begin ---------  ");
		ProfessionTag entity = professionTagDelegate.persistEntity(professionTag);
		Long id = professionTag.getId();
		LOGGER.debug("--- ProfessionTagWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionTag mergeEntity(ProfessionTag professionTag) {
		Long id = professionTag.getId();
		LOGGER.debug("--- ProfessionTagWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		ProfessionTag entity = professionTagDelegate.mergeEntity(professionTag);
		LOGGER.debug("--- ProfessionTagWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionTag saveOrUpdate(ProfessionTag professionTag) {
		Long id = professionTag.getId();
		LOGGER.debug("--- ProfessionTagWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionTag entity = professionTagDelegate.saveOrUpdate(professionTag);
		LOGGER.debug("--- ProfessionTagWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionTag> findPage(ProfessionTag professionTag, Pageable pageable) {
		LOGGER.debug("--- ProfessionTagWrapperImpl.findPage ---------  ");
		Page<ProfessionTag> page = professionTagDelegate.findPage(professionTag, pageable);
		return page;
	}

	@Override
	public ProfessionTag findByMainIdAndName(ProfessionTag professionTag) {
		
		return professionTagDelegate.findByMainIdAndName(professionTag);
	}
	
	public ProfessionTag addTag(ProfessionTag professionTag) {
		return professionTagDelegate.addTag(professionTag);
	}

	@Override
	public ProfessionTag deleteTag(ProfessionTag professionTag) {
		return professionTagDelegate.deleteTag(professionTag);
	}

	@Override
	public Long countByMainId(Long professionId) {
		return professionTagDelegate.countByMainId(professionId);
	}
	
	public List<ProfessionTag> findByMainId(Long professionId) {
		return professionTagDelegate.findByMainId(professionId);
	}
	
	@Override
	public Map<Long, List<ProfessionTag>> findMapByMainIdList(List<Long> professionIdList) {
		return professionTagDelegate.findMapByMainIdList(professionIdList);
	}
}
