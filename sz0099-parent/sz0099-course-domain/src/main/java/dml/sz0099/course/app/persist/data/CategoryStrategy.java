package dml.sz0099.course.app.persist.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dml.sz0099.course.app.persist.entity.category.CategoryExtend;


public class CategoryStrategy implements Serializable{

	private static final long serialVersionUID = -7512922270684153468L;
	
	public static final Map<Integer ,List<CategoryStrategy>>  STRATEGY_1 = new HashMap<>();
	
	public static final List<CategoryStrategy> STRATEGY_1_LIST = new ArrayList<>();
	
	public static final String SYMBOL_X = "X";
	public static final String SYMBOL_W = "w";//w
	public static final String SYMBOL_H = "h";//h
	public static final String SYMBOL_UNDER_LINE = "_";//下划线
	public static final String SYMBOL_SLASH = "/";//斜杠
	public static final String SYMBOL_DOT = ".";//点
	
	
	private String name;
	
	private String code;
	
	private String ad;
	
	private Long parentId;
	
	private boolean primary;
	
	// 构造方法
	private CategoryStrategy(String name, String code, String ad, Long parentId, boolean primary) {
		this.name = name;
		this.code = code;
		this.ad = ad;
		this.parentId = parentId;
		this.primary = primary;
		if(primary) {
			this.parentId=CategoryExtend.TOP_PARENTID;
		}
	}
	

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


	public String getAd() {
		return ad;
	}


	public void setAd(String ad) {
		this.ad = ad;
	}


	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public boolean isPrimary() {
		return primary;
	}


	public void setPrimary(boolean primary) {
		this.primary = primary;
	}



}
