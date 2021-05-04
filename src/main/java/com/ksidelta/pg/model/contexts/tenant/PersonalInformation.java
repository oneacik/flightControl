package com.ksidelta.pg.model.contexts.tenant;

import java.time.Instant;

public class PersonalInformation {
    private String firstName;
    private String secondName;
    private Instant dateOfBirth;

    private PersonalInformation(String firstName, String secondName, Instant dateOfBirth) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    static public PersonalInformation createPersonalInformation(String firstName, String secondName, Instant dateOfBirth) {
        assertPositiveDateOfBirth(dateOfBirth);

        return new PersonalInformation(firstName, secondName, dateOfBirth);
    }

    static void assertPositiveDateOfBirth(Instant dateOfBirth) {
        if (dateOfBirth.isAfter(Instant.now())) {
            throw new IllegalArgumentException("Date of birth is in the future");
        }
    }
}
