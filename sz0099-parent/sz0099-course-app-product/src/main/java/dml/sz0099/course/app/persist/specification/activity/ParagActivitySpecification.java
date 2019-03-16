package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.ParagActivity;
import dml.sz0099.course.app.persist.entity.activity.QParagActivity;

/**
 * 
 * ParagActivitySpecification 条件构造
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
 * description: ParagActivitySpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ParagActivitySpecification {

	private ParagActivitySpecification() {
		throw new IllegalStateException("ParagActivitySpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ParagActivity> getConditionByCode(final ParagActivity paragActivity) {
		return new Specification<ParagActivity>() {
			public Predicate toPredicate(Root<ParagActivity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param paragActivity
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ParagActivity paragActivity) {

		QParagActivity qparagActivity = QParagActivity.paragActivity;

		BooleanExpression eq = qparagActivity.deleted.isFalse();
		// do something
		return eq;

	}
}
