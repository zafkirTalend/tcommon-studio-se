package com.talend.tac.cases.executePlan;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddPlan extends Plan {

	// add a plan
	@Test(groups = { "addplan" },dependsOnGroups={"Menu"})
	// , dependsOnGroups = { "cleanplan" }
	@Parameters({ "plan.label", "plan.description", "plan.task" })
	public void testAddPlan(String label, String description, String task) {
		this.addPlan(label, task, description);
		// addPlan(label, description, task);

	}

}
