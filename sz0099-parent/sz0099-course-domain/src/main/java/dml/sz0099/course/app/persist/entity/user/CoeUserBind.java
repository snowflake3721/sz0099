/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.user;

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

/**
 * <pre>
 * @formatter:off
 *
 * 推广用户绑定
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
@Table(name="s9_coe_UserBind")
public class CoeUserBind extends BaseEntityExtendLong implements Serializable {
	
	private static final long serialVersionUID = -559454922257655036L;
	
	public static final PropertyContext STATUS=L.initProperty("有效状态", "设置有效状态", "有效状态", "有效状态，用户选择绑定邀请者后，由管理员审核后生效" , "status", CoeUserBind.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext STATUS_0_INIT=STATUS.initLabel(0, "初始化","I", "初始化");
	public static final LabelContext STATUS_1_WAIT_VERIFY=STATUS.initLabel(1, "待审核","W", "待审核");
	public static final LabelContext STATUS_2_PASS=STATUS.initLabel(2, "有效","P", "审核通过");
	public static final LabelContext STATUS_3_EXPIRED=STATUS.initLabel(3, "已过期","E", "已过期");

	
	@Column(name="userId",columnDefinition="BIGINT(20) COMMENT '用户Id'")
	private Long userId;//用户id,是指邀请者id，没有邀请过的用户不该有记录
	
	@Column(name="bindId",columnDefinition="BIGINT(20) COMMENT '绑定用户Id'")
	private Long bindId;//绑定 用户id，绑定之后，点击【换】按钮，即获取该id(换时，默认只取第一个有效绑定)
	
	@Column(name="topLevel",columnDefinition="INT(3) COMMENT '绑定顺序[取值1-6]'")
	private Integer topLevel;//记录绑定顺序，每人最多绑6个，1-6依次往下排
	
	@Column(name="status",columnDefinition="INT(3) COMMENT '绑定状态'")
	private Integer status;//同一个用户，可拥有多个绑定者同时处于有效状态，依次往下排
	
	@Column(name="beginTime",columnDefinition="DATETIME COMMENT '开始时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginTime;//开始时间
	
	@Column(name="endTime",columnDefinition="DATETIME COMMENT '结束时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;//结束时间
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBindId() {
		return bindId;
	}

	public void setBindId(Long bindId) {
		this.bindId = bindId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
