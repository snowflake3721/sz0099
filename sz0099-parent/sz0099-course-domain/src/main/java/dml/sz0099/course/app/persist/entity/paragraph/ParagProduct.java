/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.paragraph;

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
 * 产品段落
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
@Table(name="s9_ParagProduct")
public class ParagProduct extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = 8426536810837546707L;

	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;
	
	//@Column(name="productId", length = 20, columnDefinition = "BIGINT(20) COMMENT '产品id'")
	//private Long productId;
	
	@Column(name="paragId", length = 20, columnDefinition = "BIGINT(20) COMMENT 'paragId'")
	private Long paragId;
	
	@Transient
	private Paragraph paragraph;
	
	/**
	 * 用于接收页面传递参数，段落所属主域
	 * 这里对应 productId
	 */
	@Column(name="productId", length = 20, columnDefinition = "BIGINT(20) COMMENT '产品id'")
	private Long mainId;
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getParagId() {
		return paragId;
	}

	public void setParagId(Long paragId) {
		this.paragId = paragId;
	}

	public Paragraph getParagraph() {
		return paragraph;
	}

	public void setParagraph(Paragraph paragraph) {
		this.paragraph = paragraph;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

}
