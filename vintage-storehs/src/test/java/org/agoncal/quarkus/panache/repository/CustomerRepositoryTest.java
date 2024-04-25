package org.agoncal.quarkus.panache.repository;

//import io.quarkus.test.TestTransaction;
//import io.quarkus.test.junit.QuarkusTest;
//import jakarta.inject.Inject;
//import org.agoncal.quarkus.jpa.Customer;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.agoncal.quarkus.jpa.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class CustomerRepositoryTest {

    @Inject
    CustomerRepository customerRepository;


    @Test
    @TestTransaction
    public void should_create_and_find_customer(){
        assertTrue(customerRepository.listAllDans().size() <= customerRepository.count());
        Customer customer = new Customer("first_name","last_name","email");
        customerRepository.persist(customer);
        assertNotNull(customer.getId());

        customer = customerRepository.findById(customer.getId());
        assertEquals("last_name", customer.getLastName());
    }


//
//    @Inject
//    CustomerRepository repository;
//
//    @Test
//    @TestTransaction
//    public void shouldCreateAndFindACustomer() {
//        Customer customer = new Customer("first name", "last name", "email");
//
//        repository.persist(customer);
//        assertNotNull(customer.getId());
//
//        customer = repository.findById(customer.getId());
//        assertEquals("last name", customer.getLastName());
//    }
}