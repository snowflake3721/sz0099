/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.chan;

import java.io.Serializable;
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
import dml.sz0099.course.app.persist.entity.category.Category;

/**
 * <pre>
 * @formatter:off
 *
 * 频道与类别绑定
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
@Table(name="s9_chan_ChanRef")
public class ChanRef extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -4795277758856470669L;
	
	public static final PropertyContext STATUS=L.initProperty("关联状态", "选择关联状态", "关联状态", "关联状态，开启时会展现", "status", ChanRef.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext STATUS_0_INIT=STATUS.initLabel(0, "待审","Init", "关联状态：待审");
	public static final LabelContext STATUS_1_OPEN=STATUS.initLabel(1, "开启","Open", "关联状态：开启");
	public static final LabelContext STATUS_2_CLOSED=STATUS.initLabel(2, "关闭","Close", "关联状态：关闭");
	
	public static final PropertyContext VIEWTYPE=L.initProperty("查看类型", "选择查看类型", "查看类型", "查看类型，选择查看类型", "viewType", ChanRef.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext VIEWTYPE_0_DEFAULT=VIEWTYPE.initLabel(0, "默认","default", "默认");//默认
	public static final LabelContext VIEWTYPE_1_ARTICLE=VIEWTYPE.initLabel(1, "文章","article", "文章");//文章
	public static final LabelContext VIEWTYPE_2_PRODUCT=VIEWTYPE.initLabel(2, "产品","product", "产品");//产品
	public static final LabelContext VIEWTYPE_3_PROFESSION=VIEWTYPE.initLabel(3, "技能","profession", "技能");//技能

	public static final Integer TOP_LEVEL_1=1;
	
	public static String getLabel(Integer value, String property) {
		return L.getLabel(value, property, ChanRef.class.getName());
	}
	
	public static Map<Integer,LabelContext> getValues(String property){
		return L.getValues( property, ChanRef.class.getName());
	}
	
	@Transient
	private Category category;//类别
	
	@Column(name="viewType",columnDefinition="int(3) COMMENT '关联类型'")
	private Integer viewType;
	
	@Transient
	private Chan chan;
	
	@Column(name="baseId", length = 20, columnDefinition = "BIGINT(20) COMMENT '基本信息Id,即Chan.id'")
	private Long baseId;
	
	@Column(name="mainId", length = 20, columnDefinition = "BIGINT(20) COMMENT '主体Id'")
	private Long mainId;//类别id
	
	@Column(name="extendId", length = 20, columnDefinition = "BIGINT(20) COMMENT '扩展信息Id'")
	private Long extendId;
	
	@Column(name="positionId", length = 20, columnDefinition = "BIGINT(20) COMMENT '扩展信息positionId'")
	private Long positionId;//ChanExtend.positionId
	
	@Column(name="status",columnDefinition="int(1) COMMENT '频道状态，0待审 1 开启 2关闭'")
	private Integer status;
	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getViewType() {
		return viewType;
	}

	public void setViewType(Integer viewType) {
		this.viewType = viewType;
	}

	public Chan getChan() {
		return chan;
	}

	public void setChan(Chan chan) {
		this.chan = chan;
	}

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public Long getExtendId() {
		return extendId;
	}

	public void setExtendId(Long extendId) {
		this.extendId = extendId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

}
