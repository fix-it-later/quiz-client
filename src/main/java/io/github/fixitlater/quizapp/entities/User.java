package io.github.fixitlater.quizapp.entities;

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
    @Column (name = "email", length = 150)
    private String email;
    @Column (name = "password")
    private String passwordHash;
    @ManyToOne
    private Role role;
}
