package dml.sz0099.course.app.persist.specification.position;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.position.PositionImage;
import dml.sz0099.course.app.persist.entity.position.QPositionImage;

/**
 * 
 * PositionImageSpecification 条件构造
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
 * description: PositionImageSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class PositionImageSpecification {

	private PositionImageSpecification() {
		throw new IllegalStateException("PositionImageSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<PositionImage> getConditionByCode(final PositionImage positionImage) {
		return new Specification<PositionImage>() {
			public Predicate toPredicate(Root<PositionImage> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param positionImage
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final PositionImage positionImage) {

		QPositionImage qpositionImage = QPositionImage.positionImage;

		BooleanExpression eq = qpositionImage.deleted.isFalse();
		// do something
		return eq;

	}
}
