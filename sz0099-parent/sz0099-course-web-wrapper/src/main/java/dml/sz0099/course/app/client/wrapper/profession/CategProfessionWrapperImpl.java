package dml.sz0099.course.app.client.wrapper.profession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.profession.CategProfessionDelegate;
import dml.sz0099.course.app.client.wrapper.category.CategoryWrapper;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.entity.profession.Profession;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CategProfessionWrapperImpl,组件封装
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
public class CategProfessionWrapperImpl implements CategProfessionWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategProfessionWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CategProfessionDelegate categProfessionDelegate;
	
	@Autowired
	private ProfessionWrapper professionWrapper;
	
	@Autowired
	private CategoryWrapper categoryWrapper;
	
	/**
	 * 根据Id查询CategProfession实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CategProfession findById(Long id) {
		LOGGER.debug("--- wrapper.findById begin --------- id is:{} ", id);
		CategProfession categProfession = categProfessionDelegate.findById(id);
		LOGGER.debug("--- wrapper.findById end --------- id is:{} , result is {} ", id, categProfession);
		return categProfession;
	}
	
	@Override
	public CategProfession findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CategProfession categProfession = categProfessionDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, categProfession);
		return categProfession;
	}
	
	/**
	 * 根据IdList查询CategProfession实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CategProfession> findByIdList(List<Long> idList) {
		LOGGER.debug("--- wrapper.findByIdList begin ---------  ");
		List<CategProfession> categProfessionList = categProfessionDelegate.findByIdList(idList);
		LOGGER.debug("--- wrapper.findByIdList end ---------  result is {} ",  categProfessionList);
		return categProfessionList;
	}
	
	@Override
	public CategProfession persistEntity(CategProfession categProfession) {
		LOGGER.debug("--- wrapper.persistEntity begin ---------  ");
		CategProfession entity = categProfessionDelegate.persistEntity(categProfession);
		Long id = categProfession.getId();
		LOGGER.debug("--- wrapper.persistEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategProfession mergeEntity(CategProfession categProfession) {
		Long id = categProfession.getId();
		LOGGER.debug("--- wrapper.mergeEntity begin, id is {} ---------  ",id);
		CategProfession entity = categProfessionDelegate.mergeEntity(categProfession);
		LOGGER.debug("--- wrapper.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CategProfession saveOrUpdate(CategProfession categProfession) {
		Long id = categProfession.getId();
		LOGGER.debug("--- wrapper.saveOrUpdate begin, id is {} ---------  ",id);
		CategProfession entity = categProfessionDelegate.saveOrUpdate(categProfession);
		LOGGER.debug("--- wrapper.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CategProfession> findPage(CategProfession categProfession, Pageable pageable) {
		LOGGER.debug("--- wrapper.findPage ---------  ");
		Page<CategProfession> page = categProfessionDelegate.findPage(categProfession, pageable);
		return page;
	}
	
	@Override
	public boolean existById(Long id) {
		return categProfessionDelegate.existById(id);
	}
	
	public Map<Long, List<CategProfession>> findMapByMainIdList(List<Long> professionIdList) {
		return categProfessionDelegate.findMapByMainIdList(professionIdList);
	}
	
	public List<CategProfession> findByMainId(Long professionId){
		return categProfessionDelegate.findByMainId(professionId);
	}

	@Override
	public CategProfession changeCategory(CategProfession categProfession) {
		return categProfessionDelegate.changeCategory(categProfession);
	}
	
	public Page<CategProfession> findPageForPublish(CategProfession categProfession, Pageable pageable) {
		Page<CategProfession> page = categProfessionDelegate.findPageForPublish(categProfession, pageable);
		List<CategProfession> contentList = page.getContent();
		if (null != contentList && !contentList.isEmpty()) {
			List<Profession> professionList = new ArrayList<>(contentList.size());
			for (CategProfession cca : contentList) {
				if (null != cca) {
					Profession profession = cca.getProfession();
					professionList.add(profession);
				}
			}
			professionWrapper.fillRefForList(professionList);
		}
		return page;
	}
	
	public Page<CategProfession> findPageForPublishFromDetail(CategProfession categProfession, Pageable pageable){
		Page<CategProfession> page = categProfessionDelegate.findPageForPublishFromDetail(categProfession, pageable);
		List<CategProfession> contentList = page.getContent();
		if (null != contentList && !contentList.isEmpty()) {
			List<Profession> professionList = new ArrayList<>(contentList.size());
			for (CategProfession cca : contentList) {
				if (null != cca) {
					Profession profession = cca.getProfession();
					professionList.add(profession);
				}
			}
			professionWrapper.fillRefForList(professionList);
		}
		return page;
	}
	
	public Page<CategProfession> findPageForPublish(CategProfession categProfession, List<Long> excludeMainIdList, Pageable pageable, boolean cover, boolean banner, boolean author) {
		Page<CategProfession> page = categProfessionDelegate.findPageForPublish(categProfession, excludeMainIdList, pageable);
		List<CategProfession> contentList = page.getContent();
		if (null != contentList && !contentList.isEmpty()) {
			List<Profession> professionList = new ArrayList<>(contentList.size());
			for (CategProfession cca : contentList) {
				if (null != cca) {
					Profession profession = cca.getProfession();
					professionList.add(profession);
				}
			}
			if(cover && banner && author ) {
				professionWrapper.fillRefWithCoverAndBannerAndAuthor(professionList);
			}else {
				professionWrapper.fillRefWithCoverAndBanner(professionList);
			}
		}
		return page;
	}
	
	public Page<CategProfession> findPageForPublishWithChildren(CategProfession categProfession, List<Long> excludeMainIdList, Pageable pageable, boolean cover, boolean banner, boolean author){
		Profession profession = categProfession.getProfession();
		if(null == profession) {
			profession = new Profession();
			categProfession.setProfession(profession);
		}
		
		Category category = categProfession.getCategory();
		List<Long> baseIdList = null;
		Long baseId = categProfession.getBaseId();
		if(null != baseId) {
			baseIdList = categoryWrapper.findListByBaseIdWithChilren(baseId);
		}else {
			if(null != category) {
				Long id = category.getId();
				if(null != id) {
					baseIdList = categoryWrapper.findListByBaseIdWithChilren(id);
				}else {
					String code = category.getCode();
					baseIdList = categoryWrapper.findListByCodeWithChilren(code);
				}
			}
		}
		
		profession.setPublishStatus(Profession.PUBLISH_STATUS_PUBLISH.getValueInt());
		
		if(null != baseIdList || baseId!= null) {
			Page<CategProfession>  categoryPage =  categProfessionDelegate.findPageForPublishWithChildren(categProfession, baseIdList, excludeMainIdList, pageable);
		
			if(null != categoryPage && categoryPage.getTotalElements()>0) {
				List<CategProfession> contentList = categoryPage.getContent();
				if (null != contentList && !contentList.isEmpty()) {
					List<Profession> entityList = new ArrayList<>(contentList.size());
					for (CategProfession cca : contentList) {
						if (null != cca) {
							Profession entity = cca.getProfession();
							entityList.add(entity);
						}
					}
					if(cover && banner && author ) {
						professionWrapper.fillRefWithCoverAndBannerAndAuthor(entityList);
					}else {
						professionWrapper.fillRefWithCoverAndBanner(entityList);
					}
				}
			}
			return categoryPage;
		}
		return null;
	}

}
