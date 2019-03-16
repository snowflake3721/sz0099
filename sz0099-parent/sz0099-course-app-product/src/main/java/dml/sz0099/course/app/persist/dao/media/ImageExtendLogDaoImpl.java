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

import dml.sz0099.course.app.persist.entity.media.ImageExtendLog;
import dml.sz0099.course.app.persist.repository.media.ImageExtendLogRepository;
import dml.sz0099.course.app.persist.specification.media.ImageExtendLogSpecification;

/**
 * ImageExtendLogDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ImageExtendLogDaoImpl extends GenericDaoImpl<ImageExtendLog, Long> implements ImageExtendLogDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageExtendLogDaoImpl.class);

	@Autowired
	private ImageExtendLogRepository imageExtendLogRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = imageExtendLogRepository;
	}

	/**
	 * 根据Id查询ImageExtendLog实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ImageExtendLog findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ImageExtendLog imageExtendLog = imageExtendLogRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, imageExtendLog);
		return imageExtendLog;
	}

	@Override
	public ImageExtendLog findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ImageExtendLog imageExtendLog = imageExtendLogRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, imageExtendLog);
		return imageExtendLog;
	}

	/**
	 * 根据IdList查询ImageExtendLog实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ImageExtendLog> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ImageExtendLog> imageExtendLogList = imageExtendLogRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", imageExtendLogList);
		return imageExtendLogList;
	}

	/**
	 * 条件查询
	 */
	public Page<ImageExtendLog> findPage(ImageExtendLog imageExtendLog, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ImageExtendLogSpecification.getConditionWithQsl(imageExtendLog);
		Page<ImageExtendLog> page = imageExtendLogRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ImageExtendLog entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
