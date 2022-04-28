package doom.architect.tests;

import doom.architect.model.Appointment;
import doom.architect.serialization.AppointmentSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointSerializerTests extends AppointmentSerializer {

    private List<String> lines;

    @Override
    protected void saveToFile(List<String> lines) {
        this.lines = lines;
    }

    @Test
    @DisplayName("Serialize a single Appointment")
    void serializeASingleAppointment() {
        Appointment appointment = new Appointment(LocalDate.now());

        saveAppointments(List.of(appointment));

        assertEquals(1, lines.size());
        var line = lines.get(0);
        var expectedLine = appointment.uuid + " " + appointment.localDate;
        assertEquals(expectedLine, line);
    }
}
