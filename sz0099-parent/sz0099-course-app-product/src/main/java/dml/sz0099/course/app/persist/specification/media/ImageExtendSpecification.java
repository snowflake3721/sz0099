package dml.sz0099.course.app.persist.specification.media;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.media.ImageExtend;
import dml.sz0099.course.app.persist.entity.media.QImageExtend;

/**
 * 
 * ImageExtendSpecification 条件构造
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
 * description: ImageExtendSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ImageExtendSpecification {

	private ImageExtendSpecification() {
		throw new IllegalStateException("ImageExtendSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ImageExtend> getConditionByCode(final ImageExtend imageExtend) {
		return new Specification<ImageExtend>() {
			public Predicate toPredicate(Root<ImageExtend> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param imageExtend
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ImageExtend imageExtend) {

		QImageExtend qimageExtend = QImageExtend.imageExtend;

		BooleanExpression eq = qimageExtend.deleted.isFalse();
		// do something
		return eq;

	}
}
