package dml.sz0099.course.app.biz.service.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jit4j.core.persist.page.Jit4jPageResult;
import org.jit4j.core.service.GenericServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.service.product.CoeGradeService;
import dml.sz0099.course.app.biz.service.product.CoeProductService;
import dml.sz0099.course.app.biz.service.user.CoeUserGradeService;
import dml.sz0099.course.app.biz.service.user.CoeUserService;
import dml.sz0099.course.app.persist.dao.order.CoeOrderDao;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.CoeOrderProduct;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderBo;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderProductBo;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;


/**
 * 
 * @formatter:off
 * description: CoeOrderServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CoeOrderServiceImpl extends GenericServiceImpl<CoeOrder, Long> implements CoeOrderService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderServiceImpl.class);

	@Autowired
	private CoeOrderDao coeOrderDao;
	
	@Autowired
	private CoeProductService coeProductService;
	
	@Autowired
	private CoeGradeService coeGradeService;
	
	@Autowired
	private CoeUserGradeService coeUserGradeService;
	
	@Autowired
	private CoeUserService coeUserService;
	
	@Autowired
	private CoeOrderProdLogService coeOrderProdLogService;
	
	@Autowired
	private CoeOrderAsignService coeOrderAsignService;
	
	@Autowired
	private CoeOrderProductService coeOrderProductService;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = coeOrderDao;
	}

	/**
	 * 根据Id查询CoeOrder实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrder findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeOrder coeOrder = coeOrderDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeOrder);
		return coeOrder;
	}
	
	public CoeOrder findById(Long id, boolean withProduct) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeOrder coeOrder = findById(id);
		if(withProduct) {
			List<CoeOrderProduct>  productList = coeOrderProductService.findByOrderId(id);
			coeOrder.setProductList(productList);
		}
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, coeOrder);
		return coeOrder;
	}
	
	@Override
	public CoeOrder findByAid(Long aid) {
		LOGGER.debug("--- service>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrder coeOrder = coeOrderDao.findByAid(aid);
		LOGGER.debug("--- service>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrder);
		return coeOrder;
	}

	/**
	 * 根据IdList查询CoeOrder实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrder> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeOrder> coeOrderList = coeOrderDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", coeOrderList);
		return coeOrderList;
	}

	@Transactional
	@Override
	public CoeOrder persistEntity(CoeOrder coeOrder) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeOrder entity = save(coeOrder);
		Long id = coeOrder.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		entity.setSuccess(CoeOrder.SUCCESS_YES);
		return entity;
	}
	
	/**
	 * 生成订单
	 */
	@Transactional
	@Override
	public CoeOrder generateOrder(CoeOrder order) {
		
		CoeOrder coeOrder = new CoeOrder();
		//coeOrder.setLink(product.getLink());//确认下单之后生成提取链接
		
		//设置订单状态
		coeOrder.setStatus(CoeOrder.STATUS_PAY_WAIT);
		coeOrder.setFlowStatus(CoeOrder.FLOW_STATUS_BEGIN);
		
		coeOrder.setCreatedDate(new DateTime());
		coeOrder.setCreatedBy(order.getCreatedBy());
		coeOrder.setLastModifiedBy(order.getLastModifiedBy());
		coeOrder.setUserId(order.getUserId());
		coeOrder.setOpenIdwc(order.getOpenIdwc());
		coeOrder.setNickname(order.getNickname());
		
		//  以下三个信息，在确认下单时要更新一遍，由前端页面收集而来
		coeOrder.setMobile(order.getMobile());
		coeOrder.setQq(order.getQq());
		coeOrder.setEmail(order.getEmail());
		coeOrder.setPrice(order.getPrice());
		coeOrder.setOwnerId(order.getOwnerId());
		
		CoeOrder entity = persistEntity(coeOrder);
		//生成flowNo="dd"+aid
		entity.setFlowNo(getFlowNo(entity.getAid()));
		
		//coeOrder.setPayType(order.getPayType()); 支付类型 在确认下单时赋值
		//coeOrder.setRemark(coeOrder.getRemark());
		
		List<CoeOrderProduct> productList = order.getProductList();
		if(null != productList && !productList.isEmpty()) {
			for(CoeOrderProduct product : productList) {
				product.setOrderId(entity.getId());
				product.setFlowNo(entity.getFlowNo());
				product.setUserId(entity.getUserId());
			}
			//保存订单产品关联
			productList = coeOrderProductService.persistEntityList(productList);
			entity.setProductList(productList);
		}
		entity.setSuccess(CoeOrder.SUCCESS_YES);
		return entity;
	}
	
	

	/**
	 * 确认下单
	 * @param order
	 * @return
	 */
	@Transactional
	public CoeOrder confirmOrder(CoeOrder order) {
		Long id = order.getId();
		CoeOrder entity = findById(id);
		if(null != entity) {
			entity.setPayType(order.getPayType());
			
			entity.setRemark(order.getRemark());
			entity.setLastModifiedBy(order.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			
			entity.setMobile(order.getMobile());
			entity.setQq(order.getQq());
			entity.setEmail(order.getEmail());
			
			
			Date orderTime = new Date();
			entity.setOrderTime(orderTime);
			Date expiredTime = DateUtils.addDays(orderTime, 30);
			entity.setExpiredTime(expiredTime);
			entity.setPullStatus(CoeOrder.PULL_STATUS_NO);
			
			//设置订单状态，确认订单并已支付
			entity.setStatus(CoeOrder.STATUS_PAY_PAYED);
			entity.setFlowStatus(CoeOrder.FLOW_STATUS_INPROCESS);
			
			entity.setSuccess(CoeOrder.SUCCESS_YES);
			
			save(entity);
			
		}
		
		return entity;
	}
	
	@Transactional
	public CoeOrder mergeForInprocess(CoeOrder order) {
		Long id = order.getId();
		CoeOrder entity = findById(id);
		if(null != entity) {
			Integer status = entity.getStatus();
			Integer flowStatus=entity.getFlowStatus();
			if(CoeOrder.STATUS_PAY_PAYED.equals(status) && CoeOrder.FLOW_STATUS_INPROCESS.equals(flowStatus)) {
				Integer s = order.getStatus();
				if(CoeOrder.STATUS_PAY_INPROCESS.equals(s)) {
					
					order.setFlowNo(entity.getFlowNo());
					order.setPrice(entity.getPrice());
					order.setFlowStatus(entity.getFlowStatus());
					coeOrderAsignService.mergeForInprocess(order);
					mergeForStatus(entity, order);
					entity.setSuccess(CoeOrder.SUCCESS_YES);
				}
			}
		}
		return entity;
	}
	
	@Transactional
	public CoeOrder mergeForSent(CoeOrder order) {
		Long id = order.getId();
		CoeOrder entity = findById(id);
		if(null != entity) {
			Integer status = entity.getStatus();
			Integer flowStatus=entity.getFlowStatus();
			if(CoeOrder.STATUS_PAY_INPROCESS.equals(status) && CoeOrder.FLOW_STATUS_INPROCESS.equals(flowStatus)) {
				Integer s = order.getStatus();
				order.setFlowStatus(flowStatus);
				if(CoeOrder.STATUS_PAY_SENT.equals(s)) {
					coeOrderAsignService.mergeForSent(order);
					entity.setStatus(s);
					
					/*Integer pullMethod = order.getPullMethod();
					if(CoeProduct.PULL_METHOD_YUN.getValueInt().equals(pullMethod)
							|| CoeProduct.PULL_METHOD_INNER.getValueInt().equals(pullMethod)
					) {
						//发货时可以更新一次提取链接和提取码
						entity.setLink(order.getLink());
						entity.setPullCode(order.getPullCode());
					}*/
					
					entity.setLastModifiedBy(order.getLastModifiedBy());
					DateTime lastModifiedDate = new DateTime();
					entity.setLastModifiedDate(lastModifiedDate);
					entity = save(entity);
					entity.setSuccess(CoeOrder.SUCCESS_YES);
				}
			}
		}
		return entity;
	}
	
	@Transactional
	public CoeOrder mergeForStatus(CoeOrder  entity, CoeOrder order){
		entity.setStatus(order.getStatus());
		entity.setFlowStatus(order.getFlowStatus());
		entity.setLastModifiedBy(order.getLastModifiedBy());
		DateTime lastModifiedDate = new DateTime();
		entity.setLastModifiedDate(lastModifiedDate);
		save(entity);
		return entity;
	}
	
	
	private static String getFlowNo(Long aid) {
		StringBuilder sb = new StringBuilder("dd");
		sb.append(aid);
		return sb.toString();
	}
	
	/**
	 * 计算当前用户应付金额
	 * @param userGrade
	 * @param coeGrade
	 * @param product
	 * @return
	 */
	public Integer calPayPrice(CoeUserGrade userGrade, CoeGrade coeGrade, CoeProduct product) {
		
		Integer ratesU = userGrade.getRates();
		
		Integer priceCur = product.getPriceCur();
		Integer strategy = product.getStrategy();

		Integer ratesP = product.getRates();
		if(ratesP==null) {
			ratesP=100;//无折扣
		}

		int gradeP = product.getGrade();
		int ratesS = coeGrade.getRates();

		// 用户折扣系数不能低于产品设定的最低折扣系数
		int ratesReal = ratesU;
		
		
		if(ratesU > ratesS && ratesS>ratesP) {
			ratesReal = ratesS;
		}
		if (ratesReal < ratesP) {
			ratesReal = ratesP;
		}

		int base = coeGrade.getBaseRadix();
		int coeGradeU = coeGrade.getGrade();

		if (CoeProduct.STRATEGY_4_FREE.getValueInt().equals(strategy)) {
			return 0;
		} else if (CoeProduct.STRATEGY_0_COMMON.getValueInt().equals(strategy) || CoeProduct.STRATEGY_1_SPECIAL.equals(strategy)) {
			return priceCur;
		} else if (CoeProduct.STRATEGY_3_ALL.getValueInt().equals(strategy)) {
			Integer payPrice = priceCur * ratesReal / base;
			return payPrice;
		} else if (CoeProduct.STRATEGY_2_GRADE.getValueInt().equals(strategy)) {
			if (coeGradeU >= gradeP) {
				Integer payPrice = priceCur * ratesReal / base;
				return payPrice;
			} else {
				return priceCur;
			}
		}else if(CoeProduct.STRATEGY_5_GRADE_FREE.getValueInt().equals(strategy)) {
			if(coeGradeU>=gradeP) {
				return 0;//等级免费
			}else {
				//走等级STRATEGY_3_ALL优惠策略
				Integer payPrice = priceCur * ratesReal / base;
				return payPrice;
			}
		}

		return priceCur;
	}
	
	@Transactional
	@Override
	public CoeOrder mergeEntity(CoeOrder coeOrder) {
		Long id = coeOrder.getId();
		LOGGER.debug("--- service.mergeEntity begin, id is {} ---------  ",id);
		CoeOrder entity = findById(id);
		if(entity != null) {
			entity.setLastModifiedBy(coeOrder.getLastModifiedBy());
			DateTime lastModifiedDate = new DateTime();
			entity.setLastModifiedDate(lastModifiedDate);
			save(entity);
			entity.setSuccess(CoeOrder.SUCCESS_YES);
		}
		LOGGER.debug("--- service.mergeEntity end , id is:{} ---------", id);
		return entity;
	}

	@Transactional
	@Override
	public CoeOrder saveOrUpdate(CoeOrder coeOrder) {
		Long id = coeOrder.getId();
		LOGGER.debug("--- service.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrder entity = null;
		if(null != id) {
			entity = mergeEntity(coeOrder);
		}else {
			entity = persistEntity(coeOrder);
		}
		LOGGER.debug("--- service.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrder> findPage(CoeOrder coeOrder, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Page<CoeOrder> page = coeOrderDao.findPage(coeOrder, pageable);
		return page;
	}
	@Override
	public Page<CoeOrder> findPageForMyOrderList(CoeOrderBo coeOrder, Pageable pageable) {
		LOGGER.debug("--- service.findPage ---------  ");
		Long userId = coeOrder.getUserId();
		Page<CoeOrder> page = null;
		
		if(null!=userId) {
			List<CoeOrder> content = null;
			String title = coeOrder.getTitle();
			if(StringUtils.isNotBlank(title)) {
				page = findOrderByProduct(coeOrder, pageable);
			}else {
				page = coeOrderDao.findPageForMyOrderList(coeOrder, pageable);
				//coeOrderProductService.findByIdList(idList)
				content = page.getContent();
				if(null != content && !content.isEmpty()) {
					List<Long> orderIdList = new ArrayList<>();
					Map<Long, CoeOrder> orderMap = new HashMap<>();
					for(CoeOrder order : content) {
						Long id = order.getId();
						orderIdList.add(id);
						orderMap.put(id, order);
					}
					List<CoeOrderProduct>  productList = coeOrderProductService.findByOrderIdList(orderIdList);
					if(null != productList && !productList.isEmpty()) {
						for(CoeOrderProduct product : productList) {
							Long orderId = product.getOrderId();
							CoeOrder order = orderMap.get(orderId);
							if(null != order) {
								List<CoeOrderProduct>  entityList = order.getProductList();
								if(null==entityList) {
									entityList = new ArrayList<>();
									order.setProductList(entityList);
								}
								entityList.add(product);
							}
						}
					}
				}
			}
		}
		return page;
	}
	

	/**
	 * @param coeOrder
	 * @param pageable
	 */
	private Page<CoeOrder> findOrderByProduct(CoeOrderBo coeOrder, Pageable pageable) {
		String title = coeOrder.getTitle();
		Jit4jPageResult pageResult = null;
		long total = 0l ;
		List<CoeOrder> orderList = new ArrayList<>();
		if(StringUtils.isNotBlank(title)) {
			CoeOrderProductBo orderProductBo = new CoeOrderProductBo();
			orderProductBo.setBeginTime(coeOrder.getBeginTime());
			orderProductBo.setEndTime(coeOrder.getEndTime());
			orderProductBo.setTitle(title);
			orderProductBo.setUserId(coeOrder.getUserId());
			Page<CoeOrderProduct> page = coeOrderProductService.findPageForMyOrderList(orderProductBo, pageable);
			List<CoeOrderProduct> content = page.getContent();
			if(null != content && !content.isEmpty()) {
				total = page.getTotalElements();
				//归类成订单为主
				Map<Long, CoeOrder> map = new HashMap<>();
				for(CoeOrderProduct c : content) {
					Long id = c.getOrderId();
					CoeOrder order = map.get(id);
					if(null == order) {
						order = c.getCoeOrder();
						map.put(id, order);
					}
					orderList.add(order);
					List<CoeOrderProduct> pList = order.getProductList();
					if(null == pList) {
						pList = new ArrayList<>();
						order.setProductList(pList);
					}
					c.setCoeOrder(null);
					pList.add(c);
					
					//order.getProductList().get(0);
				}
			}
			pageResult = new Jit4jPageResult(orderList, pageable,total);
		}
		return pageResult;
	}
	
	@Override
	public Page<CoeOrder> findPageForOwnerOrderList(CoeOrderBo coeOrder, Pageable pageable){
		Long ownerId = coeOrder.getOwnerId();
		Page<CoeOrder> page = null;
		if(null!=ownerId) {
			page = coeOrderDao.findPageForOwnerOrderList(coeOrder, pageable);
		}
		return page;
	}

	@Transactional
	@Override
	public CoeOrder mergeEntityForPull(CoeOrder coeOrder) {
		Long id = coeOrder.getId();
		CoeOrder entity = findById(id);
		if(null != entity) {
			entity.setPullTime(coeOrder.getPullTime());
			entity.setLastModifiedBy(coeOrder.getLastModifiedBy());
			entity.setPullStatus(coeOrder.getPullStatus());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
		}
		return entity;
	}
	
	@Transactional
	public CoeOrder mergeEntityForEmail(CoeOrderBo coeOrder) {
		Long id = coeOrder.getId();
		LOGGER.debug("--- service.mergeEntityForEmail begin, id is {} ---------  ",id);
		CoeOrder entity = findById(id);
		if(null != entity) {
			entity.setEmail(coeOrder.getEmail());
			entity.setLastModifiedBy(coeOrder.getLastModifiedBy());
			entity.setLastModifiedDate(new DateTime());
			save(entity);
		}
		LOGGER.debug("--- service.mergeEntityForEmail end , id is:{} ---------", id);
		return entity;
	}

}
