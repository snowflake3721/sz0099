/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.shiro.security.adaptor.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jit4j.app.persist.entity.auth.User;
import org.jit8j.core.shiro.basic.UserBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dml.sz0099.course.app.client.wrapper.product.CoeUserWrapper;
import dml.sz0099.course.app.persist.entity.user.CoeUser;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-11-18 11:26:29
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-11-18	basic init
 * 
 * @formatter:on
 * </pre>
 */
//@Component
@Aspect
public class RegisterAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterAspect.class);
	
	@Autowired
	private CoeUserWrapper coeUserWrapper;
	
	public RegisterAspect() {
		LOGGER.debug("==========RegisterAspect init===============");
	}

	@Pointcut("@annotation(org.jit4j.core.shiro.security.adaptor.annotation.RegisterHandler)")
    public void registerPointCut(){}
	
	@Pointcut("execution(org.jit4j.app.persist.entity.auth.User org.jit4j.core.shiro.security.adaptor.UserAdaptorImpl.registeUser(..))")
    public void registerPointCut2(){}
	
	@Before("execution(* org.jit4j.core.shiro.security.adaptor.UserAdaptor.findUserByOpenIdAndAppId(..))")
	public void before() {
		LOGGER.debug("==========RegisterAspect before===============");
	}
	
	@After("execution(* org.jit4j.core.shiro.security.adaptor.UserAdaptor.registeUserBase(..))")
	public void after() {
		LOGGER.debug("==========RegisterAspect after===============");
	}
	
	@Around(value = "execution(* org.jit4j.core.shiro.security.adaptor.UserAdaptor.registeUserBase(..))")
	public void createCoeUser(ProceedingJoinPoint point) {
		LOGGER.info("UserAdaptor.registeUserBase begin...");
        Object[] args = point.getArgs();
        if (args != null && args.length > 0 && args[0] instanceof UserBase) {
        	try {
				Object returnValue = point.proceed();
				if(returnValue instanceof User) {
					User user = (User)returnValue;
					Long userId = user.getId();
					if(null != userId) {
						LOGGER.info("===coeUserWrapper.findThenCreate:{}===",userId);
						CoeUser coeUser = coeUserWrapper.findThenCreate(user);
						if(null != coeUser) {
							LOGGER.info("===coeUserWrapper.findThenCreate: userId:{} vs coeUserId: {} success ===",userId , coeUser.getId());
						}
					}
				} 
			} catch (Throwable e) {
				LOGGER.error("call register error: {}",e);
			}
        }
        //用改变后的参数执行目标方法
        LOGGER.debug("UserAdaptor.registeUserBase end...");
	}
	
	
}
