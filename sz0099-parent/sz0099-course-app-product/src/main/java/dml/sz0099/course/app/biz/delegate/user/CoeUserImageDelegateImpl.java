package dml.sz0099.course.app.biz.delegate.user;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.user.CoeUserImageService;
import dml.sz0099.course.app.persist.entity.user.CoeUserImage;

/**
 * coeUserImageServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CoeUserImageDelegateImpl implements CoeUserImageDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserImageDelegateImpl.class);
	
	@Autowired
	private CoeUserImageService coeUserImageService;

	/**
	 * 根据Id查询CoeUserImage实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserImage findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeUserImage coeUserImage = coeUserImageService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, coeUserImage);
		return coeUserImage;
	}
	
	@Override
	public CoeUserImage findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserImage coeUserImage = coeUserImageService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserImage);
		return coeUserImage;
	}
	
	/**
	 * 根据IdList查询CoeUserImage实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeUserImage> coeUserImageList = coeUserImageService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  coeUserImageList);
		return coeUserImageList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeUserImage persistEntity(CoeUserImage coeUserImage) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeUserImage entity = coeUserImageService.persistEntity(coeUserImage);
		Long id = coeUserImage.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserImage mergeEntity(CoeUserImage coeUserImage) {
		Long id = coeUserImage.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CoeUserImage entity = coeUserImageService.mergeEntity(coeUserImage);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserImage saveOrUpdate(CoeUserImage coeUserImage) {
		Long id = coeUserImage.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserImage entity = coeUserImageService.saveOrUpdate(coeUserImage);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserImage> findPage(CoeUserImage coeUserImage, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CoeUserImage> page = coeUserImageService.findPage(coeUserImage, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeUserImageService.existById(id);
	}
	
	public void deleteImage(CoeUserImage userImage) {
		coeUserImageService.deleteImage(userImage);
	}
}
