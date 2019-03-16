/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.article;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.BaseEntityExtendLong;

import dml.sz0099.course.app.module.define.SZ0099AppModule;

/**
 * <pre>
 * @formatter:off
 *
 * 图片
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
@Table(name="s9_art_Photo")
public class Photo extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -200666079875365438L;
	
	public static final PropertyContext SIZETYPE=L.initProperty("尺寸类型", "选择尺寸类型", "尺寸类型", "图片尺寸分为原图、大图、中图、小图四种" , "sizeType", Photo.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext SIZETYPE_SMALL=SIZETYPE.initLabel(240, "宽240像素","240px", "小图，宽240像素");
	public static final LabelContext SIZETYPE_MIDDLE=SIZETYPE.initLabel(600, "宽600像素","60px", "中图，宽600像素");
	public static final LabelContext SIZETYPE_BIG=SIZETYPE.initLabel(1024, "宽1024像素","1024px", "大图，宽1024像素");

	@Column(name="name",columnDefinition="varchar(64) COMMENT '名称'")
	private String name; //名称
	
	@Column(name="title",columnDefinition="varchar(64) COMMENT '标题'")
	private String title;//标题
	
	@Column(name="description",columnDefinition="varchar(5120) COMMENT '描述'")
	private String description;//描述
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;
	
	@Column(name="absolute",columnDefinition="varchar(255) COMMENT '绝对路径'")
	private String absolute;
	
	@Column(name="relative",columnDefinition="varchar(255) COMMENT '相对路径'")
	private String relative;
	
	@Column(name="filename",columnDefinition="varchar(128) COMMENT '文件名'")
	private String filename;
	
	@Column(name="domain",columnDefinition="varchar(60) COMMENT '域名'")
	private String domain;//域名
	
	@Column(name="fullurl",columnDefinition="varchar(255) COMMENT '全名'")
	private String fullurl;//域名+relative
	
	@Column(name="accessUrl",columnDefinition="varchar(255) COMMENT '全名'")
	private String accessUrl;
	
	@Column(name="width", length = 11, columnDefinition = "INT(11) COMMENT '原文件宽度'")
	private Integer width;//尺寸类型  小240,中768,大1024, 原oo
	
	@Column(name="height", length = 11, columnDefinition = "INT(11) COMMENT '原文件高度'")
	private Integer height;
	
	@Column(name="size", length = 11, columnDefinition = "INT(11) COMMENT '文件大小'")
	private Long size;//大小
	
	@Column(name="suffix",columnDefinition="varchar(10) COMMENT '文件后缀'")
	private String suffix;
	
	@Column(name="contentType",columnDefinition="varchar(30) COMMENT '内容类型'")
	private String contentType;
	
	@Column(name="strategy",columnDefinition="INT(2) COMMENT '处理策略'")
	private Integer strategy;//处理策略
	
	@Column(name="viewUrl",columnDefinition="varchar(255) COMMENT 'view URL'")
	private String viewUrl;
	
	@Column(name="original", length = 128, columnDefinition = "varchar(128) COMMENT '原文件名'")
	private String original;
	
	@Transient
	private File file;
	
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


	public String getRelative() {
		return relative;
	}

	public void setRelative(String relative) {
		this.relative = relative;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getFullurl() {
		return fullurl;
	}

	public void setFullurl(String fullurl) {
		this.fullurl = fullurl;
	}


	public String getAbsolute() {
		return absolute;
	}

	public void setAbsolute(String absolute) {
		this.absolute = absolute;
	}


	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getStrategy() {
		return strategy;
	}

	public void setStrategy(Integer strategy) {
		this.strategy = strategy;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}


}
