package ru.interns.mock.demo.mvd.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "terrorists")
public class Terrorists {

    @Id
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "passport_number")
    private Long passportNumber;

    @Column(name = "date_of_birthday")
    private Date dateOfBirthday;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private boolean status;
}