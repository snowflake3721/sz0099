package dml.sz0099.course.app.persist.dao.profession;

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

import dml.sz0099.course.app.persist.entity.profession.Photo;
import dml.sz0099.course.app.persist.repository.profession.PhotoRepository;
import dml.sz0099.course.app.persist.specification.profession.PhotoSpecification;

/**
 * PhotoDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository("profPhotoDaoImpl")
public class PhotoDaoImpl extends GenericDaoImpl<Photo, Long> implements PhotoDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoDaoImpl.class);

	@Autowired
	private PhotoRepository photoRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = photoRepository;
	}

	/**
	 * 根据Id查询Photo实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Photo findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		Photo photo = photoRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, photo);
		return photo;
	}

	@Override
	public Photo findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		Photo photo = photoRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, photo);
		return photo;
	}

	/**
	 * 根据IdList查询Photo实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Photo> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<Photo> photoList = photoRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", photoList);
		return photoList;
	}

	/**
	 * 条件查询
	 */
	public Page<Photo> findPage(Photo photo, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = PhotoSpecification.getConditionWithQsl(photo);
		Page<Photo> page = photoRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		Photo entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}



	public void deleteByIdListAndUserId(List<Long> idList, Long userId ){		

		LOGGER.debug("-------dao>>>PhotoDaoImpl.deleteByIdListAndUserId----------begin---------");

		photoRepository.deleteByIdListAndUserId( idList , userId);

		LOGGER.info("-------dao>>>PhotoDaoImpl.deleteByIdListAndUserId----------end---------");

	}


}
