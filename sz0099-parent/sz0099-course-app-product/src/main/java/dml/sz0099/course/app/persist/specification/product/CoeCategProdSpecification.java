package dml.sz0099.course.app.persist.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.product.CoeCategProd;
import dml.sz0099.course.app.persist.entity.product.QCoeCategProd;

/**
 * 
 * CoeCategProdSpecification 条件构造
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
 * description: CoeCategProdSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeCategProdSpecification {

	private CoeCategProdSpecification() {
		throw new IllegalStateException("CoeCategProdSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeCategProd> getConditionByCode(final CoeCategProd coeCategProd) {
		return new Specification<CoeCategProd>() {
			public Predicate toPredicate(Root<CoeCategProd> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeCategProd
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeCategProd coeCategProd) {

		QCoeCategProd qcoeCategProd = QCoeCategProd.coeCategProd;

		BooleanExpression eq = qcoeCategProd.deleted.isFalse();
		// do something
		return eq;

	}
}
