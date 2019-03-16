package dml.sz0099.course.app.client.wrapper.category;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.category.CategoryExtendDelegate;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CategoryExtendWrapperImpl,组件封装
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
public class CategoryExtendWrapperImpl implements CategoryExtendWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CategoryExtendDelegate categoryExtendDelegate;
	
	/**
	 * 根据Id查询CategoryExtend实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CategoryExtend findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CategoryExtend categoryExtend = categoryExtendDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, categoryExtend);
		return categoryExtend;
	}
	
	@Override
	public CategoryExtend findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CategoryExtend categoryExtend = categoryExtendDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryExtend);
		return categoryExtend;
	}
	
	/**
	 * 根据IdList查询CategoryExtend实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CategoryExtend> categoryExtendList = categoryExtendDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  categoryExtendList);
		return categoryExtendList;
	}
	
	@Override
	public CategoryExtend persistEntity(CategoryExtend categoryExtend) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CategoryExtend entity = categoryExtendDelegate.persistEntity(categoryExtend);
		Long id = categoryExtend.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategoryExtend mergeEntity(CategoryExtend categoryExtend) {
		Long id = categoryExtend.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		initMaxnum(categoryExtend);
		
		CategoryExtend entity = categoryExtendDelegate.mergeEntity(categoryExtend);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	/**
	 * @param categoryExtend
	 */
	private void initMaxnum(CategoryExtend categoryExtend) {
		Integer mainMaxnum = categoryExtend.getMainMaxnum();
		if(null == mainMaxnum) {
			categoryExtend.setMainMaxnum(CategoryExtend.MAINMAXNUM_DEFAULT);
		}
		
		Integer subMaxnum = categoryExtend.getSubMaxnum();
		if(null == subMaxnum) {
			categoryExtend.setSubMaxnum(CategoryExtend.SUBMAXNUM_DEFAULT);
		}
		
		Integer depthMaxnum = categoryExtend.getDepthMaxnum();
		if(null == depthMaxnum) {
			categoryExtend.setDepthMaxnum(CategoryExtend.DEPTHMAXNUM_DEFAULT);
		}
		
		Integer refMaxnum = categoryExtend.getRefMaxnum();
		if(null == refMaxnum) {
			categoryExtend.setRefMaxnum(CategoryExtend.REFMAXNUM_DEFAULT);
		}
	}

	@Override
	public CategoryExtend saveOrUpdate(CategoryExtend categoryExtend) {
		Long id = categoryExtend.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CategoryExtend entity = categoryExtendDelegate.saveOrUpdate(categoryExtend);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategoryExtend> findPage(CategoryExtend categoryExtend, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CategoryExtend> page = categoryExtendDelegate.findPage(categoryExtend, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return categoryExtendDelegate.existById(id);
	}

	@Override
	public List<CategoryExtend> findAll() {
		return categoryExtendDelegate.findAll();
	}

	@Override
	public CategoryExtend findByPositionId(Long positionId) {
		return categoryExtendDelegate.findByPositionId(positionId);
	}

	@Override
	public CategoryExtend findCategoryExtend(CategoryExtend extend) {
		return categoryExtendDelegate.findCategoryExtend(extend);
	}

	@Override
	public Long countByUserId(Long userId) {
		return categoryExtendDelegate.countByUserId(userId);
	}

	@Override
	public CategoryExtend create(CategoryExtend categoryExtend) {
		initMaxnum(categoryExtend);
		return persistEntity(categoryExtend);
	}

	@Override
	public CategoryExtend deleteEntity(CategoryExtend extend) {
		
		return categoryExtendDelegate.deleteEntity(extend);
	}
}
