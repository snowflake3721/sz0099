/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.client.resolver.profession;

import java.io.Serializable;
import java.util.List;

import dml.sz0099.course.app.persist.entity.position.Position;
import dml.sz0099.course.app.persist.entity.profession.Profession;

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

public class ProfessionRequest implements Serializable {

private static final long serialVersionUID = 8255638010455228906L;
	
	public static final Integer REQUESTTYPE_CREATE=1;
	public static final Integer REQUESTTYPE_DELETE=2;
	public static final Integer REQUESTTYPE_QUERY=3;
	
	private Long userId;
	
	private Long mainId;
	
	private Long subId;
	
	private String devId;
	
	private String project;
	
	private String module;
	
	private String variety;//品种
	
	private String position;
	
	private Long positionId;
	
	private String domain;//领域模型
	
	private Integer strategy;//处理策略
	
	private Integer requestType;//请求类型
	
	private Integer subType;//子类型
	
	private List<Profession> professionList;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getMainId() {
		return mainId;
	}
	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}
	public Long getSubId() {
		return subId;
	}
	public void setSubId(Long subId) {
		this.subId = subId;
	}
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getVariety() {
		return variety;
	}
	public void setVariety(String variety) {
		this.variety = variety;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public Integer getStrategy() {
		return strategy;
	}
	public void setStrategy(Integer strategy) {
		this.strategy = strategy;
	}
	public Integer getRequestType() {
		return requestType;
	}
	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
	public Integer getSubType() {
		return subType;
	}
	public void setSubType(Integer subType) {
		this.subType = subType;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public List<Profession> getProfessionList() {
		return professionList;
	}
	public void setProfessionList(List<Profession> professionList) {
		this.professionList = professionList;
	}
}
