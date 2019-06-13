/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package quick.poll.app.misc;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class QuickPollMvcConfigAdapter extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver phmr = new PageableHandlerMethodArgumentResolver();
		phmr.setFallbackPageable(new PageRequest(0, 5));
		argumentResolvers.add(phmr);
		// super.addArgumentResolvers(Arrays.asList(phmr));
	}

}
