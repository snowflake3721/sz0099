package dml.sz0099.course.app.persist.dao.article;

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

import dml.sz0099.course.app.persist.entity.article.PhotoCover;
import dml.sz0099.course.app.persist.repository.article.PhotoCoverRepository;
import dml.sz0099.course.app.persist.specification.article.PhotoCoverSpecification;

/**
 * PhotoCoverDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository("articlePhotoCoverDaoImpl")
public class PhotoCoverDaoImpl extends GenericDaoImpl<PhotoCover, Long> implements PhotoCoverDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoCoverDaoImpl.class);

	@Autowired
	private PhotoCoverRepository photoCoverRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = photoCoverRepository;
	}

	/**
	 * 根据Id查询PhotoCover实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PhotoCover findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		PhotoCover photoCover = photoCoverRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, photoCover);
		return photoCover;
	}

	@Override
	public PhotoCover findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		PhotoCover photoCover = photoCoverRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, photoCover);
		return photoCover;
	}

	/**
	 * 根据IdList查询PhotoCover实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<PhotoCover> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<PhotoCover> photoCoverList = photoCoverRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", photoCoverList);
		return photoCoverList;
	}

	/**
	 * 条件查询
	 */
	public Page<PhotoCover> findPage(PhotoCover photoCover, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = PhotoCoverSpecification.getConditionWithQsl(photoCover);
		Page<PhotoCover> page = photoCoverRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		PhotoCover entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public List<PhotoCover> findByMainId(Long photoCoverId) {
		List<PhotoCover> content = photoCoverRepository.findByMainId(photoCoverId);
		return content;
	}
	
	public List<PhotoCover> findByMainIdAndSubId(Long photoCoverId, Long subId){
		List<PhotoCover> content = photoCoverRepository.findByMainIdAndSubId(photoCoverId,subId);
		return content;
	}
	

	public void deleteBySubIdAndMainId(Long subId, Long mainId ){		

		LOGGER.debug("-------dao>>>PhotoCoverDaoImpl.deleteBySubIdAndMainId----------begin---------");
		photoCoverRepository.deleteBySubIdAndMainId( subId,  mainId );
	}
	
	public List<PhotoCover> findBySubIdListAndMainId(List<Long> idList, Long mainId) {
		List<PhotoCover> content = photoCoverRepository.findBySubIdListAndMainId(idList, mainId);
		return content;
	}

	@Override
	public void deleteBySubIdListAndMainId(List<Long> subIdList, Long mainId) {
		photoCoverRepository.deleteBySubIdListAndMainId(subIdList, mainId);
	}

	@Override
	public void deleteById(Long id) {
		photoCoverRepository.deleteById(id);
		
	}

	@Override
	public List<PhotoCover> findByMainIdListAndSubId(List<Long> mainIdList, Long subId) {
		List<PhotoCover> content = photoCoverRepository.findByMainIdListAndSubId(mainIdList, subId);
		return content;
	}
	
	public Long countByMainId(Long mainId) {
		return photoCoverRepository.countByMainId(mainId);
	}

}
