package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionCover;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivityPositionCover;

/**
 * 
 * CoeActivityPositionCoverSpecification 条件构造
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
 * description: CoeActivityPositionCoverSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeActivityPositionCoverSpecification {

	private CoeActivityPositionCoverSpecification() {
		throw new IllegalStateException("CoeActivityPositionCoverSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeActivityPositionCover> getConditionByCode(final CoeActivityPositionCover coeActivityPositionCover) {
		return new Specification<CoeActivityPositionCover>() {
			public Predicate toPredicate(Root<CoeActivityPositionCover> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeActivityPositionCover
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeActivityPositionCover coeActivityPositionCover) {

		QCoeActivityPositionCover qcoeActivityPositionCover = QCoeActivityPositionCover.coeActivityPositionCover;

		BooleanExpression eq = qcoeActivityPositionCover.deleted.isFalse();
		// do something
		return eq;

	}
}
