package io.github.fixitlater.quizapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends BaseEntity{
    @Column (name = "name", length = 100)
    private String name;
    @Column (name = "email", length = 150)
    private String email;
    @Column (name = "password")
    private String passwordHash;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
