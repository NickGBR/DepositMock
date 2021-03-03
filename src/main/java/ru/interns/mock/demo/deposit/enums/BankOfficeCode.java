package ru.interns.mock.demo.deposit.enums;

public enum BankOfficeCode {

    MSK(3333),
    SPB(4456),
    INNO(5456);

    private Integer code;


    BankOfficeCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
