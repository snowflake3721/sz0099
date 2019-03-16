package dml.sz0099.course.app.persist.specification.user;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.user.Sayword;
import dml.sz0099.course.app.persist.entity.user.QSayword;

/**
 * 
 * SaywordSpecification 条件构造
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
 * description: SaywordSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class SaywordSpecification {

	private SaywordSpecification() {
		throw new IllegalStateException("SaywordSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<Sayword> getConditionByCode(final Sayword sayword) {
		return new Specification<Sayword>() {
			public Predicate toPredicate(Root<Sayword> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param sayword
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final Sayword sayword) {

		QSayword qsayword = QSayword.sayword;

		BooleanExpression eq = qsayword.deleted.isFalse();
		// do something
		
		if(null != sayword) {
			Integer status = sayword.getStatus();
			
			Long userId = sayword.getUserId();
			
			if(null != status) {
				eq=eq.and(qsayword.status.eq(status));
			}
			
			if(null != userId) {
				eq=eq.and(qsayword.userId.eq(userId));
			}
		}
		
		return eq;

	}
}
