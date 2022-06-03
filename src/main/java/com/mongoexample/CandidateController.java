package com.mongoexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("get/candidates")
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @PostMapping("add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return candidateRepository.save(candidate);
    }

}
