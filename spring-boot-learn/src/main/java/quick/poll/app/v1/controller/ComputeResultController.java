/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package quick.poll.app.v1.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import quick.poll.app.dao.VoteRepository;
import quick.poll.app.dto.OptionCount;
import quick.poll.app.dto.VoteResult;
import quick.poll.app.model.Option;
import quick.poll.app.model.Vote;

@RequestMapping("/v1")
@RestController("computeResultControllerV1")
public class ComputeResultController {

    @Inject
    private VoteRepository voteRepository;
    
    
    @RequestMapping(value="/computeresult", method=RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId){
        Iterable<Vote> votes = voteRepository.findByPoll(pollId);
        Map<Long, Long> collect = StreamSupport.stream(votes.spliterator(),false).map(vote-> vote.getOption()).
        collect(Collectors.groupingBy(Option::getId,Collectors.counting()));
        List<OptionCount> voteCount = collect.entrySet().stream().map(entry -> new OptionCount(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        
        System.out.println(collect);
        long totalCount = voteCount.stream().mapToLong(o -> o.getCount()).sum();
        VoteResult  result = new VoteResult();
        result.setTotalVotes((int)totalCount);
        result.setResults(voteCount);
        System.out.println("total count:"+totalCount);
        return new ResponseEntity<VoteResult>(result, HttpStatus.OK);
    }
}
