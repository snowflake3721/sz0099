package dml.sz0099.course.app.persist.specification.position;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.position.PositionCover;
import dml.sz0099.course.app.persist.entity.position.QPositionCover;

/**
 * 
 * PositionCoverSpecification 条件构造
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
 * description: PositionCoverSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class PositionCoverSpecification {

	private PositionCoverSpecification() {
		throw new IllegalStateException("PositionCoverSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<PositionCover> getConditionByCode(final PositionCover positionCover) {
		return new Specification<PositionCover>() {
			public Predicate toPredicate(Root<PositionCover> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param positionCover
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final PositionCover positionCover) {

		QPositionCover qpositionCover = QPositionCover.positionCover;

		BooleanExpression eq = qpositionCover.deleted.isFalse();
		// do something
		return eq;

	}
}
