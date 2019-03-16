package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;
import dml.sz0099.course.app.persist.entity.profession.QProfessionPosition;

/**
 * 
 * ProfessionPositionSpecification 条件构造
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
 * description: ProfessionPositionSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ProfessionPositionSpecification {

	private ProfessionPositionSpecification() {
		throw new IllegalStateException("ProfessionPositionSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ProfessionPosition> getConditionByCode(final ProfessionPosition professionPosition) {
		return new Specification<ProfessionPosition>() {
			public Predicate toPredicate(Root<ProfessionPosition> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param professionPosition
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ProfessionPosition professionPosition) {

		QProfessionPosition qprofessionPosition = QProfessionPosition.professionPosition;

		BooleanExpression eq = qprofessionPosition.deleted.isFalse();
		// do something
		return eq;

	}
}
