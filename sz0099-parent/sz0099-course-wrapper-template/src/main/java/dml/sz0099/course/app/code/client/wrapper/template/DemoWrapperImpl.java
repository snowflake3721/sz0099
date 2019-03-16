package dml.sz0099.course.app.code.client.wrapper.template;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.code.biz.delegate.template.DemoDelegate;
import dml.sz0099.course.app.code.persist.entity.template.Demo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * DemoWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class DemoWrapperImpl implements DemoWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private DemoDelegate demoDelegate;
	
	/**
	 * 根据Id查询Demo实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Demo findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		Demo demo = demoDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, demo);
		return demo;
	}
	
	@Override
	public Demo findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		Demo demo = demoDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, demo);
		return demo;
	}
	
	/**
	 * 根据IdList查询Demo实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Demo> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<Demo> demoList = demoDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  demoList);
		return demoList;
	}
	
	@Override
	public Demo persistEntity(Demo demo) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		Demo entity = demoDelegate.persistEntity(demo);
		Long id = demo.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Demo mergeEntity(Demo demo) {
		Long id = demo.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		Demo entity = demoDelegate.mergeEntity(demo);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Demo saveOrUpdate(Demo demo) {
		Long id = demo.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		Demo entity = demoDelegate.saveOrUpdate(demo);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Demo> findPage(Demo demo, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<Demo> page = demoDelegate.findPage(demo, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return demoDelegate.existById(id);
	}
}
