package dml.sz0099.course.app.biz.delegate.user;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.delegate.user.SaywordDelegate;
import dml.sz0099.course.app.biz.service.user.SaywordService;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * saywordServiceImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class SaywordDelegateImpl implements SaywordDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaywordDelegateImpl.class);
	
	@Autowired
	private SaywordService saywordService;

	/**
	 * 根据Id查询Sayword实体对象
	 * @param id
	 * @return
	 */
	@Override
	public Sayword findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		Sayword sayword = saywordService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, sayword);
		return sayword;
	}
	
	@Override
	public Sayword findByAid(Long aid) {
		LOGGER.debug("--- delegate>>>findByAid begin --------- aid is:{} ", aid);
		Sayword sayword = saywordService.findByAid(aid);
		LOGGER.debug("--- delegate>>>findByAid end --------- aid is:{} , result is {} ", aid, sayword);
		return sayword;
	}
	
	/**
	 * 根据IdList查询Sayword实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<Sayword> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<Sayword> saywordList = saywordService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  saywordList);
		return saywordList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public Sayword persistEntity(Sayword sayword) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		Sayword entity = saywordService.persistEntity(sayword);
		Long id = sayword.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Sayword mergeEntity(Sayword sayword) {
		Long id = sayword.getId();
		LOGGER.debug("--- delegate.mergeEntity begin, id is {} ---------  ",id);
		Sayword entity = saywordService.mergeEntity(sayword);
		LOGGER.debug("--- delegate.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Sayword saveOrUpdate(Sayword sayword) {
		Long id = sayword.getId();
		LOGGER.debug("--- delegate.saveOrUpdate begin, id is {} ---------  ",id);
		Sayword entity = saywordService.saveOrUpdate(sayword);
		LOGGER.debug("--- delegate.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<Sayword> findPage(Sayword sayword, Pageable pageable) {
		LOGGER.debug("--- delegate.findPage ---------  ");
		Page<Sayword> page = saywordService.findPage(sayword, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		return saywordService.existById(id);
	}
	
	public Sayword findAndFixedSayword(Sayword sayword) {
		return saywordService.findAndFixedSayword( sayword);
	}
	public Sayword mergeSayword(Sayword sayword) {
		return saywordService.mergeSayword( sayword);
	}
	
	public Sayword findByUserIdAndMainId(Long userId) {
		return saywordService.findByUserIdAndMainId(userId);
	}
}
