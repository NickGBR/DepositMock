package ru.interns.mock.demo.deposit.enums;

public enum DepositError {
    JSON_PARSE_ERROR("Ошбка при чтении JSON"),
    USER_DOESNT_HAVE_DEPOSITS("Пользователь не имеет открытих счетов.");

    private final String errorText;

    DepositError(String errorText) {
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }
}
