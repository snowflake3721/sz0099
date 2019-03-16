/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.article;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

import dml.sz0099.course.app.persist.entity.category.Category;

/**
 * <pre>
 * @formatter:off
 *
 * 类别与产品关联
 * 一个产品属于一个类别
 * @author bruce yang at 2018-09-10 19:52:40
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-10	basic init
 * 
 * @formatter:on
 * </pre>
 */

@Entity
@Table(name="s9_art_CategArticle")
public class CoeCategArticle extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	@Column(name="categoryId" ,length = 20, columnDefinition = "BIGINT(20) COMMENT '类别Id'")
	private Long baseId;//对应CategoryRef.baseId==Category.id
	
	@JoinColumn(insertable=false,updatable=false,name="categoryId",referencedColumnName="id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Category category;//仅支持绑定一个类别
	
	@Column(name="articleId" ,length = 20, columnDefinition = "BIGINT(20) COMMENT '文章Id'")
	private Long mainId;//对应CategoryRef.mainId==CoeArticle.id
	
	
	@Column(name="cayMainId", length = 20, columnDefinition = "BIGINT(20) COMMENT '圈子id, 系统id'")
	private Long cayMainId;//圈子id, 平台id(platId)==Category.mainId 冗余存储
	
	@Column(name="caySubId", length = 20, columnDefinition = "BIGINT(20) COMMENT 'subId'")
	private Long caySubId;//可以是行业id==Category.subId 冗余存储
	
	@JoinColumn(insertable=false,updatable=false,name="articleId", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.LAZY)
	private CoeArticle article;


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	public CoeArticle getArticle() {
		return article;
	}

	public void setArticle(CoeArticle article) {
		this.article = article;
	}

	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public Long getCayMainId() {
		return cayMainId;
	}

	public void setCayMainId(Long cayMainId) {
		this.cayMainId = cayMainId;
	}

	public Long getCaySubId() {
		return caySubId;
	}

	public void setCaySubId(Long caySubId) {
		this.caySubId = caySubId;
	}
	
}
