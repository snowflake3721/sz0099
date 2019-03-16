/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.activity;

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
 * 活动时间
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
@Table(name="s9_act_ActivityFee")
public class CoeActivityFee extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	public static final PropertyContext CURRENCY=L.initProperty("货币支付类型", "支付类型", "支付类型", "支付类型，铜钱支付，RMB支付，二者均可", "currency", CoeActivityFee.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext CURRENCY_RMB=CURRENCY.initLabel(0, "RMB","RMB", "支付类型：人民币");
	public static final LabelContext CURRENCY_COIN=CURRENCY.initLabel(1, "铜钱-暂不支持","COIN", "支付类型：铜钱");
	public static final LabelContext CURRENCY_CASH=CURRENCY.initLabel(2, "余额-暂不支持","CASH", "支付类型：余额");
	public static final LabelContext CURRENCY_ALL=CURRENCY.initLabel(3, "均可-暂不支持","ALL", "支付类型：均可");
	
	public static final PropertyContext FEETYPE=L.initProperty("分摊类型", "分摊类型", "分摊类型", "分摊类型，AA，自助，免费", "feeType", CoeActivityFee.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext FEETYPE_AA=FEETYPE.initLabel(0, "AA制","AA", "分摊类型：AA");
	public static final LabelContext FEETYPE_SELF=FEETYPE.initLabel(1, "自助","SELF", "分摊类型：自助");
	public static final LabelContext FEETYPE_FREE=FEETYPE.initLabel(2, "免费","FREE", "分摊类型：免费");
	
	public static final PropertyContext PRICETYPE=L.initProperty("定价类型", "定价类型", "定价类型", "定价类型，常规活动可按退款规则申请退款;特价活动不允许退款", "priceType", CoeActivityFee.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext PRICETYPE_NORMAL=PRICETYPE.initLabel(0, "常规","NORMAL", "常规活动可按退款规则申请退款");
	public static final LabelContext PRICETYPE_SPECIAL=PRICETYPE.initLabel(1, "特价","SPECIAL", "特价活动不允许退款");
	
	public static final PropertyContext RECIEVETYPE=L.initProperty("收取方式", "收取方式", "费用收取方式", "费用收取方式：0：在线支付，1：线下收取。优先选择【在线支付】", "recieveType", CoeActivityFee.class.getName(),  SZ0099AppModule.DEVELOPER_ID_SZ0099);
	public static final LabelContext RECIEVETYPE_ONLIME=RECIEVETYPE.initLabel(0, "在线支付","ONLINE", "收取方式：在线支付");
	public static final LabelContext RECIEVETYPE_OFFLIME=RECIEVETYPE.initLabel(1, "线下收取","OFFLINE", "收取方式：线下收取");
	

	@Transient
	private CoeActivity activity;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '活动作者id'")
	private Long userId;
	
	/**
	 * 用于接收页面传递参数，段落所属主域
	 * 这里对应activityId
	 */
	@Column(name="activityId",length = 20, columnDefinition = "BIGINT(20) COMMENT '活动Id'")
	private Long mainId;
	
	@Column(name="rmbAmount",length = 11, columnDefinition = "INT(11) COMMENT 'RMB支付金额'")
	private Long rmbAmount;//单位：分
	
	@Column(name="rmbAmountOri",length = 11, columnDefinition = "INT(11) COMMENT 'RMB原价'")
	private Long rmbAmountOri;//单位：分
	
	@Column(name="cashAmount",length = 11, columnDefinition = "INT(11) COMMENT '余额支付金额'")
	private Long cashAmount;
	
	@Column(name="coinAmount",length = 11, columnDefinition = "INT(11) COMMENT '币额支付金额'")
	private Long coinAmount;
	
	@Column(name="currency",length = 3, columnDefinition = "INT(3) COMMENT '货币支付方式'")
	private Integer currency;
	
	@Column(name="feeType",length = 3, columnDefinition = "INT(3) COMMENT '分摊类型'")
	private Integer feeType;
	
	@Column(name="recieveType",length = 3, columnDefinition = "INT(3) COMMENT '收取方式'")
	private Integer recieveType;
	
	@Column(name="priceType",length = 3, columnDefinition = "INT(3) COMMENT '价格类型'")
	private Integer priceType;
	
	@Column(name="description", length = 2048, columnDefinition="varchar(2048) COMMENT '费用说明'")
	private String description;
	

	public CoeActivity getActivity() {
		return activity;
	}

	public void setActivity(CoeActivity activity) {
		this.activity = activity;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public Long getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(Long cashAmount) {
		this.cashAmount = cashAmount;
	}

	public Long getCoinAmount() {
		return coinAmount;
	}

	public void setCoinAmount(Long coinAmount) {
		this.coinAmount = coinAmount;
	}


	public Long getRmbAmount() {
		return rmbAmount;
	}

	public void setRmbAmount(Long rmbAmount) {
		this.rmbAmount = rmbAmount;
	}

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRmbAmountOri() {
		return rmbAmountOri;
	}

	public void setRmbAmountOri(Long rmbAmountOri) {
		this.rmbAmountOri = rmbAmountOri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Integer getRecieveType() {
		return recieveType;
	}

	public void setRecieveType(Integer recieveType) {
		this.recieveType = recieveType;
	}

	public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

}
