package dml.sz0099.course.app.persist.specification.tag;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.tag.CoeTag;

/**
 * 
 * CoeTagSpecification 条件构造
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
 * description: CoeTagSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeTagSpecification {

	private CoeTagSpecification() {
		throw new IllegalStateException("CoeTagSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeTag> getConditionByCode(final CoeTag coeTag) {
		return new Specification<CoeTag>() {
			public Predicate toPredicate(Root<CoeTag> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeTag
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeTag coeTag) {

		//QCoeTag qcoeTag = QCoeTag.coeTag;

		BooleanExpression eq = null;
		// do something
		return eq;

	}
}
