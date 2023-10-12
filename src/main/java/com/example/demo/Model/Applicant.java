package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@Table(name = "applicant")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String country;
    private String state;
    private String city;
    private String email;

    @ManyToMany(mappedBy = "applicants")
    @JsonIgnore
    private Set<Job> jobsApplied = new HashSet<>();

}
