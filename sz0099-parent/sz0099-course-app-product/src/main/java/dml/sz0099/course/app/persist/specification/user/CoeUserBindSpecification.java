package dml.sz0099.course.app.persist.specification.user;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.user.CoeUserBind;
import dml.sz0099.course.app.persist.entity.user.QCoeUserBind;

/**
 * 
 * CoeUserBindSpecification 条件构造
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
 * description: CoeUserBindSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeUserBindSpecification {

	private CoeUserBindSpecification() {
		throw new IllegalStateException("CoeUserBindSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeUserBind> getConditionByCode(final CoeUserBind coeUserBind) {
		return new Specification<CoeUserBind>() {
			public Predicate toPredicate(Root<CoeUserBind> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeUserBind
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeUserBind coeUserBind) {

		QCoeUserBind qcoeUserBind = QCoeUserBind.coeUserBind;

		BooleanExpression eq = qcoeUserBind.deleted.isFalse();
		// do something
		
		if(null != coeUserBind) {
			Integer status = coeUserBind.getStatus();
			
			Long userId = coeUserBind.getUserId();
			
			if(null != status) {
				eq=eq.and(qcoeUserBind.status.eq(status));
			}
			
			if(null != userId) {
				eq=eq.and(qcoeUserBind.userId.eq(userId));
			}
		}
		
		return eq;

	}
}
