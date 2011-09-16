package org.talend.designer.publish.core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.talend.designer.publish.core.internal.ChecksumComputor;
import org.talend.designer.publish.core.internal.MetadataModel;
import org.talend.designer.publish.core.internal.PomModel;
import org.xml.sax.SAXException;

public class UploadAction {

	public boolean deploy(String jarFilePath, String groupId,
			String artifactId, String version, String packaging,
			String repositoryUrl, String userName, String password)
			throws IOException, ParserConfigurationException, SAXException {
		return deploy(new File(jarFilePath), groupId, artifactId, version,
				packaging, repositoryUrl, userName, password);
	}

	private boolean deploy(File jarFile, String groupId, String artifactId,
			String version, String packaging, String repositoryUrl,
			String userName, String password) throws IOException,
			ParserConfigurationException, SAXException {
		String artifactPath = computeArtifactDestination(groupId, artifactId,
				packaging, repositoryUrl);
		String versionPath = artifactPath + version + "/";

		uploadBundleFile(versionPath, jarFile, artifactId, version, packaging,
				userName, password);

		uploadPomFile(versionPath, groupId, artifactId, version, packaging,
				userName, password);

		updateMetadataFile(artifactPath, groupId, artifactId, version,
				userName, password);

		return true;
	}

	private void uploadPomFile(String parentPath, String groupId,
			String artifactId, String version, String packaging,
			String userName, String password) throws IOException {
		// upload pom file
		PomModel pomModel = new PomModel(groupId, artifactId, version,
				packaging);
		String fileName = pomModel.getFileName();
		String filePath = parentPath + fileName;
		URL pomUrl = new URL(filePath);
		String pomContent = pomModel.toString();
		uploadContent(pomUrl, pomContent, userName, password);

		// upload md5 and sha1
		ChecksumComputor checksumComputor = new ChecksumComputor(pomContent);
		uploadMd5AndSha1(filePath, fileName, checksumComputor, userName,
				password);
	}

	private void uploadBundleFile(String parentUrl, File jarFile,
			String artifactId, String version, String packaging,
			String userName, String password) throws IOException {
		// upload jar file
		String fileName = computeBundleName(artifactId, version, packaging);
		String filePath = parentUrl + fileName;
		URL bundleUrl = new URL(filePath);
		uploadContent(bundleUrl, jarFile, userName, password);

		// upload md5 and sha1
		ChecksumComputor checksumComputor = new ChecksumComputor(jarFile);
		uploadMd5AndSha1(filePath, fileName, checksumComputor, userName,
				password);
	}

	private void updateMetadataFile(String artifactPath, String groupId,
			String artifactId, String version, String userName, String password)
			throws IOException, ParserConfigurationException, SAXException {
		String metadataPath = artifactPath + "maven-metadata.xml";
		URL metadataUrl = new URL(metadataPath);
		String readContent = readContent(metadataUrl, userName, password);
		MetadataModel metadataModel = null;
		if (readContent == null) {
			metadataModel = new MetadataModel(groupId, artifactId);
		} else {
			metadataModel = new MetadataModel(metadataPath);
		}
		metadataModel.addVersion(version);

		String newContent = metadataModel.getNewContent();
		uploadContent(metadataUrl, metadataModel.toString(), userName, password);

		// upload md5 and sha1
		ChecksumComputor checksumComputor = new ChecksumComputor(newContent);
		uploadMd5AndSha1(metadataPath, "maven-metadata.xml", checksumComputor,
				userName, password);
	}

	private void uploadMd5AndSha1(String sourceFilePath, String sourceFileName,
			ChecksumComputor checksumComputor, String userName, String password)
			throws IOException {
		// upload pom md5
		String md5CheckSum = checksumComputor.getMD5CheckSum();
		URL pomMd5Url = new URL(sourceFilePath + ".md5");
		uploadContent(pomMd5Url, md5CheckSum, userName, password);
		// upload pom sha1
		String sha1CheckSum = checksumComputor.getSHA1CheckSum();
		URL pomSha1Url = new URL(sourceFilePath + ".sha1");
		uploadContent(pomSha1Url, sha1CheckSum, userName, password);
	}

	private void uploadContent(URL targetURL, String content, String userName,
			String password) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(
				new ByteArrayInputStream(content.getBytes()));
		uploadContent(targetURL, bis, userName, password);
	}

	private void uploadContent(URL targetURL, File content, String userName,
			String password) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				content));
		uploadContent(targetURL, bis, userName, password);
	}

	private void uploadContent(URL targetURL, InputStream bis, String userName,
			String password) throws IOException {
		Authenticator.setDefault(new RepositoryAuthentication(userName,
				password));
		String host = targetURL.getHost();
		int port = targetURL.getPort();
		HttpURLConnection connection = (HttpURLConnection) targetURL
				.openConnection();
		connection.setDoOutput(true);

		connection.setRequestMethod("PUT");
		connection.setDoInput(true);
		connection.addRequestProperty("Host", host + ":" + port);
		connection.addRequestProperty("Accept",
				"text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
		connection.addRequestProperty("Connection", "keep-alive");

		byte[] contents = new byte[1024];
		OutputStream outputStream = connection.getOutputStream();
		int count = -1;
		while ((count = bis.read(contents)) != -1) {
			outputStream.write(contents, 0, count);
		}
		bis.close();
		outputStream.close();

		System.out.println("response....");
		InputStream inputStream = connection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String s = bufferedReader.readLine();
		while (s != null) {
			System.out.println(s);
			s = bufferedReader.readLine();
		}
		bufferedReader.close();
		System.out.println("end");
	}

	/**
	 * return the content file if the file not exist, return null
	 * 
	 * @param targetURL
	 * @param userName
	 * @param password
	 * @return null if file not found
	 * @throws IOException
	 */
	private String readContent(URL targetURL, String userName, String password)
			throws IOException {
		try {
			Authenticator.setDefault(new RepositoryAuthentication(userName,
					password));
			HttpURLConnection connection = (HttpURLConnection) targetURL
					.openConnection();
			connection.setDoInput(true);

			connection.setRequestMethod("GET");
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));
			StringBuilder sb = new StringBuilder();

			String s = bufferedReader.readLine();
			while (s != null) {
				sb.append(s);
				sb.append("\n");
				s = bufferedReader.readLine();
			}
			bufferedReader.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	/**
	 * the parent path of Bundle and Pom files it ends with "/"
	 * 
	 * @param groupId
	 * @param artifactId
	 * @param version
	 * @param packaging
	 * @param repositoryUrl
	 * @return
	 * @throws MalformedURLException
	 */
	private String computeArtifactDestination(String groupId,
			String artifactId, String packaging, String repositoryUrl)
			throws MalformedURLException {
		StringBuilder sb = new StringBuilder();
		sb.append(repositoryUrl);
		if (!repositoryUrl.endsWith("/")) {
			sb.append("/");
		}
		String replacedGroupId = groupId.replaceAll("\\.", "/");
		sb.append(replacedGroupId);
		sb.append("/");
		sb.append(artifactId);
		sb.append("/");
		return sb.toString();
	}

	private String computeBundleName(String artifactId, String version,
			String packaging) throws MalformedURLException {
		StringBuilder sb = new StringBuilder();
		sb.append(artifactId);
		sb.append("-");
		sb.append(version);
		sb.append(".");
		sb.append(packaging);
		return sb.toString();
	}

	class RepositoryAuthentication extends Authenticator {
		private String userName;
		private String password;

		public RepositoryAuthentication(String userName, String password) {
			super();
			this.userName = userName;
			this.password = password;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password.toCharArray());
		}
	}

	//for test
//	public static void main(String[] args) throws IOException,
//			ParserConfigurationException, SAXException {
//		UploadAction uploadAction = new UploadAction();
//		uploadAction.deploy("TestEERoute_0.1.jar", "org.talend.liugang",
//				"TestEERoute", "1.0.49-SNAPSHOT", "jar",
//				"http://localhost:8080/archiva/repository/snapshots/", "gliu",
//				"liugang123");
//	}
}
