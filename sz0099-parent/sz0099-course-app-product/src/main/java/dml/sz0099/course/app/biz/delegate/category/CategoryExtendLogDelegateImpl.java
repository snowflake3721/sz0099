package dml.sz0099.course.app.biz.delegate.category;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.category.CategoryExtendLogService;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.CategoryExtendLog;

/**
 * categoryExtendLogServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CategoryExtendLogDelegateImpl implements CategoryExtendLogDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendLogDelegateImpl.class);
	
	@Autowired
	private CategoryExtendLogService categoryExtendLogService;

	/**
	 * 根据Id查询CategoryExtendLog实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CategoryExtendLog findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CategoryExtendLog categoryExtendLog = categoryExtendLogService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, categoryExtendLog);
		return categoryExtendLog;
	}
	
	@Override
	public CategoryExtendLog findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CategoryExtendLog categoryExtendLog = categoryExtendLogService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryExtendLog);
		return categoryExtendLog;
	}
	
	/**
	 * 根据IdList查询CategoryExtendLog实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CategoryExtendLog> categoryExtendLogList = categoryExtendLogService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  categoryExtendLogList);
		return categoryExtendLogList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CategoryExtendLog persistEntity(CategoryExtendLog categoryExtendLog) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CategoryExtendLog entity = categoryExtendLogService.persistEntity(categoryExtendLog);
		Long id = categoryExtendLog.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryExtendLog mergeEntity(CategoryExtendLog categoryExtendLog) {
		Long id = categoryExtendLog.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CategoryExtendLog entity = categoryExtendLogService.mergeEntity(categoryExtendLog);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryExtendLog saveOrUpdate(CategoryExtendLog categoryExtendLog) {
		Long id = categoryExtendLog.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CategoryExtendLog entity = categoryExtendLogService.saveOrUpdate(categoryExtendLog);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategoryExtendLog> findPage(CategoryExtendLog categoryExtendLog, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CategoryExtendLog> page = categoryExtendLogService.findPage(categoryExtendLog, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return categoryExtendLogService.existById(id);
	}

	@Override
	public CategoryExtendLog persistForFail(CategoryExtend categoryExtend) {
		return categoryExtendLogService.persistForFail(categoryExtend);
	}
}
