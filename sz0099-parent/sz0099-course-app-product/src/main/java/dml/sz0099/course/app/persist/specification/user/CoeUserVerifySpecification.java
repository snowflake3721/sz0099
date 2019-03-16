package dml.sz0099.course.app.persist.specification.user;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserVerify;
import dml.sz0099.course.app.persist.entity.user.QCoeUser;
import dml.sz0099.course.app.persist.entity.user.QCoeUserVerify;

/**
 * 
 * CoeUserVerifySpecification 条件构造
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
 * description: CoeUserVerifySpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeUserVerifySpecification {

	private CoeUserVerifySpecification() {
		throw new IllegalStateException("CoeUserVerifySpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeUserVerify> getConditionByCode(final CoeUserVerify coeUserVerify) {
		return new Specification<CoeUserVerify>() {
			public Predicate toPredicate(Root<CoeUserVerify> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeUserVerify
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeUserVerify coeUserVerify) {

		QCoeUserVerify qcoeUserVerify = QCoeUserVerify.coeUserVerify;

		BooleanExpression eq = qcoeUserVerify.deleted.isFalse();
		// do something
		
		if(null != coeUserVerify) {
			
			Long userId = coeUserVerify.getUserId();
			
			
			if(null != userId) {
				eq=eq.and(qcoeUserVerify.userId.eq(userId));
			}
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionForVerify(final CoeUserVerify coeUserVerify) {
		
		QCoeUserVerify qcoeUserVerify = QCoeUserVerify.coeUserVerify;
		
		BooleanExpression eq = qcoeUserVerify.deleted.isFalse();
		
		Integer idstatus = coeUserVerify.getIdstatus();
		if(null != idstatus && idstatus!=-1) {
			eq=eq.and(qcoeUserVerify.idstatus.eq(idstatus));
		}
		
		String identity = coeUserVerify.getIdentity();
		if(StringUtils.isNotBlank(identity)) {
			eq=eq.and(qcoeUserVerify.identity.like("%"+identity+"%"));
		}
		
		String realname = coeUserVerify.getRealname();
		if(StringUtils.isNotBlank(realname)) {
			eq=eq.and(qcoeUserVerify.realname.like("%"+realname+"%"));
		}
		
		
		
		return eq;
		
	}
}
