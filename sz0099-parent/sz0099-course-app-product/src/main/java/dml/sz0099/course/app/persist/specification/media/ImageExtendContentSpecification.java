package dml.sz0099.course.app.persist.specification.media;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.media.ImageExtendContent;
import dml.sz0099.course.app.persist.entity.media.QImageExtendContent;

/**
 * 
 * ImageExtendContentSpecification 条件构造
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
 * description: ImageExtendContentSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ImageExtendContentSpecification {

	private ImageExtendContentSpecification() {
		throw new IllegalStateException("ImageExtendContentSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ImageExtendContent> getConditionByCode(final ImageExtendContent imageExtendContent) {
		return new Specification<ImageExtendContent>() {
			public Predicate toPredicate(Root<ImageExtendContent> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param imageExtendContent
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ImageExtendContent imageExtendContent) {

		QImageExtendContent qimageExtendContent = QImageExtendContent.imageExtendContent;

		BooleanExpression eq = qimageExtendContent.deleted.isFalse();
		// do something
		return eq;

	}
}
