package dml.sz0099.course.app.client.wrapper.position;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.position.PositionRefDelegate;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionImage;
import dml.sz0099.course.app.persist.entity.position.PositionRef;
import dml.sz0099.course.app.persist.entity.position.PositionRef;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PositionRefWrapperImpl,组件封装
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
public class PositionRefWrapperImpl implements PositionRefWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionRefWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private PositionRefDelegate positionRefDelegate;
	
	@Autowired
	private PositionImageWrapper positionImageWrapper;
	
	/**
	 * 根据Id查询PositionRef实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PositionRef findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		PositionRef positionRef = null;
		if(null != id) {
			positionRef = positionRefDelegate.findById(id);
		}
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, positionRef);
		return positionRef;
	}
	
	public PositionRef findById(Long id, boolean withBanner) {
		PositionRef positionRef = findById(id);
		if(withBanner) {
			List<PositionImage> bannerList = positionImageWrapper.findByRefId(id);
			positionRef.setBannerList(bannerList);
		}
		return positionRef;
	}
	
	@Override
	public PositionRef findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		PositionRef positionRef = positionRefDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, positionRef);
		return positionRef;
	}
	
	/**
	 * 根据IdList查询PositionRef实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PositionRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<PositionRef> positionRefList = positionRefDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  positionRefList);
		return positionRefList;
	}
	
	@Override
	public PositionRef persistEntity(PositionRef positionRef) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		PositionRef entity = positionRefDelegate.persistEntity(positionRef);
		Long id = positionRef.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionRef mergeEntity(PositionRef positionRef) {
		Long id = positionRef.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		PositionRef entity = positionRefDelegate.mergeEntity(positionRef);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PositionRef saveOrUpdate(PositionRef positionRef) {
		Long id = positionRef.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		PositionRef entity = positionRefDelegate.saveOrUpdate(positionRef);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PositionRef> findPage(PositionRef positionRef, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<PositionRef> page = positionRefDelegate.findPage(positionRef, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return positionRefDelegate.existById(id);
	}
	
	@Override
	public Long countForMain(PositionRef positionRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countForSub(PositionRef positionRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countForBase(PositionRef positionRef) {
		Long baseId = positionRef.getBaseId();
		return positionRefDelegate.countForBase(positionRef);
	}

	@Override
	public Long findPositionId(Long id) {
		
		return null;
	}

	@Override
	public PositionRef changeSingle(PositionRef positionRef) {
		positionRef=positionRefDelegate.changeSingle(positionRef);
		return positionRef;
	}
	
	public PositionRef addPositionRef(PositionRef positionRef) {
		positionRef=changeSingle(positionRef);
		return positionRef;
	}

	@Override
	public void deleteById(PositionRef positionRef) {
		positionRefDelegate.deleteById(positionRef);
	}
	@Override
	public void deletePositionRef(PositionRef positionRef) {
		deleteById(positionRef);
	}
	
	@Override
	public PositionRef openPositionRef(PositionRef positionRef) {
		Long id = positionRef.getId();
		if(null != id) {
			positionRef = positionRefDelegate.openPositionRef(positionRef);
		}
		return positionRef;
	}
	@Override
	public PositionRef mergeSimpleSingle(PositionRef positionRef) {
		Long id = positionRef.getId();
		if(null != id) {
			positionRef = positionRefDelegate.mergeSimpleSingle(positionRef);
		}
		return positionRef;
	}
	@Override
	public PositionRef deleteRefByBaseId(PositionRef positionRef) {
		Long baseId = positionRef.getBaseId();
		if(null != baseId) {
			positionRef = positionRefDelegate.deleteRefByBaseId(positionRef);
		}
		return positionRef;
	}
	

	@Override
	public PositionRef deleteByMainIdAndSubId(PositionRef positionRef) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Page<PositionRef> findPageByBaseId(Long baseId, Pageable pageable, boolean withImages) {
		return positionRefDelegate.findPageByBaseId(baseId, pageable, withImages);
	}

	@Override
	public List<PositionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList) {
		return positionRefDelegate.findByBaseIdAndMainIdList( baseId, mainIdList) ;
	}

	@Override
	public PositionRef findMainIdAndBaseId(PositionRef positionRef) {
		return positionRefDelegate.findMainIdAndBaseId(positionRef);
	}

	@Override
	public PositionRef mergePositionRef(PositionRef positionRef) {
		return positionRefDelegate.mergePositionRef(positionRef);
	}
}
