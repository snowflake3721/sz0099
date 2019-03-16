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

import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;
import dml.sz0099.course.app.persist.repository.profession.ProfessionImageRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionImageSpecification;

/**
 * ProfessionImageDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ProfessionImageDaoImpl extends GenericDaoImpl<ProfessionImage, Long> implements ProfessionImageDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionImageDaoImpl.class);

	@Autowired
	private ProfessionImageRepository professionImageRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionImageRepository;
	}

	/**
	 * 根据Id查询ProfessionImage实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionImage findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ProfessionImage professionImage = professionImageRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, professionImage);
		return professionImage;
	}

	@Override
	public ProfessionImage findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionImage professionImage = professionImageRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, professionImage);
		return professionImage;
	}

	/**
	 * 根据IdList查询ProfessionImage实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ProfessionImage> professionImageList = professionImageRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", professionImageList);
		return professionImageList;
	}

	/**
	 * 条件查询
	 */
	public Page<ProfessionImage> findPage(ProfessionImage professionImage, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionImageSpecification.getConditionWithQsl(professionImage);
		Page<ProfessionImage> page = professionImageRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ProfessionImage entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public List<ProfessionImage> findByRefIdList(List<Long> refIdList) {
		List<ProfessionImage> refList = professionImageRepository.findByRefIdList(refIdList);
		return refList;
	}
	
	public void deleteByRefIdList(List<Long> refIdList) {
		professionImageRepository.deleteByRefIdList(refIdList);
	}
	
	@Override
	public void deleteByRefId(Long refId) {
		professionImageRepository.deleteByRefId(refId);
	}
	
	public List<ProfessionImage> findByRefId(Long refId) {
		return professionImageRepository.findByRefId(refId);
	}

}
