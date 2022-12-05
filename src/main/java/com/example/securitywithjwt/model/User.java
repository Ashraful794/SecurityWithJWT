package com.example.securitywithjwt.model;

import com.example.securitywithjwt.validation.ValidUserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false , length=100)
    private String name;

    @Column(nullable=false,unique = true)
    private String email;

    @Column(nullable=false)
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Role> role=new HashSet<>(Arrays.asList(Role.ROLE_USER));

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Photo> photo;


    //custom annotation
    @ValidUserType
    private String userType; //permanent or vendor or contractual


}
