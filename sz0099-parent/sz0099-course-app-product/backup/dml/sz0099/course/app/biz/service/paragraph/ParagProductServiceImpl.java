package dml.sz0099.course.app.biz.service.paragraph;

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

import dml.sz0099.course.app.persist.dao.paragraph.ParagProductDao;
import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;


/**
 * 
 * @formatter:off
 * description: ParagProductServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class ParagProductServiceImpl extends GenericServiceImpl<ParagProduct, Long> implements ParagProductService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagProductServiceImpl.class);

	@Autowired
	private ParagProductDao paragProductDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = paragProductDao;
	}

	/**
	 * 根据Id查询ParagProduct实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ParagProduct findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		ParagProduct paragProduct = paragProductDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, paragProduct);
		return paragProduct;
	}
	
	@Override
	public ParagProduct findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		ParagProduct paragProduct = paragProductDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, paragProduct);
		return paragProduct;
	}

	/**
	 * 根据IdList查询ParagProduct实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<ParagProduct> paragProductList = paragProductDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", paragProductList);
		return paragProductList;
	}

	@Transactional
	@Override
	public ParagProduct persistEntity(ParagProduct paragProduct) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		ParagProduct entity = save(paragProduct);
		Long id = paragProduct.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(ParagProduct.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public ParagProduct mergeEntity(ParagProduct paragProduct) {
		Long id = paragProduct.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		ParagProduct entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(paragProduct.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(ParagProduct.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public ParagProduct saveOrUpdate(ParagProduct paragProduct) {
		Long id = paragProduct.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		ParagProduct entity = null;
		if(null != id) {
			entity = mergeEntity(paragProduct);
		}else {
			entity = persistEntity(paragProduct);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ParagProduct> findPage(ParagProduct paragProduct, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<ParagProduct> page = paragProductDao.findPage(paragProduct, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return paragProductDao.existById(id);
	}



	public ParagProduct> findByMainId(Long long, Pageable pageable ){		

		LOGGER.debug("-------service>>>ParagProductServiceImpl.findByMainId----------begin---------");

		ParagProduct> paragProduct> = paragProductDao.findByMainId( long,  pageable );

		LOGGER.info("-------service>>>ParagProductServiceImpl.findByMainId----------end---------");

		return paragProduct>;
	}


}
