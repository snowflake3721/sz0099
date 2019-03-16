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
@Table(name="s9_PhotoParag")
public class PhotoParag extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -6536620488659080007L;

	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;
	
	@Column(name="photoId", length = 20, columnDefinition = "BIGINT(20) COMMENT 'photoId'")
	private Long photoId;
	
	@Transient
	private Photo photo;
	
	@Column(name="paragId", length = 20, columnDefinition = "BIGINT(20) COMMENT 'photoId'")
	private Long paragId;
	
	@Transient
	private Paragraph parag;
	
	@Column(name="fullurl",columnDefinition="varchar(255) COMMENT '全名'")
	private String fullurl;//冗余存储图片的访问路径
	
	@Column(name="width", length = 11, columnDefinition = "INT(11) COMMENT '存储最大的尺寸类型 小240,中768,大1024'")
	private Integer width;//尺寸类型  小300,中768,大1024, 原oo
	
	@Column(name="expectedUrl",columnDefinition="varchar(255) COMMENT '期望宽度，缩略图'")
	private String expectedUrl;
	
	@Column(name="name",columnDefinition="char(60) COMMENT '名称'")
	private String name; //名称
	
	@Column(name="title",columnDefinition="char(60) COMMENT '标题'")
	private String title;//标题

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public Long getParagId() {
		return paragId;
	}

	public void setParagId(Long paragId) {
		this.paragId = paragId;
	}

	public String getFullurl() {
		return fullurl;
	}

	public void setFullurl(String fullurl) {
		this.fullurl = fullurl;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Paragraph getParag() {
		return parag;
	}

	public void setParag(Paragraph parag) {
		this.parag = parag;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getExpectedUrl() {
		return expectedUrl;
	}

	public void setExpectedUrl(String expectedUrl) {
		this.expectedUrl = expectedUrl;
	}

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

}
