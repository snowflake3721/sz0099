package dml.sz0099.course.app.persist.entity.article.bo;

import java.io.Serializable;
import java.util.List;

import dml.sz0099.course.app.persist.entity.article.PhotoParag;


public class PhotoParagBo extends PhotoParag implements Serializable {

	private static final long serialVersionUID = -7322474652835917379L;

	public PhotoParagBo(){
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
