package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.Photo;
import dml.sz0099.course.app.persist.entity.activity.QPhoto;

/**
 * 
 * PhotoSpecification 条件构造
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
 * description: PhotoSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class PhotoSpecification {

	private PhotoSpecification() {
		throw new IllegalStateException("PhotoSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<Photo> getConditionByCode(final Photo photo) {
		return new Specification<Photo>() {
			public Predicate toPredicate(Root<Photo> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param photo
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final Photo photo) {

		QPhoto qphoto = QPhoto.photo;

		BooleanExpression eq = qphoto.deleted.isFalse();
		// do something
		return eq;

	}
}
