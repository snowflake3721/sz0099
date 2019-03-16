package dml.sz0099.course.app.persist.specification.activity;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityUser;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivityUser;

/**
 * 
 * CoeActivityUserSpecification 条件构造
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
 * description: CoeActivityUserSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeActivityUserSpecification {

	private CoeActivityUserSpecification() {
		throw new IllegalStateException("CoeActivityUserSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeActivityUser> getConditionByCode(final CoeActivityUser coeActivityUser) {
		return new Specification<CoeActivityUser>() {
			public Predicate toPredicate(Root<CoeActivityUser> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeActivityUser
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeActivityUser coeActivityUser) {

		QCoeActivityUser qcoeActivityUser = QCoeActivityUser.coeActivityUser;
		// do something
		BooleanExpression eq = qcoeActivityUser.deleted.isFalse();
		
		Long mainId = coeActivityUser.getMainId();
		if(null != mainId) {
			eq=eq.and(qcoeActivityUser.mainId.eq(mainId));
		}
		List<Integer> statusList = coeActivityUser.getStatusList();
		Integer status = coeActivityUser.getStatus();
		if(null != statusList && !statusList.isEmpty()) {
			eq=eq.and(qcoeActivityUser.status.in(statusList));
		}else if(null != status) {
			eq=eq.and(qcoeActivityUser.status.eq(status));
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionWithNotself(final CoeActivityUser coeActivityUser) {

		QCoeActivityUser qcoeActivityUser = QCoeActivityUser.coeActivityUser;
		// do something
		BooleanExpression eq = qcoeActivityUser.deleted.isFalse();
		
		if(null != coeActivityUser) {
			Long mainId = coeActivityUser.getMainId();
			if(null != mainId) {
				eq=eq.and(qcoeActivityUser.mainId.ne(mainId));
			}
		}
		return eq;

	}
}
