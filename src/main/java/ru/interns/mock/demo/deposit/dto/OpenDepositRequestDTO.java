package ru.interns.mock.demo.deposit.dto;


import lombok.Builder;
import lombok.Data;
import ru.interns.mock.demo.deposit.enums.DepositError;

import java.util.*;

@Builder
@Data
public class OpenDepositRequestDTO {
    Long passportNumber;
    List<DepositError> errors;
}
