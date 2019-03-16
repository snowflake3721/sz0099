package dml.sz0099.course.app.biz.service.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.jit8j.core.util.CollectionUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.product.CoeCategProdDao;
import dml.sz0099.course.app.persist.entity.product.CoeCategProd;


/**
 * 
 * @formatter:off
 * description: CoeCategProdServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeCategProdServiceImpl extends GenericServiceImpl<CoeCategProd, Long> implements CoeCategProdService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategProdServiceImpl.class);

	@Autowired
	private CoeCategProdDao coeCategProdDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeCategProdDao;
	}

	/**
	 * 根据Id查询CoeCategProd实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeCategProd findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeCategProd coeCategProd = coeCategProdDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeCategProd);
		return coeCategProd;
	}
	
	@Override
	public CoeCategProd findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeCategProd coeCategProd = coeCategProdDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategProd);
		return coeCategProd;
	}

	/**
	 * 根据IdList查询CoeCategProd实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeCategProd> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeCategProd> coeCategProdList = coeCategProdDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeCategProdList);
		return coeCategProdList;
	}

	@Transactional
	@Override
	public CoeCategProd persistEntity(CoeCategProd coeCategProd) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeCategProd entity = save(coeCategProd);
		Long id = coeCategProd.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeCategProd.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeCategProd mergeEntity(CoeCategProd coeCategProd) {
		Long id = coeCategProd.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeCategProd entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeCategProd.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeCategProd.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeCategProd saveOrUpdate(CoeCategProd coeCategProd) {
		Long id = coeCategProd.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeCategProd entity = null;
		if(null != id) {
			entity = mergeEntity(coeCategProd);
		}else {
			entity = persistEntity(coeCategProd);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeCategProd> findPage(CoeCategProd coeCategProd, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeCategProd> page = coeCategProdDao.findPage(coeCategProd, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeCategProdDao.existById(id);
	}
	
	public List<CoeCategProd> findByMainId(Long productId){
		return coeCategProdDao.findByMainId(productId);
	}

	public List<CoeCategProd> findByMainIdList(List<Long> productIdList) {
		return coeCategProdDao.findByMainIdList(productIdList);
	}
	
	public Map<Long, List<CoeCategProd>> findMapByMainIdList(List<Long> productIdList) {
		List<CoeCategProd> content = findByMainIdList(productIdList);
		Map<Long, List<CoeCategProd>> map = null;
		if(null != content && !content.isEmpty()) {
			/*for(CoeCategProd c : content) {
				Long mainId = c.getMainId();
				List<CoeCategProd> entityList = map.get(mainId);
				if(null == entityList) {
					entityList = new ArrayList<>();
					map.put(mainId, entityList);
				}
				entityList.add(c);
			}*/
			map = CollectionUtil.convertList2Map(content, "mainId");
		}
		return map;
	}
	
	@Transactional
	public CoeCategProd changeCategory(CoeCategProd coeCategProd) {
		Long maidId = coeCategProd.getMainId();
		Long baseId = coeCategProd.getBaseId();
		List<CoeCategProd> entityList = findByMainId(maidId);
		boolean exist=false;
		if(null != entityList && !entityList.isEmpty()) {
			for(CoeCategProd entity : entityList) {
				Long entityBaseId = entity.getBaseId();
				if(baseId.equals(entityBaseId)) {
					exist=true;
					continue;
				}
				delete(entity);
				coeCategProd.setSuccess(CoeCategProd.SUCCESS_YES);
			}
		}
		if(!exist) {
			coeCategProd = save(coeCategProd);
			coeCategProd.setSuccess(CoeCategProd.SUCCESS_YES);
		}
		return coeCategProd;
	}

}
