/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.order;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <pre>
 * @formatter:off
 *
 * 订单
 * 一个订单的产品必须是同一个商家，若存在混合商家，必须每个商家生成一个订单
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
@Table(name="s9_coe_Order")
public class CoeOrder extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	public static final Integer PAYTYPE_OFF_WECHAT=11;//.线下微信转款 
	public static final Integer PAYTYPE_OFF_ALI=12;//.线下支付宝转款 
	public static final Integer PAYTYPE_ON_WECHAT=21;//.线上微信支付宝 
	public static final Integer PAYTYPE_ON_ALI=22;//.线上支付宝支付 
	
	public static final Integer STATUS_PAY_WAIT=0;//订单状态: 0 待支付
	public static final Integer STATUS_PAY_PAYED=1;//订单状态: 1 已支付待发货
	public static final Integer STATUS_PAY_INPROCESS=2;//订单状态: 2 在处理中
	public static final Integer STATUS_PAY_SENT=3;//订单状态: 3 已发货
	public static final Integer STATUS_PAY_RECIEVED=4;//订单状态: 4已收货
	public static final Integer STATUS_PAY_TM_RECIEVED=5;//订单状态: 5被动签收，超时自动签收
	public static final Integer STATUS_PAY_ALL=9;//表示所有状态
	
	public static final Integer FLOW_STATUS_BEGIN=1;//订单流程状态: 1订单开启
	public static final Integer FLOW_STATUS_INPROCESS=2;//订单流程状态: 2订单进行中
	public static final Integer FLOW_STATUS_COMPLETED=3;//订单流程状态: 3订单完成
	public static final Integer FLOW_STATUS_CANCEL=4;//订单流程状态: 4订单取消
	public static final Integer FLOW_STATUS_CLOSED=5;//订单流程状态: 5订单关闭
	
	public static final Integer PULL_STATUS_NO=0;//提取状态: 0未提取
	public static final Integer PULL_STATUS_YES=1;//提取状态: 1已提取
	public static final Integer PULL_STATUS_EXPIRED=2;//提取状态: 2已过期无法提取
	
	
	public static Map<Integer, String> payTypeMap=new HashMap<>(4);
	public static Map<Integer, String> payStatusMap=new HashMap<>(6);
	public static Map<Integer, String> flowStatusMap=new HashMap<>(5);
	public static Map<Integer, String> pullStatusMap=new HashMap<>(2);
	static {
		payTypeMap.put(PAYTYPE_OFF_WECHAT, "线下微信");
		payTypeMap.put(PAYTYPE_OFF_ALI, "线下支付宝");
		payTypeMap.put(PAYTYPE_ON_WECHAT, "线上支微信");
		payTypeMap.put(PAYTYPE_ON_ALI, "线上支付宝");
		
		payStatusMap.put(STATUS_PAY_WAIT, "待支付");
		payStatusMap.put(STATUS_PAY_PAYED, "已支付");
		payStatusMap.put(STATUS_PAY_INPROCESS, "客服处理中");
		payStatusMap.put(STATUS_PAY_SENT, "已发货");
		payStatusMap.put(STATUS_PAY_RECIEVED, "已收货");
		payStatusMap.put(STATUS_PAY_TM_RECIEVED, "自动签收");
		
		flowStatusMap.put(FLOW_STATUS_BEGIN, "开启");
		flowStatusMap.put(FLOW_STATUS_INPROCESS, "进行中");
		flowStatusMap.put(FLOW_STATUS_COMPLETED, "已完成");
		flowStatusMap.put(FLOW_STATUS_CANCEL, "已取消");
		flowStatusMap.put(FLOW_STATUS_CLOSED, "已关闭");
		
		pullStatusMap.put(PULL_STATUS_NO, "未提取");
		pullStatusMap.put(PULL_STATUS_YES, "已提取");
		pullStatusMap.put(PULL_STATUS_EXPIRED, "已过期");
		
	}
	
	//@OneToMany(mappedBy="orderId")
	@Transient
	private List<CoeOrderProduct> productList;
	
	@Transient
	private List<Long> productIdList;
	
	@Column(name="price",columnDefinition="int(8) COMMENT '支付总价(分)'")
	private Integer price;//支付总价
	
	@Column(name="remark",columnDefinition="varchar(255) COMMENT '订单备注'")
	private String remark;//产品下单时备注
	
	@Column(name="userId",columnDefinition="BIGINT(20) COMMENT '用户Id'")
	private Long userId;//用户id
	
	@Column(name="ownerId",columnDefinition="BIGINT(20) COMMENT '归属客服:CoeProduct.createdBy'")
	private Long ownerId;//订单拥有者：即产品发布者id(归属客服)
	
	@Column(name="openid",columnDefinition="char(32) COMMENT 'openid'")
	private String openIdwc;//用户微信id
	
	@Column(name="nickname",columnDefinition="char(100) COMMENT '微信昵称'")
	private String nickname;//冗余存储微信昵称
	
	/**
	 * email与QQ必须填一个
	 */
	@Column(name="qq",columnDefinition="char(16) COMMENT 'QQ号'")
	private String qq;//QQ号
	
	@Column(name="email",columnDefinition="char(60) COMMENT 'email'")
	private String email;//feihu00000@163.com
	
	@Column(name="grade",columnDefinition="int(2) COMMENT '会员等级'")
	private Integer grade;//会员等级
	
	@Column(name="rates",columnDefinition="int(4) COMMENT '折扣系数'")
	private Integer rates;//折扣系数,  折扣价=price*rates/baseRadix
	
	@Column(name="mobile",length=20,columnDefinition="char(20) COMMENT '手机号'")
	private String mobile;//用户手机号
	
	@Column(name="status",columnDefinition="int(1) COMMENT '订单状态: 0 待支付，1 已支付待发货，2 在处理中,3 已发货，4已收货 5 超时自动签收'")
	private Integer status;
	
	@Column(name="signType",columnDefinition="int(1) COMMENT '签收类型: 1用户手动签收，2超时自动签收'")
	private Integer signType;//签收类型: 1用户手动签收，2超时自动签收
	
	@Column(name="flowStatus",columnDefinition="int(1) COMMENT '订单流程状态: 1开启，2进行中，3完成，4取消，5关闭'")
	private Integer flowStatus;//订单流程状态: 1开启，2进行中，3完成，4取消，5关闭
	
	@Column(name="orderTime",columnDefinition="DATETIME COMMENT '下单时间'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderTime;//下单时间
	
	@Column(name="payTime",columnDefinition="DATETIME COMMENT '支付时间'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date payTime;//支付时间
	
	@Column(name="expiredTime",columnDefinition="DATETIME COMMENT '有效时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date expiredTime;//有效时间
	
	@Column(name="pullTime",columnDefinition="DATETIME COMMENT '提取时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pullTime;//提取时间
	
	@Column(name="pullStatus",columnDefinition="int(1) COMMENT '提取状态:0未提取，1已提取'")
	private Integer pullStatus;//提取状态
	
	@Column(name="payType",columnDefinition="int(2) COMMENT '支付方式'")
	private Integer payType;//支付方式, 11.线下微信转款 12.线下支付宝转款 21.线上微信支付宝 22.线上支付宝支付
	
	@Column(name="flowNo",columnDefinition="char(10) COMMENT '订单流水号'")
	private String flowNo;
	
	@Transient
	private Long resolverId;
	

	/**
	 * @return the payType
	 */
	public Integer getPayType() {
		return payType;
	}

	/**
	 * @param payType the payType to set
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}


	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the orderTime
	 */
	public Date getOrderTime() {
		return orderTime;
	}

	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * @return the pullTime
	 */
	public Date getPullTime() {
		return pullTime;
	}

	/**
	 * @param pullTime the pullTime to set
	 */
	public void setPullTime(Date pullTime) {
		this.pullTime = pullTime;
	}

	/**
	 * @return the openIdwc
	 */
	public String getOpenIdwc() {
		return openIdwc;
	}

	/**
	 * @param openIdwc the openIdwc to set
	 */
	public void setOpenIdwc(String openIdwc) {
		this.openIdwc = openIdwc;
	}

	/**
	 * @return the flowNo
	 */
	public String getFlowNo() {
		return flowNo;
	}

	/**
	 * @param flowNo the flowNo to set
	 */
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getRates() {
		return rates;
	}

	public void setRates(Integer rates) {
		this.rates = rates;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(Integer flowStatus) {
		this.flowStatus = flowStatus;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Integer getPullStatus() {
		return pullStatus;
	}

	public void setPullStatus(Integer pullStatus) {
		this.pullStatus = pullStatus;
	}

	public Integer getSignType() {
		return signType;
	}

	public void setSignType(Integer signType) {
		this.signType = signType;
	}

	public Long getResolverId() {
		return resolverId;
	}

	public void setResolverId(Long resolverId) {
		this.resolverId = resolverId;
	}

	public List<CoeOrderProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<CoeOrderProduct> productList) {
		this.productList = productList;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public List<Long> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<Long> productIdList) {
		this.productIdList = productIdList;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}



}
