package dml.sz0099.course.app.persist.dao.product;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dml.sz0099.course.app.persist.entity.product.CoeProduct;
import dml.sz0099.course.app.persist.repository.product.CourseProductRepository;

/**
 * CourseProductDaoImpl
 * 数据访问封装
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2017-08-16  basic init
 * 
 * 
 */
@Repository
public class CourseProductDaoImpl extends GenericDaoImpl<CoeProduct, Long> implements CourseProductDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseProductDaoImpl.class);
	
	@Autowired
	private CourseProductRepository courseProductRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = courseProductRepository;
	}

	/**
	 * 根据Id查询CourseProduct实体对象
	 * @param id
	 * @return
	 */
	@Override
	public CoeProduct findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeProduct courseProduct = courseProductRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, courseProduct);
		return courseProduct;
	}
	
	/**
	 * 根据IdList查询CourseProduct实体对象列表
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeProduct> courseProductList = courseProductRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ",  courseProductList);
		return courseProductList;
	}

}
