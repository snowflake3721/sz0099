package dml.sz0099.course.app.persist.specification.position;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.position.PositionExtend;
import dml.sz0099.course.app.persist.entity.position.QPositionExtend;

/**
 * 
 * PositionExtendSpecification 条件构造
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
 * description: PositionExtendSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class PositionExtendSpecification {

	private PositionExtendSpecification() {
		throw new IllegalStateException("PositionExtendSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<PositionExtend> getConditionByCode(final PositionExtend positionExtend) {
		return new Specification<PositionExtend>() {
			public Predicate toPredicate(Root<PositionExtend> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param positionExtend
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final PositionExtend positionExtend) {

		QPositionExtend qpositionExtend = QPositionExtend.positionExtend;

		BooleanExpression eq = qpositionExtend.deleted.isFalse();
		// do something
		return eq;

	}
}
