package me.jiniworld.demo.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    private String name;
}
