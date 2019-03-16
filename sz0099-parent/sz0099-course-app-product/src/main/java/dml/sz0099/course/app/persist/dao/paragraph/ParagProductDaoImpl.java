package dml.sz0099.course.app.persist.dao.paragraph;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jit4j.core.persist.dao.GenericDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.paragraph.ParagProduct;
import dml.sz0099.course.app.persist.repository.paragraph.ParagProductRepository;
import dml.sz0099.course.app.persist.specification.paragraph.ParagProductSpecification;

/**
 * ParagProductDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class ParagProductDaoImpl extends GenericDaoImpl<ParagProduct, Long> implements ParagProductDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagProductDaoImpl.class);

	@Autowired
	private ParagProductRepository paragProductRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = paragProductRepository;
	}

	/**
	 * 根据Id查询ParagProduct实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ParagProduct findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ParagProduct paragProduct = paragProductRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, paragProduct);
		return paragProduct;
	}

	@Override
	public ParagProduct findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ParagProduct paragProduct = paragProductRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, paragProduct);
		return paragProduct;
	}

	/**
	 * 根据IdList查询ParagProduct实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagProduct> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ParagProduct> paragProductList = paragProductRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", paragProductList);
		return paragProductList;
	}

	/**
	 * 条件查询
	 */
	public Page<ParagProduct> findPage(ParagProduct paragProduct, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ParagProductSpecification.getConditionWithQsl(paragProduct);
		Page<ParagProduct> page = paragProductRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ParagProduct entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}



	public Page<ParagProduct> findByMainId(Long productId, Pageable pageable ){		

		LOGGER.debug("-------dao>>>ParagProductDaoImpl.findByMainId----------begin---------");
		Page<ParagProduct> page = paragProductRepository.findByMainId( productId,  pageable );
		return page;
	}
	
	public Page<ParagProduct> findByMainIdAndUserId(Long productId, Long userId, Pageable pageable ){
		return paragProductRepository.findByMainIdAndUserId(productId, userId, pageable);
	}




	public void deleteByMainIdAndUserId(Long productId, Long userId ){		

		LOGGER.debug("-------dao>>>ParagProductDaoImpl.deleteByMainIdAndUserId----------begin---------");
		paragProductRepository.deleteByMainIdAndUserId( productId,  userId );
	}
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		paragProductRepository.deleteByParagIdAndUserId(paragId,userId);
	}

	@Override
	public List<ParagProduct> findListByMainId(Long productId) {
		List<ParagProduct> content = paragProductRepository.findListByMainId(productId);
		return content;
	}
	
	public List<ParagProduct> findListByMainIdAndUserId(Long productId,Long userId){
		List<ParagProduct> content = paragProductRepository.findListByMainIdAndUserId(productId, userId);
		return content;
	}
	
	public Long countByMainId(Long productId) {
		return paragProductRepository.countByMainId(productId);
	}


}
