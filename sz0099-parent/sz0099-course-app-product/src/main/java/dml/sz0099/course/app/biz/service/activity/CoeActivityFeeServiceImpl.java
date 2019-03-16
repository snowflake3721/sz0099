package dml.sz0099.course.app.biz.service.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import dml.sz0099.course.app.biz.service.tag.CoeTagService;
import dml.sz0099.course.app.persist.dao.activity.CoeActivityFeeDao;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;


/**
 * 
 * @formatter:off
 * description: CoeActivityFeeServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeActivityFeeServiceImpl extends GenericServiceImpl<CoeActivityFee, Long> implements CoeActivityFeeService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityFeeServiceImpl.class);

	@Autowired
	private CoeActivityFeeDao coeActivityFeeDao;
	
	@Autowired
	private CoeTagService coeTagService;
	
	@Autowired
	private CoeActivityService coeActivityService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeActivityFeeDao;
	}

	/**
	 * 根据Id查询CoeActivityFee实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeActivityFee findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeActivityFee coeActivityFee = coeActivityFeeDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeActivityFee);
		return coeActivityFee;
	}
	
	@Override
	public CoeActivityFee findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeActivityFee coeActivityFee = coeActivityFeeDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeActivityFee);
		return coeActivityFee;
	}

	/**
	 * 根据IdList查询CoeActivityFee实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeActivityFee> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeActivityFee> coeActivityFeeList = coeActivityFeeDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeActivityFeeList);
		return coeActivityFeeList;
	}

	@Transactional
	@Override
	public CoeActivityFee persistEntity(CoeActivityFee coeActivityFee) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeActivityFee entity = save(coeActivityFee);
		Long id = coeActivityFee.getId();
		entity.setOrderSeq(entity.getAid());
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeActivityFee.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityFee mergeEntity(CoeActivityFee coeActivityFee) {
		Long id = coeActivityFee.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeActivityFee entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeActivityFee.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeActivityFee.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeActivityFee saveOrUpdate(CoeActivityFee coeActivityFee) {
		Long id = coeActivityFee.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeActivityFee entity = null;
		if(null != id) {
			entity = mergeEntity(coeActivityFee);
		}else {
			entity = persistEntity(coeActivityFee);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeActivityFee> findPage(CoeActivityFee coeActivityFee, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeActivityFee> page = coeActivityFeeDao.findPage(coeActivityFee, pageable);
		return page;
	}
	
	@Transactional
	public CoeActivityFee addFee(CoeActivityFee coeActivityFee) {
		coeActivityFee=persistEntity(coeActivityFee);
		return coeActivityFee;
	}
	
	@Transactional
	public CoeActivityFee addFee(CoeActivity coeActivity) {
		Long id = coeActivity.getId();
		Long userId = coeActivity.getUserId();
		CoeActivityFee activityFee = new CoeActivityFee();
		activityFee.setMainId(id);
		activityFee.setCreatedBy(userId);
		activityFee.setUserId(userId);
		activityFee.setLastModifiedBy(userId);
		activityFee.setCurrency(CoeActivityFee.CURRENCY_RMB.getValueInt());
		activityFee.setFeeType(CoeActivityFee.FEETYPE_AA.getValueInt());
		activityFee.setRecieveType(CoeActivityFee.RECIEVETYPE_ONLIME.getValueInt());
		activityFee.setPriceType(CoeActivityFee.PRICETYPE_NORMAL.getValueInt());
		activityFee=addFee(activityFee);
		coeActivity.setActFee(activityFee);
		return activityFee;
	}
	
	@Transactional
	public CoeActivityFee deleteFee(CoeActivityFee coeActivityFee) {
		Long pTagId = coeActivityFee.getId();
		Long activityId = coeActivityFee.getMainId();
		CoeActivityFee entity = findById(pTagId);
		if (null != entity) {
			Long entityProfessionId = entity.getMainId();
			if (entityProfessionId.equals(activityId)) {
				delete(entity);
				coeActivityFee.setSuccess(CoeActivityFee.SUCCESS_YES);
			}
		}

		return coeActivityFee;
	}
	
	public CoeActivityFee findByMainId(CoeActivityFee coeActivityFee) {
		Long activityId = coeActivityFee.getMainId();
		if (null != activityId) {
			CoeActivityFee tag = coeActivityFeeDao.findByMainId(activityId);
			return tag;
		}
		return null;
	}
	
	public Long countByMainId(Long activityId) {
		return coeActivityFeeDao.countByMainId(activityId);
	}
	
	public CoeActivityFee findByMainId(Long activityId) {
		return coeActivityFeeDao.findByMainId(activityId);
	}
	
	public Map<Long, CoeActivityFee> findMapByMainIdList(List<Long> mainIdList) {
		List<CoeActivityFee> content = coeActivityFeeDao.findByMainIdList(mainIdList);
		Map<Long, CoeActivityFee> map = new HashMap<>();
		if(null != content && !content.isEmpty()) {
			for(CoeActivityFee c : content) {
				Long mainId = c.getMainId();
				map.put(mainId, c);
			}
		}
		return map;
	}
	
	public Page<CoeActivityFee> findPageWithNotself(CoeActivityFee coeActivityFee, Pageable pageable){
		Page<CoeActivityFee> page = coeActivityFeeDao.findPageWithNotself(coeActivityFee, pageable);
		if(null != page && page.getTotalElements()>0) {
			List<CoeActivityFee> content = page.getContent();
			List<Long> mainIdList = new ArrayList<>(content.size());
			Map<Long, CoeActivityFee> tagMap = new HashMap<>(content.size());
			Map<Long, CoeActivityFee> tagRepeatMap = new HashMap<>(content.size());
			for(CoeActivityFee c : content) {
				Long mainId = c.getMainId();
				if(!mainIdList.contains(mainId)) {
					mainIdList.add(mainId);
					tagMap.put(mainId, c);
				}else {
					tagRepeatMap.put(mainId, c);
				}
			}
			List<CoeActivity> mainList = coeActivityService.findPublishByIdList(mainIdList);
			if(null != mainList && !mainList.isEmpty()) {
				for(CoeActivity m : mainList) {
					Long mid = m.getId();
					CoeActivityFee mc = tagMap.get(mid);
					if(null != mc) {
						mc.setActivity(m);
					}
					CoeActivityFee mcr = tagRepeatMap.get(mid);
					if(null != mcr) {
						mcr.setActivity(m);
					}
				}
			}
		}
		return page;
	}
	
	@Transactional
	@Override
	public CoeActivityFee mergeDescription(CoeActivityFee coeActivityFee) {
		Long id = coeActivityFee.getId();
		CoeActivityFee entity = findById(id);
		if(null != entity) {
			entity.setDescription(coeActivityFee.getDescription());
			entity.setLastModifiedBy(coeActivityFee.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityTime.SUCCESS_YES);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeActivityFee mergeFee(CoeActivityFee coeActivityFee) {
		Long id = coeActivityFee.getId();
		CoeActivityFee entity = findById(id);
		if(null != entity) {
			entity.setRecieveType(coeActivityFee.getRecieveType());
			entity.setFeeType(coeActivityFee.getFeeType());
			entity.setPriceType(coeActivityFee.getPriceType());
			entity.setCurrency(coeActivityFee.getCurrency());
			entity.setRmbAmount(coeActivityFee.getRmbAmount());
			entity.setRmbAmountOri(coeActivityFee.getRmbAmountOri());
			entity.setCashAmount(coeActivityFee.getCashAmount());
			entity.setCoinAmount(coeActivityFee.getCoinAmount());
			entity.setDescription(coeActivityFee.getDescription());
			entity.setLastModifiedBy(coeActivityFee.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setSuccess(CoeActivityTime.SUCCESS_YES);
		}
		return entity;
	}

	@Transactional
	@Override
	public CoeActivity syncFromTemplate(CoeActivity entity, CoeActivity template) {
		Long mainId = entity.getId();
		Long templateId = template.getId();
		if(null != mainId && null != templateId) {
			CoeActivityFee feeEntity = findByMainId(mainId);
			//同步模板标签
			CoeActivityFee activityFee=findByMainId(templateId);
			if(null == feeEntity ) {
				feeEntity = new CoeActivityFee();
				feeEntity.setMainId(activityFee.getMainId());
				feeEntity.setCreatedBy(entity.getCreatedBy());
				feeEntity.setCreatedDate(entity.getLastModifiedDate());
				feeEntity.setLastModifiedBy(entity.getLastModifiedBy());
				feeEntity.setLastModifiedDate(entity.getLastModifiedDate());
			}
			if(null != activityFee) {
				feeEntity.setCashAmount(activityFee.getCashAmount());
				feeEntity.setCoinAmount(activityFee.getCoinAmount());
				feeEntity.setCurrency(activityFee.getCurrency());
				feeEntity.setDescription(activityFee.getDescription());
				feeEntity.setFeeType(activityFee.getFeeType());
				feeEntity.setPriceType(activityFee.getPriceType());
				feeEntity.setRecieveType(activityFee.getRecieveType());
				feeEntity.setRmbAmount(activityFee.getRmbAmount());
				feeEntity.setRmbAmountOri(activityFee.getRmbAmountOri());
				feeEntity.setLastModifiedBy(entity.getLastModifiedBy());
				DateTime lastModifiedDate = new DateTime();
				feeEntity.setLastModifiedDate(lastModifiedDate);
				save(feeEntity);
			}
			entity.setActFee(feeEntity);
		}
		return entity;
	}

}
