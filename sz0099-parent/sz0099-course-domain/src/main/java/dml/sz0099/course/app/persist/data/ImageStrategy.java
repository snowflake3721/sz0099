package dml.sz0099.course.app.persist.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class ImageStrategy implements Serializable{

	private static final long serialVersionUID = -7512922270684153468L;
	
	public static final Map<Integer ,List<ImageStrategy>>  STRATEGY_1 = new HashMap<>();
	
	public static final List<ImageStrategy> STRATEGY_1_LIST = new ArrayList<>();
	public static final List<ImageStrategy> STRATEGY_2_LIST = new ArrayList<>();
	public static final List<ImageStrategy> STRATEGY_3_LIST = new ArrayList<>();
	public static final List<ImageStrategy> STRATEGY_4_LIST = new ArrayList<>();
	
	

	public static final String SYMBOL_X = "X";
	public static final String SYMBOL_W = "w";//w
	public static final String SYMBOL_H = "h";//h
	public static final String SYMBOL_UNDER_LINE = "_";//下划线
	public static final String SYMBOL_SLASH = "/";//斜杠
	public static final String SYMBOL_DOT = ".";//点
	
	public static final Integer W_80 = 80;
	public static final Integer W_100 = 100;
	public static final Integer W_120 = 120;
	public static final Integer W_180 = 180;
	public static final Integer W_240 = 240;
	public static final Integer W_200 = 200;
	public static final Integer W_300 = 300;
	public static final Integer W_480 = 480;
	public static final Integer W_640 = 640;
	public static final Integer W_720 = 720;
	public static final Integer W_800 = 800;
	public static final Integer W_900 = 900;
	public static final Integer W_1024 = 1024;
	public static final Integer W_1200 = 1200;
	public static final Integer W_1440 = 1440;
	public static final Integer W_1920 = 1920;
	public static final Integer W_4K = 3840;
	public static final Integer W_8K = 7680;
	public static final Integer W_10K = 10000;
	public static final Integer W_ORI = W_10K;
	
	public static final Integer H_60 = 60;
	public static final Integer H_90 = 90;
	public static final Integer H_135 = 135;
	public static final Integer H_150 = 150;
	public static final Integer H_180 = 180;
	public static final Integer H_225 = 225;
	public static final Integer H_360 = 360;
	public static final Integer H_500 = 500;
	public static final Integer H_540 = 540;
	public static final Integer H_768 = 768;
	public static final Integer H_900 = 900;
	public static final Integer H_1080 = 1080;
	public static final Integer H_7500 = 7500;
	
	
	
	public static ImageStrategy W_80_H_60 = new ImageStrategy("80X60",W_80,H_60,1.0f,false);
	public static ImageStrategy W_120_H_90 = new ImageStrategy("120X90",W_120,H_90,1.0f,false);
	public static ImageStrategy W_180_H_135 = new ImageStrategy("180X135",W_180,H_135,1.0f,false);
	public static ImageStrategy W_200_H_150 = new ImageStrategy("200X150",W_200,H_150,1.0f,false);
	public static ImageStrategy W_240_H_180 = new ImageStrategy("240X180",W_240,H_180,1.0f,false);
	public static ImageStrategy W_300_H_225 = new ImageStrategy("300X225",W_300,H_225,1.0f,false);
	public static ImageStrategy W_450_H_250 = new ImageStrategy("450X250",450,250,1.0f,false);
	public static ImageStrategy W_480_H_360 = new ImageStrategy("480X360",W_480,H_360,1.0f,false);
	public static ImageStrategy W_720_H_540 = new ImageStrategy("720X540",W_720,H_540,1.0f,true);
	public static ImageStrategy W_900_H_500 = new ImageStrategy("900X500",W_900,H_500,1.0f,false);
	public static ImageStrategy W_1024_H_768 = new ImageStrategy("1024X768",W_1024,H_768,1.0f,false);
	public static ImageStrategy W_1200_H_900 = new ImageStrategy("1200X900",W_1200,H_900,1.0f,false);
	public static ImageStrategy W_1440_H_1080 = new ImageStrategy("1440X1080",W_1440,H_1080,1.0f,false);
	
	//原图，按原图尺寸，而非此处定义的尺寸
	public static ImageStrategy W_10K_H_7500 = new ImageStrategy("10000X7500",W_10K,H_7500,1.0f, false);
	
	static {
		STRATEGY_1_LIST.add(W_120_H_90);
		STRATEGY_1_LIST.add(W_300_H_225);
		STRATEGY_1_LIST.add(W_720_H_540);
		STRATEGY_1_LIST.add(W_1200_H_900);
		STRATEGY_1.put(1, STRATEGY_1_LIST);
		
		STRATEGY_2_LIST.add(W_80_H_60);
		STRATEGY_1.put(2, STRATEGY_2_LIST);
		
		STRATEGY_3_LIST.add(W_1200_H_900);
		STRATEGY_1.put(3, STRATEGY_3_LIST);
		
		STRATEGY_4_LIST.add(W_300_H_225);
		STRATEGY_1.put(4, STRATEGY_4_LIST);
	}
	
	private String name;
	
	private Integer width;
	private Integer height;
	
	private float quality = 0.95f;//图像压缩质量
	
	private String widthName;
	
	private String heightName;
	
	private boolean primary;
	
	// 构造方法
	private ImageStrategy(String name) {
		this.name = name;
	}
	
	private ImageStrategy(String name,Integer srcWidth,Integer srcHeight, boolean primary) {
		this.name = name;
		if(srcWidth>0 && srcHeight>0) {
			this.width = srcWidth;
			this.height = srcHeight;
		}
		this.primary=primary;
	}
	
	private ImageStrategy(String name,Integer srcWidth,Integer srcHeight,float quality, boolean primary) {
		this.name = name;
		if(srcWidth>0 && srcHeight>0) {
			this.width = srcWidth;
			this.height = srcHeight;
		}
		
		if(quality>0 && quality<=1) {
			this.quality = quality;
		}
		this.primary=primary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSrcWidth() {
		return width;
	}

	public void setSrcWidth(Integer srcWidth) {
		this.width = srcWidth;
	}

	public Integer getSrcHeight() {
		return height;
	}

	public void setSrcHeight(Integer srcHeight) {
		this.height = srcHeight;
	}

	public float getQuality() {
		return quality;
	}

	public void setQuality(float quality) {
		this.quality = quality;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getWidthName() {
		if(StringUtils.isBlank(this.widthName)) {
			this.widthName=SYMBOL_W+this.width;
		}
		return widthName;
	}

	public void setWidthName(String widthName) {
		this.widthName = widthName;
	}

	public String getHeightName() {
		if(StringUtils.isBlank(this.heightName)) {
			this.heightName=SYMBOL_H+this.height;
		}
		return heightName;
	}

	public void setHeightName(String heightName) {
		this.heightName = heightName;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}


}
