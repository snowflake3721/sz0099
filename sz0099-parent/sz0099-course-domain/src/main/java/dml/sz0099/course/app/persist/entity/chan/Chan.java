/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.chan;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * <pre>
 * @formatter:off
 *
 * 通用位置定义
 * TODO 要为每个位置定义 最大显示数量，最大允许添加数量
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
@Table(name="s9_chan_Chan")
public class Chan extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -4795277758856470669L;
	
	public static final PropertyContext STATUS=L.initProperty("频道状态", "选择频道状态", "频道状态", "频道状态，开启时会展现", "status", Chan.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext STATUS_0_INIT=STATUS.initLabel(0, "待审","Init", "位置状态：待审");
	public static final LabelContext STATUS_1_OPEN=STATUS.initLabel(1, "开启","Open", "位置状态：开启");
	public static final LabelContext STATUS_2_CLOSED=STATUS.initLabel(2, "关闭","Close", "位置状态：关闭");
	
	@Transient
	private List<ChanRef> refs;
	
	@Transient
	private Page<ChanRef> refPage;
	
	@Transient
	private ChanExtend extend;
	
	@Column(name="extendId", length = 20, columnDefinition = "BIGINT(20) COMMENT '频道模块扩展Id'")
	private Long extendId;
	
	@Column(name="positionId",columnDefinition="BIGINT(20) COMMENT '该id颁发给调用者'")
	private Long positionId;//位置id,用户申请之后产生此id,后续用户可根据此id来上传图片
	
	@Column(name="mainId", length = 20, columnDefinition = "BIGINT(20) COMMENT '圈子id, 系统id'")
	private Long mainId;//position是圈子时,存圈子id; position是system时，存平台id(platId)
	
	@Column(name="subId", length = 20, columnDefinition = "BIGINT(20) COMMENT 'subId'")
	private Long subId;//频道类别
	
	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '位置所属用户Id'")
	private Long userId;//位置所属用户
	
	@Column(name="status",columnDefinition="int(1) COMMENT '位置状态，0待审 1 开启 2关闭'")
	private Integer status;
	
	@Column(name="title",columnDefinition="char(16) COMMENT '频道标题'")
	private String title;//频道标题
	
	@Column(name="link",columnDefinition="varchar(255) COMMENT '频道链接导向'")
	private String link; //链接导向
	
	
	@Column(name="openTime",columnDefinition="DATETIME COMMENT '开启时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date openTime;//开启时间
	
	@Column(name="closedTime",columnDefinition="DATETIME COMMENT '关闭时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date closedTime;//关闭时间

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(Date closedTime) {
		this.closedTime = closedTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public ChanExtend getExtend() {
		return extend;
	}

	public void setExtend(ChanExtend extend) {
		this.extend = extend;
	}

	public Long getExtendId() {
		return extendId;
	}

	public void setExtendId(Long extendId) {
		this.extendId = extendId;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}


}
