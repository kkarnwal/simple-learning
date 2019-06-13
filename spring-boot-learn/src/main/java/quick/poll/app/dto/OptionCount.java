/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package quick.poll.app.dto;

public class OptionCount {

    private Long optionId;
    private Long count;
    
    
    
    public OptionCount(Long optionId, Long count) {
        super();
        this.optionId = optionId;
        this.count = count;
    }
    public Long getOptionId() {
        return optionId;
    }
    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }
    
    
}
