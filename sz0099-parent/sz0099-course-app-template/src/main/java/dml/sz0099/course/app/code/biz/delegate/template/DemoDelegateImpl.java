package dml.sz0099.course.app.code.biz.delegate.template;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.code.biz.service.template.DemoService;
import dml.sz0099.course.app.code.persist.entity.template.Demo;

/**
 * demoServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class DemoDelegateImpl implements DemoDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoDelegateImpl.class);
	
	@Autowired
	private DemoService demoService;

	/**
	 * 根据Id查询Demo实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Demo findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		Demo demo = demoService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, demo);
		return demo;
	}
	
	@Override
	public Demo findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		Demo demo = demoService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, demo);
		return demo;
	}
	
	/**
	 * 根据IdList查询Demo实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Demo> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<Demo> demoList = demoService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  demoList);
		return demoList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public Demo persistEntity(Demo demo) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		Demo entity = demoService.persistEntity(demo);
		Long id = demo.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Demo mergeEntity(Demo demo) {
		Long id = demo.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		Demo entity = demoService.mergeEntity(demo);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Demo saveOrUpdate(Demo demo) {
		Long id = demo.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		Demo entity = demoService.saveOrUpdate(demo);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Demo> findPage(Demo demo, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<Demo> page = demoService.findPage(demo, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return demoService.existById(id);
	}
}
