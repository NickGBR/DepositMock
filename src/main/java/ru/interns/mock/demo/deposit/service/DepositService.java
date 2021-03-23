package ru.interns.mock.demo.deposit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.interns.mock.demo.deposit.dto.DepositDTO;
import ru.interns.mock.demo.deposit.enums.*;
import ru.interns.mock.demo.deposit.repository.DepositRepository;
import ru.interns.mock.demo.deposit.repository.dao.DepositDAO;

import java.util.*;

@Slf4j
@Component
public class DepositService {
    DepositRepository depositRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public List<DepositDTO> getDeposits(Long passportNumber, List<DepositError> errors) {
        final List<DepositDAO> deposits = depositRepository.getDepositDaoByPassportNumber(passportNumber);
        final List<DepositDTO> depositDTOS = new ArrayList<>();

        if (deposits != null) {
            for (DepositDAO deposit : deposits) {
                depositDTOS.add(DepositDTO.builder()
                        .depositNumber(getDepositNumberFromDeposit(deposit))
                        .depositAmount(deposit.getDepositAmount().toString())
                        .build());
            }
            return depositDTOS;
        }
        errors.add(DepositError.USER_DOESNT_HAVE_DEPOSITS);
        return new ArrayList<>();
    }

    private String getDepositNumberFromDeposit(DepositDAO deposit) {
        return deposit.getFirstOrderCode()
                + deposit.getSecondOrderCode()
                + deposit.getCurrencyCode()
                + deposit.getControlNumber()
                + deposit.getBankOfficeCode()
                + deposit.getClientCode();
    }

    public List<DepositError> openDeposit(Long passportNumber) {

        List<DepositError> errors = new ArrayList<>();

        DepositDAO depositDAO = new DepositDAO();
        depositDAO.setFirstOrderCode(FirstOrderCode.DEFAULT.getCode().toString());
        depositDAO.setSecondOrderCode(addZeroBeforeNumber(SecondOrderCode.IP.getCode(), 2));
        depositDAO.setCurrencyCode(addZeroBeforeNumber(CurrencyCode.RUR.getCode(), 3));
        depositDAO.setControlNumber("0");
        depositDAO.setBankOfficeCode(addZeroBeforeNumber(BankOfficeCode.INNO.getCode(), 4));
        depositDAO.setDepositAmount(0L);
        if (depositRepository.findTopByOrderByIdDesc() != null) {
            Integer lastUserId = depositRepository.findTopByOrderByIdDesc().getId();
            depositDAO.setClientCode(addZeroBeforeNumber(lastUserId + 1, 7));
        } else {
            depositDAO.setClientCode(addZeroBeforeNumber(1, 7));
        }
        depositDAO.setPassportNumber(passportNumber);
        depositRepository.save(depositDAO);

        log.info(getDepositNumberFromDeposit(depositDAO));
        return null;
    }

    /**
     * Добвляет неоходимое число нулей перед блоком номера счета.
     *
     * @param initialNumbers
     * @return
     */
    private String addZeroBeforeNumber(Integer currentNumber, Integer initialNumbers) {
        if (currentNumber.toString().length() != initialNumbers) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < initialNumbers - currentNumber.toString().length(); i++) {
                sb.append(0);
            }
            sb.append(currentNumber);
            return sb.toString();
        }
        return currentNumber.toString();
    }
}
