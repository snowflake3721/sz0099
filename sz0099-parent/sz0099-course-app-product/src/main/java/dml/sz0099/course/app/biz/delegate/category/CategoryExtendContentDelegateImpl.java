package dml.sz0099.course.app.biz.delegate.category;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.category.CategoryExtendContentService;
import dml.sz0099.course.app.persist.entity.category.CategoryExtendContent;

/**
 * categoryExtendContentServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CategoryExtendContentDelegateImpl implements CategoryExtendContentDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendContentDelegateImpl.class);
	
	@Autowired
	private CategoryExtendContentService categoryExtendContentService;

	/**
	 * 根据Id查询CategoryExtendContent实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CategoryExtendContent findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CategoryExtendContent categoryExtendContent = categoryExtendContentService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, categoryExtendContent);
		return categoryExtendContent;
	}
	
	@Override
	public CategoryExtendContent findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CategoryExtendContent categoryExtendContent = categoryExtendContentService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryExtendContent);
		return categoryExtendContent;
	}
	
	/**
	 * 根据IdList查询CategoryExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CategoryExtendContent> categoryExtendContentList = categoryExtendContentService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  categoryExtendContentList);
		return categoryExtendContentList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CategoryExtendContent persistEntity(CategoryExtendContent categoryExtendContent) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CategoryExtendContent entity = categoryExtendContentService.persistEntity(categoryExtendContent);
		Long id = categoryExtendContent.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryExtendContent mergeEntity(CategoryExtendContent categoryExtendContent) {
		Long id = categoryExtendContent.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CategoryExtendContent entity = categoryExtendContentService.mergeEntity(categoryExtendContent);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryExtendContent saveOrUpdate(CategoryExtendContent categoryExtendContent) {
		Long id = categoryExtendContent.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CategoryExtendContent entity = categoryExtendContentService.saveOrUpdate(categoryExtendContent);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategoryExtendContent> findPage(CategoryExtendContent categoryExtendContent, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CategoryExtendContent> page = categoryExtendContentService.findPage(categoryExtendContent, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return categoryExtendContentService.existById(id);
	}
}
