package com.example.demoJpa;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Entity
@Table(name = "user")
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "phone")
    private int phone;
    @Column(name = "password")
    private String password;
}
