package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivityPosition;

/**
 * 
 * CoeActivityPositionSpecification 条件构造
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
 * description: CoeActivityPositionSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeActivityPositionSpecification {

	private CoeActivityPositionSpecification() {
		throw new IllegalStateException("CoeActivityPositionSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeActivityPosition> getConditionByCode(final CoeActivityPosition coeActivityPosition) {
		return new Specification<CoeActivityPosition>() {
			public Predicate toPredicate(Root<CoeActivityPosition> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeActivityPosition
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeActivityPosition coeActivityPosition) {

		QCoeActivityPosition qcoeActivityPosition = QCoeActivityPosition.coeActivityPosition;

		BooleanExpression eq = qcoeActivityPosition.deleted.isFalse();
		// do something
		return eq;

	}
}
