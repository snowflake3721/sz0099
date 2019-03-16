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

import org.jit4j.core.persist.entity.BaseEntityExtendLong;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <pre>
 * @formatter:off
 *
 * 活动时间
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
@Table(name="s9_act_ActivityTime")
public class CoeActivityTime extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	
	@Transient
	private CoeActivity activity;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '活动作者id'")
	private Long userId;
	
	/**
	 * 用于接收页面传递参数，段落所属主域
	 * 这里对应activityId
	 */
	@Column(name="activityId",length = 20, columnDefinition = "BIGINT(20) COMMENT '活动Id'")
	private Long mainId;
	
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
	
	@Transient
	private List<JoinItem> joinItemList;

	public CoeActivity getActivity() {
		return activity;
	}

	public void setActivity(CoeActivity activity) {
		this.activity = activity;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
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

	public List<JoinItem> getJoinItemList() {
		return joinItemList;
	}

	public void setJoinItemList(List<JoinItem> joinItemList) {
		this.joinItemList = joinItemList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}



}
