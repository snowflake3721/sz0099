package dml.sz0099.course.app.persist.specification.paragraph;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.paragraph.PhotoParag;
import dml.sz0099.course.app.persist.entity.paragraph.QPhotoParag;

/**
 * 
 * PhotoParagSpecification 条件构造
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
 * description: PhotoParagSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class PhotoParagSpecification {

	private PhotoParagSpecification() {
		throw new IllegalStateException("PhotoParagSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<PhotoParag> getConditionByCode(final PhotoParag photoParag) {
		return new Specification<PhotoParag>() {
			public Predicate toPredicate(Root<PhotoParag> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param photoParag
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final PhotoParag photoParag) {

		QPhotoParag qphotoParag = QPhotoParag.photoParag;

		BooleanExpression eq = qphotoParag.deleted.isFalse();
		// do something
		return eq;

	}
}
