package ru.interns.mock.demo.deposit.repository;

import org.springframework.data.repository.CrudRepository;
import ru.interns.mock.demo.deposit.repository.dao.DepositDAO;
import java.util.*;

public interface DepositRepository extends CrudRepository<DepositDAO, Long>{
    List<DepositDAO> getDepositDaoByPassportNumber(Long passportNumber);

    DepositDAO findTopByOrderByIdDesc();
}
