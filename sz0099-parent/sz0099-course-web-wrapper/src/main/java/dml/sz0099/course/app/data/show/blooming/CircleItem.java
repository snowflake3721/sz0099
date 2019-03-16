/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.show.blooming;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-04 16:58:14
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-04	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class CircleItem {
	
	public static final int ITEMTYPE_CLICK=0;//点击类型
	public static final int ITEMTYPE_VIEW=1;//查看类型
	public static final int SHOWLABEL_NO=0;//不显示标签
	public static final int SHOWLABEL_YES=1;//显示标签
	
	public static final int BTNTYPE_NORMAL=0;//按钮类型标记，为普通
	public static final int BTNTYPE_CLOSE=1;//按钮类型标记，为隐
	public static final int BTNTYPE_HUAN=2;//按钮类型标记，为换
	
	
	private String imageUrl;//图片路径
	
	private String title;
	
	private String label;//中间字
	
	private String link;//跳转路径
	
	private int itemType;
	
	private int showLabel;
	
	private int btnType;
	
	private Long userId;
	
	public CircleItem() {}
	
	public CircleItem(String imageUrl, String label, String link, int itemType, int showLabel, int btnType) {
		this.imageUrl=imageUrl;
		this.label=label;
		this.link=link;
		this.itemType=itemType;
		this.showLabel=showLabel;
		this.btnType=btnType;
		
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public int getShowLabel() {
		return showLabel;
	}

	public void setShowLabel(int showLabel) {
		this.showLabel = showLabel;
	}

	public int getBtnType() {
		return btnType;
	}

	public void setBtnType(int btnType) {
		this.btnType = btnType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
