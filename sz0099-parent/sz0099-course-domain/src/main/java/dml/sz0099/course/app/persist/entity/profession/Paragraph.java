/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.profession;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

/**
 * <pre>
 * @formatter:off
 *
 * 段落
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
@Table(name="s9_pro_Paragraph")
public class Paragraph extends BaseEntityExtendLong implements Serializable {

	
	private static final long serialVersionUID = 3413235088397602293L;

	@Column(name="name",columnDefinition="char(30) COMMENT '名称'")
	private String name; //名称
	
	@Column(name="title",columnDefinition="char(60) COMMENT '标题'")
	private String title;//标题
	
	@Column(name="description",columnDefinition="varchar(1024) COMMENT '描述'")
	private String description;//描述
	
	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;
	
	@Transient
	private List<PhotoParag> photoList;
	
	@Transient
	private ParagProfession product;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<PhotoParag> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<PhotoParag> photoList) {
		this.photoList = photoList;
	}

	public ParagProfession getProduct() {
		return product;
	}

	public void setProduct(ParagProfession product) {
		this.product = product;
	}

}
