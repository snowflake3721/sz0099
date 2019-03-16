/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.activity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.BaseEntityExtendLong;

import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * <pre>
 * @formatter:off
 *
 * 用户报名活动订单
 * 同一活动下仅允许对应一个用户
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
@Table(name="s9_act_ActivityUser")
public class CoeActivityUser extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = 8426536810837546707L;
	
	//用户状态与订单状态应完全一致(某用户单独退款情况除外)
	/*public static final PropertyContext STATUS=L.initProperty("用户状态", "用户状态", "用户状态", "用户状态", "status", CoeActivityUser.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext STATUS_INIT=STATUS.initLabel(0, "初始化","INIT", "用户状态：初始化");
	public static final LabelContext STATUS_CONFIRM=STATUS.initLabel(1, "已确认","CONFIRM", "用户状态：已确认报名，待付款");
	public static final LabelContext STATUS_PAY=STATUS.initLabel(2, "已付款","PAY", "用户状态：已付款");
	public static final LabelContext STATUS_PAY_VERIFY=STATUS.initLabel(3, "已审核","PAY_VERIFY", "活动订单状态：已付款");
	public static final LabelContext STATUS_EXEING=STATUS.initLabel(4, "出行中","EXEING", "活动订单状态：已出行");
	public static final LabelContext STATUS_FINISH=STATUS.initLabel(5, "已完成","FINISH", "活动订单状态：已完成");
	public static final LabelContext STATUS_CANCEL=STATUS.initLabel(6, "已取消","CANCEL", "活动订单状态：已取消");
	public static final LabelContext STATUS_COMPLETED=STATUS.initLabel(9, "已结束","COMPLETED", "活动订单状态：已结束");
	public static final LabelContext STATUS_CLOSED=STATUS.initLabel(10, "已关闭","CLOSED", "活动订单状态：已关闭");*/

	
	public static final PropertyContext PAY_STATUS=L.initProperty("支付状态", "支付状态", "支付状态", "支付状态", "payStatus", CoeActivityUser.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PAY_STATUS_ALL=PAY_STATUS.initLabel(0, "全部","ALL", "支付状态:全部");
	public static final LabelContext PAY_STATUS_NO=PAY_STATUS.initLabel(1, "未支付","N", "支付状态:未支付");
	public static final LabelContext PAY_STATUS_YES=PAY_STATUS.initLabel(2, "已支付","Y", "支付状态:已支付");
	
	public static final PropertyContext INSURANCE_STATUS=L.initProperty("保险状态", "保险状态", "保险状态", "保险状态", "insuranceStatus", CoeActivityUser.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext INSURANCE_STATUS_NO=INSURANCE_STATUS.initLabel(0, "未购","NO", "保险状态：未购");
	public static final LabelContext INSURANCE_STATUS_YES=INSURANCE_STATUS.initLabel(1, "已购","YES", "保险状态：已购");
	
	@Transient
	private List<Integer> statusList;
	
	@Transient
	private Integer payStatus;
	
	@Column(name="status",columnDefinition="int(3) COMMENT '用户状态，0初始化,1已确认,2已付款, 3出行中, 4已完成,5已取消, 9已结束,10已关闭'")
	private Integer status;
	
	@Column(name="insuranceStatus",columnDefinition="int(3) COMMENT '保险状态，0未购,1已购'")
	private Integer insuranceStatus;
	
	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;
	
	@Transient
	private CoeActivity activity;
	
	/**
	 * 用于接收页面传递参数，集合时间所属
	 * 这里对应activityId
	 */
	@Column(name="activityId", length = 20, columnDefinition = "BIGINT(20) COMMENT '活动id'")
	private Long mainId;
	
	@Column(name="orderId", length = 20, columnDefinition = "BIGINT(20) COMMENT '订单id'")
	private Long baseId;//订单号
	
	@Column(name="realname",length=16,columnDefinition="varchar(16) COMMENT '姓名'")
	private String realname;//真实姓名
	
	@Column(name="identity",length=20,columnDefinition="varchar(20) COMMENT '身份证号'")
	private String identity;//身份证号
	
	@Column(name="mobile",length=20,columnDefinition="varchar(20) COMMENT '手机号'")
	private String mobile;//用户手机号
	

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

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getInsuranceStatus() {
		return insuranceStatus;
	}

	public void setInsuranceStatus(Integer insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}

	public List<Integer> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}



}
