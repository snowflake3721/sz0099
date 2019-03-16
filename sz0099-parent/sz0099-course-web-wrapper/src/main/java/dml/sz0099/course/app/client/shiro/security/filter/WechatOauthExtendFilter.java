package dml.sz0099.course.app.client.shiro.security.filter;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.jit4j.core.shiro.security.adaptor.UserAdaptor;
import org.jit4j.core.shiro.security.passwd.PasswordGenerator;
import org.jit4j.core.webmvc.util.UserAgentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WechatOauthExtendFilter extends AccessControlFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(WechatOauthExtendFilter.class);

	private UserAdaptor userAdaptor;
	
	private PasswordGenerator passwordGenerator;

	@PostConstruct
	public void init() {
		LOGGER.debug("----WechatOauthExtendFilter init-----");
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		String uri = httpRequest.getRequestURI();
		StringBuffer urlBuffer = httpRequest.getRequestURL();
		
		String userAgent = UserAgentUtil.getBrowser(httpRequest);
		boolean isLogin = SecurityUtils.getSubject().isAuthenticated();
		if(isLogin) {
			
		}
		LOGGER.debug("=====WechatOauthExtendFilter>>uri: {}, userAgent: {} =====", uri, userAgent);
		String url = urlBuffer.toString();
		LOGGER.debug("=====WechatOauthExtendFilter>>url: {}, userAgent: {} =====", url, userAgent);

		

		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		return false;
	}

	@Override
	protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {

		super.postHandle(request, response);
	}

	public UserAdaptor getUserAdaptor() {
		return userAdaptor;
	}

	public void setUserAdaptor(UserAdaptor userAdaptor) {
		this.userAdaptor = userAdaptor;
	}

	public PasswordGenerator getPasswordGenerator() {
		return passwordGenerator;
	}

	public void setPasswordGenerator(PasswordGenerator passwordGenerator) {
		this.passwordGenerator = passwordGenerator;
	}

}
