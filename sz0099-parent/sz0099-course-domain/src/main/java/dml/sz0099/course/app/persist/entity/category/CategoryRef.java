/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.category;

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
 * description: 功能说明
 * @author bruce yang at 2018-10-01 23:21:27
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-10-01	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Entity
@Table(name="s9_CategoryRef")
public class CategoryRef extends BaseEntityExtendLong implements Serializable{

	private static final long serialVersionUID = 30825070522649282L;

	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;
	
	@Column(name="extendId", length = 20, columnDefinition = "BIGINT(20) COMMENT '扩展信息Id'")
	private Long extendId;
	
	@Column(name="baseId", length = 20, columnDefinition = "BIGINT(20) COMMENT '基本信息Id,即Category.id'")
	private Long baseId;
	
	@Column(name="mainId",columnDefinition="BIGINT(20) COMMENT '主体id:即关联者id,如产品id、文章id'")
	private Long mainId;
	
	/*@Column(name="subId",columnDefinition="BIGINT(20) COMMENT '子id'")
	private Long subId;//如 文章下的段落子类
*/	
	@Column(name="positionId", length = 20, columnDefinition = "BIGINT(20) COMMENT '扩展信息positionId'")
	private Long positionId;//CategoryExtend.positionId
	
	@Transient
	private CategoryExtend extend;
	
	@Transient
	private Category category;
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getExtendId() {
		return extendId;
	}

	public void setExtendId(Long extendId) {
		this.extendId = extendId;
	}

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}


	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public CategoryExtend getExtend() {
		return extend;
	}

	public void setExtend(CategoryExtend extend) {
		this.extend = extend;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
