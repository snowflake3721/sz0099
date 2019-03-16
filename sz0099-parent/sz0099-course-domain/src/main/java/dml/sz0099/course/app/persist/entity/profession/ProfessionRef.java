/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.profession;

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
import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * <pre>
 * @formatter:off
 *
 * 通用位置关联
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
@Table(name="s9_pro_ProfessionRef")
public class ProfessionRef extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -4795277758856470669L;
	
	public static final PropertyContext STATUS=L.initProperty("关联状态", "选择关联状态", "关联状态", "关联状态，开启时会展现", "status", ProfessionRef.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext STATUS_0_INIT=STATUS.initLabel(0, "待审","Init", "关联状态：待审");
	public static final LabelContext STATUS_1_OPEN=STATUS.initLabel(1, "开启","Open", "关联状态：开启");
	public static final LabelContext STATUS_2_CLOSED=STATUS.initLabel(2, "关闭","Close", "关联状态：关闭");
	
	public static final PropertyContext VIEWTYPE=L.initProperty("查看类型", "选择查看类型", "查看类型", "查看类型，选择查看类型", "viewType", ProfessionRef.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext VIEWTYPE_0_DEFAULT=VIEWTYPE.initLabel(0, "默认","default", "默认");//默认
	public static final LabelContext VIEWTYPE_1_ARTICLE=VIEWTYPE.initLabel(1, "文章","article", "文章");//文章
	public static final LabelContext VIEWTYPE_2_PRODUCT=VIEWTYPE.initLabel(2, "产品","product", "产品");//产品
	public static final LabelContext VIEWTYPE_3_PROFESSION=VIEWTYPE.initLabel(3, "技能","profession", "技能");//技能

	
	public static String getLabel(Integer value, String property) {
		return L.getLabel(value, property, ProfessionRef.class.getName());
	}
	
	public static Map<Integer,LabelContext> getValues(String property){
		return L.getValues( property, ProfessionRef.class.getName());
	}
	
	@Column(name="viewType",columnDefinition="int(3) COMMENT '关联类型'")
	private Integer viewType;
	
	@Column(name="mainNo",columnDefinition="char(10) COMMENT '主体编号'")
	private String mainNo;
	
	@Transient
	private Profession profession;
	
	@Transient
	private Object mainEntity;
	

	@Column(name="baseId", length = 20, columnDefinition = "BIGINT(20) COMMENT '技能Id,即Profession.id'")
	private Long baseId;
	
	@Column(name="extendId", length = 20, columnDefinition = "BIGINT(20) COMMENT '扩展信息Id'")
	private Long extendId;
	
	@Column(name="positionId", length = 20, columnDefinition = "BIGINT(20) COMMENT '扩展信息positionId'")
	private Long positionId;//ProfessionExtend.positionId
	
	@Transient
	private CoeUser author;
	
	@Column(name="authorId", length = 20, columnDefinition = "BIGINT(20) COMMENT '作者Id'")
	private Long authorId;//技能所有者id
	
	@Column(name="coverImage",columnDefinition="varchar(255) COMMENT '头部图片'")
	private String coverImage;
	
	@Transient
	private List<ProfessionCover> coverList;
	
	@Transient
	private List<ProfessionImage> bannerList;
	
	@Column(name="status",columnDefinition="int(1) COMMENT '位置状态，0待审 1 开启 2关闭'")
	private Integer status;
	
	@Column(name="openTime",columnDefinition="DATETIME COMMENT '开启时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date openTime;//开启时间
	
	@Column(name="refreshTime",columnDefinition="DATETIME COMMENT '刷新时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date refreshTime;//刷新时间
	
	@Column(name="closedTime",columnDefinition="DATETIME COMMENT '关闭时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date closedTime;//文章关闭时间
	
	@Column(name="mainId", length = 20, columnDefinition = "BIGINT(20) COMMENT '主体Id'")
	private Long mainId;//文章id
	
	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;//所属用户，并非文章id,是指购买此位置的用户
	
	@Column(name="topLevel", length = 11, columnDefinition = "INT(11) COMMENT '置顶等级，数值越大越靠前'")
	private Integer topLevel;//文章置顶等级，数值越大越靠前
	
	@Column(name="authorname",columnDefinition="char(10) COMMENT '作者名'")
	private String authorname;//显示作者名
	
	@Column(name="name",columnDefinition="char(30) COMMENT '名称'")
	private String name; //名称
	
	@Column(name="preIntro",columnDefinition="char(20) COMMENT '导语'")
	private String preIntro;//导语,置于标题前，靠左齐
	
	@Column(name="preIntroType", length = 3, columnDefinition = "INT(3) COMMENT '导语类型，0自定义,>1系统预定义某值'")
	private Integer preIntroType;//导语类型
	
	@Column(name="subTitle",columnDefinition="char(20) COMMENT '副标题'")
	private String subTitle;//副标题
	
	@Column(name="title",columnDefinition="varchar(40) COMMENT '标题'")
	private String title;//标题
	
	@Column(name="description",columnDefinition="varchar(255) COMMENT '文章描述'")
	private String description;
	
	@Column(name="originalLink",columnDefinition="varchar(255) COMMENT '文章原链接'")
	private String originalLink; //文章原链接
	
	@Column(name="link",columnDefinition="varchar(255) COMMENT '链接导向'")
	private String link; //链接导向
	
	/**
	 * 关闭操作，要填写备注
	 */
	@Column(name="remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String remark;//备注


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


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getOriginalLink() {
		return originalLink;
	}

	public void setOriginalLink(String originalLink) {
		this.originalLink = originalLink;
	}

	public Integer getTopLevel() {
		return topLevel;
	}

	public void setTopLevel(Integer topLevel) {
		this.topLevel = topLevel;
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

	public CoeUser getAuthor() {
		return author;
	}

	public void setAuthor(CoeUser author) {
		this.author = author;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public String getAuthorname() {
		return authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public Integer getViewType() {
		return viewType;
	}

	public void setViewType(Integer viewType) {
		this.viewType = viewType;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMainNo() {
		return mainNo;
	}

	public void setMainNo(String mainNo) {
		this.mainNo = mainNo;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public List<ProfessionImage> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<ProfessionImage> bannerList) {
		this.bannerList = bannerList;
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

	public Object getMainEntity() {
		return mainEntity;
	}

	public void setMainEntity(Object mainEntity) {
		this.mainEntity = mainEntity;
	}

	public List<ProfessionCover> getCoverList() {
		return coverList;
	}

	public void setCoverList(List<ProfessionCover> coverList) {
		this.coverList = coverList;
	}


}
