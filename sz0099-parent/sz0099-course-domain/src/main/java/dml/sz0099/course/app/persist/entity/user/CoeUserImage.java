/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

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
@Table(name="s9_coe_UserImage")
public class CoeUserImage extends BaseEntityExtendLong implements Serializable {
	
	private static final long serialVersionUID = -559454922257655036L;
	
	public static final Long SUBID_IDENTITY_FACE=1l;//对应图片位置为身份证正面
	public static final Long SUBID_IDENTITY_BACK=2l;//对应图片位置为身份证反面
	public static final Long SUBID_HEADIMAGE=3l;//对应图片位置为头像
	public static final Long SUBID_PAY_RECIEVE=4l;//对应图片位置为微信收款码
	
	public static final List<Long> SUBIDLIST_USER = new ArrayList<>(2);
	public static final List<Long> SUBIDLIST_VERIFY = new ArrayList<>(2);
	
	static {
		SUBIDLIST_USER.add(SUBID_HEADIMAGE);
		SUBIDLIST_USER.add(SUBID_PAY_RECIEVE);
	}
	static {
		SUBIDLIST_VERIFY.add(SUBID_IDENTITY_FACE);
		SUBIDLIST_VERIFY.add(SUBID_IDENTITY_BACK);
	}

	@Column(name="userId",columnDefinition="BIGINT(20) COMMENT '用户Id'")
	private Long userId;//用户id
	
	@Column(length = 255, name="fullurl",columnDefinition="varchar(255) COMMENT '头像'")
	private String fullurl;
	
	@Column(name="coeUserId",length=20,columnDefinition="BIGINT(20) COMMENT 'coeUserId=mainId=CoeUser.id'")
	private Long coeUserId;
	
	@Column(name="subId",length=1,columnDefinition="int(1) COMMENT 'subId'")
	private Long subId;//区分位置
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public String getFullurl() {
		return fullurl;
	}

	public void setFullurl(String fullurl) {
		this.fullurl = fullurl;
	}

	public Long getCoeUserId() {
		return coeUserId;
	}

	public void setCoeUserId(Long coeUserId) {
		this.coeUserId = coeUserId;
	}
	
	

	



}
