/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.show.layout.fetcher.dto;

import org.springframework.data.domain.Page;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityPosition;
import dml.sz0099.course.app.persist.entity.activity.CoeCategActivity;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-12 16:20:02
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-12	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class CoeActivityPageDto {
	
	protected Page<CoeCategActivity> categoryPage;
	
	protected CoeCategActivity category;
	
	protected Page<CoeActivityPosition> positionPage;
	
	protected CoeActivityPosition position;
	

	public Page<CoeCategActivity> getCategoryPage() {
		return categoryPage;
	}

	public void setCategoryPage(Page<CoeCategActivity> categoryPage) {
		this.categoryPage = categoryPage;
	}

	public Page<CoeActivityPosition> getPositionPage() {
		return positionPage;
	}

	public void setPositionPage(Page<CoeActivityPosition> positionPage) {
		this.positionPage = positionPage;
	}

	public CoeCategActivity getCategory() {
		return category;
	}

	public void setCategory(CoeCategActivity category) {
		this.category = category;
	}

	public CoeActivityPosition getPosition() {
		return position;
	}

	public void setPosition(CoeActivityPosition position) {
		this.position = position;
	}
}
