package dml.sz0099.course.app.code.client.main.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <pre>
 * @formatter:off
 *
 * Wrapper服务封装组件 服务启动类
 * @author bruce yang at 2018-08-29 22:19:03
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-08-29	basic init
 * 
 * @formatter:on
 * </pre>
 */
public class DemoWrapperProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoWrapperProvider.class);

    public static void main(String[] args) {
        LOGGER.debug(">>> Wrapper start >>>>>>>>");
        com.alibaba.dubbo.container.Main.main(args);
    }
}
