package dml.sz0099.course.app.persist.specification.profession;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.querydsl.core.types.dsl.BooleanExpression;

import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.entity.profession.QProfession;
import dml.sz0099.course.app.persist.entity.profession.QCategProfession;
import dml.sz0099.course.app.persist.entity.category.Category;
import dml.sz0099.course.app.persist.entity.category.QCategory;
import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.entity.profession.Profession;
import dml.sz0099.course.app.persist.entity.profession.QCategProfession;
import dml.sz0099.course.app.persist.entity.profession.QProfession;

/**
 * 
 * CategProfessionSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:32:02
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 *
 */
/**
 * 
 * @formatter:off
 * 
 * description: CategProfessionSpecification 条件构造
 * @author bruce yang at 2018-08-24 15:41:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-24	basic init
 * 
 * @formatter:on
 */
public class CategProfessionSpecification {

	private CategProfessionSpecification() {
		throw new IllegalStateException("CategProfessionSpecification class");
	}

	/**
	 * query by Specification
	 * 
	 * @param module
	 * @return
	 */
	public static Specification<CategProfession> getConditionByCode(final CategProfession categProfession) {
		return new Specification<CategProfession>() {
			public Predicate toPredicate(Root<CategProfession> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				//do something
				return null;
			}
		};
		
	}

	/**
	 * query by QslExpression 优先使用此方式
	 * 
	 * @param categProfession
	 * @return
	 */
	public static BooleanExpression getConditionWithQsl(final CategProfession categProfession, final List<Long> excludeMainIdList) {

		QCategProfession qcategProfession = QCategProfession.categProfession;

		QProfession qprofession = qcategProfession.profession;
		
		QCategory qcategory = qcategProfession.category;

		BooleanExpression eq = qprofession.deleted.isFalse();
		
		Category category = categProfession.getCategory();
		if(null != category) {
			String code = category.getCode();
			eq=eq.and(qcategProfession.baseId.eq(qcategory.id));
			if(StringUtils.isNotBlank(code)) {
				eq=eq.and(qcategory.code.eq(code));
			}
		}
		
		Long baseId = categProfession.getBaseId();
		if(null != baseId) {
			eq=eq.and(qcategProfession.baseId.eq(baseId));
		}
		
		if(null != excludeMainIdList && !excludeMainIdList.isEmpty()) {
			eq=eq.and(qcategProfession.mainId.notIn(excludeMainIdList));
		}
		
		Profession profession = categProfession.getProfession();
		if(null != profession) {
			//eq.and(qprofession.mainId.eq(qprofession.id));
			String title = profession.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eq=eq.and(qprofession.titleLower.like(sb.toString()));
			}
			
			String name = profession.getName();
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(name)).append("%");
				eq=eq.and(qprofession.name.like(sb.toString()));
			}
			
			String professionNo = profession.getProfessionNo();
			if(StringUtils.isNotBlank(professionNo)) {
				eq = eq.and(qprofession.professionNo.eq(professionNo));
			}
			
			Integer publishStatus = profession.getPublishStatus();
			if(null != publishStatus) {
				eq = eq.and(qprofession.publishStatus.eq(publishStatus));
			}
		}
		
		Long userId = profession.getUserId();
		if(null != userId) {
			eq=eq.and(qprofession.userId.eq(userId));
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionFromDetail(final CategProfession categProfession) {

		QCategProfession qcategProfession = QCategProfession.categProfession;

		QProfession qprofession = qcategProfession.profession;
		
		QCategory qcategory = qcategProfession.category;

		BooleanExpression eq = qprofession.deleted.isFalse();
		
		Category category = categProfession.getCategory();
		if(null != category) {
			String code = category.getCode();
			eq=eq.and(qcategProfession.baseId.eq(qcategory.id));
			if(StringUtils.isNotBlank(code)) {
				eq=eq.and(qcategory.code.eq(code));
			}
		}
		
		Long baseId = categProfession.getBaseId();
		if(null != baseId) {
			eq=eq.and(qcategProfession.baseId.eq(baseId));
		}
		
		Profession profession = categProfession.getProfession();
		if(null != profession) {
			//eq.and(qprofession.mainId.eq(qprofession.id));
			String title = profession.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eq=eq.and(qprofession.titleLower.like(sb.toString()));
			}
			
			String name = profession.getName();
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(name)).append("%");
				eq=eq.and(qprofession.name.like(sb.toString()));
			}
			
			String professionNo = profession.getProfessionNo();
			if(StringUtils.isNotBlank(professionNo)) {
				eq = eq.and(qprofession.professionNo.eq(professionNo));
			}
			
			Integer publishStatus = profession.getPublishStatus();
			if(null != publishStatus) {
				eq = eq.and(qprofession.publishStatus.eq(publishStatus));
			}
		}
		
		//从detail跳转至搜索页，过滤掉用户本身的技能
		Long userId = profession.getUserId();
		if(null != userId) {
			eq=eq.and(qprofession.userId.ne(userId));
		}
		
		return eq;

	}
	
	public static BooleanExpression getConditionWithChilren(final CategProfession categProfession, final List<Long> baseIdList, final List<Long> excludeMainIdList) {

		QCategProfession qcategProfession = QCategProfession.categProfession;
		
		QProfession qprofession = qcategProfession.profession;
		
		QCategory qcategory = qcategProfession.category;

		BooleanExpression eq = qcategProfession.deleted.isFalse(). and (qprofession.disable.isFalse()). and (qprofession.deleted.isFalse());
		
		Long baseId = categProfession.getBaseId();
		if(null != baseIdList && !baseIdList.isEmpty()) {
			eq=eq.and(qcategProfession.baseId.in(baseIdList));
		}else if(null != baseId) {
			eq=eq.and(qcategProfession.baseId.eq(baseId));
		}else {
			Category category = categProfession.getCategory();
			if(null != category) {
				String code = category.getCode();
				eq=eq.and(qcategProfession.baseId.eq(qcategory.id));
				if(StringUtils.isNotBlank(code)) {
					eq=eq.and(qcategory.code.eq(code));
				}
			}
		}
		
		if(null != excludeMainIdList && !excludeMainIdList.isEmpty()) {
			eq=eq.and(qcategProfession.mainId.notIn(excludeMainIdList));
		}
		
		Profession profession = categProfession.getProfession();
		if(null != profession) {
			eq.and(qcategProfession.mainId.eq(qprofession.id));
			String title = profession.getTitle();
			if(StringUtils.isNotBlank(title)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(title)).append("%");
				eq=eq.and(qprofession.titleLower.like(sb.toString()));
			}
			
			String name = profession.getName();
			if(StringUtils.isNotBlank(name)) {
				StringBuilder sb = new StringBuilder();
				sb.append("%").append(StringUtils.lowerCase(name)).append("%");
				eq=eq.and(qprofession.name.like(sb.toString()));
			}
			
			String professionNo = profession.getProfessionNo();
			if(StringUtils.isNotBlank(professionNo)) {
				eq = eq.and(qprofession.professionNo.eq(professionNo));
			}
			
			Integer publishStatus = profession.getPublishStatus();
			if(null != publishStatus) {
				eq = eq.and(qprofession.publishStatus.eq(publishStatus));
			}
		}
		
		Long userId = profession.getUserId();
		if(null != userId) {
			eq=eq.and(qprofession.userId.eq(userId));
		}
		
		return eq;

	}
}
