package dml.sz0099.course.app.persist.specification.user;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.user.CoeUserImage;
import dml.sz0099.course.app.persist.entity.user.QCoeUserImage;

/**
 * 
 * CoeUserImageSpecification 条件构造
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
 * description: CoeUserImageSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeUserImageSpecification {

	private CoeUserImageSpecification() {
		throw new IllegalStateException("CoeUserImageSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeUserImage> getConditionByCode(final CoeUserImage coeUserImage) {
		return new Specification<CoeUserImage>() {
			public Predicate toPredicate(Root<CoeUserImage> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeUserImage
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeUserImage coeUserImage) {

		QCoeUserImage qcoeUserImage = QCoeUserImage.coeUserImage;

		BooleanExpression eq = qcoeUserImage.deleted.isFalse();
		// do something
		return eq;

	}
}
