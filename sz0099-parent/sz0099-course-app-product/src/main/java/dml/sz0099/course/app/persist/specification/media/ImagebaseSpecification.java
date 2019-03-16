package dml.sz0099.course.app.persist.specification.media;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.media.Imagebase;
import dml.sz0099.course.app.persist.entity.media.QImagebase;

/**
 * 
 * ImagebaseSpecification 条件构造
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
 * description: ImagebaseSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ImagebaseSpecification {

	private ImagebaseSpecification() {
		throw new IllegalStateException("ImagebaseSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<Imagebase> getConditionByCode(final Imagebase imagebase) {
		return new Specification<Imagebase>() {
			public Predicate toPredicate(Root<Imagebase> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param imagebase
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final Imagebase imagebase) {

		QImagebase qimagebase = QImagebase.imagebase;

		BooleanExpression eq = qimagebase.deleted.isFalse();
		// do something
		return eq;

	}
}
