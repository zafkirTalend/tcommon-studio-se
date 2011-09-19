package org.talend.designer.publish.core;

import java.io.File;
import java.util.Set;

import org.talend.designer.publish.core.models.BundleModel;
import org.talend.designer.publish.core.models.DependencyModel;
import org.talend.designer.publish.core.models.FeaturesModel;

public class UploadAction {

	public boolean deployRoute(String jarFilePath, String groupId,
			String artifactId, String version,
			Set<DependencyModel> dependencies, String repositoryUrl,
			String userName, String password) throws Exception {
		return deployRoute(new File(jarFilePath), groupId, artifactId, version,
				dependencies, repositoryUrl, userName, password);
	}

	private boolean deployRoute(File jarFile, String groupId, String artifactId,
			String version, Set<DependencyModel> dependencies, String repositoryUrl, String userName,
			String password) throws Exception {
		deployBundle(jarFile, groupId, artifactId, version, dependencies, repositoryUrl,
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
			String version, Set<DependencyModel> dependencies, String repositoryURL, String userName,
			String password) throws Exception {
		BundleModel bundleModel = new BundleModel(jarFile, groupId, artifactId, version, repositoryURL, userName, password);
		bundleModel.addAllDependencies(dependencies);
		bundleModel.upload();
	}

	// for test
	public static void main(String[] args) throws Exception {
		UploadAction uploadAction = new UploadAction();
		uploadAction.deployRoute("TestEERoute_0.1.jar", "ggg.talend.liugang",
				"TestEERoute2", "2.0.22-SNAPSHOT",null,
				"http://localhost:8080/archiva/repository/snapshots/", "gliu",
				"liugang123");
	}
}
