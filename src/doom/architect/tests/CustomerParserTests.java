package doom.architect.tests;

import doom.architect.model.Appointment;
import doom.architect.model.Customer;
import doom.architect.serialization.CustomerParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomerParserTests extends CustomerParser {

    private List<String> lines;
    private List<Appointment> appointments;

    @Override
    protected List<String> getAllLines() {
        return lines;
    }

    @Override
    protected List<Appointment> getAllAppointments() {
        return appointments;
    }

    @Test
    @DisplayName("Parse a single customer that has no appointments")
    void parseASingleCustomerThatHasNoAppointments() {
        String firstName = "Ilias";
        String lastName = "Tzo";
        Long phoneNumber = 6987733263L;

        String line = firstName + " " + lastName + " " + phoneNumber + " ";
        lines = List.of(line);

        List<Customer> allCustomers = getAllCustomers();

        assertEquals(1, allCustomers.size());

        Customer customer = allCustomers.get(0);

        assertEquals(firstName, customer.firstName);
        assertEquals(lastName, customer.lastName);
        assertEquals(phoneNumber, customer.phoneNumber);
    }

    @Test
    @DisplayName("Parse a single customer with null phone number and no appointments")
    void parseASingleCustomerWithNullPhoneNumberAndNoAppointments() {
        String firstName = "Ilias";
        String lastName = "Tzo";

        String line = firstName + " " + lastName + " " + null + " ";
        lines = List.of(line);

        List<Customer> allCustomers = getAllCustomers();

        assertEquals(1, allCustomers.size());

        Customer customer = allCustomers.get(0);

        assertEquals(firstName, customer.firstName);
        assertEquals(lastName, customer.lastName);
        assertNull(customer.phoneNumber);
    }

    @Test
    @DisplayName("Parse a single customer that has 1 appointment")
    void parseASingleCustomerThatHas1Appointment() {
        String firstName = "Ilias";
        String lastName = "Tzo";
        Long phoneNumber = 6987733263L;
        UUID uuid = UUID.randomUUID();
        LocalDate date = LocalDate.now();
        Appointment appointment = new Appointment(uuid, date);

        String line = firstName + " " + lastName + " " + phoneNumber + " " + uuid;
        lines = List.of(line);
        appointments = List.of(appointment);

        List<Customer> allCustomers = getAllCustomers();

        assertEquals(1, allCustomers.size());

        Customer customer = allCustomers.get(0);

        assertEquals(firstName, customer.firstName);
        assertEquals(lastName, customer.lastName);
        assertEquals(phoneNumber, customer.phoneNumber);
        assertEquals(1, customer.appointments.size());
    }

    @Test
    @DisplayName("Parse a customer that multiple appointments")
    void parseACustomerThatMultipleAppointments() {
        String firstName = "Ilias";
        String lastName = "Tzo";
        Long phoneNumber = 6987733263L;
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        LocalDate date = LocalDate.now();
        Appointment appointment1 = new Appointment(uuid1, date);
        Appointment appointment2 = new Appointment(uuid2, date);

        var ids = uuid1 + "," + uuid2;
        String line = firstName + " " + lastName + " " + phoneNumber + " " + ids;
        lines = List.of(line);
        appointments = List.of(appointment1, appointment2);

        List<Customer> allCustomers = getAllCustomers();

        assertEquals(1, allCustomers.size());

        Customer customer = allCustomers.get(0);

        assertEquals(firstName, customer.firstName);
        assertEquals(lastName, customer.lastName);
        assertEquals(phoneNumber, customer.phoneNumber);
        assertEquals(2, customer.appointments.size());
    }

}
