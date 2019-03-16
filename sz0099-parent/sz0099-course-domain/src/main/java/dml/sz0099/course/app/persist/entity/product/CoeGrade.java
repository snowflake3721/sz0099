/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

/**
 * <pre>
 * @formatter:off
 *
 * 会员等级定义
 * @author bruce yang at 2018-09-13 00:55:57
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-13	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Entity
@Table(name="s9_coe_Grade")
public class CoeGrade extends BaseEntityExtendLong implements Serializable{

	private static final long serialVersionUID = 37692280491744127L;
	
	public static final Integer GRADE_L0 = 0;//童子拜佛 0    100
	public static final Integer GRADE_L1 = 1;//灵蛇探路 1 99   0.95
	public static final Integer GRADE_L10 = 10;//凌波微步 199  0.9
	public static final Integer GRADE_L20 = 20;//无相劫指 299  0.85
	public static final Integer GRADE_L30 = 30;//灵犀一指 399  0.8
	public static final Integer GRADE_L40 = 40;//降龙十八 499  0.7
	public static final Integer GRADE_L50 = 50;//六脉神剑 599  0.65
	public static final Integer GRADE_L60 = 60;//斗转星移 699  0.60
	public static final Integer GRADE_L70 = 70;//七伤拳 799  0.55
	public static final Integer GRADE_L80 = 80;//太极 899  0.50
	public static final Integer GRADE_L90 = 90;//独孤九剑 999  0.45
	public static final Integer GRADE_L100 = 100;//先天功 1999  0.4
	public static final Integer GRADE_L110 = 110;//极咒返阴阳 39999  0.35
	public static final Integer GRADE_L120 = 120;//三昧真火 79999  0.33
	public static final Integer GRADE_L130 = 130;//天劫不灭 99999  0.30
	
	public static final Integer BASERADIX_100=100;
	public static final Integer TAG_NUM_5=5;
	public static final Integer PROFESSION_TAG_NUM_5=5;
	
	@Column(name="baseRadix",columnDefinition="int(4) COMMENT '基数100'")
	private Integer baseRadix;//基数
	

	@Column(name="name",columnDefinition="char(16) COMMENT '名称'")
	private String name;//等级名称
	
	@Column(name="description",columnDefinition="varchar(60) COMMENT '等级描述'")
	private String description;//等级描述
	
	@Column(name="grade",columnDefinition="int(3) COMMENT '会员等级'")
	private Integer grade;//会员等级
	
	@Column(name="rates",columnDefinition="int(4) COMMENT '折扣系数'")
	private Integer rates;//折扣系数,  折扣价=price*rates/baseRadix
	
	@Column(name="fee",columnDefinition="int(11) COMMENT '会费'")
	private Integer fee;//会费
	
	@Column(name="tagNum",columnDefinition="int(3) COMMENT '标签数量'")
	private Integer tagNum;//单个产品的标签数量
	
	@Column(name="professionTagNum",columnDefinition="int(3) COMMENT '单个技能标签数量'")
	private Integer professionTagNum;//单个技能的标签数量

	/**
	 * @return the grade
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * @return the rates
	 */
	public Integer getRates() {
		return rates;
	}

	/**
	 * @param rates the rates to set
	 */
	public void setRates(Integer rates) {
		this.rates = rates;
	}

	/**
	 * @return the fee
	 */
	public Integer getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(Integer fee) {
		this.fee = fee;
	}

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

	public Integer getBaseRadix() {
		return baseRadix;
	}

	public void setBaseRadix(Integer baseRadix) {
		this.baseRadix = baseRadix;
	}

	public Integer getTagNum() {
		return tagNum;
	}

	public void setTagNum(Integer tagNum) {
		this.tagNum = tagNum;
	}

	public Integer getProfessionTagNum() {
		return professionTagNum;
	}

	public void setProfessionTagNum(Integer professionTagNum) {
		this.professionTagNum = professionTagNum;
	}
}
