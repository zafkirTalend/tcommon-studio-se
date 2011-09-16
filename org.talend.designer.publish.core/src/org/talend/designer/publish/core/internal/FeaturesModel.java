package org.talend.designer.publish.core.internal;

import java.util.ArrayList;
import java.util.List;

public class FeaturesModel {

	private String featureName;
	private String version;

	private List<String> subFeatures = new ArrayList<String>();

	private List<String> subBundles = new ArrayList<String>();

	public FeaturesModel(String featureName, String version) {
		super();
		this.featureName = featureName;
		this.version = version;
	}

	public String getFeatureName() {
		return featureName;
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
		sb.append(featureName);
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
	
	public String getFeatureContent(){
		return toString();
	}

//	public static void main(String[] args) {
//		FeaturesModel featuresModel = new FeaturesModel(
//				"CustomService-feature", "1.0.0");
//		featuresModel.addSubBundle("talend", "job-control-bundle", "1.0");
//		featuresModel.addSubBundle("talend", "ProviderJob", "1.0");
//		featuresModel.addSubBundle("talend", "ESBProvider2", "1.0");
//		featuresModel.addSubFeature("5.0-SNAPSHOT", "talend-job-controller");
//		System.out.println(featuresModel);
//	}

}
