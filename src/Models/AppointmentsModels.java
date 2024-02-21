package Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentsModels implements Serializable {

    private int id;
    private String vetName;
    private String petName;
    private LocalDateTime appHour;
    private String description;

    public String formattedAppHour() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return appHour.format(formatter);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public LocalDateTime getAppHour() {
        return appHour;
    }

    public void setAppHour(LocalDateTime appHour) {
        this.appHour = appHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
