
package service;

import model.Account;
import repository.AccountRepository;

import java.util.List;

public class AccountService {
    private final AccountRepository repo;

    // === CONSTRUCTOR ===
    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    // === CREATE ===
    // Een nieuw account toevoegen
    public boolean createAccount(Account account) {
        return repo.addAccount(account);
    }

    // Een account toevoegen via username en password
    public boolean addAccount(String username, String password) {
        return repo.addAccount(new Account(username, password));
    }

    // === READ ==
    // Een account ophalen
    public Account readAccount(String username) {
        return repo.getAccount(username);
    }

    // Alle accounts ophalen
    public List<Account> readAllAccounts() {
        return repo.getAllAccounts();
    }

    // === UPDATE ==
    // Het wachtwoord van een account bijwerken
    public boolean updatePassword(String username, String newPassword) {
        return repo.updatePassword(username, newPassword);
    }

    // === DELETE ===
    // Een account verwijderen
    public boolean delete(String username) {
        return repo.deleteAccount(username);
    }
}
