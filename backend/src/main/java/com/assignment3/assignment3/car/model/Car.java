package com.assignment3.assignment3.car.model;

import com.assignment3.assignment3.office.model.Office;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String description;

    @ManyToOne
    @JoinColumn(name = "manufacturer")
    @NotNull
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "office")
    @NotNull
    private Office office;

    @Column(length = 50)
    private String model;

    @Column(columnDefinition = "boolean default true")
    private Boolean isAvailable = true;



}
