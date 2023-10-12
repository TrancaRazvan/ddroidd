package com.example.demo.Service;

import com.example.demo.Model.Employer;
import com.example.demo.Repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployerService {
    @Autowired
    private final EmployerRepository employerRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public Employer registerEmployer(Employer employer) {
        if (employerRepository.findById(employer.getId()).isPresent()) {
            return null;
        }
        String encodedPass = passwordEncoder.encode(employer.getPassword());
        employer.setPassword(encodedPass);
        employer.setRole("EMPLOYER");
        return employerRepository.save(employer);
    }
}
