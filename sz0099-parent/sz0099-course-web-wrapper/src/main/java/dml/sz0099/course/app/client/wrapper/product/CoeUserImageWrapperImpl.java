package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.user.CoeUserImageDelegate;
import dml.sz0099.course.app.persist.entity.user.CoeUserImage;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeUserImageWrapperImpl,组件封装
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
public class CoeUserImageWrapperImpl implements CoeUserImageWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserImageWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeUserImageDelegate coeUserImageDelegate;
	
	/**
	 * 根据Id查询CoeUserImage实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserImage findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CoeUserImage coeUserImage = coeUserImageDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, coeUserImage);
		return coeUserImage;
	}
	
	@Override
	public CoeUserImage findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserImage coeUserImage = coeUserImageDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserImage);
		return coeUserImage;
	}
	
	/**
	 * 根据IdList查询CoeUserImage实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CoeUserImage> coeUserImageList = coeUserImageDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  coeUserImageList);
		return coeUserImageList;
	}
	
	@Override
	public CoeUserImage persistEntity(CoeUserImage coeUserImage) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CoeUserImage entity = coeUserImageDelegate.persistEntity(coeUserImage);
		Long id = coeUserImage.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserImage mergeEntity(CoeUserImage coeUserImage) {
		Long id = coeUserImage.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CoeUserImage entity = coeUserImageDelegate.mergeEntity(coeUserImage);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeUserImage saveOrUpdate(CoeUserImage coeUserImage) {
		Long id = coeUserImage.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CoeUserImage entity = coeUserImageDelegate.saveOrUpdate(coeUserImage);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeUserImage> findPage(CoeUserImage coeUserImage, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CoeUserImage> page = coeUserImageDelegate.findPage(coeUserImage, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return coeUserImageDelegate.existById(id);
	}

	@Override
	public void deleteImage(CoeUserImage userImage) {
		coeUserImageDelegate.deleteImage(userImage);
	}
}
