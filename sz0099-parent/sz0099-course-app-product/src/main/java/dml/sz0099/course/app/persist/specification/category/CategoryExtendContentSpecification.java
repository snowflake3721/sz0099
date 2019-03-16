package dml.sz0099.course.app.persist.specification.category;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.category.CategoryExtendContent;
import dml.sz0099.course.app.persist.entity.category.QCategoryExtendContent;

/**
 * 
 * CategoryExtendContentSpecification 条件构造
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
 * description: CategoryExtendContentSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CategoryExtendContentSpecification {

	private CategoryExtendContentSpecification() {
		throw new IllegalStateException("CategoryExtendContentSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CategoryExtendContent> getConditionByCode(final CategoryExtendContent categoryExtendContent) {
		return new Specification<CategoryExtendContent>() {
			public Predicate toPredicate(Root<CategoryExtendContent> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param categoryExtendContent
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CategoryExtendContent categoryExtendContent) {

		QCategoryExtendContent qcategoryExtendContent = QCategoryExtendContent.categoryExtendContent;

		BooleanExpression eq = qcategoryExtendContent.deleted.isFalse();
		// do something
		return eq;

	}
}
