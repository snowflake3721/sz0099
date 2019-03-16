package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.profession.ProfessionExtendDelegate;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionExtendWrapperImpl,组件封装
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
public class ProfessionExtendWrapperImpl implements ProfessionExtendWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionExtendWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ProfessionExtendDelegate professionExtendDelegate;
	
	/**
	 * 根据Id查询ProfessionExtend实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionExtend findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ProfessionExtend professionExtend = professionExtendDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, professionExtend);
		return professionExtend;
	}
	
	@Override
	public ProfessionExtend findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionExtend professionExtend = professionExtendDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, professionExtend);
		return professionExtend;
	}
	
	/**
	 * 根据IdList查询ProfessionExtend实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ProfessionExtend> professionExtendList = professionExtendDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  professionExtendList);
		return professionExtendList;
	}
	
	@Override
	public ProfessionExtend persistEntity(ProfessionExtend professionExtend) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ProfessionExtend entity = professionExtendDelegate.persistEntity(professionExtend);
		Long id = professionExtend.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionExtend mergeEntity(ProfessionExtend professionExtend) {
		Long id = professionExtend.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ProfessionExtend entity = professionExtendDelegate.mergeEntity(professionExtend);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionExtend saveOrUpdate(ProfessionExtend professionExtend) {
		Long id = professionExtend.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionExtend entity = professionExtendDelegate.saveOrUpdate(professionExtend);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionExtend> findPage(ProfessionExtend professionExtend, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ProfessionExtend> page = professionExtendDelegate.findPage(professionExtend, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return professionExtendDelegate.existById(id);
	}

	@Override
	public List<ProfessionExtend> findAll() {
		return professionExtendDelegate.findAll();
	}

	@Override
	public ProfessionExtend create(ProfessionExtend professionExtend) {
		initMaxnum(professionExtend);
		return professionExtendDelegate.create(professionExtend);
	}
	
	private void initMaxnum(ProfessionExtend professionExtend) {
		Integer mainMaxnum = professionExtend.getMainMaxnum();
		if(null == mainMaxnum) {
			professionExtend.setMainMaxnum(ProfessionExtend.MAINMAXNUM_DEFAULT);
		}
		
		Integer subMaxnum = professionExtend.getSubMaxnum();
		if(null == subMaxnum) {
			professionExtend.setSubMaxnum(ProfessionExtend.SUBMAXNUM_DEFAULT);
		}
		
		Integer refMaxnum = professionExtend.getRefMaxnum();
		if(null == refMaxnum) {
			professionExtend.setRefMaxnum(ProfessionExtend.REFMAXNUM_DEFAULT);
		}
	}
	
	@Override
	public ProfessionExtend findByPositionId(Long positionId) {
		return professionExtendDelegate.findByPositionId(positionId);
	}

	@Override
	public ProfessionExtend findProfessionExtend(ProfessionExtend extend) {
		return professionExtendDelegate.findProfessionExtend(extend);
	}

	@Override
	public Long countByUserId(Long userId) {
		return professionExtendDelegate.countByUserId(userId);
	}

	@Override
	public ProfessionExtend deleteEntity(ProfessionExtend extend) {
		
		return professionExtendDelegate.deleteEntity(extend);
	}
}
