package service;

import model.Account;
import model.UserDetail;
import repository.AccountRepository;
import repository.UserDetailRepository;

import java.util.List;

public class UserDetailService {
    private final UserDetailRepository userRepo;
    private final AccountRepository accountRepo;

    // === CONSTRUCTOR ===
    public UserDetailService(UserDetailRepository userRepo, AccountRepository accountRepo) {
        this.userRepo = userRepo;
        this.accountRepo = accountRepo;
    }

    // === CREATE ===
    // Een nieuwe gebruiker + account aanmaken
    public boolean registerUser(String username, String password, String firstName, String lastName, String email) {
        Account existingAccount = accountRepo.getAccount(username);

        if (existingAccount != null) {
            // Account bestaat al → controleer wachtwoord
            if (!existingAccount.getPassword().equals(password)) {
                System.out.println("Fout: het wachtwoord is incorrect voor dit bestaande account.");
                return false;
            }
        } else {
            // Account bestaat nog niet → maak nieuw account aan
            boolean accountCreated = accountRepo.addAccount(new Account(username, password));
            if (!accountCreated) {
                System.out.println("Fout bij het aanmaken van het account.");
                return false;
            }
        }

        // Maak en sla de gebruikersdetails op
        UserDetail user = new UserDetail(lastName, firstName, email, new Account(username, password));
        return userRepo.addUserDetail(user);
    }

    // === Overbelaste methode ===
    public boolean addUserDetail(String lastName, String firstName, String email, String accountUsername) {
        Account account = new Account(accountUsername, null);
        UserDetail user = new UserDetail(lastName, firstName, email, account);
        return userRepo.addUserDetail(user);
    }

    // === READ ===
    public UserDetail readUserDetail(String accountUsername) {
        return userRepo.getUserDetail(accountUsername);
    }

    public List<UserDetail> readAllUserDetails() {
        return userRepo.getAllUserDetails();
    }

    // === UPDATE ===
    public boolean updateEmail(String accountUsername, String newEmail) {
        return userRepo.updateEmail(accountUsername, newEmail);
    }

    // === DELETE ===
    public boolean deleteUserDetail(String accountUsername) {
        return userRepo.deleteUserDetail(accountUsername);
    }
}