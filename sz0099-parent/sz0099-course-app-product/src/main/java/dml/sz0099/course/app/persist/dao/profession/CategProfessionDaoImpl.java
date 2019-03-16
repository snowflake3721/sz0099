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

import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.repository.profession.CategProfessionRepository;
import dml.sz0099.course.app.persist.specification.profession.CategProfessionSpecification;

/**
 * CategProfessionDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CategProfessionDaoImpl extends GenericDaoImpl<CategProfession, Long> implements CategProfessionDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategProfessionDaoImpl.class);

	@Autowired
	private CategProfessionRepository categProfessionRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = categProfessionRepository;
	}

	/**
	 * 根据Id查询CategProfession实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CategProfession findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CategProfession categProfession = categProfessionRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, categProfession);
		return categProfession;
	}

	@Override
	public CategProfession findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CategProfession categProfession = categProfessionRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, categProfession);
		return categProfession;
	}

	/**
	 * 根据IdList查询CategProfession实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategProfession> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CategProfession> categProfessionList = categProfessionRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", categProfessionList);
		return categProfessionList;
	}

	/**
	 * 条件查询
	 */
	public Page<CategProfession> findPage(CategProfession categProfession, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CategProfessionSpecification.getConditionWithQsl(categProfession,null);
		Page<CategProfession> page = categProfessionRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CategProfession entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

	@Override
	public List<CategProfession> findByMainIdList(List<Long> productIdList) {
		return categProfessionRepository.findByMainIdList(productIdList);
	}
	
	public List<CategProfession> findByMainId(Long productId){
		return categProfessionRepository.findByMainId(productId);
	}
	
	public Page<CategProfession> findPageForPublish(CategProfession categProfession, Pageable pageable) {
		BooleanExpression condition = CategProfessionSpecification.getConditionWithQsl(categProfession, null);
		return categProfessionRepository.findAll(condition, pageable);
	}
	
	public Page<CategProfession> findPageForPublishFromDetail(CategProfession categProfession, Pageable pageable) {
		BooleanExpression condition = CategProfessionSpecification.getConditionFromDetail(categProfession);
		return categProfessionRepository.findAll(condition, pageable);
	}
	
	public Page<CategProfession> findPageForPublish(CategProfession categProfession, List<Long> excludeMainIdList, Pageable pageable) {
		BooleanExpression condition = CategProfessionSpecification.getConditionWithQsl(categProfession, excludeMainIdList);
		return categProfessionRepository.findAll(condition, pageable);
	}
	
	public Page<CategProfession> findPageForPublishWithChildren(CategProfession categProfession,List<Long> baseIdList, List<Long> excludeMainIdList, Pageable pageable) {
		
		BooleanExpression condition = CategProfessionSpecification.getConditionWithChilren(categProfession, baseIdList, excludeMainIdList);
		return categProfessionRepository.findAll(condition, pageable);
	}

}
