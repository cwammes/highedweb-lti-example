package org.highedweb.lti.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="assignments")
public class Assignment  implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	//LTI Information
	private int id;
	
	private String toolConsumerInstanceGuid;
	
	private String contextId;
	private String userId;
	private String lisOutcomeServiceUrl;
	private String lisResultSourcedid;	
	private String resourceLinkId;
	private String lisPersonContactEmailPrimary;
	private String lisPersonNameFull;
	private String lisPersonNameFamily;
	private String lisPersonNameGiven;
	
	//Assignment Information
	private String assignmentTitle;
	private String assignmentBody;
	private String assignmentGrade;
	private Date assignmentSubmissionDate;
	private String assignmentGradeUser;
	private Date assignmentGradeDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,  /* Generate sequence numbers by storing used
	                                                   numbers in a table with one row per table */
	    generator="generatorName")
	@TableGenerator(name="generatorName",
	    table="hibernate_sequences",  /* Name of table that will contain sequences */
	    pkColumnName="tableName",  /* Name of column containing names of tables using sequences */
	    valueColumnName="id",  /* Name of column in generator table that contains last used ID */
	    allocationSize=1  /* Flush after every insert */
	)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column
	public String getContextId() {
		return contextId;
	}
	public void setContextId(String contextId) {
		this.contextId = contextId;
	}
	
	@Column
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column
	public String getLisOutcomeServiceUrl() {
		return lisOutcomeServiceUrl;
	}
	public void setLisOutcomeServiceUrl(String lisOutcomeServiceUrl) {
		this.lisOutcomeServiceUrl = lisOutcomeServiceUrl;
	}
	
	@Column
	public String getLisPersonContactEmailPrimary() {
		return lisPersonContactEmailPrimary;
	}
	public void setLisPersonContactEmailPrimary(String lisPersonContactEmailPrimary) {
		this.lisPersonContactEmailPrimary = lisPersonContactEmailPrimary;
	}
	
	@Column
	public String getLisPersonNameFull() {
		return lisPersonNameFull;
	}
	public void setLisPersonNameFull(String lisPersonNameFull) {
		this.lisPersonNameFull = lisPersonNameFull;
	}
	
	@Column
	public String getLisPersonNameFamily() {
		return lisPersonNameFamily;
	}
	public void setLisPersonNameFamily(String lisPersonNameFamily) {
		this.lisPersonNameFamily = lisPersonNameFamily;
	}
	
	@Column
	public String getLisPersonNameGiven() {
		return lisPersonNameGiven;
	}
	public void setLisPersonNameGiven(String lisPersonNameGiven) {
		this.lisPersonNameGiven = lisPersonNameGiven;
	}
	
	@Column
	public String getAssignmentTitle() {
		return assignmentTitle;
	}
	public void setAssignmentTitle(String assignmentTitle) {
		this.assignmentTitle = assignmentTitle;
	}
	
	@Column
	public String getAssignmentBody() {
		return assignmentBody;
	}
	public void setAssignmentBody(String assignmentBody) {
		this.assignmentBody = assignmentBody;
	}
	
	@Column
	public String getAssignmentGrade() {
		return assignmentGrade;
	}
	
	public void setAssignmentGrade(String assignmentGrade) {
		this.assignmentGrade = assignmentGrade;
	}
	
	@Column
	public String getToolConsumerInstanceGuid() {
		return toolConsumerInstanceGuid;
	}
	public void setToolConsumerInstanceGuid(String toolConsumerInstanceGuid) {
		this.toolConsumerInstanceGuid = toolConsumerInstanceGuid;
	}
	
	@Column
	public String getLisResultSourcedid() {
		return lisResultSourcedid;
	}
	public void setLisResultSourcedid(String lisResultSourcedid) {
		this.lisResultSourcedid = lisResultSourcedid;
	}
	
	@Column
	public Date getAssignmentSubmissionDate() {
		return assignmentSubmissionDate;
	}
	public void setAssignmentSubmissionDate(Date assignmentSubmissionDate) {
		this.assignmentSubmissionDate = assignmentSubmissionDate;
	}
	
	@Column
	public String getAssignmentGradeUser() {
		return assignmentGradeUser;
	}
	public void setAssignmentGradeUser(String assignmentGradeUser) {
		this.assignmentGradeUser = assignmentGradeUser;
	}
	
	@Column
	public Date getAssignmentGradeDate() {
		return assignmentGradeDate;
	}
	public void setAssignmentGradeDate(Date assignmentGradeDate) {
		this.assignmentGradeDate = assignmentGradeDate;
	}	
	
	@Column
	public String getResourceLinkId() {
		return resourceLinkId;
	}
	public void setResourceLinkId(String resourceLinkId) {
		this.resourceLinkId = resourceLinkId;
	}	

}
