package dml.sz0099.course.app.biz.pipe.listener;

import javax.annotation.PostConstruct;

import org.jit4j.app.module.define.Jit4jAppModule;
import org.jit4j.core.pipe.metaq.message.MetaqMessageProducer;
import org.springframework.stereotype.Service;

@Service
public class OodSchedulerMetaqMessageProducer extends MetaqMessageProducer{

	@PostConstruct
	@Override
	public void init() {
		
		super.from=Jit4jAppModule.APP_SCHEDULER;
		
	}
	
}
