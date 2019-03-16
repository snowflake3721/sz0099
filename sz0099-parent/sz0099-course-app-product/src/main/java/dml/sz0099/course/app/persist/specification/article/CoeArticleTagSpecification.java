package dml.sz0099.course.app.persist.specification.article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.CoeArticleTag;
import dml.sz0099.course.app.persist.entity.article.QCoeArticle;
import dml.sz0099.course.app.persist.entity.article.QCoeArticleTag;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.tag.QCoeTag;

/**
 * 
 * CoeArticleTagSpecification 条件构造
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
 * description: CoeArticleTagSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeArticleTagSpecification {

	private CoeArticleTagSpecification() {
		throw new IllegalStateException("CoeArticleTagSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeArticleTag> getConditionByCode(final CoeArticleTag coeArticleTag) {
		return new Specification<CoeArticleTag>() {
			public Predicate toPredicate(Root<CoeArticleTag> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeArticleTag
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeArticleTag coeArticleTag) {

		QCoeArticleTag qcoeArticleTag = QCoeArticleTag.coeArticleTag;
		// do something
		BooleanExpression eq = qcoeArticleTag.deleted.isFalse();
		
		String name = coeArticleTag.getName();
		if(null != coeArticleTag) {
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(name).append("%");
				eq=eq.and(qcoeArticleTag.name.like(sb.toString()));
			}
		}
		return eq;

	}
	
	public static BooleanExpression getConditionWithNotself(final CoeArticleTag coeArticleTag) {

		QCoeArticleTag qcoeArticleTag = QCoeArticleTag.coeArticleTag;
		// do something
		BooleanExpression eq = qcoeArticleTag.deleted.isFalse();
		
		String name = coeArticleTag.getName();
		if(null != coeArticleTag) {
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(name).append("%");
				eq=eq.and(qcoeArticleTag.name.like(sb.toString()));
			}
			Long mainId = coeArticleTag.getMainId();
			if(null != mainId) {
				eq=eq.and(qcoeArticleTag.mainId.ne(mainId));
			}
		}
		return eq;

	}
}
