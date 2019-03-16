package dml.sz0099.course.app.persist.specification.order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.order.CoeOrderTransferLog;
import dml.sz0099.course.app.persist.entity.order.QCoeOrderTransferLog;

/**
 * 
 * CoeOrderTransferLogSpecification 条件构造
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
 * description: CoeOrderTransferLogSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeOrderTransferLogSpecification {

	private CoeOrderTransferLogSpecification() {
		throw new IllegalStateException("CoeOrderTransferLogSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeOrderTransferLog> getConditionByCode(final CoeOrderTransferLog coeOrderTransferLog) {
		return new Specification<CoeOrderTransferLog>() {
			public Predicate toPredicate(Root<CoeOrderTransferLog> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeOrderTransferLog
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeOrderTransferLog coeOrderTransferLog) {

		QCoeOrderTransferLog qcoeOrderTransferLog = QCoeOrderTransferLog.coeOrderTransferLog;

		BooleanExpression eq = qcoeOrderTransferLog.deleted.isFalse();
		// do something
		return eq;

	}
}
