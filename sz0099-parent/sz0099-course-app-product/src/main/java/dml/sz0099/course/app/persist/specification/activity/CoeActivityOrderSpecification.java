package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivityOrder;

/**
 * 
 * CoeActivityOrderSpecification 条件构造
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
 * description: CoeActivityOrderSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeActivityOrderSpecification {

	private CoeActivityOrderSpecification() {
		throw new IllegalStateException("CoeActivityOrderSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeActivityOrder> getConditionByCode(final CoeActivityOrder coeActivityOrder) {
		return new Specification<CoeActivityOrder>() {
			public Predicate toPredicate(Root<CoeActivityOrder> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeActivityOrder
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeActivityOrder coeActivityOrder) {

		QCoeActivityOrder qcoeActivityOrder = QCoeActivityOrder.coeActivityOrder;
		// do something
		BooleanExpression eq = qcoeActivityOrder.deleted.isFalse();
		
		eq=eq.and(qcoeActivityOrder.userId.eq(coeActivityOrder.getUserId()));
		
		return eq;

	}
	
	public static BooleanExpression getConditionWithNotself(final CoeActivityOrder coeActivityOrder) {

		QCoeActivityOrder qcoeActivityOrder = QCoeActivityOrder.coeActivityOrder;
		// do something
		BooleanExpression eq = qcoeActivityOrder.deleted.isFalse();
		
		if(null != coeActivityOrder) {
			Long mainId = coeActivityOrder.getMainId();
			if(null != mainId) {
				eq=eq.and(qcoeActivityOrder.mainId.ne(mainId));
			}
		}
		return eq;

	}
}
