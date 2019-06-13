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

import quick.poll.app.model.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long>{
    

    @Query(value="select v.* from option o, vote v where o.poll_id = ?1 and o.option_id = v.option_id",nativeQuery=true)
    public Iterable<Vote> findByPoll(Long pollId);
}
