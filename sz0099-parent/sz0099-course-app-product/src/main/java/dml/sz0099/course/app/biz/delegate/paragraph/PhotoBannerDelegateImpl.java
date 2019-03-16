package dml.sz0099.course.app.biz.delegate.paragraph;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.paragraph.PhotoBannerService;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoBanner;
import dml.sz0099.course.app.persist.entity.paragraph.bo.PhotoBannerBo;

/**
 * photoBannerServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class PhotoBannerDelegateImpl implements PhotoBannerDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoBannerDelegateImpl.class);
	
	@Autowired
	private PhotoBannerService photoBannerService;

	/**
	 * 根据Id查询PhotoBanner实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PhotoBanner findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		PhotoBanner photoBanner = photoBannerService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, photoBanner);
		return photoBanner;
	}
	
	@Override
	public PhotoBanner findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		PhotoBanner photoBanner = photoBannerService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, photoBanner);
		return photoBanner;
	}
	
	/**
	 * 根据IdList查询PhotoBanner实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoBanner> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<PhotoBanner> photoBannerList = photoBannerService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  photoBannerList);
		return photoBannerList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public PhotoBanner persistEntity(PhotoBanner photoBanner) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		PhotoBanner entity = photoBannerService.persistEntity(photoBanner);
		Long id = photoBanner.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoBanner mergeEntity(PhotoBanner photoBanner) {
		Long id = photoBanner.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		PhotoBanner entity = photoBannerService.mergeEntity(photoBanner);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoBanner saveOrUpdate(PhotoBanner photoBanner) {
		Long id = photoBanner.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoBanner entity = photoBannerService.saveOrUpdate(photoBanner);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoBanner> findPage(PhotoBanner photoBanner, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<PhotoBanner> page = photoBannerService.findPage(photoBanner, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return photoBannerService.existById(id);
	}


	public void deleteBySubIdAndMainId(Long paragId, Long mainId ){		

		LOGGER.debug("-------delegate>>>PhotoBannerDelegateImpl.deleteBySubIdAndMainId----------begin---------");
		photoBannerService.deleteBySubIdAndMainId( paragId,  mainId );
	}

	@Override
	public void deleteBySubIdListAndMainId(List<Long> paragIdList, Long mainId, Long userId, boolean cascade) {
		photoBannerService.deleteBySubIdListAndMainId(paragIdList, mainId, userId, cascade);
	}

	@Override
	public void deleteById(PhotoBanner photoBanner) {
		photoBannerService.deleteById(photoBanner);
	}

	@Override
	public void deleteByIdList(PhotoBannerBo photoBanner) {
		photoBannerService.deleteByIdList(photoBanner);
	}

	@Override
	public PhotoBanner createPhotoBanner(PhotoBannerBo photoBanner) {
		return photoBannerService.createPhotoBanner(photoBanner);
	}

	@Override
	public List<PhotoBanner> createPhotoBanner(List<PhotoBannerBo> photoBannerList) {
		return photoBannerService.createPhotoBanner(photoBannerList);
	}

	@Override
	public List<PhotoBanner> findBySubIdListAndMainId(List<Long> idList, Long mainId) {
		return photoBannerService.findBySubIdListAndMainId(idList, mainId);
	}

	@Override
	public PhotoBanner mergeForTitle(PhotoBanner photoBanner) {
		return photoBannerService.mergeForTitle(photoBanner);
	}

	@Override
	public List<PhotoBanner> mergeListForTitle(List<PhotoBanner> photoBannerList){
		return photoBannerService.mergeListForTitle(photoBannerList);
	}
	
	public List<PhotoBanner> persistForPhoto(List<PhotoBanner> photoBannerList){
		return photoBannerService.persistForPhoto(photoBannerList);
	}
	
	public Map<Long, List<PhotoBanner>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId) {
		
		return photoBannerService.findByMainIdListAndSubId( mainIdList,  subId);
	}


}
