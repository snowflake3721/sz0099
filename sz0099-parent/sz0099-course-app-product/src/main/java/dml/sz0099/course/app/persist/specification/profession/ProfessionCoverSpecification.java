package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.ProfessionCover;
import dml.sz0099.course.app.persist.entity.profession.QProfessionCover;

/**
 * 
 * ProfessionCoverSpecification 条件构造
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
 * description: ProfessionCoverSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ProfessionCoverSpecification {

	private ProfessionCoverSpecification() {
		throw new IllegalStateException("ProfessionCoverSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ProfessionCover> getConditionByCode(final ProfessionCover professionCover) {
		return new Specification<ProfessionCover>() {
			public Predicate toPredicate(Root<ProfessionCover> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param professionCover
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ProfessionCover professionCover) {

		QProfessionCover qprofessionCover = QProfessionCover.professionCover;

		BooleanExpression eq = qprofessionCover.deleted.isFalse();
		// do something
		return eq;

	}
}
