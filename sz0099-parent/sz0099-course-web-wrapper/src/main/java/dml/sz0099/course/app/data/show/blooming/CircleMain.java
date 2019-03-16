/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.show.blooming;

import java.util.ArrayList;
import java.util.List;

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

public class CircleMain {
	
	public static final String LABEL_DEFAULT="神";
	
	private String imageUrl;//图片路径
	
	private String title;
	
	private String label=LABEL_DEFAULT;//中间字

	private int size;//item个数
	
	List<CircleItem> itemList=new ArrayList<>(9);//最多装九个
	
	private Long userId;//当前菜单占据用户

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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<CircleItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CircleItem> itemList) {
		this.itemList = itemList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
