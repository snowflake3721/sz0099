package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.ParagProfession;
import dml.sz0099.course.app.persist.entity.profession.QParagProfession;

/**
 * 
 * ParagProfessionSpecification 条件构造
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
 * description: ParagProfessionSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ParagProfessionSpecification {

	private ParagProfessionSpecification() {
		throw new IllegalStateException("ParagProfessionSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ParagProfession> getConditionByCode(final ParagProfession paragProfession) {
		return new Specification<ParagProfession>() {
			public Predicate toPredicate(Root<ParagProfession> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param paragProfession
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ParagProfession paragProfession) {

		QParagProfession qparagProfession = QParagProfession.paragProfession;

		BooleanExpression eq = qparagProfession.deleted.isFalse();
		// do something
		return eq;

	}
}
