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
 * @author bruce yang at 2018-11-14 11:24:53
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-14	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class ShowNameUtil {

	public static String getShowname(String penname, String nickname, String defaultname) {
		
		if(StringUtils.isNotBlank(penname)) {
			return penname;
		}
		if(StringUtils.isNotBlank(nickname)) {
			return nickname;
		}
		
		if(StringUtils.isBlank(defaultname)) {
			defaultname="无名";
		}
		return defaultname;
	}
	
	
}
