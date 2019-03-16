package dml.sz0099.course.app.persist.specification.media;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.media.ImageExtendLog;
import dml.sz0099.course.app.persist.entity.media.QImageExtendLog;

/**
 * 
 * ImageExtendLogSpecification 条件构造
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
 * description: ImageExtendLogSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ImageExtendLogSpecification {

	private ImageExtendLogSpecification() {
		throw new IllegalStateException("ImageExtendLogSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ImageExtendLog> getConditionByCode(final ImageExtendLog imageExtendLog) {
		return new Specification<ImageExtendLog>() {
			public Predicate toPredicate(Root<ImageExtendLog> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param imageExtendLog
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ImageExtendLog imageExtendLog) {

		QImageExtendLog qimageExtendLog = QImageExtendLog.imageExtendLog;

		BooleanExpression eq = qimageExtendLog.deleted.isFalse();
		// do something
		return eq;

	}
}
