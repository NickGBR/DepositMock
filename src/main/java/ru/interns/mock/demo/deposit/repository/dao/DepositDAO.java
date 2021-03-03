package ru.interns.mock.demo.deposit.repository.dao;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "deposit")
public class DepositDAO {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "deposit_amount")
    private Long depositAmount;

    @Column(name = "first_order_code")
    private String firstOrderCode;

    @Column(name = "second_order_code")
    private String secondOrderCode;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "control_number")
    private String controlNumber;

    @Column (name = "bank_office_code")
    private String bankOfficeCode;

    @Column(name = "client_number")
    private String clientCode;

    @Column(name = "passport_number")
    private Long passportNumber;
}
