package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPositionImage;
import dml.sz0099.course.app.persist.entity.profession.QProfessionPositionImage;

/**
 * 
 * ProfessionPositionImageSpecification 条件构造
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
 * description: ProfessionPositionImageSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ProfessionPositionImageSpecification {

	private ProfessionPositionImageSpecification() {
		throw new IllegalStateException("ProfessionPositionImageSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ProfessionPositionImage> getConditionByCode(final ProfessionPositionImage professionPositionImage) {
		return new Specification<ProfessionPositionImage>() {
			public Predicate toPredicate(Root<ProfessionPositionImage> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param professionPositionImage
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ProfessionPositionImage professionPositionImage) {

		QProfessionPositionImage qprofessionPositionImage = QProfessionPositionImage.professionPositionImage;

		BooleanExpression eq = qprofessionPositionImage.deleted.isFalse();
		// do something
		return eq;

	}
}
