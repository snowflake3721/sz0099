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

import org.jit4j.core.persist.entity.BaseEntityExtendLong;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <pre>
 * @formatter:off
 *
 * 订单快递
 * 默认一个订单走一个快递
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
@Table(name="s9_coe_OrderExpress")
public class CoeOrderExpress extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = 5469255988936067756L;
	
	@Column(name="flowNo",columnDefinition="char(10) COMMENT '订单流水号'")
	private String flowNo;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '订单Id'")
	private Long orderId;//关联订单id, 与order一对一
	
	@Column(name="price",columnDefinition="int(8) COMMENT '快递价格(分)'")
	private Integer price;//快递价格
	
	@Column(name="expressNo",columnDefinition="char(24) COMMENT '快递流水号'")
	private String expressNo;
	
	@Column(name="expressId",columnDefinition="BIGINT(20) COMMENT '快递公司Id'")
	private Long expressId;//快递公司id
	
	@Column(name="address",columnDefinition="varchar(128) COMMENT '收货地址'")
	private String address;
	
	@Column(name="name",columnDefinition="char(16) COMMENT '收货人姓名'")
	private String name;
	
	@Column(name="phone",columnDefinition="char(16) COMMENT '收货人手机号'")
	private String phone;
	
	@Column(name="sendAddress",columnDefinition="varchar(128) COMMENT '发货地址'")
	private String sendAddress;
	
	@Column(name="sendName",columnDefinition="char(16) COMMENT '发货人姓名'")
	private String sendName;
	
	@Column(name="sendPhone",columnDefinition="char(16) COMMENT '发货人手机号'")
	private String sendPhone;
	
	@Column(name="userId",columnDefinition="BIGINT(20) COMMENT '下单用户Id'")
	private Long userId;//下单用户id
	
	@Column(name="ownerId",columnDefinition="BIGINT(20) COMMENT '订单所有者Id'")
	private Long ownerId;//订单所有者id
	
	@Column(columnDefinition="DATETIME COMMENT '订单快递开始时间'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginTime;//订单快递开始时间
	
	/**
	 * 用户收货时填充此时间
	 */
	@Column(columnDefinition="DATETIME COMMENT '订单快递结束时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date endTime;//订单快递结束时间

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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
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

	public Long getExpressId() {
		return expressId;
	}

	public void setExpressId(Long expressId) {
		this.expressId = expressId;
	}
	

}
