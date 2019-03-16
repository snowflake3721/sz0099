package dml.sz0099.course.app.code.persist.dao.template;

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

import dml.sz0099.course.app.code.persist.entity.template.Demo;
import dml.sz0099.course.app.code.persist.repository.template.DemoRepository;
import dml.sz0099.course.app.code.persist.specification.template.DemoSpecification;

/**
 * DemoDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class DemoDaoImpl extends GenericDaoImpl<Demo, Long> implements DemoDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoDaoImpl.class);

	@Autowired
	private DemoRepository demoRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = demoRepository;
	}

	/**
	 * 根据Id查询Demo实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Demo findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		Demo demo = demoRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, demo);
		return demo;
	}

	@Override
	public Demo findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		Demo demo = demoRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, demo);
		return demo;
	}

	/**
	 * 根据IdList查询Demo实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<Demo> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<Demo> demoList = demoRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", demoList);
		return demoList;
	}

	/**
	 * 条件查询
	 */
	public Page<Demo> findPage(Demo demo, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = DemoSpecification.getConditionWithQsl(demo);
		Page<Demo> page = demoRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		Demo entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}

}
