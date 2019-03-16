package dml.sz0099.course.app.biz.delegate.profession;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.profession.ProfessionExtendContentService;
import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendContent;

/**
 * professionExtendContentServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ProfessionExtendContentDelegateImpl implements ProfessionExtendContentDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionExtendContentDelegateImpl.class);
	
	@Autowired
	private ProfessionExtendContentService professionExtendContentService;

	/**
	 * 根据Id查询ProfessionExtendContent实体对象
	 * @param id
	 * @return
	 */
	@Override
	public ProfessionExtendContent findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		ProfessionExtendContent professionExtendContent = professionExtendContentService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, professionExtendContent);
		return professionExtendContent;
	}
	
	@Override
	public ProfessionExtendContent findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		ProfessionExtendContent professionExtendContent = professionExtendContentService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, professionExtendContent);
		return professionExtendContent;
	}
	
	/**
	 * 根据IdList查询ProfessionExtendContent实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProfessionExtendContent> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<ProfessionExtendContent> professionExtendContentList = professionExtendContentService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  professionExtendContentList);
		return professionExtendContentList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public ProfessionExtendContent persistEntity(ProfessionExtendContent professionExtendContent) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		ProfessionExtendContent entity = professionExtendContentService.persistEntity(professionExtendContent);
		Long id = professionExtendContent.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionExtendContent mergeEntity(ProfessionExtendContent professionExtendContent) {
		Long id = professionExtendContent.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		ProfessionExtendContent entity = professionExtendContentService.mergeEntity(professionExtendContent);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public ProfessionExtendContent saveOrUpdate(ProfessionExtendContent professionExtendContent) {
		Long id = professionExtendContent.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		ProfessionExtendContent entity = professionExtendContentService.saveOrUpdate(professionExtendContent);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<ProfessionExtendContent> findPage(ProfessionExtendContent professionExtendContent, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<ProfessionExtendContent> page = professionExtendContentService.findPage(professionExtendContent, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionExtendContentService.existById(id);
	}
}
