package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionCover;
import dml.sz0099.course.app.persist.entity.profession.QProfessionPositionCover;

/**
 * 
 * ProfessionPositionCoverSpecification 条件构造
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
 * description: ProfessionPositionCoverSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ProfessionPositionCoverSpecification {

	private ProfessionPositionCoverSpecification() {
		throw new IllegalStateException("ProfessionPositionCoverSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ProfessionPositionCover> getConditionByCode(final ProfessionPositionCover professionPositionCover) {
		return new Specification<ProfessionPositionCover>() {
			public Predicate toPredicate(Root<ProfessionPositionCover> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param professionPositionCover
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ProfessionPositionCover professionPositionCover) {

		QProfessionPositionCover qprofessionPositionCover = QProfessionPositionCover.professionPositionCover;

		BooleanExpression eq = qprofessionPositionCover.deleted.isFalse();
		// do something
		return eq;

	}
}
