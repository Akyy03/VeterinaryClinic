package UIs;

import Models.PetsModels;
import Models.VetsModels;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VetsUI {

    private Map<Integer, VetsModels> vetMap = new TreeMap<>();
    private static int idCounter = 1;
    private Scanner scanner = new Scanner(System.in);
    private Scanner vetsUserInput = new Scanner(System.in);
    Menu menu = new Menu();

    public void VetsMenu() {
        int choice = -1;
        while (choice != 0) {

            System.out.println("\nVeterinarians Menu");
            System.out.println("Please choose an option to continue:\n");

            System.out.println("1. Show veterinarians");
            System.out.println("2. Add a new veterinarian");
            System.out.println("3. Update an existing veterinarian");
            System.out.println("4. Remove an existing veterinarian");
            System.out.println("0. Back");

            int choice1 = scanner.nextInt();
            if (choice1 == 1) {
                displayVets(vetMap);
            } else if (choice1 == 2) {
                addVet();
            } else if (choice1 == 3) {
                updateVet();
            } else if (choice1 == 4) {
                removeVet();
            } else if (choice1 == 0) {
                menu.start();
            }

            if (choice1 < 0 || choice1 > 4) {
                System.out.println("Please use a valid option (0 - 4)");
                VetsMenu();
            }
        }
    }

    public static void displayVets(Map<Integer, VetsModels> vetMap) {
        if (vetMap.isEmpty()) {
            System.out.println("No veterinarians found.");
        } else {
            System.out.println("\nAll veterinarians: ");
            for (VetsModels vet : vetMap.values()) {
                System.out.println("ID: " + vet.getId() + ", Name: " + vet.getFirstName() + " " + vet.getLastName() +
                        ", Age: " + vet.getAge() + " years " + "\nSpecialty field: " + vet.getField() + ", Work address: " +
                        vet.getWorkAddress());
            }
        }
    }

    public void addVet() {
        while (true) {
            System.out.println("Enter veterinarian's first name (or type 'quit' to stop adding employees): ");
            String firstName = vetsUserInput.nextLine();
            if (firstName.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Enter veterinarian's last name: ");
            String lastName = vetsUserInput.nextLine();
            System.out.println("Enter veterinarian's age: ");
            int age = vetsUserInput.nextInt();
            vetsUserInput.nextLine();
            System.out.println("Enter veterinarian's specialty field: ");
            String field = vetsUserInput.nextLine();
            System.out.println("Enter veterinarian's work address: ");
            String workAddress = vetsUserInput.nextLine();

            VetsModels vet = new VetsModels();
            vet.setId(idCounter++);
            vet.setFirstName(firstName);
            vet.setLastName(lastName);
            vet.setAge(age);
            vet.setField(field);
            vet.setWorkAddress(workAddress);
            vetMap.put(vet.getId(), vet);
        }
    }

    public void updateVet() {
        System.out.println("Enter the ID of the employee you want to update: ");
        int vetId = scanner.nextInt();
        scanner.nextLine();

        VetsModels vet = vetMap.get(vetId);
        if (vet == null) {
            System.out.println("Employee with ID " + vetId + " not found.");
            return;
        }

        System.out.println("Enter a new first name for the employee (or leave blank to keep current): ");
        String newFirstName = vetsUserInput.nextLine();
        if (!newFirstName.isEmpty()) {
            vet.setFirstName(newFirstName);
        }

        System.out.println("Enter a new last name for the employee (or leave blank to keep current): ");
        String newLastName = vetsUserInput.nextLine();
        if (!newLastName.isEmpty()) {
            vet.setLastName(newLastName);
        }

        System.out.println("Enter a new age for the employee (or leave blank to keep current): ");
        String newAge = vetsUserInput.nextLine();
        if (!newAge.isEmpty()) {
            vet.setAge(Integer.parseInt(newAge));
        }

        System.out.println("Enter a new specialty field for the employee (or leave blank to keep current): ");
        String newField = vetsUserInput.nextLine();
        if (!newField.isEmpty()) {
            vet.setField(newField);
        }

        System.out.println("Enter a new work address for the employee (or leave blank to keep current): ");
        String newWorkAddress = vetsUserInput.nextLine();
        if (!newWorkAddress.isEmpty()) {
            vet.setWorkAddress(newWorkAddress);
        }
        System.out.println("Employee data updated successfully.");
    }

    public void removeVet() {
        System.out.println("Enter the ID of the employee you want to remove: ");
        int vetId = scanner.nextInt();
        scanner.nextLine();

        VetsModels removedVet = vetMap.remove(vetId);
        if (removedVet == null) {
            System.out.println("Employee with ID " + vetId + " not found.");
        } else {
            System.out.println("Employee removed successfully.");
        }
    }
}
