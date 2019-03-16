package dml.sz0099.course.app.biz.delegate.product;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import dml.sz0099.course.app.biz.service.product.CourseProductService;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * CourseProductDelegateImpl
 * 服务代理接口
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Service
public class CourseProductDelegateImpl implements CourseProductDelegate, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseProductDelegateImpl.class);
	
	@Autowired
	private CourseProductService courseProductService;

	/**
	 * 根据Id查询CourseProduct实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeProduct findById(Long id) {
		LOGGER.debug("--- delegate>>>findById begin --------- id is:{} ", id);
		CoeProduct courseProduct = courseProductService.findById(id);
		LOGGER.debug("--- delegate>>>findById end --------- id is:{} , result is {} ", id, courseProduct);
		return courseProduct;
	}
	
	/**
	 * 根据IdList查询CourseProduct实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- delegate>>>findByIdList begin ---------  ");
		List<CoeProduct> courseProductList = courseProductService.findByIdList(idList);
		LOGGER.debug("--- delegate>>>findByIdList end ---------  result is {} ",  courseProductList);
		return courseProductList;
	}
	
	/**
	 * 持久化实体
	 */
	@Override
	public CoeProduct persistEntity(CoeProduct courseProduct) {
		LOGGER.debug("--- delegate>>>persistEntity begin ---------  ");
		CoeProduct entity = courseProductService.persistEntity(courseProduct);
		Long id = courseProduct.getId();
		LOGGER.debug("--- delegate>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}
}
