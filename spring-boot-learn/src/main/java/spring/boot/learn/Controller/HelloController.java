/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package spring.boot.learn.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    private List<Topic> entityList = new ArrayList<>();
    
    {
        
        entityList.add(new Topic("1", "entity_1"));
        entityList.add(new Topic("2", "entity_2"));
        entityList.add(new Topic("3", "entity_3"));
        entityList.add(new Topic("4", "entity_4"));
    }
    @RequestMapping("/hello")
    String sayHi(){
        return "hi";
    }
    
    @RequestMapping(value="/topics",produces={MediaType.APPLICATION_XML_VALUE})
    List<Topic> getAllTopic(){
        return entityList;
    }
    
    @RequestMapping("/topics/all")
    public List<Topic> findAll() {
//        MultiValueMap<Stirng, String>
//        new ResponseEntity<>(entityList, headers, HttpStatus.OK);
        return entityList;
    }
 
    @RequestMapping(value = "/topics", method = RequestMethod.POST)
    public Topic addEntity(Topic entity) {
        entityList.add(entity);
        return entity;
    }
 
    @RequestMapping(value="/topics/findby/{id}",produces={MediaType.APPLICATION_XML_VALUE})
    public Topic findById(@PathVariable String id) {
        return entityList.stream().
                 filter(entity -> entity.getId().equals(id)).
                   findFirst().get();
    }
}
