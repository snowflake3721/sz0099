package dml.sz0099.course.app.persist.dao.activity;

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

import dml.sz0099.course.app.persist.entity.activity.JoinItem;
import dml.sz0099.course.app.persist.repository.activity.JoinItemRepository;
import dml.sz0099.course.app.persist.specification.activity.JoinItemSpecification;

/**
 * JoinItemDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class JoinItemDaoImpl extends GenericDaoImpl<JoinItem, Long> implements JoinItemDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(JoinItemDaoImpl.class);
	
	@Autowired
	private JoinItemRepository joinItemRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = joinItemRepository;
	}

	/**
	 * 根据Id查询JoinItem实体对象
	 * @param id
	 * @return
	 */
	@Override
	public JoinItem findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		JoinItem joinItem = joinItemRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, joinItem);
		return joinItem;
	}
	
	@Override
	public JoinItem findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		JoinItem joinItem = joinItemRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, joinItem);
		return joinItem;
	}
	
	/**
	 * 根据IdList查询JoinItem实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<JoinItem> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<JoinItem> joinItemList = joinItemRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  joinItemList);
		return joinItemList;
	}

	/**
	 * 条件查询
	 */
	public Page<JoinItem> findPage(JoinItem joinItem, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = JoinItemSpecification.getConditionWithQsl(joinItem);
		Page<JoinItem> page = joinItemRepository.findAll(condition, pageable);
		return page;
	}
	
	public Page<JoinItem> findPageWithNotself(JoinItem joinItem, Pageable pageable) {
		LOGGER.debug("--- dao.findPageWithNotself ---------  ");
		BooleanExpression condition = JoinItemSpecification.getConditionWithNotself(joinItem);
		Page<JoinItem> page = joinItemRepository.findAll(condition, pageable);
		return page;
	}
	
	public Long countByMainId(Long mainId) {
		return joinItemRepository.countByMainId(mainId);
	}
	
	public List<JoinItem> findByMainId(Long mainId){
		return joinItemRepository.findByMainId(mainId);
	}
	
	public List<JoinItem> findByBaseId(Long mainId){
		return joinItemRepository.findByBaseId(mainId);
	}
	
	@Override
	public List<JoinItem> findByMainIdList(List<Long> activityIdList) {
		return joinItemRepository.findByMainIdList(activityIdList);
	}

}
