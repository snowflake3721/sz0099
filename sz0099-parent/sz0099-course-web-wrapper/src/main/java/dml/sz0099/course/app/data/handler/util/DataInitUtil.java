/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.data.handler.util;

import java.util.Map;

import org.jit4j.app.client.wrapper.module.ModuleContextWrapper;
import org.jit4j.app.client.wrapper.pay.PayConfigWrapper;
import org.jit4j.app.module.define.AppModule;
import org.jit4j.app.persist.entity.module.ModuleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2019-01-17 14:40:11
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2019-01-17	basic init
 * 
 * @formatter:on
 * </pre>
 */
@Component
public class DataInitUtil {

	@Autowired
	private ModuleContextWrapper moduleContextWrapper;
	
	@Autowired
	private PayConfigWrapper payConfigWrapper;
	
	public void initAppModule() {
		System.out.println("----------- initAppModule begin ------------ ");
		StackTraceElement[]  stackTraces = Thread.currentThread().getStackTrace();
		if(null != stackTraces) {
			for(StackTraceElement s : stackTraces) {
				System.out.println(s.toString());
			}
		}
		Map<String, ModuleContext> appMoudleContexts = AppModule.getAppMoudleContexts();
		moduleContextWrapper.initDataForModule(appMoudleContexts);
		System.out.println("----------- initAppModule end ------------ ");
	}
	
	public void initWechatConfig() {
		System.out.println("----------- initWechatConfig begin ------------ ");
		StackTraceElement[]  stackTraces = Thread.currentThread().getStackTrace();
		if(null != stackTraces) {
			for(StackTraceElement s : stackTraces) {
				System.out.println(s.toString());
			}
		}
		payConfigWrapper.initData();
		System.out.println("----------- initWechatConfig end ------------ ");
	}
	
	public static void main(String[] args) {
		Map<String, ModuleContext> appMoudleContexts = AppModule.getAppMoudleContexts();
		System.out.println(appMoudleContexts);
	}
}
