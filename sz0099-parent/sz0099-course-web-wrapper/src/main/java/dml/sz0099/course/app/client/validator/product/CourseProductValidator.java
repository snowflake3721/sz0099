package dml.sz0099.course.app.client.validator.product;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dml.sz0099.course.app.client.wrapper.product.CoeProductWrapper;
import dml.sz0099.course.app.module.define.CourseProductRespCode;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;
import dml.sz0099.course.app.persist.entity.product.bo.CoeProductBo;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CourseProductValidator 校验组件
 * @author bruce yang at 2018-08-29 22:40:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Component
public class CourseProductValidator implements Validator {

	
	@Autowired
	private CoeProductWrapper coeProductWrapper;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CoeProductBo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CoeProductBo courseProduct = (CoeProductBo) target;

		validateUuid(errors, courseProduct.getUuid());

	}

	/**
	 * CourseProduct validate
	 * 
	 * @param errors
	 * @param uuid
	 */
	public void validateUuid(Errors errors, String uuid) {
		if (StringUtils.isEmpty(uuid)) {
			errors.rejectValue("uuid", "org.jit4j.app.product.CourseProduct.uuid.exist", "UUID不能为空");
		}
	}
	
	
	public boolean validateCoeProductUserBuy(Errors errors, CoeProduct coeProduct) {

		if (null == coeProduct) {
			coeProduct = new CoeProduct();
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_BUY_NOT_EXIST);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_BUY_NOT_EXIST);
			return false;
		}
		
		Long userId = coeProduct.getUserId();
		if(null == userId) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_ORDER_NOT_LOGIN);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_ORDER_NOT_LOGIN);
			return false;
		}
		
		Long id = coeProduct.getId();
		CoeProduct entity = coeProductWrapper.findByIdOnly(id);
		
		if(null == entity) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_BUY_NOT_EXIST);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_BUY_NOT_EXIST);
			return false;
		}
		
		Long userIdEntity = entity.getUserId();
		if(userIdEntity.equals(userId)) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_BUY_USER_SELF);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_BUY_USER_SELF);
			return false;
		}
		
		Integer shelved = entity.getShelved();
		if(!CoeProduct.SHELVED_YES.getValueInt().equals(shelved)) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_BUY_SHELVED_NO);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_BUY_SHELVED_NO);
			return false;
		}
		
		boolean deleted = entity.getDeleted();
		if(deleted) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_BUY_HAS_DELTETED);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_BUY_HAS_DELTETED);
			return false;
		}
		return true;

	}
	
	public boolean validateCoeProduct(Errors errors, CoeProduct coeProduct) {
		
		if(null == coeProduct) {
			coeProduct = new CoeProduct(); 
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NOT_EXIST);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NOT_EXIST);
			return false;
		}
		
		Integer shelved = coeProduct.getShelved();
		if(!CoeProduct.SHELVED_YES.getValueInt().equals(shelved)) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_SHELVED_NO);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_SHELVED_NO);
			return false;
		}
		
		boolean deleted = coeProduct.getDeleted();
		if(deleted) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_HAS_DELTETED);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_HAS_DELTETED);
			return false;
		}
		
		Integer publishStatus = coeProduct.getPublishStatus();
		if(!CoeProduct.PUBLISH_STATUS_PUBLISH.getValueInt().equals(publishStatus)) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NOT_PUBLISH);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NOT_PUBLISH);
			return false;
		}
		
		return true;
	}
	
	public boolean validateCoeProductForPreOrder(Errors errors, CoeProduct coeProduct) {
		boolean checked = validateCoeProduct( errors,  coeProduct);
		if(!checked) {
			return false;
		}
		Integer strategy = coeProduct.getStrategy();
		if(CoeProduct.STRATEGY_4_FREE.getValueInt().equals(strategy)) {
			//校验用户是否已成为会员，非会员不能享受免费政策
			//如何成为会员，用户下一个19.9元以上的单且收货以后，即可成为0级会员，可享受免费资源策略
			//前10名19.9， 10-30 29.9， 30-50 49.9 ， 50-80 69.9， 80-120 88.8，120-150 99.9，150-200 119.9， 200-250 159.9， 250-300 199
			//只有1级会员以上的用户才可进入管理页面，才有权限进行资源发布的操作
			
			//会员采用6个月收费机制，产品优惠期按注册排名实施会员优惠
			//成为会员，全享超过60%的优惠资源,等级越高，可享受的优惠资源越多
			//PT资源分享计划，你也可以加入分享，来发布你的资源吧，原创资源自主定价。
			
		}
		return true;
	}
	
	public Long countDraftList(CoeProduct coeProduct) {
		Long draftNum = coeProductWrapper.countDraftList(coeProduct);
		if (null != draftNum && draftNum > 5) {
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_DRAFT_EXTRA);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_DRAFT_EXTRA);
		}
		return draftNum;
	}
	

	public boolean validateBaseinfo(Errors errors, CoeProduct coeProduct) {
		boolean existChecked = validateExist(coeProduct);
		if(!existChecked) {
			return false;
		}
		
		boolean checked = validateNameAndTitle(coeProduct);
		if(!checked) {
			return false;
		}
		
		String description = coeProduct.getDescription();
		if(StringUtils.isBlank(description)) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_DESCRIPTION_EMPTY);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_DESCRIPTION_EMPTY);
			return false;
		}
		
		if(description.length()>255) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_DESCRIPTION_EXTRA_LENGTH);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_DESCRIPTION_EXTRA_LENGTH);
			return false;
		}
		
		Long userId = coeProduct.getLastModifiedBy();
		if(null==userId) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_USER_NOT_LOGIN);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_USER_NOT_LOGIN);
			return false;
		}

		
		return true;
	}

	/**
	 * @param coeProduct
	 */
	private boolean validateNameAndTitle(CoeProduct coeProduct) {
		
		String name = coeProduct.getName();
		if(StringUtils.isBlank(name)) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NAME_EMPTY);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NAME_EMPTY);
			return false;
		}
		
		if(name.length()>20) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NAME_EXTRA_LENGTH);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NAME_EXTRA_LENGTH);
			return false;
		}
		
		String title = coeProduct.getTitle();
		if(StringUtils.isBlank(title)) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_TITLE_EMPTY);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_TITLE_EMPTY);
			return false;
		}
		
		if(title.length()>60) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_TITLE_EXTRA_LENGTH);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_TITLE_EXTRA_LENGTH);
			return false;
		}
		return true;
	}

	/**
	 * @param coeProduct
	 * @return
	 */
	public boolean validateExist(CoeProduct coeProduct) {
		if(null == coeProduct) {
			coeProduct = new CoeProduct(); 
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NOT_EXIST);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NOT_EXIST);
			return false;
		}
		
		Long id = coeProduct.getId();
		if(null == id) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NOT_EXIST);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NOT_EXIST);
			return false;
		}
		
		CoeProduct entity = coeProductWrapper.findById(id);
		
		if(entity==null) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NOT_EXIST);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NOT_EXIST);
			return false;
		}
		Integer status = entity.getPublishStatus();
		boolean closed = CoeProduct.PUBLISH_STATUS_CLOSED.getValueInt().equals(status);
		if(closed) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_MERGE_HAS_CLOSED);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_MERGE_HAS_CLOSED);
			return false;
		}
		
		boolean deleted = entity.getDeleted();
		if(deleted) {
			coeProduct.setSuccess(CoeOrder.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_MERGE_HAS_DELERED);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_MERGE_HAS_DELERED);
			return false;
		}
		return true;
	}
	
	public boolean validatePrice(Errors errors, CoeProduct coeProduct) {

		boolean existChecked = validateExist(coeProduct);
		if (!existChecked) {
			return false;
		}

		boolean priceChecked = validatePrice(coeProduct);
		if(!priceChecked) {
			return false;
		}

		Integer strategy = coeProduct.getStrategy();
		if (strategy == null) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_STRATEGY_EMPTY);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_STRATEGY_EMPTY);
			return false;
		}

		if (CoeProduct.STRATEGY_2_GRADE.getValueInt().equals(strategy) 
				|| CoeProduct.STRATEGY_3_ALL.getValueInt().equals(strategy)
				|| CoeProduct.STRATEGY_5_GRADE_FREE.getValueInt().equals(strategy)
				) {
			Integer grade = coeProduct.getGrade();
			if (null == grade) {
				coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
				coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_GRADE_EMPTY);
				coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_GRADE_EMPTY);
				return false;
			}
			if (9 < grade) {
				coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
				coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_GRADE_MORETHAN_9);
				coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_GRADE_MORETHAN_9);
				return false;
			}
			if(CoeProduct.STRATEGY_2_GRADE.getValueInt().equals(strategy) 
				|| CoeProduct.STRATEGY_3_ALL.getValueInt().equals(strategy)) {
				Integer rates = coeProduct.getRates();
				if (null == rates) {
					coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
					coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_RATES_EMPTY);
					coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_RATES_EMPTY);
					return false;
				}
	
				if (rates < 35 || rates > 100) {
					coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
					coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_RATES_EXTRA);
					coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_RATES_EXTRA);
					return false;
				}
			}

		}

		Integer pullMethod = coeProduct.getPullMethod();
		if (null == pullMethod) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_PULL_METHOD_EMPTY);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_PULL_METHOD_EMPTY);
			return false;
		}
		if (CoeProduct.PULL_METHOD_YUN.getValueInt().equals(pullMethod) || CoeProduct.PULL_METHOD_INNER.getValueInt().equals(pullMethod)) {
			String link = coeProduct.getLink();
			if (StringUtils.isBlank(link)) {
				coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
				coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_PULL_METHOD_EMPTY);
				coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_PULL_METHOD_EMPTY);
				return false;
			}
			if(link.length()>254) {
				coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
				coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_LINK_LENGTH_MORE_LONG);
				coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_LINK_LENGTH_MORE_LONG);
				return false;
			}
			String pullCode = coeProduct.getPullCode();
			if (CoeProduct.PULL_METHOD_YUN.getValueInt().equals(pullMethod)) {
				if (StringUtils.isBlank(pullCode)) {
					coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
					coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_PULL_CODE_EMPTY);
					coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_PULL_CODE_EMPTY);
					return false;
				}
				
				if (pullCode.length()<4) {
					coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
					coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_PULL_CODE_LENGTH_LESS_4);
					coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_PULL_CODE_LENGTH_LESS_4);
					return false;
				}
				if (pullCode.length()>10) {
					coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
					coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_PULL_CODE_LENGTH_TOO_LONG);
					coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_PULL_CODE_LENGTH_TOO_LONG);
					return false;
				}
			}
		}

		Long userId = coeProduct.getLastModifiedBy();
		if (null == userId) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_USER_NOT_LOGIN);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_USER_NOT_LOGIN);
			return false;
		}

		return true;
	}

	/**
	 * @param coeProduct
	 */
	private boolean validatePrice(CoeProduct coeProduct) {
		Integer priceOri = coeProduct.getPriceOri();
		if (null == priceOri) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_PRICEORI_EMPTY);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_PRICEORI_EMPTY);
			return false;
		}

		Integer priceCur = coeProduct.getPriceCur();
		if (null == priceCur) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_PRICECUR_EMPTY);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_PRICECUR_EMPTY);
			return false;
		}
		return true;
	}
	
	
	public boolean validateForShelved(Errors errors, CoeProduct coeProduct) {
		boolean existChecked = validateExist(coeProduct);
		if (!existChecked) {
			return false;
		}
		
		Long id = coeProduct.getId();
		CoeProduct entity = coeProductWrapper.findByIdOnly(id);
		Long userId= coeProduct.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NOT_USER_SELF);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NOT_USER_SELF);
			return false;
		}
		return true;
	}
	
	public boolean validateForRefresh(Errors errors, CoeProduct coeProduct) {
		boolean existChecked = validateExist(coeProduct);
		if (!existChecked) {
			return false;
		}
		
		Long id = coeProduct.getId();
		CoeProduct entity = coeProductWrapper.findByIdOnly(id);
		Long userId= coeProduct.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NOT_USER_SELF);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NOT_USER_SELF);
			return false;
		}
		
		Date refreshTime = entity.getRefreshTime();//刷新时间
		Date publishTime = entity.getPublishTime();//初始发布时间
		Date currentTime = new Date();//当前时间
		if(null != refreshTime && org.jit8j.core.util.DateUtils.daysBetween(refreshTime, currentTime)<7) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_REFRESH_INVALID);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_REFRESH_INVALID);
			return false;
		}
		
		if(null != publishTime && org.jit8j.core.util.DateUtils.daysBetween(publishTime, currentTime)>365) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_REFRESH_EXTRA);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_REFRESH_EXTRA);
			return false;
		}
		return true;
	}
	
	public boolean validateForEditQuickly(Errors errors, CoeProduct coeProduct) {
		boolean existChecked = validateExist(coeProduct);
		if(!existChecked) {
			return false;
		}
		
		boolean checked = validateNameAndTitle(coeProduct);
		if(!checked) {
			return false;
		}
		
		boolean priceChecked = validatePrice(coeProduct);
		if(!priceChecked) {
			return false;
		}
		
		return true;
	}
	
	public boolean validateForPublish(Errors errors, CoeProduct coeProduct) {
		boolean existChecked = validateExist(coeProduct);
		if(!existChecked) {
			return false;
		}
		
		boolean checked = validateBaseinfo(errors, coeProduct);
		if(!checked) {
			return false;
		}
		
		boolean priceChecked = validatePrice(errors,coeProduct);
		if(!priceChecked) {
			return false;
		}
		Long id = coeProduct.getId();
		CoeProduct entity = coeProductWrapper.findById(id);
		
		
		Integer publishStatus = entity.getPublishStatus();
		if(CoeProduct.PUBLISH_STATUS_PUBLISH.getValueInt().equals(publishStatus)) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_MERGE_HAS_PUBLISHED);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_MERGE_HAS_PUBLISHED);
			return false;
		}else if(CoeProduct.PUBLISH_STATUS_CLOSED.getValueInt().equals(publishStatus)) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_MERGE_HAS_CLOSED);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_MERGE_HAS_CLOSED);
			return false;
		}
		
		return true;
	}
	
	public boolean validateForClosed(Errors errors, CoeProduct coeProduct) {
		boolean existChecked = validateExist(coeProduct);
		if(!existChecked) {
			return false;
		}
		
		Long id = coeProduct.getId();
		CoeProduct entity = coeProductWrapper.findById(id);
		
		Long userId = coeProduct.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NOT_USER_SELF);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NOT_USER_SELF);
			return false;
		}
		
		Integer publishStatus = entity.getPublishStatus();
		if(CoeProduct.PUBLISH_STATUS_CLOSED.getValueInt().equals(publishStatus)) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_MERGE_HAS_CLOSED);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_MERGE_HAS_CLOSED);
			return false;
		}
		
		return true;
	}
	
	public boolean validateForDeleted(Errors errors, CoeProduct coeProduct) {
		boolean existChecked = validateExist(coeProduct);
		if(!existChecked) {
			return false;
		}
		
		Long id = coeProduct.getId();
		CoeProduct entity = coeProductWrapper.findById(id);
		
		Long userId = coeProduct.getUserId();
		Long userIdEntity = entity.getUserId();
		if(!userIdEntity.equals(userId)) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_NOT_USER_SELF);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_NOT_USER_SELF);
			return false;
		}
		
		Integer publishStatus = entity.getPublishStatus();
		if(CoeProduct.PUBLISH_STATUS_CLOSED.getValueInt().equals(publishStatus)) {
			coeProduct.setSuccess(CoeProduct.SUCCESS_NO);
			coeProduct.setRespCode(CourseProductRespCode.SZ0099_CODE_COURSE_PRODUCT_MERGE_HAS_CLOSED);
			coeProduct.setRespMsg(CourseProductRespCode.SZ0099_MSG_COURSE_PRODUCT_MERGE_HAS_CLOSED);
			return false;
		}
		
		return true;
	}

}
