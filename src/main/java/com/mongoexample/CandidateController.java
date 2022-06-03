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
    @GetMapping("candidate/{id}")
    public Candidate getCandidate(@PathVariable String id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException() {}
    }
    @PostMapping("add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @PutMapping("update/{id}")
    public Candidate update(@PathVariable String id, @RequestBody Candidate updateCandidate) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        candidate.setName(updateCandidate.getName());
        candidate.setExp(updateCandidate.getExp());
        candidate.setEmail(updateCandidate.getEmail());
        return candidateRepository.save(candidate);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable String id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        candidateRepository.delete(candidate);

    }

}
