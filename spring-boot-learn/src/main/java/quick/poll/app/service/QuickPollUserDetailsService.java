/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package quick.poll.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import quick.poll.app.dao.UserRepository;
import quick.poll.app.model.User;

@Component
public class QuickPollUserDetailsService implements UserDetailsService {

	Logger logger = LogManager.getLogger(QuickPollUserDetailsService.class);
	@Inject
	private UserRepository userRepository;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		logger.info("User found for user name: " + user);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("User with the username %s doesn't exist", username));
			}

		List<GrantedAuthority> authorities = new ArrayList<>();
		if (user.isAdmin()) {
			authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
		}
		UserDetails ud = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
		        authorities);
		return ud;
    }


}
