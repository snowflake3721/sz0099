/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.profession;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.UserBaseEntityExtend;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * <pre>
 * @formatter:off
 *
 * 技能认证申请
 * 
 * @author bruce yang at 2018-09-28 15:46:46
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-28	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Entity
@Table(name="s9_pro_ProfessionVerify")
public class ProfessionVerify extends UserBaseEntityExtend implements Serializable {

	private static final long serialVersionUID = -8247052934215592859L;
	
	public static final PropertyContext VERIFY_TYPE=L.initProperty("技能认证类型", "选择技能认证类型", "技能认证类型", "获得认证后将显示认证信息，隐藏谨慎提醒标识", "verifyType", ProfessionVerify.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext VERIFY_TYPE_NO=VERIFY_TYPE.initLabel(0, "<span class='glyphicon glyphicon-warning-sign'></span>未认证","no", "<span class='text-danger'>特别提醒：<strong><span class='glyphicon glyphicon-warning-sign'></span></strong> 该大侠技能未经【群侠官方】认证，须谨慎合作!</span>","<span class='glyphicon glyphicon-warning-sign text-danger'></span>");
	public static final LabelContext VERIFY_TYPE_SPECIAL_PARTER=VERIFY_TYPE.initLabel(1, "❦特约合作","special_parter", "特约合作","❦");
	public static final LabelContext VERIFY_TYPE_SPECIAL_PROFESSION_VERIFY=VERIFY_TYPE.initLabel(2, "☯匠神认证","profession_verify", "匠神认证","☯");
	public static final LabelContext VERIFY_TYPE_FRIEND_VERIFY=VERIFY_TYPE.initLabel(3, "♥友情认证","friend_verify", "友情认证","♥");
	public static final LabelContext VERIFY_TYPE_MAJOR_VERIFY=VERIFY_TYPE.initLabel(4, "★专业认证","major_verify", "专业认证","★");
	public static final LabelContext VERIFY_TYPE_VIP_VERIFY=VERIFY_TYPE.initLabel(5, "◆VIP认证","vip_verify", "VIP认证","◆");
	public static final LabelContext VERIFY_TYPE_EXPERIENCE_VERIFY=VERIFY_TYPE.initLabel(6, "❉体验认证","experience_verify", "体验认证","❉");

	public static final PropertyContext VERIFY_STATUS=L.initProperty("技能认证状态", "选择技能认证状态", "技能认证状态", "技能认证通过时，方可显示认证信息", "verifyStatus", ProfessionVerify.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext VERIFY_STATUS_NO=VERIFY_STATUS.initLabel(0, "未认证","NO", "技能认证状态：未认证");
	public static final LabelContext VERIFY_STATUS_PROCESS=VERIFY_STATUS.initLabel(1, "认证中","PROCESS", "技能认证中，系统正在审核合规性...");
	public static final LabelContext VERIFY_STATUS_PASS=VERIFY_STATUS.initLabel(2, "已认证通过","PASS", "技能已认证通过");
	public static final LabelContext VERIFY_STATUS_EXPIRED=VERIFY_STATUS.initLabel(3, "已过期","EXPIRED", "该技能认证已过有效期，合作请留心！");
	public static final LabelContext VERIFY_STATUS_CLOSED=VERIFY_STATUS.initLabel(4, "已关闭","CLOSED", "技能认证已被关闭，务必谨慎核实！");
	
	
	@Column(name="professionId",length = 20, columnDefinition = "BIGINT(20) COMMENT '技能Id'")
	private Long mainId;//技能id, Profession.id
	
	
	@Column(name="applyTime",columnDefinition="DATETIME COMMENT '技能认证申请时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date applyTime;//申请时间
	
	@Column(name="refreshTime",columnDefinition="DATETIME COMMENT '技能刷新时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date refreshTime;//认证刷新时间
	
	@Column(name="closedTime",columnDefinition="DATETIME COMMENT '技能关闭时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date closedTime;//技能认证关闭时间
	
	@Column(name="expiredTime",columnDefinition="DATETIME COMMENT '技能有效期'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date expiredTime;//技能认证有效期
	
	@Column(name="verifyTime",columnDefinition="DATETIME COMMENT '技能认证审核时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date verifyTime;//技能认证审核时间
	 
	@Column(name="verifyType",columnDefinition="int(3) COMMENT '技能认证'")
	private Integer verifyType;//技能认证类型
	
	@Column(name="verifyLevel",columnDefinition="int(1) COMMENT '技能认证级别'")
	private Integer verifyLevel;//技能认证级别, 几级就显示几颗星 0-5级
	
	@Column(name="verifyPre",columnDefinition="char(10) COMMENT '技能认证导语'")
	private String verifyPre;//技能认证导语:来自于 认证类型 verifyType.label
	
	@Column(name="verifyStatus",columnDefinition="int(1) COMMENT '技能认证状态'")
	private Integer verifyStatus;//技能认证状态
	


	public Integer getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(Integer verifyType) {
		this.verifyType = verifyType;
	}

	public Integer getVerifyLevel() {
		return verifyLevel;
	}

	public void setVerifyLevel(Integer verifyLevel) {
		this.verifyLevel = verifyLevel;
	}

	public String getVerifyPre() {
		return verifyPre;
	}

	public void setVerifyPre(String verifyPre) {
		this.verifyPre = verifyPre;
	}

	public Integer getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}


}