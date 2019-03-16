package dml.sz0099.course.app.persist.dao.media;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.repository.media.ImageExtendRepository;
import dml.sz0099.course.app.persist.specification.media.ImageExtendSpecification;

/**
 * ImageExtendDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ImageExtendDaoImpl extends GenericDaoImpl<ImageExtend, Long> implements ImageExtendDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendDaoImpl.class);

	@Autowired
	private ImageExtendRepository imageExtendRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = imageExtendRepository;
	}

	/**
	 * 根据Id查询ImageExtend实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ImageExtend findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ImageExtend imageExtend = imageExtendRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, imageExtend);
		return imageExtend;
	}

	@Override
	public ImageExtend findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ImageExtend imageExtend = imageExtendRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, imageExtend);
		return imageExtend;
	}

	/**
	 * 根据IdList查询ImageExtend实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageExtend> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ImageExtend> imageExtendList = imageExtendRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", imageExtendList);
		return imageExtendList;
	}

	/**
	 * 条件查询
	 */
	public Page<ImageExtend> findPage(ImageExtend imageExtend, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ImageExtendSpecification.getConditionWithQsl(imageExtend);
		Page<ImageExtend> page = imageExtendRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ImageExtend entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public ImageExtend findByPositionId(Long positionId) {
		return imageExtendRepository.findByPositionId(positionId);
	}
	
	public ImageExtend findImageExtend(ImageExtend extend) {
		String devId = extend.getDevId();
		String project = extend.getProject();
		String module = extend.getModule();
		String variety = extend.getVariety();
		String position = extend.getPosition();
		if(StringUtils.isNotBlank(devId)
				&& StringUtils.isNotBlank(project)
				&& StringUtils.isNotBlank(module)
				&& StringUtils.isNotBlank(variety)
				&& StringUtils.isNotBlank(position)
				) {
			return imageExtendRepository.findImageExtend(devId,project,module,variety,position);
		}
		return null;
	}

	@Override
	public Long findPositionIdById(Long id) {
		ImageExtend imageExtend = findById(id);
		Long positionId = null;
		if(null != imageExtend) {
			positionId = imageExtend.getPositionId();
		}
		return positionId;
	}

}
