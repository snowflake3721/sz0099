package dml.sz0099.course.app.persist.dao.order;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.order.CoeOrderTransfer;
import dml.sz0099.course.app.persist.repository.order.CoeOrderTransferRepository;
import dml.sz0099.course.app.persist.specification.order.CoeOrderTransferSpecification;

/**
 * CoeOrderTransferDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeOrderTransferDaoImpl extends GenericDaoImpl<CoeOrderTransfer, Long> implements CoeOrderTransferDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderTransferDaoImpl.class);

	@Autowired
	private CoeOrderTransferRepository coeOrderTransferRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeOrderTransferRepository;
	}

	/**
	 * 根据Id查询CoeOrderTransfer实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrderTransfer findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeOrderTransfer coeOrderTransfer = coeOrderTransferRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeOrderTransfer);
		return coeOrderTransfer;
	}

	@Override
	public CoeOrderTransfer findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrderTransfer coeOrderTransfer = coeOrderTransferRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrderTransfer);
		return coeOrderTransfer;
	}

	/**
	 * 根据IdList查询CoeOrderTransfer实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrderTransfer> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeOrderTransfer> coeOrderTransferList = coeOrderTransferRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeOrderTransferList);
		return coeOrderTransferList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeOrderTransfer> findPage(CoeOrderTransfer coeOrderTransfer, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeOrderTransferSpecification.getConditionWithQsl(coeOrderTransfer);
		Page<CoeOrderTransfer> page = coeOrderTransferRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeOrderTransfer entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
