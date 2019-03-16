/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.show.blooming.config;

import java.util.List;

import org.jit8j.core.util.CollectionUtil;

import dml.sz0099.course.app.persist.entity.article.CoeArticlePosition;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-11 20:55:48
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-11	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class FlagFetcherConfig {

	protected List<CoeArticlePosition> content;
	
	protected CoeArticlePosition defaultPosition;
	
	
	public  CoeArticlePosition getRandomFromList() {
		return CollectionUtil.getRandomFromList(this.content,defaultPosition);
	}

	public List<CoeArticlePosition> getContent() {
		return content;
	}


	public void setContent(List<CoeArticlePosition> content) {
		this.content = content;
	}
	public CoeArticlePosition getDefaultPosition() {
		return defaultPosition;
	}
	public void setDefaultPosition(CoeArticlePosition defaultPosition) {
		this.defaultPosition = defaultPosition;
	}

	
}
