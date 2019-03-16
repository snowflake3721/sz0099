package dml.sz0099.course.app.persist.specification.order;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.CoeOrderProduct;
import dml.sz0099.course.app.persist.entity.order.QCoeOrderProduct;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderProductBo;

/**
 * 
 * CoeOrderProductSpecification 条件构造
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
 * description: CoeOrderProductSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeOrderProductSpecification {

	private CoeOrderProductSpecification() {
		throw new IllegalStateException("CoeOrderProductSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeOrderProduct> getConditionByCode(final CoeOrderProduct coeOrderProduct) {
		return new Specification<CoeOrderProduct>() {
			public Predicate toPredicate(Root<CoeOrderProduct> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeOrderProduct
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeOrderProduct coeOrderProduct) {

		QCoeOrderProduct qcoeOrderProduct = QCoeOrderProduct.coeOrderProduct;

		BooleanExpression eq = qcoeOrderProduct.deleted.isFalse();
		// do something
		return eq;

	}
	
	public static BooleanExpression getConditionForMyOrder(final CoeOrderProductBo coeOrderProduct) {
		QCoeOrderProduct qcoeOrderProduct = QCoeOrderProduct.coeOrderProduct;

		
		BooleanExpression eq = qcoeOrderProduct.deleted.isFalse()
				.and(qcoeOrderProduct.coeOrder.deleted.isFalse());
		// do something
		Long userId = coeOrderProduct.getUserId();
		if(null != userId) {
			eq = eq.and(qcoeOrderProduct.userId.eq(userId))
					.and(qcoeOrderProduct.coeOrder.userId.eq(userId));
		}
		
		Date beginTime = coeOrderProduct.getBeginTime();
		Date endTime = coeOrderProduct.getEndTime();
		if(null != beginTime && null != endTime) {
			eq=eq.and(qcoeOrderProduct.coeOrder.orderTime.between(beginTime, endTime));
		}
		
		CoeOrder order = coeOrderProduct.getCoeOrder();
		if(null != order) {
			Integer status = order.getStatus();
			if(null != status && status != CoeOrder.STATUS_PAY_ALL) {
				if(CoeOrder.STATUS_PAY_RECIEVED.equals(status)){
					eq = eq.and(qcoeOrderProduct.coeOrder.status.goe(status));
				} else if(CoeOrder.STATUS_PAY_WAIT.equals(status)){
					eq = eq.and(qcoeOrderProduct.coeOrder.status.eq(status));
				} else {
					eq = eq.and(qcoeOrderProduct.coeOrder.status.gt(CoeOrder.STATUS_PAY_WAIT)).and(qcoeOrderProduct.coeOrder.status.loe(status));
				}
			}
		}
		
		String title = coeOrderProduct.getTitle();
		if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eq=eq.and(qcoeOrderProduct.titleLower.like(sb.toString()));
		}
		
		return eq;

	}
}
