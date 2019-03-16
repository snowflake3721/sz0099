/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.category;

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
 * 产品类别
 * 产品分级最多四级
 * 顶级为项目级：项目级以下为三级，如 课程项目，其下可分：IT>后端>java
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
@Table(name="s9_Category")
public class Category extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	public static final String CODE_ARTICLE_PROFESSION="article_profession";//技能文章关联类别
	public static final String CODE_SPECIAL_REMEMBER="special_remember";//独家记忆类别
	public static final String CODE_ARTICLE_SHARED="article_shared";//记实类别
	public static final String CODE_ARTICLE_SHARED_FOOT="article_shared_foot";//徒步
	public static final String CODE_ARTICLE_SHARED_BIKE="article_shared_bike";//骑行
	public static final String CODE_ARTICLE_SHARED_TRAVEL="article_shared_travel";//旅行
	public static final String CODE_ARTICLE_SHARED_FREE="article_shared_free";//自由行
	public static final String CODE_ARTICLE_SHARED_WELFARE="article_shared_welfare";//公益
	
	public static final String CODE_ARTICLE_FLAG="article_flag";//旗帜飘扬类别
	public static final String CODE_ARTICLE_FLAG_QX="article_flag_qx";//旗帜:群侠户外
	public static final String CODE_ARTICLE_FLAG_XF="article_flag_xf";//旗帜:雪峰户外
	public static final String CODE_ARTICLE_FLAG_ACTION="article_flag_action";//旗帜:插旗行动
	
	public static final String CODE_ARTICLE_ACTION="article_action";//行动
	
	
	public static final String CODE_ROADLINE="article_roadline";//线路
	public static final String CODE_ROADLINE_EXPLORER="article_roadline_explorer";//探路行动
	public static final String CODE_ROADLINE_GUIDE="article_roadline_guide";//攻略
	
	public static final String CODE_ARTICLE_EAT="article_eat";//吃货聚焦
	public static final String CODE_ARTICLE_EAT_FARMSTAY="article_eat_farmstay";//农家乐
	public static final String CODE_ARTICLE_EAT_TOGETHOR="article_eat_togethor";//合家欢
	public static final String CODE_ARTICLE_EAT_OUTDOOR="article_eat_outdoor";//野餐团
	
	public static final String CODE_ARTICLE_EQUIP="artice_equip";//装备篇
	public static final String CODE_ARTICLE_EQUIP_REAL="artice_equip_real";//装备-实战检验
	public static final String CODE_ARTICLE_EQUIP_POPULAR="articel_equip_popular";//装备-大众流行
	
	
	public static final String CODE_PROFESSION_MAJOR="profession_major";//专业技 //对应八仙过海 以及推荐
	public static final String CODE_PROFESSION_MAJOR_360="profession_major_360";//专业技-360行
	public static final String CODE_PROFESSION_MAJOR_ARTISAN ="profession_major_artisan";//专业技-能工巧匠
	public static final String CODE_PROFESSION_MAJOR_INGENUITY="profession_major_ingenuity";//专业技-独具匠心 对应 匠心
	public static final String CODE_PROFESSION_MAJOR_TECH="profession_major_tech";//专业技-技术
	public static final String CODE_PROFESSION_MAJOR_FACTORY="profession_major_factory";//直供
	
	public static final String CODE_PROFESSION_OUTDOOR="profession_outdoor";//出行技
	public static final String CODE_PROFESSION_OUTDOOR_NAVIGATOR="profession_outdoor_navigator";//户外技-领队与向导
	public static final String CODE_PROFESSION_OUTDOOR_ENCAMPMENT="profession_outdoor_encampment";//户外技-住宿与野营
	public static final String CODE_PROFESSION_OUTDOOR_TRAFFIC="profession_outdoor_traffic";//户外技-交通
	public static final String CODE_PROFESSION_OUTDOOR_EQUIPMENT="profession_outdoor_equipment";//户外技-装备
	
	public static final String CODE_PROFESSION_LIFE="profession_life";//居家生活与服务
	
	public static final String CODE_PROFESSION_RELAX="profession_relax";//乐趣
	public static final String CODE_PROFESSION_RELAX_PHOTOGRAPH="profession_relax_photograph";//娱乐技-摄影
	public static final String CODE_PROFESSION_RELAX_STORY="profession_relax_story";//娱乐技-创作
	public static final String CODE_PROFESSION_RELAX_OUTDOOR="profession_relax_outdoor";//娱乐技-户外
	public static final String CODE_PROFESSION_RELAX_DINNER="profession_relax_dinner";//娱乐技-聚餐
	public static final String CODE_PROFESSION_RELAX_TATTLE="profession_relax_tattle";//娱乐技-其他
	
	public static final String CODE_PROFESSION_NATIVE="profession_native";//特产技
	public static final String CODE_PROFESSION_NATIVE_PHOTOGRAPH="profession_native_pick";//特产技-采摘
	public static final String CODE_PROFESSION_NATIVE_STORY="profession_native_delicacy";//特产技-美食小吃
	public static final String CODE_PROFESSION_NATIVE_TATTLE="profession_native_drink";//特产技-喝的文化
	public static final String CODE_PROFESSION_NATIVE_FARMMY="profession_native_hometown";//特产技-家乡
	
	
	public static final String CODE_PROFESSION_KILL="profession_kill";//必杀技
	//public static final String CODE_PROFESSION_KILL_MULTI="profession_kill_multi";//必杀技-连招KO
	//public static final String CODE_PROFESSION_KILL_ONE="profession_kill_one";//必杀技-一技必杀
	//public static final String CODE_PROFESSION_KILL_UNIQUE="profession_kill_unique";//必杀技-大绝招
	
	
	public static final String CODE_PROFESSION_RECOMMEND="profession_recommend";//推荐技
	public static final String CODE_PROFESSION_TODAY="profession_today";//今日神技
	
	public static final String CODE_ACTIVITY_JOIN="activity_join";//集体活动
	public static final String CODE_ACTIVITY_JOIN_RECOMMEND="activity_join_recommend";//活动推荐
	public static final String CODE_ACTIVITY_JOIN_FLAG="activity_join_flag";//活动队旗
	public static final String CODE_ACTIVITY_JOIN_FOOT="activity_join_foot";//活动徒步
	public static final String CODE_ACTIVITY_JOIN_BIKE="activity_join_bike";//活动骑行
	public static final String CODE_ACTIVITY_JOIN_TRAVEL="activity_join_travel";//活动旅行
	public static final String CODE_ACTIVITY_JOIN_CAR="activity_join_car";//活动自驾
	public static final String CODE_ACTIVITY_JOIN_EXPLORE="activity_join_explore";//活动探索
	public static final String CODE_ACTIVITY_JOIN_FREE="activity_join_free";//自由行
	public static final String CODE_ACTIVITY_JOIN_VOLUNTEER="activity_join_volunteer";//志愿者
	
	public static final String CODE_ACTIVITY_JOIN_WELFARE="activity_join_welfare";//公益
	public static final String CODE_ACTIVITY_JOIN_EAT="activity_join_eat";//聚餐
	public static final String CODE_ACTIVITY_JOIN_PICK="activity_join_pick";//采摘
	public static final String CODE_ACTIVITY_JOIN_OTHER="activity_join_other";//其他
	
	
	@Transient
	public List<CategoryRef> categoryRefs;
	
	@Column(name="name",columnDefinition="char(32) COMMENT '名称'")
	private String name; //名称
	
	@Column(name="code",columnDefinition="char(32) COMMENT '代码,唯一'")
	private String code;//代码
	
	@Column(name="ad",columnDefinition="char(6) COMMENT '编号前缀'")
	private String ad;//缩写，取code的两至三位
	
	@Column(name="countNum",columnDefinition="int(6) COMMENT '类别计数'")
	private Integer countNum;//计数
	
	@Column(name="parentId" ,length = 20, columnDefinition = "BIGINT(20) COMMENT '父类Id'")
	private Long parentId;
	
	@Transient
	private Category parent;
	
	@Transient
	private List<Category> children;
	
	@Column(name="extendId", length = 20, columnDefinition = "BIGINT(20) COMMENT '扩展信息Id'")
	private Long extendId;
	
	@Column(name="positionId", length = 20, columnDefinition = "BIGINT(20) COMMENT '扩展信息positionId'")
	private Long positionId;
	
	@Column(name="mainId", length = 20, columnDefinition = "BIGINT(20) COMMENT '圈子id, 系统id'")
	private Long mainId;//圈子id, 平台id(platId)
	
	@Column(name="subId", length = 20, columnDefinition = "BIGINT(20) COMMENT 'subId'")
	private Long subId;//可以是行业id
	
	@Column(name="userId", length = 20, columnDefinition = "BIGINT(20) COMMENT '用户id'")
	private Long userId;//用户id
	
	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
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

	public List<CategoryRef> getCategoryRefs() {
		return categoryRefs;
	}

	public void setCategoryRefs(List<CategoryRef> categoryRefs) {
		this.categoryRefs = categoryRefs;
	}



}
