/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.order;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

/**
 * <pre>
 * @formatter:off
 *
 * 订单中 产品日志记录
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
@Table(name="s9_coe_OrderProdLog")
public class CoeOrderProdLog extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '产品Id'")
	private Long productId;//关联产品id
	
	@Column(name="productNo",columnDefinition="char(10) COMMENT '产品编号'")
	private String productNo;//产品编号
	
	@Column(name="orderId",columnDefinition="BIGINT(20) COMMENT '订单id'")
	private Long orderId;//关联订单id
	
	@Column(name="name",columnDefinition="char(40) COMMENT '名称'")
	private String name; //名称
	
	@Column(name="title",columnDefinition="char(60) COMMENT '标题'")
	private String title;//标题
	
	@Column(name="description",columnDefinition="varchar(255) COMMENT '产品描述'")
	private String description;
	
	@Column(name="minutes",columnDefinition="int(8) COMMENT '时长,以分钟计'")
	private Integer minutes;//时长
	
	@Column(name="grade",columnDefinition="int(2) COMMENT '优惠等级'")
	private Integer grade;//享受优惠级别，低于该级别不能享受优惠策略
	
	/**
	 * 优惠策略，0普通(无优惠)，1特惠（特惠版产品不享受等级折上折优惠），2等级（达到该等级即可享有优惠），3不限(所有人均可享有用户等级优惠)
	 */
	@Column(name="strategy",columnDefinition="int(2) COMMENT '优惠策略'")
	private Integer strategy;
	
	@Column(name="priceCur",columnDefinition="int(8) COMMENT '现价(分)'")
	private Integer priceCur;//现价(分)
	
	@Column(name="priceOri",columnDefinition="int(8) COMMENT '原价(分)'")
	private Integer priceOri;//原价
	
	@Column(name="link",columnDefinition="varchar(255) COMMENT '产品下载链接'")
	private String link; //产品下载链接
	
	@Column(name="pullMethod",columnDefinition="int(2) COMMENT '产品提取方式'")
	private Integer pullMethod;//产品提取方式  云盘提取：10，内部服务器提取：20， email提取：30， 在线对传提取：40
	

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
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
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
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	 * @return the name
	 */
	public String getName() {
		return name;
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

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public Integer getPullMethod() {
		return pullMethod;
	}

	public void setPullMethod(Integer pullMethod) {
		this.pullMethod = pullMethod;
	}


}
