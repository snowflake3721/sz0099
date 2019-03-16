package dml.sz0099.course.app.biz.service.position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import dml.sz0099.course.app.persist.dao.position.PositionDao;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionRef;


/**
 * 
 * @formatter:off
 * description: PositionServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class PositionServiceImpl extends GenericServiceImpl<Position, Long> implements PositionService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionServiceImpl.class);

	@Autowired
	private PositionDao positionDao;
	
	@Autowired
	private PositionRefService positionRefService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = positionDao;
	}

	/**
	 * 根据Id查询Position实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Position findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		Position position = positionDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, position);
		return position;
	}
	
	@Override
	public Position findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		Position position = positionDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, position);
		return position;
	}

	/**
	 * 根据IdList查询Position实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Position> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<Position> positionList = positionDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", positionList);
		return positionList;
	}

	@Transactional
	@Override
	public Position persistEntity(Position position) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		Position entity = save(position);
		entity.setOrderSeq(entity.getAid());
		save(entity);
		Long id = position.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(Position.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public Position mergeEntity(Position position) {
		Long id = position.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		Position entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(position.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			
			entity.setDescription(position.getDescription());
			entity.setLayout(position.getLayout());
			entity.setLink(position.getLink());
			entity.setName(position.getName());
			Integer status = position.getStatus();
			
			if(Position.STATUS_1_OPEN.getValueInt().equals(status)) {
				entity.setOpenTime(new Date());
				entity.setStatus(status);
			}else if(Position.STATUS_2_CLOSED.getValueInt().equals(status)) {
				entity.setClosedTime(new Date());
				entity.setStatus(status);
			}
			
			entity.setPanel(position.getPanel());
			
			
			entity.setSubId(position.getSubId());
			entity.setTitle(position.getTitle());
			entity.setOrderSeq(position.getOrderSeq());
			
			save(entity);
			entity.setSuccess(Position.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public Position saveOrUpdate(Position position) {
		Long id = position.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		Position entity = null;
		if(null != id) {
			entity = mergeEntity(position);
		}else {
			entity = persistEntity(position);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Position> findPage(Position position, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<Position> page = positionDao.findPage(position, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return positionDao.existById(id);
	}

	@Override
	public List<Position> findMainIdAndExtendId(Position position) {
		
		return positionDao.findMainIdAndExtendId(position);
	}
	
	public List<Position> findMainIdAndExtendId(Position position, boolean withRef, boolean withImages, Pageable pageable) {
		List<Position> positionList = positionDao.findMainIdAndExtendId(position);
		
		return positionList;
	}
	
	public List<Position> findMainIdAndPositionId(Long mainId, Long positionId){
		return positionDao.findMainIdAndPositionId( mainId,  positionId);
	}

	@Override
	public List<Position> getSorted(List<Position> content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position deletePosition(Position position) {
		 Long id = position.getId();
		 Position entity = findById(id);
		 if(null != entity) {
			 delete(entity);
			 position.setSuccess(Position.SUCCESS_YES);
		 }
		return position;
	}

	@Override
	public Long countByExtendId(Long extendId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Position deleteAllByExtend(Position position) {
		
		Long positionId = position.getPositionId();
		Long mainId = position.getMainId();
		List<Position> entityList = findMainIdAndPositionId( mainId,  positionId);
		if(null != entityList && !entityList.isEmpty()) {
			List<Long> idList = new ArrayList<>();
			for(Position entity : entityList) {
				Long id = entity.getId();
				idList.add(id);
			}
			positionRefService.deleteByBaseIdList(idList, true);
			delete(entityList);
			position.setSuccess(Position.SUCCESS_YES);
		}
		return position;
	}

	@Override
	public Position findById(Long id, boolean cascade, boolean withImages, Pageable pageable) {
		Position entity = findById(id);
		if(entity != null && cascade ) {
			Long baseId = entity.getId();
			Page<PositionRef> positionRefPage = positionRefService.findPageByBaseId( baseId,  pageable,  withImages);;
			entity.setPositionRefPage(positionRefPage);
			/*if(null != page) {
				List<PositionRef> positionRefs = page.getContent();
				entity.setPositionRefs(positionRefs);
			}*/
		}
		return entity;
	}

	@Override
	public Map<Long, Position> findMapByIdList(List<Long> idList) {
		List<Position> content = findByIdList( idList);
		Map<Long, Position> map = new HashMap<>();
		if(null != content && !content.isEmpty()) {
			for(Position c : content) {
				map.put(c.getId(), c);
			}
		}
		return map;
	}

	@Override
	public List<Position> findBindList(Position position, Pageable pageable) {
		List<Position> positionList = findMainIdAndExtendId( position,  true,  true,  pageable);
		return positionList;
	}
	
	public List<Position> findPosition(Long mainId, Long subId, Long positionId, Integer panel){
		return positionDao.findPosition(mainId, subId, positionId, panel);
	}

}
