package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtend;
import dml.sz0099.course.app.persist.entity.profession.QProfessionExtend;

/**
 * 
 * ProfessionExtendSpecification 条件构造
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
 * description: ProfessionExtendSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ProfessionExtendSpecification {

	private ProfessionExtendSpecification() {
		throw new IllegalStateException("ProfessionExtendSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ProfessionExtend> getConditionByCode(final ProfessionExtend professionExtend) {
		return new Specification<ProfessionExtend>() {
			public Predicate toPredicate(Root<ProfessionExtend> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param professionExtend
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ProfessionExtend professionExtend) {

		QProfessionExtend qprofessionExtend = QProfessionExtend.professionExtend;

		BooleanExpression eq = qprofessionExtend.deleted.isFalse();
		// do something
		return eq;

	}
}
