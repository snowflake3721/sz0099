package dml.sz0099.course.app.client.wrapper.user;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.jit4j.core.persist.page.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.user.SaywordDelegate;
import dml.sz0099.course.app.core.util.HtmlUtil;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * SaywordWrapperImpl,组件封装
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
public class SaywordWrapperImpl implements SaywordWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SaywordWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private SaywordDelegate saywordDelegate;
	
	/**
	 * 根据Id查询Sayword实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Sayword findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		Sayword sayword = saywordDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, sayword);
		return sayword;
	}
	
	@Override
	public Sayword findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		Sayword sayword = saywordDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, sayword);
		return sayword;
	}
	
	/**
	 * 根据IdList查询Sayword实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Sayword> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<Sayword> saywordList = saywordDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  saywordList);
		return saywordList;
	}
	
	@Override
	public Sayword persistEntity(Sayword sayword) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		String description = sayword.getDescription();
		if(StringUtils.isNotBlank(description)) {
			sayword.setDescription(HtmlUtil.textarea2HtmlThenEscape(description));
		}
		Sayword entity = saywordDelegate.persistEntity(sayword);
		Long id = sayword.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Sayword mergeEntity(Sayword sayword) {
		Long id = sayword.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		Sayword entity = saywordDelegate.mergeEntity(sayword);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Sayword saveOrUpdate(Sayword sayword) {
		Long id = sayword.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		Sayword entity = saywordDelegate.saveOrUpdate(sayword);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Sayword> findPage(Sayword sayword, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<Sayword> page = saywordDelegate.findPage(sayword, pageable);
		return page;
	}
	
	@Override
	public Page<Sayword> findPageForHuan(Sayword sayword, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPageForHuan ---------  ");
		Long userId = sayword.getUserId();
		if(null != userId) {
			sayword.setStatus(Sayword.STATUS_2_PASS.getValueInt());
			Page<Sayword> page = saywordDelegate.findPage(sayword, pageable);
			return page;
		}
		return null;
	}
	
	public Page<Sayword> findPageForHuanSingle(Sayword sayword){
		Pageable pageable = new PageRequest(0, 1,Direction.ASC, "topLevel");
		Page<Sayword> page = findPageForHuan(sayword, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return saywordDelegate.existById(id);
	}

	@Override
	public Sayword findAndFixedSayword(Sayword sayword) {
		Sayword entity = saywordDelegate.findAndFixedSayword( sayword);
		if(null != entity) {
			String description = entity.getDescription();
			if(StringUtils.isNotBlank(description)) {
				entity.setDescription(HtmlUtil.textarea2UnEscape(description));
			}
		}
		return entity;
	}
	
	public Sayword mergeSayword(Sayword sayword) {
		String description = sayword.getDescription();
		if(StringUtils.isNotBlank(description)) {
			sayword.setDescription(HtmlUtil.textarea2Escape(description));
		}
		return saywordDelegate.mergeSayword( sayword);
	}
	
	public Sayword findByUserIdAndMainId(Long userId) {
		Sayword entity = saywordDelegate.findByUserIdAndMainId(userId);
		if(null != entity) {
			String description = entity.getDescription();
			if(StringUtils.isNotBlank(description)) {
				entity.setDescription(HtmlUtil.textarea2UnEscapeForHtml(description));
			}
		}
		return entity;
	}
}
