package dml.sz0099.course.app.client.wrapper.position;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.position.PositionExtendDelegate;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionExtendWrapperImpl,组件封装
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
public class PositionExtendWrapperImpl implements PositionExtendWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionExtendWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private PositionExtendDelegate positionExtendDelegate;
	
	/**
	 * 根据Id查询PositionExtend实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PositionExtend findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		PositionExtend positionExtend = positionExtendDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, positionExtend);
		return positionExtend;
	}
	
	@Override
	public PositionExtend findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		PositionExtend positionExtend = positionExtendDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, positionExtend);
		return positionExtend;
	}
	
	/**
	 * 根据IdList查询PositionExtend实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<PositionExtend> positionExtendList = positionExtendDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  positionExtendList);
		return positionExtendList;
	}
	
	@Override
	public PositionExtend persistEntity(PositionExtend positionExtend) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		PositionExtend entity = positionExtendDelegate.persistEntity(positionExtend);
		Long id = positionExtend.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionExtend mergeEntity(PositionExtend positionExtend) {
		Long id = positionExtend.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		PositionExtend entity = positionExtendDelegate.mergeEntity(positionExtend);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionExtend saveOrUpdate(PositionExtend positionExtend) {
		Long id = positionExtend.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		PositionExtend entity = positionExtendDelegate.saveOrUpdate(positionExtend);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionExtend> findPage(PositionExtend positionExtend, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<PositionExtend> page = positionExtendDelegate.findPage(positionExtend, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return positionExtendDelegate.existById(id);
	}

	@Override
	public List<PositionExtend> findAll() {
		return positionExtendDelegate.findAll();
	}

	@Override
	public PositionExtend create(PositionExtend positionExtend) {
		initMaxnum(positionExtend);
		return positionExtendDelegate.create(positionExtend);
	}
	
	private void initMaxnum(PositionExtend positionExtend) {
		Integer mainMaxnum = positionExtend.getMainMaxnum();
		if(null == mainMaxnum) {
			positionExtend.setMainMaxnum(PositionExtend.MAINMAXNUM_DEFAULT);
		}
		
		Integer subMaxnum = positionExtend.getSubMaxnum();
		if(null == subMaxnum) {
			positionExtend.setSubMaxnum(PositionExtend.SUBMAXNUM_DEFAULT);
		}
		
		Integer refMaxnum = positionExtend.getRefMaxnum();
		if(null == refMaxnum) {
			positionExtend.setRefMaxnum(PositionExtend.REFMAXNUM_DEFAULT);
		}
	}
	
	@Override
	public PositionExtend findByPositionId(Long positionId) {
		return positionExtendDelegate.findByPositionId(positionId);
	}

	@Override
	public PositionExtend findPositionExtend(PositionExtend extend) {
		return positionExtendDelegate.findPositionExtend(extend);
	}

	@Override
	public Long countByUserId(Long userId) {
		return positionExtendDelegate.countByUserId(userId);
	}

	@Override
	public PositionExtend deleteEntity(PositionExtend extend) {
		
		return positionExtendDelegate.deleteEntity(extend);
	}
}
