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
import javax.persistence.Transient;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <pre>
 * @formatter:off
 *
 * 集合时间与地点
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
@Table(name="s9_act_JoinItem")
public class JoinItem extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = 8426536810837546707L;

	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;
	
	@Column(name="baseId", length = 20, columnDefinition = "BIGINT(20) COMMENT 'CoeActivityTime.id'")
	private Long baseId;
	
	@Transient
	private CoeActivityTime activityTime;
	
	/**
	 * 用于接收页面传递参数，集合时间所属
	 * 这里对应activityId
	 */
	@Column(name="activityId", length = 20, columnDefinition = "BIGINT(20) COMMENT '活动id'")
	private Long mainId;
	
	@Column(name="place",columnDefinition="varchar(32) COMMENT '地点'")
	private String place;
	
	@Column(name="joinTime",columnDefinition="DATETIME COMMENT '活动截止报名时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date joinTime;//集合时间
	
	@Column(name="description",columnDefinition="varchar(512) COMMENT '补充说明'")
	private String description;
	

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

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public CoeActivityTime getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(CoeActivityTime activityTime) {
		this.activityTime = activityTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
