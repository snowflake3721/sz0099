/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.show.blooming.config;

import org.apache.commons.lang3.StringUtils;
import org.jit8j.core.util.CollectionUtil;
import org.springframework.stereotype.Component;

import dml.sz0099.course.app.client.controller.article.CoeArticleController;
import dml.sz0099.course.app.client.controller.profession.ProfessionController;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-06 00:46:13
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-06	basic init
 * 
 * @formatter:on
 * </pre>
 */

@Component
public class MainCircleConfig {

	public static final String IMAGEURL_YIN="/assets/common/tools/blooming-menu/image/yin_2_0.gif";
	public static final String IMAGEURL_HUAN="/assets/common/tools/blooming-menu3/img/sochi2014/2_figure-skating-icon.png";
	public static final String IMAGEURL_BANG="/assets/common/tools/blooming-menu3/img/musical/2_violin.png";
	public static final String IMAGEURL_HEAD="/assets/common/tools/blooming-menu/image/dml_2_0.png";
	public static final String IMAGEURL_XIN="/assets/common/tools/blooming-menu3/img/fruit/07.png";
	public static final String IMAGEURL_JIAN="/assets/common/tools/blooming-menu3/image/majiang/dragon-green.png";
	
	//@Value("${web.view.blooming.imageUrlYinArray:/assets/common/tools/blooming-menu/image/yin_2_0.jpg}")
	protected String[] imageUrlYinArray;
	
	//@Value("${web.view.blooming.imageUrlXinArray:/assets/common/tools/blooming-menu/image/xin_2_0.gif}")
	protected String[] imageUrlXinArray;
	
	protected String[] imageUrlJianArray;
	
	/*@Value("${web.view.blooming.imageUrlHuanArray:/assets/common/tools/blooming-menu/image/huan_2_0.jpg,"
			+ "/assets/common/tools/blooming-menu/image/huan_2_1.jpg,"
			+ "/assets/common/tools/blooming-menu/image/huan_2_2.jpg}")*/
	protected String[] imageUrlHuanArray;
	
	/*@Value("${web.view.blooming.imageUrlHeadArray:/assets/common/tools/blooming-menu/image/dml_2_0.png,"
			+ "/assets/common/tools/blooming-menu/image/dml_2_1.png,"
			+ "/assets/common/tools/blooming-menu/image/dml_2_2.png}")*/
	protected String[] imageUrlHeadArray;
	
	//@Value("${web.view.blooming.imageUrlBangArray:/assets/common/tools/blooming-menu/image/bang_2_0.jpg}")
	protected String[] imageUrlBangArray;
	
	//@Value("${web.view.blooming.labelArray:技,功,神,武,杀,专,帅,走,吃,术,仙}")
	protected String[] labelArray;
	
	//@Value("${web.view.blooming.linkHuan:/sz0099/ood/personal/profession/changeByUserId/}")
	protected String linkHuan=ProfessionController.LINK_HUAN;
	
	//@Value("${web.view.blooming.linkBang:/sz0099/ood/personal/profession/findPageRefForUser}")
	protected String linkBang=ProfessionController.LINK_BANG;
	
	//@Value("${web.view.blooming.linkXin:/sz0099/ood/article/userNew/}")
	protected String linkXin=CoeArticleController.USER_NEW_URL;
	
	//默认和xin取相同即可
	protected String linkJian=CoeArticleController.USER_NEW_URL;
	
	
	
	public String getRandomLabel() {
		return CollectionUtil.getRandomFromArray(labelArray,"神");
	}
	
	public String getRandomHuanImageUrl() {
		return CollectionUtil.getRandomFromArray(imageUrlHuanArray,IMAGEURL_HUAN);
	}
	
	public String getRandomHeadImageUrl() {
		return CollectionUtil.getRandomFromArray(imageUrlHeadArray,IMAGEURL_HEAD);
	}
	
	public String getRandomBangImageUrl() {
		return CollectionUtil.getRandomFromArray(imageUrlBangArray,IMAGEURL_BANG);
	}
	public String getRandomYinImageUrl() {
		return CollectionUtil.getRandomFromArray(imageUrlYinArray,IMAGEURL_YIN);
	}
	
	public String getRandomXinImageUrl() {
		return CollectionUtil.getRandomFromArray(imageUrlXinArray,IMAGEURL_XIN);
	}
	public String getRandomJianImageUrl() {
		return CollectionUtil.getRandomFromArray(imageUrlJianArray,IMAGEURL_JIAN);
	}

	public String getLinkHuan() {
		return linkHuan;
	}

	public void setLinkHuan(String linkHuan) {
		this.linkHuan = linkHuan;
	}

	public String getLinkBang() {
		return linkBang;
	}

	public void setLinkBang(String linkBang) {
		this.linkBang = linkBang;
	}

	public String getLinkXin() {
		if(StringUtils.isNotBlank(linkXin) && linkXin.indexOf("{id}")>0) {
			linkXin = linkXin.replace("{id}", "");
		}
		return linkXin;
	}

	public void setLinkXin(String linkXin) {
		
		this.linkXin = linkXin;
	}

	public String[] getImageUrlYinArray() {
		return imageUrlYinArray;
	}

	public void setImageUrlYinArray(String[] imageUrlYinArray) {
		this.imageUrlYinArray = imageUrlYinArray;
	}

	public String[] getImageUrlXinArray() {
		return imageUrlXinArray;
	}

	public void setImageUrlXinArray(String[] imageUrlXinArray) {
		this.imageUrlXinArray = imageUrlXinArray;
	}

	public String[] getImageUrlHuanArray() {
		return imageUrlHuanArray;
	}

	public void setImageUrlHuanArray(String[] imageUrlHuanArray) {
		this.imageUrlHuanArray = imageUrlHuanArray;
	}

	public String[] getImageUrlHeadArray() {
		return imageUrlHeadArray;
	}

	public void setImageUrlHeadArray(String[] imageUrlHeadArray) {
		this.imageUrlHeadArray = imageUrlHeadArray;
	}

	public String[] getImageUrlBangArray() {
		return imageUrlBangArray;
	}

	public void setImageUrlBangArray(String[] imageUrlBangArray) {
		this.imageUrlBangArray = imageUrlBangArray;
	}

	public String[] getLabelArray() {
		return labelArray;
	}

	public void setLabelArray(String[] labelArray) {
		this.labelArray = labelArray;
	}

	public String getLinkJian() {
		return linkJian;
	}

	public void setLinkJian(String linkJian) {
		this.linkJian = linkJian;
	}

	public String[] getImageUrlJianArray() {
		return imageUrlJianArray;
	}

	public void setImageUrlJianArray(String[] imageUrlJianArray) {
		this.imageUrlJianArray = imageUrlJianArray;
	}
}
