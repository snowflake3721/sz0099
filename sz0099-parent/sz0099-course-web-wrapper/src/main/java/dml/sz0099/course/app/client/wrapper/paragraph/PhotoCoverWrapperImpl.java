package dml.sz0099.course.app.client.wrapper.paragraph;

import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.paragraph.PhotoCoverDelegate;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoCover;
import dml.sz0099.course.app.persist.entity.paragraph.bo.PhotoCoverBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoCoverWrapperImpl,组件封装
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
public class PhotoCoverWrapperImpl implements PhotoCoverWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoCoverWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private PhotoCoverDelegate photoCoverDelegate;
	
	/**
	 * 根据Id查询PhotoCover实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PhotoCover findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		PhotoCover photoCover = photoCoverDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, photoCover);
		return photoCover;
	}
	
	@Override
	public PhotoCover findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		PhotoCover photoCover = photoCoverDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, photoCover);
		return photoCover;
	}
	
	/**
	 * 根据IdList查询PhotoCover实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<PhotoCover> photoCoverList = photoCoverDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  photoCoverList);
		return photoCoverList;
	}
	
	@Override
	public PhotoCover persistEntity(PhotoCover photoCover) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		PhotoCover entity = photoCoverDelegate.persistEntity(photoCover);
		Long id = photoCover.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoCover mergeEntity(PhotoCover photoCover) {
		Long id = photoCover.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		PhotoCover entity = photoCoverDelegate.mergeEntity(photoCover);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoCover saveOrUpdate(PhotoCover photoCover) {
		Long id = photoCover.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoCover entity = photoCoverDelegate.saveOrUpdate(photoCover);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoCover> findPage(PhotoCover photoCover, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<PhotoCover> page = photoCoverDelegate.findPage(photoCover, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return photoCoverDelegate.existById(id);
	}


	public void deleteBySubIdAndMainId(Long subId, Long mainId ){		

		LOGGER.debug("-------wrapper>>>PhotoCoverWrapperImpl.deleteBySubIdAndMainId----------begin---------");
		photoCoverDelegate.deleteBySubIdAndMainId( subId,  mainId );
	}

	@Override
	public List<PhotoCover> findBySubIdListAndMainId(List<Long> idList, Long mainId) {
		return photoCoverDelegate.findBySubIdListAndMainId(idList, mainId);
	}

	@Override
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId, Long userId, boolean cascade) {
		photoCoverDelegate.deleteBySubIdListAndMainId(subIdList, mainId,userId, cascade);
		
	}

	@Override
	public void deleteById(PhotoCover photoCover) {
		photoCoverDelegate.deleteById(photoCover);
	}

	@Override
	public void deleteByIdList(PhotoCoverBo photoCover) {
		photoCoverDelegate.deleteByIdList(photoCover);
		
	}

	@Override
	public PhotoCover createPhotoCover(PhotoCoverBo photoCover) {
		return photoCoverDelegate.createPhotoCover(photoCover);
	}

	@Override
	public List<PhotoCover> createPhotoCover(List<PhotoCoverBo> photoCoverList) {
		return photoCoverDelegate.createPhotoCover(photoCoverList);
	}

	@Override
	public PhotoCover mergeForTitle(PhotoCover photoCover) {
		
		return photoCoverDelegate.mergeForTitle(photoCover);
	}

	@Override
	public List<PhotoCover> mergeListForTitle(List<PhotoCover> photoCoverList) {
		return photoCoverDelegate.mergeListForTitle(photoCoverList);
	}
	
	public List<PhotoCover> persistForPhoto(List<PhotoCover> photoCoverList){
		return photoCoverDelegate.persistForPhoto(photoCoverList);
	}

	@Override
	public Map<Long, List<PhotoCover>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId) {
		
		return photoCoverDelegate.findByMainIdListAndSubId( mainIdList,  subId);
	}


}
