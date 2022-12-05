package com.example.securitywithjwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.id.IncrementGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String fileName;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private User user;

}
