/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.persist.entity.activity.vo;

import java.io.Serializable;

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

public class CoeActivityUserVo implements Serializable{

	private static final long serialVersionUID = 9199035711178364120L;

	private Long num;

	private Long mainId;
	
	public CoeActivityUserVo() {
		
	}
	
	public CoeActivityUserVo(Long num, Long mainId) {
		this.num=num;
		this.mainId=mainId;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

}
