package UIs;

import Models.AppointmentsModels;
import Models.PetsModels;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AppointmentsUI {

    private Map<Integer, AppointmentsModels> appMap = new TreeMap<>();
    private static int idCounter = 1;
    private Scanner scanner = new Scanner(System.in);
    private Scanner userInput = new Scanner(System.in);
    Menu menu = new Menu();

    public void AppointmentsMenu() {
        int choice = -1;
        while (choice != 0) {

            System.out.println("\nAppointments Menu");
            System.out.println("Please choose an option to continue:\n");

            System.out.println("1. Show appointments");
            System.out.println("2. Add a new appointment");
            System.out.println("3. Update an existing appointment");
            System.out.println("4. Remove an existing appointment");
            System.out.println("0. Back");

            int choice1 = scanner.nextInt();
            if (choice1 == 1) {
                appointments(appMap);
            } else if (choice1 == 2) {
                addAppointment();
            } else if (choice1 == 3) {
                updateAppointment();
            } else if (choice1 == 4) {
                removeAppointment();
            } else if (choice1 == 0) {
                menu.start();
            }

            if (choice1 < 0 || choice1 > 4) {
                System.out.println("Please use a valid option (0 - 4)");
                AppointmentsMenu();
            }
        }
    }

    public static void appointments(Map<Integer, AppointmentsModels> appMap) {
        if (appMap.isEmpty()) {
            System.out.println("No appointments for now.");
        } else {
            System.out.println("\nAll appointments: ");
            for (AppointmentsModels appointments : appMap.values()) {
                System.out.println("ID: " + appointments.getId() +
                        "\nVeterinarian's Name: " + appointments.getVetName() +
                        "\nPet's Name: " + appointments.getPetName() +
                        "\nDate and Time: " + appointments.formattedAppHour() +
                        "\nDescription: " + appointments.getDescription());
            }
        }
    }

    public void addAppointment() {
        while (true) {
            System.out.println("Enter veterinarian's name (or type 'quit' to cancel): ");
            String vetName = userInput.nextLine();
            if (vetName.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Enter pet's name: ");
            String petName = userInput.nextLine();

            System.out.println("Enter the date and time (YYYY-MM-DD HH:MM): ");
            String dateAndTime = userInput.nextLine();
            LocalDateTime appointmentDateAndTime = LocalDateTime.parse(dateAndTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));


            System.out.println("Enter a description for the appointment: ");
            String description = userInput.nextLine();

            AppointmentsModels appointment = new AppointmentsModels();
            appointment.setId(idCounter++);
            appointment.setVetName(vetName);
            appointment.setPetName(petName);
            appointment.setAppHour(appointmentDateAndTime);
            appointment.setDescription(description);
            appMap.put(appointment.getId(), appointment);
        }
    }

    public void updateAppointment() {
        System.out.println("Enter the ID of the appointment you want to update: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();

        AppointmentsModels appointments = appMap.get(appointmentId);
        if (appointments == null) {
            System.out.println("Appointment with ID " + appointmentId + " not found.");
            return;
        }

        System.out.println("Enter the new veterinarian's name (or leave blank to keep current): ");
        String newVetName = userInput.nextLine();
        if (!newVetName.isEmpty()) {
            appointments.setVetName(newVetName);
        }

        System.out.println("Enter the new pet's name (or leave blank to keep current): ");
        String newPetName = userInput.nextLine();
        if (!newPetName.isEmpty()) {
            appointments.setPetName(newPetName);
        }

        System.out.println("Enter the new date and time (YYYY-MM-DD HH:MM) (or leave blank to keep current): ");
        String newDateTime = userInput.nextLine();
        if (!newDateTime.isBlank()) {
            LocalDateTime newAppointmentDateTime = LocalDateTime.parse(newDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            appointments.setAppHour(newAppointmentDateTime);
        }

        System.out.println("Enter a new description (or leave blank to keep current): ");
        String newDescription = userInput.nextLine();
        if (!newDescription.isEmpty()) {
            appointments.setDescription(newDescription);
        }
        System.out.println("Appointment updated successfully.");

    }

    public void removeAppointment() {
        System.out.println("Enter the ID of the appointment you want to remove: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();

        AppointmentsModels removedAppointment = appMap.remove(appointmentId);
        if (removedAppointment == null) {
            System.out.println("Appointment with ID " + appointmentId + " not found.");
        } else {
            System.out.println("Appointment removed successfully.");
        }
    }
}

