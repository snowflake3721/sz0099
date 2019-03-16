package dml.sz0099.course.app.persist.specification.article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.ParagArticle;
import dml.sz0099.course.app.persist.entity.article.QParagArticle;

/**
 * 
 * ParagArticleSpecification 条件构造
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
 * description: ParagArticleSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ParagArticleSpecification {

	private ParagArticleSpecification() {
		throw new IllegalStateException("ParagArticleSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ParagArticle> getConditionByCode(final ParagArticle paragArticle) {
		return new Specification<ParagArticle>() {
			public Predicate toPredicate(Root<ParagArticle> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param paragArticle
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ParagArticle paragArticle) {

		QParagArticle qparagArticle = QParagArticle.paragArticle;

		BooleanExpression eq = qparagArticle.deleted.isFalse();
		// do something
		return eq;

	}
}
