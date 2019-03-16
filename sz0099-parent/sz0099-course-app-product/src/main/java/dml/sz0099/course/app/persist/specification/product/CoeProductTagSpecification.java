package dml.sz0099.course.app.persist.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.product.CoeProductTag;

/**
 * 
 * CoeProductTagSpecification 条件构造
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
 * description: CoeProductTagSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeProductTagSpecification {

	private CoeProductTagSpecification() {
		throw new IllegalStateException("CoeProductTagSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeProductTag> getConditionByCode(final CoeProductTag coeProductTag) {
		return new Specification<CoeProductTag>() {
			public Predicate toPredicate(Root<CoeProductTag> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeProductTag
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeProductTag coeProductTag) {

		//QCoeProductTag qcoeProductTag = QCoeProductTag.coeProductTag;

		BooleanExpression eq = null;
		// do something
		return eq;

	}
}
