package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrderLog;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivityOrderLog;

/**
 * 
 * CoeActivityOrderLogSpecification 条件构造
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
 * description: CoeActivityOrderLogSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeActivityOrderLogSpecification {

	private CoeActivityOrderLogSpecification() {
		throw new IllegalStateException("CoeActivityOrderLogSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeActivityOrderLog> getConditionByCode(final CoeActivityOrderLog coeActivityOrderLog) {
		return new Specification<CoeActivityOrderLog>() {
			public Predicate toPredicate(Root<CoeActivityOrderLog> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeActivityOrderLog
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeActivityOrderLog coeActivityOrderLog) {

		QCoeActivityOrderLog qcoeActivityOrderLog = QCoeActivityOrderLog.coeActivityOrderLog;

		BooleanExpression eq = qcoeActivityOrderLog.deleted.isFalse();
		// do something
		return eq;

	}
	
	public static BooleanExpression getConditionByCodeWithQslExpression(final CoeActivityOrderLog coeActivityOrderLog) {

		QCoeActivityOrderLog qcoeActivityOrderLog = QCoeActivityOrderLog.coeActivityOrderLog;
		
		BooleanExpression eq =
				qcoeActivityOrderLog.deleted.isFalse();
				
		if(null != coeActivityOrderLog) {
			String title = coeActivityOrderLog.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eq=eq.and(qcoeActivityOrderLog.titleLower.like(sb.toString()));
			}
			
			String activityNo = coeActivityOrderLog.getActivityNo();
			if(StringUtils.isNotBlank(activityNo)) {
				eq = eq.and(qcoeActivityOrderLog.activityNo.eq(activityNo));
			}
		}
		
		return eq;

	}
	
	
	public static BooleanExpression getConditionForPublish(final CoeActivityOrderLog coeActivityOrderLog) {

		QCoeActivityOrderLog qcoeActivityOrderLog = QCoeActivityOrderLog.coeActivityOrderLog;
		
		BooleanExpression eq = qcoeActivityOrderLog.deleted.isFalse();
				
		BooleanExpression eqTitle = null;
		String title = coeActivityOrderLog.getTitle();
		if(StringUtils.isNotBlank(title)) {
			StringBuilder sb = new StringBuilder();
			sb.append("%").append(StringUtils.lowerCase(title)).append("%");
			eqTitle=(qcoeActivityOrderLog.titleLower.like(sb.toString()));
		}
		
		if(null != eqTitle) {
			eq = eq.and(eqTitle);
		}
		
		Long userId = coeActivityOrderLog.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeActivityOrderLog.userId.eq(userId));
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionForPublishNotSelf(final CoeActivityOrderLog coeActivityOrderLog) {

		QCoeActivityOrderLog qcoeActivityOrderLog = QCoeActivityOrderLog.coeActivityOrderLog;
		
		BooleanExpression eq = qcoeActivityOrderLog.deleted.isFalse();
		
		if(null != coeActivityOrderLog) {
			BooleanExpression eqTitle = null;
			String title = coeActivityOrderLog.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eqTitle=(qcoeActivityOrderLog.titleLower.like(sb.toString()));
			}
			
			String activityNo = coeActivityOrderLog.getActivityNo();
			if(StringUtils.isNotBlank(activityNo)) {
				eq = eq.and(qcoeActivityOrderLog.activityNo.eq(activityNo));
			}
			
			if(null != eqTitle) {
				eq = eq.and(eqTitle);
			}
			
			Long userId = coeActivityOrderLog.getUserId();
			if(null != userId) {
				eq=eq.and(qcoeActivityOrderLog.userId.ne(userId));
			}
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionByUserId(final CoeActivityOrderLog coeActivityOrderLog) {

		QCoeActivityOrderLog qcoeActivityOrderLog = QCoeActivityOrderLog.coeActivityOrderLog;
		
		BooleanExpression eq = qcoeActivityOrderLog.deleted.isFalse();
		Long userId = coeActivityOrderLog.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeActivityOrderLog.userId.eq(userId));
		}
		return eq;
	}
	
}
