/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.position;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.BaseEntityExtendLong;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * <pre>
 * @formatter:off
 *
 * 通用位置定义
 * TODO 要为每个位置定义 最大显示数量，最大允许添加数量
 * @author bruce yang at 2018-09-10 19:52:40
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-10	basic init
 * 
 * @formatter:on
 * </pre>
 */

@Entity
@Table(name="s9_Position")
public class Position extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -4795277758856470669L;
	
	public static final PropertyContext STATUS=L.initProperty("位置状态", "选择位置状态", "位置状态", "位置状态，开启时会展现", "status", Position.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext STATUS_0_INIT=STATUS.initLabel(0, "待审","Init", "位置状态：待审");
	public static final LabelContext STATUS_1_OPEN=STATUS.initLabel(1, "开启","Open", "位置状态：开启");
	public static final LabelContext STATUS_2_CLOSED=STATUS.initLabel(2, "关闭","Close", "位置状态：关闭");
	
	public static final PropertyContext LAYOUT=L.initProperty("布局选择", "选择布局", "布局", "布局，选择布局", "layout", Position.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext LAYOUT_0_NO=LAYOUT.initLabel(0, "默认","default", "默认,无布局用于数据关联");//
	public static final LabelContext LAYOUT_1_IMG_WORD_ROLLER=LAYOUT.initLabel(1, "图文滚播","img_word_roller_h", "图文滚播");
	public static final LabelContext LAYOUT_2_IMG_3_ABREAST_ROLLER=LAYOUT.initLabel(2, "一文三图横列","img_3_abreast", "一文三图横列多文滚播");
	public static final LabelContext LAYOUT_3_IMG_1_SCREEN=LAYOUT.initLabel(3, "单图大标题","img_1_screen", "单图大标题");
	public static final LabelContext LAYOUT_4_IMG_AD_LIST=LAYOUT.initLabel(4, "缩略图列表","img_ad_list", "缩略图列表");
	public static final LabelContext LAYOUT_5_WORD_LIST=LAYOUT.initLabel(5, "文字列表","word_list", "文字列表");
	public static final LabelContext LAYOUT_6_IMG_WORD_ROLLER=LAYOUT.initLabel(6, "图文纵滚","img_word_roller_v", "图文纵滚");
	public static final LabelContext LAYOUT_7_SINGLE_WORD_IMG_ROLLER=LAYOUT.initLabel(7, "单篇图滚","single_word_img_roller", "单篇图滚");
	public static final LabelContext LAYOUT_8_IMG_2_ABREAST_LIST=LAYOUT.initLabel(8, "两图并排列表","img_2_abreast_list", "两图并排列表");
	public static final LabelContext LAYOUT_9_IMG_WORD_2_ABREAST_LIST=LAYOUT.initLabel(9, "图文并排列表","img_word_2_abreast_list", "图文并排列表");
	public static final LabelContext LAYOUT_10_IMG_3_ABREAST_WORD_ROLLER=LAYOUT.initLabel(10, "一文三图分上下 ","img_3_abreast_word_roller", "一文三图分上下，邀请人技能展示");
	public static final LabelContext LAYOUT_11_IMG_2_WORD_SINGLE=LAYOUT.initLabel(10, "一文三图分上下 ","img_2_word_single", "一文三图分上下，邀请人技能展示");
	
	//每个面板单独对应一个Position，唯一
	public static final PropertyContext PANEL=L.initProperty("展示面板", "选择展示面板", "面板", "展示面板，选择展示面板", "panel", Position.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PANEL_0_NO=PANEL.initLabel(0, "无","no", "未分配");
	public static final LabelContext PANEL_1_HEAD=PANEL.initLabel(1, "头部","head", "位置:头部");
	public static final LabelContext PANEL_2_RECOMMEND=PANEL.initLabel(2, "每日一荐","recommend", "位置：每日一荐，圆形菜单：荐 按钮");
	public static final LabelContext PANEL_3_EXPLORER=PANEL.initLabel(3, "探险者","explorer", "位置：探险者");
	public static final LabelContext PANEL_4_TRAVEL=PANEL.initLabel(4, "旅行","travel", "位置：旅行");
	public static final LabelContext PANEL_5_SPECIAL_REMEMBER=PANEL.initLabel(5, "独家记忆","special_remember", "位置：独家记忆");
	
	public static final LabelContext PANEL_6_FLAG_QX=PANEL.initLabel(6, "群侠旗帜","flag_qx", "位置：群侠旗帜，用于首页展示旗帜的地方");
	public static final LabelContext PANEL_16_FLAG_XF=PANEL.initLabel(16, "雪峰旗帜","flag_xf", "位置：雪峰旗帜，用于首页展示旗帜的地方");
	
	public static final LabelContext PANEL_7_ROADLINE=PANEL.initLabel(7, "线路","article_roadline", "位置：线路,对应分类线路");
	
	public static final LabelContext PANEL_8_EAT=PANEL.initLabel(8, "舌尖上的美味","eat", "位置：舌尖上的美味，对应 吃货聚焦");
	public static final LabelContext PANEL_9_EAT_FARMSTAY=PANEL.initLabel(9, "农○家○乐","eat_farmstay", "位置：农家乐，对应 农家乐");
	public static final LabelContext PANEL_10_EAT_TOGETHOR=PANEL.initLabel(10, "合→家←欢","eat_togethor", "位置：合家欢，对应 合家欢");
	public static final LabelContext PANEL_11_EAT_OUTDOOR=PANEL.initLabel(11, "野●餐●团","eat_outdoor", "位置：野餐团，对应 野餐团");
	
	public static final LabelContext PANEL_12_EQUIP=PANEL.initLabel(12, "装备篇","article_equip", "位置：装备篇");
	public static final LabelContext PANEL_13_EQUIP_REAL=PANEL.initLabel(13, "实战检验","article_equip_real", "位置：装备篇，对应 实战检验");
	public static final LabelContext PANEL_14_EQUIP_POPULAR=PANEL.initLabel(14, "大众流行","article_equip_popular", "位置：装备篇，对应 大众流行");
	
	public static final LabelContext PANEL_15_ACTION_FLAG=PANEL.initLabel(15, "插旗行动","articel_action_flag", "位置：行动，对应 插旗行动");
	
	public static final LabelContext PANEL_17_SHARED_FOOT=PANEL.initLabel(17, "徒步","article_shared_foot", "位置：徒步，对应 分类徒步");
	public static final LabelContext PANEL_18_SHARED_BIKE=PANEL.initLabel(18, "骑行","article_shared_bike", "位置：骑行，对应 分类骑行");
	public static final LabelContext PANEL_19_SHARED_TRAVEL=PANEL.initLabel(19, "旅行","article_shared_travel", "位置：行动，对应 分类旅行");
	public static final LabelContext PANEL_20_SHARED_WELFARE=PANEL.initLabel(20, "公益","article_shared_welfare", "位置：公益,对应分类公益");
	
	
	public static final LabelContext PANEL_PROFESSION_INVITOR=PANEL.initLabel(30, "邀请者技能","profession_invitor", "位置：邀请者技能");
	public static final LabelContext PANEL_PROFESSION_RECOMMEND=PANEL.initLabel(31, "技能推荐","profession_recommend", "位置：技能推荐");
	public static final LabelContext PANEL_PROFESSION_TODAY=PANEL.initLabel(32, "今日神技","profession_today", "位置：今日神技");
	public static final LabelContext PANEL_PROFESSION_ARTISAN=PANEL.initLabel(33, "能工巧匠","profession_major_artisan", "位置：能工巧匠");
	public static final LabelContext PANEL_PROFESSION_KILL=PANEL.initLabel(34, "必杀","profession_kill", "位置：必杀");
	public static final LabelContext PANEL_PROFESSION_INGENUITY=PANEL.initLabel(35, "独具匠心","profession_major_ingenuity", "位置：独具匠心");
	public static final LabelContext PANEL_PROFESSION_OUTDOOR=PANEL.initLabel(36, "出行","profession_outdoor", "位置：出行");
	public static final LabelContext PANEL_PROFESSION_MAJOR=PANEL.initLabel(37, "八仙过海","profession_major", "位置：八仙过海");
	public static final LabelContext PANEL_PROFESSION_MAJOR_FACTORY=PANEL.initLabel(38, "直供","profession_major_factory", "位置：直供");
	public static final LabelContext PANEL_PROFESSION_LIFE=PANEL.initLabel(39, "生活","profession_life", "位置：生活");
	public static final LabelContext PANEL_PROFESSION_RELAX=PANEL.initLabel(40, "乐趣","profession_relax", "位置：乐趣");
	public static final LabelContext PANEL_PROFESSION_NATIVE=PANEL.initLabel(41, "特产","profession_native", "位置：特产");
	public static final LabelContext PANEL_PROFESSION_MAJOR_EQUIPMENT=PANEL.initLabel(42, "装备","profession_outdoor_equipment", "位置：装备");
	
	//首页头部，每日推荐，探险者，分类头部，首页尾部，分类尾部
	public static final LabelContext PANEL_ACTIVITY_JOIN=PANEL.initLabel(100, "户外","activity_join", "位置：活动");
	public static final LabelContext PANEL_ACTIVITY_JOIN_RECOMMEND=PANEL.initLabel(101, "推荐","activity_join_recommend", "位置：活动>推荐,对应分类推荐");
	public static final LabelContext PANEL_ACTIVITY_JOIN_FOOT=PANEL.initLabel(102, "徒步","activity_join_foot", "位置：活动>徒步,对应分类徒步");
	public static final LabelContext PANEL_ACTIVITY_JOIN_BIKE=PANEL.initLabel(103, "骑行","activity_join_bike", "位置：活动>骑行,对应分类骑行");
	public static final LabelContext PANEL_ACTIVITY_JOIN_TRAVEL=PANEL.initLabel(104, "旅行","activity_join_travel", "位置：活动>旅行,对应分类旅行");
	public static final LabelContext PANEL_ACTIVITY_JOIN_CAR=PANEL.initLabel(105, "自驾","activity_join_car", "位置：活动>自驾,对应分类自驾");
	public static final LabelContext PANEL_ACTIVITY_JOIN_FREE=PANEL.initLabel(106, "自由行","activity_join_free", "位置：活动>自由行,对应分类自由行");
	public static final LabelContext PANEL_ACTIVITY_JOIN_EAT=PANEL.initLabel(107, "聚餐","activity_join_eat", "位置：活动>聚餐,对应分类聚餐");
	public static final LabelContext PANEL_ACTIVITY_JOIN_PICK=PANEL.initLabel(108, "采摘","activity_join_pick", "位置：活动>采摘,对应分类采摘");
	public static final LabelContext PANEL_ACTIVITY_JOIN_WELFARE=PANEL.initLabel(109, "公益","activity_join_welfare", "位置：活动>公益,对应分类公益");
	public static final LabelContext PANEL_ACTIVITY_JOIN_VOLUNTEER=PANEL.initLabel(110, "志愿者","activity_join_volunteer", "位置：活动>志愿者,对应分类志愿者");
	public static final LabelContext PANEL_ACTIVITY_JOIN_FLAG=PANEL.initLabel(111, "队旗","activity_join_flag", "位置：活动>队旗,对应分类队旗");
	public static final LabelContext PANEL_ACTIVITY_JOIN_OTHER=PANEL.initLabel(901, "其他","activity_join_other", "位置：活动>其他");
	public static final LabelContext PANEL_ACTIVITY_JOIN_PP1=PANEL.initLabel(901, "占位符1","activity_join_PP1", "位置：活动>占位符1");
	public static final LabelContext PANEL_ACTIVITY_JOIN_PP2=PANEL.initLabel(902, "占位符2","activity_join_PP2", "位置：活动>占位符2");
	public static final LabelContext PANEL_ACTIVITY_JOIN_PP3=PANEL.initLabel(903, "占位符3","activity_join_PP3", "位置：活动>占位符3");
	
	public static final PropertyContext SUBID=L.initProperty("选择展示页面", "选择页面", "页面", "展示页面，选择展示页面", "subId", Position.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext SUBID_0_NO=SUBID.initLabel(0, "无","no", "未分配");//未分配
	public static final LabelContext SUBID_1_INDEX=SUBID.initLabel(1, "首页","index", "首页");//首页
	public static final LabelContext SUBID_2_CATEGORY=SUBID.initLabel(2, "分类页","category", "分类页");//分类页
	public static final LabelContext SUBID_3_DETAIL=SUBID.initLabel(3, "详细页","detail", "详细页");//详细页
	public static final LabelContext SUBID_4_DATAREF=SUBID.initLabel(4, "数据关联","dataRef", "数据关联");//数据关联，不做展示
	
	
	public static String getLabel(Integer value, String property) {
		return L.getLabel(value, property, Position.class.getName());
	}
	
	public static Map<Integer,LabelContext> getValues(String property){
		return L.getValues( property, Position.class.getName());
	}
	
	@Transient
	private List<PositionRef> positionRefs;
	
	@Transient
	private Page<PositionRef> positionRefPage;
	
	@Transient
	private PositionExtend extend;
	
	@Column(name="extendId", length = 20, columnDefinition = "BIGINT(20) COMMENT '位置模块扩展Id'")
	private Long extendId;
	
	@Column(name="positionId",columnDefinition="BIGINT(20) COMMENT '该id颁发给调用者'")
	private Long positionId;//位置id,用户申请之后产生此id,后续用户可根据此id来上传图片
	
	@Column(name="mainId", length = 20, columnDefinition = "BIGINT(20) COMMENT '圈子id, 系统id'")
	private Long mainId;//position是圈子时,存圈子id; position是system时，存平台id(platId)
	
	@Column(name="subId", length = 20, columnDefinition = "BIGINT(20) COMMENT 'subId'")
	private Long subId;//可以是其下具体某个页面id，如首页，分类页
	
	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '位置所属用户Id'")
	private Long userId;//位置所属用户
	
	@Column(name="bannerImage",columnDefinition="varchar(255) COMMENT '头部图片'")
	private String bannerImage;
	
	@Column(name="layout",columnDefinition="int(3) COMMENT '布局定义'")
	private Integer layout;
	
	@Column(name="panel",columnDefinition="int(3) COMMENT '栏目定义'")
	private Integer panel;
	
	@Column(name="status",columnDefinition="int(1) COMMENT '位置状态，0待审 1 开启 2关闭'")
	private Integer status;
	
	@Column(name="name",columnDefinition="char(16) COMMENT '栏目名称'")
	private String name; //栏目名称 SUBID.label
	
	@Column(name="title",columnDefinition="char(16) COMMENT '栏目标题'")
	private String title;//栏目标题 PANEL.label
	
	@Column(name="description",columnDefinition="varchar(255) COMMENT '栏目描述'")
	private String description;
	
	@Column(name="link",columnDefinition="varchar(255) COMMENT '栏目链接导向'")
	private String link; //链接导向
	
	
	@Column(name="openTime",columnDefinition="DATETIME COMMENT '开启时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date openTime;//开启时间
	
	@Column(name="closedTime",columnDefinition="DATETIME COMMENT '关闭时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date closedTime;//关闭时间

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public Integer getLayout() {
		return layout;
	}

	public void setLayout(Integer layout) {
		this.layout = layout;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(Date closedTime) {
		this.closedTime = closedTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public PositionExtend getExtend() {
		return extend;
	}

	public void setExtend(PositionExtend extend) {
		this.extend = extend;
	}

	public Long getExtendId() {
		return extendId;
	}

	public void setExtendId(Long extendId) {
		this.extendId = extendId;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public List<PositionRef> getPositionRefs() {
		return positionRefs;
	}

	public void setPositionRefs(List<PositionRef> positionRefs) {
		this.positionRefs = positionRefs;
	}

	public Integer getPanel() {
		return panel;
	}

	public void setPanel(Integer panel) {
		this.panel = panel;
	}

	public Page<PositionRef> getPositionRefPage() {
		return positionRefPage;
	}

	public void setPositionRefPage(Page<PositionRef> positionRefPage) {
		this.positionRefPage = positionRefPage;
	}


}
