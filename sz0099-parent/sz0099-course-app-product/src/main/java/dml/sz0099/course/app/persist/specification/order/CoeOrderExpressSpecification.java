package dml.sz0099.course.app.persist.specification.order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.order.CoeOrderExpress;
import dml.sz0099.course.app.persist.entity.order.QCoeOrderExpress;

/**
 * 
 * CoeOrderExpressSpecification 条件构造
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
 * description: CoeOrderExpressSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeOrderExpressSpecification {

	private CoeOrderExpressSpecification() {
		throw new IllegalStateException("CoeOrderExpressSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeOrderExpress> getConditionByCode(final CoeOrderExpress coeOrderExpress) {
		return new Specification<CoeOrderExpress>() {
			public Predicate toPredicate(Root<CoeOrderExpress> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeOrderExpress
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeOrderExpress coeOrderExpress) {

		QCoeOrderExpress qcoeOrderExpress = QCoeOrderExpress.coeOrderExpress;

		BooleanExpression eq = qcoeOrderExpress.deleted.isFalse();
		// do something
		return eq;

	}
}
