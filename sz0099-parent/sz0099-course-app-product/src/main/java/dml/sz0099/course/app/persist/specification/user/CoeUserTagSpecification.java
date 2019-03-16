package dml.sz0099.course.app.persist.specification.user;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.user.CoeUserTag;
import dml.sz0099.course.app.persist.entity.user.QCoeUserTag;

/**
 * 
 * CoeUserTagSpecification 条件构造
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
 * description: CoeUserTagSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeUserTagSpecification {

	private CoeUserTagSpecification() {
		throw new IllegalStateException("CoeUserTagSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeUserTag> getConditionByCode(final CoeUserTag coeUserTag) {
		return new Specification<CoeUserTag>() {
			public Predicate toPredicate(Root<CoeUserTag> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeUserTag
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeUserTag coeUserTag) {

		QCoeUserTag qcoeUserTag = QCoeUserTag.coeUserTag;

		BooleanExpression eq = qcoeUserTag.deleted.isFalse();
		// do something
		return eq;

	}
}
