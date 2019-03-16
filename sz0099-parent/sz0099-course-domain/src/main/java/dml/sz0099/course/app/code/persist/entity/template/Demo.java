package dml.sz0099.course.app.code.persist.entity.template;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.jit4j.core.persist.entity.BaseEntityExtendLong;

@Entity
@Table(name="s9_temp_demo")
public class Demo extends BaseEntityExtendLong implements Serializable {

	private static final long serialVersionUID = 2045069152104295967L;

	private String name;
	
	private String code;
	
	private String decription;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}
}
