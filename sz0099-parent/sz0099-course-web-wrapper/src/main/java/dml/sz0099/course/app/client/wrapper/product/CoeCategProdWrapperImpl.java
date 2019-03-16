package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.product.CoeCategProdDelegate;
import dml.sz0099.course.app.persist.entity.product.CoeCategProd;
import dml.sz0099.course.app.persist.entity.product.CoeCategProd;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeCategProdWrapperImpl,组件封装
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
public class CoeCategProdWrapperImpl implements CoeCategProdWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategProdWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeCategProdDelegate coeCategProdDelegate;
	
	/**
	 * 根据Id查询CoeCategProd实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeCategProd findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeCategProd coeCategProd = coeCategProdDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeCategProd);
		return coeCategProd;
	}
	
	@Override
	public CoeCategProd findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeCategProd coeCategProd = coeCategProdDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategProd);
		return coeCategProd;
	}
	
	/**
	 * 根据IdList查询CoeCategProd实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeCategProd> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeCategProd> coeCategProdList = coeCategProdDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeCategProdList);
		return coeCategProdList;
	}
	
	@Override
	public CoeCategProd persistEntity(CoeCategProd coeCategProd) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeCategProd entity = coeCategProdDelegate.persistEntity(coeCategProd);
		Long id = coeCategProd.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeCategProd mergeEntity(CoeCategProd coeCategProd) {
		Long id = coeCategProd.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeCategProd entity = coeCategProdDelegate.mergeEntity(coeCategProd);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeCategProd saveOrUpdate(CoeCategProd coeCategProd) {
		Long id = coeCategProd.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeCategProd entity = coeCategProdDelegate.saveOrUpdate(coeCategProd);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeCategProd> findPage(CoeCategProd coeCategProd, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeCategProd> page = coeCategProdDelegate.findPage(coeCategProd, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeCategProdDelegate.existById(id);
	}
	
	public Map<Long, List<CoeCategProd>> findMapByMainIdList(List<Long> productIdList) {
		return coeCategProdDelegate.findMapByMainIdList(productIdList);
	}
	
	public List<CoeCategProd> findByMainId(Long productId){
		return coeCategProdDelegate.findByMainId(productId);
	}

	@Override
	public CoeCategProd changeCategory(CoeCategProd coeCategProd) {
		return coeCategProdDelegate.changeCategory(coeCategProd);
	}
}
