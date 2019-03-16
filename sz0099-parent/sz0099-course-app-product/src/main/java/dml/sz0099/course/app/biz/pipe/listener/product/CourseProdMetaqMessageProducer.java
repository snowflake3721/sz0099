package dml.sz0099.course.app.biz.pipe.listener.product;

import javax.annotation.PostConstruct;

import org.jit4j.core.pipe.metaq.message.MetaqMessageProducer;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.module.define.CourseAppModule;

@Service
public class CourseProdMetaqMessageProducer extends MetaqMessageProducer{

	@PostConstruct
	@Override
	public void init() {
		
		super.from=CourseAppModule.APP_COURSE_PRODUCT;
		
	}

	
	
	
}
