package dml.sz0099.course.app.code.biz.pipe.listener.template;

import javax.annotation.PostConstruct;

import org.jit4j.app.module.define.Jit4jAppModule;
import org.jit4j.core.pipe.metaq.message.MetaqMessageProducer;
import org.springframework.stereotype.Service;

@Service("demoMetaqMessageProducer")
public class DemoMessageProducer extends MetaqMessageProducer{

	@PostConstruct
	@Override
	public void init() {
		super.from=Jit4jAppModule.APP_DEMO;
	}

}
