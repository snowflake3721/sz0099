/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.profession;

import java.io.Serializable;

import org.jit4j.core.pub.transfer.dto.ResponseCode;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-10-02 07:34:58
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-10-02	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class ProfessionResponse<T> implements Serializable {

	private static final long serialVersionUID = 8255638010455228906L;

	public static final int SUCCESS_YES = 2;
	public static final int SUCCESS_NO = 1;
	public static final int SUCCESS_UNKNOWN = 0;

	protected String respCode = ResponseCode.CODE_SUCCESS;
	protected String respMsg = null;
	protected int success;

	protected T content = null;

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

}
