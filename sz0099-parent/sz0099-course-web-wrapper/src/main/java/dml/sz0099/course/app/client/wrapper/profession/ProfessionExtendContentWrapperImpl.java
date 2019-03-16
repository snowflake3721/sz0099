package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.profession.ProfessionExtendContentDelegate;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendContent;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionExtendContentWrapperImpl,组件封装
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
public class ProfessionExtendContentWrapperImpl implements ProfessionExtendContentWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionExtendContentWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ProfessionExtendContentDelegate professionExtendContentDelegate;
	
	/**
	 * 根据Id查询ProfessionExtendContent实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionExtendContent findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ProfessionExtendContent professionExtendContent = professionExtendContentDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, professionExtendContent);
		return professionExtendContent;
	}
	
	@Override
	public ProfessionExtendContent findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionExtendContent professionExtendContent = professionExtendContentDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, professionExtendContent);
		return professionExtendContent;
	}
	
	/**
	 * 根据IdList查询ProfessionExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ProfessionExtendContent> professionExtendContentList = professionExtendContentDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  professionExtendContentList);
		return professionExtendContentList;
	}
	
	@Override
	public ProfessionExtendContent persistEntity(ProfessionExtendContent professionExtendContent) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ProfessionExtendContent entity = professionExtendContentDelegate.persistEntity(professionExtendContent);
		Long id = professionExtendContent.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionExtendContent mergeEntity(ProfessionExtendContent professionExtendContent) {
		Long id = professionExtendContent.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ProfessionExtendContent entity = professionExtendContentDelegate.mergeEntity(professionExtendContent);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionExtendContent saveOrUpdate(ProfessionExtendContent professionExtendContent) {
		Long id = professionExtendContent.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionExtendContent entity = professionExtendContentDelegate.saveOrUpdate(professionExtendContent);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionExtendContent> findPage(ProfessionExtendContent professionExtendContent, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ProfessionExtendContent> page = professionExtendContentDelegate.findPage(professionExtendContent, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return professionExtendContentDelegate.existById(id);
	}
}
