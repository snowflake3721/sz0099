/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.webmvc.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.jit4j.app.module.define.Jit4jRespCode;
import org.jit4j.app.module.define.R;
import org.jit4j.core.pub.transfer.dto.JsonResult;
import org.jit4j.core.shiro.security.filter.JCaptchaValidateFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.rpc.RpcException;

import dml.sz0099.course.app.module.define.CourseRespCode;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-09-03 12:48:18
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-09-03	basic init
 * 
 * @formatter:on
 * </pre>
 */

@ControllerAdvice
public class CourseExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseExceptionHandler.class);

	@Value("${global.error.page:error_500_page}")
	private String defaultErrorView;

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		// If the exception is annotated with @ResponseStatus rethrow it and let
		// the framework handle it - like the OrderNotFoundException example
		// at the start of this post.
		// AnnotationUtils is a Spring Framework utility class.
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}
		// Otherwise setup and send the user to a default error-view.
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(defaultErrorView);
		return mav;
	}
	

	/*@ResponseBody
	@ExceptionHandler(RpcException.class)
    public JsonResult<String> handle( RpcException ex, HttpServletRequest request) {
		String errorCode = CourseRespCode.SZ0099_CODE_COURSE_SYS_RPC_INVOKE_ERROR;
		String respMsg = R.getMsg(errorCode);
		JsonResult<String> result = new JsonResult<>();
		result.setSuccess(JsonResult.SUCCESS_NO);
		result.setRespCode(errorCode);
		result.setRespMsg(respMsg);
		result.setContent((String)request.getAttribute(JCaptchaValidateFilter.refreshKey));
		return result;
    }*/
	
	public String getDefaultErrorView() {
		return defaultErrorView;
	}

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}
}
