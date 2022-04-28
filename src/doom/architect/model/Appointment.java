package doom.architect.model;

import java.time.LocalDate;
import java.util.UUID;

public class Appointment {

    public UUID uuid;
    public LocalDate localDate;
    public Customer customer;

    public Appointment(LocalDate localDate) {
        this(UUID.randomUUID(), localDate);
    }

    public Appointment(UUID uuid, LocalDate localDate) {
        this.uuid = uuid;
        this.localDate = localDate;
    }

}
