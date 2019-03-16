package dml.sz0099.course.app.biz.delegate.main.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseProductProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseProductProvider.class);

	public static void main(String[] args) {
		LOGGER.info("==========>>> CourseProductProvider started....");
		com.alibaba.dubbo.container.Main.main(args);
	}
}
