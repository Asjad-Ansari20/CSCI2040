import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SoccerTeamManager {
    private ArrayList<SoccerTeam> teams;
    private final String csvFilePath = "Teams (1).csv";

    public SoccerTeamManager() {
        teams = new ArrayList<>();
        loadTeamsFromCSV();
    }

    private void loadTeamsFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) {
                    String id = fields[0];
                    String name = fields[1];
                    String country = fields[2];
                    int players = Integer.parseInt(fields[3]);
                    teams.add(new SoccerTeam(id, name, country, players));
                }
            }
            System.out.println("Teams loaded successfully from " + csvFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found. Starting with an empty team list.");
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }
    }

    public void saveTeamsToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (SoccerTeam team : teams) {
                bw.write(team.getId() + "," + team.getName() + "," + team.getCountry() + "," + team.getPlayers());
                bw.newLine();
            }
            System.out.println("Teams saved successfully to " + csvFilePath);
        } catch (IOException e) {
            System.out.println("Error writing to the CSV file: " + e.getMessage());
        }
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSoccer Teams:");
            for (SoccerTeam team : teams) {
                System.out.println(team.getId() + ": " + team.getName());
            }

            System.out.println("\nOptions:");
            System.out.println("1. View Team Details");
            System.out.println("2. Add Team");
            System.out.println("3. Edit Team");
            System.out.println("4. Save and Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewTeamDetails(scanner);
                    break;
                case 2:
                    addTeam(scanner);
                    break;
                case 3:
                    editTeam(scanner);
                    break;
                case 4:
                    saveTeamsToCSV();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewTeamDetails(Scanner scanner) {
        System.out.print("Enter team ID to view details: ");
        String id = scanner.nextLine();
        SoccerTeam team = findTeamById(id);
        if (team != null) {
            System.out.println("\n" + team);
        } else {
            System.out.println("Team not found.");
        }
    }

    private void addTeam(Scanner scanner) {
        System.out.print("Enter new team ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter team name: ");
        String name = scanner.nextLine();
        System.out.print("Enter country name: ");
        String country = scanner.nextLine();
        System.out.print("Enter number of players: ");
        int players = scanner.nextInt();
        scanner.nextLine();

        if (id.isEmpty() || name.isEmpty() || country.isEmpty() || players <= 0) {
            System.out.println("All fields are required, and the number of players must be greater than 0.");
            return;
        }

        teams.add(new SoccerTeam(id, name, country, players));
        System.out.println("Team added successfully.");
    }

    private void editTeam(Scanner scanner) {
        System.out.print("Enter team ID to edit: ");
        String id = scanner.nextLine();
        SoccerTeam team = findTeamById(id);

        if (team != null) {
            System.out.print("Enter new name (current: " + team.getName() + "): ");
            String name = scanner.nextLine();
            System.out.print("Enter new country (current: " + team.getCountry() + "): ");
            String country = scanner.nextLine();
            System.out.print("Enter new number of players (current: " + team.getPlayers() + "): ");
            int players = scanner.nextInt();
            scanner.nextLine();

            if (!name.isEmpty()) team.setName(name);
            if (!country.isEmpty()) team.setCountry(country);
            if (players > 0) team.setPlayers(players);

            System.out.println("Team updated successfully.");
        } else {
            System.out.println("Team not found.");
        }
    }

    private SoccerTeam findTeamById(String id) {
        for (SoccerTeam team : teams) {
            if (team.getId().equals(id)) {
                return team;
            }
        }
        return null;
    }
}
