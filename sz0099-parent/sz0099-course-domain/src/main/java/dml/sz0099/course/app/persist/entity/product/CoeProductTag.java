/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.product;

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
 * 产品与标签绑定
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
@Table(name="s9_coe_ProductTag")
public class CoeProductTag extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	//@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '产品Id'")
	//private Long productId;
	
	@Transient
	private CoeProduct product;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '标签Id'")
	private Long tagId;
	
	@Transient
	private CoeTag tag;
	
	@Column(name="name",columnDefinition="char(32) COMMENT '冗余名称,tag.name'")
	private String name;
	
	/**
	 * 用于接收页面传递参数，段落所属主域
	 * 这里对应productId
	 */
	@Column(name="productId",length = 20, columnDefinition = "BIGINT(20) COMMENT '产品Id'")
	private Long mainId;
	
	

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public CoeProduct getProduct() {
		return product;
	}

	public void setProduct(CoeProduct product) {
		this.product = product;
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

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}



}
