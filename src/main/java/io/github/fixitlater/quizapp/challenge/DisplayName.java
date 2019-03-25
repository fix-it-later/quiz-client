package io.github.fixitlater.quizapp.challenge;

import io.github.fixitlater.quizapp.entities.User;

import javax.persistence.*;

@Entity
@Table(name = "display_name")
public class DisplayName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 30)
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
