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

import dml.sz0099.course.app.persist.entity.activity.PhotoBanner;
import dml.sz0099.course.app.persist.repository.activity.PhotoBannerRepository;
import dml.sz0099.course.app.persist.specification.activity.PhotoBannerSpecification;

/**
 * PhotoBannerDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository("activityPhotoBannerDaoImpl")
public class PhotoBannerDaoImpl extends GenericDaoImpl<PhotoBanner, Long> implements PhotoBannerDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoBannerDaoImpl.class);

	@Autowired
	private PhotoBannerRepository photoBannerRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = photoBannerRepository;
	}

	/**
	 * 根据Id查询PhotoBanner实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PhotoBanner findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		PhotoBanner photoBanner = photoBannerRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, photoBanner);
		return photoBanner;
	}

	@Override
	public PhotoBanner findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		PhotoBanner photoBanner = photoBannerRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, photoBanner);
		return photoBanner;
	}

	/**
	 * 根据IdList查询PhotoBanner实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoBanner> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<PhotoBanner> photoBannerList = photoBannerRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", photoBannerList);
		return photoBannerList;
	}

	/**
	 * 条件查询
	 */
	public Page<PhotoBanner> findPage(PhotoBanner photoBanner, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = PhotoBannerSpecification.getConditionWithQsl(photoBanner);
		Page<PhotoBanner> page = photoBannerRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		PhotoBanner entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public List<PhotoBanner> findByMainId(Long photoBannerId) {
		List<PhotoBanner> content = photoBannerRepository.findByMainId(photoBannerId);
		return content;
	}
	
	public List<PhotoBanner> findByMainIdAndSubId(Long photoBannerId, Long subId){
		List<PhotoBanner> content = photoBannerRepository.findByMainIdAndSubId(photoBannerId,subId);
		return content;
	}
	

	public void deleteBySubIdAndMainId(Long subId, Long mainId ){		

		LOGGER.debug("-------dao>>>PhotoBannerDaoImpl.deleteBySubIdAndMainId----------begin---------");
		photoBannerRepository.deleteBySubIdAndMainId( subId,  mainId );
	}
	
	public List<PhotoBanner> findBySubIdListAndMainId(List<Long> idList, Long mainId) {
		List<PhotoBanner> content = photoBannerRepository.findBySubIdListAndMainId(idList, mainId);
		return content;
	}

	@Override
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId) {
		photoBannerRepository.deleteBySubIdListAndMainId(subIdList, mainId);
	}

	@Override
	public void deleteById(Long id) {
		photoBannerRepository.deleteById(id);
		
	}

	@Override
	public List<PhotoBanner> findByMainIdListAndSubId(List<Long> mainIdList, Long subId) {
		List<PhotoBanner> content = photoBannerRepository.findByMainIdListAndSubId(mainIdList, subId);
		return content;
	}
	
	public Long countByMainId(Long mainId) {
		return photoBannerRepository.countByMainId(mainId);
	}

}
