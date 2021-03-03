package ru.interns.mock.demo.deposit.enums;

public enum CurrencyCode {

    RUR(810),
    USD(840),
    EUR(978);

    private Integer code;


    CurrencyCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
