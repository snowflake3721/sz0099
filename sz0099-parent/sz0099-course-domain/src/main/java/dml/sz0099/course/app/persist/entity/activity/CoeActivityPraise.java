/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.activity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.jit4j.core.persist.entity.UserBaseEntityExtend;
import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name="s9_act_ActivityPraise")
public class CoeActivityPraise extends UserBaseEntityExtend implements Serializable {

	private static final long serialVersionUID = -7481162195005128432L;
	
	public static final Integer STATUS_YES = 1;//已点赞
	public static final Integer STATUS_NO = 0;//已取消
	
	
	@Transient
	private CoeActivity activity;
	
	@Column(length = 20, columnDefinition = "BIGINT(20) COMMENT '文章作者id'")
	private Long authorId;
	
	/**
	 * 用于接收页面传递参数，段落所属主域
	 * 这里对应activityId
	 */
	@Column(name="activityId",length = 20, columnDefinition = "BIGINT(20) COMMENT '文章Id'")
	private Long mainId;
	
	@Column(name="status",columnDefinition="int(1) COMMENT '点赞状态'")
	private Integer status;
	
	//@Column(name="sayword",length=80,columnDefinition="varchar(80) COMMENT '座佑铭'")
	//private String sayword;//记录当时的那句传说
	
	@Column(name="refreshTime",columnDefinition="DATETIME COMMENT '点赞刷新时间'")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date refreshTime;//刷新时间，刷新后可提升该篇文章的点赞排名
	
	@JoinColumn(insertable=false,updatable=false,name="saywordId", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Sayword sayword;//记录当时的那句传说
	
	@Column(name="saywordId",length = 20, columnDefinition = "BIGINT(20) COMMENT '传说Id'")
	private Long saywordId;//记录当时的那句传说,建立关联关系
	
	@Column(name="word",length=512,columnDefinition="varchar(512) COMMENT '赞语'")
	private String word;//赞语

	public CoeActivity getActivity() {
		return activity;
	}

	public void setActivity(CoeActivity activity) {
		this.activity = activity;
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


	public Date getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
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

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}


}
