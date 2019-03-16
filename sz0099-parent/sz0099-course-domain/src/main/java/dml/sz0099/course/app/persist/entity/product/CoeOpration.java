/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.BaseEntityExtendLong;

import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * <pre>
 * @formatter:off
 *
 * 产品操作模板定义
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
@Table(name="s9_coe_Opration")
public class CoeOpration extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	public static final PropertyContext CODE=L.initProperty("操作代码", "选择操作", "操作代码", "操作代码定义操作项", "code", CoeOpration.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext CODE_PUBLISH=CODE.initLabel(1, "PUBLISH", "发布","PUBLISH", "发布产品", LabelContext.VALUETYPE_INTEGER,"№");
	public static final LabelContext CODE_SHELVED=CODE.initLabel(2, "SHELVED", "上架","SHELVED", "上架", LabelContext.VALUETYPE_INTEGER,"№");

	
	@Column(name="code",columnDefinition="char(32) COMMENT '代码'")
	private String code;//操作代码
	
	@Column(name="name",columnDefinition="char(32) COMMENT '名称'")
	private String name;//操作名称
	
	@Column(name="description",columnDefinition="varchar(128) COMMENT '操作描述'")
	private String description;//操作描述
	
	@Column(name="domain",columnDefinition="varchar(255) COMMENT '领域模型'")
	private String domain;//领域模型
	

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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}


}
