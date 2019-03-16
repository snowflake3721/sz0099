/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.article;

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
 * 文章定义
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
@Table(name="s9_art_Article")
public class CoeArticle extends UserBaseEntityExtend implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	public static final PropertyContext MAINTYPE=L.initProperty("文章输出类型", "选择文章输出类型", "文章输出类型", "文章输出类型，一个用户只能有一个最新文章，有且必须有且仅有一个最新文章", "mainType", CoeArticle.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext MAINTYPE_0_NORMAL=MAINTYPE.initLabel(0, "历史文章","N", "历史文章");
	public static final LabelContext MAINTYPE_9_MAIN=MAINTYPE.initLabel(9, "最新文章","M", "最新文章");
	
	public static final PropertyContext PUBLISH_STATUS=L.initProperty("发布状态", "选择发布状态", "发布状态", "文章编辑时处于草稿状态，完成后再进行发布", "publishStatus", CoeArticle.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PUBLISH_STATUS_DRAFT=PUBLISH_STATUS.initLabel(1, "草稿","D", "发布状态：草稿");
	public static final LabelContext PUBLISH_STATUS_PUBLISH=PUBLISH_STATUS.initLabel(2, "已发布","P", "发布状态：发布");
	public static final LabelContext PUBLISH_STATUS_CLOSED=PUBLISH_STATUS.initLabel(3, "已关闭","C", "发布状态：关闭");
	
	public static final PropertyContext RECOMMEND=L.initProperty("推荐状态", "是否推荐", "推荐状态", "是否推荐该文", "recommend", CoeArticle.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext RECOMMEND_NO=RECOMMEND.initLabel(0, "否","N", "推荐状态：未推");
	public static final LabelContext RECOMMEND_YES=RECOMMEND.initLabel(1, "是","Y", "推荐状态：已推");
	public static final LabelContext RECOMMEND_ALL=RECOMMEND.initLabel(2, "全部","A", "推荐状态：全部");

	
	
	
	public static final PropertyContext PREINTRO_TYPE=L.initProperty("导语标记(滚播画面展示)", "选择导语", "文章导语", "填写将优先展示于醒目位置", "preIntroType", CoeArticle.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PREINTRO_TYPE_NO=PREINTRO_TYPE.initLabel(0, "无","NO", "导语类型：自定义");
	public static final LabelContext PREINTRO_TYPE_1_PROFILE=PREINTRO_TYPE.initLabel(1, "人物专访>>","PROFILE", "导语类型：人物专访");
	public static final LabelContext PREINTRO_TYPE_2_GUIDE=PREINTRO_TYPE.initLabel(2, "攻略●●","GUIDE", "导语类型：攻略");
	public static final LabelContext PREINTRO_TYPE_3_REMEMBER=PREINTRO_TYPE.initLabel(3, "独家记忆︿︿","REMEMBER", "导语类型：独家记忆");
	public static final LabelContext PREINTRO_TYPE_4_TRAVEL=PREINTRO_TYPE.initLabel(4, "在路上...","TRAVEL", "导语类型：在路上");
	public static final LabelContext PREINTRO_TYPE_5_OUTDOOR=PREINTRO_TYPE.initLabel(5, "户外有感◇◇","OUTDOOR", "导语类型：户外有感");
	public static final LabelContext PREINTRO_TYPE_6_WALKSAY=PREINTRO_TYPE.initLabel(6, "边走边说→→","WALKSAY", "导语类型：边走边说");
	public static final LabelContext PREINTRO_TYPE_7_EAT=PREINTRO_TYPE.initLabel(7, "美味来了＆＆","EAT", "导语类型：美味来了");
	public static final LabelContext PREINTRO_TYPE_8_PHOTO=PREINTRO_TYPE.initLabel(8, "风景摄录¤¤","PHOTO", "导语类型：风景摄录");
	public static final LabelContext PREINTRO_TYPE_9_BIKE=PREINTRO_TYPE.initLabel(9, "风行骑手○○","BIKE", "导语类型：风行骑手");
	public static final LabelContext PREINTRO_TYPE_10_GOODS=PREINTRO_TYPE.initLabel(10, "物道☯☯","BIKE", "导语类型：物道");
	
	
	
	public static String getLabel(Integer value, String property) {
		return L.getLabel(value, property, CoeArticle.class.getName());
	}
	
	public static Map<Integer,LabelContext> getValues(String property){
		return L.getValues( property, CoeArticle.class.getName());
	}
	
	@Transient
	private CoeUser author;
	
	@Transient
	private List<CoeArticleTag> articleTagList;
	
	@Transient
	private List<PhotoCover> coverList;
	
	@Transient
	private List<PhotoBanner> bannerList;
	
	@Transient
	private List<CoeCategArticle> categoryList;//支持绑定多个类别
	
	@Transient
	private Category categoryTree;//类别树，编辑时用于选择类别

	@Transient
	private List<CoeArticle> mainTypeList;//点赞用户的主文章输出：每次发布文章，把最新的一篇置为主文章
	
	@Transient
	private Page<CoeArticle> refreshPage;//全局文章刷新排行，不分主辅
	
	@Transient
	private List<CoeArticlePraise> praiseList;//点赞
	
	@Transient
	private Page<CoeArticlePraise> praisePage;//点赞
	
	@Column(length = 3, columnDefinition = "INT(3) COMMENT '文章输出类型'")
	private Integer mainType;//文章主要输出类型：9为最新文章，0为历史文章，其他数字待定，通常一个用户只能有一篇为最新文章
	
	@Column(name="publishStatus",columnDefinition="int(1) COMMENT '文章发布状态，1 草稿 2发布 3关闭'")
	private Integer publishStatus;
	
	@Column(name="recommend",columnDefinition="int(1) COMMENT '文章推荐状态，0否 1是'")
	private Integer recommend;
	
	@Column(name="publishTime",columnDefinition="DATETIME COMMENT '文章发布时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date publishTime;//发布时间
	
	@Column(name="refreshTime",columnDefinition="DATETIME COMMENT '文章刷新时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date refreshTime;//刷新时间
	
	//被关闭的文章数量多了，将影响用户信誉，严重者封禁帐号
	@Column(name="closedTime",columnDefinition="DATETIME COMMENT '文章关闭时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date closedTime;//文章关闭时间
	
	/**
	 * 文章aid
	 */
	@Column(name="articleNo",columnDefinition="char(10) COMMENT '文章编号'")
	private String articleNo;//文章编号
	
	@Column(name="topLevel", length = 11, columnDefinition = "INT(11) COMMENT '置顶等级，数值越大越靠前'")
	private Integer topLevel;//文章置顶等级，数值越大越靠前
	
	@Column(name="penname",columnDefinition="varchar(16) COMMENT '笔名'")
	private String penname;//笔名
	
	@Column(name="name",columnDefinition="varchar(64) COMMENT '名称'")
	private String name; //名称
	
	@Column(name="preIntro",columnDefinition="varchar(20) COMMENT '导语'")
	private String preIntro;//导语,置于标题前，靠左齐
	
	@Column(name="preIntroType", length = 3, columnDefinition = "INT(3) COMMENT '导语类型，0自定义,1系统预定义某值'")
	private Integer preIntroType;//导语类型
	
	@Column(name="subTitle",columnDefinition="varchar(32) COMMENT '副标题'")
	private String subTitle;//副标题
	
	@Column(name="title",columnDefinition="varchar(64) COMMENT '标题'")
	private String title;//标题
	
	@Column(name="titleLower",columnDefinition="varchar(64) COMMENT '标题全小写'")
	private String titleLower;//标题全小写
	
	@Column(name="description",columnDefinition="varchar(5120) COMMENT '文章描述'")
	private String description;
	
	@Column(name="originalLink",columnDefinition="varchar(255) COMMENT '文章原链接'")
	private String originalLink; //文章原链接
	
	@Column(name="link",columnDefinition="varchar(1024) COMMENT '文章导向链接'")
	private String link;//导向链接
	
	//@Column(name="sayword",length=64,columnDefinition="varchar(64) COMMENT '传说'")
	//private String sayword;//文章发布时留下传说 CoeUser.sayword
	
	@JoinColumn(insertable=false,updatable=false,name="saywordId", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Sayword sayword;//记录当时的那句传说
	
	@Column(name="saywordId",length = 20, columnDefinition = "BIGINT(20) COMMENT '传说Id'")
	private Long saywordId;//记录当时的那句传说,建立关联关系

	
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

	public List<CoeCategArticle> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CoeCategArticle> categoryList) {
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

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
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

	public List<CoeArticleTag> getArticleTagList() {
		return articleTagList;
	}

	public void setArticleTagList(List<CoeArticleTag> articleTagList) {
		this.articleTagList = articleTagList;
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

	public List<CoeArticlePraise> getPraiseList() {
		return praiseList;
	}

	public void setPraiseList(List<CoeArticlePraise> praiseList) {
		this.praiseList = praiseList;
	}

	public Page<CoeArticlePraise> getPraisePage() {
		return praisePage;
	}

	public void setPraisePage(Page<CoeArticlePraise> praisePage) {
		this.praisePage = praisePage;
	}

	public CoeUser getAuthor() {
		return author;
	}

	public void setAuthor(CoeUser author) {
		this.author = author;
	}

	public List<CoeArticle> getMainTypeList() {
		return mainTypeList;
	}

	public void setMainTypeList(List<CoeArticle> mainTypeList) {
		this.mainTypeList = mainTypeList;
	}

	public Page<CoeArticle> getRefreshPage() {
		return refreshPage;
	}

	public void setRefreshPage(Page<CoeArticle> refreshPage) {
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
	
	

}
