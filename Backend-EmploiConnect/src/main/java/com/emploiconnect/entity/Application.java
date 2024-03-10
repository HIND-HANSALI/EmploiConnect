package com.emploiconnect.entity;
import com.emploiconnect.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private String title;
    private String cv;
    private String profile;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Offer offer;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private User user;
}
