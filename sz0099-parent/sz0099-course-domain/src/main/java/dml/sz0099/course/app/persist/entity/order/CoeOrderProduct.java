/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * <pre>
 * @formatter:off
 *
 * 订单快递
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
@Table(name="s9_coe_OrderProduct")
public class CoeOrderProduct extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = 5469255988936067756L;
	
	@Basic(fetch=FetchType.LAZY)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orderId",insertable=false,updatable=false,referencedColumnName="id")
	private CoeOrder coeOrder;
	
	@Transient
	private CoeProduct coeProduct;
	
	
	@Column(name="flowNo",columnDefinition="char(10) COMMENT '订单流水号'")
	private String flowNo;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '订单Id'")
	private Long orderId;//关联订单id, 与order一对一
	
	@Column(name="productId",length = 20, columnDefinition = "BIGINT(20) COMMENT '产品Id'")
	private Long productId;//关联产品id
	
	@Column(name="productNo",columnDefinition="char(10) COMMENT '产品编号'")
	private String productNo;//产品编号
	
	@Column(name="ownerId",columnDefinition="BIGINT(20) COMMENT '归属客服:CoeProduct.createdBy'")
	private Long ownerId;//订单拥有者：即产品发布者id(归属客服)
	
	@Column(name="userId",columnDefinition="BIGINT(20) COMMENT '用户Id'")
	private Long userId;//用户id
	
	@Column(name="orderTime",columnDefinition="DATETIME COMMENT '下单时间'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderTime;//下单时间
	
	@Column(name="name",columnDefinition="char(40) COMMENT '名称'")
	private String name; //名称
	
	@Column(name="title",columnDefinition="char(60) COMMENT '标题'")
	private String title;//标题
	
	@Column(name="titleLower",columnDefinition="char(60) COMMENT '标题小写'")
	private String titleLower;//标题全小写
	
	@Column(name="priceCur",columnDefinition="int(8) COMMENT '现价(分)'")
	private Integer priceCur;//现价(分)
	
	@Column(name="priceOri",columnDefinition="int(8) COMMENT '原价(分)'")
	private Integer priceOri;//原价
	
	@Column(name="link",columnDefinition="varchar(255) COMMENT '产品下载链接'")
	private String link; //产品下载链接
	
	@Column(name="pullMethod",columnDefinition="int(2) COMMENT '产品提取方式'")
	private Integer pullMethod;//产品提取方式  云盘提取：10，内部服务器提取：20， email提取：30， 在线对传提取：40
	
	@Column(name="remark",columnDefinition="varchar(64) COMMENT '订单产品备注'")
	private String remark;//产品下单时备注
	
	@Column(name="pricePay",columnDefinition="int(8) COMMENT '支付价(分)'")
	private Integer pricePay;//支付价格
	
	@Column(name="pullCode",columnDefinition="char(10) COMMENT '提取验证码'")
	private String pullCode;//提取验证码
	

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
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

	public String getTitleLower() {
		return titleLower;
	}

	public void setTitleLower(String titleLower) {
		this.titleLower = titleLower;
	}

	public Integer getPriceCur() {
		return priceCur;
	}

	public void setPriceCur(Integer priceCur) {
		this.priceCur = priceCur;
	}

	public Integer getPriceOri() {
		return priceOri;
	}

	public void setPriceOri(Integer priceOri) {
		this.priceOri = priceOri;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getPullMethod() {
		return pullMethod;
	}

	public void setPullMethod(Integer pullMethod) {
		this.pullMethod = pullMethod;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPricePay() {
		return pricePay;
	}

	public void setPricePay(Integer pricePay) {
		this.pricePay = pricePay;
	}

	public String getPullCode() {
		return pullCode;
	}

	public void setPullCode(String pullCode) {
		this.pullCode = pullCode;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public CoeOrder getCoeOrder() {
		return coeOrder;
	}

	public void setCoeOrder(CoeOrder coeOrder) {
		this.coeOrder = coeOrder;
	}

	public CoeProduct getCoeProduct() {
		return coeProduct;
	}

	public void setCoeProduct(CoeProduct coeProduct) {
		this.coeProduct = coeProduct;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}


}
