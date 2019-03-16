package dml.sz0099.course.app.biz.service.category;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.category.CategoryRefDao;
import dml.sz0099.course.app.persist.entity.category.CategoryRef;


/**
 * 
 * @formatter:off
 * description: CategoryRefServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CategoryRefServiceImpl extends GenericServiceImpl<CategoryRef, Long> implements CategoryRefService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRefServiceImpl.class);

	@Autowired
	private CategoryRefDao categoryRefDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = categoryRefDao;
	}

	/**
	 * 根据Id查询CategoryRef实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CategoryRef findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CategoryRef categoryRef = categoryRefDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, categoryRef);
		return categoryRef;
	}
	
	@Override
	public CategoryRef findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CategoryRef categoryRef = categoryRefDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryRef);
		return categoryRef;
	}

	/**
	 * 根据IdList查询CategoryRef实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CategoryRef> categoryRefList = categoryRefDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", categoryRefList);
		return categoryRefList;
	}

	@Transactional
	@Override
	public CategoryRef persistEntity(CategoryRef categoryRef) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CategoryRef entity = save(categoryRef);
		Long id = categoryRef.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CategoryRef.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CategoryRef mergeEntity(CategoryRef categoryRef) {
		Long id = categoryRef.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CategoryRef entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(categoryRef.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CategoryRef.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CategoryRef saveOrUpdate(CategoryRef categoryRef) {
		Long id = categoryRef.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CategoryRef entity = null;
		if(null != id) {
			entity = mergeEntity(categoryRef);
		}else {
			entity = persistEntity(categoryRef);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategoryRef> findPage(CategoryRef categoryRef, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CategoryRef> page = categoryRefDao.findPage(categoryRef, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return categoryRefDao.existById(id);
	}

	@Transactional
	@Override
	public void deleteByBaseId(Long baseId) {
		categoryRefDao.deleteByBaseId(baseId);
	}
	
	public Long countForBase(CategoryRef categoryRef) {
		Long baseId = categoryRef.getBaseId();
		return categoryRefDao.countForBase(categoryRef);
	}
	
	public List<CategoryRef> findByMainId(Long mainId) {
		return categoryRefDao.findByMainId(mainId);
	}
	
	@Transactional
	@Override
	public CategoryRef changeSingle(CategoryRef categoryRef) {
		Long mainId = categoryRef.getMainId();
		Long baseId = categoryRef.getBaseId();
		List<CategoryRef> content = findByMainId(mainId);
		boolean exist=false;
		if(null != content && !content.isEmpty()) {
			int i = 0;
			int size = content.size();
			
			for(CategoryRef c : content) {
				Long baseIdEntity = c.getBaseId();
				if(baseId.equals(baseIdEntity)) {
					exist = true;
					continue;
				}
				
				if(i==size-1) {
					c.setBaseId(baseId);
					c.setUserId(categoryRef.getUserId());
					c.setLastModifiedBy(categoryRef.getLastModifiedBy());
					DateTime lastModifiedDate = new DateTime();
					c.setLastModifiedDate(lastModifiedDate);
					c.setExtendId(categoryRef.getExtendId());
					c.setPositionId(categoryRef.getPositionId());
					//c.setSubId(categoryRef.getSubId());
					categoryRef=save(c);
					exist = true;
					categoryRef.setSuccess(CategoryRef.SUCCESS_YES);
					break;
				}
				if(!exist) {
					delete(c);
				}
				i++;
			}
		}
		
		if(!exist) {
			categoryRef=persistEntity(categoryRef);
			categoryRef.setSuccess(CategoryRef.SUCCESS_YES);
		}
		
		return categoryRef;
	}

}
