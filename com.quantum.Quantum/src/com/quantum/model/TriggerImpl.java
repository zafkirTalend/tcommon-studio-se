/*
 * Created on 1/03/2005
 *
 */
package com.quantum.model;

/**
 * @author Julen
 *
 */
public class TriggerImpl implements Trigger {

	private String name;
	private String moment;
	private String forEach;
	private String event;
	private String actionType;
	private String columnName;
	private String language;
	private String referencing;
	private String whenClause;
	private String status;
	private String body;
	
	public TriggerImpl(String name, String moment, String forEach, String event, String columnName, String language, 
						String referencing, String whenClause, String status, String actionType, String body) {
		this.name = name;
		this.moment = moment;
		this.forEach = forEach;
		this.event = event;
		this.columnName = columnName;
		this.language = language;
		this.referencing = referencing;
		this.whenClause = whenClause;
		this.status = status;
		this.actionType = actionType;
		this.body = body;
	}
	/* (non-Javadoc)
	 * @see com.quantum.model.Trigger#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Trigger#getMoment()
	 */
	public String getMoment() {
		return moment;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Trigger#getEvent()
	 */
	public String getEvent() {
		return event;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Trigger#getColumnName()
	 */
	public String getColumnName() {
		return columnName;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Trigger#getReferencing()
	 */
	public String getReferencing() {
		return referencing;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Trigger#getLanguaje()
	 */
	public String getLanguage() {
		return language;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Trigger#getWhenClause()
	 */
	public String getWhenClause() {
		return whenClause;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Trigger#getStatus()
	 */
	public String getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Trigger#getActionType()
	 */
	public String getActionType() {
		return actionType;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Trigger#getBody()
	 */
	public String getBody() {
		return body;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Trigger#getForEach()
	 */
	public String getForEach() {
		return forEach;
	}
}
