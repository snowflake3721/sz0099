/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.position;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

/**
 * <pre>
 * @formatter:off
 *
 * 图片基本信息
 * @author bruce yang at 2018-09-21 14:20:50
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-21	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Entity
@Table(name="s9_PositionExtendLog")
public class PositionExtendLog extends BaseEntityExtendLong implements Serializable{

	private static final long serialVersionUID = 1073035860061247207L;

	@Column(name="extendId", length = 20, columnDefinition = "BIGINT(20) COMMENT '配置id'")
	private Long extendId;
	
	@Column(name="domain",columnDefinition="varchar(60) COMMENT '域名'")
	private String domain;
	
	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;//位置申请者用户id
	
	@Column(name="devId", length = 10, columnDefinition = "char(10) COMMENT '开发者id'")
	private String devId;//开发者id
	
	@Column(name="project",columnDefinition="char(32) COMMENT '项目名称'")
	private String project;//所属项目
	
	@Column(name="module",columnDefinition="char(32) COMMENT '所属模块'")
	private String module;//所属模块
	
	@Column(name="variety",columnDefinition="char(32) COMMENT '所属类别'")
	private String variety;//所属品种，如：文章、产品
	
	@Column(name="position",columnDefinition="char(32) COMMENT '位置'")
	private String position;//所属位置，如：头部，段落内容，尾部，封面
	
	@Column(name="positionId",columnDefinition="BIGINT(20) COMMENT '该id颁发给调用者'")
	private Long positionId;//位置id,用户申请之后产生此id,后续用户可根据此id来上传图片

	@Transient
	private PositionExtendContent content;
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Long getExtendId() {
		return extendId;
	}

	public void setExtendId(Long extendId) {
		this.extendId = extendId;
	}

	public PositionExtendContent getContent() {
		return content;
	}

	public void setContent(PositionExtendContent content) {
		this.content = content;
	}
	
	


	
}
