package io.github.fixitlater.quizapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleType roleName;

    public Role (RoleType roleName){
        this.roleName = roleName;
    }
}
