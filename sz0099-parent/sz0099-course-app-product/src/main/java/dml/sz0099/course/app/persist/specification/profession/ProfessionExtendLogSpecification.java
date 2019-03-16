package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendLog;
import dml.sz0099.course.app.persist.entity.profession.QProfessionExtendLog;

/**
 * 
 * ProfessionExtendLogSpecification 条件构造
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
 * description: ProfessionExtendLogSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ProfessionExtendLogSpecification {

	private ProfessionExtendLogSpecification() {
		throw new IllegalStateException("ProfessionExtendLogSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ProfessionExtendLog> getConditionByCode(final ProfessionExtendLog professionExtendLog) {
		return new Specification<ProfessionExtendLog>() {
			public Predicate toPredicate(Root<ProfessionExtendLog> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param professionExtendLog
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ProfessionExtendLog professionExtendLog) {

		QProfessionExtendLog qprofessionExtendLog = QProfessionExtendLog.professionExtendLog;

		BooleanExpression eq = qprofessionExtendLog.deleted.isFalse();
		// do something
		return eq;

	}
}
