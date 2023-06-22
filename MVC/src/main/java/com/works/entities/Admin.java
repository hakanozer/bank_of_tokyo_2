package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    @Email
    @Size(min = 6, max = 200)
    @NotEmpty
    @NotNull
    private String email;

    @Size(min = 3, max = 100)
    @NotEmpty
    @NotNull
    private String password;

    private String remember;

}
