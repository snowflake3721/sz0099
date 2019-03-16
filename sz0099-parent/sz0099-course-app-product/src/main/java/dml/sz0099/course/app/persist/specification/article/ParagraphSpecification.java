package dml.sz0099.course.app.persist.specification.article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.Paragraph;
import dml.sz0099.course.app.persist.entity.article.QParagraph;

/**
 * 
 * ParagraphSpecification 条件构造
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
 * description: ParagraphSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ParagraphSpecification {

	private ParagraphSpecification() {
		throw new IllegalStateException("ParagraphSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<Paragraph> getConditionByCode(final Paragraph paragraph) {
		return new Specification<Paragraph>() {
			public Predicate toPredicate(Root<Paragraph> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param paragraph
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final Paragraph paragraph) {

		QParagraph qparagraph = QParagraph.paragraph;

		BooleanExpression eq = qparagraph.deleted.isFalse();
		// do something
		return eq;

	}
}
