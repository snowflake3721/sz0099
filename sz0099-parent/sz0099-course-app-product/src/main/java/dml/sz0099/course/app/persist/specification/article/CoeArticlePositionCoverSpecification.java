package dml.sz0099.course.app.persist.specification.article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionCover;
import dml.sz0099.course.app.persist.entity.article.QCoeArticlePositionCover;

/**
 * 
 * CoeArticlePositionCoverSpecification 条件构造
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
 * description: CoeArticlePositionCoverSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeArticlePositionCoverSpecification {

	private CoeArticlePositionCoverSpecification() {
		throw new IllegalStateException("CoeArticlePositionCoverSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeArticlePositionCover> getConditionByCode(final CoeArticlePositionCover coeArticlePositionCover) {
		return new Specification<CoeArticlePositionCover>() {
			public Predicate toPredicate(Root<CoeArticlePositionCover> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeArticlePositionCover
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeArticlePositionCover coeArticlePositionCover) {

		QCoeArticlePositionCover qcoeArticlePositionCover = QCoeArticlePositionCover.coeArticlePositionCover;

		BooleanExpression eq = qcoeArticlePositionCover.deleted.isFalse();
		// do something
		return eq;

	}
}
