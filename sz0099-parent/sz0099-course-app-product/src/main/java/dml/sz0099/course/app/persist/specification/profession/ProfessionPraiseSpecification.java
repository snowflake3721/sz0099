package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPraise;
import dml.sz0099.course.app.persist.entity.profession.QProfessionPraise;

/**
 * 
 * ProfessionPraiseSpecification 条件构造
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
 * description: ProfessionPraiseSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ProfessionPraiseSpecification {

	private ProfessionPraiseSpecification() {
		throw new IllegalStateException("ProfessionPraiseSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ProfessionPraise> getConditionByCode(final ProfessionPraise professionPraise) {
		return new Specification<ProfessionPraise>() {
			public Predicate toPredicate(Root<ProfessionPraise> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param professionPraise
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ProfessionPraise professionPraise) {

		QProfessionPraise qprofessionPraise = QProfessionPraise.professionPraise;

		BooleanExpression eq = qprofessionPraise.deleted.isFalse();
		// do something
		return eq;

	}
}
