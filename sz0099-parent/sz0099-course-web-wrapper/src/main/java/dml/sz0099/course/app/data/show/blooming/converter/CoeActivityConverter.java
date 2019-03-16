/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.show.blooming.converter;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.jit4j.app.module.define.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import dml.sz0099.course.app.client.resolver.media.PhotoUtil;
import dml.sz0099.course.app.client.wrapper.activity.CoeActivityWrapper;
import dml.sz0099.course.app.client.wrapper.user.CoeUserBindWrapper;
import dml.sz0099.course.app.core.util.LinkUtil;
import dml.sz0099.course.app.data.show.blooming.CircleItem;
import dml.sz0099.course.app.data.show.blooming.CircleMain;
import dml.sz0099.course.app.data.show.blooming.config.MainCircleConfig;
import dml.sz0099.course.app.data.show.layout.fetcher.CoeActivityFetcher;
import dml.sz0099.course.app.data.show.layout.fetcher.dto.CoeActivityPageDto;
import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
import dml.sz0099.course.app.persist.entity.activity.PhotoBanner;
import dml.sz0099.course.app.persist.entity.activity.PhotoCover;
import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserBind;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-04 17:12:09
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-04	basic init
 * 
 * @formatter:on
 * </pre>
 */
public class CoeActivityConverter {
	
	@Autowired
	private CoeUserBindWrapper coeUserBindWrapper;
	
	@Autowired
	private CoeActivityWrapper coeActivityWrapper;
	
	
	private MainCircleConfig mainCircleConfig;
	
	@Autowired
	private CoeActivityFetcher coeActivityFetcher;
	
	public CircleMain convert(List<CoeActivity> contentList , CoeUser currentLoginUser) {
		CircleMain circleMain = new CircleMain();
		List<CircleItem> itemList = new ArrayList<>();
		circleMain.setItemList(itemList);
		if(null != contentList && !contentList.isEmpty()) {
			convert(contentList, circleMain);
		}else {
			String headImg = currentLoginUser.getHeadImg();
			if(StringUtils.isBlank(headImg)) {
				headImg=mainCircleConfig.getRandomHeadImageUrl();
			}
			circleMain.setImageUrl(headImg);
			circleMain.setLabel(mainCircleConfig.getRandomLabel());
			circleMain.setUserId(currentLoginUser.getUserId());
			circleMain.setTitle(currentLoginUser.getNickname());
		}
		//增加“隐”按钮 缩小至顶部
		addItem(circleMain, initItemForYin());
		
		//增加“换”按钮 换一个人显示
		addItem(circleMain, initItemForHuan(circleMain.getUserId()));
		
		//增加“绑”按钮  跳转至技能绑定列表页
		addItem(circleMain, initItemForBang(circleMain.getUserId()));
		
		//增加“新”按钮  最新发布的一篇文章
		addItem(circleMain, initItemForXin(circleMain.getUserId()));
		
		//增加‘荐’按钮
		addItem(circleMain, initItemForJian(circleMain.getUserId()));
		
		//增加“联”按钮  跳转至与该技能相关联的标签搜索列表页
		
		//增加1-2枚广告按钮
		
		
		circleMain.setSize(itemList.size());
		return circleMain;
	}

	public CircleMain convert(List<CoeActivity> contentList, CircleMain circleMain){
		if(circleMain==null) {
			circleMain = new CircleMain();
		}
		List<CircleItem> itemList = circleMain.getItemList();
		if(itemList==null) {
			itemList = new ArrayList<>();
			circleMain.setItemList(itemList);
		}
		if(null != contentList && !contentList.isEmpty()) {
			int i=0;
			for(CoeActivity content : contentList) {
				if(i==0) {
					//CoeUser user = content.getAuthor();
					String headImg = content.getHeadImg();
					if(StringUtils.isBlank(headImg)) {
						headImg=mainCircleConfig.getRandomHeadImageUrl();
					}
					circleMain.setImageUrl(headImg);
					circleMain.setLabel(mainCircleConfig.getRandomLabel());
					//circleMain.setTitle(user.getNickname());
					//circleMain.setUserId(user.getUserId());
					circleMain.setUserId(content.getUserId());
					circleMain.setTitle(content.getNickname());
					
					
				}
				i++;
				CircleItem circleItem = new CircleItem();
				itemList=circleMain.getItemList();
				itemList.add(circleItem);
				
				String link = content.getOriginalLink();
				if(null==link) {
					link="";
				}
				circleItem.setLink(link);
				circleItem.setItemType(CircleItem.ITEMTYPE_VIEW);
				circleItem.setShowLabel(CircleItem.SHOWLABEL_YES);
				circleItem.setBtnType(CircleItem.BTNTYPE_NORMAL);
				
				
				List<CoeActivityTag> tagList = content.getActivityTagList();
				if(null != tagList && !tagList.isEmpty()) {
					CoeActivityTag tag = tagList.get(0);
					circleItem.setLabel(tag.getName());
				}
				String label = circleItem.getLabel();
				if(null==label) {
					circleItem.setLabel("");
				}
				List<PhotoCover> coverList = content.getCoverList();
				if(null != coverList && !coverList.isEmpty()) {
					PhotoCover cover = coverList.get(0);
					circleItem.setImageUrl(PhotoUtil.getShowUrlForExpected2(cover.getExpectedUrl(), 100, cover.getWidth()));
				}else {
					List<PhotoBanner> bannerList = content.getBannerList();
					if(null != bannerList && !bannerList.isEmpty()) {
						PhotoBanner banner = bannerList.get(0);
						circleItem.setImageUrl(PhotoUtil.getShowUrlForExpected2(banner.getExpectedUrl(), 100, banner.getWidth()));
					}
				}
			}
		}
		return circleMain;
	}
	
	public CircleItem initItemForYin() {
		return initItem(mainCircleConfig.getRandomYinImageUrl(),"隐","",CircleItem.ITEMTYPE_CLICK, CircleItem.SHOWLABEL_NO, CircleItem.BTNTYPE_CLOSE);
	}
	
	public List<CircleItem> initItemForHuan(Long userId) {
		//换谁显示？规则 TODO
		//1.可将推广的技能链接绑定到邀请人，展示与该邀请人绑定的推广用户技能，可由后台位置 技能进行绑定
		//2.如查询不到，则随机取一个技能链接
		CoeUserBind coeUserBind = new CoeUserBind();
		coeUserBind.setUserId(userId);
		List<CircleItem> itemList = null;
		Page<CoeUserBind> page = coeUserBindWrapper.findPageForHuanSingle(coeUserBind);
		if(null != page && page.getTotalElements()>0) {
			List<CoeUserBind> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				itemList = new ArrayList<>(content.size());
				for(CoeUserBind bind : content) {
					
					Long bindId = bind.getBindId();
					CircleItem item = initItem(mainCircleConfig.getRandomHuanImageUrl(),"换",mainCircleConfig.getLinkHuan()+bindId,CircleItem.ITEMTYPE_CLICK, CircleItem.SHOWLABEL_YES , CircleItem.BTNTYPE_HUAN);
					item.setUserId(bindId);
					itemList.add(item);
				}
			}
		}else {
			//没有绑定人（即没有推广者），查询位置下的技能推广列表，取一个
			 Page<CoeActivity> coeActivityPage = coeActivityWrapper.findPageForRandomUserId(userId);
			 if(null != coeActivityPage && coeActivityPage.getTotalElements()>0) {
				 List<CoeActivity> content = coeActivityPage.getContent();
					if(null != content && !content.isEmpty()) {
						itemList = new ArrayList<>(content.size());
						for(CoeActivity p : content) {
							
							Long buyerId = p.getUserId();//此处是购买此位置的用户id，默认值为该位置未被购买时是技能所有者的id
							CircleItem item = initItem(mainCircleConfig.getRandomHuanImageUrl(),"换",mainCircleConfig.getLinkHuan()+buyerId,CircleItem.ITEMTYPE_CLICK, CircleItem.SHOWLABEL_YES , CircleItem.BTNTYPE_HUAN);
							item.setUserId(buyerId);
							itemList.add(item);
						}
					}
			 }
		}
		
		return itemList;
	}
	
	public CircleItem initItemForBang(Long userId) {
		String bang="#";
		if(null != userId) {
			bang = mainCircleConfig.getLinkBang() + userId;
		}
		return initItem(mainCircleConfig.getRandomBangImageUrl(),"技",bang,CircleItem.ITEMTYPE_VIEW, CircleItem.SHOWLABEL_YES, CircleItem.BTNTYPE_NORMAL);
	}
	
	/**
	 * 查找该用户最新发布的一篇文章
	 * @param userId
	 * @return
	 */
	public CircleItem initItemForXin(Long userId) {
		String xin="#";
		if(null != userId) {
			xin = mainCircleConfig.getLinkXin() + userId;
		}
		return initItem(mainCircleConfig.getRandomXinImageUrl(),"新",xin,CircleItem.ITEMTYPE_VIEW, CircleItem.SHOWLABEL_YES, CircleItem.BTNTYPE_NORMAL);
	}
	
	public CircleItem initItemForJian(Long userId) {
		String jian="#";
		CoeActivityPosition recommendPosition = new CoeActivityPosition();
		recommendPosition.setPonMainId(Robot.ROBOT_PLAT.getId());
		recommendPosition.setPonSubId(Long.valueOf(Position.SUBID_1_INDEX.getValueInt()));
		CoeActivityPageDto recommendDto = coeActivityFetcher.fetchRecommendForMainCircle(recommendPosition, CoeActivityFetcher.STRATEGY_DEFAULT);
		
		 Page<CoeCategActivity> categoryPage=recommendDto.getCategoryPage();
		 Page<CoeActivityPosition> positionPage=recommendDto.getPositionPage();
		 if(null != positionPage && positionPage.getTotalElements()>0) {
			 CoeActivityPosition activity = positionPage.getContent().get(0);
			 jian=LinkUtil.getLink(activity.getLink(), activity.getOriginalLink(), jian);
		 }else if(null != categoryPage && categoryPage.getTotalElements()>0) {
			//获取最新一篇 分类为[技能]文章
			 CoeCategActivity category = categoryPage.getContent().get(0);
			 CoeActivity activity = category.getActivity();
			 jian=LinkUtil.getLink(activity.getLink(), activity.getOriginalLink(), jian);
		 }else {
			 if(null != userId) {
				 //获取主推
				 jian = mainCircleConfig.getLinkJian() + userId;
				 CoeActivity mainType = coeActivityWrapper.findMainTypeByUserId(userId);
				 if(null != mainType) {
					 jian=LinkUtil.getLink(mainType.getLink(), mainType.getOriginalLink(), jian);
				 }
			}
		 }
		
		return initItem(mainCircleConfig.getRandomJianImageUrl(),"荐",jian,CircleItem.ITEMTYPE_VIEW, CircleItem.SHOWLABEL_YES, CircleItem.BTNTYPE_NORMAL);
	}
	
	public CircleItem initItem(String imageUrl, String label, String link, int itemType, int showLabel, int btnType) {
		CircleItem circleItem = new CircleItem(imageUrl,label, link, itemType, showLabel, btnType);
		return circleItem;
	}
	
	public  CircleMain addItem(CircleMain circleMain, List<CircleItem> circleItemList) {
		List<CircleItem> itemList = circleMain.getItemList();
		if(null != itemList && null != circleItemList && !circleItemList.isEmpty()) {
			itemList.addAll(circleItemList);
		}
		return circleMain;
	}
	
	public  CircleMain addItem(CircleMain circleMain, CircleItem circleItem) {
		if(circleMain !=null) {
			List<CircleItem> itemList = circleMain.getItemList();
			if(null == itemList) {
				itemList = new ArrayList<>();
			}
			if(circleItem==null) {
				circleItem = new CircleItem();
			}
			itemList.add(circleItem);
		}
		return circleMain;
	}

	public CoeUserBindWrapper getCoeUserBindWrapper() {
		return coeUserBindWrapper;
	}

	public void setCoeUserBindWrapper(CoeUserBindWrapper coeUserBindWrapper) {
		this.coeUserBindWrapper = coeUserBindWrapper;
	}

	public CoeActivityWrapper getCoeActivityWrapper() {
		return coeActivityWrapper;
	}

	public void setCoeActivityWrapper(CoeActivityWrapper coeActivityWrapper) {
		this.coeActivityWrapper = coeActivityWrapper;
	}

	public MainCircleConfig getMainCircleConfig() {
		return mainCircleConfig;
	}

	public void setMainCircleConfig(MainCircleConfig mainCircleConfig) {
		this.mainCircleConfig = mainCircleConfig;
	}

}
