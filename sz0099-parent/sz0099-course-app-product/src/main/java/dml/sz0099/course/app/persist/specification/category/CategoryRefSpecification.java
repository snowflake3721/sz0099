package dml.sz0099.course.app.persist.specification.category;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.category.CategoryRef;
import dml.sz0099.course.app.persist.entity.category.QCategoryRef;

/**
 * 
 * CategoryRefSpecification 条件构造
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
 * description: CategoryRefSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CategoryRefSpecification {

	private CategoryRefSpecification() {
		throw new IllegalStateException("CategoryRefSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CategoryRef> getConditionByCode(final CategoryRef categoryRef) {
		return new Specification<CategoryRef>() {
			public Predicate toPredicate(Root<CategoryRef> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param categoryRef
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CategoryRef categoryRef) {

		QCategoryRef qcategoryRef = QCategoryRef.categoryRef;

		BooleanExpression eq = qcategoryRef.deleted.isFalse();
		// do something
		return eq;

	}
}
