/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.media;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

/**
 * <pre>
 * @formatter:off
 *
 * 图片基本信息
 * @author bruce yang at 2018-09-21 14:20:50
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-21	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Entity
@Table(name="s9_ImageExtendContent")
public class ImageExtendContent extends BaseEntityExtendLong implements Serializable{

	private static final long serialVersionUID = 1073035860061247207L;

	@Column(name="extendLogId", length = 20, columnDefinition = "BIGINT(20) COMMENT '配置id'")
	private Long extendLogId;
	
	@Column(name="content",columnDefinition="text(2000) COMMENT 'json数据'")
	private String content;

	public Long getExtendLogId() {
		return extendLogId;
	}

	public void setExtendLogId(Long extendLogId) {
		this.extendLogId = extendLogId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
