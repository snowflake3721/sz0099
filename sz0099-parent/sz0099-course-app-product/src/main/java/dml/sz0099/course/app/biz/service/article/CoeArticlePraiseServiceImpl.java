package dml.sz0099.course.app.biz.service.article;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
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

import dml.sz0099.course.app.persist.dao.article.CoeArticlePraiseDao;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;


/**
 * 
 * @formatter:off
 * description: CoeArticlePraiseServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeArticlePraiseServiceImpl extends GenericServiceImpl<CoeArticlePraise, Long> implements CoeArticlePraiseService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticlePraiseServiceImpl.class);

	@Autowired
	private CoeArticlePraiseDao coeArticlePraiseDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeArticlePraiseDao;
	}

	/**
	 * 根据Id查询CoeArticlePraise实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeArticlePraise findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeArticlePraise coeArticlePraise = coeArticlePraiseDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeArticlePraise);
		return coeArticlePraise;
	}
	
	@Override
	public CoeArticlePraise findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeArticlePraise coeArticlePraise = coeArticlePraiseDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeArticlePraise);
		return coeArticlePraise;
	}

	/**
	 * 根据IdList查询CoeArticlePraise实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeArticlePraise> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeArticlePraise> coeArticlePraiseList = coeArticlePraiseDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeArticlePraiseList);
		return coeArticlePraiseList;
	}

	@Transactional
	@Override
	public CoeArticlePraise persistEntity(CoeArticlePraise coeArticlePraise) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeArticlePraise entity = save(coeArticlePraise);
		Long id = coeArticlePraise.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeArticlePraise.SUCCESS_YES);
		return entity;
	}
	
	@Transactional
	@Override
	public CoeArticlePraise mergeEntity(CoeArticlePraise coeArticlePraise) {
		Long id = coeArticlePraise.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeArticlePraise entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeArticlePraise.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeArticlePraise.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeArticlePraise saveOrUpdate(CoeArticlePraise coeArticlePraise) {
		Long id = coeArticlePraise.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeArticlePraise entity = null;
		if(null != id) {
			entity = mergeEntity(coeArticlePraise);
		}else {
			entity = persistEntity(coeArticlePraise);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeArticlePraise> findPage(CoeArticlePraise coeArticlePraise, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeArticlePraise> page = coeArticlePraiseDao.findPage(coeArticlePraise, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return coeArticlePraiseDao.existById(id);
	}

	@Override
	public boolean existByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePraiseDao.existByMainIdAndUserId(mainId, userId);
	}

	@Override
	public CoeArticlePraise findByMainIdAndUserId(Long mainId, Long userId) {
		return coeArticlePraiseDao.findByMainIdAndUserId(mainId, userId);
	}

	@Override
	public Page<CoeArticlePraise> findByMainId(Long mainId, Pageable pageable) {
		return coeArticlePraiseDao.findByMainId( mainId,  pageable);
	}
	
	public boolean hasPraisedByMainIdAndUserId(Long mainId, Long userId) {
		CoeArticlePraise entity = findByMainIdAndUserId(mainId,userId);
		if(null != entity) {
			if(CoeArticlePraise.STATUS_YES==entity.getStatus()) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public CoeArticlePraise mergeForPraise(CoeArticlePraise coeArticlePraise) {
		Long mainId = coeArticlePraise.getMainId();
		Long userId = coeArticlePraise.getUserId();
		CoeArticlePraise entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			entity.setNickname(coeArticlePraise.getNickname());
			entity.setHeadImg(coeArticlePraise.getHeadImg());
			entity.setSayword(coeArticlePraise.getSayword());
			entity.setSaywordId(coeArticlePraise.getSaywordId());
			entity.setStatus(coeArticlePraise.getStatus());
			entity.setLastModifiedBy(coeArticlePraise.getLastModifiedBy());
			entity.setWord(coeArticlePraise.getWord());
			DateTime lastModifiedDate = new DateTime();
			entity.setRefreshTime(lastModifiedDate.toDate());
			entity.setLastModifiedDate(lastModifiedDate);
			entity = save(entity);
		}else {
			entity = save(coeArticlePraise);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public CoeArticlePraise praiseAgain(CoeArticlePraise coeArticlePraise) {
		Long mainId = coeArticlePraise.getMainId();
		Long userId = coeArticlePraise.getUserId();
		CoeArticlePraise entity = findByMainIdAndUserId( mainId,  userId);
		if(null != entity) {
			entity.setLastModifiedBy(coeArticlePraise.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			entity.setWord(coeArticlePraise.getWord());
		}
		return entity;
	}
	
	@Transactional
	public CoeArticlePraise mergeForRefreshTime(CoeArticlePraise coeArticlePraise) {
		Long id = coeArticlePraise.getId();
		if(null != id) {
			CoeArticlePraise entity = findById(id);
			if(null != entity) {
				Date refreshTime = new Date();
				entity.setRefreshTime(refreshTime);
				entity.setLastModifiedBy(coeArticlePraise.getLastModifiedBy());
				entity.setLastModifiedDate(new DateTime(refreshTime));
				coeArticlePraise=entity;
				coeArticlePraise.setSuccess(CoeArticlePraise.SUCCESS_YES);
			}
		}
		return coeArticlePraise;
	}
	
	@Transactional
	public boolean fixAllPraise(Pageable pageable) {
		if( pageable == null) {
			pageable = new PageRequest(0,100);
		}
		Page<CoeArticlePraise> all = findAll(pageable);
		if(all.hasContent()) {
			List<CoeArticlePraise> content = all.getContent();
			for(CoeArticlePraise c : content) {
				String word = c.getWord();
				if(StringUtils.isNotBlank(word)) {
					c.setWord(Base64Util.encode(HtmlUtils.htmlEscape(word, "utf-8")));
				}
				save(c);
			}
		}
		if(all.hasNext()) {
			fixAllPraise(all.nextPageable());
		}
		
		return true;
	}

}
