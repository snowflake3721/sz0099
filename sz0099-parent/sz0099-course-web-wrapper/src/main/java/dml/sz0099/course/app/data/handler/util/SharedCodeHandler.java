/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.handler.util;

import org.apache.commons.lang3.StringUtils;
import org.jit4j.app.persist.entity.auth.User;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-28 11:32:31
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-28	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class SharedCodeHandler {

	
	public static String addSharedCodeForLink(String link, User user) {
		if(StringUtils.isNotBlank(link) && null != user) {
			String sharedCode = user.getSharedCode();
			StringBuilder  sb = new StringBuilder(link);
			if(StringUtils.isNotBlank(sharedCode) && link.indexOf("sharedCode")<0 ) {
				if(link.indexOf("?")>0) {
					sb.append("&sharedCode=").append(sharedCode);
				}else {
					sb.append("?sharedCode=").append(sharedCode);
				}
				link = sb.toString();
			}
		}
		return link;
	}
}
