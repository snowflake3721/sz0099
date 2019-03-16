/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.activity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.auth.UserRole;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.UserBaseEntityExtend;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * <pre>
 * @formatter:off
 *
 * 活动定义
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
@Table(name="s9_act_Activity")
public class CoeActivity extends UserBaseEntityExtend implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	public static final PropertyContext MAINTYPE=L.initProperty("活动输出类型", "选择活动输出类型", "活动输出类型", "活动输出类型，一个用户只能有一个最新活动，有且必须有且仅有一个最新活动", "mainType", CoeActivity.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext MAINTYPE_0_NORMAL=MAINTYPE.initLabel(0, "历史活动","N", "历史活动");
	public static final LabelContext MAINTYPE_9_MAIN=MAINTYPE.initLabel(9, "最新活动","M", "最新活动");
	
	public static final PropertyContext PUBLISH_STATUS=L.initProperty("发布状态", "选择发布状态", "发布状态", "活动编辑时处于草稿状态，完成后再进行发布", "publishStatus", CoeActivity.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PUBLISH_STATUS_DRAFT=PUBLISH_STATUS.initLabel(1, "草稿","D", "发布状态：草稿");
	public static final LabelContext PUBLISH_STATUS_PUBLISH=PUBLISH_STATUS.initLabel(2, "已发布","P", "发布状态：发布");
	public static final LabelContext PUBLISH_STATUS_CLOSED=PUBLISH_STATUS.initLabel(3, "已关闭","C", "发布状态：关闭");
	
	public static final PropertyContext RECOMMEND=L.initProperty("推荐状态", "是否推荐", "推荐状态", "是否推荐该文", "recommend", CoeActivity.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext RECOMMEND_NO=RECOMMEND.initLabel(0, "否","N", "推荐状态：未推");
	public static final LabelContext RECOMMEND_YES=RECOMMEND.initLabel(1, "是","Y", "推荐状态：已推");
	public static final LabelContext RECOMMEND_ALL=RECOMMEND.initLabel(2, "全部","A", "推荐状态：全部");

	public static final PropertyContext ACT_STATUS=L.initProperty("活动状态", "选择活动状态", "活动状态", "活动编辑时处于草稿状态，完成后再进行发布", "actStatus", CoeActivity.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext ACT_STATUS_INIT=ACT_STATUS.initLabel(0, "等待召集","W", "活动状态：等待召集");
	public static final LabelContext ACT_STATUS_CALLING=ACT_STATUS.initLabel(1, "召集中","CA", "活动状态：召集中");
	public static final LabelContext ACT_STATUS_CALLING_END=ACT_STATUS.initLabel(2, "召集已结束","CAE", "活动状态：召集已结束");
	public static final LabelContext ACT_STATUS_RUNNING=ACT_STATUS.initLabel(3, "进行中","R", "活动状态：进行中");
	public static final LabelContext ACT_STATUS_RUNNING_END=ACT_STATUS.initLabel(4, "进行完毕","RE", "活动状态：进行完毕");
	public static final LabelContext ACT_STATUS_SUMMARY=ACT_STATUS.initLabel(5, "文章总结中","S", "活动状态：文章总结中");
	public static final LabelContext ACT_STATUS_TEAPARTY=ACT_STATUS.initLabel(6, "茶话会进行中","TP", "活动状态：茶话会进行中");
	public static final LabelContext ACT_STATUS_FINISH=ACT_STATUS.initLabel(9, "已结束","F", "活动状态：已结束");
	public static final LabelContext ACT_STATUS_CLOSED=ACT_STATUS.initLabel(10, "已关闭","CL", "活动状态：关闭");
	
	
	public static final PropertyContext PREINTRO_TYPE=L.initProperty("活动主题", "活动主题导语", "活动主题", "活动主题，按照选择的【活动主题】，可自定义填写前导语，通常是地形景观、内容主题等", "preIntroType", CoeActivity.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PREINTRO_TYPE_NO=PREINTRO_TYPE.initLabel(0, "无","NO", "导语：自定义");
	public static final LabelContext PREINTRO_TYPE_VOLUNTEER=PREINTRO_TYPE.initLabel(1, "志愿者","VOLUNTEER", "导语：志愿者");
	public static final LabelContext PREINTRO_TYPE_SELF=PREINTRO_TYPE.initLabel(5, "自定义◎◎◎","SELF", "导语：自定义");
	public static final LabelContext PREINTRO_TYPE_CLIMB=PREINTRO_TYPE.initLabel(10, "爬山","CLIMB", "导语：爬山");
	public static final LabelContext PREINTRO_TYPE_SHENZHEN=PREINTRO_TYPE.initLabel(11, "海岸线","COAST", "导语：海岸线");
	public static final LabelContext PREINTRO_TYPE_COASTCROSS=PREINTRO_TYPE.initLabel(12, "草原草甸","CRASS", "导语：草原草甸");
	public static final LabelContext PREINTRO_TYPE_CRASS=PREINTRO_TYPE.initLabel(14, "雪域高原","SNOW", "导语：雪域高原");
	public static final LabelContext PREINTRO_TYPE_CLOUDSEA=PREINTRO_TYPE.initLabel(15, "云海日落","CLOUDSEA", "导语：云海日落");
	public static final LabelContext PREINTRO_TYPE_OLDCITY=PREINTRO_TYPE.initLabel(16, "古城漫行","OLDCITY", "导语：古城漫行");
	public static final LabelContext PREINTRO_TYPE_TOGETHOR=PREINTRO_TYPE.initLabel(17, "茶话小聚","TOGETHOR", "导语：茶话小聚");
	public static final LabelContext PREINTRO_TYPE_PICNIC=PREINTRO_TYPE.initLabel(18, "野炊","PICNIC", "导语：野炊");
	public static final LabelContext PREINTRO_TYPE_LEISURE=PREINTRO_TYPE.initLabel(19, "休闲","LEISURE", "导语：休闲");
	public static final LabelContext PREINTRO_TYPE_BIKE=PREINTRO_TYPE.initLabel(20, "骑行","BIKE", "导语：骑行");
	public static final LabelContext PREINTRO_TYPE_FRIEND=PREINTRO_TYPE.initLabel(21, "交友","FRIEND", "导语：交友");
	
	
	public static final PropertyContext TEMPLATE=L.initProperty("模板类型", "模板类型", "模板类型", "模板类型:根据应用范围不同，选择模板存储类型：<br/>模版最多可设20个", "template", CoeActivity.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext TEMPLATE_NO=TEMPLATE.initLabel(0, "无","NO", "默认值：否，不设置为模板");
	public static final LabelContext TEMPLATE_USER=TEMPLATE.initLabel(10, "用户","user", "仅限当前登录用户使用");
	public static final LabelContext TEMPLATE_COMMON=TEMPLATE.initLabel(20, "公众","common", "公众，可供所有人选择使用");
	
	public static final PropertyContext CASCADEPARAG=L.initProperty("级联内容", "级联内容", "级联内容", "级联内容:通常每个活动拥有多个内容段落，可挑选设置某一段落为模板，与该活动仅密相关而对于其他活动无关的段落可不设为模板<br/>", "cascadeParag", CoeActivity.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext CASCADEPARAG_NO=CASCADEPARAG.initLabel(0, "否","NO", "否，维持内容段落现有模板类型状态不变");
	public static final LabelContext CASCADEPARAG_YES=CASCADEPARAG.initLabel(1, "是","YES", "默认值：是，将内容所有段落均设置为同一模板类型");
	
	//public static final PropertyContext DIFFICULTY=L.initProperty("难度系数", "难度系数", "难度系数", "难度系数:从0-99共100级，每10级上升一个大难度，由活动发布者定义本次活动的难度系数,<br/>", "difficulty", CoeActivity.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	//public static final LabelContext DIFFICULTY_LEVEL1=DIFFICULTY.initLabel(1, "1级","level_1", "否，维持内容段落现有模板类型状态不变");
	//public static final LabelContext DIFFICULTY_LEVEL2=DIFFICULTY.initLabel(2, "2级","YES", "默认值：是，将内容所有段落均设置为同一模板类型");

	
	public static final PropertyContext ACT_ORGANIZE=L.initProperty("组织形式", "组织形式", "组织形式", "组织形式", "actOrganize", CoeActivity.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext ACT_ORGANIZE_NO=ACT_ORGANIZE.initLabel(0, "无","NO", "组织形式：无");
	public static final LabelContext ACT_ORGANIZE_CALLING=ACT_ORGANIZE.initLabel(1, "集体","calling", "组织形式：集体");
	public static final LabelContext ACT_ORGANIZE_INVITE=ACT_ORGANIZE.initLabel(2, "约伴","invite", "组织形式：约伴");
	public static final LabelContext ACT_ORGANIZE_PARALLEL=ACT_ORGANIZE.initLabel(3, "分头行动","parallel", "组织形式：分头行动");
	public static final LabelContext ACT_ORGANIZE_PERSONAL=ACT_ORGANIZE.initLabel(4, "独立行动","personal", "组织形式：独立行动");
	public static final LabelContext ACT_ORGANIZE_SELF=ACT_ORGANIZE.initLabel(5, "自定义","self", "组织形式：自定义");
	
	
	public static String getLabel(Integer value, String property) {
		return L.getLabel(value, property, CoeActivity.class.getName());
	}
	
	public static Map<Integer,LabelContext> getValues(String property){
		return L.getValues( property, CoeActivity.class.getName());
	}
	
	@Transient
	private CoeUser author;
	
	@Transient
	private List<CoeActivityTag> activityTagList;
	
	@Transient
	private List<PhotoCover> coverList;
	
	@Transient
	private List<PhotoBanner> bannerList;
	
	@Transient
	private List<CoeCategActivity> categoryList;//支持绑定多个类别
	
	@Transient
	private Category categoryTree;//类别树，编辑时用于选择类别

	@Transient
	private List<CoeActivity> mainTypeList;//点赞用户的主活动输出：每次发布活动，把最新的一篇置为主活动
	
	@Transient
	private Page<CoeActivity> refreshPage;//全局活动刷新排行，不分主辅
	
	@Transient
	private List<CoeActivityPraise> praiseList;//点赞
	
	@Transient
	private List<UserRole> roleList;//发布者用户角色
	
	@Transient
	private Page<CoeActivityPraise> praisePage;//点赞
	
	@Transient
	private Long templateId;
	
	//@JoinColumn(insertable=false,updatable=false,name="feeId", referencedColumnName="id")
	//@ManyToOne(fetch=FetchType.EAGER)
	@Transient
	private CoeActivityFee actFee;
	
	@Column(name="feeId",length = 20, columnDefinition = "BIGINT(20) COMMENT '费用Id'")
	private Long feeId;
	
	//@JoinColumn(insertable=false,updatable=false,name="actTimeId", referencedColumnName="id")
	//@ManyToOne(fetch=FetchType.EAGER)
	@Transient
	private CoeActivityTime actTime;
	
	@Column(name="actTimeId",length = 20, columnDefinition = "BIGINT(20) COMMENT '时间Id'")
	private Long actTimeId;
	
	@Column(length = 3, columnDefinition = "INT(3) COMMENT '活动输出类型'")
	private Integer mainType;//活动主要输出类型：9为最新活动，0为历史活动，其他数字待定，通常一个用户只能有一篇为最新活动
	
	@Column(length = 3, columnDefinition = "INT(3) COMMENT '难度系数【0-99】'")
	private Integer difficulty;//活动难度系数
	
	@Column(length = 3, columnDefinition = "INT(3) COMMENT '模板：0非模板，10个人模板，20通用模板'")
	private Integer template;//活动模板
	@Column(length = 3, columnDefinition = "INT(3) COMMENT '级联内容段落：0否，1是'")
	private Integer cascadeParag;//级联活动内容段落

	
	
	@Column(name="publishStatus",columnDefinition="int(1) COMMENT '活动发布状态，1 草稿 2发布 3关闭'")
	private Integer publishStatus;
	
	@Column(name="actOrganize",columnDefinition="int(1) COMMENT '活动组织形式：0无 1召集 2约伴 3分头行动 4独立行动 5自定义'")
	private Integer actOrganize;
	
	@Column(name="actStatus",columnDefinition="int(3) COMMENT '活动过程状态，0等待召集,1召集中,2召集结束, 3进行中, 4进行完毕,5文章总结中, 6茶话会进行中,9已结束,10已关闭'")
	private Integer actStatus;
	
	@Column(name="recommend",columnDefinition="int(1) COMMENT '活动推荐状态，0否 1是'")
	private Integer recommend;
	
	@Column(name="publishTime",columnDefinition="DATETIME COMMENT '活动发布时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date publishTime;//发布时间
	
	@Column(name="refreshTime",columnDefinition="DATETIME COMMENT '活动刷新时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date refreshTime;//刷新时间
	
	//被关闭的活动数量多了，将影响用户信誉，严重者封禁帐号
	@Column(name="closedTime",columnDefinition="DATETIME COMMENT '活动关闭时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date closedTime;//活动关闭时间
	
	/**
	 * 活动aid
	 */
	@Column(name="activityNo",columnDefinition="char(10) COMMENT '活动编号'")
	private String activityNo;//活动编号
	
	@Column(name="topLevel", length = 11, columnDefinition = "INT(11) COMMENT '置顶等级，数值越大越靠前'")
	private Integer topLevel;//活动置顶等级，数值越大越靠前
	
	@Column(name="penname",columnDefinition="varchar(16) COMMENT '笔名'")
	private String penname;//笔名
	
	@Column(name="name",columnDefinition="varchar(64) COMMENT '名称'")
	private String name; //名称
	
	@Column(name="preIntro",columnDefinition="varchar(20) COMMENT '导语'")
	private String preIntro;//导语,置于标题前，靠左齐
	
	@Column(name="preIntroType", length = 3, columnDefinition = "INT(3) COMMENT '导语类型，0自定义,1系统预定义某值'")
	private Integer preIntroType;//导语类型(活动组织形式)
	
	@Column(name="subTitle",columnDefinition="varchar(32) COMMENT '副标题'")
	private String subTitle;//副标题
	
	@Column(name="title",columnDefinition="varchar(64) COMMENT '标题'")
	private String title;//标题
	
	@Column(name="titleLower",columnDefinition="varchar(64) COMMENT '标题全小写'")
	private String titleLower;//标题全小写
	
	@Column(name="description",columnDefinition="varchar(5120) COMMENT '活动描述'")
	private String description;
	
	@Column(name="originalLink",columnDefinition="varchar(255) COMMENT '活动原链接'")
	private String originalLink; //活动原链接
	
	@Column(name="link",columnDefinition="varchar(1024) COMMENT '活动导向链接'")
	private String link;//导向链接
	
	//@Column(name="sayword",length=64,columnDefinition="varchar(64) COMMENT '传说'")
	//private String sayword;//活动发布时留下传说 CoeUser.sayword
	
	@JoinColumn(insertable=false,updatable=false,name="saywordId", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Sayword sayword;//记录当时的那句传说
	
	@Column(name="saywordId",length = 20, columnDefinition = "BIGINT(20) COMMENT '传说Id'")
	private Long saywordId;//记录当时的那句传说,建立关联关系

	@Column(name="kilometer", length = 8, columnDefinition = "INT(8) COMMENT '活动里程,km'")
	private Integer kilometer;
	
	@Column(name="minNum", length = 8, columnDefinition = "INT(8) COMMENT '人数下限'")
	private Integer minNum;
	
	@Column(name="maxNum", length = 8, columnDefinition = "INT(8) COMMENT '人数上限'")
	private Integer maxNum;
	
	@Transient
	private Long participateNum;
	
	public List<PhotoCover> getCoverList() {
		return coverList;
	}

	public void setCoverList(List<PhotoCover> coverList) {
		this.coverList = coverList;
	}

	public List<PhotoBanner> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<PhotoBanner> bannerList) {
		this.bannerList = bannerList;
	}

	public List<CoeCategActivity> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CoeCategActivity> categoryList) {
		this.categoryList = categoryList;
	}

	public Category getCategoryTree() {
		return categoryTree;
	}

	public void setCategoryTree(Category categoryTree) {
		this.categoryTree = categoryTree;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}

	public Date getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(Date closedTime) {
		this.closedTime = closedTime;
	}

	public String getActivityNo() {
		return activityNo;
	}

	public void setActivityNo(String activityNo) {
		this.activityNo = activityNo;
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

	public String getTitleLower() {
		return titleLower;
	}

	public void setTitleLower(String titleLower) {
		this.titleLower = titleLower;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOriginalLink() {
		return originalLink;
	}

	public void setOriginalLink(String originalLink) {
		this.originalLink = originalLink;
	}

	public List<CoeActivityTag> getActivityTagList() {
		return activityTagList;
	}

	public void setActivityTagList(List<CoeActivityTag> activityTagList) {
		this.activityTagList = activityTagList;
	}

	public Integer getTopLevel() {
		return topLevel;
	}

	public void setTopLevel(Integer topLevel) {
		this.topLevel = topLevel;
	}

	public String getPenname() {
		return penname;
	}

	public void setPenname(String penname) {
		this.penname = penname;
	}

	public String getPreIntro() {
		return preIntro;
	}

	public void setPreIntro(String preIntro) {
		this.preIntro = preIntro;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getPreIntroType() {
		return preIntroType;
	}

	public void setPreIntroType(Integer preIntroType) {
		this.preIntroType = preIntroType;
	}

	public List<CoeActivityPraise> getPraiseList() {
		return praiseList;
	}

	public void setPraiseList(List<CoeActivityPraise> praiseList) {
		this.praiseList = praiseList;
	}

	public Page<CoeActivityPraise> getPraisePage() {
		return praisePage;
	}

	public void setPraisePage(Page<CoeActivityPraise> praisePage) {
		this.praisePage = praisePage;
	}

	public CoeUser getAuthor() {
		return author;
	}

	public void setAuthor(CoeUser author) {
		this.author = author;
	}

	public List<CoeActivity> getMainTypeList() {
		return mainTypeList;
	}

	public void setMainTypeList(List<CoeActivity> mainTypeList) {
		this.mainTypeList = mainTypeList;
	}

	public Page<CoeActivity> getRefreshPage() {
		return refreshPage;
	}

	public void setRefreshPage(Page<CoeActivity> refreshPage) {
		this.refreshPage = refreshPage;
	}

	public Integer getMainType() {
		return mainType;
	}

	public void setMainType(Integer mainType) {
		this.mainType = mainType;
	}


	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Long getSaywordId() {
		return saywordId;
	}

	public void setSaywordId(Long saywordId) {
		this.saywordId = saywordId;
	}

	public Sayword getSayword() {
		return sayword;
	}

	public void setSayword(Sayword sayword) {
		this.sayword = sayword;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Integer getActStatus() {
		return actStatus;
	}

	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}

	public Integer getActOrganize() {
		return actOrganize;
	}

	public void setActOrganize(Integer actOrganize) {
		this.actOrganize = actOrganize;
	}

	public Integer getKilometer() {
		return kilometer;
	}

	public void setKilometer(Integer kilometer) {
		this.kilometer = kilometer;
	}

	public Long getFeeId() {
		return feeId;
	}

	public void setFeeId(Long feeId) {
		this.feeId = feeId;
	}

	public CoeActivityFee getActFee() {
		return actFee;
	}

	public void setActFee(CoeActivityFee actFee) {
		this.actFee = actFee;
	}

	public Long getActTimeId() {
		return actTimeId;
	}

	public void setActTimeId(Long actTimeId) {
		this.actTimeId = actTimeId;
	}

	public CoeActivityTime getActTime() {
		return actTime;
	}

	public void setActTime(CoeActivityTime actTime) {
		this.actTime = actTime;
	}

	public List<UserRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<UserRole> roleList) {
		this.roleList = roleList;
	}

	public Integer getMinNum() {
		return minNum;
	}

	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}

	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	public Integer getTemplate() {
		return template;
	}

	public void setTemplate(Integer template) {
		this.template = template;
	}

	public Integer getCascadeParag() {
		return cascadeParag;
	}

	public void setCascadeParag(Integer cascadeParag) {
		this.cascadeParag = cascadeParag;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public Long getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(Long participateNum) {
		this.participateNum = participateNum;
	}


	

}
