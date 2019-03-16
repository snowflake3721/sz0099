package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.CoeArticle;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.QProfession;

/**
 * 
 * ProfessionSpecification 条件构造
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
 * description: ProfessionSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ProfessionSpecification {

	private ProfessionSpecification() {
		throw new IllegalStateException("ProfessionSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<Profession> getConditionByCode(final Profession profession) {
		return new Specification<Profession>() {
			public Predicate toPredicate(Root<Profession> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param profession
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final Profession profession) {

		QProfession qprofession = QProfession.profession;
		
		BooleanExpression eq = qprofession.deleted.isFalse();
		
		if(null != profession) {
			BooleanExpression eqTitle = null;
			String title = profession.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eqTitle=(qprofession.titleLower.like(sb.toString()));
			}
			
			String name = profession.getName();
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(name)).append("%");
				if(eqTitle != null) {
					eqTitle=eqTitle.or(qprofession.name.like(sb.toString()));
				}else {
					eqTitle=qprofession.name.like(sb.toString());
				}
			}
			
			String articleNo = profession.getProfessionNo();
			if(StringUtils.isNotBlank(articleNo)) {
				eq = eq.and(qprofession.professionNo.eq(articleNo));
			}
			
			if(null != eqTitle) {
				eq = eq.and(eqTitle);
			}
			
			Long userId = profession.getUserId();
			if(null != userId) {
				eq=eq.and(qprofession.userId.eq(userId));
			}
			Integer recommend = profession.getRecommend();
			if(null != recommend && !Profession.RECOMMEND_ALL.getValueInt().equals(recommend)) {
				eq = eq.and(qprofession.recommend.eq(recommend));
			}
			
			Integer publishStatus = profession.getPublishStatus();
			if(null != publishStatus && !Profession.PUBLISH_STATUS_ALL.getValueInt().equals(publishStatus)) {
				eq.and(qprofession.publishStatus.eq(publishStatus));
			}
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionForPublish(final Profession profession) {

		QProfession qprofession = QProfession.profession;
		
		BooleanExpression eq = qprofession.publishStatus.eq(Profession.PUBLISH_STATUS_PUBLISH.getValueInt())
				.and(qprofession.deleted.isFalse());
		
		if(null != profession) {
			BooleanExpression eqTitle = null;
			String title = profession.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eqTitle=(qprofession.titleLower.like(sb.toString()));
			}
			
			String name = profession.getName();
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(name)).append("%");
				if(eqTitle != null) {
					eqTitle=eqTitle.or(qprofession.name.like(sb.toString()));
				}else {
					eqTitle=qprofession.name.like(sb.toString());
				}
			}
			
			String articleNo = profession.getProfessionNo();
			if(StringUtils.isNotBlank(articleNo)) {
				eq = eq.and(qprofession.professionNo.eq(articleNo));
			}
			
			if(null != eqTitle) {
				eq = eq.and(eqTitle);
			}
			
			Long userId = profession.getUserId();
			if(null != userId) {
				eq=eq.and(qprofession.userId.eq(userId));
			}
			Integer recommend = profession.getRecommend();
			if(null != recommend && !Profession.RECOMMEND_ALL.getValueInt().equals(recommend)) {
				eq = eq.and(qprofession.recommend.eq(recommend));
			}
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionForPublishNotSelf(final Profession profession) {

		QProfession qprofession = QProfession.profession;
		
		BooleanExpression eq = qprofession.publishStatus.eq(Profession.PUBLISH_STATUS_PUBLISH.getValueInt())
				.and(qprofession.deleted.isFalse());
		
		if(null != profession) {
			BooleanExpression eqTitle = null;
			String title = profession.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eqTitle=(qprofession.titleLower.like(sb.toString()));
			}
			
			String name = profession.getName();
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(name)).append("%");
				if(eqTitle != null) {
					eqTitle=eqTitle.or(qprofession.name.like(sb.toString()));
				}else {
					eqTitle=qprofession.name.like(sb.toString());
				}
			}
			
			String articleNo = profession.getProfessionNo();
			if(StringUtils.isNotBlank(articleNo)) {
				eq = eq.and(qprofession.professionNo.eq(articleNo));
			}
			
			if(null != eqTitle) {
				eq = eq.and(eqTitle);
			}
			
			Long userId = profession.getUserId();
			if(null != userId) {
				eq=eq.and(qprofession.userId.ne(userId));
			}
		}
		
		return eq;

	}
}
