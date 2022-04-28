package doom.architect.tests;

import doom.architect.model.Appointment;
import doom.architect.serialization.AppointmentParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentParserTests extends AppointmentParser {

    List<String> lines;

    @Override
    protected List<String> getLinesFromFile() {
        return lines;
    }

    @Test
    @DisplayName("Parse a single appointment")
    void parseASingleAppointment() {
        UUID uuid = UUID.randomUUID();
        LocalDate date = LocalDate.now();
        String line = uuid + " " + date;
        lines = List.of(line);

        List<Appointment> allAppointments = getAllAppointments();

        assertEquals(1, allAppointments.size());

        Appointment appointment = allAppointments.get(0);

        assertEquals(uuid, appointment.uuid);
        assertEquals(date, appointment.localDate);
    }



}
