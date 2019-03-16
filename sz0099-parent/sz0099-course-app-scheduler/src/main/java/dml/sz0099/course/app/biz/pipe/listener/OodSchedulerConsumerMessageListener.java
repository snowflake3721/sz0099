package dml.sz0099.course.app.biz.pipe.listener;


import org.codehaus.plexus.util.StringUtils;
import org.jit4j.app.module.define.Jit4jAppModule;
import org.jit4j.app.persist.entity.auth.User;
import org.jit4j.core.pipe.metaq.message.ConsumerMessageListener;
import org.jit4j.core.pipe.metaq.message.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * consumerMessageListener
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		  Date		     Function
 * init	    		bruceyang     2017/8/25		 init
 * 
 */
@Service("oodSchedulerConsumerMessageListener")
public class OodSchedulerConsumerMessageListener extends ConsumerMessageListener<MessageBody> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OodSchedulerConsumerMessageListener.class);
    

    @Override
	public boolean handleMessage(Long id, String code, MessageBody body) {
		String msgCode=Jit4jAppModule.APP_AUTH_MSG_USER_MERGE_MOBILE.currentMsg();
		LOGGER.debug("=== handleMessage: code={}, id={} ===",code,id);
		if(StringUtils.isNotBlank(code) && code.equals(msgCode)) {
			Object content = body.getContent();
		}
		return false;
	}
}
