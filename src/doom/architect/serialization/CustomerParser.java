package doom.architect.serialization;

import doom.architect.model.Appointment;
import doom.architect.model.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerParser {

    public List<Appointment> allAppointments;

    public List<Customer> getAllCustomers(){
        allAppointments = getAllAppointments();
        List<String> lines = getAllLines();
        ArrayList<Customer> customers = new ArrayList<>();
        for (String line : lines) {
            Customer customer = toCustomer(line);
            customers.add(customer);
        }
        return customers;
    }

    private Customer toCustomer(String line) {
        String[] attributes = line.split(" ");
        String phoneNumberStr = attributes[2];
        Long phoneNumber;
        if (phoneNumberStr.equals("null")){
            phoneNumber = null;
        }
        else {
            phoneNumber = Long.parseLong(phoneNumberStr);
        }
        Customer customer = new Customer(attributes[0], attributes[1], phoneNumber);
        if (attributes.length > 3) {
            String ids = attributes[3];
            findAppointments(ids, customer);
        }
        return customer;
    }

    private void findAppointments(String raw, Customer customer) {
        String[] ids = raw.split(",");
        for (String id : ids) {
            UUID uuid = UUID.fromString(id);
            Appointment appointment = findAppointment(uuid);
            if (appointment != null){
                customer.newAppointment(appointment);
            }
        }
    }

    private Appointment findAppointment(UUID uuid) {
        for (Appointment appointment : allAppointments) {
            if (appointment.uuid.equals(uuid)){
                return appointment;
            }
        }
        return null;
    }

    protected List<String> getAllLines() {
        try {
            return Files.readAllLines(CustomerSerializer.path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<Appointment> getAllAppointments() {
        AppointmentParser appointmentParser = new AppointmentParser();
        return appointmentParser.getAllAppointments();
    }
}
