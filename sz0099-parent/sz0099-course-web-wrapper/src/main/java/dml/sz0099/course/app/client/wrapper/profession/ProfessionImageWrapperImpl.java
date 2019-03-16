package dml.sz0099.course.app.client.wrapper.profession;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.profession.ProfessionImageDelegate;
import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * ProfessionImageWrapperImpl,组件封装
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
public class ProfessionImageWrapperImpl implements ProfessionImageWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionImageWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private ProfessionImageDelegate professionImageDelegate;
	
	/**
	 * 根据Id查询ProfessionImage实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionImage findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		ProfessionImage professionImage = professionImageDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, professionImage);
		return professionImage;
	}
	
	@Override
	public ProfessionImage findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionImage professionImage = professionImageDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, professionImage);
		return professionImage;
	}
	
	/**
	 * 根据IdList查询ProfessionImage实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<ProfessionImage> professionImageList = professionImageDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  professionImageList);
		return professionImageList;
	}
	
	@Override
	public ProfessionImage persistEntity(ProfessionImage professionImage) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		ProfessionImage entity = professionImageDelegate.persistEntity(professionImage);
		Long id = professionImage.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionImage mergeEntity(ProfessionImage professionImage) {
		Long id = professionImage.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		ProfessionImage entity = professionImageDelegate.mergeEntity(professionImage);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionImage saveOrUpdate(ProfessionImage professionImage) {
		Long id = professionImage.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionImage entity = professionImageDelegate.saveOrUpdate(professionImage);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionImage> findPage(ProfessionImage professionImage, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<ProfessionImage> page = professionImageDelegate.findPage(professionImage, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return professionImageDelegate.existById(id);
	}
	
	public List<ProfessionImage> findByRefId(Long refId) {
		return professionImageDelegate.findByRefId(refId);
	}
}
