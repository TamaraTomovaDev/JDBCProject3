package repository;

import config.MySqlConfig;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    // === CREATE ===
    // Een nieuw account toevoegen
    public boolean addAccount(Account account) {
        // Controleer of het account al bestaat
        if (getAccount(account.getUsername()) != null) {
            System.out.println("Account bestaat al.");
            return false;
        }

        String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
        try (Connection connection = MySqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // De placeholders invullen met de waarden van het account
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());

            // De INSERT-query uitvoeren en controleren of er rijen zijn toegevoegd
            int rows = statement.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === READ (een account) ===
    // Een account uit de database ophalen
    public Account getAccount(String username) {
        String sql = "SELECT * FROM account WHERE username = ?";

        try (Connection connection = MySqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // De gebruikersnaam instellen als parameter in de SELECT-query
            statement.setString(1, username);

            // De query uitvoeren en het resultaat verwerken
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Als er een resultaat is, haal het wachtwoord op en maak een Account-objec
                String password = resultSet.getString("password");
                return new Account(username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Als het account niet bestaat, geef null terug
        return null;
    }

    // === READ (alle accounts) ===
    // Alle accounts uit de database ophalen
    public List<Account> getAllAccounts() {
        String sql = "SELECT * FROM account";
        List<Account> accounts = new ArrayList<>();

        try(Connection connection = MySqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            // Door alle rijjen lopen en Account-object maken
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                accounts.add(new Account(username, password));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }

    // === UPDATE ===
    // Het wachtwoord van een account bijwerken
    public boolean updatePassword(String username, String newPassword) {
        // Controleer eerst of het account bestaat
        if (getAccount(username) == null) {
            System.out.println("Account niet gevonden.");
            return false;
        }

        String sql = "UPDATE account SET password = ? WHERE username = ?";
        try (Connection connection = MySqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // De niuewe waarden instellen in query
            statement.setString(1, newPassword);
            statement.setString(2, username);

            // De UPDATE-query uitvoeren en controleren of er rijen zijn aangepast
            int rows = statement.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === DELETE ===
    // Een account uit de database verwijderen
    public boolean deleteAccount(String username) {
        // Controleer eerst of het account bestaat
        if(getAccount(username) == null) {
            System.out.println("Account niet gevonden.");
            return false;
        }

        String sql = "DELETE FROM account WHERE username = ?";
        try(Connection connection = MySqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            // De gebruikersnaam instellen als parameter in de DELETE-query
            statement.setString(1,username);

            // De DELETE-query uitvoeren en controleren of er rijen zijn verwijderd
            int  rows = statement.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            // Als het account niet succesvol is verwijderd, geef false terug
            return false;
        }
    }
}
