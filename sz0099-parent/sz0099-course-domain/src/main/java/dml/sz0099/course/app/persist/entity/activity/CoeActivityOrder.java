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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.BaseEntityExtendLong;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * <pre>
 * @formatter:off
 *
 * 用户报名活动订单
 * 一个用户可以下多个订单，但同一活动下仅允许对应一个用户
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
@Table(name="s9_act_ActivityOrder")
public class CoeActivityOrder extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = 8426536810837546707L;

	public static final PropertyContext STATUS=L.initProperty("活动订单状态", "活动订单状态", "活动订单状态", "活动订单状态", "status", CoeActivityOrder.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext STATUS_INIT=STATUS.initLabel(0, "初始化","INIT", "活动订单状态：初始化");
	public static final LabelContext STATUS_PAY_WAIT=STATUS.initLabel(1, "待付款","PAY_WAIT", "活动订单状态：待付款");
	public static final LabelContext STATUS_PAY_ING=STATUS.initLabel(2, "付款中","PAY_ING", "活动订单状态：付款中");
	public static final LabelContext STATUS_PAY=STATUS.initLabel(3, "已付款","PAY", "活动订单状态：已付款");//在未启用微信支付时，付款分两步：1用户付款 2管理员审核；启用微信支付后，可合二为一
	public static final LabelContext STATUS_PAY_VERIFY=STATUS.initLabel(4, "已审核","PAY_VERIFY", "活动订单状态：已审核");//支付成功后，由程序回调进行自动审核
	
	public static final LabelContext STATUS_EXEING=STATUS.initLabel(5, "出行中","EXEING", "活动订单状态：已出行");
	public static final LabelContext STATUS_FINISH=STATUS.initLabel(6, "已完成","FINISH", "活动订单状态：已完成");
	public static final LabelContext STATUS_CANCEL=STATUS.initLabel(7, "已取消","CANCEL", "活动订单状态：已取消");
	public static final LabelContext STATUS_COMPLETED=STATUS.initLabel(9, "已结束","COMPLETED", "活动订单状态：已结束");
	public static final LabelContext STATUS_CLOSED=STATUS.initLabel(10, "已关闭","CLOSED", "活动订单状态：已关闭");
	
	/**
	 * 该订单下的报名人信息
	 */
	@Transient
	private List<CoeActivityUser> userList;
	
	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;//下单用户
	
	@Transient
	private CoeActivity activity;
	
	@Transient
	private CoeActivityOrderLog orderLog;
	
	@Column(name="orderLogId", length = 20, columnDefinition = "BIGINT(20) COMMENT '快照Id'")
	private Long orderLogId;
	
	/**
	 * 用于接收页面传递参数，集合时间所属
	 * 这里对应activityId
	 */
	@Column(name="activityId", length = 20, columnDefinition = "BIGINT(20) COMMENT '活动id'")
	private Long mainId;//产品Id
	
	@Column(name="orderTime",columnDefinition="DATETIME COMMENT '下单时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date orderTime;//下单时间
	
	@Column(name="totalRmb", length = 11, columnDefinition = "INT(11) COMMENT '订单总金额(分)'")
	private Long totalRmb;//订单总金额，单位：分
	
	@Column(name="rmb", length = 11, columnDefinition = "INT(11) COMMENT '产品单价(分)'")
	private Long rmb;//产品单价，单位：分
	
	@Column(name="num", length = 8, columnDefinition = "INT(8) COMMENT '订单购买份数'")
	private Integer num;//份数
	
	@Column(name="orderStatus",columnDefinition="int(3) COMMENT '订单状态，0初始化,1待付款,2已付款, 3出行中, 4已完成,5已取消, 9已结束,10已关闭'")
	private Integer orderStatus;
	
	@Column(name="payBegin",columnDefinition="DATETIME COMMENT '付款开始时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date payBegin;//付款开始时间
	
	@Column(name="payTime",columnDefinition="DATETIME COMMENT '付款时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date payTime;//付款时间(或付款确认时间，未对接支付系统时需人工审核确认已付款)
	
	
	
	@Column(name="exeTime",columnDefinition="DATETIME COMMENT '出行时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date exeTime;//出行时间
	
	@Column(name="finishTime",columnDefinition="DATETIME COMMENT '完成时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date finishTime;//本次活动完成时间
	
	@Column(name="cancelTime",columnDefinition="DATETIME COMMENT '取消时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date cancelTime;//取消时间
	
	@Column(name="completeTime",columnDefinition="DATETIME COMMENT '结束时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date completeTime;//结束时间
	
	@Column(name="closedTime",columnDefinition="DATETIME COMMENT '关闭时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date closedTime;//关闭时间
	
	@Column(name="remark",columnDefinition="varchar(256) COMMENT '备注'")
	private String remark;//订单关闭时可备注
	
	@Column(name="clientIp",columnDefinition="varchar(16) COMMENT 'IP'")
	private String clientIp;//IP
	
	@Column(name="title",columnDefinition="varchar(64) COMMENT '订单标题'")
	private String title;//订单标题
	
	@Column(name="payType", length = 3, columnDefinition = "INT(3) COMMENT '支付方式'")
	private Integer payType;//支付方式
	
	@Column(name="outTradeNo",columnDefinition="varchar(32) COMMENT '商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一'")
	private String outTradeNo;//商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。


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

	public CoeActivity getActivity() {
		return activity;
	}

	public void setActivity(CoeActivity activity) {
		this.activity = activity;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}


	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getExeTime() {
		return exeTime;
	}

	public void setExeTime(Date exeTime) {
		this.exeTime = exeTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Date getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(Date closedTime) {
		this.closedTime = closedTime;
	}


	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Long getTotalRmb() {
		return totalRmb;
	}

	public void setTotalRmb(Long totalRmb) {
		this.totalRmb = totalRmb;
	}

	public Long getRmb() {
		return rmb;
	}

	public void setRmb(Long rmb) {
		this.rmb = rmb;
	}

	public List<CoeActivityUser> getUserList() {
		return userList;
	}

	public void setUserList(List<CoeActivityUser> userList) {
		this.userList = userList;
	}

	public CoeActivityOrderLog getOrderLog() {
		return orderLog;
	}

	public void setOrderLog(CoeActivityOrderLog orderLog) {
		this.orderLog = orderLog;
	}

	public Long getOrderLogId() {
		return orderLogId;
	}

	public void setOrderLogId(Long orderLogId) {
		this.orderLogId = orderLogId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Date getPayBegin() {
		return payBegin;
	}

	public void setPayBegin(Date payBegin) {
		this.payBegin = payBegin;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}



}
