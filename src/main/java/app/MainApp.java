package app;

import model.Account;
import model.UserDetail;
import repository.AccountRepository;
import repository.UserDetailRepository;

public class MainApp {
    public static void main(String[] args) {
        Account a = new Account("jan", "1234");
        System.out.println(a);

        new AccountRepository().addAccount(new Account("piet", "geheim"));

    }
}
