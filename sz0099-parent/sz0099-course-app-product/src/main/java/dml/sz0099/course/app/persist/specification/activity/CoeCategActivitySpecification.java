package dml.sz0099.course.app.persist.specification.activity;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivity;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivity;
import dml.sz0099.course.app.persist.entity.activity.QCoeCategActivity;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.QCategory;

/**
 * 
 * CoeCategActivitySpecification 条件构造
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
 * description: CoeCategActivitySpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeCategActivitySpecification {

	private CoeCategActivitySpecification() {
		throw new IllegalStateException("CoeCategActivitySpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeCategActivity> getConditionByCode(final CoeCategActivity coeCategActivity) {
		return new Specification<CoeCategActivity>() {
			public Predicate toPredicate(Root<CoeCategActivity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeCategActivity
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeCategActivity coeCategActivity, final List<Long> excludeMainIdList) {

		QCoeCategActivity qcoeCategActivity = QCoeCategActivity.coeCategActivity;
		
		QCoeActivity qcoeActivity = qcoeCategActivity.activity;
		
		QCategory qcategory = qcoeCategActivity.category;

		BooleanExpression eq = qcoeCategActivity.deleted.isFalse();
		
		Category category = coeCategActivity.getCategory();
		if(null != category) {
			String code = category.getCode();
			eq=eq.and(qcoeCategActivity.baseId.eq(qcategory.id));
			if(StringUtils.isNotBlank(code)) {
				eq=eq.and(qcategory.code.eq(code));
			}
		}
		
		Long baseId = coeCategActivity.getBaseId();
		if(null != baseId) {
			eq=eq.and(qcoeCategActivity.baseId.eq(baseId));
		}
		
		if(null != excludeMainIdList && !excludeMainIdList.isEmpty()) {
			eq=eq.and(qcategory.mainId.notIn(excludeMainIdList));
		}
		
		CoeActivity coeActivity = coeCategActivity.getActivity();
		if(null != coeActivity) {
			eq.and(qcoeCategActivity.mainId.eq(qcoeActivity.id));
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
			
			Integer publishStatus = coeActivity.getPublishStatus();
			if(null != publishStatus) {
				eq = eq.and(qcoeActivity.publishStatus.eq(publishStatus));
			}
		}
		
		Long userId = coeActivity.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeActivity.userId.eq(userId));
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionFromDetail(final CoeCategActivity coeCategActivity) {

		QCoeCategActivity qcoeCategActivity = QCoeCategActivity.coeCategActivity;

		QCoeActivity qcoeActivity = qcoeCategActivity.activity;
		
		QCategory qcategory = qcoeCategActivity.category;

		BooleanExpression eq = qcoeActivity.deleted.isFalse(). and (qcoeActivity.disable.isFalse());
		
		Category category = coeCategActivity.getCategory();
		if(null != category) {
			String code = category.getCode();
			eq=eq.and(qcoeCategActivity.baseId.eq(qcategory.id));
			if(StringUtils.isNotBlank(code)) {
				eq=eq.and(qcategory.code.eq(code));
			}
		}
		
		Long baseId = coeCategActivity.getBaseId();
		if(null != baseId) {
			eq=eq.and(qcoeCategActivity.baseId.eq(baseId));
		}
		
		CoeActivity coeActivity = coeCategActivity.getActivity();
		if(null != coeActivity) {
			eq.and(qcoeCategActivity.mainId.eq(qcoeActivity.id));
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
			
			String coeActivityNo = coeActivity.getActivityNo();
			if(StringUtils.isNotBlank(coeActivityNo)) {
				eq = eq.and(qcoeActivity.activityNo.eq(coeActivityNo));
			}
			
			Integer publishStatus = coeActivity.getPublishStatus();
			if(null != publishStatus) {
				eq = eq.and(qcoeActivity.publishStatus.eq(publishStatus));
			}
		}
		
		//从detail跳转至搜索页，过滤掉用户本身的技能
		Long userId = coeActivity.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeActivity.userId.ne(userId));
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionWithChilren(final CoeCategActivity coeCategActivity, final List<Long> baseIdList, final List<Long> excludeMainIdList) {

		QCoeCategActivity qcoeCategActivity = QCoeCategActivity.coeCategActivity;
		
		QCoeActivity qcoeActivity = qcoeCategActivity.activity;
		
		QCategory qcategory = qcoeCategActivity.category;

		BooleanExpression eq = qcoeCategActivity.deleted.isFalse(). and (qcoeActivity.disable.isFalse()). and (qcoeActivity.deleted.isFalse());
		
		Long baseId = coeCategActivity.getBaseId();
		if(null != baseIdList && !baseIdList.isEmpty()) {
			eq=eq.and(qcoeCategActivity.baseId.in(baseIdList));
		}else if(null != baseId) {
			eq=eq.and(qcoeCategActivity.baseId.eq(baseId));
		}else {
			Category category = coeCategActivity.getCategory();
			if(null != category) {
				String code = category.getCode();
				eq=eq.and(qcoeCategActivity.baseId.eq(qcategory.id));
				if(StringUtils.isNotBlank(code)) {
					eq=eq.and(qcategory.code.eq(code));
				}
			}
		}
		if(null != excludeMainIdList && !excludeMainIdList.isEmpty()) {
			eq=eq.and(qcoeCategActivity.mainId.notIn(excludeMainIdList));
		}
		
		CoeActivity coeActivity = coeCategActivity.getActivity();
		if(null != coeActivity) {
			eq.and(qcoeCategActivity.mainId.eq(qcoeActivity.id));
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
			
			Integer publishStatus = coeActivity.getPublishStatus();
			if(null != publishStatus) {
				eq = eq.and(qcoeActivity.publishStatus.eq(publishStatus));
			}
		}
		
		Long userId = coeActivity.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeActivity.userId.eq(userId));
		}
		
		return eq;

	}
}
