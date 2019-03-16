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

import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionFavirate;
import dml.sz0099.course.app.persist.repository.profession.ProfessionRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionSpecification;

/**
 * ProfessionDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ProfessionDaoImpl extends GenericDaoImpl<Profession, Long> implements ProfessionDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionDaoImpl.class);

	@Autowired
	private ProfessionRepository professionRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionRepository;
	}

	/**
	 * 根据Id查询Profession实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Profession findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		Profession profession = professionRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, profession);
		return profession;
	}

	@Override
	public Profession findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		Profession profession = professionRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, profession);
		return profession;
	}

	/**
	 * 根据IdList查询Profession实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Profession> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<Profession> professionList = professionRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", professionList);
		return professionList;
	}

	/**
	 * 条件查询
	 */
	public Page<Profession> findPage(Profession profession, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionSpecification.getConditionWithQsl(profession);
		Page<Profession> page = professionRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		Profession entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public Page<Profession> findPublished(Profession profession, Pageable pageable){
		
		Integer publishStatus = Profession.PUBLISH_STATUS_PUBLISH.getValueInt();
		profession.setPublishStatus(publishStatus);
		BooleanExpression condition = ProfessionSpecification.getConditionForPublish(profession);
		Page<Profession> page = professionRepository.findAll(condition, pageable);
		return page;
	}
	
	@Override
	public List<Profession> findDraftList(Profession profession) {
		Long userId = profession.getUserId();
		Integer publishStatus = Profession.PUBLISH_STATUS_DRAFT.getValueInt();
		List<Profession> content = professionRepository.findByUserIdAndPublishStatus(userId, publishStatus);
		return content;
	}
	
	public Long countDraftList(Profession profession) {
		Long userId = profession.getUserId();
		Integer publishStatus = Profession.PUBLISH_STATUS_DRAFT.getValueInt();
		return professionRepository.countByUserIdAndPublishStatus(userId, publishStatus);
	}
	
	public List<Profession> findByUserId(Long userId) {
		return professionRepository.findByUserId( userId) ;
	}
	
	public Long countByUserId(Long userId) {
		return professionRepository.countByUserId(userId);
	}
	
	public List<Profession> findPublishedByName(String name){
		return professionRepository.findPublishedByName(name);
	}
	
	public List<Profession> findPublishedByTitle(String title){
		return professionRepository.findPublishedByTitle(title);
	}
	
	public Page<Profession> findByPublished(Profession profession, Pageable pageable){
		BooleanExpression condition = ProfessionSpecification.getConditionForPublish(profession);
		Page<Profession> page = professionRepository.findAll( condition,  pageable);
		return page;
	}
	
	public Page<Profession> findByPublishedNotSelf(Profession profession, Pageable pageable){
		BooleanExpression condition = ProfessionSpecification.getConditionForPublishNotSelf(profession);
		Page<Profession> page = professionRepository.findAll( condition,  pageable);
		return page;
	}
	
	public Long countForPublishedWithoutSelf(Long userId, Long positionId) {
		Integer publishStatus = Profession.PUBLISH_STATUS_PUBLISH.getValueInt();
		return professionRepository.countForPublishedWithoutSelf( userId, positionId, publishStatus);
	}
	
	public Page<Profession> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable) {
		return professionRepository.findPageByMainTypeAndUserId( mainType, userIdList, publishStatus, pageable);
	}
	
	public List<Profession> findListByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus){
		return professionRepository.findListByMainTypeAndUserId(mainType,userIdList, publishStatus);
	}

	@Override
	public List<Profession> findByUserIdAndMainType(Integer mainType, Long userId, Integer publishStatus) {
		return professionRepository.findByUserIdAndMainType(mainType, userId, publishStatus);
	}
	
	public List<Profession> findByUserIdAndMainType(Integer mainType, Long userId){
		return professionRepository.findByUserIdAndMainType(mainType,userId);
	}

}
