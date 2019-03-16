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

import dml.sz0099.course.app.client.resolver.adaptor.position.PositionActivityAdaptor;
import dml.sz0099.course.app.client.wrapper.activity.CoeCategActivityWrapper;
import dml.sz0099.course.app.data.show.blooming.config.FlagFetcherConfig;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.CoeActivityPageDto;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
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

public class CoeActivityFetcher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeActivityFetcher.class);

	protected PositionActivityAdaptor positionActivityAdaptor;
	
	protected FlagFetcherConfig flagFetcherConfigQX;
	
	protected FlagFetcherConfig flagFetcherConfigXF;
	
	protected FlagFetcherConfig roadLineFetcherConfigQX;
	
	@Autowired
	protected CoeCategActivityWrapper coeCategActivityWrapper;
	
	/**
	 * 数据抓取策略：默认
	 */
	public static final Integer STRATEGY_DEFAULT=0;
	
	//首页大旗滚播面板
	public CoeActivityPageDto  fetchIndexFlagQX(CoeActivityPosition coeActivityPosition, Integer strategy,Pageable pageable) {
		Integer ponPanel=Position.PANEL_6_FLAG_QX.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = flagFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于2篇，从分类为【插旗行动-群侠户外】中获取最新发布的文章(6-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ARTICLE_FLAG_QX, 0, 6);
		//coeActivityDto.setCategoryPage(categoryPage);
		
		return coeActivityDto;
	}
	/**
	 * @param coeActivityPosition
	 * @param defaultActivity
	 */
	private void convert(CoeActivityPosition coeActivityPosition, CoeActivityPosition defaultActivity) {
		if(null != defaultActivity) {
			coeActivityPosition.setTitle(defaultActivity.getTitle());
			coeActivityPosition.setSubTitle(defaultActivity.getSubTitle());
			coeActivityPosition.setCoverImage(defaultActivity.getCoverImage());
			coeActivityPosition.setLink(defaultActivity.getLink());
			coeActivityPosition.setPreIntro(defaultActivity.getPreIntro());
		}
	}
	public CoeActivityPageDto fetchIndexFlagXF(CoeActivityPosition coeActivityPosition, Integer strategy,Pageable pageable) {
		Integer ponPanel=Position.PANEL_16_FLAG_XF.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = flagFetcherConfigXF.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于2篇，从分类为【插旗行动-雪峰户外】中获取最新发布的文章(2-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ARTICLE_FLAG_XF, 0, 2);
		//coeActivityDto.setCategoryPage(categoryPage);
		
		return coeActivityDto;
	}
	
	public CoeActivityPageDto fetchIndexRoadLine(CoeActivityPosition coeActivityPosition, Integer strategy,Pageable pageable) {
		Integer ponPanel=Position.PANEL_7_ROADLINE.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = roadLineFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于2篇，从分类为【攻略】中获取最新发布的文章(2-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ROADLINE_GUIDE, 0, 6);
		//coeActivityDto.setCategoryPage(categoryPage);
		
		return coeActivityDto;
	}
	
	public Page<CoeActivityPosition> fetchPositionPage(CoeActivityPageDto coeActivityPageDto, Integer strategy,Pageable pageable) {
		CoeActivityPosition coeActivityPosition = coeActivityPageDto.getPosition();
		Page<CoeActivityPosition> result = null;
		if(null != coeActivityPosition && STRATEGY_DEFAULT.equals(strategy)) {
			PositionExtend extend = positionActivityAdaptor.config();
			Long positionId = extend.getPositionId();
			//Long ponMainId=coeActivityPosition.getPonMainId();
			//Long ponSubId=coeActivityPosition.getPonSubId();
			//Integer ponPanel=coeActivityPosition.getPonPanel();
			coeActivityPosition.setPositionId(positionId);
			coeActivityPosition.setExtendId(extend.getId());
			if(null == pageable) {
				pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
			}
			LOGGER.debug("---------fetchPositionPage: positionId:{}, ponMainId:{}, ponSubId:{}, ----",positionId, coeActivityPosition.getPonSubId(), coeActivityPosition.getPonMainId());
			result = positionActivityAdaptor.findPageForPosition(coeActivityPosition, pageable);
			if(null !=result) {
				LOGGER.debug("---------fetchPositionPage: positionId:{}, ponMainId:{}, ponSubId:{}, result.totalElements:{} ----",positionId, coeActivityPosition.getPonSubId(), coeActivityPosition.getPonMainId(), result.getTotalElements());
			}
			
		}
		return result;
	}
	
	//文章首页-头条滚播面板
	public CoeActivityPageDto fetchActivityIndexHead(CoeActivityPosition coeActivityPosition, Integer strategy) {
		//Long userId = coeActivityPosition.getUserId();//可指定用户
		//从首页头条位置面板获取数据
		Integer ponPanel = Position.PANEL_1_HEAD.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto, strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【记实】中获取最新发布的文章(6-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ACTIVITY_JOIN, 0, 6);
		//coeActivityDto.setCategoryPage(categoryPage);
		return coeActivityDto;
	}
	/**
	 * @param size
	 * @param coeCategActivity
	 * @return
	 */
	public Page<CoeCategActivity> findCategoryPage(CoeActivityPageDto coeActivityPageDto, boolean withCategoryChildren, List<Long> excludeMainIdList, Pageable pageable, boolean cover, boolean banner, boolean author) {
		
		CoeCategActivity coeCategActivity = coeActivityPageDto.getCategory();
		if(null == pageable) {
			pageable = new PageRequest(0,2, Direction.DESC, "activity.refreshTime");
		}
		if(null == coeCategActivity) {
			coeCategActivity = new CoeCategActivity();
			coeActivityPageDto.setCategory(coeCategActivity);
		}
		Category category = coeCategActivity.getCategory();
		if(category == null) {
			category = new Category();
			coeCategActivity.setCategory(category);
			category.setCode(Category.CODE_ACTIVITY_JOIN);
		}
		
		Page<CoeCategActivity> categoryPage = null;
		if(withCategoryChildren) {
			categoryPage = coeCategActivityWrapper.findPageForPublishWithChildren(coeCategActivity,  excludeMainIdList, pageable, cover, banner,author);
		}else {
			categoryPage = coeCategActivityWrapper.findPageForPublish(coeCategActivity, excludeMainIdList, pageable, cover, banner,author);
		}
		return categoryPage;
	}
	
	//独家记忆面板,大屏显示
	public CoeActivityPageDto fetchSpecialRemember(CoeActivityPosition coeActivityPosition, Integer strategy) {
		//Long userId = coeActivityPosition.getUserId();//可指定用户
		//从独家记忆位置面板获取数据
		Integer ponPanel=Position.PANEL_5_SPECIAL_REMEMBER.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,1, Direction.DESC, "topLevel","refreshTime");
		
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto, strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于1篇，从分类为【独家记忆】中获取最新发布的文章(1-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_SPECIAL_REMEMBER, 0, 1);
		//coeActivityDto.setCategoryPage(categoryPage);
		
		return coeActivityDto;
	}
	
	//探险者面板,从分类为[探路行动]获取数据
	public CoeActivityPageDto fetchExplorer(CoeActivityPosition coeActivityPosition, Integer strategy) {
		//Long userId = coeActivityPosition.getUserId();//可指定用户
		//从探险者位置面板获取数据
		Integer ponPanel=Position.PANEL_3_EXPLORER.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,4, Direction.DESC, "topLevel","refreshTime");
		
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto, strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于6篇，从分类为【探路行动】中获取最新发布的文章(6-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ROADLINE_EXPLORER, 0, 4);
		//coeActivityDto.setCategoryPage(categoryPage);
			
		return coeActivityDto;
		
	}
	
	/**
	 * 获取路线攻略6条
	 * @param coeActivityPosition
	 * @param strategy
	 * @return
	 */
	public CoeActivityPageDto fetchIndexRoadLine(CoeActivityPosition coeActivityPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_7_ROADLINE.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = roadLineFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于2篇，从分类为【攻略】中获取最新发布的文章(2-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ROADLINE_GUIDE, 0, 6);
		//coeActivityDto.setCategoryPage(categoryPage);
		
		return coeActivityDto;
	}
	
	
	/**
	 * 获取join其下活动
	 * @param coeActivityPosition
	 * @param strategy
	 * @return
	 */
	public CoeActivityPageDto fetchIndexJoin(CoeActivityPosition coeActivityPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_ACTIVITY_JOIN.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = roadLineFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【集体活动】中获取最新发布的活动(6-size)篇
		findCategoryPageFilter(coeActivityDto, Category.CODE_ACTIVITY_JOIN, 0, 6);
		return coeActivityDto;
	}
	
	/**
	 * 获取徒步
	 * @param coeActivityPosition
	 * @param strategy
	 * @return
	 */
	public CoeActivityPageDto fetchIndexFoot(CoeActivityPosition coeActivityPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_ACTIVITY_JOIN_FOOT.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = roadLineFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【徒步】中获取最新发布的文章(6-size)篇
		findCategoryPageFilter(coeActivityDto, Category.CODE_ACTIVITY_JOIN_FOOT, 0, 6);
		return coeActivityDto;
	}
	
	
	/**
	 * 获取旅行
	 * @param coeActivityPosition
	 * @param strategy
	 * @return
	 */
	public CoeActivityPageDto fetchIndexTravel(CoeActivityPosition coeActivityPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_ACTIVITY_JOIN_TRAVEL.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = roadLineFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【旅行】中获取最新发布的文章(6-size)篇
		findCategoryPageFilter(coeActivityDto, Category.CODE_ACTIVITY_JOIN_TRAVEL, 0, 6);
		return coeActivityDto;
	}
	
	/**
	 * 获取骑行
	 * @param coeActivityPosition
	 * @param strategy
	 * @return
	 */
	public CoeActivityPageDto fetchIndexBike(CoeActivityPosition coeActivityPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_ACTIVITY_JOIN_BIKE.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = roadLineFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【骑行】中获取最新发布的文章(6-size)篇
		findCategoryPageFilter(coeActivityDto, Category.CODE_ACTIVITY_JOIN_BIKE, 0, 6);
		return coeActivityDto;
	}
	
	/**
	 * 获取自驾
	 * @param coeActivityPosition
	 * @param strategy
	 * @return
	 */
	public CoeActivityPageDto fetchIndexCar(CoeActivityPosition coeActivityPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_ACTIVITY_JOIN_CAR.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = roadLineFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【自驾】中获取最新发布的文章(6-size)篇
		findCategoryPageFilter(coeActivityDto, Category.CODE_ACTIVITY_JOIN_CAR, 0, 6);
		return coeActivityDto;
	}
	
	/**
	 * 获取志愿者
	 * @param coeActivityPosition
	 * @param strategy
	 * @return
	 */
	public CoeActivityPageDto fetchIndexVolunteer(CoeActivityPosition coeActivityPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_ACTIVITY_JOIN_VOLUNTEER.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = roadLineFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【志愿者】中获取最新发布的文章(6-size)篇
		findCategoryPageFilter(coeActivityDto, Category.CODE_ACTIVITY_JOIN_VOLUNTEER, 0, 6);
		return coeActivityDto;
	}
	
	/**
	 * 获取自由行
	 * @param coeActivityPosition
	 * @param strategy
	 * @return
	 */
	public CoeActivityPageDto fetchIndexFree(CoeActivityPosition coeActivityPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_ACTIVITY_JOIN_FREE.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = roadLineFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【自由行】中获取最新发布的文章(6-size)篇
		findCategoryPageFilter(coeActivityDto, Category.CODE_ACTIVITY_JOIN_FREE, 0, 6);
		return coeActivityDto;
	}
	
	/**
	 * 获取聚餐
	 * @param coeActivityPosition
	 * @param strategy
	 * @return
	 */
	public CoeActivityPageDto fetchIndexEat(CoeActivityPosition coeActivityPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_ACTIVITY_JOIN_EAT.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = roadLineFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【聚餐】中获取最新发布的文章(6-size)篇
		findCategoryPageFilter(coeActivityDto, Category.CODE_ACTIVITY_JOIN_EAT, 0, 6);
		return coeActivityDto;
	}
	
	/**
	 * 获取采摘
	 * @param coeActivityPosition
	 * @param strategy
	 * @return
	 */
	public CoeActivityPageDto fetchIndexPick(CoeActivityPosition coeActivityPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_ACTIVITY_JOIN_PICK.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = roadLineFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于6篇，从分类为【聚餐】中获取最新发布的文章(6-size)篇
		findCategoryPageFilter(coeActivityDto, Category.CODE_ACTIVITY_JOIN_PICK, 0, 6);
		return coeActivityDto;
	}
	
	/**
	 * 获取公益
	 * @param coeActivityPosition
	 * @param strategy
	 * @return
	 */
	public CoeActivityPageDto fetchIndexWelfare(CoeActivityPosition coeActivityPosition, Integer strategy) {
		Integer ponPanel=Position.PANEL_ACTIVITY_JOIN_WELFARE.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
	
		coeActivityPosition.setPonPanel(ponPanel);
		//CoeActivityPosition defaultActivity = roadLineFetcherConfigQX.getRandomFromList();
		//convert(coeActivityPosition, defaultActivity);
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto,strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		//若该位置没有推荐文章或少于2篇，从分类为【公益】中获取最新发布的文章(2-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ACTIVITY_JOIN_WELFARE, 0, 6);
		//coeActivityDto.setCategoryPage(categoryPage);
		
		return coeActivityDto;
	}
	
	//吃货--合家欢面板,从分类为[合家欢]获取数据
	public CoeActivityPageDto fetchEatTogethor(CoeActivityPosition coeActivityPosition, Integer strategy) {
		//Long userId = coeActivityPosition.getUserId();//可指定用户
		//从合家欢位置面板获取数据
		Integer ponPanel=Position.PANEL_10_EAT_TOGETHOR.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,3, Direction.DESC, "topLevel","refreshTime");
		
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto, strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于3篇，从分类为【合家欢】中获取最新发布的文章(3-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ARTICLE_EAT_TOGETHOR, 0, 3);
		//coeActivityDto.setCategoryPage(categoryPage);
			
		return coeActivityDto;
		
	}
	
	//吃货--农家乐面板,从分类为[农家乐]获取数据
	public CoeActivityPageDto fetchEatFarmstay(CoeActivityPosition coeActivityPosition, Integer strategy) {
		//Long userId = coeActivityPosition.getUserId();//可指定用户
		//从 农家乐 位置面板获取数据
		Integer ponPanel=Position.PANEL_9_EAT_FARMSTAY.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,3, Direction.DESC, "topLevel","refreshTime");
		
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto, strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于3篇，从分类为【农家乐】中获取最新发布的文章(3-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ARTICLE_EAT_FARMSTAY, 0, 3);
		//coeActivityDto.setCategoryPage(categoryPage);
			
		return coeActivityDto;
	}
	
	//吃货--野餐团面板,从分类为[野餐团]获取数据
	public CoeActivityPageDto fetchEatOutdoor(CoeActivityPosition coeActivityPosition, Integer strategy) {
		//Long userId = coeActivityPosition.getUserId();//可指定用户
		//从 野餐团 位置面板获取数据
		Integer ponPanel=Position.PANEL_11_EAT_OUTDOOR.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,3, Direction.DESC, "topLevel","refreshTime");
		
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto, strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于3篇，从分类为【野餐团】中获取最新发布的文章(3-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ARTICLE_EAT_OUTDOOR, 0, 3);
		//coeActivityDto.setCategoryPage(categoryPage);
		return coeActivityDto;
	}
	
	//装备篇面板,从分类为[装备篇]获取数据
	public CoeActivityPageDto fetchEquip(CoeActivityPosition coeActivityPosition, Integer strategy) {
		//Long userId = coeActivityPosition.getUserId();//可指定用户
		//从 装备篇 位置面板获取数据
		Integer ponPanel=Position.PANEL_12_EQUIP.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,4, Direction.DESC, "topLevel","refreshTime");
		
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto, strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于4篇，从分类为【装备篇】中获取最新发布的文章(4-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ARTICLE_EQUIP, 0, 4);
		//coeActivityDto.setCategoryPage(categoryPage);
		return coeActivityDto;
	}
	
	//装备篇-实战检验 面板,从分类为[实战检验]获取数据
	public CoeActivityPageDto fetchEquipReal(CoeActivityPosition coeActivityPosition, Integer strategy) {
		//Long userId = coeActivityPosition.getUserId();//可指定用户
		//从 装备篇 位置面板获取数据
		Integer ponPanel=Position.PANEL_13_EQUIP_REAL.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,4, Direction.DESC, "topLevel","refreshTime");
		
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto, strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于4篇，从分类为【实战检验】中获取最新发布的文章(4-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ARTICLE_EQUIP_REAL, 0, 4);
		//coeActivityDto.setCategoryPage(categoryPage);
		return coeActivityDto;
	}
	
	//装备篇-大众流行 面板,从分类为[大众流行]获取数据
	public CoeActivityPageDto fetchEquipPopular(CoeActivityPosition coeActivityPosition, Integer strategy) {
		//Long userId = coeActivityPosition.getUserId();//可指定用户
		//从 装备篇 位置面板获取数据
		Integer ponPanel=Position.PANEL_14_EQUIP_POPULAR.getValueInt();
		coeActivityPosition.setPonPanel(ponPanel);
		Pageable pageable = new PageRequest(0,4, Direction.DESC, "topLevel","refreshTime");
		
		CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
		coeActivityDto.setPosition(coeActivityPosition);
		Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto, strategy, pageable);
		coeActivityDto.setPositionPage(positionPage);
		
		//若该位置没有推荐文章或少于4篇，从分类为【大众流行】中获取最新发布的文章(4-size)篇
		//Page<CoeCategActivity> categoryPage = 
		findCategoryPageFilter(coeActivityDto, Category.CODE_ARTICLE_EQUIP_POPULAR, 0, 4);
		//coeActivityDto.setCategoryPage(categoryPage);
		return coeActivityDto;
	}
	
		//activity_action_flag
		//行动-插旗行动 面板,从分类为[插旗行动]获取数据
		public CoeActivityPageDto fetchActionFlag(CoeActivityPosition coeActivityPosition, Integer strategy) {
			//Long userId = coeActivityPosition.getUserId();//可指定用户
			//从 行动-插旗行动获取数据
			Integer ponPanel=Position.PANEL_ACTIVITY_JOIN_FLAG.getValueInt();
			coeActivityPosition.setPonPanel(ponPanel);
			Pageable pageable = new PageRequest(0,6, Direction.DESC, "topLevel","refreshTime");
			
			CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
			coeActivityDto.setPosition(coeActivityPosition);
			Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto, strategy, pageable);
			coeActivityDto.setPositionPage(positionPage);
			
			//若该位置没有推荐文章或少于4篇，从分类为【插旗行动】中获取最新发布的文章(4-size)篇
			//Page<CoeCategActivity> categoryPage = 
			findCategoryPageFilter(coeActivityDto, Category.CODE_ACTIVITY_JOIN_FLAG, 0, 6);
			//coeActivityDto.setCategoryPage(categoryPage);
			return coeActivityDto;
		}
		
		
		//每日新荐
		//每日新荐 面板,从位置为[每日新荐]获取数据
		public CoeActivityPageDto fetchRecommendForMainCircle(CoeActivityPosition coeActivityPosition, Integer strategy) {
			//Long userId = coeActivityPosition.getUserId();//可指定用户
			//从 行动-插旗行动获取数据
			Integer ponPanel=Position.PANEL_2_RECOMMEND.getValueInt();
			coeActivityPosition.setPonPanel(ponPanel);
			Pageable pageable = new PageRequest(0,1, Direction.DESC, "topLevel","refreshTime");
			
			CoeActivityPageDto coeActivityDto = new CoeActivityPageDto();
			coeActivityDto.setPosition(coeActivityPosition);
			Page<CoeActivityPosition> positionPage = fetchPositionPage(coeActivityDto, strategy, pageable);
			coeActivityDto.setPositionPage(positionPage);
			
			//若该位置没有推荐文章或少于1篇，从分类为【技能】中获取最新发布的文章(1-size)篇
			//Page<CoeCategActivity> categoryPage = 
			findCategoryPageFilter(coeActivityDto, Category.CODE_ARTICLE_PROFESSION, 0, 1);
			//coeActivityDto.setCategoryPage(categoryPage);
			return coeActivityDto;
		}
		
		
		/**
		 * 分类页 通用数据获取，点击more时触发
		 * @param strategy 数据获取策略
		 * @param coeActivityPosition 位置
		 * @param positionPageable 位置分页，固定，6条数据（分类页面展示推荐:上下各三条）
		 * @param coeCategActivity  分类
		 * @param categoryPageable 分类分页
		 * @return
		 */
		public CoeActivityPageDto fetchCommonPage(Integer strategy, CoeActivityPageDto coeActivityPageDto, Pageable positionPageable,  Pageable categoryPageable) {
			if(coeActivityPageDto ==null) {
				coeActivityPageDto = new CoeActivityPageDto(); 
			}
			CoeActivityPosition coeActivityPosition = coeActivityPageDto.getPosition();
			CoeCategActivity coeCategActivity = coeActivityPageDto.getCategory();
			if(STRATEGY_DEFAULT.equals(strategy)) {
				Page<CoeActivityPosition> positionPage = null;
				if(null != coeActivityPosition && null != positionPageable) {
					positionPage = fetchCommonPositionPage(coeActivityPageDto, strategy, positionPageable);
					coeActivityPageDto.setPositionPage(positionPage);
				}
				if(null != coeCategActivity && null != categoryPageable) {
					List<Long> excludeMainIdList = null;
					if(null != positionPage && positionPage.getTotalElements()>0) {
						List<CoeActivityPosition> positionList = positionPage.getContent();
						excludeMainIdList = new ArrayList<>(positionList.size());
						for(CoeActivityPosition position : positionList) {
							excludeMainIdList.add(position.getMainId());
							
						}
					}
					Page<CoeCategActivity> categoryPage = fetchCommonCategoryPage(coeActivityPageDto,excludeMainIdList, categoryPageable);
					coeActivityPageDto.setCategoryPage(categoryPage);
				}
				
			}
			
			return coeActivityPageDto;
		}
		
		public Page<CoeActivityPosition> fetchCommonPositionPage(CoeActivityPageDto coeActivityPageDto, Integer strategy,Pageable positionPageable){
			return fetchPositionPage(coeActivityPageDto, strategy, positionPageable);
		}
		
		public Page<CoeCategActivity> fetchCommonCategoryPage(CoeActivityPageDto coeActivityPageDto , List<Long> excludeMainIdList,  Pageable categoryPageable) {
			boolean withCategoryChildren=true;
			return findCategoryPage(coeActivityPageDto, withCategoryChildren, excludeMainIdList, categoryPageable, true, true, true);
		}
	
	/**
	 * @param positionPage
	 * @param coeActivityDto
	 */
	private Page<CoeCategActivity> findCategoryPageFilter(CoeActivityPageDto coeActivityDto, String categoryCode, int page, int maxSize) {
		long total = 0;
		Page<CoeActivityPosition> positionPage = coeActivityDto.getPositionPage();
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
			List<CoeActivityPosition> contentList = positionPage.getContent();
			for(CoeActivityPosition content : contentList) {
				Long mainId = content.getMainId();
				idList.add(mainId);
			}
		}
		Page<CoeCategActivity> categoryPage = null;
		CoeCategActivity coeCategActivity = new CoeCategActivity();
		Category category = new Category();
		coeCategActivity.setCategory(category);
		category.setCode(categoryCode);
		coeActivityDto.setCategory(coeCategActivity);
		if(total < maxSize) {
			//若该位置没有推荐文章或少于maxSize篇，从分类为【XXX】中获取最新发布的文章(maxSize-total)篇
			Pageable catePageable = new PageRequest(page,size, Direction.DESC, "activity.refreshTime");
			LOGGER.debug("---------findCategoryPage: categoryCode:{} ----",categoryCode);
			categoryPage = findCategoryPage(coeActivityDto,true, idList, catePageable, true, true, false);
			//LOGGER.debug("---------findCategoryPage: categoryCode:{}, categoryPage.totalElements:{} ----",categoryCode, categoryPage.getTotalElements());
		}else {
			Pageable catePageable = new PageRequest(page,maxSize, Direction.DESC, "activity.refreshTime");
			categoryPage = new PageResult<>(new ArrayList<>(0), catePageable, 0);
		}
		coeActivityDto.setCategoryPage(categoryPage);
		
		return categoryPage;
	}
	
	
	
	
	public PositionActivityAdaptor getPositionActivityAdaptor() {
		return positionActivityAdaptor;
	}
	public void setPositionActivityAdaptor(PositionActivityAdaptor positionActivityAdaptor) {
		this.positionActivityAdaptor = positionActivityAdaptor;
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
