package dml.sz0099.course.app.persist.specification.activity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.activity.JoinItem;
import dml.sz0099.course.app.persist.entity.activity.QCoeActivity;
import dml.sz0099.course.app.persist.entity.activity.QJoinItem;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.tag.QCoeTag;

/**
 * 
 * JoinItemSpecification 条件构造
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
 * description: JoinItemSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class JoinItemSpecification {

	private JoinItemSpecification() {
		throw new IllegalStateException("JoinItemSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<JoinItem> getConditionByCode(final JoinItem joinItem) {
		return new Specification<JoinItem>() {
			public Predicate toPredicate(Root<JoinItem> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param joinItem
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final JoinItem joinItem) {

		QJoinItem qjoinItem = QJoinItem.joinItem;
		// do something
		BooleanExpression eq = qjoinItem.deleted.isFalse();
		
		return eq;

	}
	
	public static BooleanExpression getConditionWithNotself(final JoinItem joinItem) {

		QJoinItem qjoinItem = QJoinItem.joinItem;
		// do something
		BooleanExpression eq = qjoinItem.deleted.isFalse();
		
		if(null != joinItem) {
			Long mainId = joinItem.getMainId();
			if(null != mainId) {
				eq=eq.and(qjoinItem.mainId.ne(mainId));
			}
		}
		return eq;

	}
}
