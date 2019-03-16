package dml.sz0099.course.app.persist.specification.position;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.position.QPosition;

/**
 * 
 * PositionSpecification 条件构造
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
 * description: PositionSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class PositionSpecification {

	private PositionSpecification() {
		throw new IllegalStateException("PositionSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<Position> getConditionByCode(final Position position) {
		return new Specification<Position>() {
			public Predicate toPredicate(Root<Position> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param position
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final Position position) {

		QPosition qposition = QPosition.position;

		BooleanExpression eq = qposition.deleted.isFalse();
		// do something
		return eq;

	}
}
