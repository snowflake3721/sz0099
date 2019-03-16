/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.show.layout.fetcher.dto;

import org.springframework.data.domain.Page;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;
import dml.sz0099.course.app.persist.entity.article.CoeCategArticle;

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

public class CoeArticlePageDto {
	
	protected Page<CoeCategArticle> categoryPage;
	
	protected CoeCategArticle category;
	
	protected Page<CoeArticlePosition> positionPage;
	
	protected CoeArticlePosition position;
	

	public Page<CoeCategArticle> getCategoryPage() {
		return categoryPage;
	}

	public void setCategoryPage(Page<CoeCategArticle> categoryPage) {
		this.categoryPage = categoryPage;
	}

	public Page<CoeArticlePosition> getPositionPage() {
		return positionPage;
	}

	public void setPositionPage(Page<CoeArticlePosition> positionPage) {
		this.positionPage = positionPage;
	}

	public CoeCategArticle getCategory() {
		return category;
	}

	public void setCategory(CoeCategArticle category) {
		this.category = category;
	}

	public CoeArticlePosition getPosition() {
		return position;
	}

	public void setPosition(CoeArticlePosition position) {
		this.position = position;
	}
}
