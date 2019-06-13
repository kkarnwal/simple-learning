/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package quick.poll.app.v3.controller;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import quick.poll.app.dao.UserRepository;
import quick.poll.app.model.User;

@RequestMapping("/v3")
@RestController("userControllerV3")
public class UserController {
    
    Logger logger = LogManager.getLogger(PollController.class);

    @Inject
	private UserRepository userRepository;
    
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> getAllUsers() {
		Iterable<User> findAll = userRepository.findAll();
		logger.info("users count:" + findAll);
		return new ResponseEntity<Iterable<User>>(findAll, HttpStatus.OK);
    }
    
}
