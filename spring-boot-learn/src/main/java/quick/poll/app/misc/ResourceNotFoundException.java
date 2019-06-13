/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package quick.poll.app.misc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException  extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException() {}
    
    public ResourceNotFoundException(String exception) {
        super(exception);
    }
    
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
        }
}
