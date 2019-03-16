/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.BaseEntityExtendLong;
import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.article.CoeArticle;

/**
 * <pre>
 * @formatter:off
 *
 * 推广用户绑定
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
@Table(name="s9_Sayword")
public class Sayword extends BaseEntityExtendLong implements Serializable {
	
	private static final long serialVersionUID = -559454922257655036L;
	
	public static final PropertyContext STATUS=L.initProperty("有效状态", "设置有效状态", "有效状态", "有效状态，传说一般为审核通过，由管理员可强行禁止生效" , "status", Sayword.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext STATUS_0_INIT=STATUS.initLabel(0, "初始化","I", "初始化");
	public static final LabelContext STATUS_1_WAIT_VERIFY=STATUS.initLabel(1, "待审核","W", "待审核");
	public static final LabelContext STATUS_2_PASS=STATUS.initLabel(2, "有效","P", "审核通过");
	public static final LabelContext STATUS_3_EXPIRED=STATUS.initLabel(3, "已过期","E", "已过期");
	public static final LabelContext STATUS_4_CLOSED=STATUS.initLabel(4, "已关闭","C", "已关闭");
	
	public static final PropertyContext MAINTYPE=L.initProperty("传说输出类型", "选择传说输出类型", "传说输出类型", "传说输出类型，一个用户只能有一个当前传说，有且必须有且仅有一个当前传说", "mainType", Sayword.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext MAINTYPE_0_NORMAL=MAINTYPE.initLabel(0, "历史传说","N", "历史传说");
	public static final LabelContext MAINTYPE_9_MAIN=MAINTYPE.initLabel(9, "当前传说","M", "当前传说");

	
	@Column(name="userId",columnDefinition="BIGINT(20) COMMENT '用户Id'")
	private Long userId;//用户id
	
	
	@Column(name="status",columnDefinition="INT(3) COMMENT '状态'")
	private Integer status;//可禁止显示
	
	@Column(name="publishTime",columnDefinition="DATETIME COMMENT '传说发布时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date publishTime;//传说发布时间
	
	@Column(length = 3, columnDefinition = "INT(3) COMMENT '文章输出类型'")
	private Integer mainType;//主要输出类型：9为当前传说，0为历史传说，其他数字待定，通常一个用户只能有一篇为当前传说
	
	@Column(name="description",length=64,columnDefinition="varchar(512) COMMENT '一句传说'")
	private String description;//不能太长，简短为主,内容
	
	@Column(name="remark",length=64,columnDefinition="varchar(512) COMMENT '一句传说'")
	private String remark;//传说违规关闭时，输入备注
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getMainType() {
		return mainType;
	}

	public void setMainType(Integer mainType) {
		this.mainType = mainType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



}
