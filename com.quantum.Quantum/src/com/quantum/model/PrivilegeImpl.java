package com.quantum.model;

public class PrivilegeImpl implements Privilege {

	private String grantor;
	private String grantee;
	private String access;
	private boolean grantable;

	public PrivilegeImpl(String grantor, String grantee, String access, boolean grantable) {
		this.grantor = grantor;
		this.grantee = grantee;
		this.access = access;
		this.grantable = grantable;
	}
	
	/* (non-Javadoc)
	 * @see com.quantum.model.Privilege#getAccess()
	 */
	public String getAccess() {
		return access;
	}
	
	/* (non-Javadoc)
	 * @see com.quantum.model.Privilege#isGrantable()
	 */
	public boolean isGrantable() {
		return grantable;
	}
	
	/* (non-Javadoc)
	 * @see com.quantum.model.Privilege#getGrantee()
	 */
	public String getGrantee() {
		return grantee;
	}
	
	/* (non-Javadoc)
	 * @see com.quantum.model.Privilege#getGrantor()
	 */
	public String getGrantor() {
		return grantor;
	}
	
	

}
