package dml.sz0099.course.app.client.wrapper.product;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.product.CourseProductDelegate;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * CourseProductWrapperImpl,组件封装
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
public class CourseProductWrapperImpl implements CourseProductWrapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseProductWrapperImpl.class);
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private CourseProductDelegate courseProductDelegate;
	
	/**
	 * 根据Id查询CourseProduct实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeProduct findById(Long id) {
		LOGGER.debug("--- CourseProductWrapperImpl.findById begin --------- id is:{} ", id);
		CoeProduct courseProduct = courseProductDelegate.findById(id);
		LOGGER.debug("--- CourseProductWrapperImpl.findById end --------- id is:{} , result is {} ", id, courseProduct);
		return courseProduct;
	}
	
	/**
	 * 根据IdList查询CourseProduct实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- CourseProductWrapperImpl.findByIdList begin ---------  ");
		List<CoeProduct> courseProductList = courseProductDelegate.findByIdList(idList);
		LOGGER.debug("--- CourseProductWrapperImpl.findByIdList end ---------  result is {} ",  courseProductList);
		return courseProductList;
	}
	
	@Override
	public CoeProduct persistEntity(CoeProduct courseProduct) {
		LOGGER.debug("--- CourseProductWrapperImpl.persistEntity begin ---------  ");
		CoeProduct entity = courseProductDelegate.persistEntity(courseProduct);
		Long id = courseProduct.getId();
		LOGGER.debug("--- CourseProductWrapperImpl.persistEntity end , id is:{} ---------", id);
		return entity;
	}
}
