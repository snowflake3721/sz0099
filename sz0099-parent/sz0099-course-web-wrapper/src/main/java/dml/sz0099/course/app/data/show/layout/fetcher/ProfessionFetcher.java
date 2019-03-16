/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.show.layout.fetcher;

import java.util.ArrayList;
import java.util.List;

import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.persist.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import dml.sz0099.course.app.client.resolver.adaptor.position.PositionProfessionAdaptor;
import dml.sz0099.course.app.client.wrapper.profession.CategProfessionWrapper;
import dml.sz0099.course.app.data.show.blooming.config.IngenuityFetcherConfig;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.ProfessionPageDto;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-10 09:33:41
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-10	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class ProfessionFetcher {
	
	
	private PositionProfessionAdaptor positionProfessionAdaptor;
	
	protected IngenuityFetcherConfig ingenuityFetcherConfigQX;
	
	protected IngenuityFetcherConfig recommendFetcherConfigQX;
	
	@Autowired
	protected CategProfessionWrapper categProfessionWrapper;
	
	/**
	 * 数据抓取策略：默认
	 */
	public static final Integer STRATEGY_DEFAULT=0;
	
	
	//首页头条滚播面板-专业技
	public ProfessionPageDto fetchIndexHead(ProfessionPosition professionPosition, Integer strategy) {
		// Long userId = professionPosition.getUserId();//可指定用户
		// 从首页头条位置面板获取数据
		Integer ponPanel = Position.PANEL_1_HEAD.getValueInt();
		Pageable pageable = new PageRequest(0, 6, Direction.DESC, "topLevel", "refreshTime");
		professionPosition.setPonPanel(ponPanel);

		ProfessionPosition defaultProfession = ingenuityFetcherConfigQX.getRandomFromList();
		convert(professionPosition, defaultProfession);

		ProfessionPageDto professionPageDto = new ProfessionPageDto();
		professionPageDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionPageDto,strategy, pageable);
		professionPageDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于6篇，从分类为【推荐技】中获取最新发布的文章(6-size)篇
		Page<CategProfession> categoryPage = findCategoryPageFilter(professionPageDto, Category.CODE_PROFESSION_RECOMMEND, 0, 6);
		professionPageDto.setCategoryPage(categoryPage);
				
		return professionPageDto;
	}
	
	//八仙过海面板-对应分类 major
	public ProfessionPageDto fetchIndexMajor(ProfessionPosition professionPosition, Integer strategy) {
		Integer ponPanel = Position.PANEL_PROFESSION_MAJOR.getValueInt();
		professionPosition.setPonPanel(ponPanel);
		ProfessionPosition defaultProfession = ingenuityFetcherConfigQX.getRandomFromList();
		convert(professionPosition, defaultProfession);
		
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		ProfessionPageDto professionPageDto = new ProfessionPageDto();
		professionPageDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionPageDto,strategy, pageable);
		professionPageDto.setPositionPage(positionPage);
		
		//从分类为【专业技】中获取最新发布的技能(6-size)篇
		Page<CategProfession> categoryPage = findCategoryPageFilter(professionPageDto, Category.CODE_PROFESSION_MAJOR, 0, 6);
		professionPageDto.setCategoryPage(categoryPage);
		
		return professionPageDto;
	}
	
	//出行技 面板,从位置为[出行技]获取数据
	public ProfessionPageDto fetchIndexOutdoor(ProfessionPosition professionPosition, Integer strategy) {
		//Long userId = professionPosition.getUserId();//可指定用户
		//从户外技获取数据
		Integer ponPanel=Position.PANEL_PROFESSION_OUTDOOR.getValueInt();
		professionPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		
		ProfessionPageDto professionDto = new ProfessionPageDto();
		professionDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionDto, strategy, pageable);
		professionDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于1篇，从分类为【 出行技】中获取最新推荐的技能(1-size)篇
		//Page<CategProfession> categoryPage = 
		findCategoryPageFilter(professionDto, Category.CODE_PROFESSION_OUTDOOR, 0, 6);
		//professionDto.setCategoryPage(categoryPage);
		return professionDto;
	}
	
	//生活技 面板,从位置为[生活技]获取数据
	public ProfessionPageDto fetchIndexLife(ProfessionPosition professionPosition, Integer strategy) {
		//Long userId = professionPosition.getUserId();//可指定用户
		//从生活技获取数据
		Integer ponPanel=Position.PANEL_PROFESSION_LIFE.getValueInt();
		professionPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		
		ProfessionPageDto professionDto = new ProfessionPageDto();
		professionDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionDto, strategy, pageable);
		professionDto.setPositionPage(positionPage);
		
		//从分类为【 生活服务技】中获取最新推荐的技能(6-size)篇
		//Page<CategProfession> categoryPage = 
		findCategoryPageFilter(professionDto, Category.CODE_PROFESSION_LIFE, 0, 6);
		//professionDto.setCategoryPage(categoryPage);
		return professionDto;
	}
	
	//独具匠心面板-独具匠心
	public ProfessionPageDto fetchIndexIngenuity(ProfessionPosition professionPosition, Integer strategy) {
		Integer ponPanel = Position.PANEL_PROFESSION_INGENUITY.getValueInt();
		professionPosition.setPonPanel(ponPanel);
		ProfessionPosition defaultProfession = ingenuityFetcherConfigQX.getRandomFromList();
		convert(professionPosition, defaultProfession);
		
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		ProfessionPageDto professionPageDto = new ProfessionPageDto();
		professionPageDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionPageDto,strategy, pageable);
		professionPageDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于2篇，从分类为【独具匠心】中获取最新发布的文章(6-size)篇
		Page<CategProfession> categoryPage = findCategoryPageFilter(professionPageDto, Category.CODE_PROFESSION_MAJOR_INGENUITY, 0, 6);
		professionPageDto.setCategoryPage(categoryPage);
		
		return professionPageDto;
	}
	
	//能工巧匠-能工巧匠
	public ProfessionPageDto fetchIndexArtisan(ProfessionPosition professionPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_PROFESSION_ARTISAN.getValueInt();
		professionPosition.setPonPanel(ponPanel);
		ProfessionPosition defaultProfession = recommendFetcherConfigQX.getRandomFromList();
		convert(professionPosition, defaultProfession);
		
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		ProfessionPageDto professionPageDto = new ProfessionPageDto();
		professionPageDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionPageDto,strategy, pageable);
		professionPageDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于2篇，从分类为【能工巧匠】中获取最新发布的文章(6-size)篇
		Page<CategProfession> categoryPage = findCategoryPageFilter(professionPageDto, Category.CODE_PROFESSION_MAJOR_ARTISAN, 0, 6);
		professionPageDto.setCategoryPage(categoryPage);
		
		return professionPageDto;
	}
	
	
	//乐趣
	public ProfessionPageDto fetchIndexRelax(ProfessionPosition professionPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_PROFESSION_RELAX.getValueInt();
		professionPosition.setPonPanel(ponPanel);
		ProfessionPosition defaultProfession = recommendFetcherConfigQX.getRandomFromList();
		convert(professionPosition, defaultProfession);
		
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		ProfessionPageDto professionPageDto = new ProfessionPageDto();
		professionPageDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionPageDto,strategy, pageable);
		professionPageDto.setPositionPage(positionPage);
		
		//从分类为【乐趣】中获取最新发布的文章(6-size)篇
		Page<CategProfession> categoryPage = findCategoryPageFilter(professionPageDto, Category.CODE_PROFESSION_RELAX, 0, 6);
		professionPageDto.setCategoryPage(categoryPage);
		
		return professionPageDto;
	}
	
	//特产
	public ProfessionPageDto fetchIndexNative(ProfessionPosition professionPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_PROFESSION_NATIVE.getValueInt();
		professionPosition.setPonPanel(ponPanel);
		ProfessionPosition defaultProfession = recommendFetcherConfigQX.getRandomFromList();
		convert(professionPosition, defaultProfession);
		
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		ProfessionPageDto professionPageDto = new ProfessionPageDto();
		professionPageDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionPageDto,strategy, pageable);
		professionPageDto.setPositionPage(positionPage);
		
		//从分类为【特产】中获取最新发布的文章(6-size)篇
		Page<CategProfession> categoryPage = findCategoryPageFilter(professionPageDto, Category.CODE_PROFESSION_NATIVE, 0, 6);
		professionPageDto.setCategoryPage(categoryPage);
		
		return professionPageDto;
	}
	
	//直供
	public ProfessionPageDto fetchIndexFactory(ProfessionPosition professionPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_PROFESSION_MAJOR_FACTORY.getValueInt();
		professionPosition.setPonPanel(ponPanel);
		ProfessionPosition defaultProfession = recommendFetcherConfigQX.getRandomFromList();
		convert(professionPosition, defaultProfession);
		
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		ProfessionPageDto professionPageDto = new ProfessionPageDto();
		professionPageDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionPageDto,strategy, pageable);
		professionPageDto.setPositionPage(positionPage);
		
		//从分类为【直供】中获取最新发布的文章(6-size)篇
		Page<CategProfession> categoryPage = findCategoryPageFilter(professionPageDto, Category.CODE_PROFESSION_MAJOR_FACTORY, 0, 6);
		professionPageDto.setCategoryPage(categoryPage);
		
		return professionPageDto;
	}
	
	//装备
	public ProfessionPageDto fetchIndexEquip(ProfessionPosition professionPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_PROFESSION_MAJOR_EQUIPMENT.getValueInt();
		professionPosition.setPonPanel(ponPanel);
		ProfessionPosition defaultProfession = recommendFetcherConfigQX.getRandomFromList();
		convert(professionPosition, defaultProfession);
		
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		ProfessionPageDto professionPageDto = new ProfessionPageDto();
		professionPageDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionPageDto,strategy, pageable);
		professionPageDto.setPositionPage(positionPage);
		
		//从分类为【装备】中获取最新发布的文章(6-size)篇
		Page<CategProfession> categoryPage = findCategoryPageFilter(professionPageDto, Category.CODE_PROFESSION_OUTDOOR_EQUIPMENT, 0, 6);
		professionPageDto.setCategoryPage(categoryPage);
		
		return professionPageDto;
	}
	
	//技能推广--对应推荐技
	public ProfessionPageDto fetchIndexRecommend(ProfessionPosition professionPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_PROFESSION_RECOMMEND.getValueInt();
		professionPosition.setPonPanel(ponPanel);
		ProfessionPosition defaultProfession = recommendFetcherConfigQX.getRandomFromList();
		convert(professionPosition, defaultProfession);
		
		Pageable pageable = new PageRequest(0,3, Direction.DESC, "topLevel","refreshTime");
		ProfessionPageDto professionPageDto = new ProfessionPageDto();
		professionPageDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionPageDto,strategy, pageable);
		professionPageDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于2篇，从分类为【技能推荐】中获取最新发布的文章(3-size)篇
		Page<CategProfession> categoryPage = findCategoryPageFilter(professionPageDto, Category.CODE_PROFESSION_RECOMMEND, 0, 3);
		professionPageDto.setCategoryPage(categoryPage);
		
		return professionPageDto;
	}
	
	//今日神技
	//今日神技 面板(荐 按钮),从位置为[今日神技]获取数据
	public ProfessionPageDto fetchRecommendForMainCircle(ProfessionPosition professionPosition, Integer strategy) {
		//Long userId = professionPosition.getUserId();//可指定用户
		//从今日神技获取数据
		Integer ponPanel=Position.PANEL_PROFESSION_TODAY.getValueInt();
		professionPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,1, Direction.DESC, "topLevel","refreshTime");
		
		ProfessionPageDto professionDto = new ProfessionPageDto();
		professionDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionDto, strategy, pageable);
		professionDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于1篇，从分类为【今日神技】中获取最新推荐的技能(1-size)篇
		//Page<CategProfession> categoryPage = 
		findCategoryPageFilter(professionDto, Category.CODE_PROFESSION_TODAY, 0, 1);
		//professionDto.setCategoryPage(categoryPage);
		return professionDto;
	}
	
	
	//大侠必杀 面板,从位置为[大侠必杀]获取数据
	public ProfessionPageDto fetchIndexKill(ProfessionPosition professionPosition, Integer strategy) {
		//Long userId = professionPosition.getUserId();//可指定用户
		//从今日神技获取数据
		Integer ponPanel=Position.PANEL_PROFESSION_KILL.getValueInt();
		professionPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		
		ProfessionPageDto professionDto = new ProfessionPageDto();
		professionDto.setPosition(professionPosition);
		Page<ProfessionPosition> positionPage = fetchPositionPage(professionDto, strategy, pageable);
		professionDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于6篇，从分类为【必杀技】中获取最新推荐的技能(6-size)篇
		//Page<CategProfession> categoryPage = 
		findCategoryPageFilter(professionDto, Category.CODE_PROFESSION_KILL, 0, 6);
		return professionDto;
	}
	
	

	/**
	 * @param professionPosition
	 * @param defaultProfession
	 */
	private void convert(ProfessionPosition professionPosition, ProfessionPosition defaultProfession) {
		if (null != defaultProfession) {
			professionPosition.setTitle(defaultProfession.getTitle());
			professionPosition.setSubTitle(defaultProfession.getSubTitle());
			professionPosition.setCoverImage(defaultProfession.getCoverImage());
			professionPosition.setLink(defaultProfession.getLink());
			professionPosition.setPreIntro(defaultProfession.getPreIntro());
		}
	}

	public Page<ProfessionPosition> fetchPositionPage(ProfessionPageDto professionPageDto, Integer strategy,Pageable pageable) {
		ProfessionPosition professionPosition = professionPageDto.getPosition();
		Page<ProfessionPosition> result = null;
		if(null != professionPosition && STRATEGY_DEFAULT.equals(strategy)) {
			PositionExtend extend = positionProfessionAdaptor.config();
			Long positionId = extend.getPositionId();
			//Long ponMainId=professionPosition.getPonMainId();
			//Long ponSubId=professionPosition.getPonSubId();
			//Integer ponPanel=professionPosition.getPonPanel();
			professionPosition.setPositionId(positionId);
			professionPosition.setExtendId(extend.getId());
			if(null == pageable) {
				pageable = new PageRequest(0,2, Direction.DESC, "topLevel","refreshTime");
			}
			result = positionProfessionAdaptor.findPageForPosition(professionPosition, pageable);
			
		}
		return result;
	}
	
	
	/**
	 * @param size
	 * @param categProfession
	 * @return
	 */
	private Page<CategProfession> findCategoryPage(ProfessionPageDto professionPageDto, boolean withCategoryChildren, List<Long> excludeMainIdList,Pageable pageable, boolean cover, boolean banner, boolean author) {
		CategProfession categProfession = professionPageDto.getCategory();
		if(null == pageable) {
			pageable = new PageRequest(0,2, Direction.DESC, "profession.refreshTime");
		}
		if(null == categProfession) {
			categProfession = new CategProfession();
			professionPageDto.setCategory(categProfession);
		}
		Category category = categProfession.getCategory();
		if(category == null) {
			category = new Category();
			categProfession.setCategory(category);
			category.setCode(Category.CODE_PROFESSION_MAJOR_360);
		}
		
		Page<CategProfession> categoryPage = null;
		if(withCategoryChildren) {
			categoryPage = categProfessionWrapper.findPageForPublishWithChildren(categProfession,excludeMainIdList, pageable,  cover,  banner,  author);
		}else {
			categoryPage = categProfessionWrapper.findPageForPublish(categProfession, excludeMainIdList, pageable,  cover,  banner,  author);
		}
		return categoryPage;
	}
	
	
	private Page<CategProfession> findCategoryPageFilter(ProfessionPageDto professionDto, String categoryCode, int page, int maxSize) {
		long total = 0;
		Page<ProfessionPosition> positionPage = professionDto.getPositionPage();
		if(null != positionPage) {
			total=positionPage.getTotalElements();
		}
		if(page<0) {
			page=0;
		}
		if(maxSize<0) {
			maxSize=1;
		}
		int size=maxSize;
		List<Long> idList = null;
		if(total>0 && total<maxSize) {
			int totalV=(int)total;
			size=maxSize-totalV;
			idList = new ArrayList<>(totalV);
			List<ProfessionPosition> contentList = positionPage.getContent();
			for(ProfessionPosition content : contentList) {
				Long mainId = content.getMainId();
				idList.add(mainId);
			}
		}
		Page<CategProfession> categoryPage = null;
		CategProfession categProfession = new CategProfession();
		Category category = new Category();
		categProfession.setCategory(category);
		category.setCode(categoryCode);
		professionDto.setCategory(categProfession);
		if(total < maxSize) {
			//若该位置没有推荐文章或少于maxSize篇，从分类为【XXX】中获取最新发布的文章(maxSize-total)篇
			Pageable catePageable = new PageRequest(page,size, Direction.DESC, "profession.refreshTime");
			categoryPage = findCategoryPage(professionDto,true, idList, catePageable, true, true, false);
			if(null == categoryPage) {
				//当未增加对应分类时返回null
				categoryPage = new PageResult<>(new ArrayList<>(0), catePageable, 0);
			}
		}else {
			Pageable catePageable = new PageRequest(page,maxSize, Direction.DESC, "profession.refreshTime");
			categoryPage = new PageResult<>(new ArrayList<>(0), catePageable, 0);
		}
		professionDto.setCategoryPage(categoryPage);
		
		return categoryPage;
	}
	
	/**
	 * 分类页 通用数据获取，点击more时触发
	 * @param strategy 数据获取策略
	 * @param professionPosition 位置
	 * @param positionPageable 位置分页，固定，6条数据（分类页面展示推荐:上下各三条）
	 * @param categProfession  分类
	 * @param categoryPageable 分类分页
	 * @return
	 */
	public ProfessionPageDto fetchCommonPage(Integer strategy, ProfessionPageDto professionPageDto, Pageable positionPageable,  Pageable categoryPageable) {
		if(professionPageDto ==null) {
			professionPageDto = new ProfessionPageDto(); 
		}
		ProfessionPosition professionPosition = professionPageDto.getPosition();
		CategProfession categProfession = professionPageDto.getCategory();
		if(STRATEGY_DEFAULT.equals(strategy)) {
			Page<ProfessionPosition> positionPage = null;
			if(null != professionPosition && null != positionPageable) {
				positionPage = fetchCommonPositionPage(professionPageDto, strategy, positionPageable);
				professionPageDto.setPositionPage(positionPage);
			}
			if(null != categProfession && null != categoryPageable) {
				List<Long> excludeMainIdList = null;
				if(null != positionPage && positionPage.getTotalElements()>0) {
					List<ProfessionPosition> positionList = positionPage.getContent();
					excludeMainIdList = new ArrayList<>(positionList.size());
					for(ProfessionPosition position : positionList) {
						excludeMainIdList.add(position.getMainId());
						
					}
				}
				Page<CategProfession> categoryPage = fetchCommonCategoryPage(professionPageDto,excludeMainIdList, categoryPageable);
				professionPageDto.setCategoryPage(categoryPage);
			}
			
		}
		
		return professionPageDto;
	}
	
	public Page<ProfessionPosition> fetchCommonPositionPage(ProfessionPageDto professionPageDto, Integer strategy,Pageable positionPageable){
		return fetchPositionPage(professionPageDto, strategy, positionPageable);
	}
	
	public Page<CategProfession> fetchCommonCategoryPage(ProfessionPageDto professionPageDto , List<Long> excludeMainIdList,  Pageable categoryPageable) {
		boolean withCategoryChildren=true;
		return findCategoryPage(professionPageDto, withCategoryChildren, excludeMainIdList, categoryPageable, true, true, true);
	}
	
	

	public PositionProfessionAdaptor getPositionProfessionAdaptor() {
		return positionProfessionAdaptor;
	}

	public void setPositionProfessionAdaptor(PositionProfessionAdaptor positionProfessionAdaptor) {
		this.positionProfessionAdaptor = positionProfessionAdaptor;
	}

	public IngenuityFetcherConfig getIngenuityFetcherConfigQX() {
		return ingenuityFetcherConfigQX;
	}

	public void setIngenuityFetcherConfigQX(IngenuityFetcherConfig ingenuityFetcherConfigQX) {
		this.ingenuityFetcherConfigQX = ingenuityFetcherConfigQX;
	}

	public IngenuityFetcherConfig getRecommendFetcherConfigQX() {
		return recommendFetcherConfigQX;
	}

	public void setRecommendFetcherConfigQX(IngenuityFetcherConfig recommendFetcherConfigQX) {
		this.recommendFetcherConfigQX = recommendFetcherConfigQX;
	}

	
	
	
	

}
