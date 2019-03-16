/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.activity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <pre>
 * @formatter:off
 *
 * 订单活动快照
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
@Table(name="s9_act_ActivityOrderLog")
public class CoeActivityOrderLog extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = 8426536810837546707L;

	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;//下单用户
	
	@Column(name="activityId", length = 20, columnDefinition = "BIGINT(20) COMMENT '活动id'")
	private Long mainId;//产品Id
	
	@Column(name="orderId", length = 20, columnDefinition = "BIGINT(20) COMMENT '订单id'")
	private Long orderId;//订单Id
	
	@Column(name="feeId",length = 20, columnDefinition = "BIGINT(20) COMMENT '费用Id'")
	private Long feeId;
	
	@Column(name="actTimeId",length = 20, columnDefinition = "BIGINT(20) COMMENT '时间Id'")
	private Long actTimeId;
	
	@Column(name="actOrganize",columnDefinition="int(1) COMMENT '活动组织形式：0无 1召集 2约伴 3分头行动 4独立行动 5自定义'")
	private Integer actOrganize;
	
	@Column(name="publishTime",columnDefinition="DATETIME COMMENT '活动发布时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date publishTime;//发布时间
	
	@Column(name="refreshTime",columnDefinition="DATETIME COMMENT '活动刷新时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date refreshTime;//刷新时间
	
	/**
	 * 活动aid
	 */
	@Column(name="activityNo",columnDefinition="char(10) COMMENT '活动编号'")
	private String activityNo;//活动编号
	
	@Column(name="penname",columnDefinition="varchar(16) COMMENT '笔名'")
	private String penname;//笔名
	
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
	
	@Column(name="kilometer", length = 8, columnDefinition = "INT(8) COMMENT '活动里程,km'")
	private Integer kilometer;


	
	@Column(name="beginTime",columnDefinition="DATETIME COMMENT '活动开始时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginTime;//活动开始时间
	
	@Column(name="endTime",columnDefinition="DATETIME COMMENT '活动结束时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;//活动结束时间
	
	@Column(name="offTime",columnDefinition="DATETIME COMMENT '活动截止报名时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date offTime;//活动截止报名时间
	
	
	@Column(name="rmbAmount",length = 11, columnDefinition = "INT(11) COMMENT 'RMB支付金额'")
	private Long rmbAmount;//单位：分
	
	@Column(name="rmbAmountOri",length = 11, columnDefinition = "INT(11) COMMENT 'RMB原价'")
	private Long rmbAmountOri;//单位：分
	
	@Column(name="cashAmount",length = 11, columnDefinition = "INT(11) COMMENT '余额支付金额'")
	private Long cashAmount;
	
	@Column(name="coinAmount",length = 11, columnDefinition = "INT(11) COMMENT '币额支付金额'")
	private Long coinAmount;
	
	@Column(name="currency",length = 3, columnDefinition = "INT(3) COMMENT '货币支付方式'")
	private Integer currency;
	
	@Column(name="feeType",length = 3, columnDefinition = "INT(3) COMMENT '分摊类型'")
	private Integer feeType;
	
	@Column(name="recieveType",length = 3, columnDefinition = "INT(3) COMMENT '收费方式'")
	private Integer recieveType;
	
	@Column(name="feeDescription",columnDefinition="varchar(1024) COMMENT '费用说明'")
	private String feeDescription;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getFeeId() {
		return feeId;
	}

	public void setFeeId(Long feeId) {
		this.feeId = feeId;
	}

	public Long getActTimeId() {
		return actTimeId;
	}

	public void setActTimeId(Long actTimeId) {
		this.actTimeId = actTimeId;
	}

	public Integer getActOrganize() {
		return actOrganize;
	}

	public void setActOrganize(Integer actOrganize) {
		this.actOrganize = actOrganize;
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

	public String getActivityNo() {
		return activityNo;
	}

	public void setActivityNo(String activityNo) {
		this.activityNo = activityNo;
	}

	public String getPenname() {
		return penname;
	}

	public void setPenname(String penname) {
		this.penname = penname;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getKilometer() {
		return kilometer;
	}

	public void setKilometer(Integer kilometer) {
		this.kilometer = kilometer;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getOffTime() {
		return offTime;
	}

	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}

	public Long getRmbAmount() {
		return rmbAmount;
	}

	public void setRmbAmount(Long rmbAmount) {
		this.rmbAmount = rmbAmount;
	}

	public Long getRmbAmountOri() {
		return rmbAmountOri;
	}

	public void setRmbAmountOri(Long rmbAmountOri) {
		this.rmbAmountOri = rmbAmountOri;
	}

	public Long getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(Long cashAmount) {
		this.cashAmount = cashAmount;
	}

	public Long getCoinAmount() {
		return coinAmount;
	}

	public void setCoinAmount(Long coinAmount) {
		this.coinAmount = coinAmount;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public String getFeeDescription() {
		return feeDescription;
	}

	public void setFeeDescription(String feeDescription) {
		this.feeDescription = feeDescription;
	}

	public Integer getRecieveType() {
		return recieveType;
	}

	public void setRecieveType(Integer recieveType) {
		this.recieveType = recieveType;
	}
}
