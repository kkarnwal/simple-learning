/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package spring.boot.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApiApp {

    
    public static void main(String[] args) {
        SpringApplication.run(CourseApiApp.class, args);
    }
}
