package doom.architect.serialization;

import doom.architect.model.Appointment;
import doom.architect.model.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AppointmentParser {

    public List<Appointment> getAllAppointments(){
        List<String> lines = getLinesFromFile();
        ArrayList<Appointment> appointments = new ArrayList<>();
        for (String line : lines) {
            Appointment appointment = toAppointment(line);
            appointments.add(appointment);
        }
        return appointments;
    }

    private Appointment toAppointment(String line) {
        String[] attributes = line.split(" ");
        UUID uuid = UUID.fromString(attributes[0]);
        LocalDate date = LocalDate.parse(attributes[1]);
        return new Appointment(uuid, date);
    }

    protected List<String> getLinesFromFile() {
        try {
            return Files.readAllLines(AppointmentSerializer.path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
