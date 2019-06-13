/* **********************************************************************
* Copyright 2013 VMware, Inc.  All rights reserved. VMware Confidential
* **********************************************************************
* $Id:
* $DateTime:
* $Change:
* $Author:
* *********************************************************************/
package spring.boot.learn;

import org.springframework.web.client.RestTemplate;

import quick.poll.app.model.Poll;

public class PollApiTest {

	RestTemplate restTemplate = new RestTemplate();

	private static final String QUICK_POLL_URI_V1 = "http://localhost:8080/v1/polls";

	public Poll getPollById(Long pollId) {
		return restTemplate.getForObject(QUICK_POLL_URI_V1 + "/{pollId}", Poll.class, pollId);

	}

	public static void main(String[] args) {
		System.out.println("sefs");
		PollApiTest client = new PollApiTest();
		Poll poll = client.getPollById(1L);
		System.out.println(poll);
	}

}
