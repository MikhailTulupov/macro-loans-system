package ru.cft.shift.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "loans")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "debt", precision = 10, scale = 2, nullable = false)
    private BigDecimal debt;

    @Column(name = "date_of_receive", nullable = false)
    private Instant dateOfReceive;

    @Column(name = "maturity", nullable = false)
    private Instant maturity;

    @Column(name = "interest_rate", precision = 2, scale = 2, nullable = false)
    @Size(min = 0, max = 100)
    private BigDecimal interestRate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


}
