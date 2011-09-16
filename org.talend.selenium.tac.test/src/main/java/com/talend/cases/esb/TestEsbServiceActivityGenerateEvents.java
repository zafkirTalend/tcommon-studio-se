package com.talend.cases.esb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestEsbServiceActivityGenerateEvents extends Esb {
	@Test
	@Parameters({"esb.monitor.karafurl","esb.monitor.receivenewevents.consumer","esb.monitor.generateNumForPageDisplay"})
	public void testGenerateEventsForPageDisplay(String karafUrl,String consumerName,String intNum){
		this.generateEvents(karafUrl, consumerName, Integer.parseInt(intNum));
	}
	
	@Test
	@Parameters({"esb.monitor.karafurl","esb.monitor.receivenewevents.consumer","esb.monitor.generateNumForTest"})
	public void testGenerateEventsTest(String karafUrl,String consumerName,String intNum){
		this.generateEvents(karafUrl, consumerName, Integer.parseInt(intNum));
	}
}
