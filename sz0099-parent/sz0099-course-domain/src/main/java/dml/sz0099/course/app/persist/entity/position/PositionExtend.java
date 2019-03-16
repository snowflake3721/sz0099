/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.position;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

import dml.sz0099.course.app.persist.entity.position.Position;

/**
 * <pre>
 * @formatter:off
 *
 * 通用位置模块定义
 * 
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
@Table(name="s9_PositionExtend")
public class PositionExtend extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -810430467350630451L;
	
	public static final Integer MAINMAXNUM_DEFAULT = 20;//总类数
	public static final Integer SUBMAXNUM_DEFAULT = 5;//子类个数
	public static final Integer DEPTHMAXNUM_DEFAULT = 4;//深度
	public static final Integer REFMAXNUM_DEFAULT = 20000;//关联产品数
	
	public static final Integer VARIETYMAXNUM_DEFAULT = 20;//一个用户所能创建的品种数
	
	@Transient
	public List<Position> positions;
	
	@Column(name="domain",columnDefinition="varchar(60) COMMENT '域名'")
	private String domain;//区分哪个站点
	
	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;//位置申请者用户id
	
	@Column(name="devId", length = 10, columnDefinition = "char(10) COMMENT '开发者id'")
	private String devId;//开发者id
	
	@Column(name="project",columnDefinition="char(32) COMMENT '项目名称'")
	private String project;//所属项目
	
	@Column(name="module",columnDefinition="char(32) COMMENT '所属模块'")
	private String module;//所属模块 如：文章、产品
	
	@Column(name="variety",columnDefinition="char(32) COMMENT '所属类别'")
	private String variety;//所属品种，如：文章、产品
	
	@Column(name="position",columnDefinition="char(32) COMMENT '位置 如：系统，圈子'")
	private String position;//所属位置，如：系统，圈子
	
	@Column(name="positionId",columnDefinition="BIGINT(20) COMMENT '该id颁发给调用者'")
	private Long positionId;//位置id,用户申请之后产生此id,后续用户可根据此id来上传图片
	
	@Column(name="mainMaxnum",columnDefinition="INT(4) COMMENT '位置最大数量'")
	private Integer mainMaxnum;//如 位置个数
	
	@Column(name="subMaxnum",columnDefinition="INT(4) COMMENT '子类最大数量'")
	private Integer subMaxnum;//每个类别下子类个数
	
	@Column(name="refMaxnum",columnDefinition="INT(11) COMMENT '每个品种所能挂载的产品总数'")
	private Integer refMaxnum;//每个品种所能挂载的产品总数
	

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

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getMainMaxnum() {
		return mainMaxnum;
	}

	public void setMainMaxnum(Integer mainMaxnum) {
		this.mainMaxnum = mainMaxnum;
	}

	public Integer getSubMaxnum() {
		return subMaxnum;
	}

	public void setSubMaxnum(Integer subMaxnum) {
		this.subMaxnum = subMaxnum;
	}

	public Integer getRefMaxnum() {
		return refMaxnum;
	}

	public void setRefMaxnum(Integer refMaxnum) {
		this.refMaxnum = refMaxnum;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}


}
