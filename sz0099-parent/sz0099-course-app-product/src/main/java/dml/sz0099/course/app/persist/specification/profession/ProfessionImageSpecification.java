package dml.sz0099.course.app.persist.specification.profession;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.ProfessionImage;
import dml.sz0099.course.app.persist.entity.profession.QProfessionImage;

/**
 * 
 * ProfessionImageSpecification 条件构造
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
 * description: ProfessionImageSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class ProfessionImageSpecification {

	private ProfessionImageSpecification() {
		throw new IllegalStateException("ProfessionImageSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<ProfessionImage> getConditionByCode(final ProfessionImage professionImage) {
		return new Specification<ProfessionImage>() {
			public Predicate toPredicate(Root<ProfessionImage> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param professionImage
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final ProfessionImage professionImage) {

		QProfessionImage qprofessionImage = QProfessionImage.professionImage;

		BooleanExpression eq = qprofessionImage.deleted.isFalse();
		// do something
		return eq;

	}
}
