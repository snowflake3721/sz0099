package dml.sz0099.course.app.persist.specification.article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.QCoeArticlePosition;

/**
 * 
 * CoeArticlePositionSpecification 条件构造
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
 * description: CoeArticlePositionSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeArticlePositionSpecification {

	private CoeArticlePositionSpecification() {
		throw new IllegalStateException("CoeArticlePositionSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeArticlePosition> getConditionByCode(final CoeArticlePosition coeArticlePosition) {
		return new Specification<CoeArticlePosition>() {
			public Predicate toPredicate(Root<CoeArticlePosition> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeArticlePosition
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeArticlePosition coeArticlePosition) {

		QCoeArticlePosition qcoeArticlePosition = QCoeArticlePosition.coeArticlePosition;

		BooleanExpression eq = qcoeArticlePosition.deleted.isFalse();
		// do something
		return eq;

	}
}
