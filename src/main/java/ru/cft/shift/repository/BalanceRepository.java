package ru.cft.shift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.shift.entity.BalanceEntity;

public interface BalanceRepository extends JpaRepository<BalanceEntity, Long> {

}
