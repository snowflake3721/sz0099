/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.show.blooming.config;

import java.util.List;

import org.jit8j.core.util.CollectionUtil;

import dml.sz0099.course.app.persist.entity.profession.ProfessionPosition;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-11 23:52:40
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-11	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class IngenuityFetcherConfig {

	protected List<ProfessionPosition> content;
	
	protected ProfessionPosition defaultPosition;
	
	public  ProfessionPosition getRandomFromList() {
		return CollectionUtil.getRandomFromList(this.content,defaultPosition);
	}

	public List<ProfessionPosition> getContent() {
		return content;
	}

	public void setContent(List<ProfessionPosition> content) {
		this.content = content;
	}

	public ProfessionPosition getDefaultPosition() {
		return defaultPosition;
	}

	public void setDefaultPosition(ProfessionPosition defaultPosition) {
		this.defaultPosition = defaultPosition;
	}
}
