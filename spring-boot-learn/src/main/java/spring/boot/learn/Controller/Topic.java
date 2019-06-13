/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package spring.boot.learn.Controller;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="topic")
@XmlAccessorType(XmlAccessType.FIELD)
public class Topic implements Serializable{

    private String id;
    private String name;
    private String description;
    
    public Topic(){
        
    }
    
    public Topic(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
            
    public Topic(String id, String name,String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
}
