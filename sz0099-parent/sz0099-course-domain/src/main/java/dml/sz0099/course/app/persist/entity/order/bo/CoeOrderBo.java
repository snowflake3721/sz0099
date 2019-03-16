package dml.sz0099.course.app.persist.entity.order.bo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.persist.entity.order.CoeOrder;

public class CoeOrderBo extends CoeOrder implements Serializable {

	private static final long serialVersionUID = -7322474652835917379L;

	public CoeOrderBo() {
		// do something
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date beginTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endTime;
	
	private String title;

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
