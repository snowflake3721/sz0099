package dml.sz0099.course.app.persist.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.product.CoeOpration;
import dml.sz0099.course.app.persist.entity.product.QCoeOpration;

/**
 * 
 * CoeOprationSpecification 条件构造
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
 * description: CoeOprationSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeOprationSpecification {

	private CoeOprationSpecification() {
		throw new IllegalStateException("CoeOprationSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeOpration> getConditionByCode(final CoeOpration coeOpration) {
		return new Specification<CoeOpration>() {
			public Predicate toPredicate(Root<CoeOpration> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeOpration
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeOpration coeOpration) {

		QCoeOpration qcoeOpration = QCoeOpration.coeOpration;

		BooleanExpression eq = qcoeOpration.deleted.isFalse();
		// do something
		return eq;

	}
}
