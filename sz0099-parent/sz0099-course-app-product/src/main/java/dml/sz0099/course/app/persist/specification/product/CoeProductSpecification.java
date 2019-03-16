package dml.sz0099.course.app.persist.specification.product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.product.CoeProduct;
import dml.sz0099.course.app.persist.entity.product.QCoeProduct;

/**
 * 
 * CoeProductSpecification 条件构造
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
 * description: CoeProductSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CoeProductSpecification {

	private CoeProductSpecification() {
		throw new IllegalStateException("CoeProductSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CoeProduct> getConditionByCode(final CoeProduct coeProduct) {
		return new Specification<CoeProduct>() {
			public Predicate toPredicate(Root<CoeProduct> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param coeProduct
	 * @return
	 */
	public static BooleanExpression getConditionByCodeWithQslExpression(final CoeProduct coeProduct) {

		QCoeProduct qcoeProduct = QCoeProduct.coeProduct;
		
		BooleanExpression eq = qcoeProduct.shelved.eq(CoeProduct.SHELVED_YES.getValueInt())
				.and(qcoeProduct.deleted.isFalse())
				.and(qcoeProduct.publishStatus.eq(CoeProduct.PUBLISH_STATUS_PUBLISH.getValueInt()));
				
		if(null != coeProduct) {
			String title = coeProduct.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eq=eq.and(qcoeProduct.titleLower.like(sb.toString()));
			}
			
			String name = coeProduct.getName();
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(name)).append("%");
				eq=eq.and(qcoeProduct.name.like(sb.toString()));
			}
			
			String productNo = coeProduct.getProductNo();
			if(StringUtils.isNotBlank(productNo)) {
				eq = eq.and(qcoeProduct.productNo.eq(productNo));
			}
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionForPublish(final CoeProduct coeProduct) {

		QCoeProduct qcoeProduct = QCoeProduct.coeProduct;
		
		BooleanExpression eq = qcoeProduct.publishStatus.eq(coeProduct.getPublishStatus())
				.and(qcoeProduct.deleted.isFalse());
				
		BooleanExpression eqTitle = null;
		String title = coeProduct.getTitle();
		if(StringUtils.isNotBlank(title)) {
			StringBuilder sb = new StringBuilder();
			sb.append("%").append(StringUtils.lowerCase(title)).append("%");
			eqTitle=(qcoeProduct.titleLower.like(sb.toString()));
		}
		
		String name = coeProduct.getName();
		if(StringUtils.isNotBlank(name)) {
			StringBuilder sb = new StringBuilder();
			sb.append("%").append(StringUtils.lowerCase(name)).append("%");
			if(eqTitle != null) {
				eqTitle=eqTitle.or(qcoeProduct.name.like(sb.toString()));
			}else {
				eqTitle=qcoeProduct.name.like(sb.toString());
			}
		}
		
		if(null != eqTitle) {
			eq = eq.and(eqTitle);
		}
		
		String productNo = coeProduct.getProductNo();
		if(StringUtils.isNotBlank(productNo)) {
			eq = eq.and(qcoeProduct.productNo.eq(productNo));
		}
		
		Long userId = coeProduct.getUserId();
		if(null != userId) {
			eq=eq.and(qcoeProduct.userId.eq(userId));
		}
		
		return eq;

	}
}
