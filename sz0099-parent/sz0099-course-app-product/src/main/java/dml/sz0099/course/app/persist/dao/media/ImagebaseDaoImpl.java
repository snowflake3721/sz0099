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

import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.repository.media.ImagebaseRepository;
import dml.sz0099.course.app.persist.specification.media.ImagebaseSpecification;

/**
 * ImagebaseDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ImagebaseDaoImpl extends GenericDaoImpl<Imagebase, Long> implements ImagebaseDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImagebaseDaoImpl.class);

	@Autowired
	private ImagebaseRepository imagebaseRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = imagebaseRepository;
	}

	/**
	 * 根据Id查询Imagebase实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Imagebase findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		Imagebase imagebase = imagebaseRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, imagebase);
		return imagebase;
	}

	@Override
	public Imagebase findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		Imagebase imagebase = imagebaseRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, imagebase);
		return imagebase;
	}

	/**
	 * 根据IdList查询Imagebase实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Imagebase> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<Imagebase> imagebaseList = imagebaseRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", imagebaseList);
		return imagebaseList;
	}

	/**
	 * 条件查询
	 */
	public Page<Imagebase> findPage(Imagebase imagebase, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ImagebaseSpecification.getConditionWithQsl(imagebase);
		Page<Imagebase> page = imagebaseRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		Imagebase entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
