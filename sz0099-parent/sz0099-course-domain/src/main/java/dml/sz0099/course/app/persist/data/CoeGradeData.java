/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import dml.sz0099.course.app.module.define.SZ0099Robot;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;

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
public class CoeGradeData implements Serializable{

	private static final long serialVersionUID = 37692280491744127L;
	
	public static final Integer GRADE_L0 = 0;//童子拜佛 0    100
	public static final Integer GRADE_L1 = 1;//凌波微步 199  0.9
	public static final Integer GRADE_L2 = 2;//无相劫指 299  0.85
	public static final Integer GRADE_L3 = 3;//灵犀一指 399  0.8
	public static final Integer GRADE_L4 = 4;//降龙十八 499  0.7
	public static final Integer GRADE_L5 = 5;//六脉神剑 599  0.65
	public static final Integer GRADE_L6 = 6;//斗转星移 699  0.60
	public static final Integer GRADE_L7 = 7;//七伤拳 799  0.55
	public static final Integer GRADE_L8 = 8;//太极 899  0.50
	public static final Integer GRADE_L9 = 9;//独孤九剑 999  0.45
	
	
	public static List<CoeGrade> generateCoeGradeList(){
		List<CoeGrade> entityList = new ArrayList<>();
		entityList.add(createCoeGrade("童子拜佛","0级会员",CoeGrade.GRADE_L0,100,0,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("灵蛇探路","1级会员",CoeGrade.GRADE_L1,95,99,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("凌波微步","2级会员",CoeGrade.GRADE_L10,90,199,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("无相劫指","3级会员",CoeGrade.GRADE_L20,85,299,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("灵犀一指","4级会员",CoeGrade.GRADE_L30,80,399,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("降龙十八","5级会员",CoeGrade.GRADE_L40,70,499,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("六脉神剑","6级会员",CoeGrade.GRADE_L50,65,599,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("斗转星移","7级会员",CoeGrade.GRADE_L60,60,699,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("七伤拳","8级会员",CoeGrade.GRADE_L70,55,799,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("太极","9级会员",CoeGrade.GRADE_L80,50,899,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("独孤九剑","10级会员",CoeGrade.GRADE_L90,45,1999,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("先天功","11级会员",CoeGrade.GRADE_L100,40,2999,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("极咒返阴阳","12级会员",CoeGrade.GRADE_L110,35,39999,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("三昧真火","13级会员",CoeGrade.GRADE_L120,33,79999,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		entityList.add(createCoeGrade("天劫不灭","14级会员",CoeGrade.GRADE_L130,30,99999,CoeGrade.TAG_NUM_5, CoeGrade.PROFESSION_TAG_NUM_5));
		
		return entityList;
	}
	
	public static CoeGrade createCoeGrade(String name, String description, Integer grade, Integer rates, Integer fee, Integer tagNum, Integer professionTagNum){
		CoeGrade entity = new CoeGrade();
		entity.setName(name);
		entity.setDescription(description);
		entity.setGrade(grade);
		entity.setRates(rates);
		entity.setFee(fee);
		entity.setBaseRadix(CoeGrade.BASERADIX_100);
		entity.setCreatedBy(SZ0099Robot.ROBOT_SZ0099_MEIMEI.getId());
		entity.setLastModifiedBy(SZ0099Robot.ROBOT_SZ0099_MEIMEI.getId());
		entity.setTagNum(tagNum);
		entity.setProfessionTagNum(professionTagNum);
		return entity;
	}
}
