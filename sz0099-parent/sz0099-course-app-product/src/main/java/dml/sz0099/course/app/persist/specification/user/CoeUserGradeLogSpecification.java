package dml.sz0099.course.app.persist.specification.user;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.user.CoeUserGradeLog;
import dml.sz0099.course.app.persist.entity.user.QCoeUserGradeLog;

/**
 * 
 * CoeUserGradeLogSpecification 条件构造
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
 * description: CoeUserGradeLogSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeUserGradeLogSpecification {

	private CoeUserGradeLogSpecification() {
		throw new IllegalStateException("CoeUserGradeLogSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeUserGradeLog> getConditionByCode(final CoeUserGradeLog coeUserGradeLog) {
		return new Specification<CoeUserGradeLog>() {
			public Predicate toPredicate(Root<CoeUserGradeLog> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeUserGradeLog
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeUserGradeLog coeUserGradeLog) {

		QCoeUserGradeLog qcoeUserGradeLog = QCoeUserGradeLog.coeUserGradeLog;

		BooleanExpression eq = qcoeUserGradeLog.deleted.isFalse();
		// do something
		return eq;

	}
}
