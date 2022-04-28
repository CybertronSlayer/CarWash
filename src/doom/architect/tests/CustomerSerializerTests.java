package doom.architect.tests;

import doom.architect.model.Appointment;
import doom.architect.model.Customer;
import doom.architect.serialization.CustomerSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerSerializerTests extends CustomerSerializer {

    private List<String> lines;

    @Override
    protected void saveToFile(List<String> lines) {
        this.lines = lines;
    }

    @Test
    @DisplayName("Serialize a customer that has no appointments")
    void serializeACustomerThatHasNoAppointments() {
        String firstName = "Ilias";
        String lastName = "Tzo";
        long phoneNumber = 6987733263L;
        Customer customer = new Customer(firstName, lastName, phoneNumber);

        saveCustomers(List.of(customer));

        assertEquals(1, lines.size());
        var line = lines.get(0);
        var expectedLine = firstName + " " + lastName + " " + phoneNumber + " ";
        assertEquals(expectedLine, line);
    }

    @Test
    @DisplayName("Serialize a customer that has null phone and no appointments")
    void serializeACustomerThatHasNullPhoneAndNoAppointments() {
        String firstName = "Ilias";
        String lastName = "Tzo";

        Customer customer = new Customer(firstName, lastName);

        saveCustomers(List.of(customer));

        assertEquals(1, lines.size());
        var line = lines.get(0);
        var expectedLine = firstName + " " + lastName + " " + null + " ";
        System.out.println(expectedLine);
        assertEquals(expectedLine, line);
    }

    @Test
    @DisplayName("Serialize a customer with a single appointment")
    void serializeACustomerWithASingleAppointment() {
        String firstName = "Ilias";
        String lastName = "Tzo";
        long phoneNumber = 6987733263L;

        Customer customer = new Customer(firstName, lastName, phoneNumber);

        Appointment appointment = new Appointment(LocalDate.now());

        customer.newAppointment(appointment);

        saveCustomers(List.of(customer));

        assertEquals(1, lines.size());
        var line = lines.get(0);
        var expectedLine = firstName + " " + lastName + " " + phoneNumber + " " + appointment.uuid;
        assertEquals(expectedLine, line);
    }

    @Test
    @DisplayName("Serialize a customer that has multiple appointments")
    void serializeACustomerThatHasMultipleAppointments() {
        String firstName = "Ilias";
        String lastName = "Tzo";
        long phoneNumber = 6987733263L;
        Customer customer = new Customer(firstName, lastName, phoneNumber);

        Appointment appointment1 = new Appointment(LocalDate.now());
        Appointment appointment2 = new Appointment(LocalDate.now());

        customer.newAppointment(appointment1);
        customer.newAppointment(appointment2);

        saveCustomers(List.of(customer));

        assertEquals(1, lines.size());
        var line = lines.get(0);
        String appointmentIds = appointment1.uuid + "," + appointment2.uuid;
        var expectedLine = firstName + " " + lastName + " " + phoneNumber + " " + appointmentIds;
        assertEquals(expectedLine, line);
    }


}
