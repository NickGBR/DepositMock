package ru.interns.mock.demo.deposit.dto;

import lombok.Builder;
import lombok.Data;
import ru.interns.mock.demo.deposit.enums.DepositError;
import ru.interns.mock.demo.deposit.enums.DepositStatus;
import java.util.*;

@Data
@Builder
public class ResponseDTO {
    DepositStatus status;
    List<DepositError> errors;
}
