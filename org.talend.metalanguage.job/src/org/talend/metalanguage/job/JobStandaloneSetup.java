
package org.talend.metalanguage.job;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class JobStandaloneSetup extends JobStandaloneSetupGenerated{

	public static void doSetup() {
		new JobStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

