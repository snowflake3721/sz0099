package dml.sz0099.course.app.biz.delegate.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.order.CoeOrderAsignService;
import dml.sz0099.course.app.persist.entity.order.CoeOrderAsign;

/**
 * coeOrderAsignServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeOrderAsignDelegateImpl implements CoeOrderAsignDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderAsignDelegateImpl.class);
	
	@Autowired
	private CoeOrderAsignService coeOrderAsignService;

	/**
	 * 根据Id查询CoeOrderAsign实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderAsign findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeOrderAsign coeOrderAsign = coeOrderAsignService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeOrderAsign);
		return coeOrderAsign;
	}
	
	@Override
	public CoeOrderAsign findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderAsign coeOrderAsign = coeOrderAsignService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderAsign);
		return coeOrderAsign;
	}
	
	/**
	 * 根据IdList查询CoeOrderAsign实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderAsign> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeOrderAsign> coeOrderAsignList = coeOrderAsignService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeOrderAsignList);
		return coeOrderAsignList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeOrderAsign persistEntity(CoeOrderAsign coeOrderAsign) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeOrderAsign entity = coeOrderAsignService.persistEntity(coeOrderAsign);
		Long id = coeOrderAsign.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderAsign mergeEntity(CoeOrderAsign coeOrderAsign) {
		Long id = coeOrderAsign.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeOrderAsign entity = coeOrderAsignService.mergeEntity(coeOrderAsign);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrderAsign saveOrUpdate(CoeOrderAsign coeOrderAsign) {
		Long id = coeOrderAsign.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrderAsign entity = coeOrderAsignService.saveOrUpdate(coeOrderAsign);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrderAsign> findPage(CoeOrderAsign coeOrderAsign, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeOrderAsign> page = coeOrderAsignService.findPage(coeOrderAsign, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeOrderAsignService.existById(id);
	}
}
