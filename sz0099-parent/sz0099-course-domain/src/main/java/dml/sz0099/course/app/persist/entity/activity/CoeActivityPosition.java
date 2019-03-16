/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.activity;

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

import org.jit4j.core.persist.entity.UserBaseEntityExtend;
import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * <pre>
 * @formatter:off
 *
 * 文章位置定义
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
@Table(name="s9_act_ActivityPosition")
public class CoeActivityPosition extends UserBaseEntityExtend/*BaseEntityExtendLong*/ implements Serializable {

	private static final long serialVersionUID = -4795277758856470669L;
	
	//id存PositionRef.id
	
	
	@Transient
	private CoeActivity activity;
	
	@Column(name="viewType",columnDefinition="int(3) COMMENT '关联类型'")
	private Integer viewType;
	
	
	@Column(name="mainNo",columnDefinition="char(10) COMMENT '文章编号'")
	private String mainNo;
	
	@Column(name="baseId" ,length = 20, columnDefinition = "BIGINT(20) COMMENT '位置Id'")
	private Long baseId;//对应baseId==PositionRef.baseId==Position.id
	
	@Transient
	private List<CoeActivityPositionCover> coverList;
	
	@Transient
	private List<CoeActivityPositionImage> bannerList;
	
	@Column(name="ponMainId", length = 20, columnDefinition = "BIGINT(20) COMMENT '圈子id, 系统id'")
	private Long ponMainId;//圈子id, 平台id(platId)==Position.mainId 冗余存储
	
	@Column(name="ponSubId", length = 20, columnDefinition = "BIGINT(20) COMMENT 'subId'")
	private Long ponSubId;//可以是其下具体某个页面id，如首页，分类页, ponSubId==Position.subId==in (Position.SUBID) 冗余存储
	
	@Column(name="ponLayout",columnDefinition="int(3) COMMENT '冗余布局定义'")
	private Integer ponLayout;//冗余存储布局定义
	
	@Column(name="ponPanel",columnDefinition="int(3) COMMENT '栏目定义'")
	private Integer ponPanel;//冗余存储栏目
	
	@Column(name="extendId", length = 20, columnDefinition = "BIGINT(20) COMMENT '扩展信息Id'")
	private Long extendId;
	
	@Column(name="positionId", length = 20, columnDefinition = "BIGINT(20) COMMENT '扩展信息positionId'")
	private Long positionId;//PositionExtend.positionId
	
	
	@Column(name="name",columnDefinition="char(32) COMMENT '位置名称'")
	private String name; //位置名称 name==Position.name
	
	@JoinColumn(insertable=false,updatable=false,name="saywordId", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Sayword sayword;//记录当时的那句传说
	
	@Column(name="saywordId",length = 20, columnDefinition = "BIGINT(20) COMMENT '传说Id'")
	private Long saywordId;//记录当时的那句传说,建立关联关系，以关联时用户的传说为准，并非文章的传说
	
	
	@Transient
	private CoeUser author;
	
	@Column(name="coverImage",columnDefinition="varchar(255) COMMENT '头部图片'")
	private String coverImage;
	
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
	
	@Column(name="ownerId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long ownerId;//所属用户，并非文章id,是指购买此位置的用户
	
	@Column(name="penname",columnDefinition="char(16) COMMENT '作者笔名'")
	private String penname;//显示作者笔名;技能时为 招式别名
	
	@Column(name="topLevel", length = 11, columnDefinition = "INT(11) COMMENT '置顶等级，数值越大越靠前'")
	private Integer topLevel;//文章置顶等级，数值越大越靠前
	
	
	@Column(name="preIntro",columnDefinition="char(20) COMMENT '导语'")
	private String preIntro;//导语,置于标题前，靠左齐
	
	@Column(name="preIntroType", length = 3, columnDefinition = "INT(3) COMMENT '导语类型，0自定义,>1系统预定义某值'")
	private Integer preIntroType;//导语类型
	
	@Column(name="subTitle",columnDefinition="char(32) COMMENT '副标题'")
	private String subTitle;//副标题
	
	@Column(name="title",columnDefinition="varchar(64) COMMENT '标题'")
	private String title;//标题
	
	@Column(name="description",columnDefinition="varchar(2048) COMMENT '文章描述'")
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


	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public Long getPonMainId() {
		return ponMainId;
	}

	public void setPonMainId(Long ponMainId) {
		this.ponMainId = ponMainId;
	}

	public Long getPonSubId() {
		return ponSubId;
	}

	public void setPonSubId(Long ponSubId) {
		this.ponSubId = ponSubId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}


	public List<CoeActivityPositionImage> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<CoeActivityPositionImage> bannerList) {
		this.bannerList = bannerList;
	}

	public Long getExtendId() {
		return extendId;
	}

	public void setExtendId(Long extendId) {
		this.extendId = extendId;
	}

	public String getMainNo() {
		return mainNo;
	}

	public void setMainNo(String mainNo) {
		this.mainNo = mainNo;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public CoeActivity getActivity() {
		return activity;
	}

	public void setActivity(CoeActivity activity) {
		this.activity = activity;
	}

	public Integer getPonLayout() {
		return ponLayout;
	}

	public void setPonLayout(Integer ponLayout) {
		this.ponLayout = ponLayout;
	}

	public Integer getPonPanel() {
		return ponPanel;
	}

	public void setPonPanel(Integer ponPanel) {
		this.ponPanel = ponPanel;
	}

	public Integer getViewType() {
		return viewType;
	}

	public void setViewType(Integer viewType) {
		this.viewType = viewType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<CoeActivityPositionCover> getCoverList() {
		return coverList;
	}

	public void setCoverList(List<CoeActivityPositionCover> coverList) {
		this.coverList = coverList;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getPenname() {
		return penname;
	}

	public void setPenname(String penname) {
		this.penname = penname;
	}

	public Sayword getSayword() {
		return sayword;
	}

	public void setSayword(Sayword sayword) {
		this.sayword = sayword;
	}

	public Long getSaywordId() {
		return saywordId;
	}

	public void setSaywordId(Long saywordId) {
		this.saywordId = saywordId;
	}

	
	
	

}
