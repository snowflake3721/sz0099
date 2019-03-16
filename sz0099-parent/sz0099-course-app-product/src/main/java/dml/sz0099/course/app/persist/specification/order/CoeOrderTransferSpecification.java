package dml.sz0099.course.app.persist.specification.order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.order.CoeOrderTransfer;
import dml.sz0099.course.app.persist.entity.order.QCoeOrderTransfer;

/**
 * 
 * CoeOrderTransferSpecification 条件构造
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
 * description: CoeOrderTransferSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeOrderTransferSpecification {

	private CoeOrderTransferSpecification() {
		throw new IllegalStateException("CoeOrderTransferSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeOrderTransfer> getConditionByCode(final CoeOrderTransfer coeOrderTransfer) {
		return new Specification<CoeOrderTransfer>() {
			public Predicate toPredicate(Root<CoeOrderTransfer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeOrderTransfer
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeOrderTransfer coeOrderTransfer) {

		QCoeOrderTransfer qcoeOrderTransfer = QCoeOrderTransfer.coeOrderTransfer;

		BooleanExpression eq = qcoeOrderTransfer.deleted.isFalse();
		// do something
		return eq;

	}
}
