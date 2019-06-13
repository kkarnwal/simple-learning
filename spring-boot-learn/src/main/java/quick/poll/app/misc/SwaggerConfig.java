/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package quick.poll.app.misc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("v1").select()
		        .apis(RequestHandlerSelectors.basePackage("quick.poll.app.v1.controller"))
		        .paths(PathSelectors.ant("/v1/*"))
		        .build().apiInfo(metaData()).
		        useDefaultResponseMessages(false);
	}

	@Bean
	public Docket productApiV2() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("v2").select()
		        .apis(RequestHandlerSelectors.basePackage("quick.poll.app.v2.controller"))
		        .paths(PathSelectors.ant("/v2/*")).build().apiInfo(metaData()).useDefaultResponseMessages(false);
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Spring Boot REST API", "Spring Boot REST API for quick poll", "1.0",
		        "Terms of service", "kapil karnwal", "Apache License Version 2.0",
		        "https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}


}
