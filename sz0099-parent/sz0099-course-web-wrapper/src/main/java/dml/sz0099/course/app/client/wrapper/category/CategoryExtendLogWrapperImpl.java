package dml.sz0099.course.app.client.wrapper.category;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.category.CategoryExtendLogDelegate;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.CategoryExtendLog;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CategoryExtendLogWrapperImpl,组件封装
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
public class CategoryExtendLogWrapperImpl implements CategoryExtendLogWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendLogWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CategoryExtendLogDelegate categoryExtendLogDelegate;
	
	/**
	 * 根据Id查询CategoryExtendLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CategoryExtendLog findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CategoryExtendLog categoryExtendLog = categoryExtendLogDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, categoryExtendLog);
		return categoryExtendLog;
	}
	
	@Override
	public CategoryExtendLog findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CategoryExtendLog categoryExtendLog = categoryExtendLogDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryExtendLog);
		return categoryExtendLog;
	}
	
	/**
	 * 根据IdList查询CategoryExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CategoryExtendLog> categoryExtendLogList = categoryExtendLogDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  categoryExtendLogList);
		return categoryExtendLogList;
	}
	
	@Override
	public CategoryExtendLog persistEntity(CategoryExtendLog categoryExtendLog) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CategoryExtendLog entity = categoryExtendLogDelegate.persistEntity(categoryExtendLog);
		Long id = categoryExtendLog.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryExtendLog mergeEntity(CategoryExtendLog categoryExtendLog) {
		Long id = categoryExtendLog.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CategoryExtendLog entity = categoryExtendLogDelegate.mergeEntity(categoryExtendLog);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryExtendLog saveOrUpdate(CategoryExtendLog categoryExtendLog) {
		Long id = categoryExtendLog.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CategoryExtendLog entity = categoryExtendLogDelegate.saveOrUpdate(categoryExtendLog);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategoryExtendLog> findPage(CategoryExtendLog categoryExtendLog, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CategoryExtendLog> page = categoryExtendLogDelegate.findPage(categoryExtendLog, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return categoryExtendLogDelegate.existById(id);
	}

	@Override
	public CategoryExtendLog persistForFail(CategoryExtend categoryExtend) {
		return categoryExtendLogDelegate.persistForFail(categoryExtend);
	}
}
