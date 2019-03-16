package dml.sz0099.course.app.persist.entity.order.bo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import dml.sz0099.course.app.persist.entity.order.CoeOrderProduct;


public class CoeOrderProductBo extends CoeOrderProduct implements Serializable {

	private static final long serialVersionUID = -7322474652835917379L;

	public CoeOrderProductBo(){
		//do something
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date beginTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endTime;

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
	
}
