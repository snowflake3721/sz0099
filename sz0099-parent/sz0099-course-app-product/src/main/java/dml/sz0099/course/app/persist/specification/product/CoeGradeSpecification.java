package dml.sz0099.course.app.persist.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.product.CoeGrade;
import dml.sz0099.course.app.persist.entity.product.QCoeGrade;

/**
 * 
 * CoeGradeSpecification 条件构造
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
 * description: CoeGradeSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeGradeSpecification {

	private CoeGradeSpecification() {
		throw new IllegalStateException("CoeGradeSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeGrade> getConditionByCode(final CoeGrade coeGrade) {
		return new Specification<CoeGrade>() {
			public Predicate toPredicate(Root<CoeGrade> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeGrade
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeGrade coeGrade) {

		QCoeGrade qcoeGrade = QCoeGrade.coeGrade;

		BooleanExpression eq = qcoeGrade.deleted.isFalse();
		// do something
		return eq;

	}
}
