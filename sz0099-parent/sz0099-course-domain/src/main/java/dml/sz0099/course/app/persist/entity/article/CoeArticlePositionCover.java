/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.article;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

/**
 * <pre>
 * @formatter:off
 *
 * 文章位置定义
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
@Table(name="s9_art_PositionCover")
public class CoeArticlePositionCover extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -4795277758856470669L;

	//id 存 PositionImage.id
	
	
	@Column(name="refId", length = 20, columnDefinition = "BIGINT(20) COMMENT '位置refId'")
	private Long refId;//也即refId=PositionRef.id=CoeArticlePosition.id
	
	@Column(name="photoId", length = 20, columnDefinition = "BIGINT(20) COMMENT '图片Id'")
	private Long photoId;
	
	@Column(name="fullurl",columnDefinition="varchar(255) COMMENT '图片路径全名'")
	private String fullurl;//冗余存储图片的访问路径
	
	@Column(name="width", length = 11, columnDefinition = "INT(11) COMMENT '存储最大的尺寸类型 小240,中768,大1024'")
	private Integer width;//尺寸类型  小300,中768,大1024, 原oo
	
	@Column(name="expectedUrl",columnDefinition="varchar(255) COMMENT '期望宽度，缩略图'")
	private String expectedUrl;
	
	@Column(name="mainId", length = 20, columnDefinition = "BIGINT(20) COMMENT '文章Id'")
	private Long mainId;//文章id
	
	@Column(name="ownerId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long ownerId;//所属用户，并非文章id,是指购买此位置的用户
	
	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '作者Id'")
	private Long userId;
	

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

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

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public String getFullurl() {
		return fullurl;
	}

	public void setFullurl(String fullurl) {
		this.fullurl = fullurl;
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

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	
	

}
