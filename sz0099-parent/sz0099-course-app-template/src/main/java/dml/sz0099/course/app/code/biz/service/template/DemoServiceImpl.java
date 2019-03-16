package dml.sz0099.course.app.code.biz.service.template;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.code.persist.dao.template.DemoDao;
import dml.sz0099.course.app.code.persist.entity.template.Demo;


/**
 * 
 * @formatter:off
 * description: DemoServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class DemoServiceImpl extends GenericServiceImpl<Demo, Long> implements DemoService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

	@Autowired
	private DemoDao demoDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = demoDao;
	}

	/**
	 * 根据Id查询Demo实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Demo findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		Demo demo = demoDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, demo);
		return demo;
	}
	
	@Override
	public Demo findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		Demo demo = demoDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, demo);
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
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<Demo> demoList = demoDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", demoList);
		return demoList;
	}

	@Transactional
	@Override
	public Demo persistEntity(Demo demo) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		Demo entity = save(demo);
		Long id = demo.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(Demo.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public Demo mergeEntity(Demo demo) {
		Long id = demo.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		Demo entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(demo.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(Demo.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public Demo saveOrUpdate(Demo demo) {
		Long id = demo.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		Demo entity = null;
		if(null != id) {
			entity = mergeEntity(demo);
		}else {
			entity = persistEntity(demo);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Demo> findPage(Demo demo, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<Demo> page = demoDao.findPage(demo, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return demoDao.existById(id);
	}

}
