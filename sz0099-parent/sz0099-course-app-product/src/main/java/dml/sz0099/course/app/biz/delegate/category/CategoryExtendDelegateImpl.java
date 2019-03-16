package dml.sz0099.course.app.biz.delegate.category;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.category.CategoryExtendService;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;

/**
 * categoryExtendServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CategoryExtendDelegateImpl implements CategoryExtendDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendDelegateImpl.class);
	
	@Autowired
	private CategoryExtendService categoryExtendService;

	/**
	 * 根据Id查询CategoryExtend实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CategoryExtend findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CategoryExtend categoryExtend = categoryExtendService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, categoryExtend);
		return categoryExtend;
	}
	
	@Override
	public CategoryExtend findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CategoryExtend categoryExtend = categoryExtendService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryExtend);
		return categoryExtend;
	}
	
	/**
	 * 根据IdList查询CategoryExtend实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CategoryExtend> categoryExtendList = categoryExtendService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  categoryExtendList);
		return categoryExtendList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CategoryExtend persistEntity(CategoryExtend categoryExtend) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CategoryExtend entity = categoryExtendService.persistEntity(categoryExtend);
		Long id = categoryExtend.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryExtend mergeEntity(CategoryExtend categoryExtend) {
		Long id = categoryExtend.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CategoryExtend entity = categoryExtendService.mergeEntity(categoryExtend);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryExtend saveOrUpdate(CategoryExtend categoryExtend) {
		Long id = categoryExtend.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CategoryExtend entity = categoryExtendService.saveOrUpdate(categoryExtend);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategoryExtend> findPage(CategoryExtend categoryExtend, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CategoryExtend> page = categoryExtendService.findPage(categoryExtend, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return categoryExtendService.existById(id);
	}

	@Override
	public List<CategoryExtend> findAll() {
		return categoryExtendService.findAll();
	}
	
	@Override
	public CategoryExtend findByPositionId(Long positionId) {
		return categoryExtendService.findByPositionId(positionId);
	}

	@Override
	public CategoryExtend findCategoryExtend(CategoryExtend extend) {
		return categoryExtendService.findCategoryExtend(extend);
	}
	
	public Long findPositionIdById(Long id) {
		return categoryExtendService.findPositionIdById(id);
	}
	
	public Long countByUserId(Long userId) {
		return categoryExtendService.countByUserId(userId);
	}
	
	public CategoryExtend deleteEntity(CategoryExtend extend) {
		
		return categoryExtendService.deleteEntity(extend);
	}
}
