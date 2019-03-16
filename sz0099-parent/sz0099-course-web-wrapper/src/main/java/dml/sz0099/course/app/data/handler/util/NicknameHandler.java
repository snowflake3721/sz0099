/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.handler.util;

import java.util.List;

import javax.annotation.Resource;

import org.codehaus.plexus.util.StringUtils;
import org.jit4j.core.persist.entity.UserBaseEntityExtend;
import org.jit8j.core.util.Base64Util;
import org.springframework.stereotype.Component;

import dml.sz0099.course.app.data.show.blooming.config.MainCircleConfig;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-16 11:46:56
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-16	basic init
 * 
 * @formatter:on
 * </pre>
 */

@Component
public class NicknameHandler {
	
	@Resource(name="mainCircleConfigArticle")
	private MainCircleConfig mainCircleConfig;
	
	@Resource(name="mainCircleConfigProfession")
	private MainCircleConfig mainCircleConfigProfession;

	public <T extends UserBaseEntityExtend> List<T> handleNickname(List<T> content) {
		if (null != content && !content.isEmpty()) {
			for (UserBaseEntityExtend c : content) {
				if(!c.getResolved()) {
					String nickname = c.getNickname();
					if (StringUtils.isNotBlank(nickname)) {
						c.setNickname(Base64Util.decode(nickname));
					} else {
						c.setNickname("卓无名");
					}
					String headImg = c.getHeadImg();
					if (StringUtils.isBlank(headImg)) {
						c.setHeadImg(mainCircleConfig.getRandomHeadImageUrl());
					}
					c.setResolved(true);
				}
			}
		}
		return content;
	}

	public <T extends UserBaseEntityExtend > T handleNickname(T content) {
		if (null != content && !content.getResolved()) {
			String nickname = content.getNickname();
			if (StringUtils.isNotBlank(nickname)) {
				content.setNickname(Base64Util.decode(nickname));
			} else {
				content.setNickname("卓无名");
			}
			String headImg = content.getHeadImg();
			if (StringUtils.isBlank(headImg)) {
				content.setHeadImg(mainCircleConfig.getRandomHeadImageUrl());
			}
			content.setResolved(true);
		}
		return content;
	}
	
	public <T extends CoeUser > T handleNicknameForUser(T content) {
		if (null != content && !content.getResolved()) {
			String nickname = content.getNickname();
			if (StringUtils.isNotBlank(nickname)) {
				content.setNickname(Base64Util.decode(nickname));
			} else {
				content.setNickname("卓无名");
			}
			String headImg = content.getHeadImg();
			if (StringUtils.isBlank(headImg)) {
				content.setHeadImg(mainCircleConfig.getRandomHeadImageUrl());
			}
			content.setResolved(true);
		}
		return content;
	}
}
