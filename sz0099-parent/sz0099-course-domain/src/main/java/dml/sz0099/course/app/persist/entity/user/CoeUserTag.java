/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

import dml.sz0099.course.app.persist.entity.tag.CoeTag;

/**
 * <pre>
 * @formatter:off
 *
 * 用户与标签绑定
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
@Table(name="s9_coe_UserTag")
public class CoeUserTag extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long coeUserId;
	
	@Transient
	private CoeUser user;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '标签Id'")
	private Long tagId;
	
	@Transient
	private CoeTag tag;
	
	@Column(name="name",columnDefinition="char(32) COMMENT '冗余名称,tag.name'")
	private String name;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '业务能力Id'")
	private Long professionId;//为该标签关联业务能力
	

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public CoeTag getTag() {
		return tag;
	}

	public void setTag(CoeTag tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
