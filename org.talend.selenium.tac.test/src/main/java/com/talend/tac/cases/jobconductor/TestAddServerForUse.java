package com.talend.tac.cases.jobconductor;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestAddServerForUse extends Server {
	@Test
	@Parameters({ "ServerForUseAvailable", "ServerForUseUnavailable","ServerForUseDownlabel",
			"ServeravailableHost" })
	public void testAddServerForUseAvailable(String lableavailable, String labelunactive,String downlabel,
			String availablehost) throws InterruptedException {
		this.openServerMenu();
		this.deleteAllServersNotUsed();
		this.addServer(lableavailable,"", availablehost,true);
		this.checkServerStatus(lableavailable, server_status_up);
		this.addServer(labelunactive,"",availablehost,false);
		this.checkServerStatus(labelunactive, server_status_inactive);
	}

	@Test
	@Parameters({ "ServerForUseAvailable", "ServerForUseUnavailable","ServerForUseDownlabel",
			"ServeravailableHost" })
	public void testAddServerForUseInactive(String lableavailable, String labelunactive,String downlabel,
			String availablehost) throws InterruptedException {
		this.openServerMenu();
		this.addServer(labelunactive,"",availablehost,Server.server_active_false);
		this.checkServerStatus(labelunactive, server_status_inactive);
	}

}
