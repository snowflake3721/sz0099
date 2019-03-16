package dml.sz0099.course.app.persist.specification.order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.order.CoeOrderAsign;
import dml.sz0099.course.app.persist.entity.order.QCoeOrderAsign;

/**
 * 
 * CoeOrderAsignSpecification 条件构造
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
 * description: CoeOrderAsignSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeOrderAsignSpecification {

	private CoeOrderAsignSpecification() {
		throw new IllegalStateException("CoeOrderAsignSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeOrderAsign> getConditionByCode(final CoeOrderAsign coeOrderAsign) {
		return new Specification<CoeOrderAsign>() {
			public Predicate toPredicate(Root<CoeOrderAsign> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeOrderAsign
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeOrderAsign coeOrderAsign) {

		QCoeOrderAsign qcoeOrderAsign = QCoeOrderAsign.coeOrderAsign;

		BooleanExpression eq = qcoeOrderAsign.deleted.isFalse();
		// do something
		return eq;

	}
}
