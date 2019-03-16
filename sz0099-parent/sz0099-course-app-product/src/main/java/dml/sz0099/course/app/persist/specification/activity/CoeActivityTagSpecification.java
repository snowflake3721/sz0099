package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityTag;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivity;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivityTag;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.tag.QCoeTag;

/**
 * 
 * CoeActivityTagSpecification 条件构造
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
 * description: CoeActivityTagSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeActivityTagSpecification {

	private CoeActivityTagSpecification() {
		throw new IllegalStateException("CoeActivityTagSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeActivityTag> getConditionByCode(final CoeActivityTag coeActivityTag) {
		return new Specification<CoeActivityTag>() {
			public Predicate toPredicate(Root<CoeActivityTag> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeActivityTag
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeActivityTag coeActivityTag) {

		QCoeActivityTag qcoeActivityTag = QCoeActivityTag.coeActivityTag;
		// do something
		BooleanExpression eq = qcoeActivityTag.deleted.isFalse();
		
		String name = coeActivityTag.getName();
		if(null != coeActivityTag) {
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(name).append("%");
				eq=eq.and(qcoeActivityTag.name.like(sb.toString()));
			}
		}
		return eq;

	}
	
	public static BooleanExpression getConditionWithNotself(final CoeActivityTag coeActivityTag) {

		QCoeActivityTag qcoeActivityTag = QCoeActivityTag.coeActivityTag;
		// do something
		BooleanExpression eq = qcoeActivityTag.deleted.isFalse();
		
		String name = coeActivityTag.getName();
		if(null != coeActivityTag) {
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(name).append("%");
				eq=eq.and(qcoeActivityTag.name.like(sb.toString()));
			}
			Long mainId = coeActivityTag.getMainId();
			if(null != mainId) {
				eq=eq.and(qcoeActivityTag.mainId.ne(mainId));
			}
		}
		return eq;

	}
}
