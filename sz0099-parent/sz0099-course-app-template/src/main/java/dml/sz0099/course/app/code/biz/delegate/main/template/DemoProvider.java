package dml.sz0099.course.app.code.biz.delegate.main.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoProvider.class);

	public static void main(String[] args) {
		LOGGER.info("==========>>> DemoProvider started....");
		com.alibaba.dubbo.container.Main.main(args);
	}
}
