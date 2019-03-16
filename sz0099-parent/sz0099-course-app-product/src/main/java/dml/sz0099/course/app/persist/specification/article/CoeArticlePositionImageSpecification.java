package dml.sz0099.course.app.persist.specification.article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePositionImage;
import dml.sz0099.course.app.persist.entity.article.QCoeArticlePositionImage;

/**
 * 
 * CoeArticlePositionImageSpecification 条件构造
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
 * description: CoeArticlePositionImageSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeArticlePositionImageSpecification {

	private CoeArticlePositionImageSpecification() {
		throw new IllegalStateException("CoeArticlePositionImageSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeArticlePositionImage> getConditionByCode(final CoeArticlePositionImage coeArticlePositionImage) {
		return new Specification<CoeArticlePositionImage>() {
			public Predicate toPredicate(Root<CoeArticlePositionImage> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeArticlePositionImage
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeArticlePositionImage coeArticlePositionImage) {

		QCoeArticlePositionImage qcoeArticlePositionImage = QCoeArticlePositionImage.coeArticlePositionImage;

		BooleanExpression eq = qcoeArticlePositionImage.deleted.isFalse();
		// do something
		return eq;

	}
}
