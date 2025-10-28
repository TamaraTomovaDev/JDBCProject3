package app;

import model.Account;
import model.UserDetail;
import repository.AccountRepository;
import repository.UserDetailRepository;
import service.AccountService;
import service.UserDetailService;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // Repositories en services aanmaken
        AccountRepository accountRepository = new AccountRepository();
        UserDetailRepository userDetailRepository = new UserDetailRepository();
        AccountService accountService = new AccountService(accountRepository);
        UserDetailService userDetailService = new UserDetailService(userDetailRepository);

        // === ACCOUNT CRUD ===
        System.out.println("=== ACCOUNT TEST ===");
        accountService.createAccount(new Account("jan", "1234"));
        accountService.createAccount(new Account("piet", "geheim"));

        Account jan = accountService.readAccount("jan");
        System.out.println("Gelezen account: " + jan.getUsername());

        accountService.updatePassword("jan", "nieuw1234");
        accountService.delete("piet");


// === USERDETAIL CRUD ===
        System.out.println("\n=== USERDETAIL TEST ===");
        UserDetail janDetail = new UserDetail("Jansen", "Jan", "jan@example.com", jan);
        userDetailService.createUserDetail(janDetail);

        UserDetail gelezen = userDetailService.readUserDetail("jan");
        System.out.println("Gelezen gebruiker: " + gelezen.getFirstName() + " " + gelezen.getLastName());

        userDetailService.updateEmail("jan", "jan.nieuw@example.com");

        List<UserDetail> alleGebruikers = userDetailService.readAllUserDetails();
        System.out.println("Aantal gebruikers in DB: " + alleGebruikers.size());

        userDetailService.deleteUserDetail("jan");
    }
}

