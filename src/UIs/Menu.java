package UIs;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public void start() {

        int choice = -1;
        while (choice != 0) {

            System.out.println("\nWelcome to your veterinary clinic!");
            System.out.println("Please choose an option to continue:\n");

            System.out.println("1. Pets Menu");
            System.out.println("2. Veterinarians Menu");
            System.out.println("3. Appointments Menu");
            System.out.println("0. Close");
            try {
                PetsUI petsUI = new PetsUI();
                VetsUI vetsUI = new VetsUI();
                AppointmentsUI appointmentsUI = new AppointmentsUI();

                Scanner scanner = new Scanner(System.in);
                int choice2 = scanner.nextInt();

                if (choice2 == 1) {
                    petsUI.PetsMenu();
                } else if (choice2 == 2) {
                    vetsUI.VetsMenu();
                } else if (choice2 == 3) {
                    appointmentsUI.AppointmentsMenu();
                } else if (choice2 == 0) {
                    System.exit(0);
                }

                if (choice2 < 0 || choice2 > 3) {
                    System.out.println("Please use a valid option (0 - 3)");
                    start();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option");
                start();
            }
        }
    }
}
