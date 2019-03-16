/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.media;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.BaseEntityExtendLong;

import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;

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
@Table(name="s9_ImageExtend")
public class ImageExtend extends BaseEntityExtendLong implements Serializable{

	private static final long serialVersionUID = 1073035860061247207L;

	public static final PropertyContext MAINMAXNUM=L.initProperty("主体最大图片数量", "选择最大图片数量", "主体最大图片数量", "每一个主体所拥有的最大图片数量" , "mainMaxnum", ImageExtend.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext MAINMAXNUM_100=MAINMAXNUM.initLabel(1,String.valueOf(100), "100","D", "默认",LabelContext.VALUETYPE_INTEGER,"§");
	
	public static final PropertyContext SUBMAXNUM=L.initProperty("子类最大图片数量", "选择最大图片数量", "子类最大图片数量", "每一个子类所拥有的最大图片数量" , "subMaxnum", ImageExtend.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext SUBMAXNUM_5=SUBMAXNUM.initLabel(1, String.valueOf(5),"5","D", "默认",LabelContext.VALUETYPE_INTEGER,"§");
	
	public static final PropertyContext SIZEMAX=L.initProperty("每张图片的最大尺寸", "选择图片的最大尺寸", "每张图片的最大尺寸", "每张图片的最大尺寸" , "sizeMax", ImageExtend.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext SIZEMAX_10M=SIZEMAX.initLabel(1,String.valueOf(1024*1024*10), "10M","10M", "默认",LabelContext.VALUETYPE_INTEGER,"§");
	
	
	@Transient
	List<Imagebase> images;//数量庞大，要分页查询
	
	@Column(name="domain",columnDefinition="varchar(60) COMMENT '域名'")
	private String domain;
	
	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;//位置申请者用户id
	
	@Column(name="devId", length = 10, columnDefinition = "char(10) COMMENT '开发者id'")
	private String devId;//开发者id
	
	@Column(name="project",columnDefinition="char(32) COMMENT '项目名称'")
	private String project;//所属项目
	
	@Column(name="module",columnDefinition="char(32) COMMENT '所属模块'")
	private String module;//所属模块
	
	@Column(name="variety",columnDefinition="char(32) COMMENT '所属类别'")
	private String variety;//所属品种，如：文章、产品
	
	@Column(name="position",columnDefinition="char(32) COMMENT '位置'")
	private String position;//所属位置，如：头部，段落内容，尾部，封面
	
	@Column(name="positionId",columnDefinition="BIGINT(20) COMMENT '该id颁发给调用者'")
	private Long positionId;//位置id,用户申请之后产生此id,后续用户可根据此id来上传图片
	
	@Column(name="mainMaxnum",columnDefinition="INT(6) COMMENT '主类最大数量'")
	private Integer mainMaxnum;//如 主类为产品，一个产品允许的最大图片数在此定义
	
	@Column(name="subMaxnum",columnDefinition="INT(6) COMMENT '子类最大数量'")
	private Integer subMaxnum;//如 子类为段落（一个产品内容由多个段落组成），定义每个段落的最大图片数
	
	@Column(name="sizeMax",columnDefinition="INT(11) COMMENT '每个文件大小限制'")
	private Long sizeMax;//
	
	@Transient
	private List<ImageRef> urlList;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public List<Imagebase> getImages() {
		return images;
	}

	public void setImages(List<Imagebase> images) {
		this.images = images;
	}

	public Integer getMainMaxnum() {
		return mainMaxnum;
	}

	public void setMainMaxnum(Integer mainMaxnum) {
		this.mainMaxnum = mainMaxnum;
	}

	public Integer getSubMaxnum() {
		return subMaxnum;
	}

	public void setSubMaxnum(Integer subMaxnum) {
		this.subMaxnum = subMaxnum;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public Long getSizeMax() {
		return sizeMax;
	}

	public void setSizeMax(Long sizeMax) {
		this.sizeMax = sizeMax;
	}

	public List<ImageRef> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<ImageRef> urlList) {
		this.urlList = urlList;
	}


	
}
