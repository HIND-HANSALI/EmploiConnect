package com.emploiconnect.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String sector;

    private String location;

    private LocalDate foundationDate;

    private String specializations;

    private String description;

    private boolean isDefault;

    @OneToMany(mappedBy = "company")
    private List<User> users;
}
