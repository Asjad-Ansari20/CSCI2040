import java.io.*;
import java.util.*;

public class ReadCSV {

    public static void main(String[] args) throws Exception {
        String filePath = "Teams.csv";
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

           // Read and process each line into the data list
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Display Items");
            System.out.println("2. Edit Item");
            System.out.println("3. Add New Item");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    for (String[] row : data) {
                        System.out.println(String.join("\t", row));
                    }
                    break;

                case 2:
                    System.out.print("Enter ID of the item to edit: ");
                    String editId = scanner.nextLine();

                    boolean found = false;
                    for (String[] row : data) {
                        if (row[0].equals(editId)) {
                            found = true;
                            System.out.print("Enter new Name (current: " + row[1] + "): ");
                            String newName = scanner.nextLine();
                            if (!newName.isEmpty()) {
                                row[1] = newName;
                            }

                            System.out.print("Enter new Description (current: " + row[2] + "): ");
                            String newDescription = scanner.nextLine();
                            if (!newDescription.isEmpty()) {
                                row[2] = newDescription;
                            }

                            System.out.println("Item updated.");
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Item with ID " + editId + " not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter new ID: ");
                    String newId = scanner.nextLine();
                    while (newId.isEmpty()) {
                        System.out.print("ID cannot be empty. Enter new ID: ");
                        newId = scanner.nextLine();
                    }

                    System.out.print("Enter Name: ");
                    String newName = scanner.nextLine();
                    while (newName.isEmpty()) {
                        System.out.print("Name cannot be empty. Enter Name: ");
                        newName = scanner.nextLine();
                    }

                    System.out.print("Enter Description: ");
                    String newDescription = scanner.nextLine();
                    while (newDescription.isEmpty()) {
                        System.out.print("Description cannot be empty. Enter Description: ");
                        newDescription = scanner.nextLine();
                    }

                    data.add(new String[]{newId, newName, newDescription});
                    System.out.println("New item added.");
                    break;

                case 4:

                    System.out.println("Exiting program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
