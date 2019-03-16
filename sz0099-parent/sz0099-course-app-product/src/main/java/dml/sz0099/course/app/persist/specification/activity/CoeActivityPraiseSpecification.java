package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPraise;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivityPraise;

/**
 * 
 * CoeActivityPraiseSpecification 条件构造
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
 * description: CoeActivityPraiseSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeActivityPraiseSpecification {

	private CoeActivityPraiseSpecification() {
		throw new IllegalStateException("CoeActivityPraiseSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeActivityPraise> getConditionByCode(final CoeActivityPraise coeActivityPraise) {
		return new Specification<CoeActivityPraise>() {
			public Predicate toPredicate(Root<CoeActivityPraise> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeActivityPraise
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeActivityPraise coeActivityPraise) {

		QCoeActivityPraise qcoeActivityPraise = QCoeActivityPraise.coeActivityPraise;

		BooleanExpression eq = qcoeActivityPraise.deleted.isFalse();
		// do something
		return eq;

	}
}
