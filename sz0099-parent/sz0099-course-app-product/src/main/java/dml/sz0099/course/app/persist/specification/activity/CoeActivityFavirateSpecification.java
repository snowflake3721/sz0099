package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityFavirate;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivityFavirate;

/**
 * 
 * CoeActivityFavirateSpecification 条件构造
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
 * description: CoeActivityFavirateSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeActivityFavirateSpecification {

	private CoeActivityFavirateSpecification() {
		throw new IllegalStateException("CoeActivityFavirateSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeActivityFavirate> getConditionByCode(final CoeActivityFavirate coeActivityFavirate) {
		return new Specification<CoeActivityFavirate>() {
			public Predicate toPredicate(Root<CoeActivityFavirate> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeActivityFavirate
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeActivityFavirate coeActivityFavirate) {

		QCoeActivityFavirate qcoeActivityFavirate = QCoeActivityFavirate.coeActivityFavirate;

		BooleanExpression eq = qcoeActivityFavirate.deleted.isFalse();
		// do something
		return eq;

	}
}
