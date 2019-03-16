package dml.sz0099.course.app.biz.delegate.profession;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.profession.CategProfessionService;
import dml.sz0099.course.app.persist.entity.profession.CategProfession;

/**
 * categProfessionServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CategProfessionDelegateImpl implements CategProfessionDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategProfessionDelegateImpl.class);
	
	@Autowired
	private CategProfessionService categProfessionService;

	/**
	 * 根据Id查询CategProfession实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CategProfession findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CategProfession categProfession = categProfessionService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, categProfession);
		return categProfession;
	}
	
	@Override
	public CategProfession findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		CategProfession categProfession = categProfessionService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, categProfession);
		return categProfession;
	}
	
	/**
	 * 根据IdList查询CategProfession实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategProfession> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CategProfession> categProfessionList = categProfessionService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  categProfessionList);
		return categProfessionList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CategProfession persistEntity(CategProfession categProfession) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CategProfession entity = categProfessionService.persistEntity(categProfession);
		Long id = categProfession.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategProfession mergeEntity(CategProfession categProfession) {
		Long id = categProfession.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		CategProfession entity = categProfessionService.mergeEntity(categProfession);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategProfession saveOrUpdate(CategProfession categProfession) {
		Long id = categProfession.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		CategProfession entity = categProfessionService.saveOrUpdate(categProfession);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategProfession> findPage(CategProfession categProfession, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<CategProfession> page = categProfessionService.findPage(categProfession, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return categProfessionService.existById(id);
	}
	
	public Map<Long, List<CategProfession>> findMapByMainIdList(List<Long> professionIdList) {
		return categProfessionService.findMapByMainIdList(professionIdList);
	}
	
	public List<CategProfession> findByMainId(Long professionId){
		return categProfessionService.findByMainId(professionId);
	}
	
	public CategProfession changeCategory(CategProfession categProfession) {
		return categProfessionService.changeCategory(categProfession);
	}
	
	public Page<CategProfession> findPageForPublish(CategProfession categProfession, Pageable pageable) {
		return categProfessionService.findPageForPublish(categProfession, pageable);
	}
	
	public Page<CategProfession> findPageForPublishFromDetail(CategProfession categProfession, Pageable pageable){
		
		return categProfessionService.findPageForPublishFromDetail(categProfession, pageable);
	}
	
	
	public Page<CategProfession> findPageForPublish(CategProfession categProfession, List<Long> excludeMainIdList, Pageable pageable) {
		return categProfessionService.findPageForPublish(categProfession,excludeMainIdList, pageable);
	}
	
	public Page<CategProfession> findPageForPublishWithChildren(CategProfession categProfession, List<Long> baseIdList, List<Long> excludeMainIdList, Pageable pageable){
		
		return categProfessionService.findPageForPublishWithChildren(categProfession, baseIdList, excludeMainIdList, pageable);
	}
}
