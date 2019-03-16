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

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendContent;
import dml.sz0099.course.app.persist.repository.profession.ProfessionExtendContentRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionExtendContentSpecification;

/**
 * ProfessionExtendContentDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ProfessionExtendContentDaoImpl extends GenericDaoImpl<ProfessionExtendContent, Long> implements ProfessionExtendContentDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionExtendContentDaoImpl.class);

	@Autowired
	private ProfessionExtendContentRepository professionExtendContentRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionExtendContentRepository;
	}

	/**
	 * 根据Id查询ProfessionExtendContent实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionExtendContent findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ProfessionExtendContent professionExtendContent = professionExtendContentRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, professionExtendContent);
		return professionExtendContent;
	}

	@Override
	public ProfessionExtendContent findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionExtendContent professionExtendContent = professionExtendContentRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, professionExtendContent);
		return professionExtendContent;
	}

	/**
	 * 根据IdList查询ProfessionExtendContent实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ProfessionExtendContent> professionExtendContentList = professionExtendContentRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", professionExtendContentList);
		return professionExtendContentList;
	}

	/**
	 * 条件查询
	 */
	public Page<ProfessionExtendContent> findPage(ProfessionExtendContent professionExtendContent, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionExtendContentSpecification.getConditionWithQsl(professionExtendContent);
		Page<ProfessionExtendContent> page = professionExtendContentRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ProfessionExtendContent entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
