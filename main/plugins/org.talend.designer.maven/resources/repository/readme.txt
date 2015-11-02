1) The "maven_repository.zip" contained most of maven artifacts for some phases, like test, package, install, deploy.

2) If the value of system property "maven.repository" is following, will change the value of "localRepository" for "maven_user_settings.xml".
	- local, will force updating the value of "localRepository" to location "<product>/configuration/.m2/repository".
	- global, try to set the value of "localRepository" to location "<user>/.m2/repository".
	          but if no rights to access this global location, will do same as "local".
	          
3) Those embeded artifacts from "maven_repository.zip" are copied in the location.
	- local, when not exist, then create it, will do copy at same time.
	- global, only when start product is in first time.
