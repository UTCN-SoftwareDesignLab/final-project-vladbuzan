package com.assignment3.assignment3.rental.model;

import com.assignment3.assignment3.car.model.Car;
import com.assignment3.assignment3.user.model.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer")
    @NotNull
    private User customer;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "car")
    @NotNull
    private Car car;

    @Column
    @NotNull
    private LocalDateTime rentalStart;

    @Column
    @NotNull
    private LocalDateTime rentalEnd;

    @EqualsAndHashCode.Include
    @Enumerated(EnumType.STRING)
    @Column(length=10)
    private EStatus status;

}
