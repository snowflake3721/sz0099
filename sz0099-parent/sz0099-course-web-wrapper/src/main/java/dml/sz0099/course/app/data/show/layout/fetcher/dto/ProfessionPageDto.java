/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.show.layout.fetcher.dto;

import org.springframework.data.domain.Page;

import dml.sz0099.course.app.persist.entity.profession.CategProfession;
import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;

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

public class ProfessionPageDto {
	
	protected Page<CategProfession> categoryPage;
	
	protected Page<ProfessionPosition> positionPage;
	
	protected CategProfession category;
	
	protected ProfessionPosition position;

	public Page<CategProfession> getCategoryPage() {
		return categoryPage;
	}

	public void setCategoryPage(Page<CategProfession> categoryPage) {
		this.categoryPage = categoryPage;
	}

	public Page<ProfessionPosition> getPositionPage() {
		return positionPage;
	}

	public void setPositionPage(Page<ProfessionPosition> positionPage) {
		this.positionPage = positionPage;
	}

	public CategProfession getCategory() {
		return category;
	}

	public void setCategory(CategProfession category) {
		this.category = category;
	}

	public ProfessionPosition getPosition() {
		return position;
	}

	public void setPosition(ProfessionPosition position) {
		this.position = position;
	}
}
