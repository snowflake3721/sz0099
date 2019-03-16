/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.media;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.BaseEntityExtendLong;
import org.springframework.web.multipart.MultipartFile;

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
@Table(name="s9_ImageRef")
public class ImageRef extends BaseEntityExtendLong implements Serializable{

	private static final long serialVersionUID = -8708823942719340724L;
	
	public static final PropertyContext TYPE=L.initProperty("关联类型", "选择关联类型", "关联类型", "关联类型，图片，文字", "type", ImageRef.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext TYPE_IMG=TYPE.initLabel(0, "图片","image", "图片");
	public static final LabelContext TYPE_TEXT=TYPE.initLabel(1, "文字","text", "文字");
	
	
	@Transient
	private MultipartFile file;
	
	@Transient
	private Imagebase base;

	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;
	
	@Column(name="extendId", length = 20, columnDefinition = "BIGINT(20) COMMENT '扩展信息Id'")
	private Long extendId;
	
	@Column(name="baseId", length = 20, columnDefinition = "BIGINT(20) COMMENT '基本信息Id'")
	private Long baseId;
	
	@Transient
	private ImageExtend extend;
	
	@Column(name="mainId",columnDefinition="BIGINT(20) COMMENT '主类id'")
	private Long mainId;
	
	@Column(name="subId",columnDefinition="BIGINT(20) COMMENT '子类id'")
	private Long subId;
	
	@Column(name="expectedUrl",columnDefinition="varchar(255) COMMENT '期望宽度view URL'")
	private String expectedUrl;
	
	@Column(name="expectedW", length = 11, columnDefinition = "INT(11) COMMENT '期望宽度'")
	private Integer expectedW;
	
	@Column(name="width", length = 11, columnDefinition = "INT(11) COMMENT '实际宽度'")
	private Integer width;
	
	@Column(name="height", length = 11, columnDefinition = "INT(11) COMMENT '实际高度'")
	private Integer height;
	
	@Column(name="viewUrl",columnDefinition="varchar(255) COMMENT 'view URL'")
	private String viewUrl;
	
	@Column(name="name",columnDefinition="char(60) COMMENT '名称'")
	private String name; //名称
	
	@Column(name="title",columnDefinition="char(60) COMMENT '标题'")
	private String title;//标题
	
	@Column(name="description",columnDefinition="varchar(5120) COMMENT '描述'")
	private String description;//描述
	
	@Column(name="type", length = 1, columnDefinition = "INT(1) COMMENT '段落类型'")
	private Integer type;//段落类型
	
	@Column(name="template", length = 3, columnDefinition = "INT(3) COMMENT '标记为模版'")
	private Integer template;//标记为模版，取CoeActivity.template
	

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

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public String getExpectedUrl() {
		return expectedUrl;
	}

	public void setExpectedUrl(String expectedUrl) {
		this.expectedUrl = expectedUrl;
	}

	public Integer getExpectedW() {
		return expectedW;
	}

	public void setExpectedW(Integer expectedW) {
		this.expectedW = expectedW;
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

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Imagebase getBase() {
		return base;
	}

	public void setBase(Imagebase base) {
		this.base = base;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTemplate() {
		return template;
	}

	public void setTemplate(Integer template) {
		this.template = template;
	}


	
}
