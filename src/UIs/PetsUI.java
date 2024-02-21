package UIs;

import Models.PetsModels;

import java.time.LocalDate;
import java.util.*;

public class PetsUI {

    private Map<Integer, PetsModels> petMap = new TreeMap<>();
    private static int idCounter = 1;
    private Scanner scanner = new Scanner(System.in);
    private Scanner petsUserInput = new Scanner(System.in);
    Menu menu = new Menu();

    public void PetsMenu() {

        int choice = -1;
        while (choice != 0) {

            System.out.println("\nPets Menu");
            System.out.println("Please choose an option to continue:\n");

            System.out.println("1. Show pets");
            System.out.println("2. Add a new pet");
            System.out.println("3. Update an existing pet");
            System.out.println("4. Remove an existing pet");
            System.out.println("0. Back");

            int choice1 = scanner.nextInt();
            if (choice1 == 1) {
                displayPets(petMap);
            } else if (choice1 == 2) {
                addPet();
            } else if (choice1 == 3) {
                updatePet();
            } else if (choice1 == 4) {
                removePet();
            } else if (choice1 == 0) {
                menu.start();
            }

            if (choice1 < 0 || choice1 > 4) {
                System.out.println("Please use a valid option (0 - 4)");
                PetsMenu();
            }
        }
    }

    public static void displayPets(Map<Integer, PetsModels> petMap) {
        if (petMap.isEmpty()) {
            System.out.println("No pets found.");
        } else {
            System.out.println("\nAll pets: ");
            for (PetsModels pet : petMap.values()) {
                System.out.println("ID: " + pet.getId() + ", Name: " + pet.getName() + ", Animal breed: " + pet.getAnimal() + ", Birth Date: " + pet.getBirth());
            }
        }
    }

    public void addPet(){
        while (true) {
            System.out.println("Enter pet's name (or type 'quit' to stop adding pets): ");
            String name = petsUserInput.nextLine();
            if (name.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Enter what animal the pet is: ");
            String animal = petsUserInput.nextLine();
            System.out.println("Enter pet's birthdate (YYYY-MM-DD): ");
            String birth = petsUserInput.nextLine();
            LocalDate birthDate = LocalDate.parse(birth);

            PetsModels pet = new PetsModels();
            pet.setId(idCounter++);
            pet.setName(name);
            pet.setAnimal(animal);
            pet.setBirth(birthDate);
            petMap.put(pet.getId(), pet);
        }
    }

    public void updatePet() {
        System.out.println("Enter the ID of the pet you want to update: ");
        int petId = scanner.nextInt();
        scanner.nextLine();

        PetsModels pet = petMap.get(petId);
        if (pet == null) {
            System.out.println("Pet with ID " + petId + " not found.");
            return;
        }

        System.out.println("Enter new name for the pet (or leave blank to keep current): ");
        String newName = petsUserInput.nextLine();
        if (!newName.isEmpty()) {
            pet.setName(newName);
        }

        System.out.println("Enter new animal breed for the pet (or leave blank to keep current breed): ");
        String newAnimal = petsUserInput.nextLine();
        if (!newAnimal.isEmpty()) {
            pet.setAnimal(newAnimal);
        }

        System.out.println("Enter new birth date for the pet (YYYY-MM-DD) (or leave blank to keep current): ");
        String newBirth = petsUserInput.nextLine();
        if (!newBirth.isEmpty()) {
            LocalDate newBirthDate = LocalDate.parse(newBirth);
            pet.setBirth(newBirthDate);
        }

        System.out.println("Pet updated successfully.");

    }

    public void removePet() {
        System.out.println("Enter the ID of the pet you want to remove: ");
        int petId = scanner.nextInt();
        scanner.nextLine();

        PetsModels removedPet = petMap.remove(petId);
        if (removedPet == null) {
            System.out.println("Pet with ID " + petId + " not found.");
        } else {
            System.out.println("Pet removed successfully.");
        }
    }
}
