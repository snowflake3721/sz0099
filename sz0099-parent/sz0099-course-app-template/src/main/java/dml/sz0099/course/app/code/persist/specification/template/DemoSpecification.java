package dml.sz0099.course.app.code.persist.specification.template;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.code.persist.entity.template.Demo;
import dml.sz0099.course.app.code.persist.entity.template.QDemo;

/**
 * 
 * DemoSpecification 条件构造
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
 * description: DemoSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class DemoSpecification {

	private DemoSpecification() {
		throw new IllegalStateException("DemoSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<Demo> getConditionByCode(final Demo demo) {
		return new Specification<Demo>() {
			public Predicate toPredicate(Root<Demo> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param demo
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final Demo demo) {

		QDemo qdemo = QDemo.demo;

		BooleanExpression eq = qdemo.deleted.isFalse();
		// do something
		return eq;

	}
}
