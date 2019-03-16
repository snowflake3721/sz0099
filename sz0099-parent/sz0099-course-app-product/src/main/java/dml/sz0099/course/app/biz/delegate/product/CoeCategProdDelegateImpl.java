package dml.sz0099.course.app.biz.delegate.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.product.CoeCategProdService;
import dml.sz0099.course.app.persist.entity.product.CoeCategProd;

/**
 * coeCategProdServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeCategProdDelegateImpl implements CoeCategProdDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeCategProdDelegateImpl.class);
	
	@Autowired
	private CoeCategProdService coeCategProdService;

	/**
	 * 根据Id查询CoeCategProd实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeCategProd findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeCategProd coeCategProd = coeCategProdService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeCategProd);
		return coeCategProd;
	}
	
	@Override
	public CoeCategProd findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeCategProd coeCategProd = coeCategProdService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeCategProd);
		return coeCategProd;
	}
	
	/**
	 * 根据IdList查询CoeCategProd实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeCategProd> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeCategProd> coeCategProdList = coeCategProdService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeCategProdList);
		return coeCategProdList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeCategProd persistEntity(CoeCategProd coeCategProd) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeCategProd entity = coeCategProdService.persistEntity(coeCategProd);
		Long id = coeCategProd.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeCategProd mergeEntity(CoeCategProd coeCategProd) {
		Long id = coeCategProd.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeCategProd entity = coeCategProdService.mergeEntity(coeCategProd);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeCategProd saveOrUpdate(CoeCategProd coeCategProd) {
		Long id = coeCategProd.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeCategProd entity = coeCategProdService.saveOrUpdate(coeCategProd);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeCategProd> findPage(CoeCategProd coeCategProd, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeCategProd> page = coeCategProdService.findPage(coeCategProd, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeCategProdService.existById(id);
	}
	
	public Map<Long, List<CoeCategProd>> findMapByMainIdList(List<Long> productIdList) {
		return coeCategProdService.findMapByMainIdList(productIdList);
	}
	
	public List<CoeCategProd> findByMainId(Long productId){
		return coeCategProdService.findByMainId(productId);
	}
	
	public CoeCategProd changeCategory(CoeCategProd coeCategProd) {
		return coeCategProdService.changeCategory(coeCategProd);
	}
}
