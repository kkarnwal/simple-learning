/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package quick.poll.app.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

@Entity
public class Poll extends ResourceSupport {

    @Id
    @GeneratedValue
    @Column(name="POLL_ID")
	@ApiModelProperty(value = "Database generated id.")
    private Long id;
    
    @Column(name="QUESTION")
	@NotNull
	@Size(min = 1, max = 100)
    private String question;
    
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="POLL_ID")
    @OrderBy
	@Size(max = 6, min = 2)
    private Set<Option> options;


	public Long getPollId() {
        return id;
    }


	public void setPollId(Long id) {
        this.id = id;
    }


    public String getQuestion() {
        return question;
    }


    public void setQuestion(String question) {
        this.question = question;
    }


    public Set<Option> getOptions() {
        return options;
    }


    public void setOptions(Set<Option> options) {
        this.options = options;
    }


    @Override
    public String toString() {
        return "Poll [id=" + id + ", question=" + question + ", options=" + options + "]";
    }
    
        
}
