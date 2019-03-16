package dml.sz0099.course.app.code.biz.pipe.listener.template;


import org.jit4j.core.pipe.metaq.message.PubMessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessage;

/**
 * demoConsumerMessageListener
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		  Date		     Function
 * init	    		bruceyang     2017/8/25		 init
 * 
 */
@Service("demoConsumerMessageListener")
public class DemoConsumerMessageListener extends DefaultMessageListener<PubMessageBody> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoConsumerMessageListener.class);


    @Override
    public void onReceiveMessages(MetaqMessage<PubMessageBody> message) {
    	
    	LOGGER.info("----------------------------- template model message begin ------------------------->");
    	
    	PubMessageBody body = message.getBody();
        
        if(null != body) {
        	
        	String eventCode = body.getEventCode();
        	
        	LOGGER.debug("---------- template model message : eventCode : {} ", eventCode);
        	
        }
        LOGGER.info("----------------------------- template model message end ------------------------->");
        
    }
}
