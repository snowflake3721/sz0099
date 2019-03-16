package dml.sz0099.course.app.persist.specification.position;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.position.PositionExtendContent;
import dml.sz0099.course.app.persist.entity.position.QPositionExtendContent;

/**
 * 
 * PositionExtendContentSpecification 条件构造
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
 * description: PositionExtendContentSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class PositionExtendContentSpecification {

	private PositionExtendContentSpecification() {
		throw new IllegalStateException("PositionExtendContentSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<PositionExtendContent> getConditionByCode(final PositionExtendContent positionExtendContent) {
		return new Specification<PositionExtendContent>() {
			public Predicate toPredicate(Root<PositionExtendContent> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param positionExtendContent
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final PositionExtendContent positionExtendContent) {

		QPositionExtendContent qpositionExtendContent = QPositionExtendContent.positionExtendContent;

		BooleanExpression eq = qpositionExtendContent.deleted.isFalse();
		// do something
		return eq;

	}
}
