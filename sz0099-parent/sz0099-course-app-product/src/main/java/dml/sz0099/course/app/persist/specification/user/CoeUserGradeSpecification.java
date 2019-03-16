package dml.sz0099.course.app.persist.specification.user;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;
import dml.sz0099.course.app.persist.entity.user.QCoeUserGrade;

/**
 * 
 * CoeUserGradeSpecification 条件构造
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
 * description: CoeUserGradeSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeUserGradeSpecification {

	private CoeUserGradeSpecification() {
		throw new IllegalStateException("CoeUserGradeSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeUserGrade> getConditionByCode(final CoeUserGrade coeUserGrade) {
		return new Specification<CoeUserGrade>() {
			public Predicate toPredicate(Root<CoeUserGrade> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeUserGrade
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeUserGrade coeUserGrade) {

		QCoeUserGrade qcoeUserGrade = QCoeUserGrade.coeUserGrade;

		BooleanExpression eq = qcoeUserGrade.deleted.isFalse();
		// do something
		return eq;

	}
}
