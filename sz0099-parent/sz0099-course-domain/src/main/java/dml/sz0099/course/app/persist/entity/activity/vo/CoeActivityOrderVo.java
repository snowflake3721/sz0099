/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.activity.vo;

import javax.persistence.Transient;

import org.jit4j.app.persist.entity.pay.wechat.PayResponseWechat;

import dml.sz0099.course.app.persist.entity.activity.CoeActivityOrder;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2019-01-13 17:28:43
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2019-01-13	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class CoeActivityOrderVo  {

	@Transient
	private PayResponseWechat response;
	
	@Transient
	private CoeActivityOrder actOrder;

	public PayResponseWechat getResponse() {
		return response;
	}

	public void setResponse(PayResponseWechat response) {
		this.response = response;
	}

	public CoeActivityOrder getActOrder() {
		return actOrder;
	}

	public void setActOrder(CoeActivityOrder actOrder) {
		this.actOrder = actOrder;
	}
}
