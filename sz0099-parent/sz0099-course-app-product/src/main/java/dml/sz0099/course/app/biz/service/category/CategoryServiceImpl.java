package dml.sz0099.course.app.biz.service.category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import dml.sz0099.course.app.persist.dao.category.CategoryDao;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.CategoryExtend;


/**
 * 
 * @formatter:off
 * description: CoeCategoryServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, Long> implements CategoryService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private CategoryRefService categoryRefService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = categoryDao;
	}

	/**
	 * 根据Id查询CoeCategory实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Category findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		Category category = categoryDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, category);
		return category;
	}
	
	@Override
	public Category findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		Category category = categoryDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, category);
		return category;
	}

	/**
	 * 根据IdList查询CoeCategory实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Category> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<Category> categoryList = categoryDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", categoryList);
		return categoryList;
	}

	@Transactional
	@Override
	public Category persistEntity(Category category) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		Category entity = save(category);
		Long id = category.getId();
		entity.setOrderSeq(entity.getAid());
		Long positionId = category.getPositionId();
		if(null == positionId) {
			Long parentId = category.getParentId();
			if(null != parentId ) {
				if(CategoryExtend.TOP_PARENTID.equals(parentId)) {
					category.setPositionId(category.getExtendId());
				}else {
					Category parent = findById(parentId);
					category.setPositionId(parent.getPositionId());
				}
			}
		}
		save(entity);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(Category.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public Category mergeEntity(Category category) {
		Long id = category.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		Category entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(category.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			
			entity.setAd(entity.getAd());
			entity.setCode(category.getCode());
			entity.setMainId(category.getMainId());
			entity.setSubId(category.getSubId());
			entity.setName(category.getName());
			entity.setOrderSeq(category.getOrderSeq());
			entity.setParentId(category.getParentId());
			
			Long positionId = entity.getPositionId();
			if(null == positionId) {
				entity.setPositionId(entity.getExtendId());
			}
			
			save(entity);
			entity.setSuccess(Category.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public Category saveOrUpdate(Category category) {
		Long id = category.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		Category entity = null;
		if(null != id) {
			entity = mergeEntity(category);
		}else {
			entity = persistEntity(category);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Category> findPage(Category category, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<Category> page = categoryDao.findPage(category, pageable);
		return page;
	}
	
	public Category findByCode(String code){
		Category entity = categoryDao.findByCode(code);
		return entity;
	}
	
	public boolean existByCode(String code){
		Category entity = categoryDao.findByCode(code);
		return entity!=null;
	}
	
	
	public Category findTopParentByCode(String code, boolean withChildren) {
		Map<String, Category> top = findTopParent(withChildren);
		if (null != code) {
			return top.get(code);
		}
		return null;
	}
	
	public Map<String,Category> findTopParent( boolean withChildren){
		Long parentId = CategoryExtend.TOP_PARENTID;
		Map<String,Category> contentMap = findMapByParentId( parentId,  withChildren);
		return contentMap;
	}
	
	public List<Category> findTop(boolean withChildren){
		List<Category> content = null;
		Long parentId = CategoryExtend.TOP_PARENTID;
		if(withChildren) {
			Map<String,Category> contentMap = findMapByParentId( parentId,  withChildren);
			if(null != contentMap) {
				content = new ArrayList<>(contentMap.size());
				for(Map.Entry<String, Category> entry : contentMap.entrySet()) {
					content.add(entry.getValue());
				}
			}
		}else {
			content = findByParentId(parentId);
		}
		return content;
	}
	
	public List<Category> findByParentId(Long parentId){
		List<Category> content = categoryDao.findByParentId(parentId);
		return content;
	}
	
	public List<Long> findListByCodeWithChilren(String code){
		Category entity = findByCode(code);
		List<Long> totalIdList=null;
		if(null != entity) {
			totalIdList = findListByParentId(entity.getId()) ;
		}
		return totalIdList;
	}
	
	public List<Long> findListByParentId(Long parentId){
		List<Long> totalIdList = new ArrayList<>();
		List<Long> parentIdList = new ArrayList<>(1);
		parentIdList.add(parentId);
		return findListByParentId(parentIdList,totalIdList);
	}
	public List<Long> findListByParentId(List<Long> parentIdList, List<Long> totalIdList){
		if(null != parentIdList && !parentIdList.isEmpty()) {
			if(totalIdList==null) {
				totalIdList = new ArrayList<>();
			}
			totalIdList.addAll(parentIdList);
			List<Category> content = findByParentIdList(parentIdList);
			List<Long> idList = new ArrayList<>();
			for(Category cc : content) {
				idList.add(cc.getId());
			}
			
			if(!idList.isEmpty()) {
				 findListByParentId(idList,totalIdList);
			}
			
		}
		return totalIdList;
	}
	
	public Map<String,Category> findMapByParentId(Long parentId, boolean withChildren){
		List<Category> content = findByParentId(parentId);
		Map<String, Category> top = null;
		if(null != content && !content.isEmpty() && withChildren) {
			List<Long> pIdList = new ArrayList<>(content.size());
			top = new HashMap<>(content.size());
			for(Category c : content) {
				Long pId = c.getId();
				pIdList.add(pId);
				String code = c.getCode();
				top.put(code, c);
			}
			
			Map<Long, List<Category>> subMap = findMapByParentIdList(pIdList);
			
			for(Map.Entry<String, Category> entry : top.entrySet()) {
				Category value = entry.getValue();
				if(null != value) {
					Long pid = value.getId();
					value.setChildren(subMap.get(pid));
				}
			}
			
		}
		return top;
	}
	
	public List<Category> findByParentIdList(List<Long> parentIdList){
		List<Category> content = categoryDao.findByParentIdList(parentIdList);
		return content;
	}
	
	public Map<Long, List<Category>> findMapByParentIdList(List<Long> parentIdList){
		Map<Long, List<Category>> map = null;
		
		if(null != parentIdList && !parentIdList.isEmpty()) {
			map = new HashMap<>(parentIdList.size());
			List<Category> content = findByParentIdList(parentIdList);
			List<Long> idList = new ArrayList<>();
			for(Category cc : content) {
				Long pid = cc.getParentId();
				List<Category> subList = map.get(pid);
				idList.add(cc.getId());
				if(null == subList) {
					subList = new ArrayList<>();
					map.put(pid, subList);
				}
				subList.add(cc);
			}
			
			if(null != idList && !idList.isEmpty()) {
				Map<Long, List<Category>> subMap =  findMapByParentIdList(idList);
				if(null != subMap) {
					for(Map.Entry<Long, List<Category>> entry : map.entrySet()) {
						List<Category> valueList = entry.getValue();
						if(null != valueList) {
							for(Category value : valueList) {
								Long id = value.getId();
								value.setChildren(subMap.get(id));
							}
						}
					}
				}
			}
			
		}
		return map;
	}
	
	@Override
	public boolean existById(Long id) {
		return categoryDao.existById(id);
	}
	
	public List<Category> findMainAndSub(Category category) {
		List<Category> content = categoryDao.findMainAndSub(category);
		List<Category> sorted = getSorted(content);
		return sorted;
	}
	
	public List<Category> getSorted(List<Category> content, Long endParentId){
		List<Category> sorted = new ArrayList<>();
		if(null != content && !content.isEmpty()) {
			Map<Long, Category> map = new HashMap<>(content.size());
			for(Category c : content) {
				Long id = c.getId();
				map.put(id, c);
			}
			for(Category c : content) {
				Long parentId = c.getParentId();
				if(null == parentId || CategoryExtend.TOP_PARENTID.equals(parentId) || endParentId.equals(parentId)) {
					sorted.add(c);
				}else {
					Category parent = map.get(parentId);
					List<Category> children = parent.getChildren();
					if(null == children) {
						children = new ArrayList<>();
						parent.setChildren(children);
					}
					children.add(c);
					c.setParent(parent);
				}
			}
		}
		return sorted;
	}
	
	public List<Category> getSorted(List<Category> content){
		List<Category> sorted = getSorted(content, CategoryExtend.TOP_PARENTID);
		return sorted;
	}
	
	
	public Long countByParentId(Category category) {
		return categoryDao.countByParentId(category);
	}
	
	@Transactional
	public Category deleteCategory(Category category) {
		category= deleteCategory(category, false);
		return category;
	}
	
	@Transactional
	public Category deleteCategory(Category category, boolean isEntity) {
		Long id = category.getId();
		Category entity = category;
		if(!isEntity) {
			entity = findById(id);
		}
		if(null != entity) {
			Long userIdE = entity.getUserId();
			Long userIdU = category.getUserId();
			if(userIdE.equals(userIdU)) {
				categoryRefService.deleteByBaseId(id);
				delete(entity);
				category.setSuccess(Category.SUCCESS_YES);
			}
		}
		return category;
	}
	
	public Long countByExtendId(Long extendId) {
		return categoryDao.countByExtendId(extendId);
	}
	
	@Transactional
	public Category deleteAllByExtend(Category category) {
		Long extendId = category.getExtendId();
		Long userId = category.getUserId();
		Long mainId = category.getMainId();
		Long subId = category.getSubId();
		List<Category> entityList = findForMain(extendId,  userId,  mainId,  subId);
		if(null != entityList && !entityList.isEmpty()) {
			for(Category entity : entityList) {
				deleteCategory(entity,true);
			}
			category.setSuccess(Category.SUCCESS_YES);
		}
		return category;
	}
	
	public List<Category> findForMain(Long extendId, Long userId, Long mainId, Long subId){
		List<Category> entityList = categoryDao.findForMain(extendId,  userId,  mainId,  subId);
		return entityList;
	}
	
	/**
	 * 查找id下的所有孩子，含自身
	 */
	public Category findById(Long id, boolean cascade) {
		Category category = findById(id);
		if(null != category && cascade) {
			List<Long> pidList = new ArrayList<>(1);
			pidList.add(id);
			List<Category> children = new ArrayList<>();
			category.setChildren(children);
			Category parent = findByParentIdList(category, pidList,true);
			List<Category> result = getSorted(parent.getChildren(),id);
			category.setChildren(result);
		}
		return category;
	}
	
	public Category findByParentIdList(Category parent, List<Long> pidList, boolean cascade){
		List<Category> children = parent.getChildren();
		if( null != pidList && !pidList.isEmpty() && cascade) {
			List<Category> result = findByParentIdList(pidList);
			if(null != result && !result.isEmpty()) {
				if(null == children) {
					children = new ArrayList<>();
				}
				List<Long> pList = new ArrayList<>();
				for(Category r : result) {
					pList.add(r.getId());
					r.setParent(parent);
				}
				children.addAll(result);
				findByParentIdList(parent, pList,true);
			}
		}
		return parent;
	}
	
	public Category findTop(Long parentId, boolean cascade) {
		Long topId = findTopId(parentId);
		Category top = findById(topId, cascade);
		return top;
	}
	
	public Long findTopId(Long parentId) {
		if(null == parentId || CategoryExtend.TOP_PARENTID.equals(parentId)) {
			return parentId;
		}
		Category parent = findById(parentId);
		Long id=parentId;
		if(null != parent) {
			Long pid = parent.getParentId();
			if(null == pid || CategoryExtend.TOP_PARENTID.equals(pid)) {
				return parent.getId();
			}else {
				id = findTopId(pid);
			}
		}
		return id;
	}
	
	public Map<Long,Category> findMapByIdList(List<Long> idList) {
		List<Category> categoryList = findByIdList(idList);
		Map<Long,Category> map = new HashMap<>();
		if(null != categoryList && !categoryList.isEmpty()) {
			for(Category entity : categoryList) {
				Long key = entity.getId();
				map.put(key, entity);
			}
		}
		LOGGER.debug("--- wrapper.findMapByIdList end ---------  result is {} ",  categoryList);
		return map;
	}
	
	public List<Long> findListByBaseIdWithChilren(Long baseId){
		List<Long> totalIdList=null;
		if(null != baseId) {
			totalIdList = findListByParentId(baseId) ;
		}
		return totalIdList;
	}

}
