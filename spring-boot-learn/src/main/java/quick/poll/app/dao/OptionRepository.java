/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package quick.poll.app.dao;

import org.springframework.data.repository.CrudRepository;

import quick.poll.app.model.Option;

public interface OptionRepository extends CrudRepository<Option, Long>{
    

}
