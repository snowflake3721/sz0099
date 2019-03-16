/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.BaseEntityExtendLong;
import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * <pre>
 * @formatter:off
 *
 * 订单分配
 * 将待发货订单 转为 处理中，进行支付核验，下一步执行发货操作
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
@Table(name="s9_coe_OrderAsign")
public class CoeOrderAsign extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = 5469255988936067756L;
	
	public static final PropertyContext VERIFY_STATUS=L.initProperty("审核状态", "选择审核状态", "审核状态", "审核订单", "publishStatus", CoeProduct.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext VERIFY_STATUS_INIT=VERIFY_STATUS.initLabel(0, "处理中","NOT_VERIFY", "审核状态：处理中");
	public static final LabelContext VERIFY_STATUS_HAS_TRANSFERING=VERIFY_STATUS.initLabel(1, "转单中","TRANSFERING", "审核状态：转单中");
	public static final LabelContext VERIFY_STATUS_HAS_TRANSFERED=VERIFY_STATUS.initLabel(2, "已转单","TRANSFERED", "审核状态：已转单");
	public static final LabelContext VERIFY_STATUS_HAS_VERIFIED=VERIFY_STATUS.initLabel(3, "已处理","VERIFIED", "审核状态：已处理");


	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '产品Id'")
	private Long productId;//关联产品id
	
	@Column(name="productNo",columnDefinition="char(10) COMMENT '产品编号'")
	private String productNo;//产品编号
	
	@Column(name="flowNo",columnDefinition="char(10) COMMENT '订单流水号'")
	private String flowNo;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '订单Id'")
	private Long orderId;//关联订单id, 与order一对一
	
	@Column(name="price",columnDefinition="int(8) COMMENT '支付价(分)'")
	private Integer price;//支付价格
	
	/**
	 * 核对支付价格,若收到的钱与实际支付不一致，填写此值
	 * 
	 */
	@Column(name="priceVerify",columnDefinition="int(8) COMMENT '核对支付价(分)'")
	private Integer priceVerify;
	
	@Column(name="userId",columnDefinition="BIGINT(20) COMMENT '用户Id'")
	private Long userId;//用户id
	
	@Column(name="ownerId",columnDefinition="BIGINT(20) COMMENT '订单所有者Id'")
	private Long ownerId;//订单所有者id
	
	@Column(name="resolverId",columnDefinition="BIGINT(20) COMMENT '订单处理者Id'")
	private Long resolverId;//订单第一个处理者id
	
	@Column(name="finalResolverId",columnDefinition="BIGINT(20) COMMENT '订单最终处理者Id'")
	private Long finalResolverId;//订单最终处理者id
	
	@Column(name="verifyStatus",columnDefinition="int(1) COMMENT '订单处理状态: 0 处理中，1已处理，2转出中，3已转出'")
	private Integer verifyStatus;//订单处理状态
	
	@Column(columnDefinition="DATETIME COMMENT '订单处理开始时间'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginTime;//订单处理开始时间
	
	/**
	 * 当订单处于已发货状态时，填充该时间
	 * 若订单被转出，则执行转出流程CoeOrderTransfer，该时间此时在该字段并不记录，直到有接手者将订单处理成已发货
	 */
	@Column(columnDefinition="DATETIME COMMENT '订单处理结束时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date endTime;//订单处理结束时间
	
	@Column(columnDefinition="DATETIME COMMENT '订单收货时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date recieveTime;//订单收货时间

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getResolverId() {
		return resolverId;
	}

	public void setResolverId(Long resolverId) {
		this.resolverId = resolverId;
	}

	public Integer getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
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

	public Long getFinalResolverId() {
		return finalResolverId;
	}

	public void setFinalResolverId(Long finalResolverId) {
		this.finalResolverId = finalResolverId;
	}

	public Date getRecieveTime() {
		return recieveTime;
	}

	public void setRecieveTime(Date recieveTime) {
		this.recieveTime = recieveTime;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPriceVerify() {
		return priceVerify;
	}

	public void setPriceVerify(Integer priceVerify) {
		this.priceVerify = priceVerify;
	}


}
