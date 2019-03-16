/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.media;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Table(name="s9_Imagebase")
public class Imagebase extends BaseEntityExtendLong implements Serializable{

	private static final long serialVersionUID = -8708823942719340724L;
	
	public static final char SYMBOL_SLASH = '/';//斜杠
	public static final char SYMBOL_UNDERLINE = '_';//下划线
	public static final char SYMBOL_DOT = '.';//点
	
	public static final PropertyContext STRATEGY=L.initProperty("图片处理策略", "选择图片处理策略", "图片处理策略", "图片处理策略代表每张图片应该生成哪种尺寸的子图" , "strategy", ImageExtend.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext STRATEGY_DEFAULT=STRATEGY.initLabel(1,"[300,768,1024,original]","D", "默认策略：共生成四张图[300,768,1024,original]");
	
	public static final Integer W_300 = 300;
	public static final Integer W_768 = 768;
	public static final Integer W_1024 = 1024;
	
	public static final Map<Integer,List<Integer>> STRATEGY_1 = new HashMap<>();
	public static final List<Integer> STRATEGY_1_LIST = new ArrayList<>();
	static {
		STRATEGY_1_LIST.add(W_300);
		STRATEGY_1_LIST.add(W_768);
		STRATEGY_1_LIST.add(W_1024);
		STRATEGY_1.put(1, STRATEGY_1_LIST);
	}
	
	public static final String ACCESS_TYPE_ORIGINAL="imori";//原图访问
	public static final String ACCESS_TYPE_VIEW="imview";//子图访问
	
	@Transient
	private ImageRef imageRef;//图片上传时，每张图片都对应着一个关系
	
	@Transient
	private List<ImageRef> mainRefList;
	
	@Transient
	private List<ImageRef> subRefList;
	
	@Column(name="mainRefNum", length = 11, columnDefinition = "INT(11) COMMENT '主体引用计数'")
	private Integer mainRefNum;
	@Column(name="subRefNum", length = 11, columnDefinition = "INT(11) COMMENT '子类引用计数'")
	private Integer subRefNum;

	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;
	
	@Column(name="extendId", length = 20, columnDefinition = "BIGINT(20) COMMENT '扩展信息Id'")
	private Long extendId;
	
	@Transient
	private ImageExtend extend;
	
	@Column(name="original", length = 128, columnDefinition = "varchar(128) COMMENT '原文件名'")
	private String original;
	
	@Column(name="width", length = 11, columnDefinition = "INT(11) COMMENT '宽度'")
	private Integer width;
	
	@Column(name="height", length = 11, columnDefinition = "INT(11) COMMENT '高度'")
	private Integer height;
	
	@Column(name="size", length = 11, columnDefinition = "INT(11) COMMENT '大小'")
	private Long size;
	
	@Column(name="absolute",columnDefinition="varchar(255) COMMENT '绝对路径'")
	private String absolute;
	
	@Column(name="relative",columnDefinition="varchar(255) COMMENT '相对路径'")
	private String relative;
	
	@Column(name="filename",columnDefinition="varchar(32) COMMENT '文件名'")
	private String filename;
	
	@Column(name="accessUrl",columnDefinition="varchar(255) COMMENT '访问URL'")
	private String accessUrl;
	
	@Column(name="suffix",columnDefinition="char(10) COMMENT '文件后缀'")
	private String suffix;
	
	@Column(name="contentType",columnDefinition="char(30) COMMENT '内容类型'")
	private String contentType;
	
	@Column(name="strategy",columnDefinition="INT(2) COMMENT '处理策略'")
	private Integer strategy;//处理策略
	
	
	@Column(name="viewUrl",columnDefinition="varchar(255) COMMENT 'view URL'")
	private String viewUrl;
	
	@Column(name="domain",columnDefinition="varchar(60) COMMENT '域名'")
	private String domain;
	
	@Transient
	private File file;
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAbsolute() {
		return absolute;
	}

	public void setAbsolute(String absolute) {
		this.absolute = absolute;
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

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
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

	public Long getExtendId() {
		return extendId;
	}

	public void setExtendId(Long extendId) {
		this.extendId = extendId;
	}

	public ImageExtend getExtend() {
		return extend;
	}

	public void setExtend(ImageExtend extend) {
		this.extend = extend;
	}

	public Integer getStrategy() {
		return strategy;
	}

	public void setStrategy(Integer strategy) {
		this.strategy = strategy;
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

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public List<ImageRef> getMainRefList() {
		return mainRefList;
	}

	public void setMainRefList(List<ImageRef> mainRefList) {
		this.mainRefList = mainRefList;
	}

	public List<ImageRef> getSubRefList() {
		return subRefList;
	}

	public void setSubRefList(List<ImageRef> subRefList) {
		this.subRefList = subRefList;
	}

	public Integer getMainRefNum() {
		return mainRefNum;
	}

	public void setMainRefNum(Integer mainRefNum) {
		this.mainRefNum = mainRefNum;
	}

	public Integer getSubRefNum() {
		return subRefNum;
	}

	public void setSubRefNum(Integer subRefNum) {
		this.subRefNum = subRefNum;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ImageRef getImageRef() {
		return imageRef;
	}

	public void setImageRef(ImageRef imageRef) {
		this.imageRef = imageRef;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	
}
