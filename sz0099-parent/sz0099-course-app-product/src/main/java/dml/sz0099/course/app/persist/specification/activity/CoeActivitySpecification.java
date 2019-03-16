package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivity;

/**
 * 
 * CoeActivitySpecification 条件构造
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
 * description: CoeActivitySpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeActivitySpecification {

	private CoeActivitySpecification() {
		throw new IllegalStateException("CoeActivitySpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeActivity> getConditionByCode(final CoeActivity coeActivity) {
		return new Specification<CoeActivity>() {
			public Predicate toPredicate(Root<CoeActivity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeActivity
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeActivity coeActivity) {

		QCoeActivity qcoeActivity = QCoeActivity.coeActivity;

		BooleanExpression eq = qcoeActivity.deleted.isFalse();
		// do something
		return eq;

	}
	
	public static BooleanExpression getConditionByCodeWithQslExpression(final CoeActivity coeActivity) {

		QCoeActivity qcoeActivity = QCoeActivity.coeActivity;
		
		BooleanExpression eq = qcoeActivity.publishStatus.eq(CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt())
				.and(qcoeActivity.deleted.isFalse());
				
		if(null != coeActivity) {
			String title = coeActivity.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eq=eq.and(qcoeActivity.titleLower.like(sb.toString()));
			}
			
			String name = coeActivity.getName();
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(name)).append("%");
				eq=eq.and(qcoeActivity.name.like(sb.toString()));
			}
			
			String activityNo = coeActivity.getActivityNo();
			if(StringUtils.isNotBlank(activityNo)) {
				eq = eq.and(qcoeActivity.activityNo.eq(activityNo));
			}
			Integer recommend = coeActivity.getRecommend();
			if(null != recommend && !CoeActivity.RECOMMEND_ALL.getValueInt().equals(recommend)) {
				eq = eq.and(qcoeActivity.recommend.eq(recommend));
			}
		}
		
		return eq;

	}
	
	
	public static BooleanExpression getConditionForPublish(final CoeActivity coeActivity) {

		QCoeActivity qcoeActivity = QCoeActivity.coeActivity;
		
		BooleanExpression eq = qcoeActivity.publishStatus.eq(coeActivity.getPublishStatus())
				.and(qcoeActivity.deleted.isFalse());
				
		BooleanExpression eqTitle = null;
		String title = coeActivity.getTitle();
		if(StringUtils.isNotBlank(title)) {
			StringBuilder sb = new StringBuilder();
			sb.append("%").append(StringUtils.lowerCase(title)).append("%");
			eqTitle=(qcoeActivity.titleLower.like(sb.toString()));
		}
		
		String name = coeActivity.getName();
		if(StringUtils.isNotBlank(name)) {
			StringBuilder sb = new StringBuilder();
			sb.append("%").append(StringUtils.lowerCase(name)).append("%");
			if(eqTitle != null) {
				eqTitle=eqTitle.or(qcoeActivity.name.like(sb.toString()));
			}else {
				eqTitle=qcoeActivity.name.like(sb.toString());
			}
		}
		
		if(null != eqTitle) {
			eq = eq.and(eqTitle);
		}
		
		Long userId = coeActivity.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeActivity.userId.eq(userId));
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionForPublishNotSelf(final CoeActivity coeActivity) {

		QCoeActivity qcoeActivity = QCoeActivity.coeActivity;
		
		BooleanExpression eq = qcoeActivity.publishStatus.eq(CoeActivity.PUBLISH_STATUS_PUBLISH.getValueInt())
				.and(qcoeActivity.deleted.isFalse());
		
		if(null != coeActivity) {
			BooleanExpression eqTitle = null;
			String title = coeActivity.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eqTitle=(qcoeActivity.titleLower.like(sb.toString()));
			}
			
			String name = coeActivity.getName();
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(name)).append("%");
				if(eqTitle != null) {
					eqTitle=eqTitle.or(qcoeActivity.name.like(sb.toString()));
				}else {
					eqTitle=qcoeActivity.name.like(sb.toString());
				}
			}
			
			String activityNo = coeActivity.getActivityNo();
			if(StringUtils.isNotBlank(activityNo)) {
				eq = eq.and(qcoeActivity.activityNo.eq(activityNo));
			}
			
			if(null != eqTitle) {
				eq = eq.and(eqTitle);
			}
			
			Long userId = coeActivity.getUserId();
			if(null != userId) {
				eq=eq.and(qcoeActivity.userId.ne(userId));
			}
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionByUserId(final CoeActivity coeActivity) {

		QCoeActivity qcoeActivity = QCoeActivity.coeActivity;
		
		BooleanExpression eq = qcoeActivity.publishStatus.eq(coeActivity.getPublishStatus())
				.and(qcoeActivity.deleted.isFalse());
		Long userId = coeActivity.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeActivity.userId.eq(userId));
		}
		return eq;
	}
	
}
