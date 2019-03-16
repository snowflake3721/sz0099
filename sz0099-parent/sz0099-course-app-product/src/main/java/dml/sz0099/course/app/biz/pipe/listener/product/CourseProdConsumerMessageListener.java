package dml.sz0099.course.app.biz.pipe.listener.product;


import org.apache.commons.lang3.StringUtils;
import org.jit4j.app.persist.entity.order.Order;
import org.jit4j.app.persist.entity.pay.wechat.PayNotifyWechat;
import org.jit4j.core.pipe.metaq.message.ConsumerMessageListener;
import org.jit4j.core.pipe.metaq.message.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dml.sz0099.course.app.biz.delegate.activity.CoeActivityOrderDelegate;
import dml.sz0099.course.app.module.define.CourseAppModule;

/**
 * authConsumerMessageListener
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		  Date		     Function
 * init	    		bruceyang     2017/8/25		 init
 * 
 */
@Service("courseProdConsumerMessageListener")
public class CourseProdConsumerMessageListener extends ConsumerMessageListener<MessageBody>{

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseProdConsumerMessageListener.class);

    @Autowired
    private CoeActivityOrderDelegate coeActivityOrderDelegate;

    @Override
	public boolean handleMessage(Long id, String code, MessageBody body) {
		String msgCode=CourseAppModule.APP_OOD_ACTIVITY_MSG_ORDER_MERGE_STATUS.currentMsg();
		LOGGER.debug("=== handleMessage: code={}, id={} ===",code,id);
		if(StringUtils.isNotBlank(code) && code.equals(msgCode)) {
			Object content = body.getContent();
			if(content instanceof Order) {
				Order order = (Order)content;
				Long orderId = order.getId();
				LOGGER.debug("=== handleMessage: code={}, orderId={} ===",code,orderId);
				coeActivityOrderDelegate.mergeStatus(order);
				return true;
			}else if(content instanceof PayNotifyWechat) {
				PayNotifyWechat notify = (PayNotifyWechat)content;
				String resultCode = notify.getResultCode();
				String returnCode = notify.getReturnCode();
				String respCode = notify.getRespCode();
				String respMsg = notify.getRespMsg();
				LOGGER.debug("=== handleMessage: code={}, returnCode={}, resultCode={}, respCode:{}, respMsg:{} ===",code, returnCode, resultCode, respCode, respMsg);
				coeActivityOrderDelegate.mergeStatus(notify);
				return true;
			}
		}
		return false;
	}
}
