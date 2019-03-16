package dml.sz0099.course.app.persist.specification.article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.article.PhotoBanner;
import dml.sz0099.course.app.persist.entity.article.QPhotoBanner;

/**
 * 
 * PhotoBannerSpecification 条件构造
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
 * description: PhotoBannerSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class PhotoBannerSpecification {

	private PhotoBannerSpecification() {
		throw new IllegalStateException("PhotoBannerSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<PhotoBanner> getConditionByCode(final PhotoBanner photoBanner) {
		return new Specification<PhotoBanner>() {
			public Predicate toPredicate(Root<PhotoBanner> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param photoBanner
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final PhotoBanner photoBanner) {

		QPhotoBanner qphotoBanner = QPhotoBanner.photoBanner;

		BooleanExpression eq = qphotoBanner.deleted.isFalse();
		// do something
		return eq;

	}
}
