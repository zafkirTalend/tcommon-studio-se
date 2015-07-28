package org.talend.component;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@Api(value = "components", basePath = "/components", description = "Component services")
public class ComponentService {

	@RequestMapping(value = "/components/{name}/newProperties", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ComponentProperties newComponentProperties(
			@PathVariable(value = "name") @ApiParam(name = "name", value = "Name of the component") String componentName) {
		return new ComponentProperties();
	}

}
