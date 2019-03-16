package dml.sz0099.course.app.client.wrapper.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.dozer.DozerBeanMapper;
import org.jit4j.app.module.define.Robot;
import org.jit8j.core.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import dml.sz0099.course.app.biz.delegate.product.CoeProductDelegate;
import dml.sz0099.course.app.client.resolver.adaptor.category.CategoryProductAdaptor;
import dml.sz0099.course.app.client.wrapper.paragraph.PhotoBannerWrapper;
import dml.sz0099.course.app.client.wrapper.paragraph.PhotoCoverWrapper;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoBanner;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoCover;
import dml.sz0099.course.app.persist.entity.product.CoeCategProd;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;
import dml.sz0099.course.app.persist.entity.product.CoeProductTag;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeProductWrapperImpl,组件封装
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
public class CoeProductWrapperImpl implements CoeProductWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeProductWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeProductDelegate coeProductDelegate;
	
	@Autowired
	private CoeProductTagWrapper coeProductTagWrapper;
	
	
	@Autowired
	private PhotoCoverWrapper photoCoverWrapper;
	
	@Autowired
	private PhotoBannerWrapper photoBannerWrapper;
	
	@Autowired
	private CoeCategProdWrapper coeCategProdWrapper;
	
	@Autowired
	private CategoryProductAdaptor categoryProductAdaptor;
	
	private Long mainId = Robot.ROBOT_PLAT.getId();
	
	private Long subId = Robot.ROBOT_PLAT.getId();
	
	/**
	 * 根据Id查询CoeProduct实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeProduct findById(Long id) {
		CoeProduct coeProduct = findById(id,true,true, true,true, true);
		return coeProduct;
	}
	public CoeProduct findByIdOnly(Long id) {
		CoeProduct coeProduct = findById(id,false,false, false,false, false);
		return coeProduct;
	}
	
	public CoeProduct findById(Long id, boolean withTags , boolean withCover, boolean withBanner, boolean withCategory, boolean withCategoryTree) {
		LOGGER.debug("--- CoeProductWrapperImpl.findById begin --------- id is:{} , withTags: {} ", id, withTags);
		CoeProduct coeProduct = coeProductDelegate.findById(id);
		if(null != coeProduct && withTags) {
			if(withTags) {
				List<CoeProductTag> productTagList = coeProductTagWrapper.findByMainId(id);
				coeProduct.setProTagList(productTagList);
			}
			if(withCover) {
				List<Long> subIdList = new ArrayList<>(1);
				subIdList.add(PhotoCover.SUBID_COVER_HEAD);
				List<PhotoCover>  coverList = photoCoverWrapper.findBySubIdListAndMainId(subIdList, id);
				coeProduct.setCoverList(coverList);
			}
			if(withBanner) {
				List<Long> subIdList = new ArrayList<>(1);
				subIdList.add(PhotoBanner.SUBID_BANNER_HEAD);
				List<PhotoBanner>  bannerList = photoBannerWrapper.findBySubIdListAndMainId(subIdList, id);
				coeProduct.setBannerList(bannerList);
			}
			if(withCategory) {
				//获取分类树 TODO
				List<CoeCategProd> categoryList = coeCategProdWrapper.findByMainId(id);
				if(null == categoryList) {
					categoryList = new ArrayList<>(1);
				}
				if(categoryList.isEmpty()) {
					CoeCategProd prod = new CoeCategProd();
					prod.setMainId(id);
					categoryList.add(prod);
					
				}
				coeProduct.setCategoryList(categoryList);
				if(withCategoryTree) {
					Category categoryTree = categoryProductAdaptor.queryTree(mainId, subId);
					coeProduct.setCategoryTree(categoryTree);
				}
			}
		}
		LOGGER.debug("--- CoeProductWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeProduct);
		return coeProduct;
	}
	
	public boolean existById(Long id) {
		LOGGER.debug("--- CoeProductWrapperImpl.existById begin --------- id is:{} ", id);
		return coeProductDelegate.existById(id);
	}
	
	/**
	 * 根据IdList查询CoeProduct实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeProductWrapperImpl.findByIdList begin ---------  ");
		List<CoeProduct> coeProductList = coeProductDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeProductWrapperImpl.findByIdList end ---------  result is {} ",  coeProductList);
		return coeProductList;
	}
	
	@Override
	public CoeProduct persistEntity(CoeProduct coeProduct) {
		LOGGER.debug("--- CoeProductWrapperImpl.persistEntity begin ---------  ");
		CoeProduct entity = coeProductDelegate.persistEntity(coeProduct);
		Long id = coeProduct.getId();
		LOGGER.debug("--- CoeProductWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}
	
	public CoeProduct createDraft(CoeProduct coeProduct) {
		
		CoeProduct entity = coeProductDelegate.createDraft(coeProduct);
		coeProduct.setProductNo(coeProduct.getProductNo());
		coeProduct.setOriginalLink(entity.getOriginalLink());
		coeProduct.setAid(entity.getAid());
		coeProduct.setId(entity.getId());
		
		return entity;
	}
	
	@Override
	public CoeProduct mergeProduct(CoeProduct coeProduct) {
		return coeProductDelegate.mergeProduct(coeProduct);
	}
	
	@Override
	public CoeProduct mergeForBaseinfo(CoeProduct coeProduct) {
		//转义字符
		String title = coeProduct.getTitle();
		if(StringUtils.isNotBlank(title)) {
			title = HtmlUtils.htmlEscape(title,UrlUtil.CHARSET_UTF_8);
			coeProduct.setTitle(title);
			
		}
		String name = coeProduct.getName();
		if(StringUtils.isBlank(name)) {
			coeProduct.setName(title);
		}else {
			coeProduct.setName(HtmlUtils.htmlEscape(name,UrlUtil.CHARSET_UTF_8));
		}
		
		String description = coeProduct.getDescription();
		if(StringUtils.isNotBlank(description)) {
			coeProduct.setDescription(HtmlUtils.htmlEscape(description,UrlUtil.CHARSET_UTF_8));
		}
		
		return coeProductDelegate.mergeForBaseinfo(coeProduct);
	}

	@Override
	public CoeProduct saveOrUpdate(CoeProduct coeProduct) {
		return coeProductDelegate.saveOrUpdate(coeProduct);
	}

	@Override
	public CoeProduct mergeShelved(CoeProduct coeProduct) {
		return coeProductDelegate.mergeShelved(coeProduct);
	}

	@Override
	public CoeProduct mergeProductForLink(CoeProduct coeProduct) {
		return coeProductDelegate.mergeProductForLink(coeProduct);
	}

	@Override
	public CoeProduct mergeProductForPrice(CoeProduct coeProduct) {
		return coeProductDelegate.mergeProductForPrice(coeProduct);
	}

	@Override
	public List<CoeProduct> findByShelved(CoeProduct coeProduct) {
		return coeProductDelegate.findByShelved(coeProduct);
	}
	
	@Override
	public Page<CoeProduct> findByShelved(CoeProduct coeProduct, Pageable pageable) {
		Page<CoeProduct> page = findByShelved(coeProduct,pageable,true,true, false, true);
		return page;
	}
	
	
	public Page<CoeProduct> findByShelved(CoeProduct coeProduct, Pageable pageable, boolean withTags, boolean withCover, boolean withBanner, boolean withCategory) {
		Page<CoeProduct> page = coeProductDelegate.findByShelved(coeProduct,pageable);
		if(null != page && page.getTotalElements()>0) {
			
				List<CoeProduct> content = page.getContent();
				List<Long> mainIdList = new ArrayList<>(content.size());
				Map<Long,CoeProduct> cpMap = new HashMap<>(content.size());
				for(CoeProduct cp : content) {
					Long productId = cp.getId();
					mainIdList.add(productId);
					cpMap.put(productId, cp);
				}
				if(withCover) {
					Map<Long,List<PhotoCover>> coverMap = photoCoverWrapper.findByMainIdListAndSubId(mainIdList, PhotoCover.SUBID_COVER_HEAD);
					if(null != coverMap && !coverMap.isEmpty()) {
						for(Map.Entry<Long,List<PhotoCover>> entry : coverMap.entrySet()) {
							Long productId = entry.getKey();
							CoeProduct cp = cpMap.get(productId);
							cp.setCoverList(entry.getValue());
						}
					}
				}
				if(withBanner) {
					Map<Long,List<PhotoBanner>> bannerMap = photoBannerWrapper.findByMainIdListAndSubId(mainIdList, PhotoBanner.SUBID_BANNER_HEAD);
					if(null != bannerMap && !bannerMap.isEmpty()) {
						for(Map.Entry<Long,List<PhotoBanner>> entry : bannerMap.entrySet()) {
							Long productId = entry.getKey();
							CoeProduct cp = cpMap.get(productId);
							cp.setBannerList(entry.getValue());
						}
					}
				}
				if(withTags) {
					Map<Long, List<CoeProductTag>> productTagMap = coeProductTagWrapper.findMapByMainIdList(mainIdList);
					if(null != productTagMap && !productTagMap.isEmpty()) {
						for(Map.Entry<Long,List<CoeProductTag>> entry : productTagMap.entrySet()) {
							Long productId = entry.getKey();
							CoeProduct cp = cpMap.get(productId);
							cp.setProTagList(entry.getValue());
						}
					}
				}
				if(withCategory) {
					Map<Long, List<CoeCategProd>> categoryMap = coeCategProdWrapper.findMapByMainIdList(mainIdList);
					if(null != categoryMap && !categoryMap.isEmpty()) {
						for(Map.Entry<Long,List<CoeCategProd>> entry : categoryMap.entrySet()) {
							Long productId = entry.getKey();
							CoeProduct cp = cpMap.get(productId);
							cp.setCategoryList(entry.getValue());
						}
					}
				}
				
		}
		return page;
	}
	
	@Override
	public List<CoeProduct> findShelvedByName(String name) {
		return coeProductDelegate.findShelvedByName(name);
	}

	@Override
	public List<CoeProduct> findShelvedByTitle(String title) {
		return coeProductDelegate.findShelvedByTitle(title);
	}
	
	public List<CoeProduct> findDraftList(CoeProduct coeProduct){
		return coeProductDelegate.findDraftList(coeProduct);
	}
	
	public Long countDraftList(CoeProduct coeProduct) {
		return coeProductDelegate.countDraftList(coeProduct);
	}

	//含tag信息
	@Override
	public CoeProduct findDetail(Long id) {
		return findById(id);
	}
	
	public Page<CoeProduct> findPublished(CoeProduct coeProduct, Pageable pageable) {
		String title = StringUtils.trim(coeProduct.getTitle());
		String name = StringUtils.trim(coeProduct.getName());
		if(StringUtils.isNotBlank(title)) {
			if(StringUtils.isBlank(name)) {
				coeProduct.setName(title);
			}
		}
		return coeProductDelegate.findPublished(coeProduct,pageable);
	}

	@Override
	public CoeProduct mergeForRefresh(CoeProduct coeProduct) {
		return coeProductDelegate.mergeForRefresh(coeProduct);
	}

	@Override
	public CoeProduct mergeForEditQickly(CoeProduct coeProduct) {
		return coeProductDelegate.mergeForEditQickly(coeProduct);
	}

	@Override
	public CoeProduct mergeForPublish(CoeProduct coeProduct) {
		return coeProductDelegate.mergeForPublish(coeProduct);
	}
	
	public CoeProduct mergeForClosed(CoeProduct coeProduct) {
		return coeProductDelegate.mergeForClosed(coeProduct);
	}
	
	public CoeProduct mergeForDeleted(CoeProduct coeProduct) {
		coeProduct.setDeleted(true);
		return coeProductDelegate.mergeForDeleted(coeProduct);
	}

	@Override
	public boolean publishedById(Long id) {
		boolean published = coeProductDelegate.publishedById(id);
		return published;
	}

	@Override
	public CoeProduct persistForCover(CoeProduct coeProduct) {
		List<PhotoCover>  coverList = coeProduct.getCoverList();
		if(null != coverList && !coverList.isEmpty()) {
			coverList = photoCoverWrapper.persistForPhoto(coverList);
			coeProduct.setCoverList(coverList);
		}
		return coeProduct;
	}
	
	public CoeProduct persistForBanner(CoeProduct coeProduct) {
		List<PhotoBanner>  bannerList = coeProduct.getBannerList();
		if(null != bannerList && !bannerList.isEmpty()) {
			bannerList = photoBannerWrapper.persistForPhoto(bannerList);
			coeProduct.setBannerList(bannerList);
		}
		return coeProduct;
	}
}
