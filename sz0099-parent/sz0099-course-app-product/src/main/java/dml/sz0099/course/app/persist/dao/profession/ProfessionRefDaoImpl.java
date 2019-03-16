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

import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;
import dml.sz0099.course.app.persist.repository.profession.ProfessionRefRepository;
import dml.sz0099.course.app.persist.specification.profession.ProfessionRefSpecification;

/**
 * <pre>
 * @formatter:off
 *
 * 数据访问封装
 * @author bruce yang at 2018-09-10 19:52:40
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-10	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Repository
public class ProfessionRefDaoImpl extends GenericDaoImpl<ProfessionRef, Long> implements ProfessionRefDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionRefDaoImpl.class);

	@Autowired
	private ProfessionRefRepository professionRefRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = professionRefRepository;
	}

	/**
	 * 根据Id查询ProfessionRef实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionRef findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ProfessionRef professionRef = professionRefRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, professionRef);
		return professionRef;
	}

	@Override
	public ProfessionRef findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionRef professionRef = professionRefRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, professionRef);
		return professionRef;
	}

	/**
	 * 根据IdList查询ProfessionRef实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionRef> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ProfessionRef> professionRefList = professionRefRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", professionRefList);
		return professionRefList;
	}

	/**
	 * 条件查询
	 */
	public Page<ProfessionRef> findPage(ProfessionRef professionRef, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ProfessionRefSpecification.getConditionWithQsl(professionRef);
		Page<ProfessionRef> page = professionRefRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ProfessionRef entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public void deleteByBaseId(Long baseId) {
		professionRefRepository.deleteByBaseId(baseId);
	}

	public void deleteByBaseIdList(List<Long> baseIdList) {
		professionRefRepository.deleteByBaseIdList(baseIdList);
	}
	public Long countForBase(ProfessionRef professionRef) {
		Long baseId = professionRef.getBaseId();
		return professionRefRepository.countByBaseId(baseId);
	}

	@Override
	public List<ProfessionRef> findByMainId(Long mainId) {
		return professionRefRepository.findByMainId(mainId);
	}
	
	public List<ProfessionRef> findByBaseId(Long baseId){
		return professionRefRepository.findByBaseId(baseId);
	}
	
	public void deleteByMainId(Long mainId) {
		professionRefRepository.deleteByMainId(mainId);
	}

	@Override
	public Page<ProfessionRef> findPageByBaseId(Long baseId, Pageable pageable) {
		return professionRefRepository.findPageByBaseId(baseId, pageable);
	}
	
	public List<ProfessionRef> findByBaseIdList(List<Long> baseIdList) {
		List<ProfessionRef> entityList = professionRefRepository.findByBaseIdList(baseIdList);
		return entityList;
	}
	
	public List<ProfessionRef> findByBaseIdAndMainIdList(Long baseId, List<Long> mainIdList) {
		return professionRefRepository.findByBaseIdAndMainIdList( baseId, mainIdList) ;
	}
	
	public ProfessionRef findMainIdAndBaseId(ProfessionRef professionRef) {
		Long baseId = professionRef.getBaseId();
		Long mainId = professionRef.getMainId();
		return professionRefRepository.findMainIdAndBaseId(mainId, baseId);
	}
	
	public ProfessionRef deleteRefByBaseId(ProfessionRef professionRef) {
		Long baseId = professionRef.getBaseId();
		professionRefRepository.deleteRefByBaseId(baseId);
		return professionRef;
	}
	
	

}
