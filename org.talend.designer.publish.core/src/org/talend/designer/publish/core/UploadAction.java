package org.talend.designer.publish.core;

import java.io.File;

import org.talend.designer.publish.core.internal.BundleModel;
import org.talend.designer.publish.core.internal.FeaturesModel;

public class UploadAction {

	public boolean deploy(String jarFilePath, String groupId,
			String artifactId, String version, String repositoryUrl,
			String userName, String password) throws Exception {
		return deploy(new File(jarFilePath), groupId, artifactId, version,
				repositoryUrl, userName, password);
	}

	private boolean deploy(File jarFile, String groupId, String artifactId,
			String version, String repositoryUrl, String userName,
			String password) throws Exception {
		deployBundle(jarFile, groupId, artifactId, version, repositoryUrl,
				userName, password);

		deployFeature(jarFile, groupId, artifactId, version, repositoryUrl,
				userName, password);

		return true;
	}

	private void deployFeature(File jarFile, String groupId, String artifactId,
			String version, String repositoryURL, String userName,
			String password) throws Exception {
		FeaturesModel featuresModel = new FeaturesModel(groupId, artifactId, version, repositoryURL, userName, password);
		featuresModel.upload();

	}

	private void deployBundle(File jarFile, String groupId, String artifactId,
			String version, String repositoryURL, String userName,
			String password) throws Exception {
		BundleModel bundleModel = new BundleModel(jarFile, groupId, artifactId, version, repositoryURL, userName, password);
		bundleModel.upload();
	}

	// for test
	public static void main(String[] args) throws Exception {
		UploadAction uploadAction = new UploadAction();
		uploadAction.deploy("TestEERoute_0.1.jar", "ggg.talend.liugang",
				"TestEERoute", "2.0.3-SNAPSHOT",
				"http://localhost:8080/archiva/repository/snapshots/", "gliu",
				"liugang123");
	}
}
