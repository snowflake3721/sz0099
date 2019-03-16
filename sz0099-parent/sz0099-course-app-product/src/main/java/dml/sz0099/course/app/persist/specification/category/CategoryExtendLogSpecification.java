package dml.sz0099.course.app.persist.specification.category;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.category.CategoryExtendLog;
import dml.sz0099.course.app.persist.entity.category.QCategoryExtendLog;

/**
 * 
 * CategoryExtendLogSpecification 条件构造
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
 * description: CategoryExtendLogSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CategoryExtendLogSpecification {

	private CategoryExtendLogSpecification() {
		throw new IllegalStateException("CategoryExtendLogSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CategoryExtendLog> getConditionByCode(final CategoryExtendLog categoryExtendLog) {
		return new Specification<CategoryExtendLog>() {
			public Predicate toPredicate(Root<CategoryExtendLog> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param categoryExtendLog
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CategoryExtendLog categoryExtendLog) {

		QCategoryExtendLog qcategoryExtendLog = QCategoryExtendLog.categoryExtendLog;

		BooleanExpression eq = qcategoryExtendLog.deleted.isFalse();
		// do something
		return eq;

	}
}
