/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package quick.poll.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import quick.poll.app.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
	@Query(value = "select u.* from Users u where u.USERNAME=?1", nativeQuery = true)
	public User findByUsername(String username);
}
