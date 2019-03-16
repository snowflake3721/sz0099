/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.core.util;

import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-14 16:19:06
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-14	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class LinkUtil {

	public static String getLink(String link, String originalLink, String defaultLink) {
		if(StringUtils.isNotBlank(link)) {
			return link;
		}
		if(StringUtils.isNotBlank(originalLink)) {
			return originalLink;
		}
		if(StringUtils.isNotBlank(defaultLink)) {
			return defaultLink;
		}
		return "#";
	}
}
