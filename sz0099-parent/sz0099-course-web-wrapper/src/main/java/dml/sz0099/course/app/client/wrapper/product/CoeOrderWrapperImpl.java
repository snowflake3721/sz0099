package dml.sz0099.course.app.client.wrapper.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.jit4j.app.persist.entity.auth.User;
import org.jit4j.app.persist.entity.auth.UserWechat;
import org.jit8j.core.util.Base64Util;
import org.jit8j.core.util.RandomUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.order.CoeOrderDelegate;
import dml.sz0099.course.app.biz.delegate.product.CoeGradeDelegate;
import dml.sz0099.course.app.biz.delegate.user.CoeUserDelegate;
import dml.sz0099.course.app.persist.entity.order.CoeOrder;
import dml.sz0099.course.app.persist.entity.order.CoeOrderProduct;
import dml.sz0099.course.app.persist.entity.order.bo.CoeOrderBo;
import dml.sz0099.course.app.persist.entity.product.CoeGrade;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;
import dml.sz0099.course.app.persist.entity.user.CoeUser;
import dml.sz0099.course.app.persist.entity.user.CoeUserGrade;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CoeOrderWrapperImpl,组件封装
 * @author bruce yang at 2018-08-29 21:12:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Service
public class CoeOrderWrapperImpl implements CoeOrderWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoeOrderWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CoeOrderDelegate coeOrderDelegate;
	
	@Autowired
	private CoeGradeDelegate coeGradeDelegate;
	
	@Autowired
	private CoeUserDelegate coeUserDelegate;
	
	@Autowired
	private CoeProductWrapper coeProductWrapper;
	/**
	 * 根据Id查询CoeOrder实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeOrder findById(Long id) {
		LOGGER.debug("--- CoeOrderWrapperImpl.findById begin --------- id is:{} ", id);
		CoeOrder coeOrder = coeOrderDelegate.findById(id);
		LOGGER.debug("--- CoeOrderWrapperImpl.findById end --------- id is:{} , result is {} ", id, coeOrder);
		return coeOrder;
	}
	
	public CoeOrder findById(Long id, boolean withProduct) {
		CoeOrder coeOrder = coeOrderDelegate.findById(id, withProduct);
		return coeOrder;
	}
	
	@Override
	public CoeOrder findByAid(Long aid) {
		LOGGER.debug("--- wrapper>>>findByAid begin --------- aid is:{} ", aid);
		CoeOrder coeOrder = coeOrderDelegate.findByAid(aid);
		LOGGER.debug("--- wrapper>>>findByAid end --------- aid is:{} , result is {} ", aid, coeOrder);
		return coeOrder;
	}
	
	/**
	 * 根据IdList查询CoeOrder实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeOrder> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CoeOrderWrapperImpl.findByIdList begin ---------  ");
		List<CoeOrder> coeOrderList = coeOrderDelegate.findByIdList(idList);
		LOGGER.debug("--- CoeOrderWrapperImpl.findByIdList end ---------  result is {} ",  coeOrderList);
		return coeOrderList;
	}
	
	@Override
	public CoeOrder persistEntity(CoeOrder coeOrder) {
		LOGGER.debug("--- CoeOrderWrapperImpl.persistEntity begin ---------  ");
		CoeOrder entity = coeOrderDelegate.persistEntity(coeOrder);
		Long id = coeOrder.getId();
		LOGGER.debug("--- CoeOrderWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}
	
	/**
	 * 根据产品id生成预订单
	 * @param productId
	 * @return
	 */
	public CoeOrder generateOrder(CoeOrder order) {
		CoeOrder entity = coeOrderDelegate.generateOrder(order);
		return entity;
	}
	
	/**
	 * 订单生成：将产品与用户绑定
	 * @param product
	 * @param user
	 * @return
	 */
	public CoeOrder generateOrder(CoeProduct product, User user) {
		//根据产品id查询产品
		CoeOrder order = convert2CoeOrder(product, user);
		CoeOrder entity = generateOrder(order);
		return entity;
	}
	
	public CoeOrder convert2CoeOrder(CoeProduct product, User user) {
		CoeOrder order = new CoeOrder();
		//order.setGrade(CoeGrade.GRADE_L1); //TODO 设置用户等级， 用户等级信息列表需要由CoeUserGrade提供
		order.setEmail(user.getEmail());
		order.setMobile(user.getMobile());
		
		Long userId = user.getId();
		order.setLastModifiedBy(userId);
		order.setCreatedBy(userId);
		
		UserWechat wechat = user.getWechat();
		order.setNickname(Base64Util.encode(wechat.getNickname()));
		order.setOpenIdwc(wechat.getOpenIdwc());
		
		order.setQq(user.getQq());
		
		//order.setPullCode(pullCode); 用户支付后，客服发货时可设定该值
		//order.setRemark(remark);//用户确认订单时设置
		
		order.setUserId(userId);
		order.setOwnerId(product.getUserId());
		
		CoeOrderProduct op = new CoeOrderProduct();
		op.setTitle(product.getTitle());
		op.setProductId(product.getId());
		op.setLink(product.getLink());
		op.setName(product.getName());
		op.setCreatedBy(userId);
		op.setLastModifiedBy(userId);
		
		op.setPriceCur(product.getPriceCur());//出售价
		op.setPriceOri(product.getPriceOri());//原价
		op.setTitleLower(product.getTitleLower());
		op.setProductNo(product.getProductNo());
		op.setOwnerId(product.getUserId());//设置该订单归属产品所有者(客服)
		
		//提取时：位于外部网盘的资料不受控制，位于本服务器的资源提取时要校验是否本人提取
		//外部链接失效时行不受控制，本服务器内的失效时间为15天，过期无效
		//只有当状态处于 发货 以上时，以下信息才可见
		Integer pullMehtod = product.getPullMethod();
		if(CoeProduct.PULL_METHOD_YUN.getValueInt().equals(pullMehtod)) {
			op.setPullCode(product.getPullCode());
		}else if(CoeProduct.PULL_METHOD_INNER.getValueInt().equals(pullMehtod)) {
			op.setPullCode(RandomUtil.getValidateCode());//生成6位数字提取码
		}else {
			op.setPullCode(product.getPullCode());
		}
		op.setLink(product.getLink());//设置提取链接
		op.setPullMethod(product.getPullMethod());//设置提取方式
		op.setUserId(userId);
		Date orderTime = new Date();
		op.setOrderTime(orderTime);
		
		
		CoeUser coeUser = createCoeUser(order);
		CoeUserGrade userGrade = coeUser.getUserGrade();
		Integer grade = CoeGrade.GRADE_L0;
		if(null != userGrade) {
			grade = userGrade.getGrade();
		}
		//查询系统等级定义
		CoeGrade coeGrade = coeGradeDelegate.findByGrade(grade);
		//计算实付金额,由 用户自身折扣、系统等级折扣、产品设置折扣 三部分组成
		Integer pricePay = calPayPrice(userGrade,coeGrade,product);
		op.setPricePay(pricePay);
		order.setPrice(pricePay);//只有一个产品，若有多个产品，则求和
		
		order.setGrade(grade);
		order.setRates(coeGrade.getRates());
		order.setOrderTime(orderTime);
		
		
		
		
		List<CoeOrderProduct> productList = new ArrayList<>(1);
		productList.add(op);
		order.setProductList(productList);
		
		return order;
	}
	
	@Override
	public CoeOrder confirmOrder(CoeOrder order) {
		CoeOrder entity = coeOrderDelegate.confirmOrder(order);
		List<Long> productIdList = order.getProductIdList();
		coeProductWrapper.findByIdList(productIdList);
		
		/*Long productId = entity.getProductId();
		CoeProduct product = coeProductService.findById(productId);
		
		
		Integer pullMehtod = product.getPullMethod();
		if(CoeProduct.PULL_METHOD_YUN.getValueInt().equals(pullMehtod)) {
			entity.setPullCode(product.getPullCode());
		}else if(CoeProduct.PULL_METHOD_INNER.getValueInt().equals(pullMehtod)) {
			entity.setPullCode(RandomUtil.getValidateCode());//生成6位数字提取码
		}
		entity.setLink(product.getLink());//设置提取链接
		entity.setPullMethod(product.getPullMethod());//设置提取方式
		*/
		return entity;
	}

	@Override
	public CoeOrder mergeEntity(CoeOrder coeOrder) {
		Long id = coeOrder.getId();
		LOGGER.debug("--- CoeOrderWrapperImpl.mergeEntity begin, id is {} ---------  ",id);
		CoeOrder entity = coeOrderDelegate.mergeEntity(coeOrder);
		LOGGER.debug("--- CoeOrderWrapperImpl.mergeEntity end , id is:{} ---------", id);
		return entity;
	}
	
	public CoeOrder mergeEntityForPull(CoeOrder coeOrder) {
		Long id = coeOrder.getId();
		LOGGER.debug("--- CoeOrderWrapperImpl.mergeEntityForPull begin, id is {} ---------  ",id);
		CoeOrder entity = coeOrderDelegate.mergeEntityForPull(coeOrder);
		LOGGER.debug("--- CoeOrderWrapperImpl.mergeEntityForPull end , id is:{} ---------", id);
		return entity;
	}
	
	public CoeOrder mergeEntityForEmail(CoeOrderBo coeOrder) {
		Long id = coeOrder.getId();
		LOGGER.debug("--- CoeOrderWrapperImpl.mergeEntityForEmail begin, id is {} ---------  ",id);
		CoeOrder entity = coeOrderDelegate.mergeEntityForEmail(coeOrder);
		LOGGER.debug("--- CoeOrderWrapperImpl.mergeEntityForEmail end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public CoeOrder saveOrUpdate(CoeOrder coeOrder) {
		Long id = coeOrder.getId();
		LOGGER.debug("--- CoeOrderWrapperImpl.saveOrUpdate begin, id is {} ---------  ",id);
		CoeOrder entity = coeOrderDelegate.saveOrUpdate(coeOrder);
		LOGGER.debug("--- CoeOrderWrapperImpl.saveOrUpdate end , id is:{} ---------", id);
		return entity;
	}

	@Override
	public Page<CoeOrder> findPage(CoeOrder coeOrder, Pageable pageable) {
		LOGGER.debug("--- CoeOrderWrapperImpl.findPage ---------  ");
		Page<CoeOrder> page = coeOrderDelegate.findPage(coeOrder, pageable);
		return page;
	}
	
	@Override
	public Page<CoeOrder> findPageForMyOrderList(CoeOrderBo coeOrder, Pageable pageable) {
		LOGGER.debug("--- CoeOrderWrapperImpl.findPageForMyOrderList ---------  ");
		Long userId = coeOrder.getUserId();
		Page<CoeOrder> page = null;
		if(null!=userId) {
			page = coeOrderDelegate.findPageForMyOrderList(coeOrder, pageable);
			
		}
		return page;
	}
	
	@Override
	public Page<CoeOrder> findPageForOwnerOrderList(CoeOrderBo coeOrder, Pageable pageable){
		LOGGER.debug("--- CoeOrderWrapperImpl.findPageForMyOrderList ---------  ");
		Long ownerId = coeOrder.getOwnerId();
		Page<CoeOrder> page = null;
		if(null!=ownerId) {
			page = coeOrderDelegate.findPageForOwnerOrderList(coeOrder, pageable);
		}
		return page;
	}
	
	public CoeOrder mergeForInprocess(CoeOrder order) {
		return coeOrderDelegate.mergeForInprocess(order);
	}
	
	public CoeOrder mergeForSent(CoeOrder order) {
		return coeOrderDelegate.mergeForSent(order);
	}
	
	public Integer calPayPrice(CoeUserGrade userGrade, CoeGrade coeGrade, CoeProduct product) {
		return coeOrderDelegate.calPayPrice(userGrade, coeGrade, product);
	}
	
	private CoeUser createCoeUser(CoeOrder coeOrder) {
		//查询用户及等级是否存在，如不存在，创建用户
		Long userId = coeOrder.getUserId();
		CoeUser coeUser = coeUserDelegate.findByUserId(userId);
		if(null == coeUser) {
			coeUser = convertToCoeUser(coeOrder);
			coeUser = coeUserDelegate.createCoeUser(coeUser);
		}
		
		return coeUser;
	}
	
	/**
	 * @param coeOrder
	 * @return
	 */
	private CoeUser convertToCoeUser(CoeOrder order) {
		CoeUser coeUser = new CoeUser();
		coeUser.setCreatedDate(new DateTime());
		coeUser.setCreatedBy(order.getCreatedBy());
		coeUser.setUserId(order.getUserId());
		coeUser.setOpenIdwc(order.getOpenIdwc());
		coeUser.setNickname(order.getNickname());
		
		//  以下三个信息，在确认下单时要更新一遍，由前端页面收集而来
		coeUser.setMobile(order.getMobile());
		coeUser.setQq(order.getQq());
		coeUser.setEmail(order.getEmail());
		return coeUser;
	}
	
}
