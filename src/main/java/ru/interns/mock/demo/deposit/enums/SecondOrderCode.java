package ru.interns.mock.demo.deposit.enums;

public enum SecondOrderCode {

    IP(2),
    FL(22);

    private Integer code;


    SecondOrderCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
