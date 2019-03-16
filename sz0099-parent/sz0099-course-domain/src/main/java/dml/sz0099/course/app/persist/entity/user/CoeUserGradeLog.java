/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

/**
 * <pre>
 * @formatter:off
 *
 * 用户等级缴费日志
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
@Table(name="s9_coe_UserGradeLog")
public class CoeUserGradeLog extends BaseEntityExtendLong implements Serializable{

	
	
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
	private Integer fee;//会费总金额
	
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
