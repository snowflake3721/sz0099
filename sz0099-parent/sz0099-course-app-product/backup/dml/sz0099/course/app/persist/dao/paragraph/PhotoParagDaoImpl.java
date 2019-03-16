package dml.sz0099.course.app.persist.dao.paragraph;

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

import dml.sz0099.course.app.persist.entity.paragraph.PhotoParag;
import dml.sz0099.course.app.persist.repository.paragraph.PhotoParagRepository;
import dml.sz0099.course.app.persist.specification.paragraph.PhotoParagSpecification;

/**
 * PhotoParagDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class PhotoParagDaoImpl extends GenericDaoImpl<PhotoParag, Long> implements PhotoParagDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoParagDaoImpl.class);

	@Autowired
	private PhotoParagRepository photoParagRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = photoParagRepository;
	}

	/**
	 * 根据Id查询PhotoParag实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PhotoParag findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		PhotoParag photoParag = photoParagRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, photoParag);
		return photoParag;
	}

	@Override
	public PhotoParag findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		PhotoParag photoParag = photoParagRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, photoParag);
		return photoParag;
	}

	/**
	 * 根据IdList查询PhotoParag实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoParag> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<PhotoParag> photoParagList = photoParagRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", photoParagList);
		return photoParagList;
	}

	/**
	 * 条件查询
	 */
	public Page<PhotoParag> findPage(PhotoParag photoParag, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = PhotoParagSpecification.getConditionWithQsl(photoParag);
		Page<PhotoParag> page = photoParagRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		PhotoParag entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
