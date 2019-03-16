package dml.sz0099.course.app.persist.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * 
 * CourseProductSpecification 条件构造
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
 * description: CourseProductSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CourseProductSpecification {

	private CourseProductSpecification() {
		throw new IllegalStateException("CourseProductSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeProduct> getConditionByCode(final CoeProduct courseProduct) {
		return new Specification<CoeProduct>() {
			public Predicate toPredicate(Root<CoeProduct> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param courseProduct
	 * @return
	 */
	public static BooleanExpression getConditionByCodeWithQslExpression(final CoeProduct courseProduct) {

		//QCourseProduct qcourseProduct = QCourseProduct.courseProduct;

		BooleanExpression eq = null;
		// do something
		return eq;

	}
}
