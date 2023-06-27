package com.apress.controller;

import com.apress.domain.Poll;

import com.apress.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;

    @GetMapping("/polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls() {

        return ResponseEntity.ok(pollService.getAllPolls());
    }

    @GetMapping("/polls/{pollId}")
    public Optional<Poll> getPoll(@PathVariable Long pollId) {
        return pollService.getPoll(pollId);
    }

    @PostMapping("/polls")
    public ResponseEntity<Poll> createPoll(@Valid @RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }
    @DeleteMapping("/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        return pollService.deletePoll(pollId);
    }

    @PutMapping("/polls/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        return pollService.updatePoll(poll, pollId);
    }


}
