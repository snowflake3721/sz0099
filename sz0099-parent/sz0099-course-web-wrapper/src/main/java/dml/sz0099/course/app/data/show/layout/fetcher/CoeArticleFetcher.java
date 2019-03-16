/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.show.layout.fetcher;

import java.util.ArrayList;
import java.util.List;

import org.jit4j.core.persist.page.PageRequest;
import org.jit4j.core.persist.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import dml.sz0099.course.app.client.resolver.adaptor.position.PositionArticleAdaptor;
import dml.sz0099.course.app.client.wrapper.article.CoeCategArticleWrapper;
import dml.sz0099.course.app.data.show.blooming.config.FlagFetcherConfig;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.CoeArticlePageDto;
import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.PositionExtend;

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

public class CoeArticleFetcher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeArticleFetcher.class);

	protected PositionArticleAdaptor positionArticleAdaptor;
	
	protected FlagFetcherConfig flagFetcherConfigQX;
	
	protected FlagFetcherConfig flagFetcherConfigXF;
	
	protected FlagFetcherConfig roadLineFetcherConfigQX;
	
	@Autowired
	protected CoeCategArticleWrapper coeCategArticleWrapper;
	
	/**
	 * 数据抓取策略：默认
	 */
	public static final Integer STRATEGY_DEFAULT=0;
	
	//首页大旗滚播面板
	public CoeArticlePageDto  fetchIndexFlagQX(CoeArticlePosition coeArticlePosition, Integer strategy,Pageable pageable) {
		Integer ponPanel=Position.PANEL_6_FLAG_QX.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		CoeArticlePosition defaultArticle = flagFetcherConfigQX.getRandomFromList();
		convert(coeArticlePosition, defaultArticle);
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto,strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于2篇，从分类为【插旗行动-群侠户外】中获取最新发布的文章(6-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_FLAG_QX, 0, 6);
		//coeArticleDto.setCategoryPage(categoryPage);
		
		return coeArticleDto;
	}
	/**
	 * @param coeArticlePosition
	 * @param defaultArticle
	 */
	private void convert(CoeArticlePosition coeArticlePosition, CoeArticlePosition defaultArticle) {
		if(null != defaultArticle) {
			coeArticlePosition.setTitle(defaultArticle.getTitle());
			coeArticlePosition.setSubTitle(defaultArticle.getSubTitle());
			coeArticlePosition.setCoverImage(defaultArticle.getCoverImage());
			coeArticlePosition.setLink(defaultArticle.getLink());
			coeArticlePosition.setPreIntro(defaultArticle.getPreIntro());
		}
	}
	public CoeArticlePageDto fetchIndexFlagXF(CoeArticlePosition coeArticlePosition, Integer strategy,Pageable pageable) {
		Integer ponPanel=Position.PANEL_16_FLAG_XF.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		CoeArticlePosition defaultArticle = flagFetcherConfigXF.getRandomFromList();
		convert(coeArticlePosition, defaultArticle);
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto,strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于2篇，从分类为【插旗行动-雪峰户外】中获取最新发布的文章(2-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_FLAG_XF, 0, 2);
		//coeArticleDto.setCategoryPage(categoryPage);
		
		return coeArticleDto;
	}
	
	public CoeArticlePageDto fetchIndexRoadLine(CoeArticlePosition coeArticlePosition, Integer strategy,Pageable pageable) {
		Integer ponPanel=Position.PANEL_7_ROADLINE.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		CoeArticlePosition defaultArticle = roadLineFetcherConfigQX.getRandomFromList();
		convert(coeArticlePosition, defaultArticle);
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto,strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于2篇，从分类为【攻略】中获取最新发布的文章(2-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ROADLINE_GUIDE, 0, 6);
		//coeArticleDto.setCategoryPage(categoryPage);
		
		return coeArticleDto;
	}
	
	public Page<CoeArticlePosition> fetchPositionPage(CoeArticlePageDto coeArticlePageDto, Integer strategy,Pageable pageable) {
		CoeArticlePosition coeArticlePosition = coeArticlePageDto.getPosition();
		Page<CoeArticlePosition> result = null;
		if(null != coeArticlePosition && STRATEGY_DEFAULT.equals(strategy)) {
			PositionExtend extend = positionArticleAdaptor.config();
			Long positionId = extend.getPositionId();
			//Long ponMainId=coeArticlePosition.getPonMainId();
			//Long ponSubId=coeArticlePosition.getPonSubId();
			//Integer ponPanel=coeArticlePosition.getPonPanel();
			coeArticlePosition.setPositionId(positionId);
			coeArticlePosition.setExtendId(extend.getId());
			if(null == pageable) {
				pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
			}
			LOGGER.debug("---------fetchPositionPage: positionId:{}, ponMainId:{}, ponSubId:{}, ----",positionId, coeArticlePosition.getPonSubId(), coeArticlePosition.getPonMainId());
			result = positionArticleAdaptor.findPageForPosition(coeArticlePosition, pageable);
			if(null !=result) {
				LOGGER.debug("---------fetchPositionPage: positionId:{}, ponMainId:{}, ponSubId:{}, result.totalElements:{} ----",positionId, coeArticlePosition.getPonSubId(), coeArticlePosition.getPonMainId(), result.getTotalElements());
			}
			
		}
		return result;
	}
	
	//文章首页-头条滚播面板
	public CoeArticlePageDto fetchArticleIndexHead(CoeArticlePosition coeArticlePosition, Integer strategy) {
		//Long userId = coeArticlePosition.getUserId();//可指定用户
		//从首页头条位置面板获取数据
		Integer ponPanel = Position.PANEL_1_HEAD.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto, strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【记实】中获取最新发布的文章(6-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_SHARED, 0, 6);
		//coeArticleDto.setCategoryPage(categoryPage);
		return coeArticleDto;
	}
	/**
	 * @param size
	 * @param coeCategArticle
	 * @return
	 */
	public Page<CoeCategArticle> findCategoryPage(CoeArticlePageDto coeArticlePageDto, boolean withCategoryChildren, List<Long> excludeMainIdList, Pageable pageable, boolean cover, boolean banner, boolean author) {
		
		CoeCategArticle coeCategArticle = coeArticlePageDto.getCategory();
		if(null == pageable) {
			pageable = new PageRequest(0,2, Direction.DESC, "article.refreshTime");
		}
		if(null == coeCategArticle) {
			coeCategArticle = new CoeCategArticle();
			coeArticlePageDto.setCategory(coeCategArticle);
		}
		Category category = coeCategArticle.getCategory();
		if(category == null) {
			category = new Category();
			coeCategArticle.setCategory(category);
			category.setCode(Category.CODE_ARTICLE_SHARED);
		}
		
		Page<CoeCategArticle> categoryPage = null;
		if(withCategoryChildren) {
			categoryPage = coeCategArticleWrapper.findPageForPublishWithChildren(coeCategArticle,  excludeMainIdList, pageable, cover, banner,author);
		}else {
			categoryPage = coeCategArticleWrapper.findPageForPublish(coeCategArticle, excludeMainIdList, pageable, cover, banner,author);
		}
		return categoryPage;
	}
	
	//独家记忆面板,大屏显示
	public CoeArticlePageDto fetchSpecialRemember(CoeArticlePosition coeArticlePosition, Integer strategy) {
		//Long userId = coeArticlePosition.getUserId();//可指定用户
		//从独家记忆位置面板获取数据
		Integer ponPanel=Position.PANEL_5_SPECIAL_REMEMBER.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,1, Direction.DESC, "topLevel","refreshTime");
		
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto, strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于1篇，从分类为【独家记忆】中获取最新发布的文章(1-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_SPECIAL_REMEMBER, 0, 1);
		//coeArticleDto.setCategoryPage(categoryPage);
		
		return coeArticleDto;
	}
	
	//探险者面板,从分类为[探路行动]获取数据
	public CoeArticlePageDto fetchExplorer(CoeArticlePosition coeArticlePosition, Integer strategy) {
		//Long userId = coeArticlePosition.getUserId();//可指定用户
		//从探险者位置面板获取数据
		Integer ponPanel=Position.PANEL_3_EXPLORER.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,4, Direction.DESC, "topLevel","refreshTime");
		
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto, strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于6篇，从分类为【探路行动】中获取最新发布的文章(6-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ROADLINE_EXPLORER, 0, 4);
		//coeArticleDto.setCategoryPage(categoryPage);
			
		return coeArticleDto;
		
	}
	
	/**
	 * 获取路线攻略6条
	 * @param coeArticlePosition
	 * @param strategy
	 * @return
	 */
	public CoeArticlePageDto fetchIndexRoadLine(CoeArticlePosition coeArticlePosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_7_ROADLINE.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeArticlePosition.setPonPanel(ponPanel);
		CoeArticlePosition defaultArticle = roadLineFetcherConfigQX.getRandomFromList();
		convert(coeArticlePosition, defaultArticle);
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto,strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于2篇，从分类为【攻略】中获取最新发布的文章(2-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ROADLINE_GUIDE, 0, 6);
		//coeArticleDto.setCategoryPage(categoryPage);
		
		return coeArticleDto;
	}
	
	/**
	 * 获取徒步
	 * @param coeArticlePosition
	 * @param strategy
	 * @return
	 */
	public CoeArticlePageDto fetchIndexFoot(CoeArticlePosition coeArticlePosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_17_SHARED_FOOT.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeArticlePosition.setPonPanel(ponPanel);
		CoeArticlePosition defaultArticle = roadLineFetcherConfigQX.getRandomFromList();
		convert(coeArticlePosition, defaultArticle);
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto,strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【徒步】中获取最新发布的文章(6-size)篇
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_SHARED_FOOT, 0, 6);
		return coeArticleDto;
	}
	
	
	/**
	 * 获取旅行
	 * @param coeArticlePosition
	 * @param strategy
	 * @return
	 */
	public CoeArticlePageDto fetchIndexTravel(CoeArticlePosition coeArticlePosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_19_SHARED_TRAVEL.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeArticlePosition.setPonPanel(ponPanel);
		CoeArticlePosition defaultArticle = roadLineFetcherConfigQX.getRandomFromList();
		convert(coeArticlePosition, defaultArticle);
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto,strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【旅行】中获取最新发布的文章(6-size)篇
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_SHARED_TRAVEL, 0, 6);
		return coeArticleDto;
	}
	
	/**
	 * 获取骑行
	 * @param coeArticlePosition
	 * @param strategy
	 * @return
	 */
	public CoeArticlePageDto fetchIndexBike(CoeArticlePosition coeArticlePosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_18_SHARED_BIKE.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeArticlePosition.setPonPanel(ponPanel);
		CoeArticlePosition defaultArticle = roadLineFetcherConfigQX.getRandomFromList();
		convert(coeArticlePosition, defaultArticle);
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto,strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【骑行】中获取最新发布的文章(6-size)篇
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_SHARED_BIKE, 0, 6);
		return coeArticleDto;
	}
	
	/**
	 * 获取公益
	 * @param coeArticlePosition
	 * @param strategy
	 * @return
	 */
	public CoeArticlePageDto fetchIndexWelfare(CoeArticlePosition coeArticlePosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_20_SHARED_WELFARE.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeArticlePosition.setPonPanel(ponPanel);
		CoeArticlePosition defaultArticle = roadLineFetcherConfigQX.getRandomFromList();
		convert(coeArticlePosition, defaultArticle);
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto,strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于2篇，从分类为【公益】中获取最新发布的文章(2-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_SHARED_WELFARE, 0, 6);
		//coeArticleDto.setCategoryPage(categoryPage);
		
		return coeArticleDto;
	}
	
	//吃货--合家欢面板,从分类为[合家欢]获取数据
	public CoeArticlePageDto fetchEatTogethor(CoeArticlePosition coeArticlePosition, Integer strategy) {
		//Long userId = coeArticlePosition.getUserId();//可指定用户
		//从合家欢位置面板获取数据
		Integer ponPanel=Position.PANEL_10_EAT_TOGETHOR.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,3, Direction.DESC, "topLevel","refreshTime");
		
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto, strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于3篇，从分类为【合家欢】中获取最新发布的文章(3-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_EAT_TOGETHOR, 0, 3);
		//coeArticleDto.setCategoryPage(categoryPage);
			
		return coeArticleDto;
		
	}
	
	//吃货--农家乐面板,从分类为[农家乐]获取数据
	public CoeArticlePageDto fetchEatFarmstay(CoeArticlePosition coeArticlePosition, Integer strategy) {
		//Long userId = coeArticlePosition.getUserId();//可指定用户
		//从 农家乐 位置面板获取数据
		Integer ponPanel=Position.PANEL_9_EAT_FARMSTAY.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,3, Direction.DESC, "topLevel","refreshTime");
		
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto, strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于3篇，从分类为【农家乐】中获取最新发布的文章(3-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_EAT_FARMSTAY, 0, 3);
		//coeArticleDto.setCategoryPage(categoryPage);
			
		return coeArticleDto;
	}
	
	//吃货--野餐团面板,从分类为[野餐团]获取数据
	public CoeArticlePageDto fetchEatOutdoor(CoeArticlePosition coeArticlePosition, Integer strategy) {
		//Long userId = coeArticlePosition.getUserId();//可指定用户
		//从 野餐团 位置面板获取数据
		Integer ponPanel=Position.PANEL_11_EAT_OUTDOOR.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,3, Direction.DESC, "topLevel","refreshTime");
		
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto, strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于3篇，从分类为【野餐团】中获取最新发布的文章(3-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_EAT_OUTDOOR, 0, 3);
		//coeArticleDto.setCategoryPage(categoryPage);
		return coeArticleDto;
	}
	
	/**
	 * 获取美食6条
	 * @param coeArticlePosition
	 * @param strategy
	 * @return
	 */
	public CoeArticlePageDto fetchIndexEat(CoeArticlePosition coeArticlePosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_8_EAT.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto, strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【攻略】中获取最新发布的文章(2-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_EAT, 0, 6);
		//coeArticleDto.setCategoryPage(categoryPage);
		
		return coeArticleDto;
	}
	
	//装备篇面板,从分类为[装备篇]获取数据
	public CoeArticlePageDto fetchEquip(CoeArticlePosition coeArticlePosition, Integer strategy) {
		//Long userId = coeArticlePosition.getUserId();//可指定用户
		//从 装备篇 位置面板获取数据
		Integer ponPanel=Position.PANEL_12_EQUIP.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,4, Direction.DESC, "topLevel","refreshTime");
		
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto, strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于4篇，从分类为【装备篇】中获取最新发布的文章(4-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_EQUIP, 0, 4);
		//coeArticleDto.setCategoryPage(categoryPage);
		return coeArticleDto;
	}
	
	//装备篇-实战检验 面板,从分类为[实战检验]获取数据
	public CoeArticlePageDto fetchEquipReal(CoeArticlePosition coeArticlePosition, Integer strategy) {
		//Long userId = coeArticlePosition.getUserId();//可指定用户
		//从 装备篇 位置面板获取数据
		Integer ponPanel=Position.PANEL_13_EQUIP_REAL.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,4, Direction.DESC, "topLevel","refreshTime");
		
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto, strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于4篇，从分类为【实战检验】中获取最新发布的文章(4-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_EQUIP_REAL, 0, 4);
		//coeArticleDto.setCategoryPage(categoryPage);
		return coeArticleDto;
	}
	
	//装备篇-大众流行 面板,从分类为[大众流行]获取数据
	public CoeArticlePageDto fetchEquipPopular(CoeArticlePosition coeArticlePosition, Integer strategy) {
		//Long userId = coeArticlePosition.getUserId();//可指定用户
		//从 装备篇 位置面板获取数据
		Integer ponPanel=Position.PANEL_14_EQUIP_POPULAR.getValueInt();
		coeArticlePosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,4, Direction.DESC, "topLevel","refreshTime");
		
		CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
		coeArticleDto.setPosition(coeArticlePosition);
		Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto, strategy, pageable);
		coeArticleDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于4篇，从分类为【大众流行】中获取最新发布的文章(4-size)篇
		//Page<CoeCategArticle> categoryPage = 
		findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_EQUIP_POPULAR, 0, 4);
		//coeArticleDto.setCategoryPage(categoryPage);
		return coeArticleDto;
	}
	
		//article_action_flag
		//行动-插旗行动 面板,从分类为[插旗行动]获取数据
		public CoeArticlePageDto fetchActionFlag(CoeArticlePosition coeArticlePosition, Integer strategy) {
			//Long userId = coeArticlePosition.getUserId();//可指定用户
			//从 行动-插旗行动获取数据
			Integer ponPanel=Position.PANEL_15_ACTION_FLAG.getValueInt();
			coeArticlePosition.setPonPanel(ponPanel);
			Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
			
			CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
			coeArticleDto.setPosition(coeArticlePosition);
			Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto, strategy, pageable);
			coeArticleDto.setPositionPage(positionPage);
			
			//若该位置没有推荐文章或少于4篇，从分类为【插旗行动】中获取最新发布的文章(4-size)篇
			//Page<CoeCategArticle> categoryPage = 
			findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_FLAG_ACTION, 0, 6);
			//coeArticleDto.setCategoryPage(categoryPage);
			return coeArticleDto;
		}
		
		
		//每日新荐
		//每日新荐 面板,从位置为[每日新荐]获取数据
		public CoeArticlePageDto fetchRecommendForMainCircle(CoeArticlePosition coeArticlePosition, Integer strategy) {
			//Long userId = coeArticlePosition.getUserId();//可指定用户
			//从 行动-插旗行动获取数据
			Integer ponPanel=Position.PANEL_2_RECOMMEND.getValueInt();
			coeArticlePosition.setPonPanel(ponPanel);
			Pageable pageable = new PageRequest(0,1, Direction.DESC, "topLevel","refreshTime");
			
			CoeArticlePageDto coeArticleDto = new CoeArticlePageDto();
			coeArticleDto.setPosition(coeArticlePosition);
			Page<CoeArticlePosition> positionPage = fetchPositionPage(coeArticleDto, strategy, pageable);
			coeArticleDto.setPositionPage(positionPage);
			
			//若该位置没有推荐文章或少于1篇，从分类为【技能】中获取最新发布的文章(1-size)篇
			//Page<CoeCategArticle> categoryPage = 
			findCategoryPageFilter(coeArticleDto, Category.CODE_ARTICLE_PROFESSION, 0, 1);
			//coeArticleDto.setCategoryPage(categoryPage);
			return coeArticleDto;
		}
		
		
		/**
		 * 分类页 通用数据获取，点击more时触发
		 * @param strategy 数据获取策略
		 * @param coeArticlePosition 位置
		 * @param positionPageable 位置分页，固定，6条数据（分类页面展示推荐:上下各三条）
		 * @param coeCategArticle  分类
		 * @param categoryPageable 分类分页
		 * @return
		 */
		public CoeArticlePageDto fetchCommonPage(Integer strategy, CoeArticlePageDto coeArticlePageDto, Pageable positionPageable,  Pageable categoryPageable) {
			if(coeArticlePageDto ==null) {
				coeArticlePageDto = new CoeArticlePageDto(); 
			}
			CoeArticlePosition coeArticlePosition = coeArticlePageDto.getPosition();
			CoeCategArticle coeCategArticle = coeArticlePageDto.getCategory();
			if(STRATEGY_DEFAULT.equals(strategy)) {
				Page<CoeArticlePosition> positionPage = null;
				if(null != coeArticlePosition && null != positionPageable) {
					positionPage = fetchCommonPositionPage(coeArticlePageDto, strategy, positionPageable);
					coeArticlePageDto.setPositionPage(positionPage);
				}
				if(null != coeCategArticle && null != categoryPageable) {
					List<Long> excludeMainIdList = null;
					if(null != positionPage && positionPage.getTotalElements()>0) {
						List<CoeArticlePosition> positionList = positionPage.getContent();
						excludeMainIdList = new ArrayList<>(positionList.size());
						for(CoeArticlePosition position : positionList) {
							excludeMainIdList.add(position.getMainId());
							
						}
					}
					Page<CoeCategArticle> categoryPage = fetchCommonCategoryPage(coeArticlePageDto,excludeMainIdList, categoryPageable);
					coeArticlePageDto.setCategoryPage(categoryPage);
				}
				
			}
			
			return coeArticlePageDto;
		}
		
		public Page<CoeArticlePosition> fetchCommonPositionPage(CoeArticlePageDto coeArticlePageDto, Integer strategy,Pageable positionPageable){
			return fetchPositionPage(coeArticlePageDto, strategy, positionPageable);
		}
		
		public Page<CoeCategArticle> fetchCommonCategoryPage(CoeArticlePageDto coeArticlePageDto , List<Long> excludeMainIdList,  Pageable categoryPageable) {
			boolean withCategoryChildren=true;
			return findCategoryPage(coeArticlePageDto, withCategoryChildren, excludeMainIdList, categoryPageable, true, true, true);
		}
	
	/**
	 * @param positionPage
	 * @param coeArticleDto
	 */
	private Page<CoeCategArticle> findCategoryPageFilter(CoeArticlePageDto coeArticleDto, String categoryCode, int page, int maxSize) {
		long total = 0;
		Page<CoeArticlePosition> positionPage = coeArticleDto.getPositionPage();
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
			List<CoeArticlePosition> contentList = positionPage.getContent();
			for(CoeArticlePosition content : contentList) {
				Long mainId = content.getMainId();
				idList.add(mainId);
			}
		}
		Page<CoeCategArticle> categoryPage = null;
		CoeCategArticle coeCategArticle = new CoeCategArticle();
		Category category = new Category();
		coeCategArticle.setCategory(category);
		category.setCode(categoryCode);
		coeArticleDto.setCategory(coeCategArticle);
		if(total < maxSize) {
			//若该位置没有推荐文章或少于maxSize篇，从分类为【XXX】中获取最新发布的文章(maxSize-total)篇
			Pageable catePageable = new PageRequest(page,size, Direction.DESC, "article.refreshTime");
			LOGGER.debug("---------findCategoryPage: categoryCode:{} ----",categoryCode);
			categoryPage = findCategoryPage(coeArticleDto,true, idList, catePageable, true, true, false);
			//LOGGER.debug("---------findCategoryPage: categoryCode:{}, categoryPage.totalElements:{} ----",categoryCode, categoryPage.getTotalElements());
		}else {
			Pageable catePageable = new PageRequest(page,maxSize, Direction.DESC, "article.refreshTime");
			categoryPage = new PageResult<>(new ArrayList<>(0), catePageable, 0);
		}
		coeArticleDto.setCategoryPage(categoryPage);
		
		return categoryPage;
	}
	
	
	
	
	public PositionArticleAdaptor getPositionArticleAdaptor() {
		return positionArticleAdaptor;
	}
	public void setPositionArticleAdaptor(PositionArticleAdaptor positionArticleAdaptor) {
		this.positionArticleAdaptor = positionArticleAdaptor;
	}
	public FlagFetcherConfig getFlagFetcherConfigQX() {
		return flagFetcherConfigQX;
	}
	public void setFlagFetcherConfigQX(FlagFetcherConfig flagFetcherConfigQX) {
		this.flagFetcherConfigQX = flagFetcherConfigQX;
	}
	public FlagFetcherConfig getFlagFetcherConfigXF() {
		return flagFetcherConfigXF;
	}
	public void setFlagFetcherConfigXF(FlagFetcherConfig flagFetcherConfigXF) {
		this.flagFetcherConfigXF = flagFetcherConfigXF;
	}
	public FlagFetcherConfig getRoadLineFetcherConfigQX() {
		return roadLineFetcherConfigQX;
	}
	public void setRoadLineFetcherConfigQX(FlagFetcherConfig roadLineFetcherConfigQX) {
		this.roadLineFetcherConfigQX = roadLineFetcherConfigQX;
	}
	
	

}
