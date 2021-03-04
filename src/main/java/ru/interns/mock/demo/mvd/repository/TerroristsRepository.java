package ru.interns.mock.demo.mvd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.interns.mock.demo.mvd.dao.Terrorists;

@Repository
public interface TerroristsRepository extends JpaRepository<Terrorists, Long> {
    Terrorists findByPassportNumber(Long passportNumber);
}