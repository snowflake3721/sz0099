package dml.sz0099.course.app.persist.specification.article;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;
import dml.sz0099.course.app.persist.entity.article.QCoeArticle;
import dml.sz0099.course.app.persist.entity.article.QCoeCategArticle;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.QCategory;

/**
 * 
 * CoeCategArticleSpecification 条件构造
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
 * description: CoeCategArticleSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeCategArticleSpecification {

	private CoeCategArticleSpecification() {
		throw new IllegalStateException("CoeCategArticleSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeCategArticle> getConditionByCode(final CoeCategArticle coeCategArticle) {
		return new Specification<CoeCategArticle>() {
			public Predicate toPredicate(Root<CoeCategArticle> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeCategArticle
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeCategArticle coeCategArticle, final List<Long> excludeMainIdList) {

		QCoeCategArticle qcoeCategArticle = QCoeCategArticle.coeCategArticle;
		
		QCoeArticle qcoeArticle = qcoeCategArticle.article;
		
		QCategory qcategory = qcoeCategArticle.category;

		BooleanExpression eq = qcoeCategArticle.deleted.isFalse();
		
		Category category = coeCategArticle.getCategory();
		if(null != category) {
			String code = category.getCode();
			eq=eq.and(qcoeCategArticle.baseId.eq(qcategory.id));
			if(StringUtils.isNotBlank(code)) {
				eq=eq.and(qcategory.code.eq(code));
			}
		}
		
		Long baseId = coeCategArticle.getBaseId();
		if(null != baseId) {
			eq=eq.and(qcoeCategArticle.baseId.eq(baseId));
		}
		
		if(null != excludeMainIdList && !excludeMainIdList.isEmpty()) {
			eq=eq.and(qcategory.mainId.notIn(excludeMainIdList));
		}
		
		CoeArticle coeArticle = coeCategArticle.getArticle();
		if(null != coeArticle) {
			eq.and(qcoeCategArticle.mainId.eq(qcoeArticle.id));
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
			
			Integer publishStatus = coeArticle.getPublishStatus();
			if(null != publishStatus) {
				eq = eq.and(qcoeArticle.publishStatus.eq(publishStatus));
			}
		}
		
		Long userId = coeArticle.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeArticle.userId.eq(userId));
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionFromDetail(final CoeCategArticle coeCategArticle) {

		QCoeCategArticle qcoeCategArticle = QCoeCategArticle.coeCategArticle;

		QCoeArticle qcoeArticle = qcoeCategArticle.article;
		
		QCategory qcategory = qcoeCategArticle.category;

		BooleanExpression eq = qcoeArticle.deleted.isFalse(). and (qcoeArticle.disable.isFalse());
		
		Category category = coeCategArticle.getCategory();
		if(null != category) {
			String code = category.getCode();
			eq=eq.and(qcoeCategArticle.baseId.eq(qcategory.id));
			if(StringUtils.isNotBlank(code)) {
				eq=eq.and(qcategory.code.eq(code));
			}
		}
		
		Long baseId = coeCategArticle.getBaseId();
		if(null != baseId) {
			eq=eq.and(qcoeCategArticle.baseId.eq(baseId));
		}
		
		CoeArticle coeArticle = coeCategArticle.getArticle();
		if(null != coeArticle) {
			eq.and(qcoeCategArticle.mainId.eq(qcoeArticle.id));
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
			
			String coeArticleNo = coeArticle.getArticleNo();
			if(StringUtils.isNotBlank(coeArticleNo)) {
				eq = eq.and(qcoeArticle.articleNo.eq(coeArticleNo));
			}
			
			Integer publishStatus = coeArticle.getPublishStatus();
			if(null != publishStatus) {
				eq = eq.and(qcoeArticle.publishStatus.eq(publishStatus));
			}
		}
		
		//从detail跳转至搜索页，过滤掉用户本身的技能
		Long userId = coeArticle.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeArticle.userId.ne(userId));
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionWithChilren(final CoeCategArticle coeCategArticle, final List<Long> baseIdList, final List<Long> excludeMainIdList) {

		QCoeCategArticle qcoeCategArticle = QCoeCategArticle.coeCategArticle;
		
		QCoeArticle qcoeArticle = qcoeCategArticle.article;
		
		QCategory qcategory = qcoeCategArticle.category;

		BooleanExpression eq = qcoeCategArticle.deleted.isFalse(). and (qcoeArticle.disable.isFalse()). and (qcoeArticle.deleted.isFalse());
		
		Long baseId = coeCategArticle.getBaseId();
		if(null != baseIdList && !baseIdList.isEmpty()) {
			eq=eq.and(qcoeCategArticle.baseId.in(baseIdList));
		}else if(null != baseId) {
			eq=eq.and(qcoeCategArticle.baseId.eq(baseId));
		}else {
			Category category = coeCategArticle.getCategory();
			if(null != category) {
				String code = category.getCode();
				eq=eq.and(qcoeCategArticle.baseId.eq(qcategory.id));
				if(StringUtils.isNotBlank(code)) {
					eq=eq.and(qcategory.code.eq(code));
				}
			}
		}
		if(null != excludeMainIdList && !excludeMainIdList.isEmpty()) {
			eq=eq.and(qcoeCategArticle.mainId.notIn(excludeMainIdList));
		}
		
		CoeArticle coeArticle = coeCategArticle.getArticle();
		if(null != coeArticle) {
			eq.and(qcoeCategArticle.mainId.eq(qcoeArticle.id));
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
			
			Integer publishStatus = coeArticle.getPublishStatus();
			if(null != publishStatus) {
				eq = eq.and(qcoeArticle.publishStatus.eq(publishStatus));
			}
		}
		
		Long userId = coeArticle.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeArticle.userId.eq(userId));
		}
		
		return eq;

	}
}
