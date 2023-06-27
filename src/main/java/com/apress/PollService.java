package com.apress;

import com.apress.domain.Poll;
import com.apress.error.ResourceNotFoundException;
import com.apress.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Optional;


@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public Iterable<Poll> getAllPolls(){
        Iterable<Poll> allPolls = pollRepository.findAll();
        return pollRepository.findAll();
    }

    public Optional<Poll> getPoll(Long pollId) {
        verifyPoll(pollId);
        Optional<Poll> p = pollRepository.findById(pollId);
        return pollRepository.findById(pollId);

    }

    public ResponseEntity<Poll> createPoll(Poll poll){
        poll = pollRepository.save(poll);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updatePoll(Poll poll, Long pollId) {
        verifyPoll(pollId);
        Poll p = pollRepository.save(poll);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    public ResponseEntity<?> deletePoll(Long pollId) {
        verifyPoll(pollId);
        pollRepository.deleteById(pollId);
        return ResponseEntity.noContent().build();
    }

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollRepository.findById(pollId);
        if(poll.isEmpty()){
            throw new ResourceNotFoundException("The id  " + pollId + " isn't found my friend but no problem try again");
        }
    }
}
