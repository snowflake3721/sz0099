package dml.sz0099.course.app.persist.specification.order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.order.CoeOrderProdLog;

/**
 * 
 * CoeOrderProdLogSpecification 条件构造
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
 * description: CoeOrderProdLogSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeOrderProdLogSpecification {

	private CoeOrderProdLogSpecification() {
		throw new IllegalStateException("CoeOrderProdLogSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeOrderProdLog> getConditionByCode(final CoeOrderProdLog coeOrderProdLog) {
		return new Specification<CoeOrderProdLog>() {
			public Predicate toPredicate(Root<CoeOrderProdLog> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeOrderProdLog
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeOrderProdLog coeOrderProdLog) {

		//QCoeOrderProdLog qcoeOrderProdLog = QCoeOrderProdLog.coeOrderProdLog;

		BooleanExpression eq = null;
		// do something
		return eq;

	}
}
