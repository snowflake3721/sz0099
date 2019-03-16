package dml.sz0099.course.app.persist.dao.user;

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

import dml.sz0099.course.app.persist.entity.user.CoeUserImage;
import dml.sz0099.course.app.persist.repository.user.CoeUserImageRepository;
import dml.sz0099.course.app.persist.specification.user.CoeUserImageSpecification;

/**
 * CoeUserImageDaoImpl 数据访问封装 ----------------------------------------------------------------------------------------
 * Requirement Author Date Function init bruceyang 2017-08-16 basic init
 * 
 * 
 */
@Repository
public class CoeUserImageDaoImpl extends GenericDaoImpl<CoeUserImage, Long> implements CoeUserImageDao, Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoeUserImageDaoImpl.class);

	@Autowired
	private CoeUserImageRepository coeUserImageRepository;

	@Override
	@PostConstruct
	public void initRepository() {
		this.genericJpaRepository = coeUserImageRepository;
	}

	/**
	 * 根据Id查询CoeUserImage实体对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CoeUserImage findById(Long id) {
		LOGGER.debug("--- dao>>>findById begin --------- id is:{} ", id);
		CoeUserImage coeUserImage = coeUserImageRepository.findById(id);
		LOGGER.debug("--- dao>>>findById end --------- id is:{} , result is {} ", id, coeUserImage);
		return coeUserImage;
	}

	@Override
	public CoeUserImage findByAid(Long aid) {
		LOGGER.debug("--- dao>>>findByAid begin --------- aid is:{} ", aid);
		CoeUserImage coeUserImage = coeUserImageRepository.findByAid(aid);
		LOGGER.debug("--- dao>>>findByAid end --------- aid is:{} , result is {} ", aid, coeUserImage);
		return coeUserImage;
	}

	/**
	 * 根据IdList查询CoeUserImage实体对象列表
	 * 
	 * @param idList
	 * @return
	 */
	@Override
	public List<CoeUserImage> findByIdList(List<Long> idList) {
		LOGGER.debug("--- dao>>>findByIdList begin ---------  ");
		List<CoeUserImage> coeUserImageList = coeUserImageRepository.findByIdList(idList);
		LOGGER.debug("--- dao>>>findByIdList end ---------  result is {} ", coeUserImageList);
		return coeUserImageList;
	}

	/**
	 * 条件查询
	 */
	public Page<CoeUserImage> findPage(CoeUserImage coeUserImage, Pageable pageable) {
		LOGGER.debug("--- dao.findPage, with qsl ---------  ");
		BooleanExpression condition = CoeUserImageSpecification.getConditionWithQsl(coeUserImage);
		Page<CoeUserImage> page = coeUserImageRepository.findAll(condition, pageable);
		return page;
	}

	@Override
	public boolean existById(Long id) {
		if(null == id) {
			return false;
		}
		CoeUserImage entity = findById(id);
		if (null == entity) {
			return false;
		}
		return true;
	}
	
	public List<CoeUserImage> findByCoeUserId(Long coeUserId) {
		List<CoeUserImage> content = coeUserImageRepository.findByCoeUserId(coeUserId);
		return content;
	}
	
	public List<CoeUserImage> findByCoeUserIdAndSubIdList(Long coeUserId, List<Long> subIdList){
		List<CoeUserImage> content = coeUserImageRepository.findByCoeUserIdAndSubIdList(coeUserId,subIdList);
		return content;
	}
	
	public List<CoeUserImage> findByCoeUserId(Long coeUserId, Long subId){
		List<CoeUserImage> content = coeUserImageRepository.findByCoeUserId(coeUserId,subId);
		return content;
	}

}
