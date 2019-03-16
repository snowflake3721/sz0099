package dml.sz0099.course.app.persist.specification.user;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.QCoeUser;

/**
 * 
 * CoeUserSpecification 条件构造
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
 * description: CoeUserSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeUserSpecification {

	private CoeUserSpecification() {
		throw new IllegalStateException("CoeUserSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeUser> getConditionByCode(final CoeUser coeUser) {
		return new Specification<CoeUser>() {
			public Predicate toPredicate(Root<CoeUser> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeUser
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeUser coeUser) {

		QCoeUser qcoeUser = QCoeUser.coeUser;

		BooleanExpression eq = qcoeUser.deleted.isFalse();
		// do something
		return eq;

	}
	
	
}
