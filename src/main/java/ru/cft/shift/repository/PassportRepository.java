package ru.cft.shift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.shift.entity.PassportEntity;

public interface PassportRepository extends JpaRepository<PassportEntity, Long> {

}
