package org.talend.designer.publish.core.models;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class FeaturesModel extends UploadableModel {

	private Set<String> subFeatures = new HashSet<String>();

	private Set<String> subBundles = new HashSet<String>();

	private String sourceArtifactId ;
	
	public FeaturesModel(String groupId, String sourceArtifactId, String version,
			String repositoryURL, String userName, String password) {
		super(groupId, sourceArtifactId + "-feature", version, repositoryURL, userName, password);
		this.sourceArtifactId = sourceArtifactId;
	}

	@Override
	public void upload() throws Exception {
		uploadFeature();
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
		PomModel pomModel = new PomModel(groupId, artifactId, version, "pom",
				repositoryURL, userName, password);
		pomModel.addDenpendency(new DependencyModel(groupId, sourceArtifactId,
				version));
		pomModel.upload();
	}

	private void uploadFeature() throws MalformedURLException, IOException {
		addSubBundle(groupId, sourceArtifactId, version);
		// upload feature part
		String artifactPath = getArtifactDestination();
		String versionPath = artifactPath + version + "/";
		String fileName = artifactId + "-" + version + ".xml";
		String filePath = versionPath + fileName;
		URL featureURL = new URL(filePath);
		String featureContent = getFeatureContent();
		uploadContent(featureURL, featureContent);

		// upload md5 and sha1
		uploadMd5AndSha1(filePath, fileName, featureContent);
	}

	public void addSubFeature(String version, String name) {
		StringBuilder sb = new StringBuilder();
		sb.append("<feature version='");
		sb.append(version);
		sb.append("\'>");
		sb.append(name);
		sb.append("</feature>");
		subFeatures.add(sb.toString());
	}

	public void addSubBundle(String groupId, String artifactId, String version) {
		StringBuilder sb = new StringBuilder();
		sb.append("<bundle>mvn:");
		sb.append(groupId);
		sb.append("/");
		sb.append(artifactId);
		sb.append("/");
		sb.append(version);
		sb.append("</bundle>");
		subBundles.add(sb.toString());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<features>\n");
		sb.append("\t<feature name=\"");
		sb.append(artifactId);
		sb.append("\" version=\"");
		sb.append(version);
		sb.append("\">\n");

		for (String s : subFeatures) {
			sb.append("\t\t");
			sb.append(s);
			sb.append("\n");
		}
		for (String s : subBundles) {
			sb.append("\t\t");
			sb.append(s);
			sb.append("\n");
		}
		sb.append("\t</feature>\n");
		sb.append("</features>");

		return sb.toString();
	}

	public String getFeatureContent() {
		return toString();
	}

	// public static void main(String[] args) {
	// FeaturesModel featuresModel = new FeaturesModel(
	// "CustomService-feature", "1.0.0");
	// featuresModel.addSubBundle("talend", "job-control-bundle", "1.0");
	// featuresModel.addSubBundle("talend", "ProviderJob", "1.0");
	// featuresModel.addSubBundle("talend", "ESBProvider2", "1.0");
	// featuresModel.addSubFeature("5.0-SNAPSHOT", "talend-job-controller");
	// System.out.println(featuresModel);
	// }

}
