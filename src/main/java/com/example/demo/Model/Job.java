package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "job")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    private Long id;
    private String title;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "job_applicant",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "applicant_id")
    )
    private Set<Applicant> applicants;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
