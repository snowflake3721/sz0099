/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.article;

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
 * 封面图
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
@Table(name="s9_art_PhotoCover")
public class PhotoCover extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -6536620488659080007L;
	
	public static final Long SUBID_COVER_HEAD=dml.sz0099.course.app.persist.entity.paragraph.PhotoCover.SUBID_COVER_HEAD;//对应图片位置为封面首
	public static final Long SUBID_COVER_FOOT=dml.sz0099.course.app.persist.entity.paragraph.PhotoCover.SUBID_COVER_FOOT;//对应图片位置为封面尾

	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;
	
	@Column(name="photoId", length = 20, columnDefinition = "BIGINT(20) COMMENT 'photoId'")
	private Long photoId;
	
	@Transient
	private Photo photo;
	
	@Column(name="subId", length = 20, columnDefinition = "BIGINT(20) COMMENT 'subId'")
	private Long subId;//可存多个位置
	
	@Column(name="mainId",length=20,columnDefinition="BIGINT(20) COMMENT 'mainId=CoeArticle.Id'")
	private Long mainId;
	
	@Column(name="type", length = 1, columnDefinition = "INT(1) COMMENT '段落类型'")
	private Integer type;//段落类型
	
	@Column(name="fullurl",columnDefinition="varchar(255) COMMENT '全名'")
	private String fullurl;//冗余存储图片的访问路径
	
	@Column(name="width", length = 11, columnDefinition = "INT(11) COMMENT '存储最大的尺寸类型 小240,中768,大1024'")
	private Integer width;//尺寸类型  小300,中768,大1024, 原oo
	
	@Column(name="expectedW", length = 11, columnDefinition = "INT(11) COMMENT '期望宽度'")
	private Integer expectedW;
	
	@Column(name="expectedUrl",columnDefinition="varchar(255) COMMENT '期望宽度，缩略图'")
	private String expectedUrl;
	
	@Column(name="title",columnDefinition="varchar(64) COMMENT '标题'")
	private String title;//标题
	
	@Column(name="description",columnDefinition="varchar(5120) COMMENT '描述'")
	private String description;//描述

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getExpectedW() {
		return expectedW;
	}

	public void setExpectedW(Integer expectedW) {
		this.expectedW = expectedW;
	}

}
