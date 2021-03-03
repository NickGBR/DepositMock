package ru.interns.mock.demo.deposit.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepositDTO {
    private String depositNumber;
    private String depositAmount;

}
