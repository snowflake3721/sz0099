package dml.sz0099.course.app.persist.dao.user;

import java.io.Serializable;
import java.util.Date;
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

import dml.sz0099.course.app.persist.entity.user.Sayword;
import dml.sz0099.course.app.persist.repository.user.SaywordRepository;
import dml.sz0099.course.app.persist.specification.user.SaywordSpecification;

/**
 * SaywordDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class SaywordDaoImpl extends GenericDaoImpl<Sayword, Long> implements SaywordDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaywordDaoImpl.class);

	@Autowired
	private SaywordRepository saywordRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = saywordRepository;
	}

	/**
	 * 根据Id查询Sayword实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Sayword findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		Sayword sayword = saywordRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, sayword);
		return sayword;
	}

	@Override
	public Sayword findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		Sayword sayword = saywordRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, sayword);
		return sayword;
	}

	/**
	 * 根据IdList查询Sayword实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Sayword> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<Sayword> saywordList = saywordRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", saywordList);
		return saywordList;
	}

	/**
	 * 条件查询
	 */
	public Page<Sayword> findPage(Sayword sayword, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = SaywordSpecification.getConditionWithQsl(sayword);
		Page<Sayword> page = saywordRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		Sayword entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public List<Sayword> findByUserIdAndMainId(Long userId, Integer mainType) {
		return saywordRepository.findByUserIdAndMainId(userId, mainType);
	}
	
	public List<Sayword> findByUserIdAndPublishTime(Long userId, Date begin, Date end) {
		return saywordRepository.findByUserIdAndPublishTime(userId, begin, end);
	}

}
