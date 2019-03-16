package dml.sz0099.course.app.client.wrapper.category;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.category.CategoryRefDelegate;
import dml.sz0099.course.app.persist.entity.category.CategoryRef;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CategoryRefWrapperImpl,组件封装
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
public class CategoryRefWrapperImpl implements CategoryRefWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRefWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CategoryRefDelegate categoryRefDelegate;
	
	/**
	 * 根据Id查询CategoryRef实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CategoryRef findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CategoryRef categoryRef = categoryRefDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, categoryRef);
		return categoryRef;
	}
	
	@Override
	public CategoryRef findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CategoryRef categoryRef = categoryRefDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryRef);
		return categoryRef;
	}
	
	/**
	 * 根据IdList查询CategoryRef实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CategoryRef> categoryRefList = categoryRefDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  categoryRefList);
		return categoryRefList;
	}
	
	@Override
	public CategoryRef persistEntity(CategoryRef categoryRef) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CategoryRef entity = categoryRefDelegate.persistEntity(categoryRef);
		Long id = categoryRef.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryRef mergeEntity(CategoryRef categoryRef) {
		Long id = categoryRef.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CategoryRef entity = categoryRefDelegate.mergeEntity(categoryRef);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryRef saveOrUpdate(CategoryRef categoryRef) {
		Long id = categoryRef.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CategoryRef entity = categoryRefDelegate.saveOrUpdate(categoryRef);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategoryRef> findPage(CategoryRef categoryRef, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CategoryRef> page = categoryRefDelegate.findPage(categoryRef, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return categoryRefDelegate.existById(id);
	}

	@Override
	public Long countForMain(CategoryRef categoryRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countForSub(CategoryRef categoryRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countForBase(CategoryRef categoryRef) {
		Long baseId = categoryRef.getBaseId();
		return categoryRefDelegate.countForBase(categoryRef);
	}

	@Override
	public Long findPositionId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryRef changeSingle(CategoryRef categoryRef) {
		Long mainId = categoryRef.getMainId();
		Long baseId = categoryRef.getBaseId();
		categoryRef=categoryRefDelegate.changeSingle(categoryRef);
		return categoryRef;
	}

	@Override
	public void deleteById(CategoryRef categoryRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryRef deleteByMainIdAndSubId(CategoryRef categoryRef) {
		// TODO Auto-generated method stub
		return null;
	}
}
