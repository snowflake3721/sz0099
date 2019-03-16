package dml.sz0099.course.app.biz.delegate.article;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.article.PhotoCoverService;
import dml.sz0099.course.app.persist.entity.article.PhotoCover;
import dml.sz0099.course.app.persist.entity.article.PhotoCover;
import dml.sz0099.course.app.persist.entity.article.PhotoCover;
import dml.sz0099.course.app.persist.entity.article.PhotoCover;
import dml.sz0099.course.app.persist.entity.article.bo.PhotoCoverBo;

/**
 * photoCoverServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ArticlePhotoCoverDelegateImpl implements ArticlePhotoCoverDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticlePhotoCoverDelegateImpl.class);
	
	@Autowired
	private PhotoCoverService photoCoverService;

	/**
	 * 根据Id查询PhotoCover实体对象
	 * @param id
	 * @return
	 */
	@Override
	public PhotoCover findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		PhotoCover photoCover = photoCoverService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, photoCover);
		return photoCover;
	}
	
	public PhotoCover findById(Long id, boolean withPhoto) {
		return photoCoverService.findById(id,withPhoto);
	}
	
	@Override
	public PhotoCover findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		PhotoCover photoCover = photoCoverService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, photoCover);
		return photoCover;
	}
	
	/**
	 * 根据IdList查询PhotoCover实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<PhotoCover> photoCoverList = photoCoverService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  photoCoverList);
		return photoCoverList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public PhotoCover persistEntity(PhotoCover photoCover) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		PhotoCover entity = photoCoverService.persistEntity(photoCover);
		Long id = photoCover.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoCover mergeEntity(PhotoCover photoCover) {
		Long id = photoCover.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		PhotoCover entity = photoCoverService.mergeEntity(photoCover);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public PhotoCover saveOrUpdate(PhotoCover photoCover) {
		Long id = photoCover.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		PhotoCover entity = photoCoverService.saveOrUpdate(photoCover);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<PhotoCover> findPage(PhotoCover photoCover, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<PhotoCover> page = photoCoverService.findPage(photoCover, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return photoCoverService.existById(id);
	}


	public void deleteBySubIdAndMainId(Long paragId, Long mainId ){		

		LOGGER.debug("-------delegate>>>PhotoCoverDelegateImpl.deleteBySubIdAndMainId----------begin---------");
		photoCoverService.deleteBySubIdAndMainId( paragId,  mainId );
	}

	@Override
	public void deleteBySubIdListAndMainId(List<Long> paragIdList, Long mainId, Long userId, boolean cascade) {
		photoCoverService.deleteBySubIdListAndMainId(paragIdList, mainId, userId, cascade);
	}

	@Override
	public void deleteById(PhotoCover photoCover) {
		photoCoverService.deleteById(photoCover);
	}
	
	public void deleteById(PhotoCover photoCover, boolean cascade) {
		photoCoverService.deleteById(photoCover, cascade);
	}

	@Override
	public void deleteByIdList(PhotoCoverBo photoCover) {
		photoCoverService.deleteByIdList(photoCover);
	}
	
	public void deleteImageById(PhotoCover photoCover, boolean cascade) {
		photoCoverService.deleteImageById(photoCover, cascade);
	}
	
	public PhotoCover addImageById(PhotoCover photoCover, boolean cascade) {
		return photoCoverService.addImageById(photoCover, cascade);
	}

	@Override
	public PhotoCover createPhotoCover(PhotoCover photoCover) {
		return photoCoverService.createPhotoCover(photoCover);
	}

	@Override
	public List<PhotoCover> createPhotoCover(List<PhotoCover> photoCoverList) {
		return photoCoverService.createPhotoCover(photoCoverList);
	}

	@Override
	public List<PhotoCover> findBySubIdListAndMainId(List<Long> idList, Long mainId) {
		return photoCoverService.findBySubIdListAndMainId(idList, mainId);
	}

	@Override
	public PhotoCover mergeForTitle(PhotoCover photoCover) {
		return photoCoverService.mergeForTitle(photoCover);
	}

	@Override
	public List<PhotoCover> mergeListForTitle(List<PhotoCover> photoCoverList){
		return photoCoverService.mergeListForTitle(photoCoverList);
	}
	
	public List<PhotoCover> persistForPhoto(List<PhotoCover> photoCoverList){
		return photoCoverService.persistForPhoto(photoCoverList);
	}
	
	public Map<Long, List<PhotoCover>> findByMainIdListAndSubId(List<Long> mainIdList, Long subId) {
		
		return photoCoverService.findByMainIdListAndSubId( mainIdList,  subId);
	}
	
	public PhotoCover mergeRefForDescription(PhotoCover photoCover) {
		
		return photoCoverService.mergeRefForDescription(photoCover);
	}
	public PhotoCover mergeRefForOrder(PhotoCover photoCover) {
		
		return photoCoverService.mergeRefForOrder(photoCover);
	}
	
	public Long countByMainId(Long mainId) {
		return photoCoverService.countByMainId(mainId);
	}


}
