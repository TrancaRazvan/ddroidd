package com.example.demo.Service;

import com.example.demo.Model.Applicant;
import com.example.demo.Model.Job;
import com.example.demo.Repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    @Autowired
    private final JobRepository jobRepository;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public boolean createJob(Job job) {
        if (job == null){
            return false;
        }else {
            jobRepository.save(job);
            return true;
        }
    }
    public boolean addAplicantToJob(Long jobId, Applicant applicant){
        Job job =jobRepository.findById(jobId).orElse(null);
        if (job == null){
            return false;
        }else {
            job.getApplicants().add(applicant);
            jobRepository.save(job);
            return true;
        }
    }
}
