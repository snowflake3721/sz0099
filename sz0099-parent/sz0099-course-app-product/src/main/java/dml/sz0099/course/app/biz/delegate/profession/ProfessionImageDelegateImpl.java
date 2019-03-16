package dml.sz0099.course.app.biz.delegate.profession;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.profession.ProfessionImageService;
import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;

/**
 * professionImageServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ProfessionImageDelegateImpl implements ProfessionImageDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionImageDelegateImpl.class);
	
	@Autowired
	private ProfessionImageService professionImageService;

	/**
	 * 根据Id查询ProfessionImage实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionImage findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ProfessionImage professionImage = professionImageService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, professionImage);
		return professionImage;
	}
	
	@Override
	public ProfessionImage findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionImage professionImage = professionImageService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, professionImage);
		return professionImage;
	}
	
	/**
	 * 根据IdList查询ProfessionImage实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ProfessionImage> professionImageList = professionImageService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  professionImageList);
		return professionImageList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ProfessionImage persistEntity(ProfessionImage professionImage) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ProfessionImage entity = professionImageService.persistEntity(professionImage);
		Long id = professionImage.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionImage mergeEntity(ProfessionImage professionImage) {
		Long id = professionImage.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ProfessionImage entity = professionImageService.mergeEntity(professionImage);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionImage saveOrUpdate(ProfessionImage professionImage) {
		Long id = professionImage.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionImage entity = professionImageService.saveOrUpdate(professionImage);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionImage> findPage(ProfessionImage professionImage, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ProfessionImage> page = professionImageService.findPage(professionImage, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionImageService.existById(id);
	}
	
	public List<ProfessionImage> findByRefId(Long refId) {
		return professionImageService.findByRefId(refId);
	}
}
