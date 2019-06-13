/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package quick.poll.app.v1.controller;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import quick.poll.app.dao.VoteRepository;
import quick.poll.app.model.Vote;

@RequestMapping("/v1")
@RestController("voteControllerV1")
public class VoteController {
    
    Logger logger = LogManager.getLogger(PollController.class);

    @Inject
    private VoteRepository voteRepository;
    
    @RequestMapping(value="/polls/{pollId}/votes",method=RequestMethod.POST)
    public ResponseEntity<?> ceateVote(@PathVariable Long pollId,@RequestBody Vote vote){
        Vote save = voteRepository.save(vote);
        logger.info("saving vote = "+vote+", for poll = "+pollId);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
        
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
    public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
        return voteRepository. findByPoll(pollId);
    }
    
}
