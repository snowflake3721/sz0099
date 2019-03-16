package dml.sz0099.course.app.persist.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.product.CoeProductLog;
import dml.sz0099.course.app.persist.entity.product.QCoeProductLog;

/**
 * 
 * CoeProductLogSpecification 条件构造
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
 * description: CoeProductLogSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeProductLogSpecification {

	private CoeProductLogSpecification() {
		throw new IllegalStateException("CoeProductLogSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeProductLog> getConditionByCode(final CoeProductLog coeProductLog) {
		return new Specification<CoeProductLog>() {
			public Predicate toPredicate(Root<CoeProductLog> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeProductLog
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeProductLog coeProductLog) {

		QCoeProductLog qcoeProductLog = QCoeProductLog.coeProductLog;

		BooleanExpression eq = qcoeProductLog.deleted.isFalse();
		// do something
		return eq;

	}
}
