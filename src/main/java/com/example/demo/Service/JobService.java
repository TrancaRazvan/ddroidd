package com.example.demo.Service;

import com.example.demo.Model.Applicant;
import com.example.demo.Model.Job;
import com.example.demo.Model.User;
import com.example.demo.Repository.JobRepository;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    @Autowired
    private final JobRepository jobRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ApplicantService applicantService;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public boolean createJob(Job job) {
        if (job == null) {
            return false;
        } else {
            jobRepository.save(job);
            return true;
        }
    }

    public boolean addAplicantToJob(Long jobId, Applicant applicant) {
        Applicant savedApplicant = applicantService.saveApplicant(applicant);
        Job job = jobRepository.findById(jobId).orElse(null);
        if (job == null) {
            return false;
        } else {
            job.getApplicants().add(savedApplicant);
            jobRepository.save(job);
            return true;
        }
    }

//    public ResponseEntity<?> addEmpToJob(Job job, Long userId) {
//        createJob(job);
//        Job dbJob = jobRepository.findById(job.getId()).orElse(null);
//        User user = userRepository.findById(userId).orElse(null);
//        if (dbJob != null && user != null) {
//            dbJob.setUser(userId);                 /*i dont know how to save the user to the job post, it needs an User object
//                                                       but in database i have to add a user id and i cant add an object*/
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
}
