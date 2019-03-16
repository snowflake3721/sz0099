package dml.sz0099.course.app.biz.delegate.profession;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.profession.ProfessionService;
import dml.sz0099.course.app.persist.entity.profession.Profession;

/**
 * professionServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class ProfessionDelegateImpl implements ProfessionDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionDelegateImpl.class);
	
	@Autowired
	private ProfessionService professionService;

	/**
	 * 根据Id查询Profession实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Profession findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		Profession profession = professionService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, profession);
		return profession;
	}
	
	@Override
	public Profession findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		Profession profession = professionService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, profession);
		return profession;
	}
	
	/**
	 * 根据IdList查询Profession实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Profession> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<Profession> professionList = professionService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  professionList);
		return professionList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public Profession persistEntity(Profession profession) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		Profession entity = professionService.persistEntity(profession);
		Long id = profession.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Profession mergeEntity(Profession profession) {
		Long id = profession.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		Profession entity = professionService.mergeEntity(profession);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Profession saveOrUpdate(Profession profession) {
		Long id = profession.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		Profession entity = professionService.saveOrUpdate(profession);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Profession> findPage(Profession profession, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<Profession> page = professionService.findPage(profession, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return professionService.existById(id);
	}
	
	public Page<Profession> findPublished(Profession profession, Pageable pageable) {
		return professionService.findPublished(profession,pageable);
	}
	
	public Profession mergeForRefresh(Profession profession) {
		return professionService.mergeForRefresh(profession);
	}
	
	/*public Profession mergeForEditQickly(Profession profession) {
		return professionService.mergeForEditQickly(profession);
	}*/
	
	public Profession mergeForPublish(Profession profession) {
		return professionService.mergeForPublish(profession);
	}
	
	public Profession mergeForClosed(Profession profession) {
		return professionService.mergeForClosed(profession);
	}
	
	public Profession mergeForDeleted(Profession profession) {
		return professionService.mergeForDeleted(profession);
	}
	
	public Profession createDraft(Profession profession) {

		Profession entity = professionService.createDraft(profession);
		return entity;
	}
	
	@Override
	public List<Profession> findDraftList(Profession profession){
		return professionService.findDraftList(profession);
	}
	
	@Override
	public Long countDraftList(Profession profession) {
		return professionService.countDraftList(profession);
	}

	@Override
	public Profession mergeForBaseinfo(Profession profession) {
		return professionService.mergeForBaseinfo(profession);
	}
	
	@Override
	public Profession mergeProfessionForTitle(Profession profession) {
		return professionService.mergeProfessionForTitle(profession);
	}
	@Override
	public Profession mergeArticleForTitleOnly(Profession profession) {
		return professionService.mergeArticleForTitleOnly(profession);
	}
	public Profession mergeArticleForDescriptionOnly(Profession profession) {
		return professionService.mergeArticleForDescriptionOnly(profession);
	}

	
	@Override
	public List<Profession> findByPublished(Profession profession) {
		return professionService.findByPublished(profession);
	}
	@Override
	public Page<Profession> findByPublished(Profession profession, Pageable pageable){
		return professionService.findByPublished(profession,pageable);
	}
	
	public Profession mergeForEditQickly(Profession profession) {
		return professionService.mergeForEditQickly(profession);
	}
	
	public Profession mergeForUnPublished(Profession profession) {
		return professionService.mergeForUnPublished(profession);
	}
	
	public Long countForPublishedWithoutSelf(Long userId, Long positionId) {
		
		return professionService.countForPublishedWithoutSelf( userId, positionId);
	}
	
	public Page<Profession> findByPublishedNotSelf(Profession profession, Pageable pageable){
		return professionService.findByPublishedNotSelf(profession,pageable);
	}
	
	@Override
	public List<Profession> findByUserId(Long userId) {
		return professionService.findByUserId( userId) ;
	}
	
	public Long countByUserId(Long userId) {
		return professionService.countByUserId(userId);
	}
	
	public Page<Profession> findPageByMainTypeAndUserId(Integer mainType, List<Long> userIdList, Integer publishStatus, Pageable pageable){
		return professionService.findPageByMainTypeAndUserId(mainType, userIdList, publishStatus, pageable);
	}
	
	public Page<Profession> findPageByMainTypeAndUserId(List<Long> userIdList, Pageable pageable){
		return professionService.findPageByMainTypeAndUserId(userIdList, pageable);
	}
	
	public List<Profession> findListByMainTypeAndUserId(List<Long> userIdList){
		return professionService.findListByMainTypeAndUserId( userIdList);
	}
	
	public Profession mergeForMainType(Profession profession) {
		return professionService.mergeForMainType(profession);
	}
}
