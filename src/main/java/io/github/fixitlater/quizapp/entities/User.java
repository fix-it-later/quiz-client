package io.github.fixitlater.quizapp.entities;

import io.github.fixitlater.quizapp.challenge.DisplayName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;



@Entity
@Setter
@Getter
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate addedDate;
    @Column (name = "name", length = 100)
    private String name;
    @Column (name = "email", length = 150, unique = true)
    private String email;
    @Column (name = "password")
    private String passwordHash;
    @ManyToOne
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    private DisplayName displayName;
}
