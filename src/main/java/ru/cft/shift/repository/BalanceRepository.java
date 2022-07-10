package ru.cft.shift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.shift.entity.BalanceEntity;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<BalanceEntity, Long> {
    Optional<BalanceEntity> findByUserEmail(String email);
}
