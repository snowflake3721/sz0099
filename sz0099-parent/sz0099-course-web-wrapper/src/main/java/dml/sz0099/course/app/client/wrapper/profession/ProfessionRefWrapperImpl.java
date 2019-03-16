package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.profession.ProfessionRefDelegate;
import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;
import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionRefWrapperImpl,组件封装
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
public class ProfessionRefWrapperImpl implements ProfessionRefWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionRefWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ProfessionRefDelegate professionRefDelegate;
	
	@Autowired
	private ProfessionImageWrapper professionImageWrapper;
	
	/**
	 * 根据Id查询ProfessionRef实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionRef findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ProfessionRef professionRef = null;
		if(null != id) {
			professionRef = professionRefDelegate.findById(id);
		}
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, professionRef);
		return professionRef;
	}
	
	public ProfessionRef findById(Long id, boolean withBanner) {
		ProfessionRef professionRef = findById(id);
		if(withBanner) {
			List<ProfessionImage> bannerList = professionImageWrapper.findByRefId(id);
			professionRef.setBannerList(bannerList);
		}
		return professionRef;
	}
	
	@Override
	public ProfessionRef findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionRef professionRef = professionRefDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, professionRef);
		return professionRef;
	}
	
	/**
	 * 根据IdList查询ProfessionRef实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ProfessionRef> professionRefList = professionRefDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  professionRefList);
		return professionRefList;
	}
	
	@Override
	public ProfessionRef persistEntity(ProfessionRef professionRef) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ProfessionRef entity = professionRefDelegate.persistEntity(professionRef);
		Long id = professionRef.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionRef mergeEntity(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ProfessionRef entity = professionRefDelegate.mergeEntity(professionRef);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionRef saveOrUpdate(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionRef entity = professionRefDelegate.saveOrUpdate(professionRef);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionRef> findPage(ProfessionRef professionRef, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ProfessionRef> page = professionRefDelegate.findPage(professionRef, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return professionRefDelegate.existById(id);
	}
	
	@Override
	public Long countForMain(ProfessionRef professionRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countForSub(ProfessionRef professionRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countForBase(ProfessionRef professionRef) {
		Long baseId = professionRef.getBaseId();
		return professionRefDelegate.countForBase(professionRef);
	}

	@Override
	public Long findPositionId(Long id) {
		
		return null;
	}

	@Override
	public ProfessionRef changeSingle(ProfessionRef professionRef) {
		professionRef=professionRefDelegate.changeSingle(professionRef);
		return professionRef;
	}
	
	public ProfessionRef addProfessionRef(ProfessionRef professionRef) {
		professionRef=changeSingle(professionRef);
		return professionRef;
	}

	@Override
	public void deleteById(ProfessionRef professionRef) {
		professionRefDelegate.deleteById(professionRef);
	}
	@Override
	public void deleteProfessionRef(ProfessionRef professionRef) {
		deleteById(professionRef);
	}
	
	@Override
	public ProfessionRef openProfessionRef(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		if(null != id) {
			professionRef = professionRefDelegate.openProfessionRef(professionRef);
		}
		return professionRef;
	}
	@Override
	public ProfessionRef mergeSimpleSingle(ProfessionRef professionRef) {
		Long id = professionRef.getId();
		if(null != id) {
			professionRef = professionRefDelegate.mergeSimpleSingle(professionRef);
		}
		return professionRef;
	}
	@Override
	public ProfessionRef deleteRefByBaseId(ProfessionRef professionRef) {
		Long baseId = professionRef.getBaseId();
		if(null != baseId) {
			professionRef = professionRefDelegate.deleteRefByBaseId(professionRef);
		}
		return professionRef;
	}
	

	@Override
	public ProfessionRef deleteByMainIdAndSubId(ProfessionRef professionRef) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Page<ProfessionRef> findPageByBaseId(Long baseId, Pageable pageable, boolean withCover, boolean withBanner) {
		return professionRefDelegate.findPageByBaseId(baseId, pageable, withCover, withBanner);
	}

	@Override
	public List<ProfessionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList) {
		return professionRefDelegate.findByBaseIdAndMainIdList( baseId, mainIdList) ;
	}

	@Override
	public ProfessionRef findMainIdAndBaseId(ProfessionRef professionRef) {
		return professionRefDelegate.findMainIdAndBaseId(professionRef);
	}

	@Override
	public ProfessionRef mergeProfessionRef(ProfessionRef professionRef) {
		return professionRefDelegate.mergeProfessionRef(professionRef);
	}
}
