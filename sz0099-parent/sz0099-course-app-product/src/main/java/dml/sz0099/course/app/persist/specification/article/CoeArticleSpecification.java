package dml.sz0099.course.app.persist.specification.article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.QCoeArticle;

/**
 * 
 * CoeArticleSpecification 条件构造
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
 * description: CoeArticleSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeArticleSpecification {

	private CoeArticleSpecification() {
		throw new IllegalStateException("CoeArticleSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeArticle> getConditionByCode(final CoeArticle coeArticle) {
		return new Specification<CoeArticle>() {
			public Predicate toPredicate(Root<CoeArticle> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeArticle
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeArticle coeArticle) {

		QCoeArticle qcoeArticle = QCoeArticle.coeArticle;

		BooleanExpression eq = qcoeArticle.deleted.isFalse();
		// do something
		return eq;

	}
	
	public static BooleanExpression getConditionByCodeWithQslExpression(final CoeArticle coeArticle) {

		QCoeArticle qcoeArticle = QCoeArticle.coeArticle;
		
		BooleanExpression eq = qcoeArticle.publishStatus.eq(CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt())
				.and(qcoeArticle.deleted.isFalse());
				
		if(null != coeArticle) {
			String title = coeArticle.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eq=eq.and(qcoeArticle.titleLower.like(sb.toString()));
			}
			
			String name = coeArticle.getName();
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(name)).append("%");
				eq=eq.and(qcoeArticle.name.like(sb.toString()));
			}
			
			String articleNo = coeArticle.getArticleNo();
			if(StringUtils.isNotBlank(articleNo)) {
				eq = eq.and(qcoeArticle.articleNo.eq(articleNo));
			}
			Integer recommend = coeArticle.getRecommend();
			if(null != recommend && !CoeArticle.RECOMMEND_ALL.getValueInt().equals(recommend)) {
				eq = eq.and(qcoeArticle.recommend.eq(recommend));
			}
		}
		
		return eq;

	}
	
	
	public static BooleanExpression getConditionForPublish(final CoeArticle coeArticle) {

		QCoeArticle qcoeArticle = QCoeArticle.coeArticle;
		
		BooleanExpression eq = qcoeArticle.publishStatus.eq(coeArticle.getPublishStatus())
				.and(qcoeArticle.deleted.isFalse());
				
		BooleanExpression eqTitle = null;
		String title = coeArticle.getTitle();
		if(StringUtils.isNotBlank(title)) {
			StringBuilder sb = new StringBuilder();
			sb.append("%").append(StringUtils.lowerCase(title)).append("%");
			eqTitle=(qcoeArticle.titleLower.like(sb.toString()));
		}
		
		String name = coeArticle.getName();
		if(StringUtils.isNotBlank(name)) {
			StringBuilder sb = new StringBuilder();
			sb.append("%").append(StringUtils.lowerCase(name)).append("%");
			if(eqTitle != null) {
				eqTitle=eqTitle.or(qcoeArticle.name.like(sb.toString()));
			}else {
				eqTitle=qcoeArticle.name.like(sb.toString());
			}
		}
		
		if(null != eqTitle) {
			eq = eq.and(eqTitle);
		}
		
		Long userId = coeArticle.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeArticle.userId.eq(userId));
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionForPublishNotSelf(final CoeArticle coeArticle) {

		QCoeArticle qcoeArticle = QCoeArticle.coeArticle;
		
		BooleanExpression eq = qcoeArticle.publishStatus.eq(CoeArticle.PUBLISH_STATUS_PUBLISH.getValueInt())
				.and(qcoeArticle.deleted.isFalse());
		
		if(null != coeArticle) {
			BooleanExpression eqTitle = null;
			String title = coeArticle.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eqTitle=(qcoeArticle.titleLower.like(sb.toString()));
			}
			
			String name = coeArticle.getName();
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(name)).append("%");
				if(eqTitle != null) {
					eqTitle=eqTitle.or(qcoeArticle.name.like(sb.toString()));
				}else {
					eqTitle=qcoeArticle.name.like(sb.toString());
				}
			}
			
			String articleNo = coeArticle.getArticleNo();
			if(StringUtils.isNotBlank(articleNo)) {
				eq = eq.and(qcoeArticle.articleNo.eq(articleNo));
			}
			
			if(null != eqTitle) {
				eq = eq.and(eqTitle);
			}
			
			Long userId = coeArticle.getUserId();
			if(null != userId) {
				eq=eq.and(qcoeArticle.userId.ne(userId));
			}
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionByUserId(final CoeArticle coeArticle) {

		QCoeArticle qcoeArticle = QCoeArticle.coeArticle;
		
		BooleanExpression eq = qcoeArticle.publishStatus.eq(coeArticle.getPublishStatus())
				.and(qcoeArticle.deleted.isFalse());
		Long userId = coeArticle.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeArticle.userId.eq(userId));
		}
		return eq;
	}
	
}
