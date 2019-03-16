package dml.sz0099.course.app.biz.service.product;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.jit4j.core.service.GenericServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.persist.dao.product.CourseProductDao;
import dml.sz0099.course.app.persist.entity.product.CoeProduct;


/**
 * 
 * @formatter:off
 * description: CourseProductServiceImpl 业务服务层
 * @author bruce yang at 2018-08-24 16:05:05
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
@Service
public class CourseProductServiceImpl extends GenericServiceImpl<CoeProduct, Long> implements CourseProductService, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseProductServiceImpl.class);

	@Autowired
	private CourseProductDao courseProductDao;

	/**
	 * init
	 */
	@Override
	@PostConstruct
	public void initDao() {
		this.genericDao = courseProductDao;
	}

	/**
	 * 根据Id查询CourseProduct实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeProduct findById(Long id) {
		LOGGER.debug("--- service>>>findById begin --------- id is:{} ", id);
		CoeProduct courseProduct = courseProductDao.findById(id);
		LOGGER.debug("--- service>>>findById end --------- id is:{} , result is {} ", id, courseProduct);
		return courseProduct;
	}

	/**
	 * 根据IdList查询CourseProduct实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- service>>>findByIdList begin ---------  ");
		List<CoeProduct> courseProductList = courseProductDao.findByIdList(idList);
		LOGGER.debug("--- service>>>findByIdList end ---------  result is {} ", courseProductList);
		return courseProductList;
	}

	@Transactional
	@Override
	public CoeProduct persistEntity(CoeProduct courseProduct) {
		LOGGER.debug("--- service>>>persistEntity begin ---------  ");
		CoeProduct entity = save(courseProduct);
		Long id = courseProduct.getId();
		LOGGER.debug("--- service>>>persistEntity end , id is:{} ---------", id);
		return entity;
	}

}
