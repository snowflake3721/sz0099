package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPositionImage;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivityPositionImage;

/**
 * 
 * CoeActivityPositionImageSpecification 条件构造
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
 * description: CoeActivityPositionImageSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeActivityPositionImageSpecification {

	private CoeActivityPositionImageSpecification() {
		throw new IllegalStateException("CoeActivityPositionImageSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeActivityPositionImage> getConditionByCode(final CoeActivityPositionImage coeActivityPositionImage) {
		return new Specification<CoeActivityPositionImage>() {
			public Predicate toPredicate(Root<CoeActivityPositionImage> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeActivityPositionImage
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeActivityPositionImage coeActivityPositionImage) {

		QCoeActivityPositionImage qcoeActivityPositionImage = QCoeActivityPositionImage.coeActivityPositionImage;

		BooleanExpression eq = qcoeActivityPositionImage.deleted.isFalse();
		// do something
		return eq;

	}
}
