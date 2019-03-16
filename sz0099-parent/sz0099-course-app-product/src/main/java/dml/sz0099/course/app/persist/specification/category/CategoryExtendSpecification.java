package dml.sz0099.course.app.persist.specification.category;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.category.CategoryExtend;
import dml.sz0099.course.app.persist.entity.category.QCategoryExtend;

/**
 * 
 * CategoryExtendSpecification 条件构造
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
 * description: CategoryExtendSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CategoryExtendSpecification {

	private CategoryExtendSpecification() {
		throw new IllegalStateException("CategoryExtendSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CategoryExtend> getConditionByCode(final CategoryExtend categoryExtend) {
		return new Specification<CategoryExtend>() {
			public Predicate toPredicate(Root<CategoryExtend> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param categoryExtend
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CategoryExtend categoryExtend) {

		QCategoryExtend qcategoryExtend = QCategoryExtend.categoryExtend;

		BooleanExpression eq = qcategoryExtend.deleted.isFalse();
		// do something
		return eq;

	}
}
