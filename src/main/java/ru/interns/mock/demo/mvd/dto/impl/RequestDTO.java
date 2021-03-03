package ru.interns.mock.demo.mvd.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    private String name;
    private String surname;
    private Long passportNumber;
    private Long dateOfBirthday;
    private Long kladrAddress;
    private int checkTypeCode;

    @Override
    public String toString() {
        return "MvdRequestDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", passportNumber=" + passportNumber +
                ", dateOfBirthday=" + dateOfBirthday +
                ", kladrAddress=" + kladrAddress +
                '}';
    }
}