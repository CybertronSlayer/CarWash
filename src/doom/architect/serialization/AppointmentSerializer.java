package doom.architect.serialization;

import doom.architect.model.Appointment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AppointmentSerializer {

    public static Path path = Path.of("C:\\Users\\Iwann\\Desktop\\ObjectRelations\\data\\appointments.txt");

    public void saveAppointments(List<Appointment> appointments){
        ArrayList<String> lines = new ArrayList<>();
        for (Appointment appointment : appointments) {
            String line = toLine(appointment);
            lines.add(line);
        }
        saveToFile(lines);
    }

    private String toLine(Appointment appointment) {
        return appointment.uuid + " " + appointment.localDate;
    }

    protected void saveToFile(List<String> lines){
        try {
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
