package dml.sz0099.course.app.persist.specification.order;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.QCoeOrder;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderBo;

/**
 * 
 * CoeOrderSpecification 条件构造
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
 * description: CoeOrderSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeOrderSpecification {

	private CoeOrderSpecification() {
		throw new IllegalStateException("CoeOrderSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeOrder> getConditionByCode(final CoeOrder coeOrder) {
		return new Specification<CoeOrder>() {
			public Predicate toPredicate(Root<CoeOrder> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeOrder
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CoeOrder coeOrder) {

		QCoeOrder qcoeOrder = QCoeOrder.coeOrder;

		BooleanExpression eq = qcoeOrder.deleted.isFalse();
		// do something
		Long userId = coeOrder.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeOrder.userId.eq(userId));
		}
		Date orderTime = coeOrder.getOrderTime();
		if(null != orderTime) {
			
			//eq.and(qcoeOrder.orderTime.between(from, to))
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionForMyOrder(final CoeOrderBo coeOrderBo) {

		QCoeOrder qcoeOrder = QCoeOrder.coeOrder;

		BooleanExpression eq = qcoeOrder.deleted.isFalse();
		// do something
		Long userId = coeOrderBo.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeOrder.userId.eq(userId));
		}
		
		Date beginTime = coeOrderBo.getBeginTime();
		Date endTime = coeOrderBo.getEndTime();
		if(null != beginTime && null != endTime) {
			eq=eq.and(qcoeOrder.orderTime.between(beginTime, endTime));
		}
		
		Integer status = coeOrderBo.getStatus();
		if(null != status && status != CoeOrder.STATUS_PAY_ALL) {
			if(CoeOrder.STATUS_PAY_RECIEVED.equals(status)){
				eq = eq.and(qcoeOrder.status.goe(status));
			}else if(CoeOrder.STATUS_PAY_WAIT.equals(status)){
				eq = eq.and(qcoeOrder.status.eq(status));
			}else{
				eq = eq.and(qcoeOrder.status.gt(CoeOrder.STATUS_PAY_WAIT)).and(qcoeOrder.status.loe(status));
			}
		}
		//STATUS_PAY_ALL
		
		/*String title = coeOrderBo.getTitle();
		if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eq=eq.and(qcoeOrder.like(sb.toString()));
		}*/
		
		return eq;

	}
	
	
	public static BooleanExpression getConditionForOwner(final CoeOrderBo coeOrderBo) {

		QCoeOrder qcoeOrder = QCoeOrder.coeOrder;

		BooleanExpression eq = qcoeOrder.deleted.isFalse();
		// do something
		Long ownerId = coeOrderBo.getOwnerId();
		if(null != ownerId) {
			eq=eq.and(qcoeOrder.ownerId.eq(ownerId));
		}
		
		Date beginTime = coeOrderBo.getBeginTime();
		Date endTime = coeOrderBo.getEndTime();
		if(null != beginTime && null != endTime) {
			eq=eq.and(qcoeOrder.orderTime.between(beginTime, endTime));
		}
		
		/*String title = coeOrderBo.getTitle();
		if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eq=eq.and(qcoeOrder.titleLower.like(sb.toString()));
		}*/
		
		Integer status = coeOrderBo.getStatus();
		
		if(null != status && status != CoeOrder.STATUS_PAY_ALL) {
			if(CoeOrder.STATUS_PAY_PAYED.equals(status)){
				eq = eq.and(qcoeOrder.status.eq(status));
			}else if(CoeOrder.STATUS_PAY_INPROCESS.equals(status)){
				eq = eq.and(qcoeOrder.status.eq(status));
			}else if(CoeOrder.STATUS_PAY_SENT.equals(status)){
				eq = eq.and(qcoeOrder.status.eq(status));
			}else{
				eq = eq.and(qcoeOrder.status.gt(CoeOrder.STATUS_PAY_WAIT)).and(qcoeOrder.status.loe(status));
			}
		}
		
		return eq;

	}
	
}
