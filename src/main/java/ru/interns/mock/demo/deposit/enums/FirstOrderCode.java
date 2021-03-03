package ru.interns.mock.demo.deposit.enums;

public enum FirstOrderCode {

    DEFAULT(408);

    private Integer code;


    FirstOrderCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
