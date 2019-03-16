package dml.sz0099.course.app.persist.specification.position;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.position.PositionExtendLog;
import dml.sz0099.course.app.persist.entity.position.QPositionExtendLog;

/**
 * 
 * PositionExtendLogSpecification 条件构造
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
 * description: PositionExtendLogSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class PositionExtendLogSpecification {

	private PositionExtendLogSpecification() {
		throw new IllegalStateException("PositionExtendLogSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<PositionExtendLog> getConditionByCode(final PositionExtendLog positionExtendLog) {
		return new Specification<PositionExtendLog>() {
			public Predicate toPredicate(Root<PositionExtendLog> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param positionExtendLog
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final PositionExtendLog positionExtendLog) {

		QPositionExtendLog qpositionExtendLog = QPositionExtendLog.positionExtendLog;

		BooleanExpression eq = qpositionExtendLog.deleted.isFalse();
		// do something
		return eq;

	}
}
