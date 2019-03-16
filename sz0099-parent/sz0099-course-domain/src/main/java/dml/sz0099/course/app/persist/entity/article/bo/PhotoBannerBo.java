package dml.sz0099.course.app.persist.entity.article.bo;

import java.io.Serializable;
import java.util.List;

import dml.sz0099.course.app.persist.entity.article.PhotoBanner;


public class PhotoBannerBo extends PhotoBanner implements Serializable {

	private static final long serialVersionUID = -7322474652835917379L;

	public PhotoBannerBo(){
		//do something
	}
	private List<Long> idList;

	public List<Long> getIdList() {
		return idList;
	}

	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}
}
