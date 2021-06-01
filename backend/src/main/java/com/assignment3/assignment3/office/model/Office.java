package com.assignment3.assignment3.office.model;

import com.assignment3.assignment3.car.model.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "office", cascade = CascadeType.REMOVE)
    private List<Car> cars;

    @Column
    private Double latitude;

    @Column
    private Double longitude;


}
