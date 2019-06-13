/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package quick.poll.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import quick.poll.app.model.Poll;

public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {
    

}
