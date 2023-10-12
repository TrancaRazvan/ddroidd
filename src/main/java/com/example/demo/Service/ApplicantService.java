package com.example.demo.Service;

import com.example.demo.Model.Applicant;
import com.example.demo.Repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantService {
    @Autowired
    private final ApplicantRepository applicantRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public Applicant saveApplicant(Applicant applicant) {
        if (applicantRepository.findById(applicant.getId()).isPresent()) {
            return null;
        }
        return applicantRepository.save(applicant);
    }
}
