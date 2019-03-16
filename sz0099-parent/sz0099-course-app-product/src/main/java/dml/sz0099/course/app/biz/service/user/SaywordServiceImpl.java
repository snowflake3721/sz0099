package dml.sz0099.course.app.biz.service.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.service.GenericServiceImpl;
import org.jit8j.core.util.Base64Util;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.persist.dao.user.SaywordDao;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;
import dml.sz0099.course.app.persist.entity.user.Sayword;


/**
 * 
 * @formatter:off
 * description: SaywordServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class SaywordServiceImpl extends GenericServiceImpl<Sayword, Long> implements SaywordService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaywordServiceImpl.class);

	@Autowired
	private SaywordDao saywordDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = saywordDao;
	}

	/**
	 * 根据Id查询Sayword实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Sayword findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		Sayword sayword = saywordDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, sayword);
		return sayword;
	}
	
	@Override
	public Sayword findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		Sayword sayword = saywordDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, sayword);
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
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<Sayword> saywordList = saywordDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", saywordList);
		return saywordList;
	}

	@Transactional
	@Override
	public Sayword persistEntity(Sayword sayword) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		Date publishTime = sayword.getPublishTime();
		if(null == publishTime) {
			sayword.setPublishTime(new Date());
		}
		sayword.setStatus(Sayword.STATUS_2_PASS.getValueInt());
		Sayword entity = save(sayword);
		Long orderSeq = sayword.getOrderSeq();
		if(null == orderSeq) {
			sayword.setOrderSeq(entity.getAid());
			save(entity);
		}
		Long id = sayword.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(Sayword.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public Sayword mergeEntity(Sayword sayword) {
		Long id = sayword.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		Sayword entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(sayword.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(Sayword.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public Sayword saveOrUpdate(Sayword sayword) {
		Long id = sayword.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		Sayword entity = null;
		if(null != id) {
			entity = mergeEntity(sayword);
		}else {
			entity = persistEntity(sayword);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Sayword> findPage(Sayword sayword, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<Sayword> page = saywordDao.findPage(sayword, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return saywordDao.existById(id);
	}
	
	@Transactional
	public Sayword findAndFixedSayword(Sayword sayword) {
		Long userId = sayword.getUserId();
		Integer mainType = Sayword.MAINTYPE_9_MAIN.getValueInt();
		List<Sayword> content = findByUserIdAndMainId( userId,  mainType);
		if(null != content && !content.isEmpty()) {
			int size = content.size();
			if(size==1) {
				return content.get(0);
			}else if(size>1){
				List<Sayword> updatedList = new ArrayList<>(size);
				for(;size>1;size--) {
					Sayword s = content.get(size-1);
					s.setMainType(Sayword.MAINTYPE_0_NORMAL.getValueInt());
					DateTime lastModifiedDate = new DateTime();
					s.setLastModifiedDate(lastModifiedDate);
					s.setLastModifiedBy(sayword.getLastModifiedBy());
					updatedList.add(s);
				}
				save(updatedList);
				return content.get(0);
			}
		}else {
			List<Sayword> saywordList = findCurrentDayByUserId(userId);
			if(null != saywordList && !saywordList.isEmpty()) {
				int size = saywordList.size();
				if(size==1) {
					Sayword s = saywordList.get(0);
					s.setMainType(mainType);
					DateTime lastModifiedDate = new DateTime();
					s.setLastModifiedDate(lastModifiedDate);
					s.setLastModifiedBy(sayword.getLastModifiedBy());
					sayword=save(s);
				}else {
					for(;size>1;size--) {
						Sayword s = saywordList.get(size-1);
						delete(s);
					}
					Sayword s = saywordList.get(0);
					s.setMainType(mainType);
					DateTime lastModifiedDate = new DateTime();
					s.setLastModifiedDate(lastModifiedDate);
					s.setLastModifiedBy(sayword.getLastModifiedBy());
					sayword=save(s);
				}
			}else {
				sayword.setMainType(mainType);
				sayword.setPublishTime(new Date());
				sayword = persistEntity(sayword);
			}
		}
		return sayword;
	}
	
	public Sayword fixMainTypeAndUserId(Sayword sayword) {
		Long userId = sayword.getUserId();
		Integer mainType = Sayword.MAINTYPE_9_MAIN.getValueInt();
		
		List<Sayword> content = findByUserIdAndMainId( userId,  mainType);
		if(null != content && !content.isEmpty()) {
			int size = content.size();
			if(size==1) {
				return content.get(0);
			}else if(size>1){
				List<Sayword> updatedList = new ArrayList<>(size);
				for(;size>1;size--) {
					Sayword s = content.get(size-1);
					s.setMainType(Sayword.MAINTYPE_0_NORMAL.getValueInt());
					DateTime lastModifiedDate = new DateTime();
					s.setLastModifiedDate(lastModifiedDate);
					s.setLastModifiedBy(sayword.getLastModifiedBy());
					updatedList.add(s);
				}
				save(updatedList);
				return content.get(0);
			}
		}
		return null;
	}
	
	public Sayword fixCurrentDaySayword(Sayword sayword) {
		Long userId = sayword.getUserId();
		
		List<Sayword> saywordList = findCurrentDayByUserId(userId);
		if(null != saywordList && !saywordList.isEmpty()) {
			int size = saywordList.size();
			if(size==1) {
				Sayword s = saywordList.get(0);
				return s;
				//s.setMainType(mainType);
				//DateTime lastModifiedDate = new DateTime();
				//s.setDescription(sayword.getDescription());
				//s.setLastModifiedDate(lastModifiedDate);
				//s.setLastModifiedBy(sayword.getLastModifiedBy());
				//sayword=save(s);
			}else {
				for(;size>1;size--) {
					Sayword s = saywordList.get(size-1);
					delete(s);
				}
				Sayword s = saywordList.get(0);
				return s;
				//s.setMainType(mainType);
				//s.setDescription(sayword.getDescription());
				//DateTime lastModifiedDate = new DateTime();
				//s.setLastModifiedDate(lastModifiedDate);
				//s.setLastModifiedBy(sayword.getLastModifiedBy());
				//sayword=save(s);
			}
		}
		return null;
	}
	
	public Sayword findByUserIdAndMainId(Long userId) {
		Integer mainType = Sayword.MAINTYPE_9_MAIN.getValueInt();
		List<Sayword> content = findByUserIdAndMainId(userId, mainType);
		if(null != content && !content.isEmpty()) {
			return content.get(0);
		}
		return new Sayword();
	}
	
	public List<Sayword> findByUserIdAndMainId(Long userId, Integer mainType) {
		return saywordDao.findByUserIdAndMainId(userId, mainType);
	}
	public List<Sayword> findCurrentDayByUserId(Long userId){
		Date current = new Date();
		Date begin=  DateUtils.truncate(current, Calendar.DATE);
		Date end = DateUtils.addDays(begin, 1);
		return findByUserIdAndPublishTime(userId, begin, end);
	}
	public List<Sayword> findByUserIdAndPublishTime(Long userId, Date begin, Date end) {
		return saywordDao.findByUserIdAndPublishTime(userId, begin, end);
	}
	
	@Transactional
	public Sayword mergeSayword(Sayword sayword) {
		Long id = sayword.getId();
		Integer mainType = Sayword.MAINTYPE_9_MAIN.getValueInt();

		if(null != id) {
			Sayword entity = findById(id);
			Date current = new Date();
			if(null != entity) {
				checkMainTypeThenMerge(entity, sayword);
			}else {
				sayword.setMainType(mainType);
				sayword.setPublishTime(current);
				sayword=persistEntity(sayword);
			}
		}else {
			Sayword entity = fixMainTypeAndUserId(sayword);
			boolean sucess=false;
			if(null != entity) {
				sucess=checkMainTypeThenMerge(entity, sayword);
			}
			Date current = new Date();
			if(!sucess) {
				Sayword entity2 = fixCurrentDaySayword(sayword);
				if(null != entity2) {
					entity2.setDescription(sayword.getDescription());
					entity2.setLastModifiedDate(new DateTime(current));
					entity2.setLastModifiedBy(sayword.getLastModifiedBy());
					sayword=save(entity2);
					sucess=true;
				}
			}
			if(!sucess) {
				sayword.setMainType(mainType);
				sayword.setPublishTime(current);
				sayword=persistEntity(sayword);
			}
		}
		return sayword;
	}
	
	@Transactional
	public boolean checkMainTypeThenMerge(Sayword entity, Sayword sayword) {
		Integer mainType = Sayword.MAINTYPE_9_MAIN.getValueInt();
		Integer mainTypeEntity = entity.getMainType();
		Date current = new Date();
		if(mainTypeEntity.equals(mainType)) {
			Date publishTime = entity.getPublishTime();
			if(null != publishTime) {
				Date begin=  DateUtils.truncate(current, Calendar.DATE);
				Date end = DateUtils.addDays(begin, 1);
				
				if(publishTime.after(begin) && publishTime.before(end)) {
					entity.setDescription(sayword.getDescription());
					entity.setLastModifiedDate(new DateTime(current));
					entity.setLastModifiedBy(sayword.getLastModifiedBy());
					sayword=save(entity);
				}else {
					entity.setMainType(Sayword.MAINTYPE_0_NORMAL.getValueInt());
					entity.setLastModifiedDate(new DateTime(current));
					entity.setLastModifiedBy(sayword.getLastModifiedBy());
					save(entity);
					sayword.setId(null);
					sayword.setMainType(mainType);
					sayword.setPublishTime(current);
					sayword=persistEntity(sayword);
				}
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Date current = new Date();
		Date begin=  DateUtils.truncate(current, Calendar.DATE);
		Date end = DateUtils.addDays(begin, 1);
		System.out.println(begin);
		System.out.println(end);
	}
	
	@Transactional
	public boolean fixAllSayword(Pageable pageable) {
		if( pageable == null) {
			pageable = new PageRequest(0,100);
		}
		Page<Sayword> all = findAll(pageable);
		if(all.hasContent()) {
			List<Sayword> content = all.getContent();
			for(Sayword c : content) {
				String word = c.getDescription();
				if(StringUtils.isNotBlank(word)) {
					c.setDescription(HtmlUtil.textarea2HtmlThenEscape(word));
				}
				save(c);
			}
		}
		if(all.hasNext()) {
			fixAllSayword(all.nextPageable());
		}
		
		return true;
	}


}
