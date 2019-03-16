package dml.sz0099.course.app.persist.dao.profession;

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

import dml.sz0099.course.app.persist.entity.profession.ParagProfession;
import dml.sz0099.course.app.persist.repository.profession.ParagProfessionRepository;
import dml.sz0099.course.app.persist.specification.profession.ParagProfessionSpecification;

/**
 * ParagProfessionDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository("profParagProfessionDaoImpl")
public class ParagProfessionDaoImpl extends GenericDaoImpl<ParagProfession, Long> implements ParagProfessionDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagProfessionDaoImpl.class);

	@Autowired
	private ParagProfessionRepository paragProductRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = paragProductRepository;
	}

	/**
	 * 根据Id查询ParagProfession实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ParagProfession findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		ParagProfession paragProduct = paragProductRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, paragProduct);
		return paragProduct;
	}

	@Override
	public ParagProfession findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		ParagProfession paragProduct = paragProductRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, paragProduct);
		return paragProduct;
	}

	/**
	 * 根据IdList查询ParagProfession实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<ParagProfession> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<ParagProfession> paragProductList = paragProductRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", paragProductList);
		return paragProductList;
	}

	/**
	 * 条件查询
	 */
	public Page<ParagProfession> findPage(ParagProfession paragProduct, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = ParagProfessionSpecification.getConditionWithQsl(paragProduct);
		Page<ParagProfession> page = paragProductRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		ParagProfession entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}



	public Page<ParagProfession> findByMainId(Long professionId, Pageable pageable ){		

		LOGGER.debug("-------dao>>>ParagProfessionDaoImpl.findByMainId----------begin---------");
		Page<ParagProfession> page = paragProductRepository.findByMainId( professionId,  pageable );
		return page;
	}
	
	public Page<ParagProfession> findByMainIdAndUserId(Long professionId, Long userId, Pageable pageable ){
		return paragProductRepository.findByMainIdAndUserId(professionId, userId, pageable);
	}




	public void deleteByProfessionIdAndUserId(Long professionId, Long userId ){		

		LOGGER.debug("-------dao>>>ParagProfessionDaoImpl.deleteByProfessionIdAndUserId----------begin---------");
		paragProductRepository.deleteByProfessionIdAndUserId( professionId,  userId );
	}
	
	public void deleteByParagIdAndUserId(Long paragId, Long userId, boolean cascade ) {
		paragProductRepository.deleteByParagIdAndUserId(paragId,userId);
	}

	@Override
	public List<ParagProfession> findListByMainId(Long professionId) {
		List<ParagProfession> content = paragProductRepository.findListByMainId(professionId);
		return content;
	}
	
	public List<ParagProfession> findListByMainIdAndUserId(Long professionId,Long userId){
		List<ParagProfession> content = paragProductRepository.findListByMainIdAndUserId(professionId, userId);
		return content;
	}
	
	public Long countByMainId(Long professionId) {
		return paragProductRepository.countByMainId(professionId);
	}


}
