package dml.sz0099.course.app.persist.specification.article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.CoeArticleFavirate;
import dml.sz0099.course.app.persist.entity.article.QCoeArticleFavirate;

/**
 * 
 * CoeArticleFavirateSpecification 条件构造
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
 * description: CoeArticleFavirateSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeArticleFavirateSpecification {

	private CoeArticleFavirateSpecification() {
		throw new IllegalStateException("CoeArticleFavirateSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeArticleFavirate> getConditionByCode(final CoeArticleFavirate coeArticleFavirate) {
		return new Specification<CoeArticleFavirate>() {
			public Predicate toPredicate(Root<CoeArticleFavirate> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeArticleFavirate
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeArticleFavirate coeArticleFavirate) {

		QCoeArticleFavirate qcoeArticleFavirate = QCoeArticleFavirate.coeArticleFavirate;

		BooleanExpression eq = qcoeArticleFavirate.deleted.isFalse();
		// do something
		return eq;

	}
}
