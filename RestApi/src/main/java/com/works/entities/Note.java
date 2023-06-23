package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nid;

    private String title;
    private String detail;

}
