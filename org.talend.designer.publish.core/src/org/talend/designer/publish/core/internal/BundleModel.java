package org.talend.designer.publish.core.internal;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BundleModel extends UploadableModel {

	private File jarFile;

	public BundleModel(File jarFile, String groupId, String artifactId,
			String version, String repositoryURL, String userName,
			String password) {
		super(groupId, artifactId, version, repositoryURL, userName, password);
		this.jarFile = jarFile;
	}

	@Override
	public void upload() throws Exception {
		uploadBundle();
		uploadPom();
		uploadMetadata();
	}

	private void uploadMetadata() throws Exception {
		MetadataModel metadataModel = new MetadataModel(groupId, artifactId,
				version, repositoryURL, userName, password);
		metadataModel.addVersion(version);
		metadataModel.upload();
	}

	private void uploadPom() throws IOException {
		// upload pom part
		PomModel pomModel = new PomModel(groupId, artifactId, version, "bundle",
				repositoryURL, userName, password);
		pomModel.upload();
	}

	private void uploadBundle() throws IOException {
		String artifactPath = getArtifactDestination();
		String versionPath = artifactPath + version + "/";
		// upload jar file
		String fileName = computeBundleName(artifactId, version);
		String filePath = versionPath + fileName;
		URL bundleUrl = new URL(filePath);
		uploadContent(bundleUrl, jarFile);

		// upload md5 and sha1
		uploadMd5AndSha1(filePath, fileName, jarFile);
	}

	private String computeBundleName(String artifactId, String version)
			throws MalformedURLException {
		StringBuilder sb = new StringBuilder();
		sb.append(artifactId);
		sb.append("-");
		sb.append(version);
		sb.append(".jar");
		return sb.toString();
	}
}
