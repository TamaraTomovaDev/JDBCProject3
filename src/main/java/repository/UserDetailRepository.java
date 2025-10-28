package repository;

import config.MySqlConfig;
import model.Account;
import model.UserDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDetailRepository {
    // === CREATE ===
    // Een nieuwe gebruiker toevoegen
    public boolean addUserDetail(UserDetail userDetail) {
        String sql = "INSERT INTO userdetail (firstName, lastName, email, accountUsername) VALUES (?, ?, ?, ?)";

        try(Connection connection = MySqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            // De placeholders invullen met de waarden van de gebruiker
            statement.setString(1, userDetail.getFirstName());
            statement.setString(2, userDetail.getLastName());
            statement.setString(3, userDetail.getEmail());
            statement.setString(4, userDetail.getAccount().getUsername());

            // De INSERT-query uitvoeren en controleren of er rijen zijn toegevoegd
            int rows = statement.executeUpdate();
            return rows > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // === READ (één gebruiker) ===
    // Een gebruiker ophalen op basis van gebruikersnaam
    public UserDetail getUserDetail(String accountUsername) {
        String sql = "SELECT * FROM userdetail WHERE accountUsername = ?";

        try (Connection connection = MySqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // De gebruikersnaam instellen als parameter in de SELECT-query
            statement.setString(1, accountUsername);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Als er een resultaat is, haal de gegevens op en maak een UserDetail-object
                String lastName = resultSet.getString("lastname");
                String firstName = resultSet.getString("firstname");
                String email = resultSet.getString("email");
                Account account = new Account(accountUsername, null);
                return new UserDetail(lastName, firstName, email, account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Als de gebruiker niet bestaat, geef null terug
        return null;
    }

    // === READ (alle gebruikers) ===
    // Alle gebruikers ophalen
    public List<UserDetail> getAllUserDetails() {
        String sql = "SELECT * FROM userdetail";
        List<UserDetail> users = new ArrayList<>();

        try (Connection connection = MySqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            // Alle rijen doorlopen en UserDetail-objecten maken
            while (resultSet.next()) {
                String lastName = resultSet.getString("lastname");
                String firstName = resultSet.getString("firstname");
                String email = resultSet.getString("email");
                String accountUsername = resultSet.getString("accountUsername");
                Account account = new Account(accountUsername, null);
                users.add(new UserDetail(lastName, firstName, email, account));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    // === UPDATE ===
    // Het e-mailadres van een gebruiker bijwerken
    public boolean updateUserDetail(String accountUsername, String newFirstName, String newLastName, String newEmail) {
        // Controleer eerst of de gebruiker bestaat
        if (getUserDetail(accountUsername) == null) {
            System.out.println("Gebruiker niet gevonden.");
            return false;
        }

        String sql = "UPDATE userdetail SET firstname = ?, lastname = ?, email = ? WHERE accountUsername = ?";

        try (Connection connection = MySqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // De nieuwe waarden instellen in de query
            statement.setString(1, newFirstName);
            statement.setString(2, newLastName);
            statement.setString(3, newEmail);
            statement.setString(4, accountUsername);

            // De UPDATE-query uitvoeren en controleren of er rijen zijn aangepast
            int rows = statement.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === DELETE ===
    // Een gebruiker verwijderen
    public boolean deleteUserDetail(String accountUsername) {
        if (getUserDetail(accountUsername) == null) {
            System.out.println("Gebruiker niet gevonden.");
            return false;
        }

        String sql = "DELETE FROM userdetail WHERE accountUsername = ?";

        try (Connection connection = MySqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // De gebruikersnaam instellen als parameter in de DELETE-query
            statement.setString(1, accountUsername);

            // De DELETE-query uitvoeren en controleren of er rijen zijn verwijderd
            int rows = statement.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}