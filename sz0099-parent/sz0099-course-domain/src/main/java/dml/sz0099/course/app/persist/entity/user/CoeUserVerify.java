/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * <pre>
 * @formatter:off
 *
 * 课程专用用户信息
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
@Table(name="s9_coe_UserVerify")
public class CoeUserVerify extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -3550039314891661942L;
	
	public static final PropertyContext IDSTATUS=L.initProperty("身份认证状态", "选择身份认证", "身份认证", "身份认证，用户上传身份后进行身份认证，认证通过后方可进行资料分享" , "idstatus", CoeUser.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext IDSTATUS_ALL=IDSTATUS.initLabel(-1, "全部","ALL", "全部");
	public static final LabelContext IDSTATUS_0_NO=IDSTATUS.initLabel(0, "未认证","N", "未认证");
	public static final LabelContext IDSTATUS_1_PROCESS=IDSTATUS.initLabel(1, "认证中","P", "认证中");
	public static final LabelContext IDSTATUS_2_YES=IDSTATUS.initLabel(2, "已认证","Y", "已认证");
	public static final LabelContext IDSTATUS_3_REJECT=IDSTATUS.initLabel(3, "驳回认证","R", "驳回认证");

	
	@Transient
	private CoeUser coeUser;
	
	@Column(name="userId",columnDefinition="BIGINT(20) COMMENT '用户Id'")
	private Long userId;//用户id
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long coeUserId;
	
	@Transient
	private CoeUserImage payRecieveImgE;
	
	@Column(name="realname",length=10,columnDefinition="char(10) COMMENT '姓名'")
	private String realname;//真实姓名
	
	@Column(name="identity",length=20,columnDefinition="char(20) COMMENT '身份证号'")
	private String identity;//身份证号
	
	@Column(name="identityFace",length=255,columnDefinition="varchar(255) COMMENT '身份证正面照'")
	private String identityFace;//身份证正面
	
	@Transient
	private CoeUserImage idImgFaceE;
	@Transient
	private CoeUserImage idImgBackE;
	
	@Column(name="identityBack",length=255,columnDefinition="varchar(255) COMMENT '身份证背面照'")
	private String identityBack;//身份证背面
	
	@Column(name="verifyTime",columnDefinition="DATETIME COMMENT '审核时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date verifyTime;//审核时间
	
	@Column(name="applyTime",columnDefinition="DATETIME COMMENT '申请时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date applyTime;//申请时间
	
	@Column(name="idstatus",length=1,columnDefinition="int(1) COMMENT '身份认证状态'")
	private Integer idstatus;//身份认证状态
	
	@Column(name="remark",length=255,columnDefinition="varchar(255) COMMENT '审核备注'")
	private String remark;//审核备注
	
	@Transient
	private Long subId;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public CoeUserImage getPayRecieveImgE() {
		return payRecieveImgE;
	}

	public void setPayRecieveImgE(CoeUserImage payRecieveImgE) {
		this.payRecieveImgE = payRecieveImgE;
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

	public String getIdentityFace() {
		return identityFace;
	}

	public void setIdentityFace(String identityFace) {
		this.identityFace = identityFace;
	}

	public CoeUserImage getIdImgFaceE() {
		return idImgFaceE;
	}

	public void setIdImgFaceE(CoeUserImage idImgFaceE) {
		this.idImgFaceE = idImgFaceE;
	}

	public CoeUserImage getIdImgBackE() {
		return idImgBackE;
	}

	public void setIdImgBackE(CoeUserImage idImgBackE) {
		this.idImgBackE = idImgBackE;
	}

	public String getIdentityBack() {
		return identityBack;
	}

	public void setIdentityBack(String identityBack) {
		this.identityBack = identityBack;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public Long getCoeUserId() {
		return coeUserId;
	}

	public void setCoeUserId(Long coeUserId) {
		this.coeUserId = coeUserId;
	}

	public CoeUser getCoeUser() {
		return coeUser;
	}

	public void setCoeUser(CoeUser coeUser) {
		this.coeUser = coeUser;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIdstatus() {
		return idstatus;
	}

	public void setIdstatus(Integer idstatus) {
		this.idstatus = idstatus;
	}

}
