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
 * 订单
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
@Table(name="s9_coe_OrderTransfer")
public class CoeOrderTransfer extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = 5469255988936067756L;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '订单orderAsignId'")
	private Long orderAsignId;

	@Column(name="flowNo",columnDefinition="char(10) COMMENT '订单流水号'")
	private String flowNo;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '订单Id'")
	private Long orderId;//关联订单id
	
	@Column(name="resolverId",columnDefinition="BIGINT(20) COMMENT '订单处理者Id'")
	private Long resolverId;//订单第一处理者id
	
	/**
	 * 订单接手者id,仅存储最后一个接手者的id
	 * 若订单被反复转出，详细日志见CoeOrderTransferLog
	 */
	@Column(name="trasferInId",columnDefinition="BIGINT(20) COMMENT '订单接手者Id'")
	private Long trasferInId;//订单接手者id
	
	@Column(name="trasferStatus",columnDefinition="int(1) COMMENT '订单转入状态: 0已转入，1已处理，2已转出'")
	private Integer trasferStatus;//订单转入状态
	
	@Column(columnDefinition="DATETIME COMMENT '订单转出处理开始时间'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginTime;//订单转出处理开始时间
	
	/**
	 * 只有当状态处于 已处理 时 视为该用户对此订单的操作结束
	 */
	@Column(columnDefinition="DATETIME COMMENT '订单转出处理结束时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date endTime;//订单转出处理结束时间
	
	public Long getOrderAsignId() {
		return orderAsignId;
	}

	public void setOrderAsignId(Long orderAsignId) {
		this.orderAsignId = orderAsignId;
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

	public Long getResolverId() {
		return resolverId;
	}

	public void setResolverId(Long resolverId) {
		this.resolverId = resolverId;
	}

	public Integer getTrasferStatus() {
		return trasferStatus;
	}

	public void setTrasferStatus(Integer trasferStatus) {
		this.trasferStatus = trasferStatus;
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
	


}
