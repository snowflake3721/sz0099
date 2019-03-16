/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.product;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.jit4j.app.module.define.L;
import org.jit4j.app.persist.entity.module.LabelContext;
import org.jit4j.app.persist.entity.module.PropertyContext;
import org.jit4j.core.persist.entity.BaseEntityExtendLong;
import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.module.define.SZ0099AppModule;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoBanner;
import dml.sz0099.course.app.persist.entity.paragraph.PhotoCover;

/**
 * <pre>
 * @formatter:off
 *
 * 产品定义
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
@Table(name="s9_coe_Product")
public class CoeProduct extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	public static final PropertyContext STRATEGY=L.initProperty("优惠策略", "选择优惠策略", "优惠策略", "优惠策略是促成交易的有效方法，根据产品定位选择不同的优惠策略" , "strategy", CoeProduct.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext STRATEGY_0_COMMON=STRATEGY.initLabel(0, "普通","C", "普通(无优惠)");
	public static final LabelContext STRATEGY_1_SPECIAL=STRATEGY.initLabel(1, "特惠","T", "特惠版产品不享受等级折上折优惠");
	public static final LabelContext STRATEGY_2_GRADE=STRATEGY.initLabel(2, "等级","G", "等级优惠是指只有达到该等级即可享有优惠，详见等级优惠标准");
	public static final LabelContext STRATEGY_3_ALL=STRATEGY.initLabel(3, "不限","A", "不限(所有人均可享有用户等级优惠，也即不设定等级门槛)");
	public static final LabelContext STRATEGY_4_FREE=STRATEGY.initLabel(4, "免费","F", "免费，用户无需支付任何费用");
	public static final LabelContext STRATEGY_5_GRADE_FREE=STRATEGY.initLabel(5, "等级免费","GF", "等级免费，用户达到该等级，即可免费享有此资源");
	
	
	public static final PropertyContext PULL_METHOD=L.initProperty("提取方式", "选择提取方式", "提取方式", "提取方式定义用户如何取得产品，虚拟产品与实物产品各有不同的取货方式", "pullMethod", CoeProduct.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PULL_METHOD_UNKONWN=PULL_METHOD.initLabel(0, "未指定","N", "未指定，买卖双方自行协商");
	public static final LabelContext PULL_METHOD_YUN=PULL_METHOD.initLabel(10, "云盘","YUN", "云盘提取");
	public static final LabelContext PULL_METHOD_INNER=PULL_METHOD.initLabel(20, "自主下载","SD", "自主从服务器下载");
	public static final LabelContext PULL_METHOD_EMAIL=PULL_METHOD.initLabel(30, "email","E", "email提取");
	public static final LabelContext PULL_METHOD_O2O=PULL_METHOD.initLabel(40, "在线对传","OO", "在线对传");
	public static final LabelContext PULL_METHOD_F2F=PULL_METHOD.initLabel(50, "面对面","FF", "面对面提取");
	public static final LabelContext PULL_METHOD_EXP=PULL_METHOD.initLabel(60, "快递","EXP", "快递邮寄");
	
	
	public static final PropertyContext PUBLISH_STATUS=L.initProperty("发布状态", "选择发布状态", "发布状态", "产品编辑时处于草稿状态，完成后再进行发布", "publishStatus", CoeProduct.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PUBLISH_STATUS_DRAFT=PUBLISH_STATUS.initLabel(1, "草稿","D", "发布状态：草稿");
	public static final LabelContext PUBLISH_STATUS_PUBLISH=PUBLISH_STATUS.initLabel(2, "已发布","P", "发布状态：发布");
	public static final LabelContext PUBLISH_STATUS_CLOSED=PUBLISH_STATUS.initLabel(3, "已关闭","C", "发布状态：关闭");
	
	public static final PropertyContext SHELVED=L.initProperty("上架状态", "选择是否上架", "上架状态", "产品上架后可被用户搜索到", "shelved", CoeProduct.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext SHELVED_YES=SHELVED.initLabel(1, "已上架","Y", "已上架");
	public static final LabelContext SHELVED_NO=SHELVED.initLabel(0, "已下架","N", "已下架");
	
	public static Map<Integer, LabelContext> strategyMap=new HashMap<>(5);
	public static Map<Integer, String> strategySymbolMap=new HashMap<>(5);
	
	static {
		
		strategyMap.put(STRATEGY_0_COMMON.getValueInt(), STRATEGY_0_COMMON);
		strategyMap.put(STRATEGY_1_SPECIAL.getValueInt(), STRATEGY_1_SPECIAL);
		strategyMap.put(STRATEGY_2_GRADE.getValueInt(), STRATEGY_2_GRADE);
		strategyMap.put(STRATEGY_3_ALL.getValueInt(), STRATEGY_3_ALL);
		strategyMap.put(STRATEGY_4_FREE.getValueInt(), STRATEGY_4_FREE);
		
	}
	
	
	public static String getLabel(Integer value, String property) {
		return L.getLabel(value, property, CoeProduct.class.getName());
	}
	
	public static Map<Integer,LabelContext> getValues(String property){
		return L.getValues( property, CoeProduct.class.getName());
	}
	
	@Transient
	private List<CoeProductTag> proTagList;
	
	@Transient
	private List<PhotoCover> coverList;
	
	@Transient
	private List<PhotoBanner> bannerList;
	
	@Transient
	private List<CoeCategProd> categoryList;//支持绑定多个类别
	
	@Transient
	private Category categoryTree;//类别树，编辑时用于选择类别
	
	@Column(name="publishStatus",columnDefinition="int(1) COMMENT '产品发布状态，1 草稿 2发布 3关闭'")
	private Integer publishStatus;
	
	@Column(name="publishTime",columnDefinition="DATETIME COMMENT '产品发布时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date publishTime;//发布时间
	
	@Column(name="refreshTime",columnDefinition="DATETIME COMMENT '产品刷新时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date refreshTime;//刷新时间
	
	//被关闭的产品将不能上架，不能编辑，需要通过管理员审核退回为草稿状态，才能再次发布
	//被关闭的产品数量多了，将影响用户信誉，严重者封禁帐号
	@Column(name="closedTime",columnDefinition="DATETIME COMMENT '产品关闭时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date closedTime;//产品关闭时间
	
	/**
	 * 由课程分类缩略字母+aid组成
	 */
	@Column(name="productNo",columnDefinition="char(10) COMMENT '课程编号'")
	private String productNo;//课程编号
	
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '用户Id'")
	private Long userId;
	
	@Column(name="name",columnDefinition="char(40) COMMENT '名称'")
	private String name; //名称
	
	@Column(name="title",columnDefinition="varchar(60) COMMENT '标题'")
	private String title;//标题
	
	@Column(name="titleLower",columnDefinition="char(60) COMMENT '标题'")
	private String titleLower;//标题全小写
	
	@Column(name="description",columnDefinition="varchar(255) COMMENT '产品描述'")
	private String description;
	
	@Column(name="minutes",columnDefinition="int(8) COMMENT '时长,以分钟计'")
	private Integer minutes;//时长
	
	@Column(name="priceCur",columnDefinition="int(8) COMMENT '现价(分)'")
	private Integer priceCur;//现价(分)
	
	@Column(name="priceOri",columnDefinition="int(8) COMMENT '原价(分)'")
	private Integer priceOri;//原价(吊牌价)
	
	@Column(name="grade",columnDefinition="int(3) COMMENT '优惠等级'")
	private Integer grade;//享受优惠级别，低于该级别不能享受优惠策略
	
	@Column(name="rates",columnDefinition="int(4) COMMENT '最低折扣系数,会员等级拥有的折扣不会低于该值'")
	private Integer rates;//折扣系数, 产品设定最低折扣系数，会员等级拥有的折扣不会低于该值
	
	/**
	 * 优惠策略，0普通(无优惠)，1特惠（特惠版产品不享受等级折上折优惠），2等级（达到该等级即可享有优惠），3不限(所有人均可享有用户等级优惠)，4免费
	 */
	@Column(name="strategy",columnDefinition="int(2) COMMENT '优惠策略:0普通(无优惠)，1特惠（特惠版产品不享受等级折上折优惠），2等级（达到该等级即可享有优惠），3不限(所有人均可享有用户等级优惠)，4免费'")
	private Integer strategy;
	
	@Column(name="pullMethod",columnDefinition="int(2) COMMENT '产品提取方式，云盘：10，服务器：20， email：30， 在线对传：40'")
	private Integer pullMethod=0;//产品提取方式 0未指定， 云盘：10，服务器：20， email：30， 在线对传：40
	
	@Column(name="originalLink",columnDefinition="varchar(255) COMMENT '产品原链接'")
	private String originalLink; //产品原链接
	
	@Column(name="link",columnDefinition="varchar(255) COMMENT '产品下载链接'")
	private String link;
	
	@Column(name="pullCode",columnDefinition="char(10) COMMENT '提取验证码'")
	private String pullCode;//提取验证码
	
	@Column(name="shelved",columnDefinition="int(1) COMMENT '未上架：0，已上架：1'")
	private Integer shelved;//是否上架
	
	@Column(name="shelvedTime",columnDefinition="DATETIME COMMENT '产品上下架时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date shelvedTime;//上下架时间
	
	
	
	
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the shelved
	 */
	public Integer getShelved() {
		return shelved;
	}

	/**
	 * @param shelved the shelved to set
	 */
	public void setShelved(Integer shelved) {
		this.shelved = shelved;
	}


	/**
	 * @return the priceCur
	 */
	public Integer getPriceCur() {
		return priceCur;
	}

	/**
	 * @param priceCur the priceCur to set
	 */
	public void setPriceCur(Integer priceCur) {
		this.priceCur = priceCur;
	}

	/**
	 * @return the priceOri
	 */
	public Integer getPriceOri() {
		return priceOri;
	}

	/**
	 * @param priceOri the priceOri to set
	 */
	public void setPriceOri(Integer priceOri) {
		this.priceOri = priceOri;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the minutes
	 */
	public Integer getMinutes() {
		return minutes;
	}

	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	/**
	 * @return the titleLower
	 */
	public String getTitleLower() {
		return titleLower;
	}

	/**
	 * @param titleLower the titleLower to set
	 */
	public void setTitleLower(String titleLower) {
		this.titleLower = titleLower;
	}


	/**
	 * @return the productNo
	 */
	public String getProductNo() {
		return productNo;
	}

	/**
	 * @param productNo the productNo to set
	 */
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}


	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getStrategy() {
		return strategy;
	}

	public void setStrategy(Integer strategy) {
		this.strategy = strategy;
	}

	public Integer getPullMethod() {
		return pullMethod;
	}

	public void setPullMethod(Integer pullMethod) {
		this.pullMethod = pullMethod;
	}

	public Integer getRates() {
		return rates;
	}

	public void setRates(Integer rates) {
		this.rates = rates;
	}

	public String getPullCode() {
		return pullCode;
	}

	public void setPullCode(String pullCode) {
		this.pullCode = pullCode;
	}

	public String getOriginalLink() {
		return originalLink;
	}

	public void setOriginalLink(String originalLink) {
		this.originalLink = originalLink;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getShelvedTime() {
		return shelvedTime;
	}

	public void setShelvedTime(Date shelvedTime) {
		this.shelvedTime = shelvedTime;
	}

	public Date getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}

	public Date getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(Date closedTime) {
		this.closedTime = closedTime;
	}

	public List<CoeProductTag> getProTagList() {
		return proTagList;
	}

	public void setProTagList(List<CoeProductTag> proTagList) {
		this.proTagList = proTagList;
	}

	public List<PhotoCover> getCoverList() {
		return coverList;
	}

	public void setCoverList(List<PhotoCover> coverList) {
		this.coverList = coverList;
	}

	public List<PhotoBanner> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<PhotoBanner> bannerList) {
		this.bannerList = bannerList;
	}

	public List<CoeCategProd> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CoeCategProd> categoryList) {
		this.categoryList = categoryList;
	}

	public Category getCategoryTree() {
		return categoryTree;
	}

	public void setCategoryTree(Category categoryTree) {
		this.categoryTree = categoryTree;
	}

}
