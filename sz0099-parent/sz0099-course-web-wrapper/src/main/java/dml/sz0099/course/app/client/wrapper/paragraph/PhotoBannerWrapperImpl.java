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

import dml.sz0099.course.app.biz.delegate.paragraph.PhotoBannerDelegate;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoBanner;
import dml.sz0099.course.app.persist.entity.paragraph.bo.PhotoBannerBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * PhotoBannerWrapperImpl,组件封装
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
public class PhotoBannerWrapperImpl implements PhotoBannerWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoBannerWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private PhotoBannerDelegate photoBannerDelegate;
	
	/**
	 * 根据Id查询PhotoBanner实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PhotoBanner findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		PhotoBanner photoBanner = photoBannerDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, photoBanner);
		return photoBanner;
	}
	
	@Override
	public PhotoBanner findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		PhotoBanner photoBanner = photoBannerDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, photoBanner);
		return photoBanner;
	}
	
	/**
	 * 根据IdList查询PhotoBanner实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoBanner> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<PhotoBanner> photoBannerList = photoBannerDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  photoBannerList);
		return photoBannerList;
	}
	
	@Override
	public PhotoBanner persistEntity(PhotoBanner photoBanner) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		PhotoBanner entity = photoBannerDelegate.persistEntity(photoBanner);
		Long id = photoBanner.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoBanner mergeEntity(PhotoBanner photoBanner) {
		Long id = photoBanner.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		PhotoBanner entity = photoBannerDelegate.mergeEntity(photoBanner);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoBanner saveOrUpdate(PhotoBanner photoBanner) {
		Long id = photoBanner.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoBanner entity = photoBannerDelegate.saveOrUpdate(photoBanner);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoBanner> findPage(PhotoBanner photoBanner, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<PhotoBanner> page = photoBannerDelegate.findPage(photoBanner, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return photoBannerDelegate.existById(id);
	}


	public void deleteBySubIdAndMainId(Long subId, Long mainId ){		

		LOGGER.debug("-------wrapper>>>PhotoBannerWrapperImpl.deleteBySubIdAndMainId----------begin---------");
		photoBannerDelegate.deleteBySubIdAndMainId( subId,  mainId );
	}

	@Override
	public List<PhotoBanner> findBySubIdListAndMainId(List<Long> idList, Long mainId) {
		return photoBannerDelegate.findBySubIdListAndMainId(idList, mainId);
	}

	@Override
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId, Long userId, boolean cascade) {
		photoBannerDelegate.deleteBySubIdListAndMainId(subIdList, mainId,userId, cascade);
		
	}

	@Override
	public void deleteById(PhotoBanner photoBanner) {
		photoBannerDelegate.deleteById(photoBanner);
	}

	@Override
	public void deleteByIdList(PhotoBannerBo photoBanner) {
		photoBannerDelegate.deleteByIdList(photoBanner);
		
	}

	@Override
	public PhotoBanner createPhotoBanner(PhotoBannerBo photoBanner) {
		return photoBannerDelegate.createPhotoBanner(photoBanner);
	}

	@Override
	public List<PhotoBanner> createPhotoBanner(List<PhotoBannerBo> photoBannerList) {
		return photoBannerDelegate.createPhotoBanner(photoBannerList);
	}

	@Override
	public PhotoBanner mergeForTitle(PhotoBanner photoBanner) {
		
		return photoBannerDelegate.mergeForTitle(photoBanner);
	}

	@Override
	public List<PhotoBanner> mergeListForTitle(List<PhotoBanner> photoBannerList) {
		return photoBannerDelegate.mergeListForTitle(photoBannerList);
	}
	
	public List<PhotoBanner> persistForPhoto(List<PhotoBanner> photoBannerList){
		return photoBannerDelegate.persistForPhoto(photoBannerList);
	}

	@Override
	public Map<Long, List<PhotoBanner>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId) {
		
		return photoBannerDelegate.findByMainIdListAndSubId( mainIdList,  subId);
	}


}
