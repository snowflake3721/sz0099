package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityFee;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivity;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivityFee;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.tag.QCoeTag;

/**
 * 
 * CoeActivityFeeSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:32:02
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 *
 */
/**
 * 
 * @formatter:off
 * 
 * description: CoeActivityFeeSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeActivityFeeSpecification {

	private CoeActivityFeeSpecification() {
		throw new IllegalStateException("CoeActivityFeeSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeActivityFee> getConditionByCode(final CoeActivityFee coeActivityFee) {
		return new Specification<CoeActivityFee>() {
			public Predicate toPredicate(Root<CoeActivityFee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeActivityFee
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeActivityFee coeActivityFee) {

		QCoeActivityFee qcoeActivityFee = QCoeActivityFee.coeActivityFee;
		// do something
		BooleanExpression eq = qcoeActivityFee.deleted.isFalse();
		
		return eq;

	}
	
	public static BooleanExpression getConditionWithNotself(final CoeActivityFee coeActivityFee) {

		QCoeActivityFee qcoeActivityFee = QCoeActivityFee.coeActivityFee;
		// do something
		BooleanExpression eq = qcoeActivityFee.deleted.isFalse();
		
		if(null != coeActivityFee) {
			Long mainId = coeActivityFee.getMainId();
			if(null != mainId) {
				eq=eq.and(qcoeActivityFee.mainId.ne(mainId));
			}
		}
		return eq;

	}
}
