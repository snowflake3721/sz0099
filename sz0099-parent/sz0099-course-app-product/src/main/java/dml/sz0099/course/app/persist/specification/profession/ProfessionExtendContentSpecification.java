package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.ProfessionExtendContent;
import dml.sz0099.course.app.persist.entity.profession.QProfessionExtendContent;

/**
 * 
 * ProfessionExtendContentSpecification 条件构造
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
 * description: ProfessionExtendContentSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ProfessionExtendContentSpecification {

	private ProfessionExtendContentSpecification() {
		throw new IllegalStateException("ProfessionExtendContentSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ProfessionExtendContent> getConditionByCode(final ProfessionExtendContent professionExtendContent) {
		return new Specification<ProfessionExtendContent>() {
			public Predicate toPredicate(Root<ProfessionExtendContent> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param professionExtendContent
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ProfessionExtendContent professionExtendContent) {

		QProfessionExtendContent qprofessionExtendContent = QProfessionExtendContent.professionExtendContent;

		BooleanExpression eq = qprofessionExtendContent.deleted.isFalse();
		// do something
		return eq;

	}
}
