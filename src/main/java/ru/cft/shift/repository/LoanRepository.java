package ru.cft.shift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.cft.shift.model.LoanEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long>{
    List<LoanEntity> findAllByUserId(Long id);

    Optional<LoanEntity> findByIdAndUserId(Long loanId, Long id);

    @Modifying
    @Query(value = "update loans set debt = (debt - ?3) where id = ?1 and user_id = ?2", nativeQuery = true)
    void setDebtSumByUserId(Long loanId, Long userId, BigDecimal sum);
}
