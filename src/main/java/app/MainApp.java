package app;

import model.Account;
import model.UserDetail;
import repository.AccountRepository;
import repository.UserDetailRepository;
import service.AccountService;
import service.UserDetailService;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Repositories en services aanmaken
        AccountRepository accountRepository = new AccountRepository();
        UserDetailRepository userDetailRepository = new UserDetailRepository();
        AccountService accountService = new AccountService(accountRepository);
        UserDetailService userDetailService = new UserDetailService(userDetailRepository);

        boolean running = true;

        while (running) {
            System.out.println("\n=== Hoofdmenu ===");
            System.out.println("1. Account beheren");
            System.out.println("2. Gebruikersdetails beheren");
            System.out.println("3. Stoppen");
            System.out.print("Kies een optie: ");
            int hoofdKeuze = Integer.parseInt(scanner.nextLine());

            switch (hoofdKeuze) {
                case 1 -> {
                    boolean accountMenu = true;
                    while (accountMenu) {
                        System.out.println("\n=== Accountbeheer Menu ===");
                        System.out.println("1. Nieuwe account toevoegen");
                        System.out.println("2. Alle accounts tonen");
                        System.out.println("3. Account opzoeken");
                        System.out.println("4. Account verwijderen");
                        System.out.println("5. Gegevens bijwerken");
                        System.out.println("6. Terug naar hoofdmenu");
                        System.out.print("Kies een optie: ");
                        int accountKeuze = Integer.parseInt(scanner.nextLine());

                        switch (accountKeuze) {
                            case 1 -> {
                                System.out.print("Gebruikersnaam: ");
                                String username = scanner.nextLine();
                                System.out.print("Wachtwoord: ");
                                String password = scanner.nextLine();

                                if (accountService.addAccount(username, password)) {
                                    System.out.println("✅ Account succesvol toegevoegd!");
                                } else {
                                    System.out.println("⚠️ Account bestaat al of fout bij toevoegen.");
                                }
                            }

                            case 2 -> {
                                List<Account> accounts = accountService.readAllAccounts();
                                System.out.println("\n--- Alle accounts ---");
                                accounts.forEach(System.out::println);
                            }

                            case 3 -> {
                                System.out.print("Voer gebruikersnaam in: ");
                                String username = scanner.nextLine();
                                Account account = accountService.readAccount(username);
                                if (account != null) {
                                    System.out.println("Gevonden: " + account);
                                } else {
                                    System.out.println("Account niet gevonden.");
                                }
                            }

                            case 4 -> {
                                System.out.println("⚠️ Let op: bij het verwijderen van een account worden ook gekoppelde gebruikersdetails verwijderd.");
                                System.out.print("Voer gebruikersnaam in om te verwijderen: ");
                                String username = scanner.nextLine();
                                if (accountService.delete(username)) {
                                    System.out.println("Account verwijderd.");
                                } else {
                                    System.out.println("Account niet gevonden.");
                                }
                            }

                            case 5 -> {
                                System.out.print("Huidige gebruikersnaam: ");
                                String oldUsername = scanner.nextLine();
                                System.out.print("Nieuwe gebruikersnaam: ");
                                String newUsername = scanner.nextLine();
                                System.out.print("Nieuw wachtwoord: ");
                                String newPassword = scanner.nextLine();

                                if (accountService.updateAccount(oldUsername, newUsername, newPassword)) {
                                    System.out.println("Gebruikersnaam en wachtwoord succesvol bijgewerkt!");
                                } else {
                                    System.out.println("Account niet gevonden.");
                                }
                            }

                            case 6 -> accountMenu = false;

                            default -> System.out.println("Ongeldige keuze, probeer opnieuw.");
                        }
                    }
                }

                case 2 -> {
                    boolean userDetailMenu = true;
                    while (userDetailMenu) {
                        System.out.println("\n--- Gebruikersdetails Menu ---");
                        System.out.println("1. Gebruikersdetail toevoegen");
                        System.out.println("2. Alle gebruikersdetails tonen");
                        System.out.println("3. Gebruikersdetail opzoeken");
                        System.out.println("4. Gegevens bijwerken");
                        System.out.println("5. Gebruikersdetail verwijderen");
                        System.out.println("6. Terug naar hoofdmenu");

                        System.out.print("Kies een optie: ");
                        int detailKeuze = Integer.parseInt(scanner.nextLine());

                        switch (detailKeuze) {
                            case 1 -> {
                                System.out.print("Gebruikersnaam (moet bestaan): ");
                                String username = scanner.nextLine();
                                System.out.print("Voornaam: ");
                                String firstName = scanner.nextLine();
                                System.out.print("Achternaam: ");
                                String lastName = scanner.nextLine();
                                System.out.print("E-mail: ");
                                String email = scanner.nextLine();

                                UserDetail user = new UserDetail(lastName, firstName, email, new Account(username, ""));
                                if (userDetailService.createUserDetail(user)) {
                                    System.out.println("✅ Gebruikersdetail succesvol toegevoegd!");
                                } else {
                                    System.out.println("⚠️ Fout bij toevoegen van gebruikersdetail (controleer of account bestaat).");
                                }
                            }

                            case 2 -> {
                                List<UserDetail> details = userDetailService.readAllUserDetails();
                                System.out.println("\n--- Alle gebruikersdetails ---");
                                details.forEach(System.out::println);
                            }

                            case 3 -> {
                                System.out.print("Voer gebruikersnaam in: ");
                                String username = scanner.nextLine();
                                UserDetail detail = userDetailService.readUserDetail(username);
                                if (detail != null) {
                                    System.out.println("Gevonden: " + detail);
                                } else {
                                    System.out.println("Geen gebruikersdetail gevonden.");
                                }
                            }

                            case 4 -> {
                                System.out.print("Gebruikersnaam: ");
                                String username = scanner.nextLine();
                                System.out.print("Nieuwe voornaam: ");
                                String newFirstName = scanner.nextLine();
                                System.out.print("Nieuwe achternaam: ");
                                String newLastName = scanner.nextLine();
                                System.out.print("Nieuwe e-mail: ");
                                String newEmail = scanner.nextLine();

                                if (userDetailService.updateUserDetail(username, newFirstName, newLastName, newEmail)) {
                                    System.out.println("✅ Gebruikersdetail succesvol bijgewerkt!");
                                } else {
                                    System.out.println("⚠️ Bijwerken mislukt. Controleer of de gebruikersnaam bestaat.");
                                }
                            }
                            case 5 -> {
                                System.out.print("Gebruikersnaam om te verwijderen: ");
                                String username = scanner.nextLine();
                                if (userDetailService.deleteUserDetail(username)) {
                                    System.out.println("Gebruikersdetail succesvol verwijderd.");
                                } else {
                                    System.out.println("Verwijderen mislukt. Controleer of de gebruikersnaam bestaat.");
                                }
                            }
                            case 6 -> userDetailMenu = false;

                            default -> System.out.println("Ongeldige keuze, probeer opnieuw.");
                        }
                    }
                }

                case 3 -> {
                    System.out.println("Programma afgesloten. 👋");
                    running = false;
                }

                default -> System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }

        scanner.close();
    }
}
