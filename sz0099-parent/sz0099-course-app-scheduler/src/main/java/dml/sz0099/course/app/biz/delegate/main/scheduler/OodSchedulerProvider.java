package dml.sz0099.course.app.biz.delegate.main.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OodSchedulerProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(OodSchedulerProvider.class);

	public static void main(String[] args) {
		LOGGER.info("==========>>> OodSchedulerProvider started....");
		com.alibaba.dubbo.container.Main.main(args);
	}
}
