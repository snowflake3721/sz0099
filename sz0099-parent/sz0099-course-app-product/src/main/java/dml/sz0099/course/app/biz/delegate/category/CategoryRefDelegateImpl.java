package dml.sz0099.course.app.biz.delegate.category;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.category.CategoryRefService;
import dml.sz0099.course.app.persist.entity.category.CategoryRef;

/**
 * categoryRefServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CategoryRefDelegateImpl implements CategoryRefDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRefDelegateImpl.class);
	
	@Autowired
	private CategoryRefService categoryRefService;

	/**
	 * 根据Id查询CategoryRef实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CategoryRef findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CategoryRef categoryRef = categoryRefService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, categoryRef);
		return categoryRef;
	}
	
	@Override
	public CategoryRef findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CategoryRef categoryRef = categoryRefService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryRef);
		return categoryRef;
	}
	
	/**
	 * 根据IdList查询CategoryRef实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CategoryRef> categoryRefList = categoryRefService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  categoryRefList);
		return categoryRefList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CategoryRef persistEntity(CategoryRef categoryRef) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CategoryRef entity = categoryRefService.persistEntity(categoryRef);
		Long id = categoryRef.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryRef mergeEntity(CategoryRef categoryRef) {
		Long id = categoryRef.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CategoryRef entity = categoryRefService.mergeEntity(categoryRef);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryRef saveOrUpdate(CategoryRef categoryRef) {
		Long id = categoryRef.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CategoryRef entity = categoryRefService.saveOrUpdate(categoryRef);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategoryRef> findPage(CategoryRef categoryRef, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CategoryRef> page = categoryRefService.findPage(categoryRef, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return categoryRefService.existById(id);
	}
	
	public Long countForBase(CategoryRef categoryRef) {
		Long baseId = categoryRef.getBaseId();
		return categoryRefService.countForBase(categoryRef);
	}
	
	public CategoryRef changeSingle(CategoryRef categoryRef) {
		categoryRef=categoryRefService.changeSingle(categoryRef);
		return categoryRef;
	}
}
