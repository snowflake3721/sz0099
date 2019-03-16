package dml.sz0099.course.app.persist.specification.article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.PhotoCover;
import dml.sz0099.course.app.persist.entity.article.QPhotoCover;

/**
 * 
 * PhotoCoverSpecification 条件构造
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
 * description: PhotoCoverSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class PhotoCoverSpecification {

	private PhotoCoverSpecification() {
		throw new IllegalStateException("PhotoCoverSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<PhotoCover> getConditionByCode(final PhotoCover photoCover) {
		return new Specification<PhotoCover>() {
			public Predicate toPredicate(Root<PhotoCover> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param photoCover
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final PhotoCover photoCover) {

		QPhotoCover qphotoCover = QPhotoCover.photoCover;

		BooleanExpression eq = qphotoCover.deleted.isFalse();
		// do something
		return eq;

	}
}
