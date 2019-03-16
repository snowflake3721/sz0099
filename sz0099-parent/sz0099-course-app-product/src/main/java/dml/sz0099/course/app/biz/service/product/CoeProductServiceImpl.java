package dml.sz0099.course.app.biz.service.product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.codehaus.plexus.util.StringUtils;
import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.product.CoeProductDao;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;


/**
 * 
 * @formatter:off
 * description: CoeProductServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeProductServiceImpl extends GenericServiceImpl<CoeProduct, Long> implements CoeProductService, Serializable {

	private static final long serialVersionUID = 2544053574902113969L;

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductServiceImpl.class);

	@Autowired
	private CoeProductDao coeProductDao;
	
	@Autowired
	private CoeGradeService coeGradeService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeProductDao;
	}

	/**
	 * 根据Id查询CoeProduct实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeProduct findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeProduct coeProduct = coeProductDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeProduct);
		return coeProduct;
	}
	
	public boolean existById(Long id) {
		CoeProduct coeProduct = coeProductDao.findById(id);
		return coeProduct!=null;
	}

	/**
	 * 根据IdList查询CoeProduct实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeProduct> coeProductList = coeProductDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeProductList);
		return coeProductList;
	}
	
	/**
	 * 新建草稿，将会产生产品originalLink,productNo
	 * @param coeProduct
	 * @return
	 */
	@Transactional
	@Override
	public CoeProduct createDraft(CoeProduct coeProduct) {
		//执行数据初始化
		coeProduct.setPublishStatus(CoeProduct.PUBLISH_STATUS_DRAFT.getValueInt());
		coeProduct.setShelved(CoeProduct.SHELVED_NO.getValueInt());
		coeProduct.setGrade(CoeGrade.GRADE_L0);
		
		CoeGrade grade = coeGradeService.findByGrade(CoeGrade.GRADE_L0);
		coeProduct.setRates(grade.getRates());
		coeProduct.setStrategy(CoeProduct.STRATEGY_0_COMMON.getValueInt());
		
		CoeProduct entity = persistEntity(coeProduct);
		return entity;
	}

	@Transactional
	@Override
	public CoeProduct persistEntity(CoeProduct coeProduct) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		String title = coeProduct.getTitle();
		coeProduct.setTitleLower(StringUtils.lowerCase(title));
		CoeProduct entity = save(coeProduct);
		Long id = entity.getId();
		Long aid = entity.getAid();
		entity.setProductNo(String.valueOf(aid));
		String originalLink = coeProduct.getOriginalLink();
		if(StringUtils.isNotBlank(originalLink)) {
			originalLink=originalLink.replace("{id}", String.valueOf(id));
			entity.setOriginalLink(originalLink);
		}
		entity.setSuccess(CoeProduct.SUCCESS_YES);
		
		save(entity);
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}
	
	public static void main(String[] args) {
		CoeProduct coeProduct = new CoeProduct();
		Long id=1234400L;
		String originalLink = "/product/detail/{id}";
		if(StringUtils.isNotBlank(originalLink)) {
			originalLink=originalLink.replace("{id}", String.valueOf(id));
		}
		System.out.println(originalLink);
	}
	
	@Transactional
	@Override
	public CoeProduct saveOrUpdate(CoeProduct coeProduct) {
		Long id = coeProduct.getId();
		if(null != id) {
			return mergeProduct(coeProduct);
		}else {
			return persistEntity(coeProduct);
		}
	}
	
	@Transactional
	@Override
	public CoeProduct mergeProduct(CoeProduct coeProduct) {
		Long id = coeProduct.getId();
		CoeProduct entity = findById(id);
		if(null != entity) {
			entity.setDescription(coeProduct.getDescription());
			entity.setMinutes(coeProduct.getMinutes());
			entity.setLastModifiedBy(coeProduct.getLastModifiedBy());
			entity.setName(coeProduct.getName());
			entity.setLink(coeProduct.getLink());
			entity.setPriceCur(coeProduct.getPriceCur());
			entity.setPriceOri(coeProduct.getPriceOri());
			entity.setShelved(coeProduct.getShelved());
			entity.setStrategy(coeProduct.getStrategy());
			entity.setGrade(coeProduct.getGrade());
			String title = coeProduct.getTitle();
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			entity.setLastModifiedBy(coeProduct.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
			coeProduct.setSuccess(CoeProduct.SUCCESS_YES);
		}
		return coeProduct;
	}
	
	@Transactional
	@Override
	public CoeProduct mergeForBaseinfo(CoeProduct coeProduct) {
		CoeProduct entity = mergeForBaseinfo(null, coeProduct);
		if(null != entity) {
			save(entity);
			entity.setSuccess(CoeProduct.SUCCESS_YES);
		}
		return entity;
	}
	
	public CoeProduct mergeForBaseinfo(CoeProduct entity, CoeProduct coeProduct) {
		Long id = coeProduct.getId();
		if(null == entity) {
			entity = findById(id);
		}
		if (null != entity) {
			entity.setName(coeProduct.getName());
			String title = coeProduct.getTitle();
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			entity.setDescription(coeProduct.getDescription());
			entity.setLastModifiedBy(coeProduct.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeProduct mergeShelved(CoeProduct coeProduct) {
		Long id = coeProduct.getId();
		CoeProduct entity = findById(id);
		if(null != entity) {
			entity.setShelved(coeProduct.getShelved());
			entity.setLastModifiedBy(coeProduct.getLastModifiedBy());
			DateTime dateTime = new DateTime();
			entity.setLastModifiedDate(dateTime);
			entity.setShelvedTime(dateTime.toDate());
			save(entity);
			coeProduct.setSuccess(CoeProduct.SUCCESS_YES);
		}
		return coeProduct;
	}
	
	@Transactional
	@Override
	public CoeProduct mergeProductForLink(CoeProduct coeProduct) {
		Long id = coeProduct.getId();
		CoeProduct entity = findById(id);
		if(null != entity) {
			entity.setLink(coeProduct.getLink());
			String title = coeProduct.getTitle();
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			save(entity);
			coeProduct.setSuccess(CoeProduct.SUCCESS_YES);
		}
		return coeProduct;
	}
	
	@Transactional
	@Override
	public CoeProduct mergeProductForPrice(CoeProduct coeProduct) {
		CoeProduct entity = mergeProductForPrice(null, coeProduct);
		if(null != entity) {
			save(entity);
			entity.setSuccess(CoeProduct.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	public CoeProduct mergeProductForPrice(CoeProduct entity , CoeProduct coeProduct) {
		Long id = coeProduct.getId();
		if(null == entity) {
			entity = findById(id);
		}
		if(null != entity) {
			entity.setPriceCur(coeProduct.getPriceCur());
			entity.setPriceOri(coeProduct.getPriceOri());
			entity.setStrategy(coeProduct.getStrategy());
			entity.setRates(coeProduct.getRates());
			entity.setLink(coeProduct.getLink());
			entity.setPullCode(coeProduct.getPullCode());
			entity.setStrategy(coeProduct.getStrategy());
			entity.setGrade(coeProduct.getGrade());
			entity.setPullMethod(coeProduct.getPullMethod());
			entity.setLastModifiedBy(coeProduct.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
		}
		return entity;
	}
	
	public List<CoeProduct> findByShelved(CoeProduct coeProduct){
		
		String title = coeProduct.getTitle();
		if(StringUtils.isNotBlank(title)) {
			//findByTitle
			return findShelvedByTitle(title);
		}
		String name = coeProduct.getName();
		if(StringUtils.isNotBlank(name)) {
			//findByName
			return findShelvedByName(name);
		}
		return null;
	}
	
	public Page<CoeProduct> findByShelved(CoeProduct coeProduct, Pageable pageable){
		return coeProductDao.findByShelved( coeProduct,  pageable);
	}
	
	public List<CoeProduct> findShelvedByName(String name){
		return coeProductDao.findShelvedByName(name);
	}
	
	public List<CoeProduct> findShelvedByTitle(String title){
		return coeProductDao.findShelvedByTitle(title);
	}

	@Override
	public List<CoeProduct> findDraftList(CoeProduct coeProduct) {
		
		Long userId = coeProduct.getUserId();
		
		if(null != userId) {
			return coeProductDao.findDraftList(coeProduct);
		}
		
		return null;
	}
	
	public Long countDraftList(CoeProduct coeProduct) {
		return coeProductDao.countDraftList(coeProduct);
	}
	
	public CoeProduct findDetail(Long id) {
		return findById(id);
	}
	
	public Page<CoeProduct> findPublished(CoeProduct coeProduct, Pageable pageable){
		
		Page<CoeProduct> page = coeProductDao.findPublished(coeProduct, pageable);
		
		return page;
	}
	
	@Transactional
	public CoeProduct mergeForRefresh(CoeProduct coeProduct) {
		Long id = coeProduct.getId();
		CoeProduct entity = findById(id);
		if(null != entity) {
			DateTime lastModifiedDate = new DateTime();
			Date refreshTime = lastModifiedDate.toDate();
			entity.setRefreshTime(refreshTime);
			entity.setLastModifiedBy(coeProduct.getLastModifiedBy());
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			coeProduct.setRefreshTime(refreshTime);
			coeProduct.setSuccess(CoeProduct.SUCCESS_YES);
		}
		return coeProduct;
	}
	
	@Transactional
	public CoeProduct mergeForEditQickly(CoeProduct product) {
		Long id = product.getId();
		CoeProduct entity = findById(id);
		if(null != entity) {
			DateTime lastModifiedDate = new DateTime();
			String title = product.getTitle();
			entity.setTitle(title);
			entity.setTitleLower(StringUtils.lowerCase(title));
			
			entity.setName(product.getName());
			entity.setPriceCur(product.getPriceCur());
			entity.setPriceOri(product.getPriceOri());
			entity.setPullCode(product.getPullCode());
			entity.setLink(product.getLink());
			
			entity.setLastModifiedBy(product.getLastModifiedBy());
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			product.setSuccess(CoeProduct.SUCCESS_YES);
		}
		return product;
	}
	
	@Transactional
	public CoeProduct mergeForPublish(CoeProduct product) {
		Long id = product.getId();
		CoeProduct entity = findById(id);
		if(null != entity) {
			entity=mergeForBaseinfo(entity, product);
			entity=mergeProductForPrice(entity, product);
			Date publishTime = new Date();
			entity.setPublishTime(publishTime);
			entity.setPublishStatus(CoeProduct.PUBLISH_STATUS_PUBLISH.getValueInt());
			entity.setRefreshTime(publishTime);
			save(entity);
		}
		return product;
	}
	
	@Transactional
	public CoeProduct mergeForClosed(CoeProduct product) {
		Long id = product.getId();
		CoeProduct entity = findById(id);
		if(null != entity) {
			DateTime closedTime = new DateTime();
			entity.setClosedTime(closedTime.toDate());
			entity.setPublishStatus(CoeProduct.PUBLISH_STATUS_CLOSED.getValueInt());
			entity.setLastModifiedBy(product.getLastModifiedBy());
			entity.setLastModifiedDate(closedTime);
			save(entity);
			product=entity;
			product.setSuccess(CoeProduct.SUCCESS_YES);
		}
		return product;
	}
	@Transactional
	public CoeProduct mergeForDeleted(CoeProduct product) {
		Long id = product.getId();
		CoeProduct entity = findById(id);
		if(null != entity) {
			DateTime deletedTime = new DateTime();
			entity.setDeleted(product.getDeleted());
			entity.setLastModifiedBy(product.getLastModifiedBy());
			entity.setLastModifiedDate(deletedTime);
			save(entity);
			product=entity;
			product.setSuccess(CoeProduct.SUCCESS_YES);
		}
		return product;
	}
	
	public boolean publishedById(Long id) {
		 CoeProduct entity = findById(id);
		 Integer publishStatus = entity.getPublishStatus();
		 boolean published = CoeProduct.PUBLISH_STATUS_PUBLISH.equals(publishStatus);
		return published;
	}

}
