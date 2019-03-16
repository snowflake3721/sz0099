package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.ProfessionRef;
import dml.sz0099.course.app.persist.entity.profession.QProfessionRef;

/**
 * 
 * @formatter:off
 * 
 * description: ProfessionRefSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ProfessionRefSpecification {

	private ProfessionRefSpecification() {
		throw new IllegalStateException("ProfessionRefSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ProfessionRef> getConditionByCode(final ProfessionRef professionRef) {
		return new Specification<ProfessionRef>() {
			public Predicate toPredicate(Root<ProfessionRef> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param professionRef
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ProfessionRef professionRef) {

		QProfessionRef qprofessionRef = QProfessionRef.professionRef;

		BooleanExpression eq = qprofessionRef.deleted.isFalse();
		// do something
		return eq;

	}
}
