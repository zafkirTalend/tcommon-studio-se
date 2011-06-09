package com.talend.tac.cases.executePlan;

import org.testng.annotations.Test;

public class TestExecutionPlanMenu extends Plan {

	@Test(groups = { "Menu" })
	
	public void testOpenMenuExecutionPlan() {
		this.openExecutionPlanMenu();

	}

}
