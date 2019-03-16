package dml.sz0099.course.app.persist.specification.paragraph;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.entity.paragraph.QParagProduct;

/**
 * 
 * ParagProductSpecification 条件构造
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
 * description: ParagProductSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ParagProductSpecification {

	private ParagProductSpecification() {
		throw new IllegalStateException("ParagProductSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ParagProduct> getConditionByCode(final ParagProduct paragProduct) {
		return new Specification<ParagProduct>() {
			public Predicate toPredicate(Root<ParagProduct> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param paragProduct
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ParagProduct paragProduct) {

		QParagProduct qparagProduct = QParagProduct.paragProduct;

		BooleanExpression eq = qparagProduct.deleted.isFalse();
		// do something
		return eq;

	}
}
