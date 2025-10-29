## Reflectievragen

### 1. Waarom gebruiken we PreparedStatement in plaats van Statement?
PreparedStatement is veiliger en efficiënter dan Statement omdat:
- Het voorkomt **SQL-injecties** door gebruik van placeholders (`?`).
- Het biedt **betere prestaties** bij herhaalde queries (precompiled).
- Het zorgt voor **automatische type-conversie** via `setString()`, `setInt()`, enz.
- Het maakt code **leesbaarder en onderhoudbaarder**.
- Het behandelt **speciale tekens** veilig, waardoor fouten en beveiligingslekken worden voorkomen.

### 2. Wat is het voordeel van de scheiding tussen repository, service en app?
- **Repository**: verantwoordelijk voor database-interacties.
- **Service**: bevat businesslogica en validatie.
- **App**: regelt gebruikersinteractie.
  Deze scheiding maakt de code **modulair, testbaar en onderhoudbaar**.

### 3. Hoe zou je wachtwoorden kunnen versleutelen voordat ze in de database komen?
Door **hashing** toe te passen met bijvoorbeeld `BCrypt`. Zo worden wachtwoorden niet in platte tekst opgeslagen.

### 4. Wat gebeurt er als de database offline is? Hoe kun je dit opvangen?
De applicatie krijgt een `SQLException`. Dit kun je opvangen met:
- **try-catch** blokken.
- Een duidelijke foutmelding tonen.
- Eventueel **retry-logica** of een fallback-mechanisme implementeren.