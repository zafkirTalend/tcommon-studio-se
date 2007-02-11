/*
 * Created on 5/03/2005
 *
 */
package com.quantum.model;

/**
 * @author Julen
 *
 */
public class CheckImpl implements Check{
	private String name;
	private String body;
	
	public CheckImpl(String name, String body) {
		this.name = name;
		this.body = body;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Check#getBody()
	 */
	public String getBody() {
		return body;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Check#getName()
	 */
	public String getName() {
		return name;
	}
}
