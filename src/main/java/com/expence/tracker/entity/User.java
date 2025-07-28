package com.expence.tracker.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name="user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @Column(unique = true)
    private String email;
    private Long age;
    @Column(name = "createdAt", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updatedAt")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
