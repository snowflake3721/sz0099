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

import org.jit4j.core.persist.entity.UserBaseEntityExtend;

import dml.sz0099.course.app.persist.entity.user.Sayword;

/**
 * <pre>
 * @formatter:off
 *
 * 文章点赞记录
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
@Table(name="s9_art_ArticleFavirate")
public class CoeArticleFavirate extends UserBaseEntityExtend implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	public static final Integer STATUS_YES = 1;//已收藏
	public static final Integer STATUS_NO = 0;//已取消
	
	
	@Transient
	private CoeArticle article;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '文章作者id'")
	private Long authorId;
	
	/**
	 * 用于接收页面传递参数，段落所属主域
	 * 这里对应articleId
	 */
	@Column(name="articleId",length = 20, columnDefinition = "BIGINT(20) COMMENT '文章Id'")
	private Long mainId;
	
	@Column(name="status",columnDefinition="int(1) COMMENT '点赞状态'")
	private Integer status;
	
	@JoinColumn(insertable=false,updatable=false,name="saywordId", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Sayword sayword;//记录当时的那句传说
	
	@Column(name="saywordId",length = 20, columnDefinition = "BIGINT(20) COMMENT '传说Id'")
	private Long saywordId;//记录当时的那句传说,建立关联关系
	

	public CoeArticle getArticle() {
		return article;
	}

	public void setArticle(CoeArticle article) {
		this.article = article;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public Long getSaywordId() {
		return saywordId;
	}

	public void setSaywordId(Long saywordId) {
		this.saywordId = saywordId;
	}

	public Sayword getSayword() {
		return sayword;
	}

	public void setSayword(Sayword sayword) {
		this.sayword = sayword;
	}


}
