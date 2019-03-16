package dml.sz0099.course.app.persist.specification.article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePraise;
import dml.sz0099.course.app.persist.entity.article.QCoeArticlePraise;

/**
 * 
 * CoeArticlePraiseSpecification 条件构造
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
 * description: CoeArticlePraiseSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeArticlePraiseSpecification {

	private CoeArticlePraiseSpecification() {
		throw new IllegalStateException("CoeArticlePraiseSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeArticlePraise> getConditionByCode(final CoeArticlePraise coeArticlePraise) {
		return new Specification<CoeArticlePraise>() {
			public Predicate toPredicate(Root<CoeArticlePraise> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeArticlePraise
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeArticlePraise coeArticlePraise) {

		QCoeArticlePraise qcoeArticlePraise = QCoeArticlePraise.coeArticlePraise;

		BooleanExpression eq = qcoeArticlePraise.deleted.isFalse();
		// do something
		return eq;

	}
}
