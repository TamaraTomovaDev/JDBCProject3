package service;

import model.Account;
import model.UserDetail;
import repository.AccountRepository;
import repository.UserDetailRepository;

import java.util.List;

public class UserDetailService {
    private final UserDetailRepository repo;

    // === CONSTRUCTOR ===
    public UserDetailService(UserDetailRepository repo) {
        this.repo = repo;
    }

    // === CREATE ===
    // Een nieuwe gebruiker toevoegen
    public boolean createUserDetail(UserDetail userDetail) {
        return repo.addUserDetail(userDetail);
    }

    // === READ ===
    // Een gebruiker ophalen
    public UserDetail readUserDetail(String accountUsername) {
        return repo.getUserDetail(accountUsername);
    }

    // Alle gebruikers ophalen
    public List<UserDetail> readAllUserDetails() {
        return repo.getAllUserDetails();
    }

    // === UPDATE ===
    // Het e-mailadres van een gebruiker bijwerken
    public boolean updateEmail(String accountUsername, String newEmail) {
        return repo.updateEmail(accountUsername, newEmail);
    }

    // === DELETE ===
    // Een gebruiker verwijderen
    public boolean deleteUserDetail(String accountUsername) {
        return repo.deleteUserDetail(accountUsername);
    }
}
