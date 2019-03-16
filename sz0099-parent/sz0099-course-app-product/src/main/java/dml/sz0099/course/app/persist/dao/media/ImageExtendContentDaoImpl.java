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

import dml.sz0099.course.app.persist.entity.media.ImageExtendContent;
import dml.sz0099.course.app.persist.repository.media.ImageExtendContentRepository;
import dml.sz0099.course.app.persist.specification.media.ImageExtendContentSpecification;

/**
 * ImageExtendContentDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ImageExtendContentDaoImpl extends GenericDaoImpl<ImageExtendContent, Long> implements ImageExtendContentDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendContentDaoImpl.class);

	@Autowired
	private ImageExtendContentRepository imageExtendContentRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = imageExtendContentRepository;
	}

	/**
	 * 根据Id查询ImageExtendContent实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ImageExtendContent findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ImageExtendContent imageExtendContent = imageExtendContentRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, imageExtendContent);
		return imageExtendContent;
	}

	@Override
	public ImageExtendContent findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ImageExtendContent imageExtendContent = imageExtendContentRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, imageExtendContent);
		return imageExtendContent;
	}

	/**
	 * 根据IdList查询ImageExtendContent实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ImageExtendContent> imageExtendContentList = imageExtendContentRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", imageExtendContentList);
		return imageExtendContentList;
	}

	/**
	 * 条件查询
	 */
	public Page<ImageExtendContent> findPage(ImageExtendContent imageExtendContent, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ImageExtendContentSpecification.getConditionWithQsl(imageExtendContent);
		Page<ImageExtendContent> page = imageExtendContentRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ImageExtendContent entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
