package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityTime;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivityTime;

/**
 * 
 * CoeActivityTimeSpecification 条件构造
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
 * description: CoeActivityTimeSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeActivityTimeSpecification {

	private CoeActivityTimeSpecification() {
		throw new IllegalStateException("CoeActivityTimeSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeActivityTime> getConditionByCode(final CoeActivityTime coeActivityTime) {
		return new Specification<CoeActivityTime>() {
			public Predicate toPredicate(Root<CoeActivityTime> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeActivityTime
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeActivityTime coeActivityTime) {

		QCoeActivityTime qcoeActivityTime = QCoeActivityTime.coeActivityTime;
		// do something
		BooleanExpression eq = qcoeActivityTime.deleted.isFalse();
		
		return eq;

	}
	
	public static BooleanExpression getConditionWithNotself(final CoeActivityTime coeActivityTime) {

		QCoeActivityTime qcoeActivityTime = QCoeActivityTime.coeActivityTime;
		// do something
		BooleanExpression eq = qcoeActivityTime.deleted.isFalse();
		
		if(null != coeActivityTime) {
			Long mainId = coeActivityTime.getMainId();
			if(null != mainId) {
				eq=eq.and(qcoeActivityTime.mainId.ne(mainId));
			}
		}
		return eq;

	}
}
