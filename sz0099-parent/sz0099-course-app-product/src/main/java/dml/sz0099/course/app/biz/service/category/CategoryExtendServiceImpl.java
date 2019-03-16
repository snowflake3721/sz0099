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

import dml.sz0099.course.app.persist.dao.category.CategoryExtendDao;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;


/**
 * 
 * @formatter:off
 * description: CategoryExtendServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CategoryExtendServiceImpl extends GenericServiceImpl<CategoryExtend, Long> implements CategoryExtendService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtendServiceImpl.class);

	@Autowired
	private CategoryExtendDao categoryExtendDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = categoryExtendDao;
	}

	/**
	 * 根据Id查询CategoryExtend实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CategoryExtend findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CategoryExtend categoryExtend = categoryExtendDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, categoryExtend);
		return categoryExtend;
	}
	
	@Override
	public CategoryExtend findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CategoryExtend categoryExtend = categoryExtendDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, categoryExtend);
		return categoryExtend;
	}

	/**
	 * 根据IdList查询CategoryExtend实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategoryExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CategoryExtend> categoryExtendList = categoryExtendDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", categoryExtendList);
		return categoryExtendList;
	}

	@Transactional
	@Override
	public CategoryExtend persistEntity(CategoryExtend categoryExtend) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CategoryExtend entity = save(categoryExtend);
		Long id = categoryExtend.getId();
		entity.setPositionId(id);
		save(entity);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CategoryExtend.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CategoryExtend mergeEntity(CategoryExtend categoryExtend) {
		Long id = categoryExtend.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CategoryExtend entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(categoryExtend.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			
			entity.setDevId(categoryExtend.getDevId());
			entity.setDepthMaxnum(categoryExtend.getDepthMaxnum());
			entity.setDomain(categoryExtend.getDomain());
			entity.setMainMaxnum(categoryExtend.getMainMaxnum());
			entity.setModule(categoryExtend.getModule());
			entity.setPosition(categoryExtend.getPosition());
			entity.setProject(categoryExtend.getProject());
			entity.setRefMaxnum(categoryExtend.getRefMaxnum());
			entity.setSubMaxnum(categoryExtend.getSubMaxnum());
			entity.setUserId(categoryExtend.getUserId());
			entity.setVariety(categoryExtend.getVariety());
			
			Long positionId = entity.getPositionId();
			if(null == positionId) {
				entity.setPositionId(id);
			}
			save(entity);
			entity.setSuccess(CategoryExtend.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CategoryExtend saveOrUpdate(CategoryExtend categoryExtend) {
		Long id = categoryExtend.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CategoryExtend entity = null;
		if(null != id) {
			entity = mergeEntity(categoryExtend);
		}else {
			entity = persistEntity(categoryExtend);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategoryExtend> findPage(CategoryExtend categoryExtend, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CategoryExtend> page = categoryExtendDao.findPage(categoryExtend, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return categoryExtendDao.existById(id);
	}
	
	@Override
	public CategoryExtend findByPositionId(Long positionId) {
		return categoryExtendDao.findByPositionId(positionId);
	}

	@Override
	public CategoryExtend findCategoryExtend(CategoryExtend extend) {
		return categoryExtendDao.findCategoryExtend(extend);
	}
	
	@Override
	public Long findPositionIdById(Long id) {
		return categoryExtendDao.findPositionIdById(id);
	}
	
	public Long countByUserId(Long userId) {
		return categoryExtendDao.countByUserId(userId);
	}
	
	@Transactional
	public CategoryExtend deleteEntity(CategoryExtend extend) {
		Long id = extend.getId();
		CategoryExtend entity = findById(id);
		if(null != entity) {
			Long userE = entity.getUserId();
			Long userU = extend.getUserId();
			if(userE.equals(userU)) {
				delete(entity);
				extend.setSuccess(CategoryExtend.SUCCESS_YES);
			}
		}
		return extend;
	}
	

}
