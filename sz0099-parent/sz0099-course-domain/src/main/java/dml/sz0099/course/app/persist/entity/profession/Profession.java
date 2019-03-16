/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.profession;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.UserBaseEntityExtend;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * <pre>
 * @formatter:off
 *
 * 用户的业务能力定义: 可以有多项，最多设置三项，以对应三个标签
 * 三项业务能力，每项业务可有多个段落作为描述（暂定最多允许添加两个段落）
 * @author bruce yang at 2018-09-28 15:46:46
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-28	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Entity
@Table(name="s9_pro_Profession")
public class Profession extends UserBaseEntityExtend implements Serializable {

	private static final long serialVersionUID = -8247052934215592859L;
	
	public static final PropertyContext MAINTYPE=L.initProperty("技能输出类型", "选择技能输出类型", "技能输出类型", "技能输出类型，一个用户只能有一个主技能，有且必须有且仅有一个主类型", "mainType", Profession.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext MAINTYPE_0_NORMAL=MAINTYPE.initLabel(0, "辅助技能","N", "辅助技能");
	public static final LabelContext MAINTYPE_9_MAIN=MAINTYPE.initLabel(9, "主技能","M", "主要技能");
	
	
	public static final PropertyContext PUBLISH_STATUS=L.initProperty("技能状态", "选择技能状态", "技能状态", "技能编辑时处于修炼状态，完成后再进行发布", "publishStatus", Profession.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PUBLISH_STATUS_ALL=PUBLISH_STATUS.initLabel(0, "全部","ALL", "发布状态：全部");
	public static final LabelContext PUBLISH_STATUS_DRAFT=PUBLISH_STATUS.initLabel(1, "修炼中","D", "发布状态：修炼中");
	public static final LabelContext PUBLISH_STATUS_PUBLISH=PUBLISH_STATUS.initLabel(2, "已发布","P", "发布状态：发布");
	public static final LabelContext PUBLISH_STATUS_CLOSED=PUBLISH_STATUS.initLabel(3, "已关闭","C", "发布状态：关闭");

	public static final PropertyContext RECOMMEND=L.initProperty("推荐状态", "是否推荐", "推荐状态", "是否推荐该文", "recommend", Profession.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext RECOMMEND_NO=RECOMMEND.initLabel(0, "否","N", "推荐状态：未推");
	public static final LabelContext RECOMMEND_YES=RECOMMEND.initLabel(1, "是","Y", "推荐状态：已推");
	public static final LabelContext RECOMMEND_ALL=RECOMMEND.initLabel(2, "全部","A", "推荐状态：全部");

	
	public static final PropertyContext PREINTRO_TYPE=L.initProperty("导语标记(滚播画面展示)", "选择导语", "技能导语", "填写将优先展示于醒目位置", "preIntroType", Profession.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PREINTRO_TYPE_NO=PREINTRO_TYPE.initLabel(30, "无","NO", "导语类型：自定义");
	public static final LabelContext PREINTRO_TYPE_44_BASIC=PREINTRO_TYPE.initLabel(44, "『基本技』>>","BASIC", "导语类型：基本技");
	public static final LabelContext PREINTRO_TYPE_31_PHOTO=PREINTRO_TYPE.initLabel(31, "『摄影技』☀☀","PHOTO", "导语类型：摄影技");
	public static final LabelContext PREINTRO_TYPE_32_ARTISON=PREINTRO_TYPE.initLabel(32, "『工匠技』>>","ARTISON", "导语类型：工匠技");
	public static final LabelContext PREINTRO_TYPE_33_MAJOR=PREINTRO_TYPE.initLabel(33, "『专业技』●●","MAJOR", "导语类型：专业技");
	public static final LabelContext PREINTRO_TYPE_34_KILL=PREINTRO_TYPE.initLabel(34, "『必杀技』★★","KILL", "导语类型：必杀技");
	public static final LabelContext PREINTRO_TYPE_35_EQUIP=PREINTRO_TYPE.initLabel(35, "『装备技』○○","EQUIP", "导语类型：装备技");
	public static final LabelContext PREINTRO_TYPE_36_TRAVEL=PREINTRO_TYPE.initLabel(36, "『旅行技』◎◎","TRAVEL", "导语类型：旅行技");
	public static final LabelContext PREINTRO_TYPE_37_OUTDOOR=PREINTRO_TYPE.initLabel(37, "『户外技』>>","OUTDOOR", "导语类型：旅行技");
	public static final LabelContext PREINTRO_TYPE_38_PIONEER=PREINTRO_TYPE.initLabel(38, "『探路技』︿︿","PIONEER", "导语类型：探路技");
	public static final LabelContext PREINTRO_TYPE_39_EXPLORER=PREINTRO_TYPE.initLabel(39, "『探险技』→→","EXPLORER", "导语类型：探险技");
	public static final LabelContext PREINTRO_TYPE_40_EAT=PREINTRO_TYPE.initLabel(40, "『美味技』♨♨","EAT", "导语类型：美味技");
	public static final LabelContext PREINTRO_TYPE_41_FACTORY=PREINTRO_TYPE.initLabel(41, "『工业技』۞۞","FACTORY", "导语类型：工业技");
	public static final LabelContext PREINTRO_TYPE_42_CLOTH=PREINTRO_TYPE.initLabel(42, "『穿戴技』❀❀","CLOTH", "导语类型：穿戴技");
	public static final LabelContext PREINTRO_TYPE_43_BEAUTIFUL=PREINTRO_TYPE.initLabel(43, "『美人技』❈❈","BEAUTIFUL", "导语类型：美人技");
	
	
	@Transient
	private List<CategProfession> categoryList;//支持绑定多个类别
	
	@Transient
	private Category categoryTree;//类别树，编辑时用于选择类别
	
	@Transient
	private CoeUser author;
	
	@Transient
	private List<ParagProfession> paragList;
	
	@Transient
	private List<ProfessionTag> proTagList;
	
	
	@Transient
	private List<ProfessionPraise> praiseList;//点赞
	
	@Transient
	private Page<ProfessionPraise> praisePage;//点赞
	
	
	@Transient
	private List<ProfessionRef> refList;//绑定
	
	@Transient
	private Page<ProfessionRef> refPage;//绑定
	
	@Transient
	private List<Profession> mainTypeList;//点赞用户的主技能输出
	
	@Transient
	private Page<Profession> refreshPage;//全局技能刷新排行，不分主辅
	
	
	@Transient
	private List<PhotoCover> coverList;
	
	@Transient
	private List<PhotoBanner> bannerList;
	
	@Transient
	private Long baseId;//技能id接收参数, 此时,mainId==baseId
	
	@Column(name="extendId", length = 20, columnDefinition = "BIGINT(20) COMMENT '技能模块扩展Id'")
	private Long extendId;
	
	@Column(name="positionId",columnDefinition="BIGINT(20) COMMENT '该id颁发给调用者'")
	private Long positionId;//位置id,用户申请之后产生此id,后续用户可根据此id来上传图片
	
	//@Column(name="mainId", length = 20, columnDefinition = "BIGINT(20) COMMENT '圈子id, 系统id'")
	//private Long mainId;//position是圈子时,存圈子id; position是system时，存平台id(platId)
	
	//@Column(name="subId", length = 20, columnDefinition = "BIGINT(20) COMMENT 'subId'")
	//private Long subId;//可以是其下具体某个分类
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long coeUserId;
	
	@Column(length = 3, columnDefinition = "INT(3) COMMENT '技能输出类型'")
	private Integer mainType;//技能主要输出类型：9为主技能，0为辅助技能，其他数字待定，通常一个用户只能有一个为主技能
	
	@Column(name="recommend",columnDefinition="int(1) COMMENT '技能推荐状态，0否 1是'")
	private Integer recommend;
	
	@Column(name="topLevel", length = 11, columnDefinition = "INT(11) COMMENT '置顶等级，数值越大越靠前'")
	private Integer topLevel;//技能置顶等级，数值越大越靠前
	
	/**
	 * 技能aid
	 */
	@Column(name="professionNo",columnDefinition="char(10) COMMENT '技能编号'")
	private String professionNo;//技能编号
	
	@Column(name="penname",columnDefinition="varchar(16) COMMENT '技能别名'")
	private String penname;//技能别名，如：必杀技：极咒返阴阳
	
	@Column(name="name",columnDefinition="varchar(64) COMMENT '业务名称'")
	private String name; //名称
	
	@Column(name="preIntro",columnDefinition="varchar(20) COMMENT '导语'")
	private String preIntro;//导语,置于标题前，靠左齐
	
	@Column(name="preIntroType", length = 3, columnDefinition = "INT(3) COMMENT '导语类型，0自定义,1系统预定义某值'")
	private Integer preIntroType;//导语类型
	
	@Column(name="subTitle",columnDefinition="varchar(32) COMMENT '副标题'")
	private String subTitle;//副标题
	
	@Column(name="title",columnDefinition="varchar(64) COMMENT '业务标题'")
	private String title;//标题
	
	@Column(name="titleLower",columnDefinition="varchar(64) COMMENT '标题'")
	private String titleLower;//标题全小写
	
	@Column(name="description",columnDefinition="varchar(5120) COMMENT '业务描述'")
	private String description;
	
	@Column(name="publishStatus",columnDefinition="int(1) COMMENT '技能状态，1 修炼中 2已发布 3已关闭'")
	private Integer publishStatus;
	
	@Column(name="publishTime",columnDefinition="DATETIME COMMENT '技能发布时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date publishTime;//发布时间
	
	@Column(name="refreshTime",columnDefinition="DATETIME COMMENT '技能刷新时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date refreshTime;//刷新时间
	
	//被关闭的产品将不能上架，不能编辑，需要通过管理员审核退回为草稿状态，才能再次发布
	//被关闭的技能数量多了，将影响用户信誉，严重者封禁帐号
	@Column(name="closedTime",columnDefinition="DATETIME COMMENT '技能关闭时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date closedTime;//技能关闭时间
	
	@Column(name="expiredTime",columnDefinition="DATETIME COMMENT '技能有效期'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date expiredTime;//技能有效期
	 
	
	@Column(name="grade",columnDefinition="int(3) COMMENT '技能等级'")
	private Integer grade;//技能级别，不同的级别拥有的服务能力不同，例如：达到级别后可利用该技能进行下单服务
	

	@Column(name="originalLink",columnDefinition="varchar(255) COMMENT '技能专属链接'")
	private String originalLink; //技能专属链接
	
	@Column(name="link",columnDefinition="varchar(1024) COMMENT '技能导向链接'")
	private String link; //技能导向链接
	
	@JoinColumn(insertable=false,updatable=false,name="saywordId", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Sayword sayword;//记录当时的那句传说
	
	@Column(name="saywordId",length = 20, columnDefinition = "BIGINT(20) COMMENT '传说Id'")
	private Long saywordId;//记录当时的那句传说,建立关联关系
	
	public Long getCoeUserId() {
		return coeUserId;
	}

	public void setCoeUserId(Long coeUserId) {
		this.coeUserId = coeUserId;
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

	public List<ParagProfession> getParagList() {
		return paragList;
	}

	public void setParagList(List<ParagProfession> paragList) {
		this.paragList = paragList;
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

	public String getTitleLower() {
		return titleLower;
	}

	public void setTitleLower(String titleLower) {
		this.titleLower = titleLower;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public String getOriginalLink() {
		return originalLink;
	}

	public void setOriginalLink(String originalLink) {
		this.originalLink = originalLink;
	}

	public List<ProfessionTag> getProTagList() {
		return proTagList;
	}

	public void setProTagList(List<ProfessionTag> proTagList) {
		this.proTagList = proTagList;
	}

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

	public String getProfessionNo() {
		return professionNo;
	}

	public void setProfessionNo(String professionNo) {
		this.professionNo = professionNo;
	}

	public String getPreIntro() {
		return preIntro;
	}

	public void setPreIntro(String preIntro) {
		this.preIntro = preIntro;
	}

	public Integer getPreIntroType() {
		return preIntroType;
	}

	public void setPreIntroType(Integer preIntroType) {
		this.preIntroType = preIntroType;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public CoeUser getAuthor() {
		return author;
	}

	public void setAuthor(CoeUser author) {
		this.author = author;
	}

	public String getPenname() {
		return penname;
	}

	public void setPenname(String penname) {
		this.penname = penname;
	}

	public List<CategProfession> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategProfession> categoryList) {
		this.categoryList = categoryList;
	}

	public Category getCategoryTree() {
		return categoryTree;
	}

	public void setCategoryTree(Category categoryTree) {
		this.categoryTree = categoryTree;
	}

	public List<ProfessionPraise> getPraiseList() {
		return praiseList;
	}

	public void setPraiseList(List<ProfessionPraise> praiseList) {
		this.praiseList = praiseList;
	}

	public Page<ProfessionPraise> getPraisePage() {
		return praisePage;
	}

	public void setPraisePage(Page<ProfessionPraise> praisePage) {
		this.praisePage = praisePage;
	}

	public Long getExtendId() {
		return extendId;
	}

	public void setExtendId(Long extendId) {
		this.extendId = extendId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Page<ProfessionRef> getRefPage() {
		return refPage;
	}

	public void setRefPage(Page<ProfessionRef> refPage) {
		this.refPage = refPage;
	}

	public List<ProfessionRef> getRefList() {
		return refList;
	}

	public void setRefList(List<ProfessionRef> refList) {
		this.refList = refList;
	}
	
	/*public Long getMainId() {
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
	}*/
	
	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public Integer getMainType() {
		return mainType;
	}

	public void setMainType(Integer mainType) {
		this.mainType = mainType;
	}

	public List<Profession> getMainTypeList() {
		return mainTypeList;
	}

	public void setMainTypeList(List<Profession> mainTypeList) {
		this.mainTypeList = mainTypeList;
	}

	public Page<Profession> getRefreshPage() {
		return refreshPage;
	}

	public void setRefreshPage(Page<Profession> refreshPage) {
		this.refreshPage = refreshPage;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Integer getTopLevel() {
		return topLevel;
	}

	public void setTopLevel(Integer topLevel) {
		this.topLevel = topLevel;
	}

}