/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.product;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * <pre>
 * @formatter:off
 *
 * 产品操作日志
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
@Table(name="s9_coe_ProductLog")
public class CoeProductLog extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	@Column(name="code",columnDefinition="char(32) COMMENT '代码'")
	private String code;//操作代码
	
	@Column(name="name",columnDefinition="char(32) COMMENT '名称'")
	private String name;//操作名称
	
	@Column(name="description",columnDefinition="varchar(128) COMMENT '操作描述'")
	private String description;//操作描述
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;//用户id
	
	@Column(name="nickname",columnDefinition="char(100) COMMENT '微信昵称'")
	private String nickname;//用户昵称
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '产品Id'")
	private Long productId;//产品id
	
	@Column(name="productname",columnDefinition="char(40) COMMENT '产品名称'")
	private String productname;//产品名称

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

}
