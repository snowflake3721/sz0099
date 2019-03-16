package dml.sz0099.course.app.persist.dao.media;

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

import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.repository.media.ImageRefRepository;
import dml.sz0099.course.app.persist.specification.media.ImageRefSpecification;

/**
 * ImageRefDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ImageRefDaoImpl extends GenericDaoImpl<ImageRef, Long> implements ImageRefDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageRefDaoImpl.class);

	@Autowired
	private ImageRefRepository imageRefRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = imageRefRepository;
	}

	/**
	 * 根据Id查询ImageRef实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ImageRef findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ImageRef imageRef = imageRefRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, imageRef);
		return imageRef;
	}

	@Override
	public ImageRef findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ImageRef imageRef = imageRefRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, imageRef);
		return imageRef;
	}

	/**
	 * 根据IdList查询ImageRef实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ImageRef> imageRefList = imageRefRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", imageRefList);
		return imageRefList;
	}

	/**
	 * 条件查询
	 */
	public Page<ImageRef> findPage(ImageRef imageRef, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ImageRefSpecification.getConditionWithQsl(imageRef);
		Page<ImageRef> page = imageRefRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ImageRef entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public Long countForMain(ImageRef imageRef) {
		Long extendId = imageRef.getExtendId();
		Long mainId = imageRef.getMainId();
		return imageRefRepository.countForMain(extendId,mainId);
	}
	
	public Long countForSub(ImageRef imageRef) {
		Long extendId = imageRef.getExtendId();
		Long mainId = imageRef.getMainId();
		Long subId = imageRef.getSubId();
		return imageRefRepository.countForSub(extendId,mainId, subId);
	}
	
	public Long countForBase(ImageRef imageRef) {
		Long baseId = imageRef.getBaseId();
		return imageRefRepository.countForBase(baseId);
	}
	
	/**
	 * @param extendId
	 * @param mainId
	 * @return
	 */
	public List<ImageRef> findByExtendIdAndMainId(Long extendId, Long mainId) {

		List<ImageRef> content = imageRefRepository.findByExtendIdAndMainId(extendId, mainId);
		return content;
	}

	/**
	 * @param extendId
	 * @param mainId
	 * @param subId
	 * @return
	 */
	public List<ImageRef> findByExtendIdAndMainId(Long extendId, Long mainId, Long subId) {
		List<ImageRef> content = imageRefRepository.findByExtendIdAndMainId(extendId, mainId, subId);
		return content;
	}

}
