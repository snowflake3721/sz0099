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
@Table(name="s9_coe_User")
public class CoeUser extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -3550039314891661942L;
	
	
	public static final PropertyContext PAYRECIEVABLE=L.initProperty("启用收款码", "是否启用收款码", "启用收款码", "收款码，启用收款码后，用户打赏金额直接进入作者微信钱包" , "payRecievable", CoeUser.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PAYRECIEVABLE_0_NO=PAYRECIEVABLE.initLabel(0, "未启用","N", "未启用");
	public static final LabelContext PAYRECIEVABLE_1_YES=PAYRECIEVABLE.initLabel(1, "已启用","Y", "已启用");
	
	public static final PropertyContext PRIVACY_SETTING=L.initProperty("隐私设置", "显示或隐藏", "显示或隐藏", "隐私设置：显示或隐藏" , "showable", CoeUser.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PRIVACY_SETTING_0_HIDE=PRIVACY_SETTING.initLabel(0, "隐藏","HIDE", "未启用");
	public static final LabelContext PRIVACY_SETTING_1_SHOW=PRIVACY_SETTING.initLabel(1, "显示","SHOW", "已启用");
	
	
	@Transient
	private List<CoeUserImage> images;
	
	//@Column(name="sayword",length=64,columnDefinition="varchar(64) COMMENT '一句传说'")
	@Transient
	private Sayword sayword;//不能太长，简短为主
	
	@Transient
	private CoeUserVerify userVerify;//用户认证信息

	@Column(name="userId",columnDefinition="BIGINT(20) COMMENT '用户Id'")
	private Long userId;//用户id
	
	@Column(name="sex",columnDefinition="int(1) COMMENT '性别'")
    private Integer sex;
	
	@Column(name="openid",columnDefinition="char(32) COMMENT 'openid'")
	private String openIdwc;//用户微信id
	
	@Column(name="nickname",columnDefinition="char(100) COMMENT '微信昵称'")
	private String nickname;//冗余存储微信昵称
	
	@Column(name="postname",columnDefinition="char(16) COMMENT '职务称呼'")
	private String postname;//职务称呼
	
	@Column(name="postnameShow",length=1,columnDefinition="int(1) COMMENT '职务称呼显示状态:0隐藏 1显示'")
	private Integer postnameShow;
	
	@Column(length = 255, name="headimgurl",columnDefinition="varchar(255) COMMENT '头像'")
	private String headImg;
	
	@Transient
	private CoeUserImage headImgE;
	
	@Column(name="payRecievable",length=1,columnDefinition="int(1) COMMENT '启用收款码'")
	private Integer payRecievable;//允许启用收款码
	
	@Column(length = 255, name="payRecieveImg",columnDefinition="varchar(255) COMMENT '收款码'")
	private String payRecieveImg;
	
	@Transient
	private CoeUserImage payRecieveImgE;
	
	/**
	 * email与QQ必须填一个
	 */
	@Column(name="qq",columnDefinition="char(16) COMMENT 'QQ号'")
	private String qq;//QQ号
	
	@Column(name="qqShow",length=1,columnDefinition="int(1) COMMENT 'qq显示状态:0隐藏 1显示'")
	private Integer qqShow;//默认 隐藏
	
	@Column(name="email",columnDefinition="char(60) COMMENT 'email'")
	private String email;//feihu00000@163.com
	
	@Column(name="emailShow",length=1,columnDefinition="int(1) COMMENT 'Email显示状态:0隐藏 1显示'")
	private Integer emailShow;//默认 隐藏
	
	@Column(name="mobile",length=20,columnDefinition="varchar(20) COMMENT '手机号'")
	private String mobile;//用户手机号
	
	@Column(name="mobileShow",length=1,columnDefinition="int(1) COMMENT '手机号显示状态:0隐藏 1显示'")
	private Integer mobileShow;//默认 隐藏
	
	/*@Column(name="identity",length=20,columnDefinition="char(20) COMMENT '身份证号'")
	private String identity;//身份证号
	
	@Column(name="identityFace",length=255,columnDefinition="varchar(255) COMMENT '身份证正面照'")
	private String identityFace;//身份证正面
	
	@Transient
	private CoeUserImage idImgFaceE;
	@Transient
	private CoeUserImage idImgBackE;
	
	@Column(name="identityBack",length=255,columnDefinition="varchar(255) COMMENT '身份证背面照'")
	private String identityBack;//身份证背面
	*/
	
	
	@Column(name="refreshTime",columnDefinition="DATETIME COMMENT '用户刷新时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date refreshTime;//刷新时间
	
	
	@Transient
	private CoeUserGrade userGrade;
	
	@Transient
	private Long subId;
	
	@Transient
	private boolean resolved;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOpenIdwc() {
		return openIdwc;
	}

	public void setOpenIdwc(String openIdwc) {
		this.openIdwc = openIdwc;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public CoeUserGrade getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(CoeUserGrade userGrade) {
		this.userGrade = userGrade;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public List<CoeUserImage> getImages() {
		return images;
	}

	public void setImages(List<CoeUserImage> images) {
		this.images = images;
	}

	public CoeUserImage getHeadImgE() {
		return headImgE;
	}

	public void setHeadImgE(CoeUserImage headImgE) {
		this.headImgE = headImgE;
	}

	public String getPayRecieveImg() {
		return payRecieveImg;
	}

	public void setPayRecieveImg(String payRecieveImg) {
		this.payRecieveImg = payRecieveImg;
	}

	public CoeUserImage getPayRecieveImgE() {
		return payRecieveImgE;
	}

	public void setPayRecieveImgE(CoeUserImage payRecieveImgE) {
		this.payRecieveImgE = payRecieveImgE;
	}

	public Integer getPayRecievable() {
		return payRecievable;
	}

	public void setPayRecievable(Integer payRecievable) {
		this.payRecievable = payRecievable;
	}


	public Date getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}

	public Sayword getSayword() {
		return sayword;
	}

	public void setSayword(Sayword sayword) {
		this.sayword = sayword;
	}

	public boolean getResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	public String getPostname() {
		return postname;
	}

	public void setPostname(String postname) {
		this.postname = postname;
	}

	public CoeUserVerify getUserVerify() {
		return userVerify;
	}

	public void setUserVerify(CoeUserVerify userVerify) {
		this.userVerify = userVerify;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getPostnameShow() {
		return postnameShow;
	}

	public void setPostnameShow(Integer postnameShow) {
		this.postnameShow = postnameShow;
	}

	public Integer getQqShow() {
		return qqShow;
	}

	public void setQqShow(Integer qqShow) {
		this.qqShow = qqShow;
	}

	public Integer getEmailShow() {
		return emailShow;
	}

	public void setEmailShow(Integer emailShow) {
		this.emailShow = emailShow;
	}

	public Integer getMobileShow() {
		return mobileShow;
	}

	public void setMobileShow(Integer mobileShow) {
		this.mobileShow = mobileShow;
	}

	
	

	



}
