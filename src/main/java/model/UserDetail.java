package model;

import java.util.Objects;

public class UserDetail {
    private String lastName;
    private String firstName;
    private String email;
    private Account account;

    public UserDetail() {
    }

    public UserDetail(String lastName, String firstName, String email, Account account) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.account = account;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserDetail that)) return false;
        return Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(email, that.email) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, email, account);
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", account=" + account +
                '}';
    }
}
