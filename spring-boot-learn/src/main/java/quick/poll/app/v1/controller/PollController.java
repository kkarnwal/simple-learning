/* **********************************************************************
 * Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
 * **********************************************************************
 * $Id:
 * $DateTime:
 * $Change:
 * $Author:
 * *********************************************************************/
package quick.poll.app.v1.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

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

import quick.poll.app.dao.PollRepository;
import quick.poll.app.misc.ResourceNotFoundException;
import quick.poll.app.model.Poll;

@RequestMapping("/v1")
@RestController("pollControllerV1")
@Api(value = "Polls", description = "poll api.")
public class PollController {

	Logger logger = LogManager.getLogger(PollController.class);

	@Inject
	private PollRepository pollRepository;

	/*
	 * @RequestMapping("/polltest") String sayHi() { return "hi"; }
	 */

	@ApiOperation(value = "list all polls", response = Iterable.class)
	@RequestMapping(method = RequestMethod.GET, value = "/polls", produces = "application/json")
	public ResponseEntity getAllPolls() {
		Iterable<Poll> findAll = pollRepository.findAll();
		findAll.forEach(poll -> {
			logger.info(poll);
			updatePollResourceWithLinks(poll);
		});
		return new ResponseEntity(findAll, HttpStatus.OK);
	}

	@ApiOperation(value = "get poll", response = Poll.class)
	@RequestMapping(method = RequestMethod.GET, value = "/polls/{id}")
	public ResponseEntity getPoll(@PathVariable Long id) {
		Optional<Poll> poll = pollRepository.findById(id);
		if (poll.isPresent()) {
			updatePollResourceWithLinks(poll.get());
			return new ResponseEntity(poll.get(), HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Record not found for id:" + id);
		}
	}

	@RequestMapping(value = "/polls", method = RequestMethod.POST)
	public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
		logger.info("poll create req = " + poll);
		Poll save = pollRepository.save(poll);
		logger.info("poll create req = " + poll + " successfull.");
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
.path("/{id}").buildAndExpand(save.getPollId())
		        .toUri();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uri);

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/polls/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePoll(@RequestBody Poll poll,
	        @PathVariable Long id) {
		verify(id);
		poll.setPollId(id);
		Poll save = pollRepository.save(poll);
		logger.info(save + " updated req successfull.");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private void updatePollResourceWithLinks(Poll poll) {
		poll.add(linkTo(methodOn(PollController.class).getAllPolls()).slash(poll.getPollId()).withSelfRel());
		poll.add(linkTo(methodOn(VoteController.class).getAllVotes(poll.getPollId())).withRel("votes"));
		poll.add(linkTo(methodOn(ComputeResultController.class).computeResult(poll.getPollId())).withRel(
		        "compute-result"));

	}

	private void verify(Long id) {
		Optional<Poll> poll = pollRepository.findById(id);
		if (!poll.isPresent()) {
			throw new ResourceNotFoundException("Poll not found for id:" + id);
		}

	}

	@RequestMapping(value = "/polls/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePoll(@PathVariable Long id) {
		verify(id);
		pollRepository.deleteById(id);
		logger.info("poll id =  " + id + " , deleted successfully.");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
