package dml.sz0099.course.app.persist.specification.media;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.media.ImageRef;
import dml.sz0099.course.app.persist.entity.media.QImageRef;

/**
 * 
 * ImageRefSpecification 条件构造
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
 * description: ImageRefSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ImageRefSpecification {

	private ImageRefSpecification() {
		throw new IllegalStateException("ImageRefSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ImageRef> getConditionByCode(final ImageRef imageRef) {
		return new Specification<ImageRef>() {
			public Predicate toPredicate(Root<ImageRef> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param imageRef
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ImageRef imageRef) {

		QImageRef qimageRef = QImageRef.imageRef;

		BooleanExpression eq = qimageRef.deleted.isFalse();
		// do something
		return eq;

	}
}
