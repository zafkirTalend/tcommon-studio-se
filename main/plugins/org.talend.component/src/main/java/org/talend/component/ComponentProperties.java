package org.talend.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("componentProperties")
public class ComponentProperties {
	
	@JsonProperty
	String testProp;

}
