package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.ProfessionFavirate;
import dml.sz0099.course.app.persist.entity.profession.QProfessionFavirate;

/**
 * 
 * ProfessionFavirateSpecification 条件构造
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
 * description: ProfessionFavirateSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ProfessionFavirateSpecification {

	private ProfessionFavirateSpecification() {
		throw new IllegalStateException("ProfessionFavirateSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ProfessionFavirate> getConditionByCode(final ProfessionFavirate professionFavirate) {
		return new Specification<ProfessionFavirate>() {
			public Predicate toPredicate(Root<ProfessionFavirate> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param professionFavirate
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ProfessionFavirate professionFavirate) {

		QProfessionFavirate qprofessionFavirate = QProfessionFavirate.professionFavirate;

		BooleanExpression eq = qprofessionFavirate.deleted.isFalse();
		// do something
		return eq;

	}
}
